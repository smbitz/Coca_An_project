package codegears.coca.ui;

import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.Tile;

public class PurchaseTile extends AbstractFarmTile {

	public PurchaseTile( float pX, float pY, TextureRegion pTextureRegion ) {
		super( pX, pY, pTextureRegion );
	}

	@Override
	protected boolean touchOnPreferedRegion(float localX, float localY){
		System.out.println("Purchase Farm Touch");
		float scale = this.getParent().getScaleX();
		float x = localX;
		float y = localY;
		if(( y > 85 ) && ( x > 160 ) && 
						(y < (this.getHeightScaled()-175)+85) && (x < (this.getWidthScaled()-320)+160)){
			return true;
		}
		return false;
	}
}
