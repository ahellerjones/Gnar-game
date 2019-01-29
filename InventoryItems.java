
public class InventoryItems {
	//Marcy's cat is inventory item number 13.
	public String itemName;
	public boolean isEquipable;
	Player player;
	public int itemNumber;
	public int quantity;
	public int price;
	public Attributes attributes;
	// public static final InventoryItems HIDES = new InventoryItems(-1, "Hides", player);
	public InventoryItems(int mapNumber, String itemName, Player player) {
		this.itemName = itemName;
		this.player = player;
		this.itemNumber = mapNumber;
		quantity = 1;
		price = 0;
	}
	public InventoryItems(int mapNumber, String itemName, Player player, int price, Attributes itemAttributes) {
		this.itemName = itemName;
		this.player = player;
		this.itemNumber = mapNumber;
		quantity = 1;
		this.price = price;
		this.attributes = itemAttributes;
	}


	public static String[] invtoString(InventoryItems[] inv) {
		String[] returnString = new String[inv.length];
		for(int i = 0; i < inv.length; i++) {
			returnString[i] = inv[i].itemName;
		}
		return returnString;
	}
	
//	public static String[] itemAttributes(InventoryItems item) {
//		switch(item.itemNumber) {
//			case(-2): //Plastic_Blade
//				String[] attributes = {"5 Attack", "5 Strength"};
//				return attributes;
//				
//		}
//		
//		return null; // string
//	}
	
	public static String equipSlot(int index) {
		String[] equipStrings = {"", "Head", "Body", "Necklace", "Weapon"};
		return equipStrings[index];
	}
	
	
}
