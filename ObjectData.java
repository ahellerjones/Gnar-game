import java.util.Scanner;

public class ObjectData {
	public static InventoryItems dropLists[][] = new InventoryItems[20][];
	
	public static String findName(int mapNumber, char objChar) { 
		switch (objChar) { 
			case 'T':
				return buildingNames(mapNumber, objChar);
			case 'Ω':
				return buildingNames(mapNumber, objChar);
			case 'p':
				return npcNames(mapNumber, objChar);
			case 't':
				return npcNames(mapNumber, objChar);
			case 'ø':
				return npcNames(mapNumber, objChar);
			case 'c':
				return npcNames(mapNumber, objChar);
		}
		return ""; // stub
	}
	
	public static String buildingNames(int mapNumber, char objChar) {
		//___Building Names Arrays___\\
		String[] townNames = new String[50];
		String[] tavernNames = new String[50];
		//___Town Names___\\
		townNames[12] = 	"The Town of Gnarivores";
		
		//___Tavern Names___\\	
		tavernNames[26] = 	"Cathedral Tavern";
		
		switch (objChar) {
			case 'T': 
				return townNames[mapNumber];
			case 'Ω':
				return tavernNames[mapNumber];
		}
		return ""; // stub.
		
	}
	
	public static int buildingNumbers(int mapNumber, char objChar) {
		// Building Numbers arrays
		int[] townNumbers = 	new int[25];
		int[] tavernNumbers = 	new int[45];
		
		//___Town Numbers___\\	
		townNumbers[12] = 26; 	// Town Gnarivores on map 12 is on map 26.
		
		//___Tavern Numbers___\\			
		tavernNumbers[26] = 27;	// Tavern in Gnarivores on map 26 is on map 27.
		
		switch (objChar) { 
			case 'T':
				return 	townNumbers[mapNumber];
			case 'Ω':
				return 	tavernNumbers[mapNumber];
		}
		
		
		return -1; //stub 
	}
	public static String npcNames (int mapNumber, char objChar) {
		String[] talkerNames = new String[40];
		talkerNames[27] = "Marcy"; // map 27's talking NPC's name is Marcy
		
		
		String[] traderNames = new String[40];
		traderNames[27] = "Joe McShm'o";
		
		switch (objChar) { 
			case 'p': 
				return talkerNames[mapNumber];
			case 't':
				return traderNames[mapNumber];
			case 'ø':
				return "Marcy's cat";
			case 'c':
				return "Cow";
		}
		return ""; // stub 
	}
	
	/*
	 * Attack, strength, defence, hp, level
	 */
	public static int[] npcArray(int mapNumber, char objChar) {
		switch (objChar) { 
			case 'c':
				int[] cowArray = {1, 1, 3, 4, 2};
				return cowArray;
		}
		return null; // stub 
	}
	/**
	 * The purpose of out sourcing this method to a whole nother class is to try and keep things neater by not storing all the 
	 * dialogue data in this data storage which can get cumbersome.
	 */
	public static String[] npcDialogue(int mapNumber, char objChar) {
		return NpcData.dialogueStorage(mapNumber, objChar);
	}
	/**
	 * returns whether or not an npc has a quest
	 * @param mapNumber
	 * @param npcChar
	 * @return
	 */
	public static boolean hasQuest(int mapNumber, char objChar) { 
		boolean[] hasQuest = new boolean[40];
		hasQuest[27] = true;
		switch (objChar) { 
		case 'p':
			return hasQuest[mapNumber];
		case 't':
			return false;
		}
		
		return hasQuest[mapNumber];
	}
	public static boolean wantsToTrade(int mapNumber, char objChar) { 
		boolean[] wantsToTrade = new boolean[40];
		wantsToTrade[27] = false; // some people want to trade... some don't.
		switch (objChar) { 
		case 'p':
			return wantsToTrade[mapNumber];
		case 't':
			return true;
		}
		
		return wantsToTrade[mapNumber];
	}
	
