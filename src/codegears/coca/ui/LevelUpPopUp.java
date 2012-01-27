package codegears.coca.ui;

import java.util.HashMap;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import codegears.coca.GameActivity;
import codegears.coca.data.TextureVar;

public class LevelUpPopUp extends Sprite implements ButtonListener {

	private int level;
	private ButtonSprite closeButton;
	private BitmapNumber levelUpNumber;
	private Sprite levelUpDialog;
	
	private LevelUpPopUpListener lListener;
	
	public void setListener(LevelUpPopUpListener getlListener){
		lListener = getlListener;
	}
	
	public LevelUpPopUp(float pX, float pY, HashMap<String, TextureRegion> textureCollection, 
							HashMap<String, TiledTextureRegion> tiledTextureCollection) {
		super(pX, pY, GameActivity.FIX_SCENE_WIDTH, GameActivity.FIX_SCENE_HEIGHT, textureCollection.get( TextureVar.TEXTURE_BACKGROUND_DIALOG ));
		this.setVisible(false);
		
		levelUpNumber = new BitmapNumber( 300, 180, textureCollection.get( TextureVar.TEXTURE_EMPTY_FARM ), tiledTextureCollection.get( TextureVar.TILEDTEXTURE_NUMBER_LEVEL_UP ), 3);
		closeButton = new ButtonSprite( 500, -10, textureCollection.get( TextureVar.TEXTURE_CLOSE_BUTTON ));
		closeButton.setListener( this );
		levelUpDialog = new Sprite(200, 110, textureCollection.get( TextureVar.TEXTURE_LEVEL_UP_DIALOG ));
		
		levelUpDialog.attachChild( levelUpNumber );
		levelUpDialog.attachChild( closeButton );
		
		this.attachChild( levelUpDialog );
	}
	
	public void setVisible(Scene scene, boolean visible){
		super.setVisible(visible);
		if(this.isVisible()){
			//registerTouchArea
			scene.registerTouchArea( closeButton );
		} else {
			//unregisterTouchArea
			scene.unregisterTouchArea( closeButton );
		}
	}
	
	public void setLevel(int level){
		this.level = level;
		levelUpNumber.setData( level, BitmapNumber.ALIGN_CENTER, -12);
	}

	@Override
	public void onClickUp(ButtonSprite bSprite) {
		if( bSprite.equals( closeButton ) ){
			lListener.onLevelPopUpCloseButtonClick();
		}
	}

	@Override
	public void onClickDown(ButtonSprite buttonSprite) {
		// TODO Auto-generated method stub
		
	}
}
