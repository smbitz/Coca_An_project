package codegears.coca.data;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import codegears.coca.LoadListener;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;

public class ItemManager implements NetworkThreadListener {
	
	private LoadListener listener;
	private ArrayList<Item> item;
	
	public ItemManager(){
		item = new ArrayList<Item>();
	}
	
	public void load(String url){
		NetworkThreadUtil.getXml( url, this );
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
	}

	public ArrayList<Item> getItemByType(String getType) {
		ArrayList<Item> newArrayItemByType = null;
		
		for(Item fetchItem:item){
			if(fetchItem.getItemType().equals(getType)){
				newArrayItemByType.add(fetchItem);
			}
		}
		
		return newArrayItemByType;
	}

	public ArrayList<Item> getSupplyItem() {
		ArrayList<Item> newArraySupplyItem = null;
		
		for(Item fetchItem:item){
			if(fetchItem.getId().equals(DefaultVar.ITEM_ID_WATER)||
				 fetchItem.getId().equals(DefaultVar.ITEM_ID_SAPPAN_WOOD)||
				 fetchItem.getId().equals(DefaultVar.ITEM_ID_PELLET_FOOD)||
				 fetchItem.getId().equals(DefaultVar.ITEM_ID_FERTILIZER_A)||
				 fetchItem.getId().equals(DefaultVar.ITEM_ID_FERTILIZER_B)||
				 fetchItem.getId().equals(DefaultVar.ITEM_ID_VACCINE_A)||
				 fetchItem.getId().equals(DefaultVar.ITEM_ID_VACCINE_B)||
				 fetchItem.getId().equals(DefaultVar.ITEM_ID_MICROORGANISM_A)||
				 fetchItem.getId().equals(DefaultVar.ITEM_ID_MICROORGANISM_B)){
				newArraySupplyItem.add(fetchItem);
			}
		}
		
		return newArraySupplyItem;
	}

	/*public ArrayList<Item> getBuildItem() {
		ArrayList<Item> newArrayBuildItem = null;
		
		for(Item fetchItem:item){
			if(fetchItem.getId().equals()){
				newArrayBuildItem.add(fetchItem);
			}
		}
		
		return newArrayBuildItem;
	}*/
	
}
