import java.util.Scanner;

public class Quests {
	public int 		questNumber;
	public int 	currentIndex;
	public String[] 	questLog;
	public boolean isComplete;	
	public int decisionInt;
	public Quests(int mapNumber) {
		this.questNumber = mapNumber; 	
		this.currentIndex = 0;
		questLog = NpcData.questData(mapNumber);
		isComplete = false; 
		this.decisionInt = 0;
	}

	public void increaseIndex() { 
		currentIndex += 1;
	}
	
	public String toString() { 
		return questLog[currentIndex];
	}
	
	public static String[] nonStaticDialogue(char objectChar, Player p) {
		switch (objectChar) {
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
				}
				break;
		
		
		//Marcy's cat's dialogue	
			case 'ø': // 
					for (int i = 0; i < 10; i++) {
						if(! p.pHasQuest(27)) {
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
				boolean flag2 = true;
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
							p.addItem(cat);
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
						p.removeItem(removedCat);
						p.quests[p.getQuestArrayNumber(27)].isComplete = true;
						p.quests[p.getQuestArrayNumber(27)].currentIndex = 3;
						break;
					}
				}
		}
	}
	
}
		



