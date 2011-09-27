package com.wimbli.serverevents;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.wimbli.serverevents.DataSource.Type;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DataParser {
	
	protected static final Logger log = Logger.getLogger("Minecraft");
	
	private ServerEvents plugin;
	
	protected DataParser(ServerEvents plugin) {
		this.plugin = plugin;
	}
	
	protected boolean load (String url) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DataHandler handler = new DataHandler();
			saxParser.parse(url, handler);
			return true;
		} catch (SAXException e) {
			log.severe(e.getMessage());
		} catch (IOException e2) {
			log.severe(e2.getMessage());
		} catch (ParserConfigurationException e3) {
			log.severe(e3.getMessage());
		}
		return false;
	}

	protected class DataHandler extends DefaultHandler {
		boolean conf = false;
		boolean queue = false;
		boolean file = false;
		boolean twitter = false;
		boolean database = false;
		boolean chat = false;
		boolean random = false;
		boolean join = false;
		boolean quit = false;
		boolean ban = false;
		boolean kick = false;
		boolean command = false;
		boolean death = false;
		boolean block = false;
		boolean msg = false;
		boolean enabled = true;
		boolean start = false;
		boolean stop = false;
		
		String msgString = "";
		Message curMessage;
		
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if (qName.equalsIgnoreCase("msg")) {
				msg = true;
				curMessage = new Message();
				for (int i=0; i<attributes.getLength(); i++) {
					curMessage.addParam(attributes.getLocalName(i), attributes.getValue(i));
				}
				return;
			}
			
			String enabledStr = attributes.getValue("enabled");
			if (enabledStr != null) {
				if (enabledStr.equalsIgnoreCase("false") || enabledStr.equalsIgnoreCase("no")) {
					enabled = false;
				} else {
					enabled = true;
				}
			}
			
			if (qName.equalsIgnoreCase("random") || qName.equalsIgnoreCase("join") || qName.equalsIgnoreCase("quit") || 
				qName.equalsIgnoreCase("ban") || qName.equalsIgnoreCase("kick") || qName.equalsIgnoreCase("command") || 
				qName.equalsIgnoreCase("death") || qName.equalsIgnoreCase("start") || qName.equalsIgnoreCase("stop")) {
				
				String fileStr = attributes.getValue("file");
				String chatStr = attributes.getValue("chat");
				String twitterStr = attributes.getValue("twitter");
				String databaseStr = attributes.getValue("database");
				
				if (fileStr != null && (fileStr.equalsIgnoreCase("false") || fileStr.equalsIgnoreCase("no"))) {
					DataSource.addToDisabled(Type.FILE, qName.toLowerCase());
				}
				
				if (chatStr != null && (chatStr.equalsIgnoreCase("false") || chatStr.equalsIgnoreCase("no"))) {
					DataSource.addToDisabled(Type.CHAT, qName.toLowerCase());
				}
				
				if (twitterStr != null && (twitterStr.equalsIgnoreCase("false") || twitterStr.equalsIgnoreCase("no"))) {
					DataSource.addToDisabled(Type.TWITTER, qName.toLowerCase());
				}
				
				if (databaseStr != null && (databaseStr.equalsIgnoreCase("false") || databaseStr.equalsIgnoreCase("no"))) {
					DataSource.addToDisabled(Type.DATABASE, qName.toLowerCase());
				}
			}

			if (qName.equalsIgnoreCase("conf")) {
				conf = true;
				enabled = true; // cant disable the conf
			} else if (qName.equalsIgnoreCase("preventDefaultMinecraftDeathMessages")) {
				DataSource.disableDefaultDeathMessages = enabled;
			} else if (qName.equalsIgnoreCase("queue")) {
				queue = true;
				DataSource.enableQueue = enabled;
				if (enabled) {
					String mpm = attributes.getValue("messages_per_minute");
					String hold = attributes.getValue("messages_to_hold");
					
					if (mpm != null) {
						try {
							int mpmi = Integer.parseInt(mpm);
							if (mpmi > 0) {
								DataSource.mpm = mpmi;
							} else {
								log.warning(ServerEvents.configFile + ": 'conf.queue.messages_per_minute' should be greater than 0");
							}
						} catch (Exception e) {
							log.warning(ServerEvents.configFile + ": 'conf.queue.messages_per_minute' should be an integer");
						}
					}
					
					if (hold != null) {
						try {
							int holdi = Integer.parseInt(hold);
							if (holdi > 0) {
								DataSource.hold = holdi;
							} else {
								log.warning(ServerEvents.configFile + ": 'conf.queue.messages_to_hold' should be greater than 0");
							}
						} catch (Exception e) {
							log.warning(ServerEvents.configFile + ": 'conf.queue.messages_to_hold' should be an integer");
						}
					}
				}
			} else if (qName.equalsIgnoreCase("file")) {
				file = true;
				if (enabled) {
					
					String tmpUri = attributes.getValue("uri");
					String tmpKeep = attributes.getValue("keep_old");
					
					String finalUri = "ServerEvents" + File.separatorChar + "server_events.txt";
					int finalKeep = 5;
					
					if (tmpUri == null || tmpUri.equals("")) {
						log.warning(ServerEvents.configFile + ": 'conf.file.uri' must be specified");
					}
					
					if (tmpKeep == null || tmpKeep.equals("")) {
						log.warning(ServerEvents.configFile + ": 'conf.file.keep_old' must be specified");
					} else {
						try {
							int keep = Integer.parseInt(tmpKeep);
							if (keep > 0) {
								finalKeep = keep;
							} else {
								log.warning(ServerEvents.configFile + ": 'conf.file.keep_old' should be greater than 0");
							}
						} catch (Exception e) {
							log.warning(ServerEvents.configFile + ": 'conf.file.keep_old' must an integer greater than 0");
						}
					}
					DataSource.addFileDataSource(finalUri, finalKeep);
				}
			} else if (qName.equalsIgnoreCase("twitter")) {
				twitter = true;
				if (enabled) {
					int rate = 350;
					String rate_limit = attributes.getValue("rate_limit");
					try {
						rate = Integer.parseInt(rate_limit);
						if (rate <= 0) {
							rate = 350;
							log.warning(ServerEvents.configFile + ": 'conf.twitter.rate_limit' must an integer greater than 0");
						}
					} catch (Exception e) {
						log.warning(ServerEvents.configFile + ": 'conf.twitter.rate_limit' must an integer greater than 0");
					}
					int hourOffset = 0;
					String ts_offset = attributes.getValue("timestamp_hour_offset");
					try {
						hourOffset = Integer.parseInt(ts_offset);
						if (hourOffset < -24 || hourOffset > 24) {
							hourOffset = 0;
							log.warning(ServerEvents.configFile + ": 'conf.twitter.timestamp_hour_offset' must an integer between -24 and 24");
						}
					} catch (Exception e) {
						log.warning(ServerEvents.configFile + ": 'conf.twitter.timestamp_hour_offset' must an integer between -24 and 24");
					}

					DataSource.addTwitterDataSource(attributes.getValue("accessToken"), attributes.getValue("accessTokenSecret"), rate, hourOffset);
				}
			} else if (qName.equalsIgnoreCase("database")) {
				database = true;
				if (enabled) {
					DataSource.addDatabaseDataSource(attributes.getValue("username"), attributes.getValue("password"), attributes.getValue("database"), attributes.getValue("table"), attributes.getValue("driver"));
				}
			} else if (qName.equalsIgnoreCase("chat")) {
				chat = true;
				if (enabled) {
					DataSource.addChatDataSource(plugin, attributes.getValue("prefix"), attributes.getValue("prefix_color"), attributes.getValue("color"));
				}
			} else if (qName.equalsIgnoreCase("random")) {
				random = true;
				try {
					int delay = Integer.parseInt(attributes.getValue("delay"));
					if (delay < 60000) {
						Messages.randomDelay = 60000;
					} else {
						Messages.randomDelay = delay;
					}
				} catch (Exception e) {}
			} else if (qName.equalsIgnoreCase("join")) {
				join = true;
			} else if (qName.equalsIgnoreCase("quit")) {
				quit = true;
			} else if (qName.equalsIgnoreCase("ban")) {
				ban = true;
			} else if (qName.equalsIgnoreCase("kick")) {
				kick = true;
			} else if (qName.equalsIgnoreCase("command")) {
				command = true;
			} else if (qName.equalsIgnoreCase("death")) {
				death = true;
			} else if (qName.equalsIgnoreCase("block")) {
				block = true;
			} else if (qName.equalsIgnoreCase("start")) {
				start = true;
			} else if (qName.equalsIgnoreCase("stop")) {
				stop = true;
			}
		}
		
		public void characters(char[] ch, int start, int length) {
			if (msg && enabled) {
				msgString += new String(ch, start, length);
			}
		}

		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (qName.equalsIgnoreCase("msg") && enabled) {
				msg = false;
				curMessage.setMessage(msgString);
				msgString = "";
				
				if (random) {
					Messages.addMessage(Messages.Type.RANDOM, curMessage);
				} else if (join) {
					Messages.addMessage(Messages.Type.JOIN, curMessage);
				} else if (quit) {
					Messages.addMessage(Messages.Type.QUIT, curMessage);
				} else if (ban) {
					Messages.addMessage(Messages.Type.BAN, curMessage);
				} else if (kick) {
					Messages.addMessage(Messages.Type.KICK, curMessage);
				} else if (command) {
					Messages.addMessage(Messages.Type.COMMAND, curMessage);
				} else if (death) {
					Messages.addMessage(Messages.Type.DEATH, curMessage);
				} else if (block) {
					Messages.addMessage(Messages.Type.BLOCK, curMessage);
				} else if (start) {
					Messages.addMessage(Messages.Type.START, curMessage);
				} else if (stop) {
					Messages.addMessage(Messages.Type.STOP, curMessage);
				}
			} else if (qName.equalsIgnoreCase("conf")) {
				conf = false;
			} else if (qName.equalsIgnoreCase("queue")) {
				queue = false;
			} else if (qName.equalsIgnoreCase("file")) {
				file = false;
			} else if (qName.equalsIgnoreCase("twitter")) {
				twitter = false;
			} else if (qName.equalsIgnoreCase("chat")) {
				chat = false;
			} else if (qName.equalsIgnoreCase("database")) {
				database = false;
			} else if (qName.equalsIgnoreCase("random")) {
				random = false;
			} else if (qName.equalsIgnoreCase("join")) {
				join = false;
			} else if (qName.equalsIgnoreCase("quit")) {
				quit = false;
			} else if (qName.equalsIgnoreCase("ban")) {
				ban = false;
			} else if (qName.equalsIgnoreCase("kick")) {
				kick = false;
			} else if (qName.equalsIgnoreCase("command")) {
				command = false;
			} else if (qName.equalsIgnoreCase("death")) {
				death = false;
			} else if (qName.equalsIgnoreCase("block")) {
				block = false;
			} else if (qName.equalsIgnoreCase("start")) {
				start = false;
			} else if (qName.equalsIgnoreCase("stop")) {
				stop = false;
			}
		}
	}
}