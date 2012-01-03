package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.BuildingManager;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Item;
import codegears.coca.data.ItemManager;
import codegears.coca.data.ItemQuantityPair;
import codegears.coca.data.Player;
import codegears.coca.ui.ShopItem;
import codegears.coca.util.MilliSecToHour;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ShopDialog extends Activity implements OnClickListener {
	
	private ArrayList<Item> shopItem;
	private ArrayList<ItemQuantityPair> backpackItem;
	private Player currentPlayer;
	private MyApp app;
	private BuildingManager bManager;
	private ImageButton closeButton;
	private LinearLayout shopLayout;
	private LinearLayout backpackLayout;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.shopdialog);
    
    app = (MyApp)this.getApplication();
    bManager = app.getBuildingManager();
    currentPlayer = app.getCurrentPlayer();
    shopItem = app.getItemManager().getItemByType(ItemManager.ITEM_TYPE_NORMAL);
    backpackItem = currentPlayer.getBackpack();
    
    closeButton = (ImageButton) findViewById(R.id.shopCloseButton);
    closeButton.setOnClickListener(this);
    shopLayout = (LinearLayout) findViewById(R.id.shopItem);
    backpackLayout = (LinearLayout) findViewById(R.id.backpackItem);
    
    //Build shop dialog
    for(Item fetchShopItem:shopItem){
    	ShopItem newShopItem = new ShopItem(this);
    	newShopItem.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) );
    	newShopItem.setItemName(fetchShopItem.getName());
    	newShopItem.setItemPrice(String.valueOf(fetchShopItem.getPrice()));
    	//newShopItem.setItemTime(String.valueOf(MilliSecToHour.getConvert(bManager.getMatchBuilding(bManager.getBuildingIdFromItemBuild(fetchShopItem.getId())).getBuildPeriod())));
    
    	shopLayout.addView(newShopItem);
    }
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
}
