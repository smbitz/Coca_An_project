package codegears.coca.data;

public class ItemExchangeItem {
	
	private String id;
	private int quantity;
	
	private Item item;
	
	public void setDataFromNode(String setId,int setQuantity){
		id = setId;
		quantity = setQuantity;
	}
	
}
