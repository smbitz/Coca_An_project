package codegears.coca.data;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import codegears.coca.LoadListener;
import codegears.coca.MyApp;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;

public class ItemManager implements NetworkThreadListener {
	
	public static final String ITEM_ID_MORNING_GLORY_SEED = "10";
	public static final String ITEM_ID_CHINESE_CABBAGE_SEED = "20";
	public static final String ITEM_ID_PUMPKIN_SEED = "30";
	public static final String ITEM_ID_BABY_CORN_SEED = "40";
	public static final String ITEM_ID_STRAW_MUSHROOMS_SEED = "50";
	public static final String ITEM_ID_CHICKEN_BABY = "60";
	public static final String ITEM_ID_PIG_BABY = "70";
	public static final String ITEM_ID_COW_BABY = "80";
	public static final String ITEM_ID_SHEEP_BABY = "90";
	public static final String ITEM_ID_OSTRICH_BABY = "100";
	public static final String ITEM_ID_FISH_BABY = "110";
	public static final String ITEM_ID_SQUID_BABY = "120";
	public static final String ITEM_ID_SCALLOPS_BABY = "130";
	public static final String ITEM_ID_SHRIMP_BABY = "140";
	public static final String ITEM_ID_OYSTER_BABY = "150";
	public static final String ITEM_ID_MORNING_GLORY = "160";
	public static final String ITEM_ID_CHINESE_CABBAGE = "170";
	public static final String ITEM_ID_PUMPKIN = "180";
	public static final String ITEM_ID_BABY_CORN = "190";
	public static final String ITEM_ID_STRAW_MUSHROOMS = "200";
	public static final String ITEM_ID_CHICKEN = "210";
	public static final String ITEM_ID_PIG = "220";
	public static final String ITEM_ID_COW = "230";
	public static final String ITEM_ID_SHEEP = "240";
	public static final String ITEM_ID_OSTRICH = "250";
	public static final String ITEM_ID_FISH = "260";
	public static final String ITEM_ID_SQUID = "270";
	public static final String ITEM_ID_SCALLOPS = "280";
	public static final String ITEM_ID_SHRIMP = "290";
	public static final String ITEM_ID_OYSTER = "300";
	public static final String ITEM_ID_WATER = "310";
	public static final String ITEM_ID_SAPPAN_WOOD = "320";
	public static final String ITEM_ID_PELLET_FOOD = "330";
	public static final String ITEM_ID_PEARL = "340";
	public static final String ITEM_ID_GOLD = "350";
	public static final String ITEM_ID_DIAMOND = "360";
	public static final String ITEM_ID_MORNING_GLORY_COUPON = "5010";
	public static final String ITEM_ID_CHINESE_CABBAGE_COUPON = "5020";
	public static final String ITEM_ID_PUMPKIN_COUPON = "5030";
	public static final String ITEM_ID_BABY_CORN_COUPON = "5040";
	public static final String ITEM_ID_STRAW_MUSHROOMS_COUPON = "5050";
	public static final String ITEM_ID_CHICKEN_COUPON = "5060";
	public static final String ITEM_ID_PIG_COUPON = "5070";
	public static final String ITEM_ID_COW_COUPON = "5080";
	public static final String ITEM_ID_SHEEP_COUPON = "5090";
	public static final String ITEM_ID_OSTRICH_COUPON = "50100";
	public static final String ITEM_ID_FISH_COUPON = "50110";
	public static final String ITEM_ID_SQUID_COUPON = "50120";
	public static final String ITEM_ID_SCALLOPS_COUPON = "50130";
	public static final String ITEM_ID_SHRIMP_COUPON = "50140";
	public static final String ITEM_ID_OYSTER_COUPON = "50150";
	public static final String ITEM_ID_FERTILIZER_A = "7010";
	public static final String ITEM_ID_FERTILIZER_B = "7020";
	public static final String ITEM_ID_VACCINE_A = "7030";
	public static final String ITEM_ID_VACCINE_B = "7040";
	public static final String ITEM_ID_MICROORGANISM_A = "7050";
	public static final String ITEM_ID_MICROORGANISM_B = "7060";
	public static final String ITEM_ID_MONEY = "money";
	
	public static final String ITEM_TYPE_NORMAL = "normal";
	public static final String ITEM_TYPE_COUPON = "coupon";
	public static final String ITEM_TYPE_SPECIAL = "special";
	
	private static final String ITEM_URL = "ITEM_URL";
	
	private LoadListener listener;
	private ArrayList<Item> item;
	
	private MyApp app;
	
	public ItemManager(MyApp app){
		this.app = app;
		item = new ArrayList<Item>();
	}
	
	public void load(){
		String urlString = app.getConfig().get(ITEM_URL).toString();
		NetworkThreadUtil.getXml( urlString, this );
	}

	public void setLoadListener(LoadListener listener){
		this.listener = listener;
	}
	
