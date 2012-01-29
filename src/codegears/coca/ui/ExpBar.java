package codegears.coca.ui;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class ExpBar extends Sprite {

	private float fullWidth;
	
	public ExpBar( float pX, float pY, TextureRegion pTextureRegion ) {
		super( pX, pY, pTextureRegion );
		fullWidth = this.getWidth();
	}

	public void setExpPercent(float exp){
		this.setWidth( exp * fullWidth  );
	}
}
