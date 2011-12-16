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

import codegears.coca.data.DefaultVar;
import codegears.coca.data.Player;
import codegears.coca.dialog.CouponDialog;
import codegears.coca.dialog.ShopDialog;
import codegears.coca.dialog.SpecialCodeDialog;
import codegears.coca.ui.ButtonListener;
import codegears.coca.ui.ButtonSprite;
import codegears.coca.ui.FarmSprite;

import android.content.Intent;
import android.view.Display;
import android.widget.Toast;

public class GameActivity extends BaseGameActivity implements ButtonListener,
				IScrollDetectorListener, IPinchZoomDetectorListener, IOnSceneTouchListener {

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

		farmMapSprite.setListener( this );
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
		// TODO Auto-generated method stub

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
			Intent i = new Intent( this, ShopDialog.class );
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

}