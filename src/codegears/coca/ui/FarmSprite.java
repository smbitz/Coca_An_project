package codegears.coca.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.TextureVar;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;

public class FarmSprite extends Sprite {
	
	private ArrayList<AbstractFarmTile> farmTileList;
	private ArrayList<AbstractFarmTile> purchaseTileList;
	private Player currentPlayer;
	private HashMap<String, TextureRegion> textureCollection;
	private FarmTileListener tileListener;
	
	private float startedMouseTouchX;
	private float startedMouseTouchY;
	private float startedPositionX;
	private float startedPositionY;
	private float startedZoomFactor;
	
	public FarmSprite(HashMap<String, TextureRegion> getTextureCollection){
		super(0, 0, getTextureCollection.get(TextureVar.TEXTURE_FARM_DEFAULT));
		farmTileList = new ArrayList<AbstractFarmTile>();
		purchaseTileList = new ArrayList<AbstractFarmTile>();
		textureCollection = getTextureCollection;
	}
	
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY ){
		if(pSceneTouchEvent.getPointerID() != 0){
			System.out.println("MULTI TOUCH");
			return false;
		}
		if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN){
			startedMouseTouchX = pSceneTouchEvent.getX();
			startedMouseTouchY = pSceneTouchEvent.getY();
			startedPositionX = this.getX();
			startedPositionY = this.getY();
		} else if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE){
			this.setPosition( startedPositionX - (startedMouseTouchX - pSceneTouchEvent.getX() ), startedPositionY - (startedMouseTouchY - pSceneTouchEvent.getY()));
		}
		return true;
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
			//AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection.get( TextureVar.TEXTURE_FARM_NOTOCCUPY ) );
			AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection );
			tile.setData( tileData );
			farmTileList.add( tile );
			if( !tileData.getIsOccupy() ){
				int indexX = loop % 8;
				int indexY = loop / 8;
				if((indexX % 2 == 0) && (indexY % 2 == 0)){
					//AbstractFarmTile purchaseTile = new PurchaseTile(setX, setY, tr );
					AbstractFarmTile purchaseTile = FarmTileBuilder.createFarmTile(setX, setY, tileData, textureCollection);
					purchaseTile.setData( tileData );
					purchaseTileList.add( purchaseTile );
				}
			}
			
			//Set Tile Position
			setX+=TextureVar.TILE_WIDTH;
			if(setX==TextureVar.ALL_TILE_WIDTH){
				setX = 0;
				setY+=TextureVar.TILE_HEIGHT;
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
	
	public void pinchStarted(){
		startedZoomFactor = this.getScaleX();
	}
	
	public void pinchZoom(float factor){
		this.setScale( startedZoomFactor * factor); 
	}
}
