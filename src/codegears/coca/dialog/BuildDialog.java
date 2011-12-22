package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Item;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;

public class BuildDialog extends Activity {
	
	public static final String BUILDING_ID = "BUILDING_ID";
	private ArrayList<Item> buildItem;
	private Player currentPlayer;
	private MyApp app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.builddialog);
		app = (MyApp)this.getApplication();		
		buildItem = app.getItemManager().getBuildItem();
		currentPlayer = app.getCurrentPlayer();
	}
}
