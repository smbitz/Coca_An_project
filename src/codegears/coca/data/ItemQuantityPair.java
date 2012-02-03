package codegears.coca.data;

import org.w3c.dom.Node;

import android.R.string;

public class ItemQuantityPair {
	private String id;
	private int quantity;
	
	private Item item;
	
	public void setDataFromXmlNode(Node node){
		id = node.getAttributes().getNamedItem("id").getNodeValue();
		quantity = Integer.parseInt(node.getAttributes().getNamedItem("quantity").getNodeValue());
	}
	
	public Item getItem(){
		return this.item;
	}
	
	public String getId(){
		return this.id;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public String getItemType(){
		return this.item.getItemType();
	}
	
	public void setItem(Item setValue){
		this.item = setValue;
	}
	
	public void setItemQuantity(int setValue){
		this.quantity = setValue;
	}
	
	public void setId(String setValue){
		this.id = setValue;
	}
	
}
