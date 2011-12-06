package codegears.coca.ui;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.view.MotionEvent;

public class ButtonSprite extends Sprite {

	private ButtonListener listener;
	
	public void setListener(ButtonListener l){
		listener = l;
	}
	
	public ButtonSprite(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
		// TODO Auto-generated constructor stub
	}
	
	@Override
  public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
 		 switch(pSceneTouchEvent.getAction())
      {
          case MotionEvent.ACTION_DOWN:
         	 			this.setVisible(false);
                return true;
         
          case MotionEvent.ACTION_UP:
         	 			this.setVisible(true);
         	 		  listener.onClick(this);
                return true;
      }
     
      return false;
  }
}