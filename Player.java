import java.util.Scanner;
import java.util.Arrays;

public class Player {
	public static int[] 		loc = new int[2];
	public static Map 			currentMap; 
	public Scanner 				in = new Scanner(System.in);
	public static boolean 		isInBuilding;
	//public static String[] 	questList;
	public static int 			playerQuestIndex;
	public Quests[] quests = 	new Quests[10];
	public static Map			lastMap;
	InventoryItems[] inventory = new InventoryItems[10];
	public static int 			inventoryIndex;
	
	public Player(Map currentMap, int a, int b) {
		loc[0] = a;
		loc[1] = b;
		if(currentMap == null) {
			Map currentMap1 = new Map(12, this);
			currentMap = currentMap1;	
		}
		this.currentMap = currentMap;
		this.currentMap.coords[a][b] = 'X';
		playerQuestIndex = 0;
		lastMap = this.currentMap;
		inventoryIndex = 0;
		//this.questList = new String[10];
	}
	public void move(int movNum) { 
		currentMap.coords[Player.loc[0]][Player.loc[1]] = '.';
		switch (movNum) {
		case 0: // up
//			if (loc[0] == 0 && currentMap.mapNumber < 5) {
//				System.out.println("You cannot continue forward");
//				currentMap.printMap();
//				break;
//			} else if (loc[0] == 0) { 
//				//Map newmap = new Map(currentMap.mapNumber - 5);
//				//currentMap = newmap;
//				changeMap(currentMap.mapNumber - 5); //works fine
//				loc[0] = 4;
//				currentMap.printMap();
//				break;
//			}
			if (!boundaryCheck(0)) { 
				this.action();
				break;
			}
			
			Player.loc[0] -= 1;
			//currentMap.printMap();
			break;
		case 1: // down
			if (!boundaryCheck(1)) { 
				this.action();
				break;
			}
			Player.loc[0] += 1;
			//currentMap.printMap();
			break;
		case 2: // left
			if (!boundaryCheck(2)) { 
				this.action();
				break;
			}
			Player.loc[1] -= 1;
			//currentMap.printMap();
			break;
		case 3: //right 
			if (!boundaryCheck(3)) { 
				this.action();
				break;
			}
			Player.loc[1] += 1;
			//currentMap.printMap();
			break;	
		}
		for(int i = 0; i < currentMap.isAdjacent().length; i++) {
			if(currentMap.isAdjacent()[i]) {
				currentMap.objects[i].Interactions(currentMap.objects[i].objChar);
			}
		}
	}
	public void action() { 
		currentMap.printMap();
		System.out.println("What would you like to do?");
		String input = in.nextLine();
		String input1;
		int jim;
		if (input.indexOf(' ') == -1) {
			jim = input.length();	
		} else { 
			jim = input.indexOf(' ');
		}
		input1 = input.substring(0, jim) ;
		
		
		switch (input1) { 
			case "mov": 
				String input2 = input.substring(input.indexOf(' ') + 1);
				if(input2.equals("up")) {
					move(0);
				} else if(input2.equals("down")) {
					move(1);
				} else if(input2.equals("left")) {
					move(2);
				} else if(input2.equals("right")) {
					move(3);
				}
				break;
			case "questlist":
				if (this.quests[0] == null) { 
					System.out.println("You don't have any quests yet. "
							+ "Go explore some towns and interact with people to find some!");
					break;
				}
				for (int i = 0; i < quests.length; i++) {
					if(quests[i]== null) { 
						break;
					}
					System.out.println(quests[i].toString());
					System.out.println(quests[0].questNumber + "    " + quests[0].currentIndex + this.pHasQuest(27));
					
				}
				break;
			case "exit":
				returnMap();
				break;
			case "l":
				String input3 = input.substring(input.indexOf(' ') + 1);
				if (input3.equals("m")) {
					Quests m = new Quests(27);
					this.addQuest(m);
					InventoryItems cat = new InventoryItems(13, "Marcy's Cat", this);
					this.addItem(cat);
					m.increaseIndex();
					this.quests[this.getQuestArrayNumber(27)].decisionInt = 1; 
				}
				if(input3.equals("p")) {
					this.changeMap(27);
				}
				break;
			case "inventory":
				boolean go = true;
				int i = 0;
				if(inventory[0] == null) { 
					System.out.println("You don't have any items. Go explore and find some!");
					break;
				} else {
					System.out.println("Inventory:");
				}
				while (go) {
					
					if (inventory[i] == null) {
						break;
					} else { 
						System.out.printf("%d. %s\n", i + 1, inventory[i].itemName);
					}
					i++;
				}
				break;
			
			
		}
	}
	public boolean boundaryCheck(int mov) {
		/*
		 * 0 up
		 * 1 down
		 * 2 left
		 * 3 right
		 */
		
		int[] boundaryEdge = 	{0, 4, 0, 4};
		int[] locationCheck = 	{0, 0, 1, 1};
		int[] changeMap = 		{-5, 5, -1, 1};
		int[] changedValues = 	{4, 0, 4, 0};
		
		int[][] boundaryArrays = new int[4][5];
		int[] upBoundary = 		{0, 1, 2, 3, 4}; 		boundaryArrays[0] = upBoundary;
		int[] downBoundary = 	{20, 21, 22, 23, 24};	boundaryArrays[1] = downBoundary;
		int[] leftBoundary = 	{0, 5, 10, 15, 20};		boundaryArrays[2] = leftBoundary;
		int[] rightBoundary = 	{4, 9, 14, 19, 24};		boundaryArrays[3] = rightBoundary;
		
		String[] badMovements = {"forward", "backward", "to the left", "to the right"};
		
		if (loc[locationCheck[mov]] == boundaryEdge[mov] 
				&& Arrays.asList(boundaryArrays[mov]).contains(currentMap.mapNumber)) {
			System.out.printf("A strange force stops you from continuing %s", badMovements[mov]);
			return false;
			
		} else if (loc[locationCheck[mov]] == boundaryEdge[mov]) { 
			changeMap(currentMap.mapNumber + changeMap[mov]); //move the map ahead.
			loc[locationCheck[mov]] = changedValues[mov];	
		}
		return true;
			//currentMap.printMap();
	
	}
	public void changeMap(int mapNumber) {
		//Map newMap = new Map(mapNumber);
		lastMap = currentMap;
		this.currentMap = new Map(mapNumber, this);
	}
	public void returnMap() { 
		currentMap = new Map(Map.motherMap(currentMap.mapNumber), this);
	}
	public static void centerPlayer() {
		loc[0] = 4;
		loc[1] = 2;
	}
	
