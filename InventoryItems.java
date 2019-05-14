/*
 * -6 Simple helmet (T1);
 * -5 Beef
 * -4 Raw cow meat
 * -3 Turkey Leg
 * -2 Plastic Blade
 * -1 Hides
 * 
 */


/* General equip slots -- 
 * 1. Head
 * 2. Body
 * 3. Necklace
 * 4. Weapon
 */

/* 
 * weird equip slots --
 * -1 hpHeal raw food to be cooked
 * -2 hpHeal cooked food that can be eaten for more hp
 */


public class InventoryItems {
	//Marcy's cat is inventory item number 13.
	public String itemName;
	public boolean isEquipable;
	static Player player;
	public int itemNumber;
	public int quantity;
	public int price;
	public Attributes attributes;
	
	public static InventoryItems Turkey_Leg = 
			new InventoryItems(-3, "Turkey Leg", player, 3, Attributes.t_l);
	public static InventoryItems Beef = 
			new InventoryItems(-4, "Beef", player, 3, Attributes.beef);
	public static InventoryItems Hides = 
				new InventoryItems(-1, "Hides", player, 0, null, 4);
	public static InventoryItems Raw_Beef = 
				new InventoryItems(-5, "Raw beef", player, 0, Attributes.rawBeef, 2);
	
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
		case -2:
			player.health += this.attributes.hpHeal;
			int excessHeal1 = 0;
			if (player.health > (player.modifiers[3] + 10)) {
				excessHeal1 = player.health - (player.modifiers[3] + 10);
				player.health = player.modifiers[3] + 10;
			}
			System.out.printf("%s healed for %d Hitpoints\n", 
					this.itemName, this.attributes.hpHeal - excessHeal1);
			player.removeItem(this, 1);
			break;
		
		}
	}
	
}
