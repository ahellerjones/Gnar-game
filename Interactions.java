
public class Interactions {
	public char objectCharacter;
	public Player player;
	public String objectName;
	
	public Interactions(char objChar, Player p) {
		this.objectCharacter = objChar;
		this.player = p;
		
		//this.objectName = objN;
		
	}
	public void startGame() {
		player.changeMap(12);
	}
	
	public void enterBuilding(int mapNumber) {
		Map newMap = new Map(mapNumber, player); 
		this.player.changeMap(mapNumber);
		this.player.centerPlayer();
		//this.player.currentMap.printMap();
	}
	
	
	public void reLoadMap(int mapNumber) {  
		this.player.changeMap(mapNumber);
	
	}
	
}

