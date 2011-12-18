package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Item;
import android.app.Activity;
import android.os.Bundle;

public class SupplyBoxDialog extends Activity{
	
	private ArrayList<Item> supplyItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.supplydialog);
		
		supplyItem = MyApp.getItemManager().getSupplyItem();
	}
}
