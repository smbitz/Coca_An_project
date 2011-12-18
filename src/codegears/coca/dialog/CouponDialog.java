package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.DefaultVar;
import codegears.coca.data.Item;
import android.app.Activity;
import android.os.Bundle;

public class CouponDialog extends Activity {
	
	private ArrayList<Item> couponItem;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.coupondialog);
    
    couponItem = MyApp.getItemManager().getItemByType(DefaultVar.ITEM_TYPE_COUPON);
	}
}
