import java.util.Scanner;
public class Driver {
	
	public static void main(String[] args) {
		Player p = new Player(null, 3, 2);
		Interactions startgame = new Interactions('p', p);
		startgame.startGame();
				
		Map map = new Map(12, p); // this is the center map, the starting map.
		boolean hasHeWonYet = false;
		while (!hasHeWonYet) {
			p.action();
			
		}
		
	
	}
}


