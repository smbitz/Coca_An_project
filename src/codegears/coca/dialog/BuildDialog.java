package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Item;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;

public class BuildDialog extends Activity {
	
	private ArrayList<Item> buildItem;
	private Player currentPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.builddialog);
		
		buildItem = MyApp.getItemManager().getBuildItem();
		currentPlayer = MyApp.getCurrentPlayer();
	}
}
