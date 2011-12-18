package codegears.coca.data;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import codegears.coca.LoadListener;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;
import codegears.coca.util.NetworkUtil;

public class Player implements NetworkThreadListener {

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
	
	//---- Harvest completed tile ----//
	public void harvest(Tile tile){
	}
	
	//---- add supply to tile ----//
	public void addSupply(Tile tile){
	}
}
