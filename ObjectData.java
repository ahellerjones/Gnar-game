
public class ObjectData {
	
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
		}
		return ""; // stub 
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
}
