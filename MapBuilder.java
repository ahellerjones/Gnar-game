public class MapBuilder {
	public static boolean g28Flag = false; //If they've bought the gilliweed
	public static boolean cowFlagList[] = new boolean[50];
	public static boolean lizardFlagList[] = new boolean[50];
	public static boolean c7Flag;
	public static boolean c12Flag;
	public static boolean c26Flag;
	public static boolean l11Flag;
	static Objects[][] objects = new Objects[35][];
	
	public static Objects[] getObjects(int mapNumber, Player p) { 
		cowFlagList[7] = 	c7Flag;
		cowFlagList[12] = c12Flag;
		cowFlagList[26] = c26Flag;
		lizardFlagList[11] = l11Flag;
		 //increase this as needed to input more town / cave numbers
		//Objects go as: new Objects('object character', int row, int column, int map, p);
		
		//Map 0 -- needed to throw null pointers using motherMap
		Objects stub = new Objects('T', 2, 0, 0, p);
		Objects[] ob0 = new Objects[1];
		ob0[0] = stub;
		objects[0] = ob0;
		
		
		// Map 7 -- tester map
		Objects T7 = new Objects('T', 2, 0, 7, p);
		Objects C7 = changeableObjects('c', 2, 4, 7, p);
		Objects[] ob7 = new Objects[2];
		ob7[0] = T7;
		ob7[1] = C7;
		objects[7]= ob7;
		
		// Map 11 -- evil Lizard dudes  
		Objects l11 = new Objects('l', 1, 1, 11, p);
		Objects[] ob11 = new Objects[1];
		ob11[0] = l11;
		objects[11] = ob11;
		
		// Map 12 -- Starting map. Contains town of Gnarivores
		Objects T1 = new Objects('T', 2, 0, 12, p); //gnarivores
		Objects T2 = new Objects('T', 2, 1, 12, p); //gnarivores
		Objects T3 = new Objects('T', 3, 0, 12, p); //gnarivores
		Objects C1 = changeableObjects('c', 4, 4, 12, p);
//		if (MapBuilder.c12Flag) { 
//			C1 = new Objects('d', 4, 4, 12, p);
//		} else {
//			C1 = new Objects('c', 4, 4, 12, p);
//		}
		Objects[] ob12 = new Objects[4];
		
		ob12[0] = T1;
		ob12[1] = T2;
		ob12[2] = T3;
		ob12[3] = C1;
		objects[12] = ob12;
				//Map 26 -- map 12's Town
				Objects p26 = new Objects('p', 4, 4, 26, p);
				Objects o1 = new Objects('o', 2, 0, 26, p); 
				Objects o2 = new Objects('o', 1, 0, 26, p);
				Objects C4 = new Objects('Â', 2, 4, 26, p);
				Objects c26 = changeableObjects('c', 2, 2, 26, p); 
//				if (MapBuilder.c26Flag) { 
//					c26 = new Objects('d', 2, 2, 26, p);
//				} else {
//					c26 = new Objects('c', 2, 2, 26, p);
//				}
				
				
				
				Objects[] ob26 = new Objects[5];
				ob26[0] = o1;
				ob26[1] = o2;
				ob26[2] = C4;
				ob26[3] = c26;
				ob26[4] = p26;
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
					Objects r2 = new Objects('r', 2, 0, 28, p);
					Objects p3 = new Objects('Å', 2, 3, 28, p);
					Objects W1 = new Objects('_', 1, 0, 28, p);
					Objects W2 = new Objects('_', 1, 1, 28, p);
					Objects W3 = new Objects('_', 1, 2, 28, p);
					Objects W4 = new Objects('_', 1, 3, 28, p);
					Objects W5 = new Objects('|', 2, 4, 28, p);
					Objects W6 = new Objects('|', 3, 4, 28, p);
					Objects W7 = new Objects('|', 4, 4, 28, p);
					Objects S1 = new Objects(' ', 0, 0, 28, p);
					Objects S2 = new Objects(' ', 0, 1, 28, p);
					Objects S3 = new Objects(' ', 0, 2, 28, p);
					Objects S4 = new Objects(' ', 0, 3, 28, p);
					Objects S5 = new Objects(' ', 0, 4, 28, p);
					Objects S6 = new Objects(' ', 1, 4, 28, p);
		
					Objects[] ob28 = {r2, p3, W1, W2, W3, W4, W5,
							W6, W7, S1, S2, S3, S4, S5, S6};
					objects[28] = ob28;
					
		Objects c1 = new Objects('Ã', 3, 3, 13, p);
		Objects p13 = new Objects('t',0, 4, 13, p);
		Objects[] ob13 = {c1, p13};
		objects[13] = ob13;
		
		Objects g8 = new Objects('g', 3, 3, 8, p);
		Objects[] ob8 = {g8};
		objects[8] = ob8;
		
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
	public static Objects changeableObjects(
			char objChar, int column, int row, int mapNumber, Player p) {
		switch (objChar) {
			case'c':
				if(!cowFlagList[mapNumber]) {
					Objects cow = new Objects('c', column, row, mapNumber, p);
					return cow;
				}
				break;
			case 'l':
				if(!lizardFlagList[mapNumber]) {
					Objects lizardMan = new Objects('l', column, row, mapNumber, p);
					return lizardMan;
				} 
				break;
		}
		return new Objects('d', column, row, mapNumber, p);
				
	}
}
