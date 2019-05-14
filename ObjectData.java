import java.util.Scanner;
/*
 * InventoryItems: 	int mapNumber, String itemName, Player player, int price, Attributes itemAttributes
 * Attributes: 	int attack, int strength, defence int hpModifier, int hpHeal, int equipSlot, int quantitySold		 	
 */

/* Protocol for adding attackable Npc's :
 * Add character to as a changeable object using the method in MapBuilder
 * Add object to the map's objects array
 * Add entry into NPCdrops method
 * Add entry into droplocations, tripFlags (add flag to mapbuilder too), and dropAdd
 * 
 * 
 */



public class ObjectData {
	public static InventoryItems dropLists[][] = new InventoryItems[20][];
	public static boolean sellMode; //sell = true
	public static String findName(int mapNumber, char objChar) { 
		switch (objChar) { 
			case 'T':
				return buildingNames(mapNumber, objChar);
			case 'Ω':
				return buildingNames(mapNumber, objChar);
			case '¢': 
				return buildingNames(mapNumber, objChar);
			case 'p':
				return npcNames(mapNumber, objChar);
			case 't':
				return npcNames(mapNumber, objChar);
			case 'ø':
				return npcNames(mapNumber, objChar);
			case 'c':
				return npcNames(mapNumber, objChar);
			case 'œ':
				return npcNames(mapNumber, objChar);
			case 'l':
				return npcNames(mapNumber, objChar);
		}
		return ""; // stub
	}
	
	public static String buildingNames(int mapNumber, char objChar) {
		//___Building Names Arrays___\\
		String[] townNames = new String[50];
		String[] tavernNames = new String[50];
		String[] messHallNames = new String[50];
		//___Town Names___\\
		townNames[12] = 	"The Town of Gnarivores";
		
		//___Tavern Names___\\	
		tavernNames[26] = 	"Cathedral Tavern";
		
		//___Mess Hall Names___\\
		messHallNames[26] = "Gnarivore's mess hall";
		
		switch (objChar) {
			case 'T': 
				return townNames[mapNumber];
			case 'Ω':
				return tavernNames[mapNumber];
			case '¢':
				return messHallNames[mapNumber];
		}
		return ""; // stub.
		
	}
	
	public static int buildingNumbers(int mapNumber, char objChar) {
		// Building Numbers arrays
		int[] townNumbers = 	new int[25];
		int[] tavernNumbers = 	new int[45];
		int[] messHallNumbers = new int[45];
		//___Town Numbers___\\	
		townNumbers[12] = 26; 	// Town Gnarivores on map 12 is on map 26.
		
		//___Tavern Numbers___\\			
		tavernNumbers[26] = 27;	// Tavern in Gnarivores on map 26 is on map 27.
		
		//__Mess Hall Numbers___\\
		messHallNumbers[26] = 28; //Mess hall in Gnarivores on map 26 is on map 28.
		
		switch (objChar) { 
			case 'T':
				return 	townNumbers[mapNumber];
			case 'Ω':
				return 	tavernNumbers[mapNumber];
			case '¢':
				return messHallNumbers[mapNumber];
		}
		
		
		return -1; //stub 
	}
	public static String npcNames (int mapNumber, char objChar) {
		String[] talkerNames = new String[40];
		talkerNames[27] = "Marcy"; // map 27's talking NPC's name is Marcy
		talkerNames[26] = "Old Lady";
		String[] miscNames = new String[40];
		miscNames[28] = "Mistah Cook";
		String[] traderNames = new String[40];
		traderNames[27] = "Joe McShm'o";
		traderNames[13] = "Sketchy Dude";
		
		switch (objChar) { 
			case 'p': 
				return talkerNames[mapNumber];
			case 't':
				return traderNames[mapNumber];
			case 'ø':
				return "Marcy's cat";
			case 'œ':
				return miscNames[mapNumber];
			case 'c':
				return "Cow";
			case 'l': 
				return "Evil Lizard Man";
		}
		return ""; // stub 
	}
	
