package codegears.coca.ui;

import org.anddev.andengine.opengl.texture.region.TextureRegion;
import codegears.coca.data.Tile;

public class FarmTileBuilder {

	public static AbstractFarmTile createFarmTile(int pX, int pY, Tile tile, TextureRegion textureRegion){
		return new FarmTile(pX, pY, textureRegion);
	}
}
