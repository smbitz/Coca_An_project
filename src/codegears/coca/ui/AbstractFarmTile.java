package codegears.coca.ui;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.Tile;

public class AbstractFarmTile extends Sprite {

	private Tile data;
	
	public AbstractFarmTile( float pX, float pY, TextureRegion pTextureRegion ) {
		super( pX, pY, pTextureRegion );
	}

	public void setData(Tile t){
		data = t;
	}
	
	public Tile getData(){
		return data;
	}
}
