package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.BuildingManager;
import codegears.coca.data.Item;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;
import codegears.coca.ui.BuildItem;
import codegears.coca.util.MilliSecToHour;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class BuildDialog extends Activity implements OnClickListener {
	
	public static final String ITEM_ID = "ITEM_ID";
	public static final String EXTRA_LAND_TYPE = "tileLandType";
	
	private ArrayList<Item> itemList;
	private ArrayList<BuildItem> buildItemList;
	private Player currentPlayer;
	private MyApp app;
	private BuildingManager bManager;
	
	private ImageButton closeButton;
	private LinearLayout itemLayout;
	private String getLandType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.builddialog);
		app = (MyApp)this.getApplication();
		bManager = app.getBuildingManager();
		itemList = app.getItemManager().getBuildItem();
		buildItemList = new ArrayList<BuildItem>();
		currentPlayer = app.getCurrentPlayer();
		
		closeButton = (ImageButton) this.findViewById(R.id.plantCloseButton);
		closeButton.setOnClickListener(this);
		itemLayout = (LinearLayout)this.findViewById( R.id.plantItem );
		
		//Check land type
		getLandType = getIntent().getExtras().getString( EXTRA_LAND_TYPE );
		
	  //Set Item on item layout.
		if( getLandType.equals(Tile.LAND_TYPE_LAND) ){
			for(Item fetachItem:itemList){
				if( fetachItem.getId().equals(ItemManager.ITEM_ID_MORNING_GLORY_SEED)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_SEED)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_PUMPKIN_SEED)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_BABY_CORN_SEED)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_SEED)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_CHICKEN_BABY)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_PIG_BABY)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_COW_BABY)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_SHEEP_BABY)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_OSTRICH_BABY) ){
					
							BuildItem newBuildItem = new BuildItem(this);
							this.setBuildItemObject( newBuildItem, fetachItem );
								
							if(fetachItem.getId().equals(ItemManager.ITEM_ID_MORNING_GLORY_SEED)){
								newBuildItem.setItemImage(R.drawable.itemid10);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_SEED)){
								newBuildItem.setItemImage(R.drawable.itemid20);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_PUMPKIN_SEED)){
								newBuildItem.setItemImage(R.drawable.itemid30);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_BABY_CORN_SEED)){
								newBuildItem.setItemImage(R.drawable.itemid40);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_SEED)){
								newBuildItem.setItemImage(R.drawable.itemid50);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_CHICKEN_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid60);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_PIG_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid70);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_COW_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid80);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_SHEEP_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid90);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_OSTRICH_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid100);
							}
							
							itemLayout.addView(newBuildItem);
							buildItemList.add( newBuildItem );
				}
			}
		}else if( getLandType.equals(Tile.LAND_TYPE_SEA) ){
			for(Item fetachItem:itemList){
				if( fetachItem.getId().equals(ItemManager.ITEM_ID_FISH_BABY)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_SQUID_BABY)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_SCALLOPS_BABY)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_SHRIMP_BABY)||
						fetachItem.getId().equals(ItemManager.ITEM_ID_OYSTER_BABY) ){
					
							BuildItem newBuildItem = new BuildItem(this);
							this.setBuildItemObject( newBuildItem, fetachItem );
							
							if(fetachItem.getId().equals(ItemManager.ITEM_ID_FISH_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid110);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_SQUID_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid120);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_SCALLOPS_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid130);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_SHRIMP_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid140);
							}else if(fetachItem.getId().equals(ItemManager.ITEM_ID_OYSTER_BABY)){
								newBuildItem.setItemImage(R.drawable.itemid150);
							}
							
							itemLayout.addView(newBuildItem);
							buildItemList.add( newBuildItem );
				}
			}
		}
	}

	private void setBuildItemObject(BuildItem setBItem, Item setItem){
		setBItem.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) );
		setBItem.setItemName(setItem.getName());
		setBItem.setItemId( setItem.getId() );
		setBItem.setItemTime(String.valueOf(MilliSecToHour.getConvert(bManager.getMatchBuilding(bManager.getBuildingIdFromItemBuild(setItem.getId())).getBuildPeriod())));
		
		//Set price or quantity
		int itemPositionInBackpack = currentPlayer.searchBackpackItem(setItem.getId());
		if(itemPositionInBackpack>=0){
			int foundItemQuantity = currentPlayer.getBackpack().get(itemPositionInBackpack).getQuantity();
			if( foundItemQuantity>0 ){
				setBItem.setItemTimeColor( this.getResources().getColor( R.color.dark_green ) );
				setBItem.setItemPrice("x "+String.valueOf(foundItemQuantity));
			}else {
				setBItem.setItemPrice(String.valueOf(setItem.getPrice()));
			}
		}else{
			setBItem.setItemPrice(String.valueOf(setItem.getPrice()));
		}
		
		//If unavailable item
		if( setItem.getPrice()>currentPlayer.getMoney() &&
				itemPositionInBackpack<=0 ){
			setBItem.setUnavailableItem();
		}else{
			setBItem.setOnClickListener( this );
		}
	}
	
	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		} else {
			for(BuildItem item:buildItemList){
				if(v.equals( item )){
					Intent intent = new Intent();
					intent.putExtra( ITEM_ID, item.getItemId() );
					this.setResult( Activity.RESULT_OK, intent );
					this.finish();
				}
			}
		}
	}
	
}