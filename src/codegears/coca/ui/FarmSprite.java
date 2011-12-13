package codegears.coca.ui;

import java.util.ArrayList;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.Player;
import codegears.coca.data.Tile;

public class FarmSprite extends Sprite {
	
	private ButtonListener listener;
	private ArrayList<AbstractFarmTile> farmTileList;
	private ArrayList<AbstractFarmTile> purchaseTileList;
	private Player currentPlayer;
	
	public FarmSprite(float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
		farmTileList = new ArrayList<AbstractFarmTile>();
		purchaseTileList = new ArrayList<AbstractFarmTile>();
	}

	public void setListener(ButtonListener l){
		listener = l;
	}

	public void setPlayer(Player p){
		currentPlayer = p;
		ArrayList<Tile> tileList = p.getTile();
		//---- Create FarmTile ----//
		for(Tile tileData:tileList){
			AbstractFarmTile tile = FarmTileBuilder.createFarmTile( tileData ); 
			tile.setData( tileData );
			farmTileList.add( tile );
			if( tileData.getBuildingStatus() == Tile.BUILDING_NOTOCCUPY ){
				//if index match condition
					AbstractFarmTile purchaseTile = new PurchaseTile(0, 0, null);
					purchaseTile.setData( tileData );
					purchaseTileList.add( purchaseTile );
			}
		}
		//---- add All tile to farmMap in proper order ----//
		for(AbstractFarmTile tile:farmTileList){
			this.attachChild( tile );
		}
		for(AbstractFarmTile tile:purchaseTileList){
			this.attachChild( tile );
		}
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,float pTouchAreaLocalX, float pTouchAreaLocalY) {
    return true;
	}
	
}
