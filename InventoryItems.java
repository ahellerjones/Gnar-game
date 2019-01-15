
public class InventoryItems {
	//Marcy's cat is inventory item number 13.
	public String itemName;
	public boolean isEquipable;
	Player player;
	public int itemNumber;
	
	public InventoryItems(int mapNumber, String itemName, Player player) {
		this.itemName = itemName;
		this.player = player;
		this.itemNumber = mapNumber;
	}
	
}
