package codegears.coca.data;

public class BuildingYieldItem {
	
	private String id;
	private int quantity;
	private int chance;
	private int randomTime;
	
	private Item item;
	
	public void setDataFromNode(String setId, int setQuantity, int setChance, int setRandomTime){
		id = setId;
		quantity = setQuantity;
		chance = setChance;
		randomTime = setRandomTime;
	}
	
	public String getId(){
		return id;
	}
	
	public void setItem(Item setItem){
		this.item = setItem;
	}
	
}
