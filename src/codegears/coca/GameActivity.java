package codegears.coca;

import java.util.HashMap;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
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
import codegears.coca.data.DefaultVar;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;
import codegears.coca.dialog.BuildDialog;
import codegears.coca.dialog.CouponDialog;
import codegears.coca.dialog.PurchaseDialog;
import codegears.coca.dialog.ShopDialog;
import codegears.coca.dialog.SpecialCodeDialog;
import codegears.coca.ui.ButtonListener;
import codegears.coca.ui.ButtonSprite;
import codegears.coca.ui.FarmSprite;
import codegears.coca.ui.FarmTileListener;

import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.widget.Toast;

public class GameActivity extends BaseGameActivity implements ButtonListener,
				IScrollDetectorListener, IPinchZoomDetectorListener, IOnSceneTouchListener, FarmTileListener {

	public static final int REQUEST_PURCHASETILE = 1;
	public static final int REQUEST_BUILD = 2;
	private ZoomCamera mZoomCamera;
	private Scene mMainScene;

	private FarmSprite farmMapSprite;

	private ButtonSprite couponButton;
	private ButtonSprite specialCodeButton;
	private ButtonSprite soundButton;
	private ButtonSprite shopButton;

	private SurfaceScrollDetector mScrollDetector;
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
		final Display display = getWindowManager().getDefaultDisplay();
		int cameraWidth = display.getWidth();
		int cameraHeight = display.getHeight();

		mZoomCamera = new ZoomCamera( 0, 0, cameraWidth, cameraHeight );

		Engine engine = new Engine( new EngineOptions( true, ScreenOrientation.LANDSCAPE,
						new RatioResolutionPolicy( cameraWidth, cameraHeight ), mZoomCamera ) );

		try {
			if ( MultiTouch.isSupported( this ) ) {
				engine.setTouchController( new MultiTouchController() );
			} else {
				Toast.makeText( this,
								"Sorry your device does NOT support MultiTouch!\n\n(No PinchZoom is possible!)",
								Toast.LENGTH_LONG ).show();
			}
		} catch ( final MultiTouchException e ) {
			Toast.makeText(
							this,
							"Sorry your Android Version does NOT support MultiTouch!\n\n(No PinchZoom is possible!)",
							Toast.LENGTH_LONG ).show();
		}

		return engine;
	}

	@Override
	public void onLoadResources() {
		BitmapTextureAtlas mDefaultFarmMapTextureAtlas = new BitmapTextureAtlas( 8, 8 );
		BitmapTextureAtlas mFarmMapTextureAtlas = new BitmapTextureAtlas( 512, 512 );
		BitmapTextureAtlas mFarmMapNotOccupyTextureAtlas = new BitmapTextureAtlas( 512, 512 );
		BitmapTextureAtlas mCouponButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSpecialCodeButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSoundButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mShopButtonTextureAtlas = new BitmapTextureAtlas( 256, 256 );

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath( "gfx/" );
		TextureRegion mDefaultFarmMapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mDefaultFarmMapTextureAtlas, this, "defaultFarmSprite.png", 0, 0 );
		textureCollection.put( DefaultVar.TEXTURE_FARM_DEFAULT, mDefaultFarmMapTextureRegion );
		TextureRegion mFarmMapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mFarmMapTextureAtlas, this, "farm.png", 0, 0 );
		textureCollection.put( DefaultVar.TEXTURE_FARM, mFarmMapTextureRegion );
		TextureRegion mFarmMapNotOccupyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mFarmMapNotOccupyTextureAtlas, this, "farmNotOccupy.png", 0, 0 );
		textureCollection.put( DefaultVar.TEXTURE_FARM_NOTOCCUPY, mFarmMapNotOccupyTextureRegion );
		TextureRegion mCouponButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mCouponButtonTextureAtlas, this, "couponButton.png", 0, 0 );
		textureCollection.put( DefaultVar.TEXTURE_COUPONBUTTON, mCouponButtonTextureRegion );
		TextureRegion mSpecialCodeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSpecialCodeButtonTextureAtlas, this, "specialCodeButton.png", 0, 0 );
		textureCollection.put( DefaultVar.TEXTURE_SPECIALCODEBUTTON, mSpecialCodeTextureRegion );
		TextureRegion mSoundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSoundButtonTextureAtlas, this, "sound.png", 0, 0 );
		textureCollection.put( DefaultVar.TEXTURE_SOUNDBUTTON, mSoundTextureRegion );
		TextureRegion mShopTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mShopButtonTextureAtlas, this, "shop.png", 0, 0 );
		textureCollection.put( DefaultVar.TEXTURE_SHOPBUTTON, mShopTextureRegion );

		this.mEngine.getTextureManager().loadTexture( mFarmMapTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mCouponButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mSpecialCodeButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mSoundButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mShopButtonTextureAtlas );
	}

	@Override
	public Scene onLoadScene() {
		app = (MyApp)this.getApplication();
		currentPlayer = app.getCurrentPlayer();
		
		mEngine.registerUpdateHandler( new FPSLogger() );

		mMainScene = new Scene();
		mMainScene.setBackground( new ColorBackground( 0.09804f, 0.6274f, 0.8784f ) );

		couponButton = new ButtonSprite( 200, 0, textureCollection.get( DefaultVar.TEXTURE_COUPONBUTTON ) );
		specialCodeButton = new ButtonSprite( 300, 5, textureCollection.get( DefaultVar.TEXTURE_SPECIALCODEBUTTON ) );
		soundButton = new ButtonSprite( 390, 5, textureCollection.get( DefaultVar.TEXTURE_SOUNDBUTTON ) );
		shopButton = new ButtonSprite( 0, 0, textureCollection.get( DefaultVar.TEXTURE_SHOPBUTTON ) );
		farmMapSprite = new FarmSprite( textureCollection );
		farmMapSprite.setPlayer( currentPlayer );
		farmMapSprite.setFarmTileListener(this);

		couponButton.setListener( this );
		specialCodeButton.setListener( this );
		soundButton.setListener( this );
		shopButton.setListener( this );

		farmMapSprite.attachChild( shopButton );

		this.mScrollDetector = new SurfaceScrollDetector( this );
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

		//mMainScene.setOnSceneTouchListener(this);
		mMainScene.setTouchAreaBindingEnabled( true );

		return mMainScene;
	}

	@Override
	public void onLoadComplete() {
		/*Tile testTile = MyApp.getCurrentPlayer().getTile().get(0);
		Tile testTile2 = MyApp.getCurrentPlayer().getTile().get(22);
		testTile.setBuildingId("10");
		testTile.setProgress(1);
		testTile.setSupply(2);
		testTile.setExtraId("3");
		testTile.setRottenPeriod(4);
		testTile.setBuilding(MyApp.getBuildingManager().getMatchBuilding("10"));
		
		System.out.println("Before 1 : "+MyApp.getCurrentPlayer().getTile().get(0).getExtraId());
		currentPlayer.addExtraItem(testTile, app.getItemManager().getMatchItem("7020"));
		System.out.println("Quantity : "+currentPlayer.getBackpack().get(currentPlayer.searchBackpackItem("7020")).getQuantity());
		System.out.println("After 1 : "+MyApp.getCurrentPlayer().getTile().get(0).getExtraId());
		
		testTile.setIsOccupy(true);
		System.out.println("Before buildId : "+testTile.getBuildingId());
		currentPlayer.setMoney(0);
		currentPlayer.getBackpack().get(currentPlayer.searchBackpackItem("10")).setItemQuantity(0);
		System.out.println("Before Build : "+currentPlayer.getMoney());
		currentPlayer.build(testTile, MyApp.getBuildingManager().getMatchBuilding("10"));
		System.out.println("After buildId : "+testTile.getBuildingId());
		System.out.println("After Build : "+currentPlayer.getMoney());*/
		//System.out.println(currentPlayer.isMoveable(testTile, testTile2));
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
			this.startActivity( i );
		} else if ( buttonSprite == this.shopButton ) {
			Intent i = new Intent( this, ShopDialog.class);
			this.startActivity( i );
		} 
	}

	@Override
	public void onPinchZoom( PinchZoomDetector arg0, TouchEvent arg1, float pZoomFactor ) {
		this.mZoomCamera.setZoomFactor( this.mPinchZoomStartedCameraZoomFactor * pZoomFactor );
	}

	@Override
	public void onPinchZoomFinished( PinchZoomDetector arg0, TouchEvent arg1, float pZoomFactor ) {
		this.mZoomCamera.setZoomFactor( this.mPinchZoomStartedCameraZoomFactor * pZoomFactor );
	}

	@Override
	public void onPinchZoomStarted( PinchZoomDetector arg0, TouchEvent arg1 ) {
		this.mPinchZoomStartedCameraZoomFactor = this.mZoomCamera.getZoomFactor();
	}

	@Override
	public void onScroll( ScrollDetector arg0, TouchEvent arg1, float pDistanceX, float pDistanceY ) {
		final float zoomFactor = this.mZoomCamera.getZoomFactor();
		this.mZoomCamera.offsetCenter( -pDistanceX / zoomFactor, -pDistanceY / zoomFactor );
	}

	@Override
	public boolean onSceneTouchEvent( Scene arg0, TouchEvent pSceneTouchEvent ) {
		if ( this.mPinchZoomDetector != null ) {
			this.mPinchZoomDetector.onTouchEvent( pSceneTouchEvent );

			if ( this.mPinchZoomDetector.isZooming() ) {
				this.mScrollDetector.setEnabled( false );
			} else {
				if ( pSceneTouchEvent.isActionDown() ) {
					this.mScrollDetector.setEnabled( true );
				}
				this.mScrollDetector.onTouchEvent( pSceneTouchEvent );
			}
		} else {
			this.mScrollDetector.onTouchEvent( pSceneTouchEvent );
		}

		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == REQUEST_PURCHASETILE){
			currentPlayer.purchase( activeTile );
			if(resultCode == Activity.RESULT_OK){
				currentPlayer.purchase( activeTile );
			} 
		} else if(requestCode == REQUEST_BUILD){
			String buildingId = data.getStringExtra( BuildDialog.BUILDING_ID );
			Building building = app.getBuildingManager().getMatchBuilding(buildingId);
			currentPlayer.build( activeTile, building );
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
		this.startActivityForResult( intent, REQUEST_BUILD );
	}

	@Override
	public void onAddItemRequest( Tile data ) {
		//display add item panel
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