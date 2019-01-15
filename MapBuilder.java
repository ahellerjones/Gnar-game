
public class MapBuilder {
	
	
	public static Objects[] getObjects(int mapNumber, Player p) { 
		Objects[][] objects = new Objects[35][]; //increase this as needed to input more town / cave numbers
		//Objects go as: new Objects('object character', int row, int column, int map, p);
		
		//Map 0 -- needed to throw null pointers using motherMap
		Objects stub = new Objects('T', 2, 0, 0, p);
		Objects[] ob0 = new Objects[1];
		ob0[0] = stub;
		objects[0] = ob0;
		
		
		// Map 7 -- tester map
		Objects T7 = new Objects('T', 2, 0, 7, p);
		Objects[] ob7 = new Objects[1];
		ob7[0] = T7;
		objects[7]= ob7;
		
		
		// Map 12 -- Starting map. Contains town of Gnarivores
		Objects T1 = new Objects('T', 2, 0, 12, p); //gnarivores
		Objects T2 = new Objects('T', 2, 1, 12, p); //gnarivores
		Objects T3 = new Objects('T', 3, 0, 12, p); //gnarivores
		Objects E1 = new Objects('¬', 4, 4, 12, p);
		Objects[] ob12 = new Objects[4];
		
		ob12[0] = T1;
		ob12[1] = T2;
		ob12[2] = T3;
		ob12[3] = E1;
		objects[12] = ob12;
				//Map 26 -- map 12's Town
				Objects Ω1 = new Objects('Ω', 2, 0, 26, p); 
				Objects Ω2 = new Objects('Ω', 1, 0, 26, p);
				Objects[] ob26 = new Objects[2];
				ob26[0] = Ω1;
				ob26[1] = Ω2;
				objects[26] = ob26;
					//Map 27 -- map 26's tavern
					Objects p1 = new Objects('p', 2, 1, 27, p);
					Objects p2 = new Objects('t', 4, 0, 27, p);
					Objects w1 = new Objects('_', 1, 0, 27, p);
					Objects w2 = new Objects('_', 1, 1, 27, p);
					Objects w3 = new Objects('_', 1, 2, 27, p);
					Objects w4 = new Objects('_', 1, 3, 27, p);
					Objects w5 = new Objects('|', 2, 4, 27, p);
					Objects w6 = new Objects('|', 3, 4, 27, p);
					Objects w7 = new Objects('|', 4, 4, 27, p);
					Objects s1 = new Objects(' ', 0, 0, 27, p);
					Objects s2 = new Objects(' ', 0, 1, 27, p);
					Objects s3 = new Objects(' ', 0, 2, 27, p);
					Objects s4 = new Objects(' ', 0, 3, 27, p);
					Objects s5 = new Objects(' ', 0, 4, 27, p);
					Objects s6 = new Objects(' ', 1, 4, 27, p);
					
					
					Objects[] ob27 = {p1, p2, w1, w2, w3, w4, w5,
							w6, w7, s1, s2, s3, s4, s5, s6};
					objects[27] = ob27;
		
					Objects c1 = new Objects('ø', 3, 3, 13, p);
		Objects[] ob13 = {c1};
		objects[13] = ob13;
		
		
		return objects[mapNumber];
	}
	
	public static char[][] buildMap() {
		char[][] coords = new char[5][5];
		for (int i = 0; i < 5; i++) { 	
			for (int j = 0; j < 5; j++) { 
				coords[i][j] = '.';	
			}
		}
		return coords;
	}
}