	/*
	 * Attack, strength, defence, hp, level
	 */
	public static int[] npcArray(int mapNumber, char objChar) {
		switch (objChar) { 
			case 'c':
				int[] cowArray = {7, 10, 4, 7, 4};
				return cowArray;
			case 'l':
				int[] lizardArray = {15, 15, 15, 15, 15};
				return lizardArray;
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
		hasQuest[28] = true;
		hasQuest[27] = true;
		hasQuest[26] = false;
		switch (objChar) { 
		case 'p':
			return hasQuest[mapNumber];
		case 't':
			return false;
		case 'ø':
			return hasQuest[mapNumber];
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
	 * InventoryItems: 	
	 * int mapNumber, String itemName, Player player, int price, Attributes itemAttributes
	 * Attributes: 	
	 * int attack, int strength, int defence int hpModifier, int hpHeal, int equipSlot, int quantitySold boolean isEquip		 	
	 */
		
		/* General equip slots -- 
		 * 1. Head
		 * 2. Body
		 * 3. Necklace
		 * 4. Weapon
		 */

		/* 
		 * weird equip slots --
		 * -1 hpHeal raw food to be cooked
		 * -2 hpHeal cooked food that can be eaten for more hp
		 */
		
		case 27:
				Attributes p_b = new Attributes(10, 10, 0, 0, 0, 4, 1, true);
				InventoryItems Plastic_Blade = new InventoryItems(-2, "Plastic Blade", p, 3, p_b);
				Attributes t_l = new Attributes(0, 0, 0, 0, 3, -1, 2, false);
				InventoryItems Turkey_Leg = new InventoryItems(-3, "Turkey Leg", p, 3, t_l);
				Attributes plateBody1 = new Attributes(0, 0, 10, 0, 0, 2, 1, true);
				InventoryItems Tough_Body_Armor = new InventoryItems(-3, "Body Armor", p, 8, plateBody1);
				InventoryItems[] inv27 = {Plastic_Blade, Tough_Body_Armor, Turkey_Leg} ;
				return inv27;
				
		case 13: 
			Attributes helmet1 = new Attributes(0, 0, 5, 0, 0, 1, 1, false);
			InventoryItems Simple_Helmet = new InventoryItems(-6, "Simple Helmet", p, 5, helmet1);
			Attributes wd = new Attributes(0, 0, 0, 0, 0, 0, 1, false);
			Attributes boat = new Attributes(0, 0, 0, 0, 0, 0, 1, false);
			if(Player.pHasQuest(28) && (Player.quests[Player.getQuestArrayNumber(28)].currentIndex == 0 ||
					Player.quests[Player.getQuestArrayNumber(28)].currentIndex == 1)) {
				InventoryItems Weed = new InventoryItems(13, "1g of Gilliweed", p, 2, wd);
				InventoryItems[] inv13 = {Weed, Simple_Helmet};
				return inv13;
			}
			
			InventoryItems kayak = new InventoryItems(13, "Dagger Mamba", p, 80, boat);
			InventoryItems[] inv13 = {Simple_Helmet, kayak};
			return inv13;
		}
		return null; //stub 
		
	}
	public static InventoryItems printInv(InventoryItems[] inventory, Player p) {
		Scanner in = new Scanner(System.in);
		String[] inventoryStringList = new String[inventory.length];
		for(int k = 0; k < inventory.length; k++) {
			if (inventory[k] == null){
				k = inventory.length;
			} else if(inventory[k].attributes == null) {
				
			} else if(sellMode){
				inventoryStringList[k] = String.format(
					"\n|%d. %dx %s \t%d Hides|", 
						k + 1, inventory[k].attributes.quantitySold, inventory[k].itemName, ((inventory[k].price / 2) + 1));
			} else {
				inventoryStringList[k] = String.format(
						"\n|%d. %dx %s \t%d Hides|", 
							k + 1, inventory[k].attributes.quantitySold, inventory[k].itemName, inventory[k].price);
			}
		}
		int l = 0;
		int [] positionArray = new int[10];
		for(int i = 0; i < inventory.length; i++) {
			if(!(inventoryStringList[i] == null)) {
				positionArray[l] = i;
				l++;	
			}
		}
		String[] fixedInventoryStringList = new String[l];
		
		for (int i = 0; i < l; i++) {
			fixedInventoryStringList[i] = inventoryStringList[positionArray[i]];
		}
		
		int tester = 0;
		if(l == 0) {
			System.out.println("Aint got no items to sell boy.");
			return null;
		} else {
			for(int i = 0; i < fixedInventoryStringList.length; i++) {
				if(fixedInventoryStringList[i].length() > tester) {
					tester = fixedInventoryStringList[i].length();
				}
			}
			for(int j = 0; j < tester + 3; j++) {
				System.out.print("-");
			}
			for(int k = 0; k < fixedInventoryStringList.length; k++) {
				System.out.print(fixedInventoryStringList[k]);
			}
			System.out.println();
			for(int j = 0; j < tester + 3; j++) {
				System.out.print("-");
			}
			System.out.println();
			if (sellMode) { 
				System.out.println("Enter the 'sell inventoryNumber' to sell an item in your inventory\n"
						+ "Or just press enter to exit");
			} else {
			System.out.println("\"Would you like to buy anything?\"\n"
					+ "Enter \"buy itemNumber\" to buy, or \"stats itemNumber\" for the item attributes\n"
					+ "Enter \"sellmode\" to enter sell mode or 'n' to exit");
			}
			int jim;
			String input = in.nextLine();
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
					break;
				case("sell"):			
				String input5 = input.substring(input.indexOf(' ') + 1);
					try {
						Integer.parseInt(input5);
					} catch(Exception e) { 
						System.out.println("*** If you want to sell the item listed at 1., Enter "
								+ "'sell 1' ***");
							return printInv(inventory, p);
					}
					int itemSelection2 = Integer.parseInt(input5) - 1;
					if (itemSelection2 >= inventory.length) {
						System.out.println("Not a valid selection, try again nerd");
						return printInv(inventory, p);
					} else {
						p.sellItems(inventory[Integer.parseInt(input5) - 1]);
					}
					break;
					
				case("sellmode"):
					sellMode = true;	
					printInv(Player.inventory, p);
					sellMode = false;
			}
		}
		return null;
				
	}
	/* Use the quantity InventoryItems constructor
	 * nt mapNumber, String itemName, Player player, int price, Attributes itemAttributes,
			int quantity
	 */
	// Attributes: 	int attack, int strength, 
	// defence int hpModifier, int hpHeal, int equipSlot, int quantitySold boolean isEquip
	public static InventoryItems[] npcDrops(int mapNumber, char objChar, Player p) {
		Attributes lizardSworda = new Attributes(14, 14, 0, 2, 0, 4, 1, true);
		InventoryItems lizardSword = new InventoryItems(11, "Lizard Sword", p, 20, lizardSworda, 1);
		InventoryItems[] cowDrops = new InventoryItems[2];
		InventoryItems[] lizardDrops = new InventoryItems[2];
		lizardDrops[0] = InventoryItems.Hides;
		lizardDrops[1] = lizardSword;
		cowDrops[0] = InventoryItems.Hides;
		cowDrops[1] = InventoryItems.Raw_Beef;
		switch(objChar) {
		case 'c':
			return cowDrops;
		case 'l':
			return lizardDrops;
		
		case 'd':
			return dropLocations(mapNumber, objChar);
		}
	return null;
	}
	
