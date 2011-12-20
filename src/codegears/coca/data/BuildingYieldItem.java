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
		return this.id;
	}
	
	public int getRandomTime(){
		return this.randomTime;
	}
	
	public int getChance(){
		return this.chance;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public Item getItem(){
		return this.item;
	}
	
	public void setItem(Item setItem){
		this.item = setItem;
	}
	
}
