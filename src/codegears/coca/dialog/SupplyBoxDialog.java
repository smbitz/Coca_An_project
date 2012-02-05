package codegears.coca.dialog;

import java.text.DecimalFormat;
import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Building;
import codegears.coca.data.BuildingManager;
import codegears.coca.data.Item;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import codegears.coca.util.MilliSecToHour;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SupplyBoxDialog extends Activity implements OnClickListener{
	
	public static final String EXTRA_BUILD_ID = "BUILD_ID";
	public static final String EXTRA_SUPPLY_PERIOD = "SUPPLY_PERIOD";
	public static final String EXTRA_BUILD_PERIOD = "BUILD_PERIOD";
	public static final String EXTRA_ITEM_EXTRA_ID = "ITEM_EXTRA_ID";
	
	public static final int RESULT_SUPPLY = 5;
	public static final int RESULT_EXTRA1 = 6;
	public static final int RESULT_EXTRA2 = 7;
	public static final int RESULT_MOVE = 8;
	
	private ArrayList<Item> supplyItem;
	private Player currentPlayer;
	private MyApp app;
	private String getBuildId;
	private String getSupplyPeriod;
	private String getBuildPeriod;
	private String getItemExtraId;
	
	private ImageButton closeButton;
	private ImageButton supplyItemButton;
	private ImageButton extraItem1Button;
	private ImageButton extraItem2Button;
	private ImageButton moveButton;
	
	private ImageView buildingImage;
	
	private ImageView addItemNone1;
	private ImageView addItemNone2;
	private ImageView addItemNone3;
	
	private TextView buildingName;
	private TextView supplyPeriod;
	private TextView buildPeriod;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.supplydialog);
		
		app = (MyApp)this.getApplication();
		supplyItem = app.getItemManager().getSupplyItem();
		currentPlayer = app.getCurrentPlayer();
		
		//Get intent extra.
		//------- varliable name should not be verb # Chet ---------// 
		getBuildId = getIntent().getExtras().getString( EXTRA_BUILD_ID );
		getSupplyPeriod = getIntent().getExtras().getString( EXTRA_SUPPLY_PERIOD );
		getBuildPeriod = getIntent().getExtras().getString( EXTRA_BUILD_PERIOD );
		getItemExtraId = getIntent().getExtras().getString( EXTRA_ITEM_EXTRA_ID );
		
		closeButton = (ImageButton) findViewById(R.id.supplyCloseButton);
		closeButton.setOnClickListener(this);
		supplyItemButton = (ImageButton) findViewById(R.id.supplyItem);
		supplyItemButton.setOnClickListener(this);
		extraItem1Button = (ImageButton) findViewById(R.id.supplyExtraItem1);
		extraItem1Button.setOnClickListener(this);
		extraItem2Button = (ImageButton) findViewById(R.id.supplyExtraItem2);
		extraItem2Button.setOnClickListener(this);
		moveButton = (ImageButton) findViewById(R.id.supplyMoveItem);
		moveButton.setOnClickListener(this);
		
		buildingImage = (ImageView) findViewById(R.id.supplyBuildingImage);
		
		addItemNone1 = (ImageView) findViewById( R.id.AddSupplyItemNone1 );
		addItemNone2 = (ImageView) findViewById( R.id.AddSupplyItemNone2 );
		addItemNone3 = (ImageView) findViewById( R.id.AddSupplyItemNone3 );
		
		buildingName = (TextView) findViewById(R.id.supplyBuildingName);
		supplyPeriod = (TextView) findViewById(R.id.supplySupplyPeriod);
		buildPeriod = (TextView) findViewById(R.id.supplyBuildPeriod);
		
		buildingName.setTextColor(Color.BLACK);
		supplyPeriod.setTextColor(Color.BLACK);
		buildPeriod.setTextColor(Color.BLACK);
		
		//Set name,supply period,build period
		Building currentBuilding = app.getBuildingManager().getMatchBuilding(getBuildId);
		float supplyProgress = (Float.parseFloat(getSupplyPeriod)/currentBuilding.getSupplyPeriod()) * 100;
		String supplyProgressStr = new DecimalFormat("0.00").format( supplyProgress );
		
		buildingName.setText( currentBuilding.getBuildName() );
		supplyPeriod.setText( supplyProgressStr+" %" );
		buildPeriod.setText(MilliSecToHour.getConvert( Integer.parseInt(getBuildPeriod) ));
		
		//Set building image
		if( getBuildId.equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ){
			buildingImage.setImageResource(R.drawable.itemid5010);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ){
			buildingImage.setImageResource(R.drawable.itemid5020);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_PUMPKIN ) ){
			buildingImage.setImageResource(R.drawable.itemid5030);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_BABY_CORN ) ){
			buildingImage.setImageResource(R.drawable.itemid5040);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
			buildingImage.setImageResource(R.drawable.itemid5050);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_CHICKEN ) ){
			buildingImage.setImageResource(R.drawable.itemid5060);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_PIG ) ){
			buildingImage.setImageResource(R.drawable.itemid5070);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_COW ) ){
			buildingImage.setImageResource(R.drawable.itemid5080);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_SHEEP ) ){
			buildingImage.setImageResource(R.drawable.itemid5090);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
			buildingImage.setImageResource(R.drawable.itemid50100);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_FISH ) ){
			buildingImage.setImageResource(R.drawable.itemid50110);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_SQUID ) ){
			buildingImage.setImageResource(R.drawable.itemid50120);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_SCALLOPS ) ){
			buildingImage.setImageResource(R.drawable.itemid50130);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_SHRIMP ) ){
			buildingImage.setImageResource(R.drawable.itemid50140);
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_OYSTER ) ){
			buildingImage.setImageResource(R.drawable.itemid50150);
		}
		
		//Set supply, extraItem image.
		if( getBuildId.equals( BuildingManager.BUILDING_ID_MORNING_GLORY )||
				getBuildId.equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE )||
				getBuildId.equals( BuildingManager.BUILDING_ID_PUMPKIN )||
				getBuildId.equals( BuildingManager.BUILDING_ID_BABY_CORN )||
				getBuildId.equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
			
			supplyItemButton.setImageResource(R.drawable.image_popup_plant01);
			extraItem1Button.setImageResource(R.drawable.image_popup_plant02);
			extraItem2Button.setImageResource(R.drawable.image_popup_plant03);
			
			if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_WATER )) ){
				addItemNone1.setVisibility( View.VISIBLE );
				supplyItemButton.setOnClickListener(null);
			}
			if( getItemExtraId.equals("NULL") ){
				if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_FERTILIZER_A )) ){
					addItemNone2.setVisibility( View.VISIBLE );
					extraItem1Button.setOnClickListener(null);
				}
				if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_FERTILIZER_B )) ){
					addItemNone3.setVisibility( View.VISIBLE );
					extraItem2Button.setOnClickListener(null);
				}
			}else{
				addItemNone2.setVisibility( View.VISIBLE );
				addItemNone3.setVisibility( View.VISIBLE );
				
				extraItem1Button.setOnClickListener(null);
				extraItem2Button.setOnClickListener(null);
			}
			
		}else	if( getBuildId.equals( BuildingManager.BUILDING_ID_CHICKEN )||
				getBuildId.equals( BuildingManager.BUILDING_ID_PIG )||
				getBuildId.equals( BuildingManager.BUILDING_ID_COW )||
				getBuildId.equals( BuildingManager.BUILDING_ID_SHEEP )||
				getBuildId.equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
			
			supplyItemButton.setImageResource(R.drawable.image_popup_animal01);
			extraItem1Button.setImageResource(R.drawable.image_popup_animal02);
			extraItem2Button.setImageResource(R.drawable.image_popup_animal03);
			
			if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_SAPPAN_WOOD )) ){
				addItemNone1.setVisibility( View.VISIBLE );
				supplyItemButton.setOnClickListener(null);
			}
			if( getItemExtraId.equals("NULL") ){
				if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_VACCINE_A )) ){
					addItemNone2.setVisibility( View.VISIBLE );
					extraItem1Button.setOnClickListener(null);
				}
				if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_VACCINE_B )) ){
					addItemNone3.setVisibility( View.VISIBLE );
					extraItem2Button.setOnClickListener(null);
				}
			}else{
				addItemNone2.setVisibility( View.VISIBLE );
				addItemNone3.setVisibility( View.VISIBLE );
				
				extraItem1Button.setOnClickListener(null);
				extraItem2Button.setOnClickListener(null);
			}
				
		}else if( getBuildId.equals( BuildingManager.BUILDING_ID_FISH )||
							getBuildId.equals( BuildingManager.BUILDING_ID_SQUID )||
							getBuildId.equals( BuildingManager.BUILDING_ID_SCALLOPS )||
							getBuildId.equals( BuildingManager.BUILDING_ID_SHRIMP )||
							getBuildId.equals( BuildingManager.BUILDING_ID_OYSTER ) ){
			
			supplyItemButton.setImageResource(R.drawable.image_popup_seaanimal01);
			extraItem1Button.setImageResource(R.drawable.image_popup_seaanimal02);
			extraItem2Button.setImageResource(R.drawable.image_popup_seaanimal03);
			
			if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_PELLET_FOOD )) ){
				addItemNone1.setVisibility( View.VISIBLE );
				supplyItemButton.setOnClickListener(null);
			}
			if( getItemExtraId.equals("NULL") ){
				if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_MICROORGANISM_A )) ){
					addItemNone2.setVisibility( View.VISIBLE );
					extraItem1Button.setOnClickListener(null);
				}
				if( !(currentPlayer.checkBackpackItemNone( ItemManager.ITEM_ID_MICROORGANISM_B )) ){
					addItemNone3.setVisibility( View.VISIBLE );
					extraItem2Button.setOnClickListener(null);
				}
			}else{
				addItemNone2.setVisibility( View.VISIBLE );
				addItemNone3.setVisibility( View.VISIBLE );
				
				extraItem1Button.setOnClickListener(null);
				extraItem2Button.setOnClickListener(null);
			}
			
		}
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}else if( v.equals(supplyItemButton) ){
			this.setResult( RESULT_SUPPLY );
			this.finish();
		}else if( v.equals(extraItem1Button) ){
			this.setResult( RESULT_EXTRA1 );
			this.finish();			
		}else if( v.equals(extraItem2Button) ){
			this.setResult( RESULT_EXTRA2 );
			this.finish();			
		}else if( v.equals(moveButton) ){
			this.setResult( RESULT_MOVE );
			this.finish();			
		}
	}
}
