
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
			System.out.println("Defence Bonus: " + a.attack);
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
	public int[] assignArray(Attributes array) {
		int[] returnArray = {array.attack, array.strength, array.hpModifier, array.hpHeal,
				array.equipSlot, array.quantitySold};
		return returnArray;
	}
	
}
