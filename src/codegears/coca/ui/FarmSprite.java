package codegears.coca.ui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.rmi.CORBA.Tie;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import codegears.coca.data.BuildingManager;
import codegears.coca.data.ItemManager;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;

public class FarmSprite extends Sprite {
	
	private static final float ZOOM_MIN = 1.0f;
	private static final float ZOOM_MAX = 1.0f;
	
	private static final int FARM_START_X = 880;
	private static final int FARM_START_Y = 464;
	private static final int FARM_INCREASE_X = 141;
	private static final int FARM_INCREASE_Y = 71;
	
	private static final float MAP_SCALE = (float) 1.37;
	
	private ArrayList<AbstractFarmTile> farmTileList;
	private ArrayList<AbstractFarmTile> purchaseTileList;
	private ArrayList<AbstractFarmTile> extraItemTileList;
	private ArrayList<SupplyOnTile> supplyItemTileList;
	
	private Player currentPlayer;
	private HashMap<String, TextureRegion> textureCollection;
	private HashMap<String, TiledTextureRegion> tiledTextureCollection;
	private FarmTileListener tileListener;
	
	private float startedMouseTouchX;
	private float startedMouseTouchY;
	private float startedPositionX;
	private float startedPositionY;
	private float startedZoomFactor;
	private boolean startTouch;
	
	private MapSprite map;
	private Sprite iconBuyArea;
	
	public FarmSprite(HashMap<String, TextureRegion> getTextureCollection, 
			HashMap<String, TiledTextureRegion> gettiledTextureCollection){
		super(0, 0, getTextureCollection.get(TextureVar.TEXTURE_FARM_MAP_DEFAULT));
		
		map = new MapSprite( 0, 0, getTextureCollection.get(TextureVar.TEXTURE_FARM_MAP_DEFAULT) );
		map.setScale( MAP_SCALE );
		
		farmTileList = new ArrayList<AbstractFarmTile>();
		purchaseTileList = new ArrayList<AbstractFarmTile>();
		extraItemTileList = new ArrayList<AbstractFarmTile>();
		supplyItemTileList = new ArrayList<SupplyOnTile>();
		
		textureCollection = getTextureCollection;
		tiledTextureCollection = gettiledTextureCollection;
		startTouch = false;
		this.attachChild(map);
	}
	
	@Override
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
			if( tileData.getIsOccupy() ){
				//AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection.get( TextureVar.TEXTURE_FARM_NOTOCCUPY ) );
				AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection );
				tile.setData( tileData );
				
