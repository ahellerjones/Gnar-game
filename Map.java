import java.util.Scanner;

public class Map {
	public char[][] coords = new char[5][5];
 	public int mapNumber;
	public Objects[] objects;
	public Player player;
	public Map motherMap;
	
	public Map(int mapNumberSpecifier, Player p) {
		this.coords = MapBuilder.buildMap();
		this.mapNumber = mapNumberSpecifier;
		this.objects = MapBuilder.getObjects(mapNumberSpecifier, p);
		for(int i = 0; i < this.objects.length; i++) { 
			this.coords[this.objects[i].objectLocation[0]][this.objects[i].objectLocation[1]] 
					= this.objects[i].objChar; 
		}
		this.player = p;
		// this.motherMap = Map(motherMap(mapNumberSpecifier), p);
	
	}	
	// Checks to see if an object is adjacent
	
	/*
	 * 0 up
	 * 1 down
	 * 2 left
	 * 3 right
	 */
	
	
	public boolean[] isAdjacent() { 
		boolean[] isAdjacent = new boolean[this.objects.length]; // Set it as four to account for all possible movements
		for(int i = 0; i < this.objects.length; i++) { 
			// Check Up
			if ((this.objects[i].objectLocation[0] == Player.loc[0] - 1)
				&& (this.objects[i].objectLocation[1] == Player.loc[1])) {
				isAdjacent[i] = true;
			}
			// Check Down
			if ((this.objects[i].objectLocation[0] == Player.loc[0] + 1)
				&& (this.objects[i].objectLocation[1] == Player.loc[1] )) {
				isAdjacent[i] = true;
			}
			// Check Right
			if ((this.objects[i].objectLocation[1] == Player.loc[1] + 1)
				&& (this.objects[i].objectLocation[0] == Player.loc[0] )) {
				isAdjacent[i] = true;
			}
			//Check Left
			if ((this.objects[i].objectLocation[1] == Player.loc[1] - 1)
				&& (this.objects[i].objectLocation[0] == Player.loc[0])) {
				isAdjacent[i] = true;
			}
		}
			return isAdjacent;
	}
	public boolean isAdjacent(int movNum) { 
		boolean[] isAdjacent = new boolean[4]; // Set it as four to account for all possible movements
		for(int i = 0; i < this.objects.length; i++) { 
			// Check Up
			if ((this.objects[i].objectLocation[0] == Player.loc[0] - 1)
				&& (this.objects[i].objectLocation[1] == Player.loc[1])) {
				isAdjacent[0] = true;;
			}
			// Check Down
			if ((this.objects[i].objectLocation[0] == Player.loc[0] + 1)
				&& (this.objects[i].objectLocation[1] == Player.loc[1] )) {
				isAdjacent[1] = true;
			}
			// Check Right
			if ((this.objects[i].objectLocation[1] == Player.loc[1] + 1)
				&& (this.objects[i].objectLocation[0] == Player.loc[0] )) {
				isAdjacent[3] = true;
			}
			//Check Left
			if ((this.objects[i].objectLocation[1] == Player.loc[1] - 1)
				&& (this.objects[i].objectLocation[0] == Player.loc[0])) {
				isAdjacent[2] = true;
			}
		}
			return isAdjacent[movNum];
	}
	
	
	public void printMap() {
		this.coords[Player.loc[0]][Player.loc[1]] = 'X';
		for (int i = 0; i < 5; i++) {
			System.out.print("[");
			for (int j = 0; j < 5; j++) { 
				System.out.print(" " + this.coords[i][j]);
			}
			System.out.print(" ]\n");
		}
	}
	// Returns which map came before this map.
	public static int motherMap(int mapNumber) {
		int[] motherMaps = new int[40];
		motherMaps[0] = 	0;
		motherMaps[26] = 	12;
		motherMaps[27] = 	26;
		motherMaps[28] = 	26;
		
		return motherMaps[mapNumber];
	}
	
	public char isOverRiding() {
		for (int i = 0; i < this.objects.length; i++ ) {
			if(Player.loc[0] == objects[i].objectLocation[0] &&
				Player.loc[1] == objects[i].objectLocation[1]) {
				return objects[i].objChar;	
			}
		}
		
		return '`';
	}
	
	
}

		
	
