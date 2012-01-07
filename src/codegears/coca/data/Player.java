package codegears.coca.data;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import codegears.coca.LoadListener;
import codegears.coca.MyApp;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;

public class Player implements NetworkThreadListener {

	public static final int NUMBER_PERCENTS_TO_ADD_SUPPLY = 95;
	public static final int QTY_USE_EXTRA = 1;
	public static final int QTY_USE_SUPPLY = 1;
	public static final int QTY_START_FARM_PLAYER = 16;
	public static final int QTY_TO_BUILD = 1;
	public static final int BUY_EACH_AREA = 4;
	public static final int TILE_MAX_X = 8;
	public static final int TILE_MAX_Y = 8;
	private static final String PLAYER_URL = "PLAYER_URL";
	
	private String facebookId;
	private int exp;
	private int money;
	private Boolean isNew;
	private String name;
	
	private LoadListener listener;
	private ArrayList<Tile> tileList;
	private ArrayList<ItemQuantityPair> backpack;
	private MyApp app;

	public Player(MyApp app){
		this.app = app;
		tileList = new ArrayList<Tile>();
		backpack = new ArrayList<ItemQuantityPair>();
	}
	
	public void setFacebookId(String setValue){
		this.facebookId = setValue;		
	}
	
	public void load(){
		//app.getConfig()URL
		String urlString = app.getConfig().get(PLAYER_URL).toString();
		
		//pack postData
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("facebook_id", facebookId);
		String postData = NetworkUtil.createPostData(dataMap);
		
		//call NetworkThreadUtil.getXml
		NetworkThreadUtil.getXml(urlString, postData, this);
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
	
	public Boolean getIsNew(){
		return isNew;
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
		load();
	}
	
	//---- Build tile ----//
	public void build(Tile currentTile, Building building){
		int moneyItem = app.getItemManager().howMoney(building.getBuildItemId());
		
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
	
	public int searchBackpackItem(String searchItemId) {
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
		int moneySupply = app.getItemManager().howMoney(supplyItemId);
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
		
		if(isAllowToPurchase()){
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
		String moveTileLandType = moveTile.getLandType();
		String destinationTileLandType = destinationTile.getLandType();
		
		if(moveTileLandType.equals(destinationTileLandType)&&destinationTile.getBuilding()==null){
			return true;
		}
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
	
  //--- buy item ----//
	public void buy(String itemId, int quantity){
		Item currentItem = app.getItemManager().getMatchItem(itemId);
		
		if(money>=(currentItem.getPrice()*quantity)){
			int itemPosition = searchBackpackItem(itemId);
			
			if(itemPosition>=0){
				
				backpack.get(itemPosition).setItemQuantity(backpack.get(itemPosition).getQuantity()+quantity);
			}else{
				ItemQuantityPair newBackpack = new ItemQuantityPair();
				newBackpack.setItemQuantity(quantity);
				newBackpack.setItem(currentItem);
				newBackpack.setId(itemId);
				backpack.add(newBackpack);
			}
			money -= (currentItem.getPrice()*quantity);
		}
	}
	
  //---- sell item----//
	public void sell(String itemId, int quantity){
		int itemPosition = searchBackpackItem(itemId);
		
		if(itemPosition>=0){
			int currentItemQty = backpack.get(itemPosition).getQuantity();
			
			if(currentItemQty>=quantity){
				backpack.get(itemPosition).setItemQuantity(backpack.get(itemPosition).getQuantity()-quantity);
				money += (backpack.get(itemPosition).getItem().getSellPrice())*quantity;
			}
		}
	}
	
  //---- Update Player data to Server ----//
	public void updateToServer(){
		int checkValue = 0;
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			
		  //getTileData and change millisec to sec.
			Element landElement = doc.createElement("land");
			for(Tile tileData:tileList){
				checkValue+=(((int) tileData.getProgress()/1000)+((int) tileData.getSupply()/1000)+((int) tileData.getRottenPeriod()/1000));
				
				Element tileElement = doc.createElement("tile");
				tileElement.setAttribute("land_type", tileData.getLandType());
				tileElement.setAttribute("is_occupy", String.valueOf(tileData.getIsOccupy()));
				tileElement.setAttribute("building_id", tileData.getBuildingId());
				tileElement.setAttribute("progress", String.valueOf(tileData.getProgress()/1000));
				tileElement.setAttribute("supply_left", String.valueOf(tileData.getSupply()/1000));
				tileElement.setAttribute("extra_id", tileData.getExtraId());
				tileElement.setAttribute("rotten_period", String.valueOf(tileData.getRottenPeriod()/1000));
				landElement.appendChild(tileElement);
			}
			
		  //getBackpackItem
			Element backpackElement = doc.createElement("backpack");
			for(ItemQuantityPair backpackItem:backpack){
				checkValue-=((int) backpackItem.getQuantity());
				
				Element backpackItemElement = doc.createElement("item");
				backpackItemElement.setAttribute("id", backpackItem.getId());
				backpackItemElement.setAttribute("quantity", String.valueOf(backpackItem.getQuantity()));
				backpackElement.appendChild(backpackItemElement);
			}
			
			//Player
			Element playerElement = doc.createElement("player");
			playerElement.setAttribute("facebook_id", facebookId);
			playerElement.setAttribute("exp", String.valueOf(exp));
			playerElement.setAttribute("money", String.valueOf(money));
			playerElement.setAttribute("is_new", String.valueOf(isNew));
			playerElement.setAttribute("c1", String.valueOf(checkValue));
			
			playerElement.appendChild(landElement);
			playerElement.appendChild(backpackElement);
			doc.appendChild(playerElement);
			
			//Send Data To Server
	    
			
		} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
		}
	}

	public boolean isAllowToPurchase() {
		int moneyToPurchase = getMoneyRequiredForPurchaseTile();
		int levelToPurchase = getLevelRequiredForPurchaseTile();
		
		if(money>=moneyToPurchase&&getLevel()>=levelToPurchase){
			return true;
		}else {
			return false;
		}
	}

	public void addItemToBackpack(String itemId, int itemQuantity) {
		int searchBackpack = searchBackpackItem(itemId);
		
		if( searchBackpack>=0 ){
			backpack.get(searchBackpack).setItemQuantity(backpack.get(searchBackpack).getQuantity()+itemQuantity);
		}else{
			ItemQuantityPair newItemQuantityPair = new ItemQuantityPair();
			newItemQuantityPair.setId(itemId);
			newItemQuantityPair.setItemQuantity(itemQuantity);
			newItemQuantityPair.setItem(app.getItemManager().getMatchItem(itemId));
			backpack.add(newItemQuantityPair);
		}
	}
	
	public void supply(Tile tile){
	}
	
	public void extra1(Tile tile){
	}
	
	public void extra2(Tile tile){
	}
}
