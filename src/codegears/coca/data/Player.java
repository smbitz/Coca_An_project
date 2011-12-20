package codegears.coca.data;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.hardware.Camera.Size;

import codegears.coca.LoadListener;
import codegears.coca.MyApp;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;
import codegears.coca.util.NetworkUtil;

public class Player implements NetworkThreadListener {

	public static final int NUMBER_PERCENTS_TO_ADD_SUPPLY = 95;
	public static final int QTY_USE_EXTRA = 1;
	public static final int QTY_USE_SUPPLY = 1;
	public static final int QTY_START_FARM_PLAYER = 16;
	public static final int QTY_TO_BUILD = 1;
	public static final int BUY_EACH_AREA = 4;
	public static final int TILE_MAX_X = 8;
	public static final int TILE_MAX_Y = 8;
	
	private String facebookId;
	private int exp;
	private int money;
	private Boolean isNew;
	private String name;
	
	private LoadListener listener;
	private ArrayList<Tile> tileList;
	private ArrayList<ItemQuantityPair> backpack;

	public Player(){
		tileList = new ArrayList<Tile>();
		backpack = new ArrayList<ItemQuantityPair>();
	}
	
	public void load(String url, String postData){
		NetworkThreadUtil.getXml( url, postData, this);
	}
	
	private void onXmlComplete(Document document) {
		facebookId = document.getDocumentElement().getAttribute("facebook_id");
		exp = Integer.parseInt(document.getDocumentElement().getAttribute("exp"));
		money = Integer.parseInt(document.getDocumentElement().getAttribute("money"));
		isNew = Boolean.valueOf(document.getDocumentElement().getAttribute("is_new"));
		
		NodeList fetchXml = document.getDocumentElement().getChildNodes();
		
		//Land Data Backpack Data
		for(int i = 0; i < fetchXml.getLength(); i++){
			if(fetchXml.item(i).getNodeName().equals("land")){
				NodeList fetchLandXml = fetchXml.item(i).getChildNodes();
				
				for(int j = 0; j < fetchLandXml.getLength(); j++){
					if(fetchLandXml.item(j).getNodeName().equals("tile")){
						Tile newTile = new Tile();
						newTile.setDataFromXmlNode(fetchLandXml.item(j));
						tileList.add(newTile);
					}
				}
			}else if(fetchXml.item(i).getNodeName().equals("backpack")){
				NodeList fetchBackpackXml = fetchXml.item(i).getChildNodes();
				
				for(int z = 0; z < fetchBackpackXml.getLength(); z++){
					if(fetchBackpackXml.item(z).getNodeName().equals("item")){
						ItemQuantityPair newItemQuantityPair = new ItemQuantityPair();
						newItemQuantityPair.setDataFromXmlNode(fetchBackpackXml.item(z));
						backpack.add(newItemQuantityPair);
					}
				}
			}
		}
		
	}

	public ArrayList<Tile> getTile(){
		return tileList;
	}
	
	public ArrayList<ItemQuantityPair> getBackpack(){
		return backpack;
	}                                              
	
	public void setLoadListener(LoadListener listener){
		this.listener = listener;
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
	}
	
	//---- Build tile ----//
	public void build(Tile currentTile, Building building){
		int moneyItem = MyApp.getItemManager().howMoney(building.getBuildItemId());
		
		if(currentTile.isAllowToBuild(building)){
			if(isItemEnough(building.getId(), QTY_TO_BUILD)){
				for(int i = 0; i < backpack.size(); i++){
					if(backpack.get(i).getId().equals(building.getBuildItemId())){
						backpack.get(i).setItemQuantity(backpack.get(i).getQuantity()-QTY_TO_BUILD);
					}
					currentTile.buildTile(building);
				}
			}else if(money>=(moneyItem*QTY_TO_BUILD)){
				money-=(moneyItem*QTY_TO_BUILD);
				currentTile.buildTile(building);
			}
		}
	}
	
