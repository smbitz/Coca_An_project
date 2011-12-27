package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.DefaultVar;
import codegears.coca.data.Item;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class CouponDialog extends Activity implements OnClickListener {
	
	private ArrayList<Item> couponItem;
	private Player currentPlayer;
	private MyApp app;
	private ImageButton closeButton;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.coupondialog);
    
    app = (MyApp)this.getApplication();
    couponItem = app.getItemManager().getItemByType(ItemManager.ITEM_TYPE_COUPON);
    currentPlayer = app.getCurrentPlayer();
    
    closeButton = (ImageButton) this.findViewById(R.id.couponCloseButton);
    closeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
}
