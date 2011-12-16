package codegears.coca.data;

public class BuildingExtra {
	
	private String id;
	private int result;
	
	private Item item;
	
	public void setDataFromNode(String setId,int setResult){
		this.id = setId;
		this.result = setResult;
	}
	
	public String getId(){
		return id;
	}
	
	public void setItem(Item setItem){
		this.item = setItem;
	}
	
}