	/*Drop lists: All the drops (from dead npc's) are stored in the double Array
	 * dropAdd adds the certain drops to a certain index in the array
	 * EVERY UNIQUE NPC HAS A UNIQUE INDEX IN THE dropList,
	 * For example, the dead cow at map 12's drop is stored in
	 * dropList[0]
	 * The dead cow in map 26 is at 1;
	 * cow @ 7 is 2 
	 * lizard man @ 11 is 3 
	 */
	
	public static void dropAdd(int mapNumber, char objChar, 
			InventoryItems[] drops) {
		switch (objChar) {
		//cows
		case 'c':
			switch(mapNumber) {
				case 7:
					dropLists[2] = drops;
					break;
				case 12:
					dropLists[0] = drops;
					break;
				case 26: 
					dropLists[1] = drops;
					break;
			}
		case 'l': 
			dropLists[3] = drops;
			break;
		}
		
	}
	public static InventoryItems[] dropLocations(int mapNumber, char objChar) {
		switch (objChar) {
			case 'd':
				switch(mapNumber) {
					case 7:
						return dropLists[2];
					case 12:
						return dropLists[0];
					case 26: 
						return dropLists[1];
				}
				break;
		}
		return null;
	}
	
	public static void tripFlags(int mapNumber, char objChar) {
		switch (objChar) {
		case 'c':
			switch (mapNumber) {
			case 12:
				MapBuilder.c12Flag = true;
				break;
			case 26:
				MapBuilder.c26Flag = true;
				break;
			case 7: 
				MapBuilder.c7Flag = true;
			}
		case 'l':
			switch(mapNumber) {
			case 11:
				MapBuilder.l11Flag = true;
			}
				
		case 'g':
		}
	}
	
	public static String interactionType(char objChar) {
		switch (objChar) {
			case 'r': 
				return "cook on the range";
		}
		return null;
	}
	public static void objectInteractionMethod(char objChar, Player p) {
		switch (objChar) {
		case 'r':
			AccessoryMethods.rangeCook();
		}
	}
}

