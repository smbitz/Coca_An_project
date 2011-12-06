package codegears.coca.data;

import java.util.ArrayList;
import android.util.Xml;

public class Building {
	
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
	
	public void setDataFromXmlNode(){
		
	}
	
	public Item getExtraItem1(){
		return null;
	}
	
	public Item getExtraItem2(){
		return null;
	}
	
	public ItemQuantityPair generateYieldItem(){
		return null;
	}
	
	public int generateYieldMoney(){
		return 0;
	}
}
