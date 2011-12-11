package codegears.coca.ui;

import codegears.coca.data.Tile;

public class FarmTileBuilder {

	public static AbstractFarmTile createFarmTile(Tile tile){
		return new FarmTile(0, 0, null);
	}
}
