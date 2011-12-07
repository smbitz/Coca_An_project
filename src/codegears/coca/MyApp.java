package codegears.coca;

import codegears.coca.data.BuildingManager;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import codegears.coca.util.Config;
import android.app.Application;

public class MyApp extends Application implements LoadListener {
	
	private BuildingManager bManager;
	private ItemManager iManager;
	private Player currentPlayer;
	private Config config;
	private boolean isBManagerLoad;
	private boolean isIManagerLoad;
	private boolean isCurrentPlayerLoad;
	
	private LoadListener listener;
	
	@Override
	public void onCreate(){
		config = new Config();
		bManager = new BuildingManager();
		iManager = new ItemManager();
		currentPlayer = new Player();
		isBManagerLoad = false;
		isIManagerLoad = false;
		isCurrentPlayerLoad = false;
	}
	
	public void load(){
		bManager.setLoadListener( this );
		bManager.load(/*URL*/config.getData("ITEM_URL"));
		iManager.setLoadListener( this );
		iManager.load(/*URL*/config.getData("ITEM_URL"));
		currentPlayer.setLoadListener( this );
		currentPlayer.load(/*URL*/config.getData("ITEM_URL"));

	}
	
	public void setLoadListener(LoadListener listener){
		this.listener = listener;
	}
	
	public BuildingManager getBuildingManager(){
		return bManager;
	}
	
	public Config getConfig(){
		return config;
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
			listener.onLoadComplete( this );
		}
	}
}