package codegears.coca.ui;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.view.MotionEvent;

public class FarmSprite extends Sprite {
	
	private ButtonListener listener;
	
	public void setListener(ButtonListener l){
		listener = l;
	}

	public FarmSprite(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
    return true;
	}
	
}
