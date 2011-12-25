package codegears.coca.data;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class Item {
	
	private String EXCHANGE_ITEM = "exchange_item";
	
	private String id;
	private String name;
	private int price;
	private String itemType;
	
	private ArrayList<ItemExchangeItem> exchangeItem;
	
	public Item(){
		exchangeItem = new ArrayList<ItemExchangeItem>();
	}
	
	public void setDataFromXmlNode(Node node){
		id = node.getAttributes().getNamedItem("id").getNodeValue();
		name = node.getAttributes().getNamedItem("name").getNodeValue();
		price = Integer.parseInt(node.getAttributes().getNamedItem("buying_price").getNodeValue());
		itemType = node.getAttributes().getNamedItem("item_type").getNodeValue();

		for(int i = 0; i < node.getChildNodes().getLength(); i++){
			Node getExchangeNode = node.getChildNodes().item(i);
			
			if(getExchangeNode.getNodeName().equals(EXCHANGE_ITEM)){
				ItemExchangeItem newExchangeItem = new ItemExchangeItem();
				newExchangeItem.setDataFromNode(getExchangeNode.getAttributes().getNamedItem("id").getNodeValue(), Integer.parseInt(getExchangeNode.getAttributes().getNamedItem("quantity").getNodeValue()));
				exchangeItem.add(newExchangeItem);
			}
		}
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getItemType(){
		return this.itemType;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public ArrayList<ItemExchangeItem> getExchangeItem(){
		return this.exchangeItem;
	}

	public int getSellPrice() {
		return (this.price/2);
	}
}
