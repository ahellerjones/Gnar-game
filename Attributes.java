
public class Attributes {
	// public InventoryItems associatedItem;
	public int attack = 	0;
	public int strength = 	0;
	public int defence = 	0;
	public int hpModifier = 0;
	public int hpHeal = 	0;
	public int equipSlot = 	0;
	public int quantitySold = 0;
	public boolean isEquipable = false;
	public int[] attributesArray = new int[6];
	public String[] attributesString = {"Attack", "Strength", "Defence", "hpModifier",
			"hpHeal"};
	public static Attributes t_l = 		new Attributes(0, 0, 0, 0, 3, -1, 2, false);
	public static Attributes rawBeef = 	new Attributes(0, 0, 0, 0, 1, -2, 1, false);
	public static Attributes beef = 	new Attributes(0, 0, 0, 0, 5, -1, 1, false);
	public Attributes(int attack, int strength, int defence,
			int hpModifier, int hpHeal, int equipSlot, int quantitySold, boolean isEquip) {
		this.attack = 		attack;
		this.strength = 	strength;
		this.defence = 		defence;
		this.hpModifier = 	hpModifier;
		this.hpHeal = 		hpHeal;
		this.equipSlot = 	equipSlot;
		this.quantitySold = quantitySold;
		this.isEquipable = 	isEquip;
		this.attributesArray = assignArray(this);
		
	}

	public static void printAttributes(Attributes a) {
		if(a == null) {
			System.out.println("This item has no attributes");
			return;
		}
		if(!(a.attack == 0)) {
			System.out.println("Attack Bonus: " + a.attack);
		}
		if (!(a.strength == 0)) { 
			System.out.println("Strength Bonus: " + a.strength);
		}
		if(!(a.defence == 0)) {
			System.out.println("Defence Bonus: " + a.defence);
		}
		if (!(a.hpModifier == 0)) { 
			System.out.println("Hitpoints Boost: " + a.hpModifier);
		}
		if (!(a.hpHeal == 0)) { 
			System.out.println("Heals: " + a.hpHeal);
		}
		if (!(a.equipSlot == 0)) { 
			System.out.println("Eqipment Slot: " + InventoryItems.equipSlot(a.equipSlot));
		}
	}
	public static String[] modifierStrings(InventoryItems[] equipment) {
		String[] equipmentString = new String[equipment.length];
		for (int i = 0; i < equipment.length; i++) {
			if(!(equipment[i] == null)) {
				equipmentString[i] = "";
				if(!(equipment[i].attributes.attack == 0)) {
					equipmentString[i] += ("\nAttack Bonus: " + equipment[i].attributes.attack);
				}
				if (!(equipment[i].attributes.strength == 0)) { 
					equipmentString[i] += ("\nStrength Bonus: " + equipment[i].attributes.strength);
				}
				if(!(equipment[i].attributes.defence == 0)) {
					equipmentString[i] += ("\nDefence Bonus: " + equipment[i].attributes.defence);
				}
				if (!(equipment[i].attributes.hpModifier == 0)) { 
					equipmentString[i] += ("\nHitpoints Boost: " + equipment[i].attributes.hpModifier);
				}
				if (!(equipment[i].attributes.hpHeal == 0)) { 
					equipmentString[i] += ("\nHeals: " + equipment[i].attributes.hpHeal);
				
				}
			}
			
		}
		return equipmentString;														
		
		
	}
	public int[] assignArray(Attributes array) {
		int[] returnArray = {array.attack, array.strength, array.hpModifier, array.hpHeal,
				array.equipSlot, array.quantitySold};
		return returnArray;
	}
	
}
