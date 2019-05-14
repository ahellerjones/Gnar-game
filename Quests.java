import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
 * Marcy's quests is 	#27
 * Cook's quest is 		#28
 * Main quest is 		#1
 */

public class Quests {
	public int 		questNumber;
	public int 		currentIndex;
	public String[] 	questLog;
	public boolean 	isComplete;	
	public int 		decisionInt;
	public static boolean hasSeenShit;
	public Quests(int mapNumber) {
		this.questNumber = 	mapNumber; 	
		this.currentIndex = 0;
		questLog = 			NpcData.questData(mapNumber);
		isComplete = 		false; 
		this.decisionInt = 	0;
	}

	public void increaseIndex() { 
		currentIndex += 1;
	}
	
	public String toString() { 
		return questLog[currentIndex];
	}
	
	public static String[] nonStaticDialogue(char objectChar, Player p) {
		switch (objectChar) {
		case 't':
			return NpcData.dialogueStorage(p.currentMap.mapNumber, objectChar);
		case 'p': 
			switch (p.currentMap.mapNumber) {
				case 27: 
					//System.out.println(p.pHasQuest(27));
					if (!p.pHasQuest(27)) { 
						return NpcData.dialogueStorage(p.currentMap.mapNumber, objectChar);
						
					} 
					if (p.quests[p.getQuestArrayNumber(27)].currentIndex == 0)  {
						String[] return1 = {"Keep looking for my cat man, "
								+ "I need him back as soon as possible\n"};
						//System.out.println(p.inventory[0].itemNumber);
						return return1;
					} 
					
					if (p.checkInv(13)) {
						String[] return2 = {"\"Thank you so much for returning Mr. Jim for me!\nI can't thank you enough\""};
						return return2;
					} else {
						String[] return3 = {"\"Thanks for your help man\""};
						return return3;
					}
				case 26: 
					return NpcData.dialogueStorage(p.currentMap.mapNumber, objectChar);	
			}
			
		
				break;
		
		
		//Marcy's cat's dialogue	
			case 'ø': // 
					for (int i = 0; i < 10; i++) {
						if(!p.pHasQuest(27)) {
							String[] haventStartedQuest =  {"\"Go away\"", "Holy shit a talking cat..."};
							return haventStartedQuest;	
						} else if (p.quests[p.getQuestArrayNumber(27)].decisionInt == 0){ 
							String[] startedQuest = {
									"\"Go away!\"", "Holy shit a talking cat...",
									"\"Leave me alone\"", "Marcy has been looking for you, I'm here to take you back right meow",
									"\"\"","\"I'm sorry that she's sent you out to do her dirty work, but I ran away for a reason\"",
									"\"All that woman ever does is parade me around to show me off because of my ability to speak\"",
									"\"She doesn't actually love me, she only loves showing me off\"", 
									"\"Listen, if you leave me alone and go back to her and tell her I'm dead\n"
									+ " I'll teach you how to more efficiently skin Cows so you'll get 2 hides instead of 1"};
							return startedQuest;
						}
							
					}
			case 'œ':
				if(!p.pHasQuest(Player.currentMap.mapNumber)) {
					return NpcData.dialogueStorage(Player.currentMap.mapNumber, objectChar);
				} else if (p.quests[p.getQuestArrayNumber(28)].currentIndex == 1) {
					String[] return1 = {"Keep lookin for the 'weed man... I'm dyin over here"};
					return return1;
				} else {
					String[] return2 = {"Dude!! You're the man! Thanks a ton, you can use the range as much as you want"};
					
					MapBuilder.g28Flag = true;
					//p.quests[p.getQuestArrayNumber(28)].increaseIndex();
					return return2;
				}
		}
	String[] poop = {""};
	return poop; // stub
		
	}
	
