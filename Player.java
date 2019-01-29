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
	InventoryItems[] equipment = new InventoryItems[5];
	
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
					this.addItem(cat, 1);
					m.increaseIndex();
					this.quests[this.getQuestArrayNumber(27)].decisionInt = 1; 
				}
				if(input3.equals("p")) {
					this.changeMap(27);
				}
				if(input3.equals("h")) {
					InventoryItems hides = new InventoryItems(-1, "Hides", this);
					this.addItem(hides, 12);
				}
				break;
			case "inventory":
				boolean go = true;
				
				if(inventory[0] == null) { 
					System.out.println("You don't have any items. Go explore and find some!");
					break;
				} else {
					System.out.println("Inventory:");
				}
				for (int i = 0; i < inventoryIndex; i++) {
					System.out.printf("%d. %dx %s\n", i + 1, inventory[i].quantity, inventory[i].itemName);
				}
				break;
			case "equip":
				String input5 = input.substring(input.indexOf(' ') + 1);
				int inventoryNumber = Integer.parseInt(input5) - 1;
				equip(this.inventory[inventoryNumber]);
				break;
			case "equipment":
				//boolean go = true;
				
				for(int i = 1; i < 5; i++) {
					if(equipment[i] == null) {
						System.out.println(InventoryItems.equipSlot(i) + ": no item equipped");
					} else {
					System.out.printf("%d. %s: %s\n",i, InventoryItems.equipSlot(i), equipment[i + 1].itemName);
					}
				}
			
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
		this.quests[this.getQuestArrayNumber(questNumber)] = quests[9];
		quests[9] = null;
	}
		
	public void addItem(InventoryItems item, int quantity) {
		System.out.println(quantity + " " + 
				item.itemName + " added to inventory");
		if(checkInv(item)) {
			this.inventory[invItemArrayNumber(item)].quantity += quantity;
		} else {
			this.inventory[inventoryIndex] = item;
			this.inventory[inventoryIndex].quantity = quantity;
			inventoryIndex++;
		}
	}
	public void removeItem(InventoryItems item, int quantityToRemove) {
		if(checkInv(item)) { 
			inventory[invItemArrayNumber(item)].quantity -= quantityToRemove;
			if (inventory[invItemArrayNumber(item)].quantity > 0) {
				System.out.printf("%dx %s removed from inventory.\n",quantityToRemove,item.itemName);
				return;
			}
		} 
		System.out.println(inventory[invItemArrayNumber(item)].quantity + " Quantity");
		final int indexOfItemToRemove = invItemArrayNumber(item);
		final String itemRemoved = item.itemName;
		inventory[indexOfItemToRemove] = null;
		for (int i = indexOfItemToRemove + 1; i < 10; i++) {
			if(inventory[i] == null) {
				
				inventoryIndex -= 1;
				break;
			} else {
				
				inventory[i - 1] = inventory[i];
				
				inventory[i] = null;
			}
		}
		
		System.out.println(itemRemoved + " removed from inventory.");
		System.out.println(inventoryIndex);
		
		
	
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
	public boolean checkInv(InventoryItems item) {
		for (int i = 0; i < inventory.length; i++) {
			if(inventory[i] == null) {
				return false;
			}
			if(inventory[i].itemNumber == item.itemNumber) {
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
	public int invItemArrayNumber(int itemNumber) {
		for (int i = 0; i < 9; i++) {
			if(this.inventory[i] == null) {
				return -1;
			}
			if(this.inventory[i].itemNumber == itemNumber) {
				return i;
			}
		}
		return -1;
	}
	
	public void buyItems(InventoryItems item) {
		if (item.price != 1) {
			System.out.println("\"Would you like to buy " 
				+ item.itemName + " for " + item.price + " Hides?\\\"\nEnter y to confirm");
		} else {
			System.out.println("\"Would you like to buy a " 
					+ item.itemName + " for " + item.price + " Hide?\"");
		}
		String doTheyWantToBuyThisShit = in.nextLine();
		if (doTheyWantToBuyThisShit.equals("y")) {
			if (!(this.invItemArrayNumber(-1) == -1) &&
					this.inventory[this.invItemArrayNumber(-1)].quantity >= item.price) {
				removeItem(this.inventory[this.invItemArrayNumber(-1)], item.price);
				addItem(item, item.attributes.quantitySold);
			} else {
				System.out.println("\"You don't have enough Hides man, go kill some cows.\"");
			}
		
		}
	}
	
	public void equip(InventoryItems item) {
		if (this.equipment[item.attributes.equipSlot] == null &&
				!(item.attributes.equipSlot == 0)) {
			this.equipment[item.attributes.equipSlot] = item;
			removeItem(item, 1);
			System.out.println(item.itemName + " equipped to the the " 
					+ InventoryItems.equipSlot(item.attributes.equipSlot + 1));
			return;
		} else { 
			unEquip(this.equipment[item.attributes.equipSlot]);
			equip(item);
		}
	}
	
	public void unEquip(InventoryItems item) {
		addItem(item, 1);
		this.equipment[item.attributes.equipSlot] = null;
	}
}

	
		


