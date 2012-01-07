package codegears.coca;

import java.util.HashMap;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
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
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionLibrary;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import codegears.coca.data.Building;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;
import codegears.coca.dialog.BuildDialog;
import codegears.coca.dialog.CouponDialog;
import codegears.coca.dialog.ItemGetDialog;
import codegears.coca.dialog.PurchaseDialog;
import codegears.coca.dialog.ShopDialog;
import codegears.coca.dialog.SpecialCodeDialog;
import codegears.coca.dialog.SupplyBoxDialog;
import codegears.coca.dialog.TutorialDialog;
import codegears.coca.ui.ButtonListener;
import codegears.coca.ui.ButtonSprite;
import codegears.coca.ui.FarmSprite;
import codegears.coca.ui.FarmTileListener;

import android.app.Activity;
import android.content.Intent;

public class GameActivity extends BaseGameActivity implements ButtonListener,
				IPinchZoomDetectorListener, IOnSceneTouchListener, FarmTileListener {

	public static final int REQUEST_PURCHASETILE = 1;
	public static final int REQUEST_BUILD = 2;
	public static final int REQUEST_SPECIALCODE = 3;
	public static final int REQUEST_ADD_ITEM = 3;
	public static final int REQUEST_SUPPLY = 4;
	public static final int REQUEST_HARVEST = 5;
	public static final int REQUEST_SHOP = 6;
	
	private static final int FIX_SCENE_WIDTH = 480;
	private static final int FIX_SCENE_HEIGHT = 320;
		
	private ZoomCamera mZoomCamera;
	private Scene mMainScene;

	private FarmSprite farmMapSprite;

	private ButtonSprite couponButton;
	private ButtonSprite specialCodeButton;
	private ButtonSprite soundButton;
	private ButtonSprite shopButton;

	private PinchZoomDetector mPinchZoomDetector;
	private float mPinchZoomStartedCameraZoomFactor;

	private HashMap<String, TextureRegion> textureCollection;
	
	private MyApp app;
	//---- Data Variable ----//
	private Player currentPlayer;
	private Tile activeTile;
	
	@Override
	public Engine onLoadEngine() {
		textureCollection = new HashMap<String, TextureRegion>();
		int cameraWidth = FIX_SCENE_WIDTH;
		int cameraHeight = FIX_SCENE_HEIGHT;

		mZoomCamera = new ZoomCamera( 0, 0, cameraWidth, cameraHeight );

		Engine engine = new Engine( new EngineOptions( true, ScreenOrientation.LANDSCAPE,
						new FillResolutionPolicy(), mZoomCamera ) );
		
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
		BitmapTextureAtlas mDefaultFarmMapTextureAtlas = new BitmapTextureAtlas( 512, 512 );
		BitmapTextureAtlas mFarmMapTextureAtlas = new BitmapTextureAtlas( 512, 512 );
		BitmapTextureAtlas mFarmMapNotOccupyTextureAtlas = new BitmapTextureAtlas( 32, 32 );
		BitmapTextureAtlas mCouponButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSpecialCodeButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSoundButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mShopButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath( "gfx/" );
		TextureRegion mDefaultFarmMapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mDefaultFarmMapTextureAtlas, this, "defaultFarmSprite.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM_DEFAULT, mDefaultFarmMapTextureRegion );
		TextureRegion mFarmMapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mFarmMapTextureAtlas, this, "farm.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM, mFarmMapTextureRegion );
		TextureRegion mFarmMapNotOccupyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mFarmMapNotOccupyTextureAtlas, this, "farmNotOccupy.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM_NOTOCCUPY, mFarmMapNotOccupyTextureRegion );
		TextureRegion mCouponButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mCouponButtonTextureAtlas, this, "couponButton.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_COUPONBUTTON, mCouponButtonTextureRegion );
		TextureRegion mSpecialCodeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSpecialCodeButtonTextureAtlas, this, "specialCodeButton.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SPECIALCODEBUTTON, mSpecialCodeTextureRegion );
		TextureRegion mSoundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSoundButtonTextureAtlas, this, "sound.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SOUNDBUTTON, mSoundTextureRegion );
		TextureRegion mShopTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mShopButtonTextureAtlas, this, "shop.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SHOPBUTTON, mShopTextureRegion );

		this.mEngine.getTextureManager().loadTexture( mDefaultFarmMapTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mFarmMapTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mFarmMapNotOccupyTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mCouponButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mSpecialCodeButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mSoundButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mShopButtonTextureAtlas );
	}

	@Override
	public Scene onLoadScene() {
		app = (MyApp)this.getApplication();
		currentPlayer = app.getCurrentPlayer();
		System.out.println("Start");
		currentPlayer.updateToServer();
		
		mEngine.registerUpdateHandler( new FPSLogger() );

		mMainScene = new Scene();
		mMainScene.setBackground( new ColorBackground( 0.09804f, 0.6274f, 0.8784f ) );

		couponButton = new ButtonSprite( 200, 0, textureCollection.get( TextureVar.TEXTURE_COUPONBUTTON ) );
		specialCodeButton = new ButtonSprite( 300, 5, textureCollection.get( TextureVar.TEXTURE_SPECIALCODEBUTTON ) );
		soundButton = new ButtonSprite( 390, 5, textureCollection.get( TextureVar.TEXTURE_SOUNDBUTTON ) );
		shopButton = new ButtonSprite( 0, -120, textureCollection.get( TextureVar.TEXTURE_SHOPBUTTON ) );
		farmMapSprite = new FarmSprite( textureCollection );
		farmMapSprite.setPlayer( currentPlayer );
		farmMapSprite.setFarmTileListener(this);

		couponButton.setListener( this );
		specialCodeButton.setListener( this );
		soundButton.setListener( this );
		shopButton.setListener( this );

		farmMapSprite.attachChild( shopButton );

		if ( MultiTouch.isSupportedByAndroidVersion() ) {
			try {
				this.mPinchZoomDetector = new PinchZoomDetector( this );
			} catch ( final MultiTouchException e ) {
				this.mPinchZoomDetector = null;
			}
		} else {
			this.mPinchZoomDetector = null;
		}

		farmMapSprite.registerChildTouchArea(mMainScene);
		mMainScene.attachChild( farmMapSprite );
		mMainScene.attachChild( couponButton );
		mMainScene.attachChild( specialCodeButton );
		mMainScene.attachChild( soundButton );

		mMainScene.registerTouchArea( couponButton );
		mMainScene.registerTouchArea( specialCodeButton );
		mMainScene.registerTouchArea( soundButton );
		mMainScene.registerTouchArea( shopButton );
		mMainScene.registerTouchArea( farmMapSprite );

		mMainScene.setOnSceneTouchListener(this);
		mMainScene.setTouchAreaBindingEnabled( false );

		return mMainScene;
	}

	@Override
	public void onLoadComplete() {
		//open newspaper
		//if(currentPlayer.getIsNew()){
			//open tutorial
			Intent i = new Intent(this, TutorialDialog.class);
			startActivity(i);
		//}
	}

	public HashMap<String, TextureRegion> getTextureCollection(){
		return this.textureCollection;
	}
	
	@Override
	public void onClick( ButtonSprite buttonSprite ) {
		if ( buttonSprite == this.couponButton ) {
			Intent i = new Intent( this, CouponDialog.class );
			this.startActivity( i );
		} else if ( buttonSprite == this.specialCodeButton ) {
			Intent i = new Intent( this, SpecialCodeDialog.class );
			this.startActivityForResult( i, REQUEST_SPECIALCODE );
		} else if ( buttonSprite == this.shopButton ) {
			Intent i = new Intent( this, ShopDialog.class);
			this.startActivityForResult( i, REQUEST_SHOP );
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
			} 
		} else if(requestCode == REQUEST_BUILD){
			if(resultCode == Activity.RESULT_OK){
				String itemForBuildId = data.getStringExtra( BuildDialog.ITEM_ID );
				String buildingId = app.getBuildingManager().getBuildingIdFromItemBuild( itemForBuildId );
				Building building = app.getBuildingManager().getMatchBuilding(buildingId);
				currentPlayer.build( activeTile, building );
				//update farmSprite
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
			}
		} else if(requestCode == REQUEST_ADD_ITEM){
			if(resultCode == SupplyBoxDialog.RESULT_SUPPLY){
				currentPlayer.supply(activeTile);
			} else if(resultCode == SupplyBoxDialog.RESULT_EXTRA1){
				currentPlayer.extra1(activeTile);
			} else if(resultCode == SupplyBoxDialog.RESULT_EXTRA2){
				currentPlayer.extra2(activeTile);
			} else if(resultCode == SupplyBoxDialog.RESULT_MOVE){
				//enter move state
			}
		} else if(requestCode == REQUEST_SHOP){
			if(resultCode == ShopDialog.RESULT_BUY){
				String itemId = data.getStringExtra( ShopDialog.EXTRA_ITEM_ID );
				currentPlayer.buy( itemId, 1 );
			} else if(resultCode == ShopDialog.RESULT_SELL){
				String itemId = data.getStringExtra( ShopDialog.EXTRA_ITEM_ID );
				currentPlayer.sell( itemId, 1 );
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
		this.startActivityForResult( intent, REQUEST_ADD_ITEM );
	}

	@Override
	public void onSupplyRequest( Tile data ) {
		currentPlayer.addSupply(data);
		//display supply animation
	}

	@Override
	public void onHarvestRequest( Tile data ) {
		currentPlayer.harvest(data);
		//display harvest animation
	}

}