	public static void nonStaticInteractions(int mapNumber, char objChar, Player p) {
		Scanner in = new Scanner(System.in);
		switch (objChar) {
			//Marcy's cat
			case 'ø':
				boolean flag = false;
				
				while (!flag) {
					int i = 0;
					if(p.quests == null || p.quests[i] == null)
						break;
					if (p.quests[i].questNumber == 27) {
						flag = true;
					}
					i++;
				}
					if(p.quests == null || !flag) {
						System.out.println("I wonder if this cat belongs to anyone...");
					} else if (p.quests[p.getQuestArrayNumber(27)].decisionInt == 0) {
						System.out.println("Enter 1 to take the cat back to Marcy, 2 to leave him be.");
						String input = in.nextLine(); 
						if(input.equals("1")) {
							p.quests[p.getQuestArrayNumber(27)].decisionInt = 1;
							InventoryItems cat = new InventoryItems(13, "Marcy's Cat", p);
							p.addItem(cat, 1);
							p.quests[p.getQuestArrayNumber(27)].currentIndex = 2;
						
						} else if (input.equals("2")) {
							p.quests[p.getQuestArrayNumber(27)].decisionInt = 2;
							p.quests[p.getQuestArrayNumber(27)].isComplete = true;
							p.quests[p.getQuestArrayNumber(27)].currentIndex = 4;
							break;
						} else {
							System.out.println("Read the dialogue again and enter a 1 or a 2");
							
						}
					} else if (p.quests[p.getQuestArrayNumber(27)].isComplete &&
					p.quests[p.getQuestArrayNumber(27)].decisionInt == 2) {
						System.out.println("\"Thanks for not taking me back man, that was a good decision\"");
						break;
					} else if (p.quests[p.getQuestArrayNumber(27)].decisionInt == 1) {
						System.out.println("Here's where that talking cat used to sit...\n"
								+ "I wonder where he is now...");
						if(p.checkInv(13)) {
							System.out.println("Oh wait he's in my inventory.");
						}
					}
					break;
			case 'p':
				
				switch (mapNumber) {
				case 27:	
					if (p.quests[p.getQuestArrayNumber(27)].decisionInt == 1 && 
						!p.quests[p.getQuestArrayNumber(27)].isComplete) {
						
						InventoryItems removedCat = new InventoryItems(13, "Marcy's Cat", p);
						p.removeItem(removedCat, 1);
						InventoryItems Hides = new InventoryItems(-1, "Hides", p);
						p.addItem(Hides, 7);
						p.quests[p.getQuestArrayNumber(27)].isComplete = true;
						p.quests[p.getQuestArrayNumber(27)].currentIndex = 3;
						break;
					}
				}
				break;
			case 'g':
				Attributes wd = new Attributes(0, 0, 0, 0, 0, 0, 1, false);
				InventoryItems Weed = new InventoryItems(13, "1g of Gilliweed", p, 2, wd);
				if(p.pHasQuest(28) && (p.quests[p.getQuestArrayNumber(28)].currentIndex == 0 || 
						p.quests[p.getQuestArrayNumber(28)].currentIndex == 1)) {
					System.out.println("Aha! You find some Gilliweed would you like to pull it up?\n"
							+ "Enter y if you want to");
					if(in.nextLine().equals("y")) {
						p.quests[p.getQuestArrayNumber(28)].increaseIndex();
						p.addItem(Weed, 1);
						crazyCookQuestShit(p);
					}
				} else { 
					System.out.println("Hmm some Gilliweed, this stuff smells kinda funny");
				}
				break;
			case 'œ':
				Attributes wd1 = new Attributes(0, 0, 0, 0, 0, 0, 1, false);
				InventoryItems Weed1 = new InventoryItems(13, "1g of Gilliweed", p, 2, wd1);
				if(MapBuilder.g28Flag) {
					Player.removeItem(Weed1, 2);
				}
				if(Player.quests[Player.getQuestArrayNumber(28)].currentIndex == 2) {
					Player.quests[Player.getQuestArrayNumber(28)].increaseIndex();
					Player.quests[Player.getQuestArrayNumber(28)].isComplete = true;
				break;
				}
		}
	}
	
