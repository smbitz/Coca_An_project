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
	
	private static final int FARM_START_X = 880;
	private static final int FARM_START_Y = 464;
	private static final int FARM_INCREASE_X = 141;
	private static final int FARM_INCREASE_Y = 71;
	
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
	private boolean startTouch;
	private Sprite map;
	private Sprite iconBuyArea;
	
	public FarmSprite(HashMap<String, TextureRegion> getTextureCollection){
		super(0, 0, getTextureCollection.get(TextureVar.TEXTURE_FARM_MAP_DEFAULT));
		map = new Sprite( 0, 0, getTextureCollection.get(TextureVar.TEXTURE_FARM_MAP_DEFAULT) );
		map.setScale((float) 1.37);
		farmTileList = new ArrayList<AbstractFarmTile>();
		purchaseTileList = new ArrayList<AbstractFarmTile>();
		textureCollection = getTextureCollection;
		startTouch = false;
		this.attachChild(map);
	}
	
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY ){
		if(pSceneTouchEvent.getPointerID() != 0){
			return false;
		}
		if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN){
			startedMouseTouchX = pSceneTouchEvent.getX();
			startedMouseTouchY = pSceneTouchEvent.getY();
			startedPositionX = this.getX();
			startedPositionY = this.getY();
			startTouch = true;
		} else if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE){
			if(startTouch){
				this.setPosition( startedPositionX - (startedMouseTouchX - pSceneTouchEvent.getX() ), startedPositionY - (startedMouseTouchY - pSceneTouchEvent.getY()));
			}
		} else if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP){
			startTouch = false;
		}
		return false;
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
		int setX = FARM_START_X;
		int setY = FARM_START_Y;
		int loop = 0;
		int countLine = 1;
		int countLoop = 1;
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
			setX+=FARM_INCREASE_X;//TextureVar.TILE_WIDTH;
			setY+=FARM_INCREASE_Y;
			//if(setX==TextureVar.ALL_TILE_WIDTH+880){
			if(countLoop%8==0&&countLoop>0){
				setX = FARM_START_X-(countLine*FARM_INCREASE_X);
				setY = FARM_START_Y+(countLine*FARM_INCREASE_Y);
				countLine++;
			}
			countLoop++;
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
	
	public void update(Scene scene){
		for(AbstractFarmTile tile:farmTileList){
			this.detachChild( tile );
			scene.unregisterTouchArea( tile );
		}
		farmTileList.clear();
		for(AbstractFarmTile tile:purchaseTileList){
			this.detachChild( tile );
			scene.unregisterTouchArea( tile );
		}
		purchaseTileList.clear();
		ArrayList<Tile> tileList = currentPlayer.getTile();
		//---- Create FarmTile ----//
		int setX = 880;
		int setY = 464;
		int loop = 0;
		int countLine = 1;
		int countLoop = 1;
		for(Tile tileData:tileList){
			if( tileData.getIsOccupy() ){
				//AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection.get( TextureVar.TEXTURE_FARM_NOTOCCUPY ) );
				AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection );
				tile.setData( tileData );
				farmTileList.add( tile );
			}else if( !tileData.getIsOccupy() ){
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
			setX+=FARM_INCREASE_X;//TextureVar.TILE_WIDTH;
			setY+=FARM_INCREASE_Y;
			//if(setX==TextureVar.ALL_TILE_WIDTH+880){
			if(countLoop%8==0&&countLoop>0){
				setX = FARM_START_X-(countLine*FARM_INCREASE_X);
				setY = FARM_START_Y+(countLine*FARM_INCREASE_Y);
				countLine++;
			}
			countLoop++;
			loop++;
		}
		//---- add All tile to farmMap in proper order ----//
		for(AbstractFarmTile tile:purchaseTileList){
			this.attachChild( tile );
			scene.registerTouchArea( tile );
		}
		for(AbstractFarmTile tile:farmTileList){
			this.attachChild( tile );
			scene.registerTouchArea( tile );
		}
		this.setFarmTileListener( this.tileListener );
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
	
	public void setMoveState(){
		for(AbstractFarmTile tile:farmTileList){
			tile.setMoveState();
		}
		for(AbstractFarmTile tile:purchaseTileList){
			tile.setMoveState();
		}
	}

	public void setNormalState(){
		for(AbstractFarmTile tile:farmTileList){
			tile.setNormalState();
		}
		for(AbstractFarmTile tile:purchaseTileList){
			tile.setNormalState();
		}
	}
}
