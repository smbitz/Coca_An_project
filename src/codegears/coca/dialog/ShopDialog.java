package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.BuildingManager;
import codegears.coca.data.Item;
import codegears.coca.data.ItemManager;
import codegears.coca.data.ItemQuantityPair;
import codegears.coca.data.Player;
import codegears.coca.ui.ShopItem;
import codegears.coca.util.MilliSecToHour;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ShopDialog extends Activity implements OnClickListener {

	public static final int RESULT_BUY = 4;
	public static final int RESULT_SELL = 5;
	
	public static final String EXTRA_ITEM_ID = "ITEM_ID";
	
	private ArrayList<Item> shopItem;
	private ArrayList<ItemQuantityPair> backpackItem;
	private ArrayList<ShopItem> shopLayoutList;
	private ArrayList<ShopItem> backpackLayoutList;
	private Player currentPlayer;
	private MyApp app;
	private BuildingManager bManager;
	private ImageButton closeButton;
	private LinearLayout shopLayout;
	private LinearLayout backpackLayout;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.shopdialog);
    
    shopLayoutList = new ArrayList<ShopItem>();
    backpackLayoutList = new ArrayList<ShopItem>();
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
    	newShopItem.setItemId( fetchShopItem.getId() );
    	newShopItem.setItemName(fetchShopItem.getName());
    	newShopItem.setItemPrice(String.valueOf(fetchShopItem.getPrice()));
    	
    	//If unavailable item
    	if( fetchShopItem.getPrice()>currentPlayer.getMoney() ){
    		newShopItem.setUnavailableItem();
    	}else {
    		newShopItem.setClickable( true );
      	newShopItem.setOnClickListener( this );
      	newShopItem.getShopButton().setOnClickListener( this );
    	}
    	
    	//Set Image Item
    	if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_MORNING_GLORY_SEED)){
  			newShopItem.setItemImage(R.drawable.itemid10);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_SEED)){
  			newShopItem.setItemImage(R.drawable.itemid20);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_PUMPKIN_SEED)){
  			newShopItem.setItemImage(R.drawable.itemid30);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_BABY_CORN_SEED)){
  			newShopItem.setItemImage(R.drawable.itemid40);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_SEED)){
  			newShopItem.setItemImage(R.drawable.itemid50);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_CHICKEN_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid60);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_PIG_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid70);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_COW_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid80);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_SHEEP_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid90);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_OSTRICH_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid100);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_FISH_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid110);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_SQUID_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid120);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_SCALLOPS_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid130);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_SHRIMP_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid140);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_OYSTER_BABY)){
  			newShopItem.setItemImage(R.drawable.itemid150);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_WATER)){
  			newShopItem.setItemImage(R.drawable.itemid310);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_SAPPAN_WOOD)){
  			newShopItem.setItemImage(R.drawable.itemid320);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_PELLET_FOOD)){
  			newShopItem.setItemImage(R.drawable.itemid330);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_PEARL)){
  			newShopItem.setItemImage(R.drawable.itemid340);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_GOLD)){
  			newShopItem.setItemImage(R.drawable.itemid350);
  		}else if(fetchShopItem.getId().equals(ItemManager.ITEM_ID_DIAMOND)){
  			newShopItem.setItemImage(R.drawable.itemid360);
  		}
    	
    	if( fetchShopItem.getId().equals(ItemManager.ITEM_ID_WATER)||
    			fetchShopItem.getId().equals(ItemManager.ITEM_ID_SAPPAN_WOOD)||
    			fetchShopItem.getId().equals(ItemManager.ITEM_ID_PELLET_FOOD)||
    			fetchShopItem.getId().equals(ItemManager.ITEM_ID_PEARL)||
    			fetchShopItem.getId().equals(ItemManager.ITEM_ID_GOLD)||
    			fetchShopItem.getId().equals(ItemManager.ITEM_ID_DIAMOND) ){
    		
    		newShopItem.setItemTime("");
    		newShopItem.setItemBackground(R.drawable.plant_shop_nontime);
    	}else{
    		newShopItem.setItemTime(String.valueOf(MilliSecToHour.getConvert(bManager.getMatchBuilding(bManager.getBuildingIdFromItemBuild(fetchShopItem.getId())).getBuildPeriod())));
    	}
    	
    	shopLayout.addView(newShopItem);
    	shopLayoutList.add( newShopItem );
    }
    
    //Build backpack dialog
    for(ItemQuantityPair fetchBackpackItem:backpackItem){
    	if( !fetchBackpackItem.getItemType().equals( ItemManager.ITEM_TYPE_SPECIAL ) &&
    			!fetchBackpackItem.getItemType().equals( ItemManager.ITEM_TYPE_COUPON ) ){
			    	ShopItem newBackpackItem = new ShopItem(this);
			    	newBackpackItem.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) );
			    	newBackpackItem.setItemId( fetchBackpackItem.getItem().getId() );
			    	newBackpackItem.setItemName(fetchBackpackItem.getItem().getName());
			    	newBackpackItem.setItemPrice(String.valueOf(fetchBackpackItem.getItem().getSellPrice()));
			    	newBackpackItem.setItemTime("");
			    	newBackpackItem.setItemBackground(R.drawable.plant_shop_nontime);
			    	newBackpackItem.setShopButton(R.drawable.button_sell);
			    	newBackpackItem.setClickable( true );
			    	newBackpackItem.setOnClickListener( this );
			    	newBackpackItem.getShopButton().setOnClickListener( this );
			  		
			    //Set ItemQuantity
			    if(fetchBackpackItem.getQuantity()>0){
			    	newBackpackItem.setItemTimeColor( this.getResources().getColor( R.color.dark_green ) );
			    	newBackpackItem.setItemTime("x "+String.valueOf(fetchBackpackItem.getQuantity()));
			    }
			    	
			    //Set Image Item
			  	if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_MORNING_GLORY_SEED)){
			  		newBackpackItem.setItemImage(R.drawable.itemid10);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_SEED)){
						newBackpackItem.setItemImage(R.drawable.itemid20);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_PUMPKIN_SEED)){
						newBackpackItem.setItemImage(R.drawable.itemid30);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_BABY_CORN_SEED)){
						newBackpackItem.setItemImage(R.drawable.itemid40);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_SEED)){
						newBackpackItem.setItemImage(R.drawable.itemid50);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_CHICKEN_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid60);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_PIG_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid70);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_COW_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid80);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SHEEP_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid90);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_OSTRICH_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid100);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_FISH_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid110);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SQUID_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid120);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SCALLOPS_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid130);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SHRIMP_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid140);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_OYSTER_BABY)){
						newBackpackItem.setItemImage(R.drawable.itemid150);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_MORNING_GLORY)){
						newBackpackItem.setItemImage(R.drawable.itemid160);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_CHINESE_CABBAGE)){
						newBackpackItem.setItemImage(R.drawable.itemid170);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_PUMPKIN)){
						newBackpackItem.setItemImage(R.drawable.itemid180);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_BABY_CORN)){
						newBackpackItem.setItemImage(R.drawable.itemid190);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS)){
						newBackpackItem.setItemImage(R.drawable.itemid200);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_CHICKEN)){
						newBackpackItem.setItemImage(R.drawable.itemid210);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_PIG)){
						newBackpackItem.setItemImage(R.drawable.itemid220);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_COW)){
						newBackpackItem.setItemImage(R.drawable.itemid230);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SHEEP)){
						newBackpackItem.setItemImage(R.drawable.itemid240);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_OSTRICH)){
						newBackpackItem.setItemImage(R.drawable.itemid250);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_FISH)){
						newBackpackItem.setItemImage(R.drawable.itemid260);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SQUID)){
						newBackpackItem.setItemImage(R.drawable.itemid270);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SCALLOPS)){
						newBackpackItem.setItemImage(R.drawable.itemid280);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SHRIMP)){
						newBackpackItem.setItemImage(R.drawable.itemid290);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_OYSTER)){
						newBackpackItem.setItemImage(R.drawable.itemid300);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_WATER)){
						newBackpackItem.setItemImage(R.drawable.itemid310);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_SAPPAN_WOOD)){
						newBackpackItem.setItemImage(R.drawable.itemid320);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_PELLET_FOOD)){
						newBackpackItem.setItemImage(R.drawable.itemid330);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_PEARL)){
						newBackpackItem.setItemImage(R.drawable.itemid340);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_GOLD)){
						newBackpackItem.setItemImage(R.drawable.itemid350);
					}else if(fetchBackpackItem.getId().equals(ItemManager.ITEM_ID_DIAMOND)){
						newBackpackItem.setItemImage(R.drawable.itemid360);
					}
			    	
			  		backpackLayout.addView(newBackpackItem);
			  		backpackLayoutList.add( newBackpackItem );
			    }
    }
	}

	@Override
	public void onClick(View v) {
		if( v.equals( closeButton ) ){
			this.finish();
		}
		for(ShopItem shopLayout:shopLayoutList){
			if( v.equals( shopLayout.getShopButton() )){
				Intent intent = new Intent();
				intent.putExtra( EXTRA_ITEM_ID, shopLayout.getItemId() );
				this.setResult( RESULT_BUY, intent );
				this.finish();
			}
		}
		for(ShopItem backpackLayout:backpackLayoutList){
			if( v.equals( backpackLayout.getShopButton() )){
				Intent intent = new Intent();
				intent.putExtra( EXTRA_ITEM_ID, backpackLayout.getItemId() );
				this.setResult( RESULT_SELL, intent );
				this.finish();
			}
		}
	}
}
