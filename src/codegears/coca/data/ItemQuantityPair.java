package codegears.coca.data;

import org.w3c.dom.Node;

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
	
	public void setItem(Item setValue){
		this.item = setValue;
	}
	
}