	//---- Harvest completed tile ----//
	public void harvest(Tile tile){
		int getYieldMoney = tile.getBuilding().generateYieldMoney();
		
		if(tile.getBuildingStatus()==Tile.BUILDING_COMPLETED){
			//Harvest Yield Item
			ArrayList<ItemQuantityPair> getYieldItem = tile.getBuilding().generateYieldItem();
			String getTileExtraId = tile.getExtraId();
			float harvestPercentsExtraA = tile.getBuilding().getExtra().get(0).getResult();
			float harvestPercentsExtraB = tile.getBuilding().getExtra().get(1).getResult();
			
			for(ItemQuantityPair arrayGetYieldItem:getYieldItem){
				String yieldItemId = arrayGetYieldItem.getItem().getId();
				int itemPositionBackpack = searchBackpackItem(yieldItemId);
				int yieldItemQty = arrayGetYieldItem.getQuantity();
				
				int extraQuantity = 0;
				
			  //Match with all item id can have extra item
				if(yieldItemId.equals(ItemManager.ITEM_ID_MORNING_GLORY)||
					 yieldItemId.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE)||
					 yieldItemId.equals(ItemManager.ITEM_ID_PUMPKIN)||
					 yieldItemId.equals(ItemManager.ITEM_ID_BABY_CORN)||
					 yieldItemId.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS)||
					 yieldItemId.equals(ItemManager.ITEM_ID_CHICKEN)||
					 yieldItemId.equals(ItemManager.ITEM_ID_PIG)||
					 yieldItemId.equals(ItemManager.ITEM_ID_COW)||
					 yieldItemId.equals(ItemManager.ITEM_ID_SHEEP)||
					 yieldItemId.equals(ItemManager.ITEM_ID_OSTRICH)||
					 yieldItemId.equals(ItemManager.ITEM_ID_FISH)||
					 yieldItemId.equals(ItemManager.ITEM_ID_SQUID)||
					 yieldItemId.equals(ItemManager.ITEM_ID_SCALLOPS)||
					 yieldItemId.equals(ItemManager.ITEM_ID_SHRIMP)||
					 yieldItemId.equals(ItemManager.ITEM_ID_OYSTER)){
					if(getTileExtraId.equals(ItemManager.ITEM_ID_FERTILIZER_A)||
						 getTileExtraId.equals(ItemManager.ITEM_ID_MICROORGANISM_A)||
						 getTileExtraId.equals(ItemManager.ITEM_ID_VACCINE_A)){
						extraQuantity = (int) (yieldItemQty*(harvestPercentsExtraA/100));
					}else if(getTileExtraId.equals(ItemManager.ITEM_ID_FERTILIZER_B)||
							     getTileExtraId.equals(ItemManager.ITEM_ID_MICROORGANISM_B)||
							     getTileExtraId.equals(ItemManager.ITEM_ID_VACCINE_B)){
						extraQuantity = (int) (yieldItemQty*(harvestPercentsExtraB/100));
					}
					yieldItemQty+=extraQuantity;
				}
				
				if(itemPositionBackpack>=0){
					int currentBackpackQty = this.backpack.get(itemPositionBackpack).getQuantity();
					this.backpack.get(itemPositionBackpack).setItemQuantity(currentBackpackQty+yieldItemQty);
				}else{
					ItemQuantityPair newItem = new ItemQuantityPair();
					newItem.setItemQuantity(yieldItemQty);
					newItem.setItem(arrayGetYieldItem.getItem());
					newItem.setId(arrayGetYieldItem.getItem().getId());
					this.backpack.add(newItem);
				}
			}
			
		  //Harvest Money
			this.money += getYieldMoney;
			
