
public class NpcData {
	/** Stores the dialogue data and returns based on the given map and npcCharacter
	 *  Only problem is that you can only have one type of npc per map.
	 *  
	 * @param mapNumber 
	 * @param npcChar
	 * @return dialogueString
	 */
	public static boolean isNonStatic(char objChar) {
		char[] nonStaticChar = new char[10];
		nonStaticChar[0] = 'ø';
		for(int i = 0; i < 10; i++) { 
			if(objChar == nonStaticChar[i]) {
				return true;
			}
		}
		return false; 
		}
	
	public static String[] dialogueStorage(int mapNumber, char npcChar) { 
		//***___Talker Dialogue___***\\
		String[][] talkerDialogue = new String[40][];
		
			//__Marcy's Dialogue___\\ 
			String[] marcyDialogue27 = {
					"\"Well hello there, don't see a new face in these parts all that often\"",
					"\"Come to think of it... nobody has really moved since I can last remember...\"",
					"\"Listen, think you could do me a favor? My cat ran away few days back and I could really use some help"
					+ " tracking her down. \n She's most likely ran out of town, but not too far \n"
					+ " I'll give you 7 hides as compensation. Think you could help?\""
			};
			
			
			talkerDialogue[27] = marcyDialogue27;
			
			//___Marcy's Cat's Dialogue___\\
			//String[] haventStartedQuest =  {"\" Go away\"\n", "Holy shit a talking cat..."};
							
		
		
		//***___Trader Dialogue___***\\
		String[][]traderDialogue = new String[40][];
			
			//___Joe McShm'o's Dialogue___\\
			String[] joeDialogue27 = {
					"\"Sup man, names Joe McShm'o.\n"
					+ " Listen, I got some good shit to trade for hides if you're lookin to barter.\""
			};
			traderDialogue[27] = joeDialogue27;
			
			
		//***___Alt Dialogues___***\\
		
		
		switch (npcChar) {
		case 'p':
			return talkerDialogue[mapNumber];
		case 't':
			return traderDialogue[mapNumber];
		
		case 'ø':
			//
			
		}
		String[] stub = {""};
		return stub;
	}
	
	public static String[] questData(int mapNumber) { 
		String[][] questLogs = new String[40][];
		
		//___Marcy's quest___\\
		String[] marcyQuest = new String[5];
		marcyQuest[0] = "I need to find Marcy's cat... Maybe I'll go look in the surrounding areas";
		marcyQuest[1] = "I found Marcy's cat. Just need to bring it back to her now.";
		marcyQuest[2] = "I'm taking the cat back to Marcy for my reward.";
		marcyQuest[3] = "I took the cat back to Marcy and got my reward of 7 Hides.\t\t QUEST COMPLETE";
		marcyQuest[4] = "I let the cat be and now get 2 Hides instead of 1 when I kill cows.\t\t QUEST COMPLETE";
			
		questLogs[27] = marcyQuest;
		
		return questLogs[mapNumber];
	
		
	}
	
//	public String[] nonStaticDialogue(int mapNumber, char npcChar, Player p) { 
//		
//	}
	
	

}
