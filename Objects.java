import java.util.Scanner;

public class Objects {
	Scanner 		in = new Scanner(System.in);
	public String 	objectName;
	public int 		mapNumber;
	public char 	objChar;
	public int[] 	objectLocation = new int[2]; 
	public Player 	player;		
			
	public Objects(char objectType,
			int a, int b, int mapNumber, Player p) { 
		
		objectLocation[0] =	a;
		objectLocation[1] = b;
		this.objChar = 		objectType;
		this.mapNumber = 	mapNumber;
		this.objectName = 	ObjectData.findName(this.mapNumber, this.objChar);
		this.player = 		p;
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
	
}
