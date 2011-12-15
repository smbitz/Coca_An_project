package codegears.coca.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.DefaultVar;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;

public class FarmSprite extends Sprite {
	
	private ButtonListener listener;
	private ArrayList<AbstractFarmTile> farmTileList;
	private ArrayList<AbstractFarmTile> purchaseTileList;
	private Player currentPlayer;
	private HashMap<String, TextureRegion> textureCollection;
	
	public FarmSprite(HashMap<String, TextureRegion> getTextureCollection){
		super(0, 0, getTextureCollection.get(DefaultVar.TEXTURE_FARM_DEFAULT));
		farmTileList = new ArrayList<AbstractFarmTile>();
		purchaseTileList = new ArrayList<AbstractFarmTile>();
		textureCollection = getTextureCollection;
	}

	public void setListener(ButtonListener l){
		listener = l;
	}

	public void setPlayer(Player p){
		currentPlayer = p;
		ArrayList<Tile> tileList = p.getTile();
		//---- Create FarmTile ----//
		int setX = 0;
		int setY = 0;
		int CountRow = 0;
		for(Tile tileData:tileList){
			if( tileData.getIsCooupy() ){
				//if index match condition
				AbstractFarmTile purchaseTile = new PurchaseTile(setX, setY, textureCollection.get( DefaultVar.TEXTURE_FARM ));
				purchaseTile.setData( tileData );
				purchaseTileList.add( purchaseTile );
			}else{
				AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection.get( DefaultVar.TEXTURE_FARM_NOTOCCUPY ) ); 
				tile.setData( tileData );
				farmTileList.add( tile );
			}
			
			//Set Tile Position
			setX+=DefaultVar.TILE_WIDTH;
			if(setX==DefaultVar.ALL_TILE_WIDTH){
				setX = 0;
				setY+=DefaultVar.TILE_HEIGHT;
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
