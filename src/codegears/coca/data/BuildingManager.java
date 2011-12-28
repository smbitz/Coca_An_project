package codegears.coca.data;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import codegears.coca.LoadListener;
import codegears.coca.MyApp;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;

public class BuildingManager implements NetworkThreadListener {

	private static final String BUILDING_URL = "BUILDING_URL";
	
	private LoadListener listener;
	private ArrayList<Building> building;
	
	private MyApp app;
	
	//---- Can't called by any class except MyApp ----//
	public BuildingManager(MyApp app) {
		this.app = app;
		building = new ArrayList<Building>();
	}
	
	public void load(){
		String urlString = app.getConfig().get(BUILDING_URL).toString();
		NetworkThreadUtil.getXml( urlString, this );
	}

	private void onXmlComplete(Document document) {
		NodeList fetchXml = document.getDocumentElement().getElementsByTagName("building");
		for(int i = 0; i < fetchXml.getLength(); i++){
			Building newBuilding = new Building();
			newBuilding.setDataFromXmlNode(fetchXml.item(i));
			building.add(newBuilding);
		}
	}
	
	public ArrayList<Building> getBuilding(){
		return building;
	}
	
	public void setLoadListener(LoadListener listener){
		this.listener = listener;
	}
	
	public Building getMatchBuilding(String id){
		for(Building matchBuildingId:building){
			if(matchBuildingId.getId().equals(id)){
				return matchBuildingId;
			}
		}
		return null;
	}
	
	@Override
	public void onNetworkDocSuccess(String urlString, Document document) {
		onXmlComplete(document);
		listener.onLoadComplete( this );
	}

	@Override
	public void onNetworkRawSuccess(String urlString, String result) {
	}

	@Override
	public void onNetworkFail(String urlString) {
		load();
	}
	
}