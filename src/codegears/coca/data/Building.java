package codegears.coca.data;

import java.util.ArrayList;

import org.w3c.dom.Node;

import android.util.Xml;

public class Building {
	
	public static final String EXTRA_BUILDING = "extra";
	public static final String YIELD_BUILDING = "yield_item";
	
	private String id;
	private String name;
	private String buildingType;
	private int buildPeriod;
	private String supplyId;
	private int supplyPeriod;
	private String buildItemId;
	private int rottenPeriod;

	private ArrayList<BuildingExtra> extra;
	private ArrayList<BuildingYieldItem> yieldItem;
	
	private Item buildItem;
	private Item supplyItem;
	
	public Building() {
		extra = new ArrayList<BuildingExtra>();
		yieldItem = new ArrayList<BuildingYieldItem>();
	}
	
	public void setDataFromXmlNode(Node node){
		id = node.getAttributes().getNamedItem("id").getNodeValue();
		name = node.getAttributes().getNamedItem("name").getNodeValue();
		buildingType = node.getAttributes().getNamedItem("building_type").getNodeValue();
		buildPeriod = ((Integer.parseInt(node.getAttributes().getNamedItem("build_period").getNodeValue())*60)*1000);
		supplyId = node.getAttributes().getNamedItem("supply_id").getNodeValue();
		supplyPeriod = ((Integer.parseInt(node.getAttributes().getNamedItem("supply_period").getNodeValue())*60)*1000);
		buildItemId = node.getAttributes().getNamedItem("build_item").getNodeValue();
		rottenPeriod = ((Integer.parseInt(node.getAttributes().getNamedItem("rotten_period").getNodeValue())*60)*1000);
		
		//Building Extra and Yield Item
		for(int i = 0; i < node.getChildNodes().getLength(); i++){
			Node getNode = node.getChildNodes().item(i);
			
			if(getNode.getNodeName().equals(EXTRA_BUILDING)){
				BuildingExtra newExtra = new BuildingExtra();
				newExtra.setDataFromNode(getNode.getAttributes().getNamedItem("id").getNodeValue(), Integer.parseInt(getNode.getAttributes().getNamedItem("result").getNodeValue()));
				extra.add(newExtra);
			}else if(getNode.getNodeName().equals(YIELD_BUILDING)){
				BuildingYieldItem newYield = new BuildingYieldItem();
				newYield.setDataFromNode(getNode.getAttributes().getNamedItem("id").getNodeValue(), Integer.parseInt(getNode.getAttributes().getNamedItem("quantity").getNodeValue()), Integer.parseInt(getNode.getAttributes().getNamedItem("chance").getNodeValue()), Integer.parseInt(getNode.getAttributes().getNamedItem("random_time").getNodeValue()));
				yieldItem.add(newYield);
			}
		}
	}
	
	public ArrayList<BuildingExtra> getExtra(){
		return extra;
	}
	
	public ArrayList<BuildingYieldItem> getYieldItem(){
		return yieldItem;
	}
	
	public Item getBuildItem(){
		return this.buildItem;
	}
	
	public Item getSupplyItem(){
		return this.supplyItem;
	}
	
	public String getBuildItemId(){
		return this.buildItemId;
	}
	
	public String getSupplyId(){
		return this.supplyId;
	}
	
	public String getId(){
		return this.id;
	}
	
	public int getBuildPeriod(){
		return this.buildPeriod;
	}
	
	public int getSupplyPeriod(){
		return this.supplyPeriod;
	}
	
	public String getBuildingType(){
		return this.buildingType;
	}
	
	public int getRottenPeriod(){
		return this.rottenPeriod;
	}
	
	public void setBuildItem(Item setItem){
		this.buildItem = setItem;
	}
	
	public void setSupplyItem(Item setItem){
		this.supplyItem = setItem;
	}
	
	public ArrayList<ItemQuantityPair> generateYieldItem(){
		ArrayList<ItemQuantityPair> totalYieldItem = new ArrayList<ItemQuantityPair>();
		
		for(BuildingYieldItem arrayOfYieldItem:yieldItem){
			if(!(arrayOfYieldItem.getId().equals(ItemManager.ITEM_ID_MONEY))){
				for(int i = 0; i < arrayOfYieldItem.getRandomTime(); i++){
					int randomChance = (int) (Math.random()*100);
					if(randomChance < arrayOfYieldItem.getChance()){
						Item itemToPush = arrayOfYieldItem.getItem();
						
						ItemQuantityPair newItemPair = new ItemQuantityPair();
						newItemPair.setItemQuantity(arrayOfYieldItem.getQuantity());
						newItemPair.setItem(itemToPush);
						totalYieldItem.add(newItemPair);
					}
				}
			}
		}
		
		return totalYieldItem;
	}
	
	public int generateYieldMoney(){
		int totalYieldMoney = 0;
		
		for(BuildingYieldItem arrayOfYieldItem:yieldItem){
			if(arrayOfYieldItem.getId().equals(ItemManager.ITEM_ID_MONEY)){
				for(int i = 0; i < arrayOfYieldItem.getRandomTime(); i++){
					int randomChance = (int) (Math.random()*100);
					if(randomChance < arrayOfYieldItem.getChance()){
						totalYieldMoney+=arrayOfYieldItem.getQuantity();
					}
				}
			}
		}
		
		return totalYieldMoney;
	}
	
  //---- return extra item1  ----//
	public Item getExtraItem1(){
		Item extraItem1 = extra.get(0).getItem();
		return extraItem1;
	}
	
  //---- return extra item2  ----//
	public Item getExtraItem2(){
		Item extraItem2 = extra.get(1).getItem();
		return extraItem2;
	}
}