	public static void checkQuestItem(InventoryItems item, int mapNumber, Player p){
		switch (mapNumber) {
		case 13: 			//Checking to see if the player is buying gilliweed in cook quest
			if(p.pHasQuest(28) && (p.quests[p.getQuestArrayNumber(28)].currentIndex == 0 || 
				p.quests[p.getQuestArrayNumber(28)].currentIndex == 1)) {
				p.quests[p.getQuestArrayNumber(28)].increaseIndex();
			}
		}
		
	}
		
	public static void crazyCookQuestShit(Player p) {
		if(!hasSeenShit) {
			Scanner in = new Scanner(System.in);
			for(int i = 0; i < 10; i++) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("You begin to pull it up...");
				double j = Math.random();
				if(j < .3) {
					System.out.println("The world around you begins to collapse...");
				} else if(Math.random() > .6) {
					System.out.println(j * 3000);
				}
			}
			for(int i = 0; i < 3; i++) {
				
				for (int k = 0; k < 20; k++) {
					for (int l = 0; l <= k; l++) {
						try {
							TimeUnit.MILLISECONDS.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.print("*");
					}
					System.out.println();
				}
				for (int k = 20; k > 0; k--) {
					for (int l = 0; l <= k; l++) {
						System.out.print("*");
					}
					System.out.println();
				}
			}
			for (int k = 0; k < 20; k++) {
				for (int l = 0; l <= k; l++) {
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print("*");
				}
				System.out.println();
			}
			for (int k = 0; k < 15; k++) {
				try {
					TimeUnit.MILLISECONDS.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("*************************************************************");
			}
			
			System.out.println("\n\n~You feel your eyes and skin begin to burn and smolder as an extremely\n"
					+ "intense light begins to penetrate your very being. Your hair fries and the pain is insurmountable~\n"
					+ "Press enter to continue.");
			in.nextLine();
			
			System.out.println("\n\n\n\n~All of a sudden it all vannishes, your wounds healed and the light subsides.~"
					+ "\nPress enter to continue.\n"); 
					in.nextLine();
			
			System.out.println("~You are floating in an empty void of nothingness; you hear a voice.~");
			System.out.println("Press enter to continue.");
			in.nextLine();
			System.out.println("\"You have been chosen by the gods to protect the land you live in called the Land of Gnar\"");
			System.out.println("Press enter to continue.");
			in.nextLine();
			System.out.println("What in the actual fuck is going on?? Jesus H. Pogosticking Christ! You people nearly\n"
					+ "fried my fucking brains out!!! Who's there? Let me out of here!");
			System.out.println("Press enter to continue.");
			in.nextLine();
			System.out.println("\"We are the Gods of Gnar, we have chosen you to be the savior of our land\"");
			System.out.println("\"For years we have looked over Gnar in all its glory, but our powers have been subverted and must be restored.\"");
			System.out.println("Press enter to continue.");
			in.nextLine();
			System.out.println("\"Our powers are manifested in three items that have been taken over by demons that plague our land\"");
			System.out.println("\"These items were taken by those who fear the Gnar, who stray away and live sedentary lives of void\"");
			System.out.println("\"You must go out to the land and take back these items so that we again may have our powers back\"");
			System.out.println("Press enter to continue.");
			in.nextLine();
			System.out.println("\"You must gather:\n1. The Brown Claw.\n2. Water from the mighty River of Jimothy"
					+ "\n3. Slay the Boater of the Club and eat his heart\"");
			System.out.println("Press enter to continue.");
			in.nextLine();
			System.out.println("Eat his heart? Are you serious???");
			System.out.println("Press enter to continue.");
			in.nextLine();
			System.out.println("\"Yes\"");
			System.out.println("Enter 1 if you wish to help the Gods, or 2 to refuse");
			if (in.nextLine().equals("2")) {
				System.out.println("\"Fine. You can just watch all of this dialogue again then.\"");
			} else {
				System.out.println("\"Very good, we will be in contact again once you have gathered the items\"");
				Quests mainQuest = new Quests(1);
				p.addQuest(mainQuest);
				hasSeenShit = true;
			}
		}
		
	}
}
	

		