	public static InventoryItems[] traderInventories(int mapNumber, Player p) {
		switch (mapNumber) {
		/*
		 * InventoryItems: 	int mapNumber, String itemName, Player player, int price, Attributes itemAttributes
		 * Attributes: 	int attack, int strength, defence int hpModifier, int hpHeal, int equipSlot, int quantitySold		 	
		 */
		
		case 27:
				Attributes p_b = new Attributes(5, 5, 0, 0, 0, 4, 1, true);
				InventoryItems Plastic_Blade = new InventoryItems(-2, "Plastic Blade", p, 3, p_b);
				Attributes t_l = new Attributes(0, 0, 0, 0, 3, -1, 2, false);
				InventoryItems Turkey_Leg = new InventoryItems(-3, "Turkey Leg", p, 3, t_l);
				InventoryItems[] inv27 = {Plastic_Blade, Turkey_Leg} ;
				return inv27;
				
		}
		return null; //stub 
		
	}
	public static InventoryItems printInv(InventoryItems[] inventory, Player p) {
		Scanner in = new Scanner(System.in);
		String[] inventoryStringList = new String[inventory.length];
		for(int k = 0; k < inventory.length; k++) {
			inventoryStringList[k] = String.format(
					"\n|%dx %d. %s \t%d Hides|", 
						k + 1, inventory[k].attributes.quantitySold, inventory[k].itemName, inventory[k].price);
		}
		
		int tester = 0;
		for(int i = 0; i < inventory.length; i++) {
			if(inventoryStringList[i].length() > tester) {
				tester = inventoryStringList[i].length();
			}
		}
		for(int j = 0; j < tester + 3; j++) {
			System.out.print("-");
		}
		for(int k = 0; k < inventory.length; k++) {
			System.out.print(inventoryStringList[k]);
		}
		System.out.println();
		for(int j = 0; j < tester + 3; j++) {
			System.out.print("-");
		}
		System.out.println();
		System.out.println("\"Would you like to buy anything?\"\n"
				+ "Enter \"buy itemNumber\" to buy, or \"stats itemNumber\" for the item attributes\n"
				+ "or 'n' to exit");
		String input = in.nextLine();
		int jim;
		if (input.indexOf(' ') == -1) {
			jim = input.length();	
		} else { 
			jim = input.indexOf(' ');
		}
		String input1 = input.substring(0, jim) ;
		switch(input1) { 
			case("stats"):
				String input2 = input.substring(input.indexOf(' ') + 1);
				try {
					Integer.parseInt(input2);
				} catch(Exception e) { 
					System.out.println("*** If you want to the stats of the item listed at 1., Enter "
							+ "'stats 1' ***");
					printInv(inventory, p);
				}
				int itemSelection = Integer.parseInt(input2) - 1;
				if (itemSelection >= inventory.length) {
					System.out.println("Not a valid selection, try again nerd");
					printInv(inventory, p);
					break;
				} else {
					Attributes.printAttributes(inventory[itemSelection].attributes);
				}
				printInv(inventory, p);
				break;
			case("buy"):
//				InventoryItems hides = new InventoryItems(-1, "blart", p);
//				p.buyItems(hides);
//				
//					
				String input4 = input.substring(input.indexOf(' ') + 1);
					try {
						Integer.parseInt(input4);
					} catch(Exception e) { 
						System.out.println("*** If you want to buy the item listed at 1., Enter "
								+ "'buy 1' ***");
							return printInv(inventory, p);
					}
					int itemSelection1 = Integer.parseInt(input4) - 1;
					if (itemSelection1 >= inventory.length) {
						System.out.println("Not a valid selection, try again nerd");
						return printInv(inventory, p);
					} else {
						p.buyItems(inventory[Integer.parseInt(input4) - 1]);
					}
		}
		return null;
				
	}
	/* Use the quantity InventoryItems constructor
	 * int mapNumber, String itemName, Player player, int price, Attributes itemAttributes,
			int quantity
	 */
	public static InventoryItems[] npcDrops(int mapNumber, char objChar, Player p) {
		InventoryItems[] cowDrops = new InventoryItems[2];
		cowDrops[0] = new InventoryItems(-1, "Hides", p, 0, null, 3);
		cowDrops[1] = new InventoryItems(-4, "Raw beef", p, 0, null, 1);
		switch(objChar) {
		case 'c':
			return cowDrops;
		
		case 'd':
			return dropLocations(mapNumber, objChar);
		}
	return null;
	}
	
	public static void dropAdd(int mapNumber, char objChar, 
			InventoryItems[] drops) {
		switch (objChar) {
		case 'c':
			switch(mapNumber) {
				case 12:
					dropLists[0] = drops;
					break;
			}
		break;
		}
		
	}
	public static InventoryItems[] dropLocations(int mapNumber, char objChar) {
		switch (objChar) {
			case 'd':
				switch(mapNumber) {
					case 12:
						return dropLists[0];
				}
				break;
		}
		return null;
	}
	
	public static void tripFlags(int mapNumber, char objChar) {
		switch (objChar) {
		case 'c':
			switch(mapNumber) {
				case 12:
					MapBuilder.c12Flag = true;
			}
			break;
		}
	}
}

