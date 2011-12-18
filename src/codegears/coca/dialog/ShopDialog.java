package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.DefaultVar;
import codegears.coca.data.Item;
import android.app.Activity;
import android.os.Bundle;

public class ShopDialog extends Activity {
	
	private ArrayList<Item> shopItem;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.shopdialog);
    
    shopItem = MyApp.getItemManager().getItemByType(DefaultVar.ITEM_TYPE_NORMAL);
	}
}
