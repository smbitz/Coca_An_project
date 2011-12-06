package codegears.coca.data;

import org.w3c.dom.Document;

import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;

public class BuildingManager implements NetworkThreadListener {

	//---- Can't called by any class except MyApp ----//
	public BuildingManager() {
		// TODO Auto-generated constructor stub
	}
	
	public void load(){
		
	}

	@Override
	public void onNetworkDocSuccess(String urlString, Document document) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkRawSuccess(String urlString, String result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkFail(String urlString) {
		// TODO Auto-generated method stub
		
	}
	
}
