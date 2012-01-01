package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Item;
import codegears.coca.data.ItemManager;
import codegears.coca.data.ItemQuantityPair;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

public class ShopDialog extends Activity implements OnClickListener {
	
	private ArrayList<Item> shopItem;
	private ArrayList<ItemQuantityPair> backpackItem;
	private Player currentPlayer;
	private MyApp app;
	private ImageButton closeButton;
	private HorizontalScrollView storeItemScrollView;
	private HorizontalScrollView backpackItemScrollView;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.shopdialog);
    
    app = (MyApp)this.getApplication();
    currentPlayer = app.getCurrentPlayer();
    shopItem = app.getItemManager().getItemByType(ItemManager.ITEM_TYPE_NORMAL);
    backpackItem = currentPlayer.getBackpack();
    
    closeButton = (ImageButton) this.findViewById(R.id.shopCloseButton);
    closeButton.setOnClickListener(this);
    storeItemScrollView = (HorizontalScrollView) this.findViewById(R.id.shopItemScrollView);
    backpackItemScrollView = (HorizontalScrollView) this.findViewById(R.id.backpackItemScrollView);
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
}
