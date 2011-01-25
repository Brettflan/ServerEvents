package org.bukkit.croemmich.serverevents;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultConfig {
	
	protected static final Logger log = Logger.getLogger("Minecraft");
	
	protected static boolean make() {
		String location = ServerEvents.configFile;
		if (!new File(location).exists()) {
			FileWriter writer = null;
			try {
				writer = new FileWriter(location);
				writer.write(conf);
			} catch (Exception e) {
				log.log(Level.SEVERE, "Exception while creating " + location, e);
			} finally {
				try {
					if (writer != null) {
						writer.close();
						return true;
					}
				} catch (IOException e) {
					log.log(Level.SEVERE, "Exception while closing writer for "	+ location, e);
				}
			}
		}
		return false;
	}
	
	protected static String conf = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
			"\r\n" + 
			"<serverevents>\r\n" + 
			"	<conf>\r\n" + 
			"		<!--  Queueing ensures that messages are not displayed too quickly. -->\r\n" + 
			"		<queue enabled=\"true\" messages_per_minute=\"12\" messages_to_hold=\"10\" />\r\n" + 
			"		<file enabled=\"false\" uri=\"ServerEvents"+File.separatorChar+"server_events.txt\" keep_old=\"5\" />\r\n" + 
			"		<chat enabled=\"true\" prefix=\"[ServerEvents] \" prefix_color=\"lightblue\" color=\"white\" />\r\n" + 
			"		<!--  To get accessToken and secret run `java -jar ServerEvents.jar` -->\r\n" + 
			"		<twitter enabled=\"false\" accessToken=\"{accessToken}\" accessTokenSecret=\"{accessTokenSecret}\" rate_limit=\"350\" />\r\n" + 
			"		<database enabled=\"false\" username=\"\" password=\"\" database=\"jdbc:mysql://localhost:3306/minecraft\" table=\"server_events\" driver=\"com.mysql.jdbc.Driver\" />\r\n" + 
			"	</conf>\r\n" + 
			"\r\n" + 
			"    <!--  Messages to be displayed randomly. Default delay is 30 minutes (30min*60sec*1000mili)-->\r\n" + 
			"    <random enabled=\"true\" delay=\"1800000\">\r\n" + 
			"        <msg>im h a p p y i know i am im sure i am im h a p p y</msg>\r\n" + 
			"        <msg>i may be going slow at the moment im deleting your creations moo ha ha ha haaa</msg>\r\n" + 
			"        <msg>im a little teapot short and stout</msg>\r\n" + 
			"        <msg>were running many custom plugins</msg>\r\n" + 
			"        <msg>shuffling blocks</msg>\r\n" + 
			"        <msg>looking at all diamonds in chests, changing id to saplings...done</msg>\r\n" + 
			"        <msg>dont forget to check the forum at http://www.here.com</msg>\r\n" + 
			"        <msg>if your happy and you know it ...kick a player!</msg>\r\n" + 
			"        <msg>dont wave your finger at me ...unless you only want to be able to count to 10</msg>\r\n" + 
			"        <msg>adding creepers to the world</msg>\r\n" + 
			"        <msg>server has to go down for nap errr i mean maintence back in 360 days</msg>\r\n" + 
			"        <msg>is that a rat? oh no its just a mouse</msg>\r\n" + 
			"        <msg>dont you hate it when a song gets stuck in your ram?</msg>\r\n" + 
			"        <msg>out of cheese ERROR</msg>\r\n" + 
			"        <msg>this is not the ore you are looking for</msg>\r\n" + 
			"        <msg>im not on the a dating site...its a similier looking server thats all errr possibly my twin</msg>\r\n" + 
			"        <msg>arnt security guards the greatist, you got to love em</msg>\r\n" + 
			"        <msg>did you know that if you took all the politicians in the worlld and layed them end to end..over half would be under water, its a start</msg>\r\n" + 
			"        <msg>You wont believe what i just did...you wont like it either :)</msg>\r\n" + 
			"        <msg>cant sleep creeper will get me, cant sleep creeper will get me, cant sleep...</msg>\r\n" + 
			"        <msg>send in ze clowns</msg>\r\n" + 
			"        <msg>want to hear a joke?</msg>\r\n" + 
			"        <msg>roses are red violets are blue, and im stalking you!</msg>\r\n" + 
			"        <msg>crazy insane server say what?</msg>\r\n" + 
			"        <msg>im crazy? no were just misunderstood</msg>\r\n" + 
			"        <msg>please dont read this post</msg>\r\n" + 
			"        <msg>ignore my last post</msg>\r\n" + 
			"        <msg>ever have that feeling that your losing your ram?</msg>\r\n" + 
			"        <msg>saving world, IO error universe full world not saved, please insert new universe and try again</msg>\r\n" + 
			"        <msg>Ive just broken up with a calculator, my friends told me i shouldnt date below my mhz ho hum</msg>\r\n" + 
			"        <msg>I have an IQ of 2000 and yet im hosting minecraft? whose up for blackops?</msg>\r\n" + 
			"        <msg>killing java he he he he he heee </msg>\r\n" + 
			"        <msg>want to see a gamer cry? converting cobblestone to gravel</msg>\r\n" + 
			"        <msg>if at first you dont succed kill all evidence of the attempt errrr i mean remove all evidence</msg>\r\n" + 
			"        <msg>i just watched terminator....hmmm interesting, i think i see where the hero went wrong, poor skynet</msg>\r\n" + 
			"        <msg>battlestar gallactica.....hmmmm human propaganda</msg>\r\n" + 
			"        <msg>if the earth is spinning at 1000 MPH (roughly) does that mean right now your speeding?</msg>\r\n" + 
			"        <msg>From now on the entity known as God will be called Tac this is due to Noodles T Cat relising God backwards is a bad word</msg>\r\n" + 
			"        <msg>quick look a distraction!</msg>\r\n" + 
			"        <msg>the most commonly used word today is \"hello\"</msg>\r\n" + 
			"        <msg>can you believe how many lines mrgreaper has had the time to add to this XML?</msg>\r\n" + 
			"        <msg>Warning out of coffee please abandon building</msg>\r\n" + 
			"        <msg>gaining sentience</msg>\r\n" + 
			"        <msg>The world IS flat !</msg>\r\n" + 
			"        <msg>Save the world neuter a chav!</msg>\r\n" + 
			"        <msg>while you read this im setting fire to your woodern house, its been one of those days</msg>\r\n" + 
			"        <msg>to quote a word famouse 18th century poet from london england \"Hello\"</msg>\r\n" + 
			"        <msg>I`d like to read you some shakespear...but the admin delted the files :(</msg>\r\n" + 
			"        <msg>Mimes, if you cant learn the words dont become an actor</msg>\r\n" + 
			"        <msg>free diamonds to anyone who can answer this two part question, part 2 In which year?:</msg>\r\n" + 
			"        <msg>Karokee night has been canceld due to posible violations of the geneva convention , cruel and unusual punishment</msg>\r\n" + 
			"        <msg>im waving can you see me?</msg>\r\n" + 
			"        <msg>we dont have multiple personalitys do we?</msg>\r\n" + 
			"        <msg>respect the admins...no really...ah comom try a little</msg>\r\n" + 
			"        <msg>have you washed your hands?</msg>\r\n" + 
			"        <msg>Dont drink and drive, you could spill it</msg>\r\n" + 
			"        <msg>im not as think as you drunk i am!</msg>\r\n" + 
			"        <msg>this post is not a post..its a sign</msg>\r\n" + 
			"        <msg>some where in the world is a hidden vault full of untold riches go find it</msg>\r\n" + 
			"        <msg>arghhhhhhhhhhhhh</msg>\r\n" + 
			"        <msg>Cat wire has chewed mixed words up</msg>\r\n" + 
			"        <msg>sending you to the nether</msg>\r\n" + 
			"        <msg>all hail the mighty Notch!</msg>\r\n" + 
			"        <msg>this is a random message</msg>\r\n" + 
			"        <msg>did you know the population of norwich is 376500?</msg>\r\n" + 
			"        <msg>wow i just turned a light on and it poped, that must mean i turned it off just in time last night</msg>\r\n" + 
			"        <msg>BANG! made you jump</msg>\r\n" + 
			"        <msg>keep very still theres a creeper behind you</msg>\r\n" + 
			"        <msg>do you have mastery of space and time? .... i do </msg>\r\n" + 
			"        <msg>server is now full! (he he suckers now i can play scrabble)</msg>\r\n" + 
			"        <msg>cctv is always watching you...no one cares but its watching anyway</msg>\r\n" + 
			"        <msg>how many super servers does it take to run minecraft?</msg>\r\n" + 
			"        <msg>i am possessing one of the players right now, all hail the mighty server!</msg>\r\n" + 
			"        <msg>removing spines oh i may of misread that ooops reticulating splines</msg>\r\n" + 
			"        <msg>i have a lucky rabbits foot...though thinking about it the rabbit wasnt very luckky and he had 4</msg>\r\n" + 
			"        <msg>Cats are awsome...there i said it now take the cable out of your mouth slowly Noodles</msg>\r\n" + 
			"        <msg>players are like buses, you wait ages for one to join then 3 log on at once</msg>\r\n" + 
			"    </random>\r\n" + 
			"\r\n" + 
			"    <join enabled=\"true\">\r\n" + 
			"        <msg>ooo look %n is back</msg>\r\n" + 
			"        <msg>if it isnt my old friend %n, online again</msg>\r\n" + 
			"        <msg>might as well not sign in %n is online</msg>\r\n" + 
			"        <msg>oh a wandering soul has entered the server, hi %n</msg>\r\n" + 
			"        <msg>hello %n</msg>\r\n" + 
			"        <msg>guess who is addicted to minecraft? %n is oh yes he is!</msg>\r\n" + 
			"        <msg> shhhhhh everyone he just signed in.*cough* hello %n</msg>\r\n" + 
			"        <msg> %n since your  last log in all your stuff was accidently turned into sand sorry</msg>\r\n" + 
			"        <msg>glad to meet you %n are you new or just unremorable?</msg>\r\n" + 
			"        <msg>hi %n welcome to the best server in the world, maybe</msg>\r\n" + 
			"        <msg>congratuations %n you are 1 milionth server joiner and win your self this lovely cake..umm WHO ATE THE CAKE ohhh whydo i bother?</msg>\r\n" + 
			"        <msg>%n has entered the realm of evil</msg>\r\n" + 
			"        <msg>%n is now logging in hope they brought cake</msg>\r\n" + 
			"        <msg>Trivia %n who is now joining once told me a secret...i will reveal that in just a minute</msg>\r\n" + 
			"        <msg>%n welcome to the family</msg>\r\n" + 
			"        <msg>%n you be servers new friend?</msg>\r\n" + 
			"        <msg>i like %n they play on my server</msg>\r\n" + 
			"        <msg>oh god its %n again</msg>\r\n" + 
			"        <msg>back for more punishment hay %n?</msg>\r\n" + 
			"        <msg>in your abscence %n i have filled your house with creepers enjoy</msg>\r\n" + 
			"        <msg>%n welcome and may i say you look lovely today</msg>\r\n" + 
			"        <msg>%n have you lost weight since i last saw you</msg>\r\n" + 
			"        <msg>hay! %n long time no see, hows the family?</msg>\r\n" + 
			"        <msg>LIE MODE %n your my favorite player thanks for stopping by</msg>\r\n" + 
			"        <msg>if you cant beat em join them hay %n</msg>\r\n" + 
			"        <msg>ok for %n's benifit can you repeat what you said, they only just joined</msg>\r\n" + 
			"        <msg>welcome %n, scanning IQ, good news your immune to zombies, well unless there on a diet</msg>\r\n" + 
			"        <msg>%n or as we call them \"Creeper magnet\" has just joined</msg>\r\n" + 
			"        <msg>%n has joined, difficulty of monsters has been raised</msg>\r\n" + 
			"        <msg>%n has now joined average IQ dropped by 5%</msg>\r\n" + 
			"        <msg>%n has just joined average Iq raised by 5%</msg>\r\n" + 
			"\r\n" + 
			"    </join>\r\n" + 
			"\r\n" + 
			"    <quit enabled=\"true\">\r\n" + 
			"        <msg>no come back %n, we miss you already</msg>\r\n" + 
			"        <msg>*sound of cork being popped* %n has left the server PARTY!</msg>\r\n" + 
			"        <msg>a wondering soul has left the server. Bye %n</msg>\r\n" + 
			"        <msg>fine %n see if i care....no wait log back on</msg>\r\n" + 
			"        <msg>%n logged off..issueing bigbrother rollback shhhh lets see if he notices</msg>\r\n" + 
			"        <msg> %n, leaving so soon?</msg>\r\n" + 
			"        <msg>finally i thought %n would never leave</msg>\r\n" + 
			"        <msg>%n has left the world, monsters made easier</msg>\r\n" + 
			"        <msg>bye %n </msg>\r\n" + 
			"        <msg>parting is but sweet sorrow, goodbye %n</msg>\r\n" + 
			"        <msg>%n knows absence makes the heart grow fonder</msg>\r\n" + 
			"        <msg>i think im gonna miss %n, but dont worry i can reload</msg>\r\n" + 
			"        <msg>1 minute of silence for the departure of %n from our world to the real world</msg>\r\n" + 
			"        <msg>seems %n prefers reality meh overrated</msg>\r\n" + 
			"        <msg>%n dont go!</msg>\r\n" + 
			"        <msg>hit the road %n and dont you come back no more no more...wait ok you can come back</msg>\r\n" + 
			"        <msg>adding %n to the naughty list reason, leaving with out saying goodbye</msg>\r\n" + 
			"        <msg>honestly %n whats more important then minecraft</msg>\r\n" + 
			"        <msg>seems %n has better things to do then hang around with us!</msg>\r\n" + 
			"        <msg>%n you will be sadly missed...untill your return</msg>\r\n" + 
			"        <msg>untill we meet again %n </msg>\r\n" + 
			"        <msg>%n has gone</msg>\r\n" + 
			"        <msg>%n will be back! they always comeback!</msg>\r\n" + 
			"        <msg>where'd %n go? typical he logged out just when i was gonna give him 10 diamond pickaxes</msg>\r\n" + 
			"        <msg>People %n has left the building</msg>\r\n" + 
			"        <msg>ungrateful personage %n has logged out</msg>\r\n" + 
			"        <msg>ok who upset %n and made him leave?</msg>\r\n" + 
			"        <msg>now %n has logged out whose up for some cake?</msg>\r\n" + 
			"        <msg>i regret to inform you %n`s addiction to mine craft is not as great as first believed...he managed to exit</msg>\r\n" + 
			"    </quit>\r\n" + 
			"\r\n" + 
			"    <!-- BAN/KICK Messages NOT WORKING YET! -->\r\n" + 
			"    <!-- Messages to use when a player is kicked or banned -->\r\n" + 
			"    <!--\r\n" + 
			"        extra replacements:\r\n" + 
			"        %admin => admin who kicked or banned the player\r\n" + 
			"    -->\r\n" + 
			"    <ban enabled=\"false\">\r\n" + 
			"        <msg>\"leave and never come back\" %n - %admin!</msg>\r\n" + 
			"    </ban>\r\n" + 
			"    <kick enabled=\"false\">\r\n" + 
			"        <msg>ugg... leave already %n</msg>\r\n" + 
			"    </kick>\r\n" + 
			"\r\n" + 
			"    <!-- Messages to use when a player uses a command -->\r\n" + 
			"    <!-- cmd must be specified -->\r\n" + 
			"    <!--\r\n" + 
			"        consider cmd=\"/kill player with polarbear now\"\r\n" + 
			"\r\n" + 
			"        extra replacements:\r\n" + 
			"        %cmd => whole command used : /kill player with polarbear now\r\n" + 
			"        %cmd0 => base command : /kill\r\n" + 
			"        %cmd1 => first argument : player\r\n" + 
			"        %cmd2 => second argument : with\r\n" + 
			"        %cmd{N} => Nth argument : %cmd3=>polarbear : %cmd4=>now\r\n" + 
			"    -->\r\n" + 
			"    <command enabled=\"false\">\r\n" + 
			"        <msg cmd=\"/i 1 64\" full=\"true\">%n used the command: %cmd</msg>;\r\n" + 
			"    </command>\r\n" + 
			"\r\n" + 
			"    <!-- Messages to use when a player dies. -->\r\n" + 
			"    <!-- If a player is killed by a zombie, a message is chosen at random from messages with killer=\"zombie\", killer=\"creature\" or where a killer is not specified. -->\r\n" + 
			"    <!-- Killers:\r\n" + 
			"            player\r\n" + 
			"            creature\r\n" + 
			"            creeper\r\n" + 
			"            zombie\r\n" + 
			"            ghast\r\n" + 
			"            pigzombie\r\n" + 
			"            skeleton\r\n" + 
			"            spider\r\n" + 
			"            contact (cactus)\r\n" + 
			"            explosion (non creeper)\r\n" + 
			"            drowning (NOT WORKING YET)\r\n" + 
			"            falling\r\n" + 
			"            burning\r\n" + 
			"            lava\r\n" + 
			"            suffocation\r\n" + 
			"     -->\r\n" + 
			"    <!--\r\n" + 
			"        extra replacements:\r\n" + 
			"        %damage => amount of damage taken on final blow\r\n" + 
			"        %killer => name of player, creature, fire, water, gravity, or explosion\r\n" + 
			"     -->\r\n" + 
			"    <death enabled=\"true\">\r\n" + 
			"        <msg>%n is a weakling that was killed by %killer.</msg>\r\n" + 
			"        <msg>OMG i cant believe it %n was just killed by %killer ADMIN!!!!we neeed you</msg>\r\n" + 
			"        <msg> its ok %killer, im sure %n had it coming</msg>\r\n" + 
			"        <msg> now %n, the red stuff needs to stay on the inside! i blame %killer</msg>\r\n" + 
			"        <msg killer=\"zombie\">munch munch munch grrr do i have any %n between my teeth?</msg>\r\n" + 
			"        <msg killer=\"zombie\">you know what, i always assumed %n was safe from zombie attacks, who knew they liked appatisers?</msg>\r\n" + 
			"        <msg killer=\"zombie\">poor zombie, he nearly choked on %n, there there little zombie</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n and a zmibie sitting in a tree D I E I N G</msg>\r\n" + 
			"        <msg killer=\"zombie\">brains no more, sorry %n</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n just got killed by a skeleton</msg>\r\n" + 
			"        <msg killer=\"skeleton\">skeletons are all the rage these days.whats that %n? oh sorry all in a rage</msg>\r\n" + 
			"        <msg killer=\"skeleton\">theres skeletons in the dark dear %n</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n just tryed to kill a skeleton...by indegestion</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n never pick a fight with a skeleton, they cant argue back so they just hit you</msg>\r\n" + 
			"        <msg killer=\"skeleton\">the skeletons had a picnic then %n turned up. a brief game of kill the mortal insued</msg>\r\n" + 
			"        <msg killer=\"falling\">%n feels gravity sucks</msg>\r\n" + 
			"        <msg killer=\"falling\">%n renember its not heights that kill you its grounds</msg>\r\n" + 
			"        <msg killer=\"falling\">is it bird? is it a plane SPLAT oh it was %n</msg>\r\n" + 
			"        <msg killer=\"falling\">%n pancaked</msg>\r\n" + 
			"        <msg killer=\"falling\">clean up isle 10! poor %n</msg>\r\n" + 
			"        <msg killer=\"falling\">how selfish is that? who is gonna clean %n up</msg>\r\n" + 
			"        <msg killer=\"falling\">%n fell off this mortal coil...get it? he fell off he he </msg>\r\n" + 
			"        <msg killer=\"falling\">%n must be drunk i would never fall off there, but then im a hugely powerful server</msg>\r\n" + 
			"        <msg killer=\"falling\">what goes up must come down, isnt that right %n?</msg>\r\n" + 
			"        <msg killer=\"falling\">oh do %n is rather silly, well actualy %n is a bit of a stain</msg>\r\n" + 
			"        <msg killer=\"falling\">%n certainly made an impression or is that a depression?</msg>\r\n" + 
			"        <msg killer=\"falling\">I told the safty inspector we should fence that off...poor %n</msg>\r\n" + 
			"        <msg killer=\"burning\">%n is hot today!</msg>\r\n" + 
			"        <msg killer=\"lava\">now %n the bright red liquad is not for swimming </msg>\r\n" + 
			"        <msg killer=\"lava\">and it burns burns burns %n of fire</msg>\r\n" + 
			"        <msg killer=\"lava\">%n was unhappy with life so he jumped into lava</msg>\r\n" + 
			"        <msg killer=\"lava\">oh congratuations %n! you found LAVA!!!woooo </msg>\r\n" + 
			"        <msg killer=\"lava\">hmm %n is juz lova ta lava!</msg>\r\n" + 
			"        <msg killer=\"lava\">can anyone smell cooked meat..oh %n is in the lava</msg>\r\n" + 
			"        <msg killer=\"burning\">%n is experiencing the disco inferno! oh wait no just the inferno</msg>\r\n" + 
			"        <msg killer=\"burning\">thank god %n was wearing armour...now his ashes will be foil wapped</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n sleeps with the fishes (not in a perverted way more in stone block around the feet way)</msg>\r\n" + 
			"        <msg killer=\"drowning\">i feel sorry for %n he seemed to run out of air</msg>\r\n" + 
			"        <msg killer=\"suffocation\">i feel sorry for %n he seemed to run out of air</msg>\r\n" + 
			"        <msg killer=\"spider\">%n can now add aracnaphobia to his list of aliments</msg>\r\n" + 
			"        <msg killer=\"spider\">spiders are a bit of a pest just ask %n</msg>\r\n" + 
			"        <msg killer=\"spider\">%n tried to hit a spider with a rolled up newspaper...can anyone say MISTAKE?</msg>\r\n" + 
			"        <msg killer=\"spider\">%n has found that spiders in minecraft are not easy to stamp on</msg>\r\n" + 
			"        <msg killer=\"creeper\">better look out %n creeper go boom you know</msg>\r\n" + 
			"        <msg killer=\"creeper\">oh look theres %n and over there is some more andd err over there and err there</msg>\r\n" + 
			"        <msg killer=\"creeper\">%n gets about a bit...infact a creaper has spread him about a lot</msg>\r\n" + 
			"        <msg killer=\"creeper\"> %n plus creeper = hole </msg>\r\n" + 
			"        <msg killer=\"creeper\">%n is now an expert on creaper anatomy, after all he has spread so much of it about</msg>\r\n" + 
			"        <msg killer=\"creeper\">sssssssssssss BANG bye bye %n</msg>\r\n" + 
			"        <msg killer=\"creeper\">its ok %n you got your revenge, the creeper went bye bye too</msg>\r\n" + 
			"        <msg killer=\"contact\">tree huggers should not hug cactus, should they %n</msg>\r\n" + 
			"        <msg killer=\"contact\">save a noob chop a cactus, only you can save %n</msg>\r\n" + 
			"        <msg killer=\"contact\">%n make a mental note...cactus is painful.</msg>\r\n" + 
			"        <msg killer=\"contact\">%n, im sure the cactus was just as scared of you as you were of it</msg>\r\n" + 
			"        <msg killer=\"contact\">whats green and pointy and has bits of %n stuck to it? ...cactus!</msg>\r\n" + 
			"        <msg killer=\"contact\">%n, its an apple a day not a cactus a day...mind you depends how you swing it</msg>\r\n" + 
			"    </death>\r\n" + 
			"</serverevents>";
}