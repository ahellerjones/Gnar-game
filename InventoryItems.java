/*
 * -4 Raw cow meat
 * -3 Turkey Leg
 * -2 Plastic Blade
 * -1 Hides
 * 
 */

/* 
 * equip slots --
 * -1 hpHeal food
 */




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
	public InventoryItems(int mapNumber, String itemName, Player player, int price, Attributes itemAttributes,
			int quantity) {
		this.itemName = itemName;
		this.player = player;
		this.itemNumber = mapNumber;
		this.quantity = quantity;
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
	
	public void consume() {
		switch(this.attributes.equipSlot) {
		case -1:
			player.health += this.attributes.hpHeal;
			int excessHeal = 0;
			if (player.health > (player.modifiers[3] + 10)) {
				excessHeal = player.health - player.modifiers[3];
				player.health = player.modifiers[3];
			}
			System.out.printf("%s healed for %d Hitpoints\n", 
					this.itemName, this.attributes.hpHeal - excessHeal);
			player.removeItem(this, 1);
		
		}
	}
	
}