		  //Clear Tile
			tile.clearTile();
		}else if(tile.getBuildingStatus()==Tile.BUILDING_ROTTED){
		  //Harvest Money
			this.money += getYieldMoney;
			
		  //Clear Tile
			tile.clearTile();
		}
	}
	
	private int searchBackpackItem(String searchItemId) {
		for(int i = 0; i < backpack.size(); i++){
			if(backpack.get(i).getId().equals(searchItemId)){
				return i;
			}
		}
		
		return -1;
	}

	//---- add supply to tile ----//
	public void addSupply(Tile tile){
		String supplyItemId = tile.getBuilding().getSupplyId();
		int moneySupply = MyApp.getItemManager().howMoney(supplyItemId);
		int currentTileSupplyPercents = tile.getSupplyPercentage()*100;
		
		if(currentTileSupplyPercents<NUMBER_PERCENTS_TO_ADD_SUPPLY){
			if(isItemEnough(supplyItemId, QTY_USE_SUPPLY)){
				int searchSupply = searchBackpackItem(supplyItemId);
				
				if(searchSupply>=0){
					int currentQty = backpack.get(searchSupply).getQuantity();
					backpack.get(searchSupply).setItemQuantity(currentQty-1);
					tile.setSupply(tile.getBuilding().getSupplyPeriod());
				}
			}else if(this.money > moneySupply){
				this.money -= moneySupply;
				tile.setSupply(tile.getBuilding().getSupplyPeriod());
			}
		}
	}

	private boolean isItemEnough(String checkId, int checkQuantity) {
	  //check from backpack for enough item in that itemId
		for(int i = 0; i < backpack.size(); i++){
			if(backpack.get(i).getId().equals(checkId)&&backpack.get(i).getQuantity()>=checkQuantity){
				return true;
			}
		}
		
		return false;
	}
	
  //---- Purchase that tile ----//
	public void purchase(Tile purchaseTile){
		int moneyToPurchase = getMoneyRequiredForPurchaseTile();
		int levelToPurchase = getLevelRequiredForPurchaseTile();
		
		System.out.println("Can't Purchase !! "+money+":"+moneyToPurchase+" "+getLevel()+":"+levelToPurchase);
		
		if(money>=moneyToPurchase&&getLevel()>=levelToPurchase){
			System.out.println("Can Purchase !!");
			//Calculate money
			money -= moneyToPurchase;
			
		  //Change tile Status
			for(int i = 0; i < tileList.size(); i++){
				if(tileList.get(i)==purchaseTile){
					tileList.get(i).setIsOccupy(true);
					tileList.get(i+1).setIsOccupy(true);
					tileList.get(i+TILE_MAX_X).setIsOccupy(true);
					tileList.get(i+TILE_MAX_X+1).setIsOccupy(true);
				}
			}
		}
	}

	private int getLevel() {
		int currentPlayerLevel = 0;
		int expForNextLevel = 0;
		
		while(exp>=expForNextLevel){
			currentPlayerLevel++;
			expForNextLevel = (int) (50+(50*(Math.pow(currentPlayerLevel, 2))));
		}
		
		return currentPlayerLevel;
	}

	private int getLevelRequiredForPurchaseTile() {
		int currentPlayerFarm = getCurrentPlayerFarm();
		int requireLevel = 5*currentPlayerFarm;
		return requireLevel;
	}

	private int getCurrentPlayerFarm() {
		int totalPlayerFarm = 0;
		for(Tile arrayTile:tileList){
			if(arrayTile.getIsOccupy()){
				totalPlayerFarm++;
			}
		}
		totalPlayerFarm -= QTY_START_FARM_PLAYER;
		totalPlayerFarm = (totalPlayerFarm/BUY_EACH_AREA)+1;
		return totalPlayerFarm;
	}

	private int getMoneyRequiredForPurchaseTile() {
		int currentPlayerFarm = getCurrentPlayerFarm();
		int requireMoney = (int) (500+(500*Math.pow(currentPlayerFarm, 2)));
		return requireMoney;
	}
	
  //---- add extraItem to targetTile
	public Boolean addExtraItem(Tile targetTile, Item extraItem){
		String extraItemId1 = targetTile.getBuilding().getExtraItem1().getId();
		String extraItemId2 = targetTile.getBuilding().getExtraItem2().getId();
		
		if(extraItem.getId().equals(extraItemId1)||extraItem.getId()==extraItemId2){
			if(isItemEnough(extraItem.getId(), QTY_USE_EXTRA)){
				int searchExtra = searchBackpackItem(extraItem.getId());
				
				if(searchExtra>=0){
					int currentExtraQty = backpack.get(searchExtra).getQuantity();
					backpack.get(searchExtra).setItemQuantity(currentExtraQty-1);
					targetTile.setExtraId(extraItem.getId());
					return true;
				}
			}
		}
		
		return false;
	}
	
  //---- Check for possible move condtion (1) ----//
	public Boolean isMoveable(Tile moveTile, Tile destinationTile){ 	
		return false;
	}
	
	//---- proceed move tile ----//
	public void moveTile(Tile moveTile, Tile destinationTile){
		destinationTile.setBuildingId(moveTile.getBuildingId());
		destinationTile.setProgress(moveTile.getProgress());
		destinationTile.setSupply(moveTile.getSupply());
		destinationTile.setExtraId(moveTile.getExtraId());
		destinationTile.setRottenPeriod(moveTile.getRottenPeriod());
		destinationTile.setBuilding(moveTile.getBuilding());
		
		moveTile.clearTile();
	}
}
