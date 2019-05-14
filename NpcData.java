
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
			
			//__Lizard lady__\\
			String[] ladyDialogue26 = {
					"\"Hello there brave adventurer! Have you seen those Evil Lizard Dudes that are"
					+ " just outside of town?", "They're super powerful so I would watch out if I "
							+ "were you, \nbut if you kill them I'm sure you could make use of their powerful"
							+ "nswords\""};
			
			talkerDialogue[26] = ladyDialogue26;
			talkerDialogue[27] = marcyDialogue27;
			
			//___Marcy's Cat's Dialogue___\\
			//String[] haventStartedQuest =  {"\" Go away\"\n", "Holy shit a talking cat..."};
							
		
		
		//***___Trader Dialogue___***\\
		String[][]traderDialogue = new String[40][];
			
			//___Joe McShm'o's Dialogue___\\
			String[] joeDialogue27 = {
					"\"Sup man, names Joe McShm'o.\n"
					+ "Listen, I got some good shit to trade for hides if you're lookin to barter.\n\""
					+ "Here's a what's I gots: "
			};
			String[] weedDealer13 = {"\"Yo, wanna buy some Gilliweed?\"",
					"Ok... how much?", "\"2 Hides, take a look at the rest of my stuff\""
					};
			traderDialogue[13] = weedDealer13;
			traderDialogue[27] = joeDialogue27;
			
			
		//***___Alt Dialogues___***\\
			String[][] miscDialogue = new String[40][];
				//Mistah Cook, map 28
				String[] cook28Dialogue = {"Welcome to my mess hall, I got this nice ole range"
						+ " here that you can cook your raw food on if ya like.\n Only thing is that the"
						+ " damn thing ran out of fuel, and unfortunately I haven't been programmed\n"
						+ " to be able to move. \nIf you could go out and find me like a gram... \nmake it 2 grams of Gilliweed"
						+ " to light up the range I'll let you cook any food on it ya like"
						+ "\n One of my pals usually hangs out right outside of town, you might be able to buy some off of him."};
					miscDialogue[28] = cook28Dialogue;
		
		switch (npcChar) {
		case 'p':
			return talkerDialogue[mapNumber];
		case 't':
			return traderDialogue[mapNumber];
		case 'œ':
			return miscDialogue[mapNumber];
			
		}
		String[] stub = {""};
		return stub;
	}
	
	public static String[] questData(int mapNumber) { 
		String[][] questLogs = new String[40][];
		String[] mainQuest = new String[5];
		mainQuest[0] = "The Gods of Gnar have contacted me and I have to retieve their items for them\n"
				+ "They need me to: 1. Retrieve the Brown Claw, 2. Drink from the river of Jimothy, and\n"
				+ "3. Eat the heart out of the Boater of the Club... What a weird day I'm having.";
		questLogs[1] = mainQuest;
		//___Marcy's quest___\\
		String[] marcyQuest = new String[5];
		marcyQuest[0] = "I need to find Marcy's cat... Maybe I'll go look in the surrounding areas";
		marcyQuest[1] = "I found Marcy's cat. Just need to bring it back to her now.";
		marcyQuest[2] = "I'm taking the cat back to Marcy for my reward.";
		marcyQuest[3] = "I took the cat back to Marcy and got my reward of 7 Hides.\t\t QUEST COMPLETE";
		marcyQuest[4] = "I let the cat be and now get 2 Hides instead of 1 when I kill cows.\t\t QUEST COMPLETE";
			
		questLogs[27] = marcyQuest;
		String[] cookQuest = new String[4];
		cookQuest[0] = "I need to find some Gilliweed to use the cook's range. I've got 0/2 grams.\n "
				+ "Who cooks by burning Gilliweed?";
		cookQuest[1] = "I need to find some Gilliweed to use the cook's range. I've got 1/2 grams.\n ";
		cookQuest[2] = "I've got all his weed, just need to take it back... I might have more important things to do though...";
		cookQuest[3] = "I took the Gilliweed back to the cook, now I can finally stick my raw meat in his range  QUEST COMPLETE";
		questLogs[28] = cookQuest;
		return questLogs[mapNumber];
	
		
	}
	
	
	
	

}
