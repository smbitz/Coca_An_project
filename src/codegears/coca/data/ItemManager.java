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
	
}
