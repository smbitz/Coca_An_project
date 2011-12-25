package codegears.coca.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.DefaultVar;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;

public class FarmSprite extends Sprite {
	
	private ArrayList<AbstractFarmTile> farmTileList;
	private ArrayList<AbstractFarmTile> purchaseTileList;
	private Player currentPlayer;
	private HashMap<String, TextureRegion> textureCollection;
	private FarmTileListener tileListener;
	
	public FarmSprite(HashMap<String, TextureRegion> getTextureCollection){
		super(0, 0, getTextureCollection.get(DefaultVar.TEXTURE_FARM_DEFAULT));
		farmTileList = new ArrayList<AbstractFarmTile>();
		purchaseTileList = new ArrayList<AbstractFarmTile>();
		textureCollection = getTextureCollection;
	}
	
	public void setFarmTileListener(FarmTileListener listener){
		tileListener = listener;
		for(AbstractFarmTile tile:farmTileList){
			tile.setFarmTileListener( tileListener );
		}
		for(AbstractFarmTile tile:purchaseTileList){
			tile.setFarmTileListener( tileListener );
		}
	}

	public void setPlayer(Player p){
		currentPlayer = p;
		ArrayList<Tile> tileList = p.getTile();
		//---- Create FarmTile ----//
		int setX = 0;
		int setY = 0;
		int loop = 0;
		for(Tile tileData:tileList){
			AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection.get( DefaultVar.TEXTURE_FARM_NOTOCCUPY ) );
			tile.setData( tileData );
			farmTileList.add( tile );
			if( !tileData.getIsOccupy() ){
				int indexX = loop % 8;
				int indexY = loop / 8;
				if((indexX % 2 == 0) && (indexY % 2 == 0)){
					AbstractFarmTile purchaseTile = new PurchaseTile(setX, setY, textureCollection.get( DefaultVar.TEXTURE_FARM ));
					purchaseTile.setData( tileData );
					purchaseTileList.add( purchaseTile );
				}
			}
			
			//Set Tile Position
			setX+=DefaultVar.TILE_WIDTH;
			if(setX==DefaultVar.ALL_TILE_WIDTH){
				setX = 0;
				setY+=DefaultVar.TILE_HEIGHT;
			}
			loop++;
		}
		//---- add All tile to farmMap in proper order ----//
		for(AbstractFarmTile tile:farmTileList){
			this.attachChild( tile );
		}
		for(AbstractFarmTile tile:purchaseTileList){
			this.attachChild( tile );
		}
	}

	public void registerChildTouchArea(Scene scene){
		for(AbstractFarmTile tile:farmTileList){
			if(tile.getData().getBuildingStatus() != Tile.BUILDING_NOTOCCUPY){
				scene.registerTouchArea( tile );
			}
		}
		for(AbstractFarmTile tile:purchaseTileList){
			scene.registerTouchArea( tile );
		}
	}
	
}
