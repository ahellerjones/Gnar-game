import java.util.Scanner;

public class AccessoryMethods {
	InventoryItems[] c7Inv;
	InventoryItems[] c12Inv;
	InventoryItems[] d27Inv;
	
	/*
	 * Attack - 5
	 * Strength - 5
	 * Defence - 5
	 * hpModifier - which is always base 10
	 */
	@SuppressWarnings("unused")
	public static void addAttributes(InventoryItems[] equipment, Player p) {
		int[] nullSet = {5, 5, 5, 0};
//		if (p.modifiers == null) {
//			return nullSet;
//		}
		p.modifiers = nullSet;
		for (int i = 0; i < 4; i++) {
			for(int j = 1; j < 5; j++) {
				if (p.equipment[j] == null) {
				} else {
					p.modifiers[i] += equipment[j].attributes.attributesArray[i];
				}
			}
		}
	}
	public static int[] calculateHits(Player p, int[] enemyArray) {
		// First number in the array is the damage the player gives,
		// The second is the damage the enemy gives. 
		int[] returnArray = new int[2];
		double hit = Math.random() * (p.modifiers[0] / enemyArray[2]);
		hit += hit * ((p.modifiers[1] + 60) / 100);
		if(hit % 1 < .5) {
			returnArray[0] = (int) hit;
		} else {
			returnArray[0] = (int)(hit + 1);
		}
		
		double enemyHit = Math.random() * (enemyArray[0] / p.modifiers[2]);
		enemyHit += enemyHit * ((enemyArray[1] + 60) / 100);
		if (enemyHit % 1 < .5) {
			returnArray[1] = (int) enemyHit;
		} else {
			returnArray[1] = (int)(enemyHit + 1);
		}
		return returnArray;
	}
	
	public static void die(Player p) {
		System.out.println("Oh dear, you are dead!");
		p.changeMap(12);
	}
	public static InventoryItems[] viewDrops(InventoryItems[] drops, Player p, char objChar) {
		Scanner in = new Scanner(System.in);
		int dropsCount = 0;
		if(drops[0] == null) {
			System.out.println("\nYou've already looted all of the drops from here");
			return null;
		}
		while (dropsCount < drops.length && !(drops[dropsCount] == null)) {
			System.out.printf("%d. %dx %s\n",dropsCount + 1, drops[dropsCount].quantity, 
					drops[dropsCount].itemName);
			dropsCount++;
		}
		System.out.println("\nWould you like to pick up the drops?\n"
				+ "Enter the list number of the item you want, and 'end' when done\n"
				+ "Or enter 'all' to take all itmes.\n");
		boolean continueLooting = true;
		while(continueLooting) {
			if (drops[0] == null) {
				return drops;
			}
			String input = in.nextLine();
			if(input.equals("1") || input.equals("2") ||
						input.equals("3") || input.equals("4") ||
						 input.equals("5")) {
				int inventoryNumber = Integer.parseInt(input) - 1;
					if(inventoryNumber > drops.length) {
						System.out.println("Choose an item using it's index in the drop list\n");
						viewDrops(drops, p, objChar);
					}
					if(drops[inventoryNumber] == null  ) {
					System.out.println("Choose an item using it's index in the drop list\n");
					viewDrops(drops, p, objChar);
					} else {
					p.addItem(drops[inventoryNumber], drops[inventoryNumber].quantity);
					// No idea if this works below, copied from Player.removeItem()
					drops[inventoryNumber] = null;
					for (int i = inventoryNumber; i < drops.length; i++) {
						try { 
							if (drops[i + 1] == null) {
								break;
							}
						} catch (Exception e) {
							break;
						}
						drops[i] = drops[i + 1];
						drops[i + 1] = null;
					}
					viewDrops(drops, p, objChar);
					break;
				}
			}
			
			switch(input) {
				case "all":
					if (!(drops[0] == null)) {
	 					for (int i = 0; i < drops.length; i++) {
							p.addItem(drops[i], drops[i].quantity);
							drops[i] = null;
	 					}
					}
					continueLooting = false;
					break;
				case "end":
					continueLooting = false;
					break;
			}
		}
	ObjectData.dropAdd(Player.currentMap.mapNumber, objChar, drops);	

	p.changeMap(Player.currentMap.mapNumber);
	return drops;
	
	}
	public static void rangeCook() {
		
		if(!Player.hasCompletedQuest(Player.currentMap.mapNumber)) {
			System.out.println("\nYou do not have permission to use this range\n");
			return;
		}
		
		Scanner in = new Scanner(System.in);
		if (Player.inventory[0] == null) {
			System.out.println("You have no items to cook!");
		} else {
			System.out.println("Enter the inventory index of the item you wish to cook");
			for (int i = 0; i < Player.inventoryIndex; i++) {
				System.out.printf("%d. %dx %s\n", i + 1, Player.inventory[i].quantity, Player.inventory[i].itemName);
			}
			int indexOfItemToCook = in.nextInt() - 1;
			//Player.removeItem(Player.inventory[indexOfItemToCook], 1);
			Player.addItem(cookItem(Player.inventory[indexOfItemToCook]), 1);
		}
	}
	public static InventoryItems cookItem(InventoryItems item) { 
		InventoryItems cookedList[] = new InventoryItems[30];
		cookedList[3] = InventoryItems.Turkey_Leg;
		cookedList[5] = InventoryItems.Beef;
		Player.removeItem(Player.inventory[Player.invItemArrayNumber(item)], 1);		
			if (item.attributes.equipSlot == -1 ||
					item.attributes.equipSlot == -2) {
				return(cookedList[-item.itemNumber]);
		}
			//Player.removeItem(Player.inventory[Player.invItemArrayNumber(item)], 1);
		return null;
	}
}
	
