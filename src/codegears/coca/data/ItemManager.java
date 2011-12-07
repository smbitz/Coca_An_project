package codegears.coca.data;

import java.util.ArrayList;

import org.w3c.dom.Document;

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

	@Override
	public void onNetworkDocSuccess( String urlString, Document document ) {
		listener.onLoadComplete( this );
	}

	@Override
	public void onNetworkRawSuccess( String urlString, String result ) {
	}

	@Override
	public void onNetworkFail( String urlString ) {
	}
	
}
