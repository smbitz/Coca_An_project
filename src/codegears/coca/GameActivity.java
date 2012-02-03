package codegears.coca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionLibrary;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import codegears.coca.data.Building;
import codegears.coca.data.BuildingManager;
import codegears.coca.data.Item;
import codegears.coca.data.ItemManager;
import codegears.coca.data.PlayerListener;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;
import codegears.coca.dialog.BuildDialog;
import codegears.coca.dialog.CouponDialog;
import codegears.coca.dialog.ItemGetDialog;
import codegears.coca.dialog.NewspaperDialog;
import codegears.coca.dialog.PurchaseDialog;
import codegears.coca.dialog.ShopDialog;
import codegears.coca.dialog.SpecialCodeDialog;
import codegears.coca.dialog.SupplyBoxDialog;
import codegears.coca.dialog.TutorialDialog;
import codegears.coca.ui.AbstractFarmTile;
import codegears.coca.ui.FillItemOnTile;
import codegears.coca.ui.ButtonListener;
import codegears.coca.ui.ButtonSprite;
import codegears.coca.ui.FarmSprite;
import codegears.coca.ui.FarmTileListener;
import codegears.coca.ui.LevelUpPopUp;
import codegears.coca.ui.LevelUpPopUpListener;
import codegears.coca.ui.StatusBar;
import codegears.coca.ui.SupplyOnTile;
import codegears.coca.util.LoadResource;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.MotionEvent;