				//Add Butterfly Animate
				if( tileData.getBuildingStatus() != Tile.BUILDING_EMPTY &&
						tileData.getBuildingStatus() != Tile.BUILDING_ROTTED ){
					if( tileData.getExtraId().equals( ItemManager.ITEM_ID_FERTILIZER_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_VACCINE_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_MICROORGANISM_A ) ||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_FERTILIZER_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_VACCINE_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_MICROORGANISM_A ) ){
						AbstractFarmTile tileExtra = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection );
						tileExtra.setData( tileData );
						tileExtra.setColor(1, 0, 0, (float) 0.3);
						//tile.attachChild(tileExtraB);
						extraItemTileList.add( tileExtra );
					}
					
					if( tileData.getBuildingStatus() != Tile.BUILDING_ROTTED &&
							tileData.getBuildingStatus() != Tile.BUILDING_COMPLETED ){
						if( tileData.getSupply()<=0 ){
							if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(setX, setY, textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_PLANT_01 ));
								supplyItemTileList.add(supplyItem);
							}else if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(setX, setY, textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_ANIMAL_01 ));
								supplyItemTileList.add(supplyItem);
							}else if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(setX, setY, textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_SEA_ANIMAL_01 ));
								supplyItemTileList.add(supplyItem);
							}
						}
					}
					
					AnimatedSprite butterflyAnimate = new AnimatedSprite(50, 25, tiledTextureCollection.get( TextureVar.TEXTURE_BUTTERFLY_ANIMATE ).deepCopy());
					butterflyAnimate.animate(100);
					tile.attachChild( butterflyAnimate );
				}
				
				farmTileList.add( tile );
			}else	if( !tileData.getIsOccupy() ){
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
		}
		for(AbstractFarmTile tile:extraItemTileList){
			this.attachChild( tile );
		}
		for(AbstractFarmTile tile:farmTileList){
			this.attachChild( tile );
		}
		this.setFarmTileListener( this.tileListener );
	}
	
	public void update(Scene scene){
		for(AbstractFarmTile tile:purchaseTileList){
			this.detachChild( tile );
		}
		purchaseTileList.clear();
		for(AbstractFarmTile tile:farmTileList){
			this.detachChild( tile );
		}
		farmTileList.clear();
		for(AbstractFarmTile tile:extraItemTileList){
			this.detachChild( tile );
		}
		extraItemTileList.clear();
		for(SupplyOnTile tile:supplyItemTileList){
			this.detachChild( tile );
		}
		supplyItemTileList.clear();
		
		ArrayList<Tile> tileList = currentPlayer.getTile();
		//---- Create FarmTile ----//
		int setX = 880;
		int setY = 464;
		int loop = 0;
		int countLine = 1;
		int countLoop = 1;
		for(Tile tileData:tileList){
			if( tileData.getIsOccupy() ){
				AbstractFarmTile tile = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection );
				tile.setData( tileData );
				
				//Add Butterfly Animate
				if( tileData.getBuildingStatus() != Tile.BUILDING_EMPTY ){
					if( tileData.getExtraId().equals( ItemManager.ITEM_ID_FERTILIZER_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_VACCINE_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_MICROORGANISM_A ) ||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_FERTILIZER_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_VACCINE_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_MICROORGANISM_A ) ){
						AbstractFarmTile tileExtra = FarmTileBuilder.createFarmTile( setX, setY, tileData, textureCollection );
						tileExtra.setColor(1, 0, 0, (float) 0.1);
						extraItemTileList.add( tileExtra );
					}
					
					if( tileData.getBuildingStatus() != Tile.BUILDING_ROTTED &&
							tileData.getBuildingStatus() != Tile.BUILDING_COMPLETED ){
						if( tileData.getSupply()<=0 ){
							if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(setX, setY, textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_PLANT_01 ));
								supplyItemTileList.add(supplyItem);
							}else if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(setX, setY, textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_ANIMAL_01 ));
								supplyItemTileList.add(supplyItem);
							}else if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(setX, setY, textureCollection.get( TextureVar.TEXTURE_SUPPLY_ITEM_SEA_ANIMAL_01 ));
								supplyItemTileList.add(supplyItem);
							}
						}
					}
					
					AnimatedSprite butterflyAnimate = new AnimatedSprite(50, 25, tiledTextureCollection.get( TextureVar.TEXTURE_BUTTERFLY_ANIMATE ).deepCopy());
					butterflyAnimate.animate(100);
					tile.attachChild( butterflyAnimate );
				}
				
				farmTileList.add( tile );
			}else if( !tileData.getIsOccupy() ){
				int indexX = loop % 8;
				int indexY = loop / 8;
				if((indexX % 2 == 0) && (indexY % 2 == 0)){
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
		}
		for(AbstractFarmTile tile:farmTileList){
			this.attachChild( tile );
		}
		for(AbstractFarmTile tile:extraItemTileList){
			this.attachChild( tile );
		}
		for(SupplyOnTile tile:supplyItemTileList){
			this.attachChild( tile );
		}
		this.setFarmTileListener( this.tileListener );
	}

	public void registerChildTouchArea(Scene scene){
		scene.registerTouchArea( map );
		for(AbstractFarmTile tile:farmTileList){
			if(tile.getData().getBuildingStatus() != Tile.BUILDING_NOTOCCUPY){
				scene.registerTouchArea( tile );
			}
		}
		for(AbstractFarmTile tile:purchaseTileList){
				scene.registerTouchArea( tile );
		}
	}
	
	public void unRegisterChildTouchArea(Scene scene){
		scene.unregisterTouchArea( map );
		for(AbstractFarmTile tile:farmTileList){
			if(tile.getData().getBuildingStatus() != Tile.BUILDING_NOTOCCUPY){
				scene.unregisterTouchArea( tile );
			}
		}
		for(AbstractFarmTile tile:purchaseTileList){
				scene.unregisterTouchArea( tile );
		}
	}
	
	public void pinchStarted(){
		startedZoomFactor = this.getScaleX();
	}
	
	public void pinchZoom(float factor){
		float zoom = startedZoomFactor * factor;
		zoom = Math.min( zoom, ZOOM_MAX );
		zoom = Math.max( zoom, ZOOM_MIN );
		this.setScale( zoom); 
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
