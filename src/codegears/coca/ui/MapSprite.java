package codegears.coca.ui;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class MapSprite extends Sprite {

	private static float CAMERA_W = 960;
	private static float CAMERA_H = 640;
	private static float MIN_X = -1020;
	private static float MAX_X = 1020;
	private static float MIN_Y = -740;
	private static float MAX_Y = 680;
	
	private float startedMouseTouchX;
	private float startedMouseTouchY;
	private float startedPositionX;
	private float startedPositionY;
	private boolean startTouch;
	
	public MapSprite( float pX, float pY, TextureRegion pTextureRegion ) {
		super( pX, pY, pTextureRegion );
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY ){
		if(pSceneTouchEvent.getPointerID() != 0){
			return false;
		}
		if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN){
			startedMouseTouchX = pSceneTouchEvent.getX();
			startedMouseTouchY = pSceneTouchEvent.getY();
			startedPositionX = this.getParent().getX();
			startedPositionY = this.getParent().getY();
			startTouch = true;
		} else if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE){
			if(startTouch){
				float x = startedPositionX - (startedMouseTouchX - pSceneTouchEvent.getX() );
				x = Math.min( x, (MAX_X)  * this.getParent().getScaleX() * 1.37f - 1030 );
				x = Math.max( x, (MIN_X) * this.getParent().getScaleX() * 1.37f - 60);
				float y = startedPositionY - (startedMouseTouchY - pSceneTouchEvent.getY() );
				y = Math.min( y, (MAX_Y) * this.getParent().getScaleX() * 1.37f - 720 );
				y = Math.max( y, (MIN_Y) * this.getParent().getScaleX() * 1.37f - 130);
				this.getParent().setPosition( x, y );
				System.out.println(this.getParent().getX() + " : " + this.getParent().getY());
			}
		} else if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP){
			startTouch = false;
		}
		return false;
	}

}