public class GameActivity extends BaseGameActivity implements ButtonListener,
				IPinchZoomDetectorListener, IOnSceneTouchListener, FarmTileListener, 
				PlayerListener, LevelUpPopUpListener {

	public static final int REQUEST_PURCHASETILE = 1;
	public static final int REQUEST_BUILD = 2;
	public static final int REQUEST_SPECIALCODE = 3;
	public static final int REQUEST_ADD_ITEM = 4;
	public static final int REQUEST_SUPPLY = 5;
	public static final int REQUEST_HARVEST = 6;
	public static final int REQUEST_SHOP = 7;
	public static final int REQUEST_COUPON = 8;
	public static final int REQUEST_TUTORUAL = 9;
	
	public static final int FIX_SCENE_WIDTH = 960;
	public static final int FIX_SCENE_HEIGHT = 640;
	
	public static final int STATE_NORMAL = 1;
	public static final int STATE_MOVE = 2;
	public static final int STATE_LEVELUP = 3;
	
	private static final Float SHOP_SCALE_SIZE = (float) 1.4;
	
	private int state;
	
	private ZoomCamera mZoomCamera;
	private Scene mMainScene;

	private FarmSprite farmMapSprite;

	private ButtonSprite couponButton;
	private ButtonSprite specialCodeButton;
	private ButtonSprite soundButton;
	private ButtonSprite shopButton;
	
	private ButtonSprite mClickImageButton;

	private PinchZoomDetector mPinchZoomDetector;
	private float mPinchZoomStartedCameraZoomFactor;

	private HashMap<String, TextureRegion> textureCollection;
	private HashMap<String, TiledTextureRegion> tiledTextureCollection;
	private HashMap<String, Font> fontCollection;
	private HashMap<String, Music> musicCollection;
	
	private Timer myTimer;
	
	private MyApp app;
	private LevelUpPopUp levelUpPopUp;
	private StatusBar statusBar;
	
	private TimerHandler gameTimerHandler;
	
	private FillItemOnTile newFillItemOnTile;
	
	//---- Data Variable ----//
	private Player currentPlayer;
	private Tile activeTile;
	
	@Override
	public Engine onLoadEngine() {
		textureCollection = new HashMap<String, TextureRegion>();
		fontCollection =  new HashMap<String, Font>();
		musicCollection = new HashMap<String, Music>();
		tiledTextureCollection = new HashMap<String, TiledTextureRegion>();
		int cameraWidth = FIX_SCENE_WIDTH;
		int cameraHeight = FIX_SCENE_HEIGHT;

		mZoomCamera = new ZoomCamera( 0, 0, cameraWidth, cameraHeight );

		Engine engine = new Engine( new EngineOptions( true, ScreenOrientation.LANDSCAPE,
						new FillResolutionPolicy(), mZoomCamera ).setNeedsMusic( true ) );
		
		try {
			if ( MultiTouch.isSupported( this ) ) {
				engine.setTouchController( new MultiTouchController() );
			}
		} catch ( final MultiTouchException e ) {
		}

		return engine;
	}

	@Override
	public void onLoadResources() {
		LoadResource.loadTexture( this, this.mEngine, textureCollection );
		LoadResource.loadTiledTexture(this, this.mEngine, tiledTextureCollection);
		LoadResource.loadFont( this, this.mEngine, fontCollection );
		LoadResource.loadMusic(this, this.mEngine, musicCollection );
	}

	@Override
	public Scene onLoadScene() {
		musicCollection.get( LoadResource.SOUND_BG ).play();

		app = (MyApp)this.getApplication();
		currentPlayer = app.getCurrentPlayer();
		currentPlayer.updateToServer();
		currentPlayer.setPlayerListener( this );
		
		state = STATE_NORMAL;
		
		mEngine.registerUpdateHandler( new FPSLogger() );

		mMainScene = new Scene();
		mMainScene.setBackground( new ColorBackground( 0.09804f, 0.6274f, 0.8784f ) );

		couponButton = new ButtonSprite( 750, 5, textureCollection.get( TextureVar.TEXTURE_COUPONBUTTON ) );
		specialCodeButton = new ButtonSprite( 650, 15, textureCollection.get( TextureVar.TEXTURE_SPECIALCODEBUTTON ) );
		soundButton = new ButtonSprite( 870, 15, textureCollection.get( TextureVar.TEXTURE_SOUNDBUTTON ) );
		shopButton = new ButtonSprite( 1435, 425, textureCollection.get( TextureVar.TEXTURE_SHOPBUTTON ) );
		shopButton.setVisible( false );
		farmMapSprite = new FarmSprite( textureCollection, tiledTextureCollection );
		levelUpPopUp = new LevelUpPopUp( 0, 0, textureCollection, tiledTextureCollection);
		
		shopButton.setScale( SHOP_SCALE_SIZE );
		
		farmMapSprite.setPlayer( currentPlayer );
		farmMapSprite.setFarmTileListener(this);
		couponButton.setListener( this );
		specialCodeButton.setListener( this );
		soundButton.setListener( this );
		shopButton.setListener( this );
		levelUpPopUp.setListener( this );

		statusBar = new StatusBar(0, 0, textureCollection, fontCollection, tiledTextureCollection);
		statusBar.setFarmName( currentPlayer.getName() );
		statusBar.setLevel( currentPlayer.getLevel() );
		statusBar.setExp( currentPlayer.getExpPercent() );
		statusBar.setMoney( currentPlayer.getMoney() );
		
		if ( MultiTouch.isSupportedByAndroidVersion() ) {
			try {
				this.mPinchZoomDetector = new PinchZoomDetector( this );
			} catch ( final MultiTouchException e ) {
				this.mPinchZoomDetector = null;
			}
		} else {
			this.mPinchZoomDetector = null;
		}

		farmMapSprite.attachChild( shopButton );
		farmMapSprite.registerChildTouchArea(mMainScene);
		mMainScene.attachChild( farmMapSprite );
		mMainScene.attachChild( couponButton );
		mMainScene.attachChild( specialCodeButton );
		mMainScene.attachChild( soundButton );
		mMainScene.attachChild( statusBar );
		mMainScene.attachChild( levelUpPopUp );

		mMainScene.registerTouchArea( couponButton );
		mMainScene.registerTouchArea( specialCodeButton );
		mMainScene.registerTouchArea( soundButton );
		mMainScene.registerTouchArea( shopButton );
		mMainScene.registerTouchArea( levelUpPopUp );
		//mMainScene.registerTouchArea( farmMapSprite );

		mMainScene.setOnSceneTouchListener(this);
		mMainScene.setTouchAreaBindingEnabled( false );

		return mMainScene;
	}
	
	@Override
	public void onLoadComplete() {
		currentPlayer.start(System.currentTimeMillis());
		gameTimerHandler = new TimerHandler( 0.1f, new ITimerCallback(){
												public void onTimePassed(final TimerHandler handler){
													System.out.println("Update !!");
													if(state == STATE_LEVELUP){
														//unregister
														farmMapSprite.unRegisterChildTouchArea( mMainScene );
														farmMapSprite.update( mMainScene );
													} else {
														//unregister
														farmMapSprite.unRegisterChildTouchArea( mMainScene );
														farmMapSprite.update( mMainScene );
														//register
														farmMapSprite.registerChildTouchArea( mMainScene );
													}
													currentPlayer.update(System.currentTimeMillis());
												}}
									 		 );
		mMainScene.registerUpdateHandler( gameTimerHandler );
		//open newspaper
		//if(currentPlayer.getIsNew()){
			//open tutorial
			Intent i = new Intent(this, TutorialDialog.class);
			startActivityForResult(i, REQUEST_TUTORUAL);
		//}else
			//open Newpaper
			//Intent newIntent = new Intent(this, NewspaperDialog.class);
			//startActivity( newIntent );
	}

	@Override
	public void onClickDown( ButtonSprite buttonSprite ) {
		musicCollection.get( LoadResource.SOUND_BUTTON ).play();
		if ( buttonSprite == this.couponButton ) {
			mClickImageButton = new ButtonSprite( 750, 5, textureCollection.get( TextureVar.TEXTURE_COUPONBUTTON ) );
			mClickImageButton.setColor(0, 0, 0, (float) 0.3);
			mMainScene.attachChild( mClickImageButton );
		} else if ( buttonSprite == this.specialCodeButton ) {
			mClickImageButton = new ButtonSprite( 650, 15, textureCollection.get( TextureVar.TEXTURE_SPECIALCODEBUTTON ) );
			mClickImageButton.setColor(0, 0, 0, (float) 0.3 );
			mMainScene.attachChild( mClickImageButton );
		} else if ( buttonSprite == this.shopButton ) {
			buttonSprite.setVisible( true );
		} else if ( buttonSprite == this.soundButton ) {
			
		}
	}

	@Override
	public void onClickUp(ButtonSprite buttonSprite) {
		if ( buttonSprite == this.couponButton ) {
			this.runOnUpdateThread( new Runnable() {
					@Override
					public void run() {
						mMainScene.detachChild( mClickImageButton );
					}
			} );
			Intent i = new Intent( this, CouponDialog.class );
			this.startActivityForResult( i, REQUEST_COUPON );
		} else if ( buttonSprite == this.specialCodeButton ) {
			this.runOnUpdateThread( new Runnable() {
					@Override
					public void run() {
						mMainScene.detachChild( mClickImageButton );
					}
			} );
			Intent i = new Intent( this, SpecialCodeDialog.class );
			this.startActivityForResult( i, REQUEST_SPECIALCODE );
		} else if ( buttonSprite == this.shopButton ) {
			buttonSprite.setVisible( false );
			Intent i = new Intent( this, ShopDialog.class);
			this.startActivityForResult( i, REQUEST_SHOP );
		} else if ( buttonSprite == this.soundButton ) {
			
		}
	}
	
	@Override
	public void onPinchZoom( PinchZoomDetector arg0, TouchEvent arg1, float pZoomFactor ) {
		farmMapSprite.pinchZoom(pZoomFactor);
	}

	@Override
	public void onPinchZoomFinished( PinchZoomDetector arg0, TouchEvent arg1, float pZoomFactor ) {
		farmMapSprite.pinchZoom(pZoomFactor);
	}

	@Override
	public void onPinchZoomStarted( PinchZoomDetector arg0, TouchEvent arg1 ) {
		farmMapSprite.pinchStarted();
	}

	@Override
	public boolean onSceneTouchEvent( Scene arg0, TouchEvent pSceneTouchEvent ) {
		if ( this.mPinchZoomDetector != null ) {
			this.mPinchZoomDetector.onTouchEvent( pSceneTouchEvent );
			
		}
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == REQUEST_PURCHASETILE){
			if(resultCode == Activity.RESULT_OK){
				currentPlayer.purchase( activeTile );
				//update farmSprite
				
				//update player to server
				currentPlayer.updateToServer();
			} 
		} else if(requestCode == REQUEST_BUILD){
			if(resultCode == Activity.RESULT_OK){
				String itemForBuildId = data.getStringExtra( BuildDialog.ITEM_ID );
				String buildingId = app.getBuildingManager().getBuildingIdFromItemBuild( itemForBuildId );
				Building building = app.getBuildingManager().getMatchBuilding(buildingId);
				currentPlayer.build( activeTile, building );
				//farmMapSprite.update( mMainScene );
				currentPlayer.updateToServer();
			}
		} else if(requestCode == REQUEST_SPECIALCODE){
			if(resultCode == Activity.RESULT_OK){
				String itemId = data.getStringExtra( SpecialCodeDialog.PUT_EXTRA_ITEM_ID );
				int itemQuantity = data.getIntExtra( SpecialCodeDialog.PUT_EXTRA_ITEM_QUANTITY, 0 );
				Intent intent = new Intent(this, ItemGetDialog.class);
				intent.putExtra( SpecialCodeDialog.PUT_EXTRA_ITEM_ID, itemId );
				intent.putExtra( SpecialCodeDialog.PUT_EXTRA_ITEM_QUANTITY, itemQuantity );
				this.startActivity( intent );	
				//add item to player
				currentPlayer.addItemToBackpack(itemId, itemQuantity);
				
				//update player to server
				currentPlayer.updateToServer();
			}
		} else if(requestCode == REQUEST_ADD_ITEM){
			if(resultCode == SupplyBoxDialog.RESULT_SUPPLY){
				currentPlayer.addSupply(activeTile);
			} else if(resultCode == SupplyBoxDialog.RESULT_EXTRA1){
				currentPlayer.addExtraItem1(activeTile);
			} else if(resultCode == SupplyBoxDialog.RESULT_EXTRA2){
				currentPlayer.addExtraItem2(activeTile);
			} else if(resultCode == SupplyBoxDialog.RESULT_MOVE){
				//enter move state
				state = STATE_MOVE;
				farmMapSprite.setMoveState();
			}
			
			//update player to server
			currentPlayer.updateToServer();
		} else if(requestCode == REQUEST_SHOP){
			if(resultCode == ShopDialog.RESULT_BUY){
				String itemId = data.getStringExtra( ShopDialog.EXTRA_ITEM_ID );
				currentPlayer.buy( itemId, 1 );
				
				//back to shop dialog
				Intent i = new Intent( this, ShopDialog.class);
				this.startActivityForResult( i, REQUEST_SHOP );
			} else if(resultCode == ShopDialog.RESULT_SELL){
				String itemId = data.getStringExtra( ShopDialog.EXTRA_ITEM_ID );
				currentPlayer.sell( itemId, 1 );
				
				//back to shop dialog
				Intent i = new Intent( this, ShopDialog.class);
				this.startActivityForResult( i, REQUEST_SHOP );
			}
			
			//update player to server
			currentPlayer.updateToServer();
		} else if(requestCode == REQUEST_TUTORUAL){
			if(resultCode == TutorialDialog.TUTORIAL_ACTIVITY_CLOSE){
				Intent newIntent = new Intent(this, NewspaperDialog.class);
				startActivity( newIntent );
			}
		}
	}

	@Override
	public void onPurchaseRequest( Tile data ) {
		activeTile = data;
		Intent intent = new Intent(this, PurchaseDialog.class);
		this.startActivityForResult( intent, REQUEST_PURCHASETILE );
	}

	@Override
	public void onBuildRequest( Tile data ) {
		activeTile = data;
		Intent intent = new Intent(this, BuildDialog.class);
		intent.putExtra( BuildDialog.EXTRA_LAND_TYPE, activeTile.getLandType() );
		this.startActivityForResult( intent, REQUEST_BUILD );
	}

	@Override
	public void onAddItemRequest( Tile data ) {
		activeTile = data;
		Intent intent = new Intent(this, SupplyBoxDialog.class);
		intent.putExtra( SupplyBoxDialog.EXTRA_BUILD_ID, activeTile.getBuildingId() );
		intent.putExtra( SupplyBoxDialog.EXTRA_SUPPLY_PERIOD, String.valueOf( activeTile.getSupply() ));
		intent.putExtra( SupplyBoxDialog.EXTRA_BUILD_PERIOD, String.valueOf( activeTile.getProgress() ));
		intent.putExtra( SupplyBoxDialog.EXTRA_ITEM_EXTRA_ID, String.valueOf( activeTile.getExtraId() ));
		this.startActivityForResult( intent, REQUEST_ADD_ITEM );
	}

	@Override
	public void onSupplyRequest( Tile data, float tileX, float tileY ) {
		TextureRegion supplyTexture = null;
		
		//Clear old fill supply image
		this.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				farmMapSprite.detachChild( newFillItemOnTile );
			};
		}
		);
		
		//Add supply to player 
		currentPlayer.addSupply(data);
		
		//Update farm
		this.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				farmMapSprite.update( mMainScene );
			};
		}
		);
		
		//display supply animation
		if( data.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ||
				data.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ||
				data.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ||
				data.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ||
				data.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
			supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_PLANT_01 );
		}else if( data.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
							data.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
							data.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
							data.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
							data.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
			supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_ANIMAL_01 );
		}else if( data.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
							data.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
							data.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
							data.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ||
							data.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ){
			supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_SEA_ANIMAL_01 );
		}
		newFillItemOnTile = new FillItemOnTile(tileX, tileY, supplyTexture);
		//Sprite newIconPlus = new Sprite(tileX, tileY, textureCollection.get( TextureVar.TEXTURE_SPECIALCODEBUTTON ));
		//farmMapSprite.attachChild( newIconPlus );
		farmMapSprite.attachChild( newFillItemOnTile );
		
		//farmMapSprite.update( mMainScene );
		//System.out.println("Yess !!");
		
		//update player to server
		//currentPlayer.updateToServer();
	}

	@Override
	public void onHarvestRequest( Tile data ) {
		currentPlayer.harvest(data);
		//display harvest animation
		
		//update player to server
		currentPlayer.updateToServer();
		
		//Update farm
		this.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				farmMapSprite.update( mMainScene );
			};
		}
		);
		
		//Harvest Animation
	}

	@Override
	public void onMoveRequest( Tile data ) {
		state = STATE_NORMAL;
		farmMapSprite.setNormalState();
		currentPlayer.swap(activeTile, data);
		
		///Update farm
		farmMapSprite.update( mMainScene );
	}

	@Override
	public void onLevelUp(Player player) {
		musicCollection.get( LoadResource.SOUND_LEVEL_UP ).play();
		this.state = STATE_LEVELUP;
		
		this.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				farmMapSprite.unRegisterChildTouchArea( mMainScene );
				mMainScene.unregisterTouchArea( shopButton );
				mMainScene.unregisterTouchArea( couponButton );
				mMainScene.unregisterTouchArea( soundButton );
				mMainScene.unregisterTouchArea( specialCodeButton );
			}
		} );
		
		statusBar.setLevel( player.getLevel() );
		statusBar.setMoney( player.getMoney() );
		levelUpPopUp.setLevel( player.getLevel() );
		levelUpPopUp.setVisible( mMainScene, true );
	}

	@Override
	public void onExpUp(Player player) {
		statusBar.setMoney( player.getMoney() );
	}

	@Override
	public void onLevelPopUpCloseButtonClick() {
		musicCollection.get( LoadResource.SOUND_CHOOSE_CLICK ).play();
		this.state = STATE_NORMAL;
		farmMapSprite.registerChildTouchArea( mMainScene );
		mMainScene.registerTouchArea( shopButton );
		mMainScene.registerTouchArea( couponButton );
		mMainScene.registerTouchArea( soundButton );
		mMainScene.registerTouchArea( specialCodeButton );
		levelUpPopUp.setVisible( mMainScene, false );
	}

}