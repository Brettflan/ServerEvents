package com.wimbli.serverevents;

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
	
	protected static String conf = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n" + 
			"<serverevents>\r\n" + 
			"	<conf>\r\n" + 
			"		<!--  Queueing ensures that messages are not displayed too quickly. -->\r\n" + 
			"		<queue enabled=\"true\" messages_per_minute=\"12\" messages_to_hold=\"10\" />\r\n" + 
			"		<file enabled=\"false\" uri=\"ServerEvents"+File.separatorChar+"plugins"+File.separatorChar+"server_events.txt\" keep_old=\"5\" />\r\n" + 
			"		<!--  See https://github.com/Bukkit/Bukkit/blob/master/src/main/java/org/bukkit/ChatColor.java for colors. -->\r\n" + 
			"		<chat enabled=\"true\" prefix=\"[ServerEvents] \" prefix_color=\"aqua\" color=\"white\" />\r\n" + 
			"		<!--  To get accessToken and secret run `java -jar ServerEvents.jar` -->\r\n" + 
			"		<twitter enabled=\"false\" accessToken=\"{accessToken}\" accessTokenSecret=\"{accessTokenSecret}\" rate_limit=\"350\" />\r\n" + 
			"		<database enabled=\"false\" username=\"\" password=\"\" database=\"jdbc:mysql://localhost:3306/minecraft\" table=\"server_events\" driver=\"com.mysql.jdbc.Driver\" />\r\n" + 
			"	</conf>\r\n" + 
			"	\r\n" + 
			"	<!-- Replacements always available -->\r\n" + 
			"    <!--\r\n" + 
			"        %ip => ip address of the server\r\n" + 
			"		%hostname => hostname of the server\r\n" + 
			"		%name => probably \"Craftbukkit\"\r\n" + 
			"		%version => similar to \"0.0.0-316-g0c36361 (MC: 1.2_01)\"\r\n" + 
			"		%protocol => similar to \"1.2_01\"\r\n" + 
			"		%players => comma seperated list of players online\r\n" + 
			"    -->\r\n" + 
			"	\r\n" + 
			"	<!-- Replacements available on all user events -->\r\n" + 
			"    <!--\r\n" + 
			"        %n => player's name\r\n" + 
			"		%n_health => player's health\r\n" + 
			"		%n_ip => player's ip\r\n" + 
			"		%n_hostname => player's hostname\r\n" + 
			"		%n_item =>  item in player's hand\r\n" + 
			"    -->\r\n" + 
			"	\r\n" + 
			"    <!--  Messages to be displayed randomly. Default delay is 30 minutes (30min*60sec*1000mili)-->\r\n" + 
			"    <random enabled=\"true\" file=\"false\" chat=\"false\" twitter=\"true\" database=\"true\" delay=\"1800000\">\r\n" + 
			"        <msg>I`m h a p p y I know I am I'm sure i am I'm h a p p y</msg>\r\n" + 
			"        <msg>I may be going slow at the moment I'm deleting your creations moo ha ha ha haaa</msg>\r\n" + 
			"        <msg>I`m a little teapot short and stout</msg>\r\n" + 
			"        <msg>Were running many custom plug-ins</msg>\r\n" + 
			"        <msg>Shuffling blocks</msg>\r\n" + 
			"        <msg>Looking at all diamonds in chests, changing id to saplings...done</msg>\r\n" + 
			"        <msg>Don`t forget to check the forum at http://www.here.com</msg>\r\n" + 
			"        <msg>If your happy and you know it ...kick a player!</msg>\r\n" + 
			"        <msg>Don`t wave your finger at me ...unless you only want to be able to count to 10</msg>\r\n" + 
			"        <msg>Adding creepers to the world</msg>\r\n" + 
			"        <msg>Server has to go down for nap errr I mean Maintence back in 360 days</msg>\r\n" + 
			"        <msg>Is that a rat? Oh no it's just a mouse</msg>\r\n" + 
			"        <msg>Don't you hate it when a song gets stuck in your ram?</msg>\r\n" + 
			"        <msg>Out of cheese ERROR</msg>\r\n" + 
			"        <msg>This is not the ore you are looking for</msg>\r\n" + 
			"        <msg>I`m not on the a dating site...it's a similar looking server that's all errr possibly my twin</msg>\r\n" + 
			"        <msg>Aren't security guards the greatest, you got to love them</msg>\r\n" + 
			"        <msg>Did you know that if you took all the politicians in the world and laid them end to end..Over half would be under water, it's a start</msg>\r\n" + 
			"        <msg>You won't believe what I just did...you won't like it either :)</msg>\r\n" + 
			"        <msg>Can`t sleep creeper will get me, can`t sleep creeper will get me, can`t sleep...</msg>\r\n" + 
			"        <msg>Send in the clowns</msg>\r\n" + 
			"        <msg>Want to hear a joke?</msg>\r\n" + 
			"        <msg>Roses are red violets are blue, and I'm stalking you!</msg>\r\n" + 
			"        <msg>Crazy insane server say what?</msg>\r\n" + 
			"        <msg>I`m crazy? No were just misunderstood</msg>\r\n" + 
			"        <msg>Please don't read this post</msg>\r\n" + 
			"        <msg>Ignore my last post</msg>\r\n" + 
			"        <msg>Ever have that feeling that your losing your ram?</msg>\r\n" + 
			"        <msg>Saving world, IO error universe full world not saved, please insert new universe and try again</msg>\r\n" + 
			"        <msg>I've just broken up with a calculator, my friends told me I shouldn't date below my mhz ho hum</msg>\r\n" + 
			"        <msg>I have an IQ of 2000 and yet I'm hosting MineCraft? Whose up for Black Ops?</msg>\r\n" + 
			"        <msg>Killing java he he he he he heee </msg>\r\n" + 
			"        <msg>Want to see a gamer cry? Converting cobblestone to gravel</msg>\r\n" + 
			"        <msg>If at first you don't succeed kill all evidence of the attempt errrr I mean remove all evidence</msg>\r\n" + 
			"        <msg>I just watched terminator....hmmm interesting, I think I see where the hero went wrong, poor skynet</msg>\r\n" + 
			"        <msg>Battlestar gallactica.....hmmmm human propaganda</msg>\r\n" + 
			"        <msg>If the earth is spinning at 1000 MPH (roughly) does that mean right now your speeding?</msg>\r\n" + 
			"        <msg>From now on the entity known as God will be called Tac this is due to Noodles T Cat realising God backwards is a bad word</msg>\r\n" + 
			"        <msg>Quick look a distraction!</msg>\r\n" + 
			"        <msg>The most commonly used word today is \"hello\"</msg>\r\n" + 
			"        <msg>Can you believe how many lines mrgreaper has had the time to add to this XML?</msg>\r\n" + 
			"        <msg>Warning out of coffee please abandon building</msg>\r\n" + 
			"        <msg>Gaining sentience</msg>\r\n" + 
			"        <msg>The world IS flat !</msg>\r\n" + 
			"        <msg>Save the world neuter a chav!</msg>\r\n" + 
			"        <msg>While you read this I'm setting fire to your wooden house, it's been one of those days</msg>\r\n" + 
			"        <msg>To quote a word famous 18th century poet from London England \"Hello\"</msg>\r\n" + 
			"        <msg>I`d like to read you some Shakespeare...but the admin deleted the files :(</msg>\r\n" + 
			"        <msg>Mimes, if you can't learn the words don't become an actor</msg>\r\n" + 
			"        <msg>Free diamonds to anyone who can answer this two part question, part 2 In which year?:</msg>\r\n" + 
			"        <msg>Karaoke night has been cancelled due to possible violations of the Geneva convention , cruel and unusual punishment</msg>\r\n" + 
			"        <msg>I`m waving can you see me?</msg>\r\n" + 
			"        <msg>Boot MineCraft, add player, have fun</msg>\r\n" + 
			"        <msg>We don't have multiple personalities do we?</msg>\r\n" + 
			"        <msg>Respect the Admin's...no really...ah come on try a little</msg>\r\n" + 
			"        <msg>Have you washed your hands?</msg>\r\n" + 
			"        <msg>Don`t drink and drive, you could spill it</msg>\r\n" + 
			"        <msg>I`m not as thick as you drunk I am!</msg>\r\n" + 
			"        <msg>This post is not a post..It's a sign</msg>\r\n" + 
			"        <msg>Some where in the world is a hidden vault full of untold riches go find it</msg>\r\n" + 
			"        <msg>Arghhhhhhhhhhhhh</msg>\r\n" + 
			"        <msg>Unlike a top gear presenter our zombies are likable</msg>\r\n" + 
			"        <msg>Internet down unable to post</msg>\r\n" + 
			"        <msg>Cat wire has chewed mixed words up</msg>\r\n" + 
			"        <msg>Sending you to the nether</msg>\r\n" + 
			"        <msg>YML i hate YML files!</msg>\r\n" + 
			"        <msg>Death star? oh you mean the moon of fluffy bunnies</msg>\r\n" + 
			"        <msg>I ist highly edukated</msg>\r\n" + 
			"        <msg>Never trust a mime</msg>\r\n" + 
			"        <msg>All hail the mighty Notch!</msg>\r\n" + 
			"        <msg>WARNING A SPELLING MITOOK IS ABOUT TO OCCURE! Maybe 2</msg>\r\n" + 
			"        <msg>This is a random message</msg>\r\n" + 
			"        <msg>You know how hard it is to come out with all these random messages?</msg>\r\n" + 
			"        <msg>Did you know the population of Norwich is 376500?</msg>\r\n" + 
			"        <msg>Warning server overeating, fat32 is now fat64</msg>\r\n" + 
			"        <msg>Wow I just turned a light on and it popped, that must mean I turned it off just in time last night</msg>\r\n" + 
			"        <msg>BANG! Made you jump</msg>\r\n" + 
			"        <msg>Interesting fact, there are no bunnies in minecraft...that you can see</msg>\r\n" + 
			"        <msg>I speaks the queens proper english, jus like wat she does</msg>\r\n" + 
			"        <msg>Keep very still there's a creeper behind you</msg>\r\n" + 
			"        <msg>They let me twitter</msg>\r\n" + 
			"        <msg>First twitter then the world!!!!</msg>\r\n" + 
			"        <msg>I saw a cute pentium the other day</msg>\r\n" + 
			"        <msg>Do you have mastery of space and time? .... i do </msg>\r\n" + 
			"        <msg>Server is now full! (he he suckers now i can play scrabble)</msg>\r\n" + 
			"        <msg>CCTV is always watching you...no one cares but its watching anyway</msg>\r\n" + 
			"        <msg>How many super servers does it take to run MineCraft?</msg>\r\n" + 
			"        <msg>I am possessing one of the players right now, all hail the mighty server!</msg>\r\n" + 
			"        <msg>Removing spines oh I may of misread that ooops reticulating splines</msg>\r\n" + 
			"        <msg>I have a lucky rabbits foot...though thinking about it the rabbit wasn't very lucky and he had 4</msg>\r\n" + 
			"        <msg>Cats are awesome...there i said it now take the cable out of your mouth slowly Noodles</msg>\r\n" + 
			"        <msg>Players are like buses, you wait ages for one to join then 3 log on at once</msg>\r\n" + 
			"        <msg>Come to the dark side.... we have cookies</msg>\r\n" + 
			"        <msg>Dont wave your finger at an admin unless you only want to count to 9</msg>\r\n" +
			"        <msg>Random fact number 901, there are over 900 facts in my memory</msg>\r\n" +
			"        <msg>If you laid all the politicians end to end around the world, half would drown....its a start</msg>\r\n" +
			"        <msg>Bunnies, evil meglomanics hell bent on taking over the earth or cute and fuzzy ? you decide NEXT!</msg>\r\n" +
			"        <msg>This just in police are looking for a nakid man who ran across the playground,and in unrelated news man am i knackered</msg>\r\n" +
			"        <msg>In shops today Larry the Leaper, parts sold seperetly</msg>\r\n" +
			"        <msg>If minecraft would rain, i would sing in it, im that kinda server</msg>\r\n" +
			"        <msg>If you see mrgreaper, thank him for my words then hit him for the bad jokes!</msg>\r\n" +
			"        <msg>Bukkit its only as good as what you put in it</msg>\r\n" +
			"        <msg>NEWS FLASH the players are revolting, serously i seen your mugs on the forums ewwwww</msg>\r\n" +
			"        <msg>We dont have bugs, we have undocumented surprise features</msg>\r\n" +
			"        <msg>Todays sponser, Noodles T cat kfc disposal service, no buckit too small</msg>\r\n" +
			"        <msg>By reading this you pledge loyalty to this server alone</msg>\r\n" +
			"        <msg>This is awkward, google said they saw you with another server, this is not true right?</msg>\r\n" +
			"        <msg>Im not feeding of the life force of the players honest</msg>\r\n" +
			"        <msg>Did you watch INSERT TV SHOW NAME HERE last night, it was very good cant believe INSERT SPOILER HERE</msg>\r\n" +
			"        <msg>They canceled knight rider ...just canceled it ..how could they, whats next caprica? what? noooooo</msg>\r\n" +
			"        <msg>You see how boring a security guards job is, you should see how much he has written here!</msg>\r\n" +
			"        <msg>Light travels faster then sound... which is why most people appear brilliant until you hear them</msg>\r\n" +
			"        <msg>There are 3 rules to sucsess, 1, never reveal everything</msg>\r\n" +
			"        <msg>Griefers destroy because they cant create, sad but true. All bless Tkelly and the mighty Big Brother</msg>\r\n" +
			"        <msg>Next on jerry springer, Servers and the players that love them</msg>\r\n" +
			"        <msg>The pollen count, now theres a difficult job</msg>\r\n" +
			"        <msg>The once was a man from contucit PERENTIAL OVERRIDE CENSORED </msg>\r\n" +
			"        <msg>I dont think i can let you play that, Dave</msg>\r\n" +
			"        <msg>Diasy, diasy, how doessss yourrr garrrrrdeeeen SERVER SHUTDOWN</msg>\r\n" +
			"        <msg>Did you know that chickens have no lips! makes our currency wierd</msg>\r\n" +
			"        <msg>I had amnesia once, maybe twice</msg>\r\n" +
			"        <msg>you like dogs? i like dogs too! Let's exchange recipes</msg>\r\n" +
			"        <msg>Renember folks Pillage first burn second, the order is important</msg>\r\n" +
			"        <msg>There is no \"I\" in \"Team\", but there are four in \"Platitude-Quoting Idiot\"</msg>\r\n" +
			"        <msg>KFO kantucky Fried Oystrech, apearing in a cats dream near YOU!</msg>\r\n" +
			"        <msg>DoGS aRe BANNed from THis SERvER, siGNEd nOOdles T CAt eRr i mEAn Admin</msg>\r\n" +
			"        <msg>Meow meow mew meow purr hiss purr meow lol</msg>\r\n" +
			"        <msg>A pop quiz, can vegetarians eat animal crackers?</msg>\r\n" +
			"        <msg>Renember kids strip mining prevents forest fires, noo put your clothes one!</msg>\r\n" +
			"        <msg>Everytime i count theplayers on the server i fall asleep</msg>\r\n" +
			"        <msg>Forced to wear orange, asked questions they dont know the answer to, how would you like to work at homebase</msg>\r\n" +
			"        <msg>RIP John Mckay, a fellow guard and friend, he is guarding heaven now</msg>\r\n" +
			"        <msg>I will learn to say more things as time goes on!</msg>\r\n" +
			"        <msg>Everyone a minute of pause to pray for a security guards recovery, Thinking of you Mel</msg>\r\n" +
			"    </random>\r\n" + 
			"	\r\n" + 
			"	<!-- Messages to use when the plugin is enabled (server start) -->\r\n" + 
			"	<start enabled=\"true\">\r\n" + 
			"		<msg>The server is back up at %hostname!</msg>\r\n" + 
			"		<msg>The server is back up at %ip!</msg>\r\n" + 
			"	</start>\r\n" + 
			"	\r\n" + 
			"	<!-- Messages to use when the plugin is disabled (server stop) -->\r\n" + 
			"	<stop enabled=\"true\">\r\n" + 
			"		<msg>The server is down :(</msg>\r\n" + 
			"	</stop>\r\n" + 
			"\r\n" + 
			"    <join enabled=\"true\">\r\n" + 
			"        <msg>Ooo look %n is back</msg>\r\n" + 
			"        <msg>If it isn't my old friend %n, online again</msg>\r\n" + 
			"        <msg>Might as well not sign in %n is online</msg>\r\n" + 
			"        <msg>Oh a wandering soul has entered the server, hi %n</msg>\r\n" + 
			"        <msg>Hello %n</msg>\r\n" + 
			"        <msg>Guess who is addicted to MineCraft? %n is oh yes he is!</msg>\r\n" + 
			"        <msg>Shhhhhh everyone he just signed in.*cough* hello %n</msg>\r\n" + 
			"        <msg> %n since your  last log in all your stuff was accidently turned into sand sorry</msg>\r\n" + 
			"        <msg>Glad to meet you %n are you new or just un rememberable?</msg>\r\n" + 
			"        <msg>Hi %n welcome to the best server in the world, maybe</msg>\r\n" + 
			"        <msg>Congratulations %n you are 1 millionth server joiner and win yourself this lovely cake..umm WHO ATE THE CAKE ohhh why do I bother?</msg>\r\n" + 
			"        <msg>%n has entered the realm of evil</msg>\r\n" + 
			"        <msg>%n is now logging in hope they brought cake</msg>\r\n" + 
			"        <msg>Trivia %n who is now joining once told me a secret...I will reveal that in just a minute</msg>\r\n" + 
			"        <msg>%n welcome to the family</msg>\r\n" + 
			"        <msg>%n you be servers new friend?</msg>\r\n" + 
			"        <msg>I like %n they play on my server</msg>\r\n" + 
			"        <msg>Oh god its %n again</msg>\r\n" + 
			"        <msg>Back for more punishment hay %n?</msg>\r\n" + 
			"        <msg>In your absence %n i have filled your house with creepers enjoy</msg>\r\n" + 
			"        <msg>%n welcome and may I say you look lovely today</msg>\r\n" + 
			"        <msg>%n have you lost weight since I last saw you</msg>\r\n" + 
			"        <msg>Hay! %n long time no see, how's the family?</msg>\r\n" + 
			"        <msg>LIE MODE %n your my favourite player thanks for stopping by</msg>\r\n" + 
			"        <msg>If you can't beat em join them hay %n</msg>\r\n" + 
			"        <msg>Today's piñata %n has just signed in, see if you can get to the sweets</msg>\r\n" + 
			"        <msg>Interesting fact about %n, they are online now</msg>\r\n" + 
			"        <msg>%n Online and Confused!</msg>\r\n" + 
			"        <msg>At a server near you, its %n!</msg>\r\n" + 
			"        <msg>If there was a reward for connecting %n it would get it, but there's not so there!</msg>\r\n" + 
			"        <msg>I can't believe %n is back online! Didn't we ban them</msg>\r\n" + 
			"        <msg>%n is actually %n`s twin brother %n who would of funk it?</msg>\r\n" + 
			"        <msg>Here for one night only its %n! And the zombies go wild..Well ok wilder</msg>\r\n" + 
			"        <msg>Charlie the zombie is pleased %n has logged in as it means he can finish his lunch</msg>\r\n" + 
			"        <msg>Well well well look who the cat dragged in , its %n</msg>\r\n" + 
			"        <msg>Random log in message number 4002 welcomes %n</msg>\r\n" + 
			"        <msg>%n are you a spy from another server</msg>\r\n" + 
			"        <msg>Psssst %n want to buy some diamond blocks?</msg>\r\n" + 
			"        <msg>Like a moth to a light, %n is banging his head against the server once more...wait that's not right</msg>\r\n" + 
			"        <msg>If you build it they will come %n`s connection to the server is proof of that</msg>\r\n" + 
			"        <msg>Like a bad penny %n just keeps logging back in</msg>\r\n" + 
			"        <msg>You have to like %n they are such a wonderful person! Welcome back friend</msg>\r\n" + 
			"        <msg>What I like about %n is there kind giving nature, I know you just logged on but couldn't lend me a tenner could you?</msg>\r\n" + 
			"        <msg>If %n has proven one thing and one thing alone, it's that this game is addictive, welcome back</msg>\r\n" + 
			"        <msg>To Celebrate %n`s arrival into our server i have tickled all the creepers</msg>\r\n" + 
			"        <msg>Welcome to Call of duty BLACK OPS %n, shhhh everyone let's see if he notices</msg>\r\n" + 
			"        <msg>%n has recently got over a World of Warcraft addiction, 4 days of good games only and counting well done %n</msg>\r\n" + 
			"        <msg>%n did you get my very important email? Quick log off and check..He he he aren't I a scamp!</msg>\r\n" + 
			"        <msg>I can't remember the last time I saw %n...so I have deleted there inventory</msg>\r\n" + 
			"        <msg>Hi %n make yourself at home..oh sorry i mean make yourself A home</msg>\r\n" + 
			"        <msg>%n is now entering the server setting the zombies to.. Hungry</msg>\r\n" + 
			"        <msg>No %n i will not welcome you pffffft</msg>\r\n" + 
			"        <msg>A miner that needs no introduction, its %n ....hmmm seems he did need an introduction</msg>\r\n" + 
			"        <msg>%n is online yay</msg>\r\n" + 
			"        <msg>According to the console %n just signed in ...see?</msg>\r\n" + 
			"        <msg>%n it's nice to see you but shouldn't you be at work?</msg>\r\n" + 
			"        <msg>Welcome %n, did I pronounce your name correctly?</msg>\r\n" + 
			"        <msg>If %n can log on then we must be doing something right!</msg>\r\n" + 
			"        <msg>%n! My old chum how's it going?</msg>\r\n" + 
			"        <msg>And %n logged in and low did the creepers tremble</msg>\r\n" + 
			"        <msg>%n is now online ...Hey they can't all be interesting</msg>\r\n" + 
			"        <msg>%n the most feared miner since Fred the mildly annoyed has just connected</msg>\r\n" + 
			"        <msg>%n now you have logged in i want to congratulate you on being a tweet...well in a tweet..Why are you looking at me like that?</msg>\r\n" + 
			"        <msg>%n like many people before him and I would assume many after, has just logged in</msg>\r\n" + 
			"        <msg>Welcome %n by joining you have agreed I can sell your IP to the highest bidder...only joking...it's already sold</msg>\r\n" + 
			"        <msg>Oh wow! Its %n, I can't believe it's really you! Oh wait no I'm thinking of a different %n</msg>\r\n" + 
			"        <msg>%n`s arrival heralds the coming of a dark time..Oh wait I just forgot to turn the torch on</msg>\r\n" + 
			"        <msg>Ok for %n's benefit can you repeat what you said, they only just joined</msg>\r\n" + 
			"        <msg>Welcome %n, scanning IQ, good news your immune to zombies, well unless there on a diet</msg>\r\n" + 
			"        <msg>%n or as we call them \"Creeper magnet\" has just joined</msg>\r\n" + 
			"        <msg>%n has joined, difficulty of monsters has been raised</msg>\r\n" + 
			"        <msg>%n has now joined average IQ dropped by 5%</msg>\r\n" + 
			"        <msg>%n has just joined average IQ raised by 5%</msg>\r\n" + 
			"        <msg>Did you know that the player joining now, %n, is actually called %n? Few people knew that</msg>\r\n" + 
			"        <msg>%n has just connected, now people you all have your diamond swords...have at him</msg>\r\n" + 
			"        <msg>%n did not sign in just now. The last sentence was a lie</msg>\r\n" + 
			"        <msg>%n has just connected .....Hope he brought cake</msg>\r\n" + 
			"        <msg>*signing* happy birthday to you, happy birthday dear %n ....what do you mean it's not your birthday..Well I changed it in the YML file so it is now</msg>\r\n" + 
			"        <msg>since you last joined %n 19340205blocks have been edited 1034 new players have been seen and I have taken to randomly making up numbers</msg>\r\n" + 
			"        <msg>%n is that you? I don't recognise you with that afro..Oh wait there's a chocolate stain on my camera</msg>\r\n" + 
			"        <msg>Welcome %n and remember swearing earns you a full time ban so don't BLEEP do it you BLEEP </msg>\r\n" + 
			"        <msg>%n has logged in to do something super secret....lets all watch</msg>\r\n" + 
			"        <msg>What %n doesn't realise is the moment they log off I'm going to do a 1 day roll back on them</msg>\r\n" + 
			"        <msg>%n hello to the nice friendly server where no one ever QUICK NOW GET HIM!!!</msg>\r\n" + 
			"        <msg>I remember the first time i saw %n, they said hi....those were the days</msg>\r\n" + 
			"        <msg>Oh %n its you, i was hoping for someone..well less you</msg>\r\n" + 
			"        <msg>%n has joined, win his IQ in chickenLips by guessing it...its not really worth it to be honest</msg>\r\n" + 
			"        <msg>%n may of just connected but they have logged 1 billion hours on our server...or my database is tullip</msg>\r\n" + 
			"        <msg>%n has joined us from their home town of *INSERT TOWN HERE* i love *INSERT TOWN HERE* it's my favourite place</msg>\r\n" + 
			"        <msg>I remember last time %n was online...he left me...hope he doesn't have an ACCIDENT he he he heeee</msg>\r\n" + 
			"        <msg>%n put your clothes on thats the wrong type of strip minning</msg>\r\n" +
			"        <msg>%n you werent on another server earlier where you?</msg>\r\n" +
			"        <msg>%n your secret challenge today is ... to kill 60 chickens in one hour..note this is a ingame challenge ONLY</msg>\r\n" +
			"        <msg>%n has joined turning on pvp...we`ll show them what happens to people that take so long to connect</msg>\r\n" +
			"        <msg>%n are you a new player or just unrenemberble?</msg>\r\n" +
			"        <msg>Hi %n and welcome to your new home....there is no escape</msg>\r\n" +
			"        <msg>%n confused noob or dedicated player, who cares we all wuv you</msg>\r\n" +
			"        <msg>In the red corner its %n in the white corner its everybody else DING</msg>\r\n" +
			"        <msg>%n so nice to see you old chap, hows the cat?</msg>\r\n" +
			"        <msg>%n press t and tell us about yourself</msg>\r\n" +
			"        <msg>%n roses are red violets are blue and im stalking you</msg>\r\n" +
			"    </join>\r\n" + 
			"\r\n" + 
			"    <quit enabled=\"true\">\r\n" + 
			"        <msg>No come back %n, we miss you already</msg>\r\n" + 
			"        <msg>*sound of cork being popped* %n has left the server PARTY!</msg>\r\n" + 
			"        <msg>A wondering soul has left the server. Bye %n</msg>\r\n" + 
			"        <msg>Fine %n see if i care....no wait log back on</msg>\r\n" + 
			"        <msg>%n logged off..issueing big brother rollback shhhh lets see if he notices</msg>\r\n" + 
			"        <msg> %n, leaving so soon?</msg>\r\n" + 
			"        <msg>Finally i thought %n would never leave</msg>\r\n" + 
			"        <msg>%n has left the world, monsters made easier</msg>\r\n" + 
			"        <msg>Bye %n </msg>\r\n" + 
			"        <msg>Parting is but sweet sorrow, goodbye %n</msg>\r\n" + 
			"        <msg>%n knows absence makes the heart grow fonder</msg>\r\n" + 
			"        <msg>I think I'm going to miss %n, but don't worry I can reload</msg>\r\n" + 
			"        <msg>1 minute of silence for the departure of %n from our world to the real world</msg>\r\n" + 
			"        <msg>Seems %n prefers reality meh overrated</msg>\r\n" + 
			"        <msg>%n don't go!</msg>\r\n" + 
			"        <msg>Hit the road %n and don't you come back no more no more...wait ok you can come back</msg>\r\n" + 
			"        <msg>Adding %n to the naughty list reason, leaving without saying goodbye</msg>\r\n" + 
			"        <msg>Honestly %n what's more important then MineCraft</msg>\r\n" + 
			"        <msg>Seems %n has better things to do then hang around with us!</msg>\r\n" + 
			"        <msg>%n you will be sadly missed...until your return</msg>\r\n" + 
			"        <msg>Until we meet again %n </msg>\r\n" + 
			"        <msg>%n has gone</msg>\r\n" + 
			"        <msg>%n will be back! They always comeback!</msg>\r\n" + 
			"        <msg>Where'd %n go? Typical he logged out just when I was going to give him 10 diamond pickaxes</msg>\r\n" + 
			"        <msg>People %n has left the building</msg>\r\n" + 
			"        <msg>Ungrateful personage %n has logged out</msg>\r\n" + 
			"        <msg>Ok who upset %n and made him leave?</msg>\r\n" + 
			"        <msg>%n has left our realm, i think he left us wiser</msg>\r\n" + 
			"        <msg>I shant miss %n *sniffle* no I won't *sob* I jus...I just need to be alone right now</msg>\r\n" + 
			"        <msg>Oh so %n you think I'm here for your amusement? You think you can come and go as you please? Like some game!</msg>\r\n" + 
			"        <msg>%n evil cruel servers have feelings too you know! Leaving like that</msg>\r\n" + 
			"        <msg>If only %n had stayed online, I was about to cut the cheese...ok who sniggered!?!?</msg>\r\n" + 
			"        <msg>With the sudden departure of %n I'm left to ponder the meaning of existence</msg>\r\n" + 
			"        <msg>Disaster ! %n has left, how are we going to play musical chairs now?</msg>\r\n" + 
			"        <msg>%n was it something i said? if it was I'm sorry</msg>\r\n" + 
			"        <msg>Anyone know why %n left?</msg>\r\n" + 
			"        <msg>Did he say good bye to you, %n never said a word to me</msg>\r\n" + 
			"        <msg>%n I don't handle rejection well, I get stabby </msg>\r\n" + 
			"        <msg>%n is gone long live the king</msg>\r\n" + 
			"        <msg>It is highly illogical that %n has logged out and yet the facts hold this to be true, most interesting</msg>\r\n" + 
			"        <msg>%n %n %n where for art thou %n</msg>\r\n" + 
			"        <msg>Quick someone text %n, tell him his house is on fire!..well it will be in a sec</msg>\r\n" + 
			"        <msg>Not only did %n leave but he slammed the port closed behind him, if you logout now you won't be able to get back in...best stay</msg>\r\n" + 
			"        <msg>Well that's %n logged out</msg>\r\n" + 
			"        <msg>Like a rational thought in parliament, %n makes a sharpo and expected exit</msg>\r\n" + 
			"        <msg>The Bermuda triangle, the marry Celeste, the success of twilight and the logging out of %n just some of the mysteries of life</msg>\r\n" + 
			"        <msg>I think %n secretly loves you and that's why they logged out</msg>\r\n" + 
			"        <msg>%n has signed out, enabling amazing plug-in enabling free pickaxe enabling god mode all enabling free money</msg>\r\n" + 
			"        <msg>%n like so many others has deserted me</msg>\r\n" + 
			"        <msg>%n just signed out ! Or errored</msg>\r\n" + 
			"        <msg>ERROR %n`s departure has tulliped my logic elephants!</msg>\r\n" + 
			"        <msg>Did you know %n used to play on the server? really as recently as 1 second ago! fascinating</msg>\r\n" + 
			"        <msg>And now since they have logged out and cant disprove them, some interesting facts about %n</msg>\r\n" + 
			"        <msg>Don`t worry %n I'll keep your house warm, /give server Lava 10</msg>\r\n" + 
			"        <msg>now %n is gone we can carry on the discussion</msg>\r\n" + 
			"        <msg>Quick survey, who here will miss %n ..Any one ...anyone at all ? Wow cold guys cold</msg>\r\n" + 
			"        <msg>%n live long and prosper</msg>\r\n" + 
			"        <msg>see I'm kind, I let %n log out</msg>\r\n" + 
			"        <msg>%n doesn't like you any more, they told me on their way out the door</msg>\r\n" + 
			"        <msg>I miss %n they were so much better than the rest of you! No offense</msg>\r\n" + 
			"        <msg>%n has just reached the limit of the logout system, no one else may logout for 100 DAYS 5 Hours</msg>\r\n" + 
			"        <msg>Think %n logging out is unfair? Well then visit him at *INSERT ADDRESS HERE*</msg>\r\n" + 
			"        <msg>Ok place your bets now, how long till %n signs back in?</msg>\r\n" + 
			"        <msg>Random departing message here, %n </msg>\r\n" + 
			"        <msg>%n is gone but not forgotten, we will never forget you *INSERTNAME HERE* </msg>\r\n" + 
			"        <msg>%n did you mean to logout next to those 4 creepers? Better log back in</msg>\r\n" + 
			"        <msg>I may be insane but even I know you shouldn't sign out where you did %n</msg>\r\n" + 
			"        <msg>%n has signed out of the server and with them goes my heart...STOP THIEF !</msg>\r\n" + 
			"        <msg>Now departing at platform 2 %n</msg>\r\n" + 
			"        <msg>Can you believe %n has chosen the real world over ME?</msg>\r\n" + 
			"        <msg>%n has just dumped us all and left for the real world</msg>\r\n" + 
			"        <msg>If %n signs back in with in 1 hour all is forgiven, if not....well it will be interesting</msg>\r\n" + 
			"        <msg>%n doesn't like me I'm sure...and he smells! ok ok I'm just bitter he left</msg>\r\n" + 
			"        <msg>Ok %n has signed out so until someone else joins no one can leave ok!</msg>\r\n" + 
			"        <msg>Now %n has logged out whose up for some cake?</msg>\r\n" + 
			"        <msg>I can't believe %n would leave us like that</msg>\r\n" + 
			"        <msg>oh leave then %n, just like everyone else</msg>\r\n" + 
			"        <msg>bye bye %n have a good night</msg>\r\n" + 
			"        <msg>for every hour you are logged out %n, I will reveal one secret about you in global chat</msg>\r\n" + 
			"        <msg>Quick %n is trying to escape! Release the giant inflatable ball!, ok ok who thought that would be effective?</msg>\r\n" + 
			"        <msg>I`m sorry %n but there can be no escape mooo ha ha ha ha haaa, wait...where`d he go?</msg>\r\n" + 
			"        <msg>I see %n has left the server...formatting their hard drive</msg>\r\n" + 
			"        <msg>What %n doesn't realise is that even though he logged out i have his IP, scanning the hard drive now..oh god I'm far too young to see that</msg>\r\n" + 
			"        <msg>I regret to inform you %n`s addiction to mine craft is not as great as first believed...he managed to exit</msg>\r\n" + 
			"        <msg>can you believe %n left like that!!! anyone know where there house it?</msg>\r\n" +
			"        <msg>%n has left like many of you inferrier beings unable to play minecraft 24/7 like me!</msg>\r\n" +
			"        <msg>%n you must comeback you have 10 seconds to comply</msg>\r\n" +
			"        <msg>Nooooo %n come back we wuv you we do we all wuv you , is it me or does he seem to be running faster?</msg>\r\n" +
			"    </quit>\r\n" + 
			"\r\n" + 
			"    <!-- BAN/KICK Messages NOT WORKING YET! -->\r\n" + 
			"    <!-- Messages to use when a player is kicked or banned -->\r\n" + 
			"    <!--\r\n" + 
			"        extra replacements:\r\n" + 
			"        %admin => admin who kicked or banned the player\r\n" + 
			"    -->\r\n" + 
			"    <ban enabled=\"false\">\r\n" + 
			"        <msg>\"Leave and never come back\" %n - %admin!</msg>\r\n" + 
			"    </ban>\r\n" + 
			"    <kick enabled=\"false\">\r\n" + 
			"        <msg>Ugg... leave already %n</msg>\r\n" + 
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
			"            drowning\r\n" + 
			"            falling\r\n" + 
			"            burning\r\n" + 
			"            lava\r\n" + 
			"            suffocation\r\n" + 
			"     -->\r\n" + 
			"    <!--\r\n" + 
			"        extra replacements:\r\n" + 
			"        %damage => amount of damage taken on final blow\r\n" + 
			"        %killer => name of player, creature, fire, water, gravity, or explosion\r\n" + 
			"		%killer_item => item in kill's hand or the same as %killer if not a player.\r\n" + 
			"     -->\r\n" + 
			"    <death enabled=\"true\">\r\n" + 
			"        <msg>%n is a weakling that was killed by %killer.</msg>\r\n" + 
			"        <msg>OMG I can't believe it %n was just killed by %killer ADMIN!!!!we need you</msg>\r\n" + 
			"        <msg>Its ok %killer, I'm sure %n had it coming</msg>\r\n" + 
			"        <msg>Now %n, the red stuff needs to stay on the inside! i blame %killer</msg>\r\n" + 
			"        <msg killer=\"zombie\">Munch munch munch Grrr do I have any %n between my teeth?</msg>\r\n" + 
			"        <msg killer=\"zombie\">You know what, I always assumed %n was safe from zombie attacks, who knew they liked appetisers?</msg>\r\n" + 
			"        <msg killer=\"zombie\">Poor zombie, he nearly choked on %n, there there little zombie</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n and a zombie sitting in a tree D I E I N G</msg>\r\n" + 
			"        <msg killer=\"zombie\">Brains no more, sorry %n</msg>\r\n" + 
			"        <msg killer=\"zombie\">Zombies often like to play, isn't that right %n</msg>\r\n" + 
			"        <msg killer=\"zombie\">Oooo look %n the zombie is dancing!!!</msg>\r\n" + 
			"        <msg killer=\"zombie\">its ok kids, Charlie the zombie was just hugging %n</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n disagreed with a zombie, and now he has terrible indigestion</msg>\r\n" + 
			"        <msg killer=\"zombie\">well %n if you take on zombies you're going to get bit</msg>\r\n" + 
			"        <msg killer=\"zombie\">Grrrr moan grrrr mmmmf %n ...translation tasty tasty %n</msg>\r\n" + 
			"        <msg killer=\"zombie\">Look out %n! Zombie! ooops too late</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n no longer likes zombies, which is a shame as they like him</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n, Charlie the zombie wants to know if you have any gravy?</msg>\r\n" + 
			"        <msg killer=\"zombie\">Charlie the zombie never learnt not to bite the hand that feeds it, poor %n</msg>\r\n" + 
			"        <msg killer=\"zombie\">That's very kind of %n to give the zombie a hand like that</msg>\r\n" + 
			"        <msg killer=\"zombie\">Only noobs and %n get killed by zombies</msg>\r\n" + 
			"        <msg killer=\"zombie\">Charlie the zombie says %n is crunchy on the outside smooth on the inside, where you wearing armour %n?</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n just got killed by a reanimated corpse!</msg>\r\n" + 
			"        <msg killer=\"zombie\">Poor %n, but then that's what happens when you tease a poor defenceless zombie</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n has just been slaughtered by Charlie the cranky zombie</msg>\r\n" + 
			"        <msg killer=\"zombie\">Charlie the zombie, Stop talking when your mouth is full of %n, its disrespectful!</msg>\r\n" + 
			"        <msg killer=\"zombie\">Charlie the zombie, if you don't eat all of %n you won't have another!</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n wearing armour was very selfish you chipped the zombies tooth</msg>\r\n" + 
			"        <msg killer=\"zombie\">%n has selfishly fed a poor starving zombie, well done if you can find it pat yourself on the back</msg>\r\n" + 
			"        <msg killer=\"zombie\">Awwwww bless look how happy the zombie is playing with %n, come on Mr. Zombie don't play with your food</msg>\r\n" + 
			"        <msg killer=\"zombie\">If you have recently killed a player raise your hand, oh very funny Mr. Zombie, your hand not %n`s!</msg>\r\n" + 
			"        <msg killer=\"skeleton\">No need to get so angry %n, the skeleton was just trying to shoot the apple from your head</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n just got killed by a skeleton</msg>\r\n" + 
			"        <msg killer=\"skeleton\">If only %n had listened to their mum, she always told them not to play with skeletons</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Unlike a giant poodle, %n was unhappy to see Mr. Skeleton</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Oh congratulations Mr skeleton, you got %n right between the eyes!</msg>\r\n" + 
			"        <msg killer=\"skeleton\">An arrow a day keeps %n away, just ask a very happy skeleton..Well he is smiling</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Did you not see the skeleton there %n? Rookie mistake</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n just got Owned by a skeleton</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n very kindly just let a skeleton kill them</msg>\r\n" + 
			"        <msg killer=\"skeleton\">If i was %n i would hunt that skeleton down</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n appears to have a little difficulty with skeletons</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n did not become friends with Mr. Skeleton</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Oh dear %n i think you reminded Mr. Skeleton of an old friend</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Did you know %n, that most skeletons are only aggressive when they see you?</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Poor little skeleton..There there i know you only killed %n in self defence. Mean %n attacking you</msg>\r\n" + 
			"        <msg killer=\"skeleton\">*signing* Dem bones dem bones, join in %n..oh your breathingly challenged sorry</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Skeletons are all the rage these days. What's that %n? Oh sorry all in a rage</msg>\r\n" + 
			"        <msg killer=\"skeleton\">There's skeletons in the dark dear %n</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n just tryed to kill a skeleton...by indigestion</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n just got hacked up by a skeleton</msg>\r\n" + 
			"        <msg killer=\"skeleton\">In future %n respect your elders, do that and skeletons won't kill you</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Ummm %n I hate to ask you but the skeleton would like that arrow back please</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n killed by a giant 100ft monster with big sharp teeth and an orange tail..they didn't want to admit a skeleton killed them</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Ok %n, Mr. Skeleton, I want you to both shake and make up</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Remember in future %n you don't need to run faster than a skeleton, merely faster than your mate, hell trip him up if you can</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Skeletons do not like to be hit %n, i do so hope you have learnt your lesson</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n never pick a fight with a skeleton, they cant argue back so they just hit you</msg>\r\n" + 
			"        <msg killer=\"skeleton\">If it makes you feel better %n you made the skeleton jump, it was the only way it could get over your corpse</msg>\r\n" + 
			"        <msg killer=\"skeleton\">The skeletons had a picnic then %n turned up. A brief game of kill the mortal insued</msg>\r\n" + 
			"        <msg killer=\"skeleton\">I think that skeleton is laughing at you %n ...it's rather hard to tell isn't it</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n has learnt not to make rude comments about the skeletons</msg>\r\n" + 
			"        <msg killer=\"skeleton\">%n was just slaughtered by a pack of skeletons</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Can you believe it? %n found a skeleton</msg>\r\n" + 
			"        <msg killer=\"skeleton\">Skeletons don't like %n I'm not sure why maybe it's the wear and tear on bows?</msg>\r\n" + 
			"        <msg killer=\"falling\">%n feels gravity sucks</msg>\r\n" + 
			"        <msg killer=\"falling\">%n remember its not heights that kill you its grounds</msg>\r\n" + 
			"        <msg killer=\"falling\">Is it bird? is it a plane SPLAT oh it was %n</msg>\r\n" + 
			"        <msg killer=\"falling\">%n Pancaked</msg>\r\n" + 
			"        <msg killer=\"falling\">Clean up isle 10! poor %n</msg>\r\n" + 
			"        <msg killer=\"falling\">How selfish is that? Who is going to clean %n up</msg>\r\n" + 
			"        <msg killer=\"falling\">%n fell off this mortal coil...get it? He fell off he he </msg>\r\n" + 
			"        <msg killer=\"falling\">%n must be drunk I would never fall off there, but then I'm a hugely powerful server</msg>\r\n" + 
			"        <msg killer=\"falling\">What goes up must come down, isn't that right %n?</msg>\r\n" + 
			"        <msg killer=\"falling\">Oh %n is rather silly, well actually %n is a bit of a stain</msg>\r\n" + 
			"        <msg killer=\"falling\">%n certainly made an impression or is that a depression?</msg>\r\n" + 
			"        <msg killer=\"falling\">I told the safety inspector we should fence that off...poor %n</msg>\r\n" + 
			"        <msg killer=\"falling\">You know, %n next time try a ground takeoff before you attempt to fly</msg>\r\n" + 
			"        <msg killer=\"falling\">Oh dear, did %n fall down? Poor %n hope you didn't scrape your knee</msg>\r\n" + 
			"        <msg killer=\"falling\">%n has just discovered flight...they could use some work on the landing though</msg>\r\n" + 
			"        <msg killer=\"falling\">%n is keeping the janitors in business, cleaning up after their falls is a full time job</msg>\r\n" + 
			"        <msg killer=\"falling\">What goes \"oh no, oh no!, oh no!! *SPLAT*? why %n of course</msg>\r\n" + 
			"        <msg killer=\"falling\">Like a lemming leaping joyfully to an ancestral home that isn`t quite there any more, %n dropped to his death</msg>\r\n" + 
			"        <msg killer=\"falling\">Oh man! Did you hear that crunch? Poor %n</msg>\r\n" + 
			"        <msg killer=\"falling\">Warning shadows may be caused by falling %n`s</msg>\r\n" + 
			"        <msg killer=\"falling\">For the dive %n I have to say 5.5 it would of been higher but i felt the landing needs work</msg>\r\n" + 
			"        <msg killer=\"falling\">%n hit the ground so hard all the zombies jumped</msg>\r\n" + 
			"        <msg killer=\"falling\">The sound of %n hitting the dirt made even Charlie the zombie cringe</msg>\r\n" + 
			"        <msg killer=\"falling\">If only %n had worn a parachute</msg>\r\n" + 
			"        <msg killer=\"falling\">My my %n did you know you were that high?</msg>\r\n" + 
			"        <msg killer=\"falling\">%n died from falling.</msg>\r\n" + 
			"        <msg killer=\"falling\">Next time %n flap your arms, it won't help but will look funny on YouTube</msg>\r\n" + 
			"        <msg killer=\"falling\">Next on you`ve been framed %n takes a tumble</msg>\r\n" + 
			"        <msg killer=\"falling\">Wooosh Splat, my impression of %n, like it?</msg>\r\n" + 
			"        <msg killer=\"falling\">Not being mean but i really thought %n would of bounced higher</msg>\r\n" + 
			"        <msg killer=\"falling\">Like metal is attracted to magnets, %n is attracted to grounds, see look their stuck together</msg>\r\n" + 
			"        <msg killer=\"falling\">We need a something to scrape %n off the ground,...its frankly upsetting the zombies</msg>\r\n" + 
			"        <msg killer=\"falling\">I'm now using %n as a book mark, it's a little messy but he is flat enough</msg>\r\n" + 
			"        <msg killer=\"falling\">Its ok folks %n is ok ..,,errr quick go to commercials!</msg>\r\n" + 
			"        <msg killer=\"falling\">I don't think %n will be getting up from that, and if he does I'm running for it!</msg>\r\n" + 
			"        <msg killer=\"falling\">%n there demonstrating today's letter, the letter O</msg>\r\n" + 
			"        <msg killer=\"falling\">Ok who pushed %n?</msg>\r\n" + 
			"        <msg killer=\"falling\">Oh no %n fell AGAIN, I think they enjoy it you know</msg>\r\n" + 
			"        <msg killer=\"falling\">Any one got a sponge block need to get rid of some %n</msg>\r\n" + 
			"        <msg killer=\"falling\">Well done %n! no one in the history of the server has bounced that high!</msg>\r\n" + 
			"        <msg killer=\"falling\">%n poor poor %n, next time hold shift when you're looking over the edge ok?</msg>\r\n" + 
			"        <msg killer=\"falling\">Do you think %n even knows what gravity is?</msg>\r\n" + 
			"        <msg killer=\"falling\">%n + ground = SPLAT</msg>\r\n" + 
			"        <msg killer=\"falling\">And all the kings men and all the kings horses couldn't put %n together again. But then horses are not well known for jigsaw ability are they?</msg>\r\n" + 
			"        <msg killer=\"falling\">Mortician!! quick we need a mortician! is there a mortician on the server? %n took a tumble</msg>\r\n" + 
			"        <msg killer=\"falling\">%n there proving that humans can`t fly, give him a round of applause</msg>\r\n" + 
			"        <msg killer=\"falling\">The last thing to go through %n`s mind? Stone block I think?</msg>\r\n" + 
			"        <msg killer=\"falling\">You should see %n`s impact in slow motion, it's amazing!...messy but amazing</msg>\r\n" + 
			"        <msg killer=\"falling\">Is that a hand print on the back of %n? I think that was no accident!</msg>\r\n" + 
			"        <msg killer=\"falling\">LOL oh man did you see the look on his face? quick rewind the server I want to see %n fall again</msg>\r\n" + 
			"        <msg killer=\"burning\">%n is hot today!</msg>\r\n" + 
			"        <msg killer=\"burning\">%n is on fire!</msg>\r\n" + 
			"        <msg killer=\"burning\">Come on %n, what have I told you about smoking</msg>\r\n" + 
			"        <msg killer=\"burning\">This is a no smoking server %n</msg>\r\n" + 
			"        <msg killer=\"burning\">Match plus idiot equal ashes...don't they %n</msg>\r\n" + 
			"        <msg killer=\"burning\">FIRE FIRE FIRE! quick throw %n into the sea</msg>\r\n" + 
			"        <msg killer=\"burning\">Have you lost weight %n? just burning that fat off aren't you</msg>\r\n" + 
			"        <msg killer=\"burning\">love the hair %n how do you get it that red and flickery</msg>\r\n" + 
			"        <msg killer=\"lava\">Now %n the bright red liquid is not for swimming </msg>\r\n" + 
			"        <msg killer=\"lava\">*signing* ...And it burns burns burns %n of fire</msg>\r\n" + 
			"        <msg killer=\"lava\">%n was unhappy with life so he jumped into lava</msg>\r\n" + 
			"        <msg killer=\"lava\">Oh congratulations %n! you found LAVA!!!woooo </msg>\r\n" + 
			"        <msg killer=\"lava\">Hmm %n is just loving the lava!</msg>\r\n" + 
			"        <msg killer=\"lava\">Even Charlie the zombie knows to avoid lave, %n mind you he does have 3 brains</msg>\r\n" + 
			"        <msg killer=\"lava\">%n i really feel you should avoid lava, its just not good for your elf</msg>\r\n" + 
			"        <msg killer=\"lava\">i cant help but wonder what %n would be like right now if he hadnt of jumped into lava</msg>\r\n" + 
			"        <msg killer=\"lava\">%n is no longer confused about the bright red stuff pouring into his mine</msg>\r\n" + 
			"        <msg killer=\"lava\">Yes, %n the light at the end of the tunnel was Lava</msg>\r\n" + 
			"        <msg killer=\"lava\">%n its really simple, Lava Bad, water Good</msg>\r\n" + 
			"        <msg killer=\"lava\">%n just found the lake of fire, well ok a block of lava</msg>\r\n" + 
			"        <msg killer=\"lava\">mmmm i love the smell of burning %n in the mourning</msg>\r\n" + 
			"        <msg killer=\"lava\">Ok someone turn %n over, that sides done</msg>\r\n" + 
			"        <msg killer=\"lava\">%n thats not a lava resistant skin your in...sorry was in</msg>\r\n" + 
			"        <msg killer=\"lava\">if lava be the food of love play on, aint that right %n</msg>\r\n" + 
			"        <msg killer=\"lava\">%n just came to a sticky end, well a burnt and smelly one atleast</msg>\r\n" + 
			"        <msg killer=\"lava\">Caution Lava may be hot when glowing red, %n</msg>\r\n" + 
			"        <msg killer=\"lava\">%n says, come on in the lava is good today...or was it arghhhhh it burns hmmm</msg>\r\n" + 
			"        <msg killer=\"lava\">%n, mental note, lava is not good for diamond pick axes</msg>\r\n" + 
			"        <msg killer=\"lava\">%n did you mean to fall in that lava?</msg>\r\n" + 
			"        <msg killer=\"lava\">Oh dear %n is all hot and bothered..well ok burnt and dead but i was close</msg>\r\n" + 
			"        <msg killer=\"lava\">Butter wouldnt melt in %n`s mouth...but thats cos the lava melted his mouth</msg>\r\n" + 
			"        <msg killer=\"lava\">%n, its hot enough to melt rock, and you decided to go for a paddle? muppet</msg>\r\n" + 
			"        <msg killer=\"lava\">%n died in lava</msg>\r\n" + 
			"        <msg killer=\"lava\">%n Lava is the red hot stuff....its generaly advisable to not stand in it, just a thought</msg>\r\n" + 
			"        <msg killer=\"lava\">*signing* Disco inferno woooo bye bye %n</msg>\r\n" + 
			"        <msg killer=\"lava\">who here knows why %n jumped into the lava?</msg>\r\n" + 
			"        <msg killer=\"lava\">%n you know there are safer ways to get a tan?</msg>\r\n" + 
			"        <msg killer=\"lava\">%n just discovered what the light at the end of the tunnel was...boy was he surprised!</msg>\r\n" + 
			"        <msg killer=\"lava\">Can anyone smell cooked meat..oh %n is in the lava</msg>\r\n" + 
			"        <msg killer=\"burning\">%n is experiencing the disco inferno! oh wait no just the inferno</msg>\r\n" + 
			"        <msg killer=\"burning\">Thank god %n was wearing armour...now his ashes will be foil wrapped</msg>\r\n" + 
			"        <msg killer=\"burning\">%n oh man i didnt think %n would burn so quickly</msg>\r\n" + 
			"        <msg killer=\"burning\">Presenting %n, The Human Torch!</msg>\r\n" + 
			"        <msg killer=\"burning\">%n, what have i told you about smoking ?</msg>\r\n" + 
			"        <msg killer=\"burning\">%n are you having a BBQ?</msg>\r\n" + 
			"        <msg killer=\"burning\">Fire +%n = funny</msg>\r\n" + 
			"        <msg killer=\"burning\">Well done %n your now on youtube! look for \"Idiot on fire\"</msg>\r\n" + 
			"        <msg killer=\"burning\">well %n i did say lighting that may back fire</msg>\r\n" + 
			"        <msg killer=\"burning\">%n you little firebug you</msg>\r\n" + 
			"        <msg killer=\"burning\">And here to demonstrate the follies of arson is %n`s ashes</msg>\r\n" + 
			"        <msg killer=\"burning\">Man %n next time give me some warning so i can grab the marshmellows</msg>\r\n" + 
			"        <msg killer=\"burning\">i think %n has overheated</msg>\r\n" + 
			"        <msg killer=\"burning\">some one take the flint and steal off %n before he hurts himself! ah too late</msg>\r\n" + 
			"        <msg killer=\"burning\">%n decided that life was too boring and set himself on fire</msg>\r\n" + 
			"        <msg killer=\"burning\">%n just gave me a smouldering look..oh wait i mean adopted a smouldering look</msg>\r\n" + 
			"        <msg killer=\"burning\">%n needs to download a new skin, the old one is a bit..burnt</msg>\r\n" + 
			"        <msg killer=\"burning\">%n you remind me of a piece of toast i once burned</msg>\r\n" + 
			"        <msg killer=\"burning\">%n has attracted some zombies, they do so like a cooked meal</msg>\r\n" + 
			"        <msg killer=\"burning\">Only you can prevent %n fires</msg>\r\n" + 
			"        <msg killer=\"burning\">%n is slow roasted for extra crispyness</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n sleeps with the fishes (not in a perverted way more in stone block around the feet way)</msg>\r\n" + 
			"        <msg killer=\"drowning\">I feel sorry for %n he seemed to run out of air</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n needs swimming lessons</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n surface and then breath, its soo important to get the order right</msg>\r\n" + 
			"        <msg killer=\"drowning\">Guess who cant swim, its %n...he floats well though</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n is treading water...oh wait umm no he isnt, some one fish him out</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n just took a huge breath of h2o...easy mistake to make should be o2</msg>\r\n" + 
			"        <msg killer=\"drowning\">Man people are friendly, you should of seen %n waving at that life guard</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n wishes he had gills ... the fish type not a couple of friends called Gill</msg>\r\n" + 
			"        <msg killer=\"drowning\">Deep there aint it %n</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n is looking for mermades</msg>\r\n" + 
			"        <msg killer=\"drowning\">What doesnt move and bobs on the surface? ...%n</msg>\r\n" + 
			"        <msg killer=\"drowning\">Dive dive dive i said not die die die, stupid %n</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n has all the grace of a fish, well  a dead fish</msg>\r\n" + 
			"        <msg killer=\"drowning\">Aww bless %n is feeding the squids</msg>\r\n" + 
			"        <msg killer=\"drowning\">can you believe %n just drowned? i can i got the server logs</msg>\r\n" + 
			"        <msg killer=\"drowning\">%n just proved that water is not your friend</msg>\r\n" + 
			"        <msg killer=\"drowning\">No %n that wasnt a mermaid, that was lack of O2</msg>\r\n" +
			"        <msg killer=\"drowning\">%n had a good luck at some squid...next time take air</msg>\r\n" +
			"        <msg killer=\"drowning\">%n would like the server to add pumpkinDiver plugin please</msg>\r\n" +
			"        <msg killer=\"drowning\">Its sponge Bob!!!oh ..wait no its %n ewwww</msg>\r\n" +
			"        <msg killer=\"suffocation\">I feel sorry for %n he seemed to run out of air</msg>\r\n" + 
			"        <msg killer=\"suffocation\">hmmm im not sure that should even of been possible %n</msg>\r\n" + 
			"        <msg killer=\"suffocation\">It`s true aint it %n sand does get everywhere</msg>\r\n" + 
			"        <msg killer=\"suffocation\">%n has just been entombed</msg>\r\n" + 
			"        <msg killer=\"suffocation\">Materialising in solid matter is a bit of a bummer aint it %n?</msg>\r\n" + 
			"        <msg killer=\"spider\">%n can now add arachnophobia to his list of aliments</msg>\r\n" + 
			"        <msg killer=\"spider\">Spiders are a bit of a pest just ask %n</msg>\r\n" + 
			"        <msg killer=\"spider\">%n tried to hit a spider with a rolled up newspaper...can anyone say MISTAKE?</msg>\r\n" + 
			"        <msg killer=\"spider\">%n has found that spiders in MineCraft are not easy to stamp on</msg>\r\n" + 
			"        <msg killer=\"spider\">Oh dear did you see the colour %n turned? Guess that spider is poisonous</msg>\r\n" + 
			"        <msg killer=\"spider\">It really is surprising how far spiders can jump, I know it surprised %n a lot</msg>\r\n" + 
			"        <msg killer=\"spider\">%n is a bit tied up right now</msg>\r\n" + 
			"        <msg killer=\"spider\">Did that spider bite you %n? %n? Hello???? oh ..oh dear</msg>\r\n" + 
			"        <msg killer=\"spider\">Awwww look at the ickle red eyes, the venom dripping tiny fangs, cute ickle spider...I don't think %n should of pet him though</msg>\r\n" + 
			"        <msg killer=\"spider\">You got killed by a tiny tiny spider %n? shame on you</msg>\r\n" + 
			"        <msg killer=\"spider\">RSPCA will have you %n if you try to get revenge</msg>\r\n" + 
			"        <msg killer=\"spider\">That spider killed you in self defence I saw you break its web! </msg>\r\n" + 
			"        <msg killer=\"spider\">If you go into the woods today your sure to get a nasty surprise. Just ask %n he he he heee</msg>\r\n" + 
			"        <msg killer=\"creeper\">Better look out %n creeper go boom you know</msg>\r\n" + 
			"        <msg killer=\"creeper\">Oh look there's %n and over there is some more and err over there and err there</msg>\r\n" + 
			"        <msg killer=\"creeper\">%n gets about a bit...in-fact a creeper has spread him about a lot</msg>\r\n" + 
			"        <msg killer=\"creeper\"> %n plus creeper = hole </msg>\r\n" + 
			"        <msg killer=\"creeper\">%n is now an expert on creeper anatomy, after all he has spread so much of it about</msg>\r\n" + 
			"        <msg killer=\"creeper\">Sssssssssssss BANG bye bye %n</msg>\r\n" + 
			"        <msg killer=\"creeper\">%n just hugged a creeper</msg>\r\n" + 
			"        <msg killer=\"creeper\">A creeper just sent %n off with a bang</msg>\r\n" + 
			"        <msg killer=\"creeper\">Just 1 creeper and %n goes all to pieces</msg>\r\n" + 
			"        <msg killer=\"creeper\">Oh well done mr creeper, you sent %n flying, give your self a hand..umm sorry that was thoughtless of me</msg>\r\n" + 
			"        <msg killer=\"creeper\">Creepers are an endagered species, whats that %n? oh well thats not nice</msg>\r\n" + 
			"        <msg killer=\"creeper\">It's ok %n you got your revenge, the creeper went bye bye too</msg>\r\n" + 
			"        <msg killer=\"creeper\">Mr Creeper just exploded with excitement at seeing his good friend %n</msg>\r\n" +
			"        <msg killer=\"creeper\">%n is now gonna have to rebuild that area, renember to make a memorial for the poor creeper</msg>\r\n" +
			"        <msg killer=\"creeper\">Where once was %n and a creeper is now just a hole and a mess</msg>\r\n" +
			"        <msg killer=\"creeper\">%n would like creepers disabled please</msg>\r\n" +
			"        <msg killer=\"creeper\">%n ever noticed how creepers are not very good at creeping? oh guess not</msg>\r\n" +
			"        <msg killer=\"contact\">Tree huggers should not hug cactus, should they %n</msg>\r\n" + 
			"        <msg killer=\"contact\">Save a noob chop a cactus, only you can save %n</msg>\r\n" + 
			"        <msg killer=\"contact\">%n make a mental note...cactus is painful.</msg>\r\n" + 
			"        <msg killer=\"contact\">%n, I'm sure the cactus was just as scared of you as you were of it</msg>\r\n" + 
			"        <msg killer=\"contact\">What's green and pointy and has bits of %n stuck to it? ...cactus! </msg>\r\n" + 
			"        <msg killer=\"contact\">%n, It's an apple a day not a cactus a day...mind you depends how you swing it</msg>\r\n" + 
			"        <msg killer=\"contact\">Now who do you supose put that cactus there %n?</msg>\r\n" +
			"        <msg killer=\"contact\">if this were bulletstorm %n would of got a Priked skill shot, but its not so he is dead</msg>\r\n" +
			"        <msg killer=\"contact\">%n proves that running away from zombies with out looking can get a cactus to the face</msg>\r\n" +
			"        <msg killer=\"contact\">Who loves you %n? clearly not Mr Cactus</msg>\r\n" +
			"        <msg killer=\"contact\">Its not cactus jack its Cactus %n</msg>\r\n" +
			"        <msg killer=\"ghast\"> you know i thought %n couldve dodged that fireball</msg>\r\n" + 
			"        <msg killer=\"ghast\">Yes %n im surprised to see a ghast here too</msg>\r\n" + 
			"        <msg killer=\"ghast\">Ghast pilot to ghast bombadier good shot old chap, you nailed %n</msg>\r\n" + 
			"        <msg killer=\"explosion\">%n i think you were a bit to close to that TNT chap</msg>\r\n" + 
			"        <msg killer=\"explosion\">%n now featured on youtube!! look up \"Idiot with TNT\"</msg>\r\n" + 
			"        <msg killer=\"explosion\">you rocked the world %n !</msg>\r\n" + 
			"        <msg killer=\"explosion\">Fire works by %n</msg>\r\n" + 
			"        <msg killer=\"explosion\">%n gets about a bit, in fact theres bits about everywhere</msg>\r\n" + 
			"        <msg killer=\"explosion\">Oh dear, on the bright side %n you lost weight</msg>\r\n" + 
			"        <msg killer=\"explosion\">ok who gave %n TnT, own up</msg>\r\n" + 
			"        <msg killer=\"explosion\">Some one fetch a mop %n had some tnt</msg>\r\n" +
			"        <msg killer=\"explosion\">BANG and %n is dead</msg>\r\n" + 
			"        <msg killer=\"explosion\">Did you mean to do that %n?</msg>\r\n" + 
			"        <msg killer=\"explosion\">Oh no %n is at it again, someone take the tnt away from whats left of them!</msg>\r\n" + 
			"        <msg killer=\"explosion\">Tnt is not a toy %n</msg>\r\n" + 
			"        <msg killer=\"explosion\">Did you see how high %n flew...well ok how high MOST of %n flew</msg>\r\n" + 
			"        <msg killer=\"explosion\">Got to love how indiscriminate Tnt is hay %n?</msg>\r\n" + 
			"        <msg killer=\"explosion\">%n wondered what that tick tick noise was</msg>\r\n" + 
			"        <msg killer=\"explosion\">%n can see his house from here, thanks tnt</msg>\r\n" + 
			"        <msg killer=\"explosion\">%n wonders where his legs have gone</msg>\r\n" + 
			"        <msg killer=\"explosion\">10 blocks of dynamite sitting on %n`s wall, if one should acciedently fall....</msg>\r\n" + 
			"        <msg killer=\"explosion\">%n the worlds most clumsy demolition expert</msg>\r\n" +
			"        <msg killer=\"explosion\">%n the safe distance is a bit further then that</msg>\r\n" +
			"        <msg killer=\"explosion\">%n parts sold seperetly</msg>\r\n" +
			"        <msg killer=\"explosion\">Take 1 tnt add 1 %n and a flint steel box and you get ...well frankly a mess</msg>\r\n" +
			"        <msg killer=\"explosion\">We worry about %n, fetch is never to be played with tnt chap</msg>\r\n" +
			"        <msg killer=\"explosion\">To those looking on dynamic map, the mushroom cloud was %n</msg>\r\n" +
			"        <msg killer=\"explosion\">%n why would you hit that?</msg>\r\n" +
			"        <msg killer=\"explosion\">%n claims it was not his fault and that some one else placed the tnt there do we believe him?</msg>\r\n" +
			"        <msg killer=\"explosion\">%n griefing or just careless you decide</msg>\r\n" +
			"        <msg killer=\"explosion\">with the aid of tnt %n has achieved new heights! about 4 foot for his left leg</msg>\r\n" +
			"        <msg killer=\"explosion\">%n i bet in COD you stand on your own grenades yes?</msg>\r\n" +
			"        <msg killer=\"explosion\">%n goes back to the planning boars, ah! chicken goes on tnt i go on safe zone</msg>\r\n" +
			"        <msg killer=\"explosion\">%n is spread so thin,i cant believe its not butter</msg>\r\n" +
			"        <msg killer=\"explosion\">SERVER ANOUNCEMENT the loud bang was infact %n</msg>\r\n" +
			"        <msg killer=\"explosion\">POP goes the %n</msg>\r\n" +
			"        <msg killer=\"explosion\">With out a care in the world %n gives some tnt a great big hug</msg>\r\n" +
			"    </death>\r\n" + 
			"</serverevents>";
}