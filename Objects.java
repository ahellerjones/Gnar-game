import java.util.Scanner;

public class Objects {
	Scanner 		in = new Scanner(System.in);
	public String 	objectName;
	public int 		mapNumber;
	public char 	objChar;
	public int[] 	objectLocation = new int[2]; 
	public Player 	player;		
	public int[] 	objArray;		
	public Objects(char objectType,
			int a, int b, int mapNumber, Player p) { 
		
		objectLocation[0] =	a;
		objectLocation[1] = b;
		this.objChar = 		objectType;
		this.mapNumber = 	mapNumber;
		this.objectName = 	ObjectData.findName(this.mapNumber, this.objChar);
		this.player = 		p;
		if(ObjectData.npcArray(mapNumber, this.objChar) != null) {
			objArray = ObjectData.npcArray(mapNumber, this.objChar);
		}
	}
	public void Interactions(char objChar) { // works
		switch(objChar) { 
			case 'T':
				buildingInteractions();
				break;
			case 'Ω':
				buildingInteractions();
				break;
			case 'p':
				npcInteractions();
				Quests.nonStaticInteractions(this.mapNumber, this.objChar, this.player);
				break;
			case 't':
				npcInteractions();
				break;
			case 'ø':
				nonStaticNpcInteractions();
				break;
			case 'c':
				optionalCombat();
				break;
			case 'd':
				openLoot();
				break;
		}
	}
	public void buildingInteractions() {
			System.out.printf("Would you like to enter  %s?\n"
					+ "Enter y/n\n", this.objectName);
			String input = in.nextLine();
			if(input.equals("y")) { 
				Interactions enterBuilding = new Interactions(this.objChar, player);
				enterBuilding.enterBuilding(ObjectData.buildingNumbers(this.mapNumber, this.objChar));
			}
		}
	public void npcInteractions() {
		System.out.printf("Would you like to talk to %s?\n"
				+ "Enter y/n\n", this.objectName);
		String input = in.nextLine();
		
		if(input.equals("y")) { 
//			for (int i = 0; i < ObjectData.npcDialogue(this.mapNumber, this.objChar).length; i++) {
//				System.out.println(ObjectData.npcDialogue(this.mapNumber, this.objChar)[i]);
//				System.out.println("\nPress enter to continue");
//				in.nextLine();
//			}
			
			for (int i = 0; i < Quests.nonStaticDialogue(this.objChar, this.player).length; i++) {
				System.out.println(Quests.nonStaticDialogue(this.objChar, this.player)[i]);
				System.out.println("\nPress enter to continue");
				in.nextLine();
			}
		
			if (ObjectData.hasQuest(this.mapNumber, this.objChar) && !player.pHasQuest(this.mapNumber)) {
				System.out.printf("Help out %s? \n Enter y/n\n", this.objectName);
				if(in.nextLine().equals("y")) { 
					Quests newQuest = new Quests(this.mapNumber);
					System.out.println(this.mapNumber);
					player.addQuest(newQuest);
			}
		}
			if (ObjectData.wantsToTrade(this.mapNumber, this.objChar)) {
				ObjectData.printInv(ObjectData.traderInventories(this.mapNumber, this.player), player);
			}
	}
			

	}
	
	public void nonStaticNpcInteractions() { 
		for (int i = 0; i < Quests.nonStaticDialogue(objChar, player).length; i++) {
			System.out.println(Quests.nonStaticDialogue(objChar, player)[i]);
			System.out.println("\nPress enter to continue");
			in.nextLine();
		}
		Quests.nonStaticInteractions(mapNumber, objChar, this.player);
	}
	
	public void optionalCombat() {
		System.out.printf("Attack the %s (level: %d)?\n", 
				this.objectName, ObjectData.npcArray(
						this.mapNumber, this.objChar)[3]);
		if (in.nextLine().equals("y")) {
			combat();
		}
	}
	
	public void combat() {
		boolean continueFight = true;
		while(continueFight) {
			int[] hits = AccessoryMethods.calculateHits(player, ObjectData.npcArray(
					this.mapNumber, this.objChar));
			player.health -= hits[1];
			this.objArray[3] -= hits[0];
			System.out.printf("\nYou hit the %s for %d hitpoints!\n", this.objectName, hits[0]);
			System.out.printf("%s hit you for %d hitpoints!\n", this.objectName, hits[1]);
			if(player.health <= 0) {
				AccessoryMethods.die(player); //need to work on this shit!!!
			}
			if(this.objArray[3] <= 0) {
				System.out.printf("\nYou killed the %s\n\n", this.objectName);
				System.out.println("Here are the drops:");
				InventoryItems[] drops = new InventoryItems[5];
				AccessoryMethods.viewDrops(ObjectData.npcDrops(
						mapNumber, objChar, player), player, this.objChar);
				ObjectData.tripFlags(mapNumber, objChar);
				player.changeMap(mapNumber);
				continueFight = false;
			} else {
				System.out.printf("Your HP: %d \t Enemy HP: %d\n", player.health, this.objArray[3]);
				System.out.println("What would you like to do? Enter choice below");
				System.out.println("Hit (hit),\tRun (run), \tChange equipment and / or heal (equip)");
				switch(in.nextLine()) {
				case "hit":
					break;
				case "run":
					int[] runawayHits = 
						AccessoryMethods.calculateHits(player, ObjectData.npcArray(
							this.mapNumber, this.objChar));
					player.health -= runawayHits[1];
					System.out.printf("%s hit you for %d hitpoints as you run away!\n", 
							this.objectName, runawayHits[1]);
					if(player.health <= 0) {
						AccessoryMethods.die(player);
						break;
					} else {
						continueFight = false;
						break;
					}
				case "equip":
					for (int i = 0; i < Player.inventoryIndex; i++) {
						System.out.printf(
							"%d. %dx %s\n", i + 1, player.inventory[i].quantity, player.inventory[i].itemName);
					}
					System.out.println("Enter the index of the item you wish to equp.\n"
							+ "If you have no items then just press enter.");
					String scannerInput = in.nextLine();
					if(scannerInput.equals("")) {
						break;
					}
					int inventoryNumber = Integer.parseInt(scannerInput) - 1;
					if(player.inventory[inventoryNumber] == null) {
						System.out.println("Ain't got an item in that inventory spot boy");
						break;
					}
					player.equip(player.inventory[inventoryNumber]);
					AccessoryMethods.addAttributes(player.equipment, player);
					break;
				}
					
			}
			
		}
	}
	
	public void openLoot() {
		
		AccessoryMethods.viewDrops(
				ObjectData.dropLocations(this.mapNumber, this.objChar), this.player, this.objChar);
	
	}
	
}