	public void addQuest(Quests quest) {
		this.quests[playerQuestIndex] = quest;
		this.playerQuestIndex += 1;
	}
	
	public void questList() { 
		for(int i = 0; i < quests.length; i++) { 
			System.out.printf("%d. %s", i, this.quests[i].toString());
		}
	}
	
	public void removeQuest(int questNumber) {
//		for (int i = 0; i < this.quests.length; i++) {
//			if (quests[i])
//			if (quests[i].questNumber == questNumber) {
//				Quests temp = new Quests(quests[i].questNumber);
//				quests[i] = quests[9];
//				quests[9] = null;
//			}
			
			this.quests[this.getQuestArrayNumber(questNumber)] = quests[9];
			quests[9] = null;
		
	
	}
		
	public void addItem(InventoryItems item) {
		System.out.println(item.itemName + " added to inventory");
		this.inventory[inventoryIndex] = item;
	}
	public void removeItem(InventoryItems item) {
		final int indexOfItemToRemove = invItemArrayNumber(item);
		final String itemRemoved = item.itemName;
		inventory[invItemArrayNumber(item)] = inventory[invItemArrayNumber(item) + 1];
		inventory[indexOfItemToRemove + 1] = null;	
		System.out.println(itemRemoved + " removed from inventory.");
	}
	
	public Quests getQuest(int mapNumber) {
		for (int i = 0; i < this.quests.length; i++) {
			if(quests[i].questNumber == mapNumber)  {
				return quests[i];
			}
		}
		return null;
	}
	public int getQuestArrayNumber(int questNumber) {
		for (int i = 0; i < 10; i++) {
			if(quests[i].questNumber == questNumber) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean pHasQuest(int mapNumber) {
		for (int i = 0; i < 10; i++) {
			if(quests[i] == null) {
				return false;
			}
			if(quests[i].questNumber == mapNumber) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkInv(int mapNumber) {
		for (int i = 0; i < inventory.length; i++) {
			if(inventory[i] == null) {
				return false;
			}
			if(inventory[i].itemNumber == mapNumber) {
				return true;
			}
		}
		return false; 
	}
	
	public int invItemArrayNumber(InventoryItems item) {
		for (int i = 0; i < 9; i++) {
			if(this.inventory[i] == null) {
				return -1;
			}
			if(this.inventory[i].itemNumber == item.itemNumber) {
				return i;
			}
		}
		return -1;
	}
}

	
		