	private void onXmlComplete(Document document) {
		NodeList fetchXml = document.getDocumentElement().getElementsByTagName("item");
		for(int i = 0; i < fetchXml.getLength(); i++){
			Item newItem = new Item();
			newItem.setDataFromXmlNode(fetchXml.item(i));
			item.add(newItem);
		}
	}

	public ArrayList<Item> getItem(){
		return item;
	}
	
	public Item getMatchItem(String id){
		for(Item matchItemId:item){
			if(matchItemId.getId().equals(id)){
				return matchItemId;
			}
		}
		return null;
	}
	
	@Override
	public void onNetworkDocSuccess( String urlString, Document document ) {
		onXmlComplete(document);
		listener.onLoadComplete( this );
	}

	@Override
	public void onNetworkRawSuccess( String urlString, String result ) {
	}

	@Override
	public void onNetworkFail( String urlString ) {
		load();
	}
	
	public ArrayList<Item> getItemByType(String getType) {
		ArrayList<Item> newArrayItemByType = new ArrayList<Item>();
		
		for(Item fetchItem:item){
			if(fetchItem.getItemType().equals(getType)){
				newArrayItemByType.add(fetchItem);
			}
		}
		
		return newArrayItemByType;
	}

	public ArrayList<Item> getSupplyItem() {
		ArrayList<Item> newArraySupplyItem = new ArrayList<Item>();
		
		for(Item fetchItem:item){
			if(fetchItem.getId().equals(ITEM_ID_WATER)||
				 fetchItem.getId().equals(ITEM_ID_SAPPAN_WOOD)||
				 fetchItem.getId().equals(ITEM_ID_PELLET_FOOD)||
				 fetchItem.getId().equals(ITEM_ID_FERTILIZER_A)||
				 fetchItem.getId().equals(ITEM_ID_FERTILIZER_B)||
				 fetchItem.getId().equals(ITEM_ID_VACCINE_A)||
				 fetchItem.getId().equals(ITEM_ID_VACCINE_B)||
				 fetchItem.getId().equals(ITEM_ID_MICROORGANISM_A)||
				 fetchItem.getId().equals(ITEM_ID_MICROORGANISM_B)){
				newArraySupplyItem.add(fetchItem);
			}
		}
		
		return newArraySupplyItem;
	}

	public ArrayList<Item> getBuildItem() {
		ArrayList<Item> newArrayBuildItem = new ArrayList<Item>();
		
		for(Item fetchItem:item){
			if(fetchItem.getId().equals(ITEM_ID_MORNING_GLORY_SEED)||
				 fetchItem.getId().equals(ITEM_ID_CHINESE_CABBAGE_SEED)||
				 fetchItem.getId().equals(ITEM_ID_PUMPKIN_SEED)||
				 fetchItem.getId().equals(ITEM_ID_BABY_CORN_SEED)||
				 fetchItem.getId().equals(ITEM_ID_STRAW_MUSHROOMS_SEED)||
				 fetchItem.getId().equals(ITEM_ID_CHICKEN_BABY)||
				 fetchItem.getId().equals(ITEM_ID_PIG_BABY)||
				 fetchItem.getId().equals(ITEM_ID_COW_BABY)||
				 fetchItem.getId().equals(ITEM_ID_SHEEP_BABY)||
				 fetchItem.getId().equals(ITEM_ID_OSTRICH_BABY)||
				 fetchItem.getId().equals(ITEM_ID_FISH_BABY)||
				 fetchItem.getId().equals(ITEM_ID_SQUID_BABY)||
				 fetchItem.getId().equals(ITEM_ID_SCALLOPS_BABY)||
				 fetchItem.getId().equals(ITEM_ID_SHRIMP_BABY)||
				 fetchItem.getId().equals(ITEM_ID_OYSTER_BABY)){
				newArrayBuildItem.add(fetchItem);
			}
		}
		
		return newArrayBuildItem;
	}
	
	public ArrayList<Item> getCouponItem() {
		ArrayList<Item> newArrayCouponItem = new ArrayList<Item>();
		
		for(Item fetchItem:item){
			if(fetchItem.getId().equals(ITEM_ID_MORNING_GLORY_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_CHINESE_CABBAGE_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_PUMPKIN_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_BABY_CORN_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_STRAW_MUSHROOMS_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_CHICKEN_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_PIG_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_COW_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_SHEEP_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_OSTRICH_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_FISH_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_SQUID_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_SCALLOPS_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_SHRIMP_COUPON)||
					 fetchItem.getId().equals(ITEM_ID_OYSTER_COUPON)){
				newArrayCouponItem.add(fetchItem);
			}
		}
		
		return newArrayCouponItem;
	}

	public int howMoney(String id) {
		for(Item matchItemId:item){
			if(matchItemId.getId().equals(id)){
				return matchItemId.getPrice();
			}
		}
		return 0;
	}
	
}
