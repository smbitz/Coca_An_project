package codegears.coca;

import java.util.ArrayList;
import java.util.HashMap;

import codegears.coca.data.Building;
import codegears.coca.data.BuildingExtra;
import codegears.coca.data.BuildingManager;
import codegears.coca.data.BuildingYieldItem;
import codegears.coca.data.Item;
import codegears.coca.data.ItemExchangeItem;
import codegears.coca.data.ItemManager;
import codegears.coca.data.ItemQuantityPair;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;
import codegears.coca.util.Config;
import codegears.coca.util.NetworkUtil;
import android.app.Application;
import android.graphics.Typeface;

public class MyApp extends Application implements LoadListener {
	
	private static final String FONT_POSITION = "font/DB_HelvethaicaMon_X_Med_v3.2.ttf";
	
	private BuildingManager bManager;
	private ItemManager iManager;
	private Player currentPlayer;
	private Config config;
	private boolean isBManagerLoad;
	private boolean isIManagerLoad;
	private boolean isCurrentPlayerLoad;
	private String facebookId;
	private String facebookName;
	private Typeface textFont;
	
	private LoadListener listener;
	
	@Override
	public void onCreate(){
		config = new Config(getApplicationContext());
		bManager = new BuildingManager(this);
		iManager = new ItemManager(this);
		currentPlayer = new Player(this);
		isBManagerLoad = false;
		isIManagerLoad = false;
		isCurrentPlayerLoad = false;
		textFont = Typeface.createFromAsset(getAssets(), FONT_POSITION);
		facebookId = "11223344555";
		facebookName = "Name";
	}
	
	public void load(){
		bManager.setLoadListener( this );
		bManager.load();
		iManager.setLoadListener( this );
		iManager.load();
		currentPlayer.setFacebookId( facebookId );
		currentPlayer.setName( facebookName );
		currentPlayer.setLoadListener( this );
		currentPlayer.load();
	}
	
	public void setLoadListener(LoadListener listener){
		this.listener = listener;
	}
	
	public BuildingManager getBuildingManager(){
		return bManager;
	}
	
	public ItemManager getItemManager(){
		return iManager;
	}
	
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	
	public Config getConfig(){
		return config;
	}
	
	public String getFacebookId(){
		return facebookId;
	}
	
	public Typeface getTextFont(){
		return textFont;
	}

	@Override
	public void onLoadComplete( Object obj ) {
		if(obj == bManager){
			isBManagerLoad = true;
		} else if(obj == iManager){
			isIManagerLoad = true;
		} else if(obj == currentPlayer){
			isCurrentPlayerLoad = true;
		}
		if(isBManagerLoad && isIManagerLoad && isCurrentPlayerLoad){
			manageData();
			listener.onLoadComplete( this );
		}
	}

	private void manageData() {
		//Set Item Building Data
		ArrayList<Building> arrayOfBuilding = bManager.getBuilding();
		
		//for all building
		for(Building buildingFeatch:arrayOfBuilding){
			buildingFeatch.setBuildItem(iManager.getMatchItem(buildingFeatch.getBuildItemId()));
			buildingFeatch.setSupplyItem(iManager.getMatchItem(buildingFeatch.getSupplyId()));
			
			ArrayList<BuildingExtra> arrayBuildingExtra = buildingFeatch.getExtra();
			for(BuildingExtra extraFeatch:arrayBuildingExtra){
				extraFeatch.setItem(iManager.getMatchItem(extraFeatch.getId()));
			}
			
			ArrayList<BuildingYieldItem> arrayBuildingYield = buildingFeatch.getYieldItem();
			for(BuildingYieldItem yieldItemFeatch:arrayBuildingYield){
				if(!(yieldItemFeatch.getId().equals("money"))){
					yieldItemFeatch.setItem(iManager.getMatchItem(yieldItemFeatch.getId()));
				}
			}
		}
		
	  //Set Item Data
		ArrayList<Item> arrayOfItem = iManager.getItem();
		
	  //For all item
		//Set exchange item
		for(Item itemFeatch:arrayOfItem){
			ArrayList<ItemExchangeItem> arrayItemExchange = itemFeatch.getExchangeItem();
			for(ItemExchangeItem exchangeItemFeatch:arrayItemExchange){
				exchangeItemFeatch.setItem(iManager.getMatchItem(exchangeItemFeatch.getId()));
			}
		}
		
	  //Set Player Data
		ArrayList<Tile> arrayPlayerTile = currentPlayer.getTile();
		
	  //Set Tile
		for(Tile tileFeatch:arrayPlayerTile){
			if(!(tileFeatch.getBuildingId().equals(null))){
				tileFeatch.setBuilding(bManager.getMatchBuilding(tileFeatch.getBuildingId()));
			}
		}
		
		//Set Backpack
		ArrayList<ItemQuantityPair> arrayPlayerBackpack = currentPlayer.getBackpack();
		for(ItemQuantityPair backpackFeatch:arrayPlayerBackpack){
			backpackFeatch.setItem(iManager.getMatchItem(backpackFeatch.getId()));
		}
	}
}