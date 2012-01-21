package codegears.coca.ui;

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
		return false;
	}
}
