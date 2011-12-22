package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Item;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;

public class SupplyBoxDialog extends Activity{
	
	private ArrayList<Item> supplyItem;
	private Player currentPlayer;
	private MyApp app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.supplydialog);
		
		app = (MyApp)this.getApplication();
		supplyItem = app.getItemManager().getSupplyItem();
		currentPlayer = app.getCurrentPlayer();
	}
}
