package codegears.coca;

import codegears.coca.data.BuildingManager;
import codegears.coca.util.Config;
import android.app.Application;

public class MyApp extends Application {
	private BuildingManager bManager;
	private Config config;
	
	@Override
	public void onCreate(){
		bManager = new BuildingManager();
		config = new Config();
	}
	
	public BuildingManager getBuildingManager(){
		return bManager;
	}
	
	public Config getConfig(){
		return config;
	}
}
