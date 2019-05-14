import java.util.Scanner;
import java.util.Arrays;

public class Player {
	public static int[] 			loc = new int[2];
	public static Map 				currentMap; 
	public Scanner 					in = new Scanner(System.in);
	public static boolean 			isInBuilding;
	//public static String[] 		questList;
	public static int 				playerQuestIndex;
	public static Quests[] quests = 		new Quests[10];
	public static Map				lastMap;
	public static InventoryItems[] inventory = 	new InventoryItems[10];
	public static int 				inventoryIndex;
	InventoryItems[] equipment = 	new InventoryItems[5];
	public int health;
	public static boolean enteringBuilding;
	/*		Modifiers indexing
	 * 	 0. Attack = 	0;
	 *	 1. Strength = 	0;
	 *	 2. Defence 
	 *	 3. hpModifier = 0;
	 */
	public int[] modifiers; 
	
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
		AccessoryMethods.addAttributes(equipment, this);
		health = modifiers[3] + 10;
	}
	public void move(int movNum) { 
		currentMap.coords[Player.loc[0]][Player.loc[1]] = '.';
		if(!(currentMap.isOverRiding() == '`')) {
			currentMap.coords[Player.loc[0]][Player.loc[1]] = currentMap.isOverRiding();
		}
		
		if(currentMap.isAdjacent(movNum)) {
			System.out.println("Something is in your way and cannot move this direction");
			action();
		}
		switch (movNum) {
		case 0: // up
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
		
		boolean[] isAdjacent = currentMap.isAdjacent();
			for(int i = 0; i < isAdjacent.length; i++) {
				if(isAdjacent[i] && !enteringBuilding) {
					currentMap.objects[i].Interactions(currentMap.objects[i].objChar);
				}
			}
			enteringBuilding = false;
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
					System.out.printf("%d. %s\n\n", i + 1, quests[i].toString());
					
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
				if(input3.equals("l")) {
					Attributes p_b = new Attributes(10, 10, 0, 0, 0, 4, 1, true);
					InventoryItems Plastic_Blade = new InventoryItems(-2, "Plastic Blade", this, 3, p_b);
					Attributes t_l = new Attributes(0, 0, 0, 0, 3, -1, 2, false);
					InventoryItems Turkey_Leg = new InventoryItems(-3, "Turkey Leg", this, 3, t_l);
					this.addItem(Turkey_Leg, 2);
					this.addItem(Plastic_Blade, 1);
				}
				if(input3.equals("k")) {
					this.changeMap(28);
					Attributes wd = new Attributes(0, 0, 0, 0, 0, 0, 1, false);
					InventoryItems Weed = new InventoryItems(13, "1g of Gilliweed", this, 2, wd);
					addItem(Weed, 2);
					addItem(InventoryItems.Raw_Beef, 1);
					Quests main = new Quests(28);
					addQuest(main);
					main.increaseIndex();
					main.increaseIndex();
					Quests actualMain = new Quests(1);
					addQuest(actualMain);
					
				}
				
				break;
			case "inventory":
				boolean go = true;
				if(inventory[0] == null) { 
					System.out.println("\nYou don't have any items. Go explore and find some!\n");
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
				if(this.inventory[inventoryNumber] == null) {
					System.out.println("Ain't got an item in that inventory spot boy");
					break;
				}
				equip(this.inventory[inventoryNumber]);
				AccessoryMethods.addAttributes(equipment, this);
				break;
			
			case "unequip":
				String input7000 = input.substring(input.indexOf(' ') + 1);
				int lazyCodingNumber = Integer.parseInt(input7000);
				if(this.equipment[lazyCodingNumber] == null) {
					System.out.println("Ain't got an item in that equipment spot boy");
					break;
				}
				unEquip(this.equipment[lazyCodingNumber]);
				AccessoryMethods.addAttributes(equipment, this);
				break;
				
			case "equipment":
				//boolean go = true;
				
				for(int i = 1; i < 5; i++) {
//					if(equipment[i] == null && (i == 1 || i == 2)) {
//						System.out.println(i + ". " + InventoryItems.equipSlot(i) + ":\t\t no item equipped");
//					}
					if(equipment[i] == null) {
						System.out.println(i + ". " + InventoryItems.equipSlot(i) + ":\t no item equipped");
					} else {
					System.out.printf("%d. %s:\t %s\n",i, InventoryItems.equipSlot(i), equipment[i].itemName);
					}
				}
				
				for (int j = 0; j < 4; j++) {
//					System.out.printf(format, args)
				}
				break;
			case "stats":
				// String jcsdim = (input.substring(input.indexOf(' ') + 1));
				if ((input.substring(input.indexOf(' ') + 1).equals("stats"))) {
					String[] modifierStrings = Attributes.modifierStrings(equipment);
					for(int i = 0; i < 5; i++) {  
						if((modifierStrings[i] != (null))) {
							System.out.print(equipment[i].itemName + ":");
							System.out.println(modifierStrings[i]);
							System.out.println();;
						}
					}
					System.out.println("Health: " + health);
				} else {
					int input4 = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
					if(equipment[input4] == null) {
						System.out.println("You don't have anything equipped in that slot\n");
					} else { 
						Attributes.printAttributes(equipment[input4].attributes);
					}
					
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
		Player.currentMap = new Map(mapNumber, this);
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
		
	public static void addItem(InventoryItems item, int quantity) {
		System.out.println(quantity + " " + 
				item.itemName + " added to inventory");
		if(checkInv(item)) {
			inventory[invItemArrayNumber(item)].quantity += quantity;
		} else {
			inventory[inventoryIndex] = item;
			inventory[inventoryIndex].quantity = quantity;
			inventoryIndex++;
		}
	}
	public static void removeItem(InventoryItems item, int quantityToRemove) {
		if(checkInv(item)) { 
			inventory[invItemArrayNumber(item)].quantity -= quantityToRemove;
			if (inventory[invItemArrayNumber(item)].quantity > 0) {
				System.out.printf("%dx %s removed from inventory.\n",quantityToRemove,item.itemName);
				return;
			}
		} 
		//System.out.println(inventory[invItemArrayNumber(item)].quantity + " Quantity");
		
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
		//System.out.println(inventoryIndex);
		
		
	
	}
	
	
	
	public Quests getQuest(int mapNumber) {
		for (int i = 0; i < this.quests.length; i++) {
			if(quests[i].questNumber == mapNumber)  {
				return quests[i];
			}
		}
		return null;
	}
	public static int getQuestArrayNumber(int questNumber) {
		if(quests[0] == null) {
			return -1;
		}
		for (int i = 0; i < 10; i++) {
			if(quests[i].questNumber == questNumber) {
				return i;
			}
		}
		return -1;
	}
	
	public static boolean pHasQuest(int mapNumber) {
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
	
	public static boolean checkInv(int mapNumber) {
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
	public static boolean checkInv(InventoryItems item) {
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
	
	public static int invItemArrayNumber(InventoryItems item) {
		for (int i = 0; i < 9; i++) {
			if(inventory[i] == null) {
				return -1;
			}
			if(inventory[i].itemNumber == item.itemNumber) {
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
				Quests.checkQuestItem(item, currentMap.mapNumber, this);
			} else {
				System.out.println("\"You don't have enough Hides man, go kill some cows.\"");
			}
		
		}
	}
	public void sellItems(InventoryItems item) {
		int sellingPrice = (item.price / 2) + 1;
		if (item.price != 1) {
			System.out.println("\"Would you like to sell " 
				+ item.itemName + " for " + sellingPrice + " Hides?\\\"\nEnter y to confirm");
		} else {
			System.out.println("\"Would you like to buy a " 
					+ item.itemName + " for " + sellingPrice + " Hide?\"");
		}
		String doTheyWantToBuyThisShit = in.nextLine();
		if (doTheyWantToBuyThisShit.equals("y")) {
			if(!(item.attributes == null)) {
				removeItem(item, item.attributes.quantitySold);
				addItem(InventoryItems.Hides, sellingPrice);
			}	
		}
	}
	
	public void equip(InventoryItems item) {
		if (item.attributes == null) {
			System.out.println("\nThat item cannot be equipped\n");
			return;
		}
		if(item.attributes.equipSlot < 0) {
			item.consume();
			return;
		}
		if (this.equipment[item.attributes.equipSlot] == null &&
				!(item.attributes.equipSlot == 0)) {
			this.equipment[item.attributes.equipSlot] = item;
			removeItem(item, 1);
			System.out.println(item.itemName + " equipped to the the " 
					+ InventoryItems.equipSlot(item.attributes.equipSlot));
			return;
		} else { 
			unEquip(this.equipment[item.attributes.equipSlot]);
			equip(item);
		}
		AccessoryMethods.addAttributes(equipment, this);
	}
	
	public void unEquip(InventoryItems item) {
		addItem(item, 1);
		System.out.println(item.itemName + " removed from equipment.\n");
		this.equipment[item.attributes.equipSlot] = null;
		AccessoryMethods.addAttributes(equipment, this);
	}
	public static boolean hasCompletedQuest(int mapNumber) {
		if(pHasQuest(mapNumber)) {
			return quests[getQuestArrayNumber(mapNumber)].isComplete;
		}
		return false;
	}
}

	
		


