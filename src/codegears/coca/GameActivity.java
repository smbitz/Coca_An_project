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
	public static final int REQUEST_ADD_ITEM = 4;
	public static final int REQUEST_SUPPLY = 5;
	public static final int REQUEST_HARVEST = 6;
	public static final int REQUEST_SHOP = 7;
	public static final int REQUEST_COUPON = 8;
	
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
		BitmapTextureAtlas mDefaultFarmMapTextureAtlas = new BitmapTextureAtlas( 2048, 2048 );
		BitmapTextureAtlas mFarmMapTextureAtlas = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas mFarmMapNotOccupyTextureAtlas = new BitmapTextureAtlas( 32, 32 );
		BitmapTextureAtlas mCouponButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSpecialCodeButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSoundButtonTextureAtlas = new BitmapTextureAtlas( 128, 64 );
		BitmapTextureAtlas mShopButtonTextureAtlas = new BitmapTextureAtlas( 1024, 256 );
		
		//Texture tile.
		//Vege
		BitmapTextureAtlas tileAtlasMorningGlory01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasMorningGlory02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasMorningGlory03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasMorningGlory04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasChineseCabbage01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChineseCabbage02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChineseCabbage03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChineseCabbage04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasPumpkin01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPumpkin02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPumpkin03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPumpkin04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasBabyCorn01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasBabyCorn02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasBabyCorn03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasBabyCorn04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasStrawMushrooms01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasStrawMushrooms02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasStrawMushrooms03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasStrawMushrooms04 = new BitmapTextureAtlas( 512, 256 );
		
		//Land Animal
		BitmapTextureAtlas tileAtlasCorralLandAnimal = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasChicken01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasChicken02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasChicken03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasChicken04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas tileAtlasPig01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasPig02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasPig03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasPig04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas tileAtlasCow01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasCow02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasCow03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasCow04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas tileAtlasSheep01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasSheep02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasSheep03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasSheep04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas tileAtlasOstrich01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasOstrich02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasOstrich03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasOstrich04 = new BitmapTextureAtlas( 128, 128 );
		
		//Sea Animal
		BitmapTextureAtlas tileAtlasCorralSeaAnimal = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasFish01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasFish02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasFish03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasFish04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas tileAtlasSquid01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasSquid02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasSquid03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasSquid04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas tileAtlasScallops01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasScallops02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasScallops03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasScallops04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas tileAtlasShrimp01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasShrimp02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasShrimp03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasShrimp04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas tileAtlasOyster01 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasOyster02 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasOyster03 = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas tileAtlasOyster04 = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath( "gfx/" );
		TextureRegion mDefaultFarmMapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mDefaultFarmMapTextureAtlas, this, "defaultFarmSprite.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM_MAP_DEFAULT, mDefaultFarmMapTextureRegion );
		TextureRegion mFarmMapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mFarmMapTextureAtlas, this, "T08.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM, mFarmMapTextureRegion );
		TextureRegion mFarmMapNotOccupyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mFarmMapNotOccupyTextureAtlas, this, "farmNotOccupy.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM_NOTOCCUPY, mFarmMapNotOccupyTextureRegion );
		TextureRegion mCouponButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mCouponButtonTextureAtlas, this, "button_coupon.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_COUPONBUTTON, mCouponButtonTextureRegion );
		TextureRegion mSpecialCodeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSpecialCodeButtonTextureAtlas, this, "button_code.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SPECIALCODEBUTTON, mSpecialCodeTextureRegion );
		TextureRegion mSoundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSoundButtonTextureAtlas, this, "button_opensound.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SOUNDBUTTON, mSoundTextureRegion );
		TextureRegion mShopTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mShopButtonTextureAtlas, this, "map_market.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SHOPBUTTON, mShopTextureRegion );
		
	  //Texture tile.
		//Vege
		TextureRegion tileRegionMorningGlory01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasMorningGlory01, this, "T01_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_MORNING_GLORY_01 ,tileRegionMorningGlory01 );
		TextureRegion tileRegionMorningGlory02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasMorningGlory02, this, "T01_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_MORNING_GLORY_02 ,tileRegionMorningGlory02 );
		TextureRegion tileRegionMorningGlory03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasMorningGlory03, this, "T01_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_MORNING_GLORY_03 ,tileRegionMorningGlory03 );
		TextureRegion tileRegionMorningGlory04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasMorningGlory04, this, "T01_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_MORNING_GLORY_04 ,tileRegionMorningGlory04 );
		
		TextureRegion tileRegionChineseCabbage01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChineseCabbage01, this, "T02_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_01 ,tileRegionChineseCabbage01 );
		TextureRegion tileRegionChineseCabbage02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChineseCabbage02, this, "T02_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_02 ,tileRegionChineseCabbage02 );
		TextureRegion tileRegionChineseCabbage03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChineseCabbage03, this, "T02_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_03 ,tileRegionChineseCabbage03 );
		TextureRegion tileRegionChineseCabbage04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChineseCabbage04, this, "T02_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_04 ,tileRegionChineseCabbage04 );
		
		TextureRegion tileRegionPumpkin01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPumpkin01, this, "T03_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PUMPKIN_01 ,tileRegionPumpkin01 );
		TextureRegion tileRegionPumpkin02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPumpkin02, this, "T03_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PUMPKIN_02 ,tileRegionPumpkin02 );
		TextureRegion tileRegionPumpkin03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPumpkin03, this, "T03_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PUMPKIN_03 ,tileRegionPumpkin03 );
		TextureRegion tileRegionPumpkin04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPumpkin04, this, "T03_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PUMPKIN_04 ,tileRegionPumpkin04 );
		
		TextureRegion tileRegionBabyCorn01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasBabyCorn01, this, "T04_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_BABY_CORN_01 ,tileRegionBabyCorn01 );
		TextureRegion tileRegionBabyCorn02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasBabyCorn02, this, "T04_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_BABY_CORN_02 ,tileRegionBabyCorn02 );
		TextureRegion tileRegionBabyCorn03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasBabyCorn03, this, "T04_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_BABY_CORN_03 ,tileRegionBabyCorn03 );
		TextureRegion tileRegionBabyCorn04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasBabyCorn04, this, "T04_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_BABY_CORN_04 ,tileRegionBabyCorn04 );
		
		TextureRegion tileRegionStrawMushrooms01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasStrawMushrooms01, this, "T05_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_01 ,tileRegionStrawMushrooms01 );
		TextureRegion tileRegionStrawMushrooms02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasStrawMushrooms02, this, "T05_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_02 ,tileRegionStrawMushrooms02 );
		TextureRegion tileRegionStrawMushrooms03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasStrawMushrooms03, this, "T05_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_03 ,tileRegionStrawMushrooms03 );
		TextureRegion tileRegionStrawMushrooms04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasStrawMushrooms04, this, "T05_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_04 ,tileRegionStrawMushrooms04 );
		
		//Land Animal
		TextureRegion tileRegionCorralLandAnimal = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCorralLandAnimal, this, "T06.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CORRAL_LAND_ANIMAL ,tileRegionCorralLandAnimal );
		
		TextureRegion tileRegionChicken01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChicken01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHIKEN_01 ,tileRegionChicken01 );
		TextureRegion tileRegionChicken02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChicken02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHIKEN_02 ,tileRegionChicken02 );
		TextureRegion tileRegionChicken03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChicken03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHIKEN_03 ,tileRegionChicken03 );
		TextureRegion tileRegionChicken04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChicken04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHIKEN_04 ,tileRegionChicken04 );
		
		TextureRegion tileRegionPig01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPig01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PIG_01 ,tileRegionPig01 );
		TextureRegion tileRegionPig02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPig02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PIG_02 ,tileRegionPig02 );
		TextureRegion tileRegionPig03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPig03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PIG_03 ,tileRegionPig03 );
		TextureRegion tileRegionPig04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPig04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PIG_04 ,tileRegionPig04 );
		
		TextureRegion tileRegionCow01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCow01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_COW_01 ,tileRegionCow01 );
		TextureRegion tileRegionCow02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCow02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_COW_02 ,tileRegionCow02 );
		TextureRegion tileRegionCow03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCow03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_COW_03 ,tileRegionCow03 );
		TextureRegion tileRegionCow04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCow04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_COW_04 ,tileRegionCow04 );
		
		TextureRegion tileRegionSheep01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSheep01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHEEP_01 ,tileRegionSheep01 );
		TextureRegion tileRegionSheep02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSheep02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHEEP_02 ,tileRegionSheep02 );
		TextureRegion tileRegionSheep03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSheep03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHEEP_03 ,tileRegionSheep03 );
		TextureRegion tileRegionSheep04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSheep04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHEEP_04 ,tileRegionSheep04 );
		
		TextureRegion tileRegionOstrich01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOstrich01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OSTRICH_01 ,tileRegionOstrich01 );
		TextureRegion tileRegionOstrich02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOstrich02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OSTRICH_02 ,tileRegionOstrich02 );
		TextureRegion tileRegionOstrich03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOstrich03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OSTRICH_03 ,tileRegionOstrich03 );
		TextureRegion tileRegionOstrich04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOstrich04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OSTRICH_04 ,tileRegionOstrich04 );

		//Sea Animal
		TextureRegion tileRegionCorralSeaAnimal = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCorralSeaAnimal, this, "T07.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CORRAL_SEA_ANIMAL ,tileRegionCorralSeaAnimal );
		
		TextureRegion tileRegionFish01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasFish01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_FISH_01 ,tileRegionFish01 );
		TextureRegion tileRegionFish02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasFish02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_FISH_02 ,tileRegionFish02 );
		TextureRegion tileRegionFish03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasFish03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_FISH_03 ,tileRegionFish03 );
		TextureRegion tileRegionFish04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasFish04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_FISH_04 ,tileRegionFish04 );
		
		TextureRegion tileRegionSquid01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSquid01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SQUID_01 ,tileRegionSquid01 );
		TextureRegion tileRegionSquid02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSquid02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SQUID_02 ,tileRegionSquid02 );
		TextureRegion tileRegionSquid03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSquid03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SQUID_03 ,tileRegionSquid03 );
		TextureRegion tileRegionSquid04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSquid04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SQUID_04 ,tileRegionSquid04 );
		
		TextureRegion tileRegionScallops01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasScallops01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SCALLOPS_01 ,tileRegionScallops01 );
		TextureRegion tileRegionScallops02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasScallops02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SCALLOPS_02 ,tileRegionScallops02 );
		TextureRegion tileRegionScallops03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasScallops03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SCALLOPS_03 ,tileRegionScallops03 );
		TextureRegion tileRegionScallops04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasScallops04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SCALLOPS_04 ,tileRegionScallops04 );
		
		TextureRegion tileRegionShrimp01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasShrimp01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHRIMP_01 ,tileRegionShrimp01 );
		TextureRegion tileRegionShrimp02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasShrimp02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHRIMP_02 ,tileRegionShrimp02 );
		TextureRegion tileRegionShrimp03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasShrimp03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHRIMP_03 ,tileRegionShrimp03 );
		TextureRegion tileRegionShrimp04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasShrimp04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHRIMP_04 ,tileRegionShrimp04 );
		
		TextureRegion tileRegionOyster01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOyster01, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OYSTER_01 ,tileRegionOyster01 );
		TextureRegion tileRegionOyster02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOyster02, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OYSTER_02 ,tileRegionOyster02 );
		TextureRegion tileRegionOyster03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOyster03, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OYSTER_03 ,tileRegionOyster03 );
		TextureRegion tileRegionOyster04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOyster04, this, "60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OYSTER_04 ,tileRegionOyster04 );
		
		this.mEngine.getTextureManager().loadTexture( mDefaultFarmMapTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mFarmMapTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mFarmMapNotOccupyTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mCouponButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mSpecialCodeButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mSoundButtonTextureAtlas );
		this.mEngine.getTextureManager().loadTexture( mShopButtonTextureAtlas );
		
		//Texture tile.
		//Vege
		this.mEngine.getTextureManager().loadTexture( tileAtlasMorningGlory01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasMorningGlory02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasMorningGlory03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasMorningGlory04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasChineseCabbage01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasChineseCabbage02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasChineseCabbage03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasChineseCabbage04 );

		this.mEngine.getTextureManager().loadTexture( tileAtlasPumpkin01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasPumpkin02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasPumpkin03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasPumpkin04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasBabyCorn01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasBabyCorn02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasBabyCorn03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasBabyCorn04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasStrawMushrooms01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasStrawMushrooms02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasStrawMushrooms03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasStrawMushrooms04 );
		
		//Land Animal
		this.mEngine.getTextureManager().loadTexture( tileAtlasCorralLandAnimal );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasChicken01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasChicken02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasChicken03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasChicken04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasPig01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasPig02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasPig03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasPig04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasCow01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasCow02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasCow03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasCow04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasSheep01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasSheep02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasSheep03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasSheep04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasOstrich01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasOstrich02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasOstrich03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasOstrich04 );
		
		//Sea Animal
		this.mEngine.getTextureManager().loadTexture( tileAtlasCorralSeaAnimal );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasFish01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasFish02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasFish03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasFish04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasSquid01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasSquid02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasSquid03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasSquid04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasScallops01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasScallops02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasScallops03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasScallops04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasShrimp01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasShrimp02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasShrimp03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasShrimp04 );
		
		this.mEngine.getTextureManager().loadTexture( tileAtlasOyster01 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasOyster02 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasOyster03 );
		this.mEngine.getTextureManager().loadTexture( tileAtlasOyster04 );
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
			this.startActivityForResult( i, REQUEST_COUPON );
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
				
				//update player to server
				currentPlayer.updateToServer();
			} 
		} else if(requestCode == REQUEST_BUILD){
			if(resultCode == Activity.RESULT_OK){
				String itemForBuildId = data.getStringExtra( BuildDialog.ITEM_ID );
				String buildingId = app.getBuildingManager().getBuildingIdFromItemBuild( itemForBuildId );
				Building building = app.getBuildingManager().getMatchBuilding(buildingId);
				currentPlayer.build( activeTile, building );
				
				//update farmSprite
				
				//update player to server
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
			}
			
			//update player to server
			currentPlayer.updateToServer();
		} else if(requestCode == REQUEST_SHOP){
			if(resultCode == ShopDialog.RESULT_BUY){
				String itemId = data.getStringExtra( ShopDialog.EXTRA_ITEM_ID );
				currentPlayer.buy( itemId, 1 );
			} else if(resultCode == ShopDialog.RESULT_SELL){
				String itemId = data.getStringExtra( ShopDialog.EXTRA_ITEM_ID );
				currentPlayer.sell( itemId, 1 );
			}
			
			//update player to server
			currentPlayer.updateToServer();
		} else if(requestCode == REQUEST_COUPON){
			
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
		
		//update player to server
		currentPlayer.updateToServer();
	}

	@Override
	public void onHarvestRequest( Tile data ) {
		currentPlayer.harvest(data);
		//display harvest animation
		
		//update player to server
		currentPlayer.updateToServer();
	}

}