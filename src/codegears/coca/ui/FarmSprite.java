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
import codegears.coca.data.ItemQuantityPair;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Player;
import codegears.coca.data.Tile;

public class FarmSprite extends Sprite implements FillItemListener, HarvestItemListener {
	
	private static final float ZOOM_MIN = 1.0f;
	private static final float ZOOM_MAX = 1.0f;
	
	private static final int FARM_START_X = 880;
	private static final int FARM_START_Y = 464;
	private static final int FARM_INCREASE_X = 141;
	private static final int FARM_INCREASE_Y = 71;
	
	private static final float MAP_SCALE = (float) 1.37;
	
	public static final String FILL_ITEM_TYPE_SUPPLY = "FILL_ITEM_TYPE_SUPPLY";
	public static final String FILL_ITEM_TYPE_EXTRA_A = "FILL_ITEM_TYPE_EXTRA_A";
	public static final String FILL_ITEM_TYPE_EXTRA_B = "FILL_ITEM_TYPE_EXTRA_B";
	
	private ArrayList<AbstractFarmTile> farmTileList;
	private ArrayList<AbstractFarmTile> purchaseTileList;
	
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
	private FillItemOnTile newFillItemOnTile;
	private HarvestItemPopUp newHarvestItemPopUp;
	private ArrayList<TextureRegion> allHarvestItemTextureRegion;
	
	public FarmSprite(HashMap<String, TextureRegion> getTextureCollection, 
			HashMap<String, TiledTextureRegion> gettiledTextureCollection){
		super(0, 0, getTextureCollection.get(TextureVar.TEXTURE_FARM_MAP_DEFAULT));
		
		map = new MapSprite( 0, 0, getTextureCollection.get(TextureVar.TEXTURE_FARM_MAP_DEFAULT) );
		map.setScale( MAP_SCALE );
		
		farmTileList = new ArrayList<AbstractFarmTile>();
		purchaseTileList = new ArrayList<AbstractFarmTile>();
		
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
				if( tileData.getBuildingStatus() != Tile.BUILDING_EMPTY ){
					AnimatedSprite butterflyAnimate = new AnimatedSprite(50, 25, tiledTextureCollection.get( TextureVar.TEXTURE_BUTTERFLY_ANIMATE ).deepCopy());
					butterflyAnimate.animate(100);
					tile.attachChild( butterflyAnimate );
					
					if( tileData.getExtraId().equals( ItemManager.ITEM_ID_FERTILIZER_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_VACCINE_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_MICROORGANISM_A ) ||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_FERTILIZER_B )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_VACCINE_B )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_MICROORGANISM_B ) ){
						AbstractFarmTile tileExtra = FarmTileBuilder.createExtraFarmTile( 0, 5, tileData, textureCollection );
						tileExtra.setColor(0.4f, 0, 0, (float) 0.1);
						tile.attachChild( tileExtra );
					}
					
					if( tileData.getBuildingStatus() != Tile.BUILDING_ROTTED &&
							tileData.getBuildingStatus() != Tile.BUILDING_COMPLETED ){
						if( tileData.getSupply()<=0 ){
							if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(0, 0, textureCollection.get( TextureVar.TEXTURE_SUPPLY_WATER ));
								tile.attachSupplyOnTile(supplyItem);
							}else if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(0, 0, textureCollection.get( TextureVar.TEXTURE_SUPPLY_STRAW ));
								tile.attachSupplyOnTile(supplyItem);
							}else if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(0, 0, textureCollection.get( TextureVar.TEXTURE_SUPPLY_PELLET_FOOD ));
								tile.attachSupplyOnTile(supplyItem);
							}
						}
					}
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
					AnimatedSprite butterflyAnimate = new AnimatedSprite(50, 25, tiledTextureCollection.get( TextureVar.TEXTURE_BUTTERFLY_ANIMATE ).deepCopy());
					butterflyAnimate.animate(100);
					tile.attachChild( butterflyAnimate );
					
					if( tileData.getExtraId().equals( ItemManager.ITEM_ID_FERTILIZER_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_VACCINE_A )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_MICROORGANISM_A ) ||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_FERTILIZER_B )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_VACCINE_B )||
							tileData.getExtraId().equals( ItemManager.ITEM_ID_MICROORGANISM_B ) ){
						AbstractFarmTile tileExtra = FarmTileBuilder.createExtraFarmTile( 0, 5, tileData, textureCollection );
						tileExtra.setColor(0.4f, 0, 0, (float) 0.1);
						tile.attachChild( tileExtra );
					}
					
					if( tileData.getBuildingStatus() != Tile.BUILDING_ROTTED &&
							tileData.getBuildingStatus() != Tile.BUILDING_COMPLETED ){
						if( tileData.getSupply()<=0 ){
							if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ||
									tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(0, 0, textureCollection.get( TextureVar.TEXTURE_SUPPLY_WATER ));
								tile.attachSupplyOnTile(supplyItem);
							}else if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(0, 0, textureCollection.get( TextureVar.TEXTURE_SUPPLY_STRAW ));
								tile.attachSupplyOnTile(supplyItem);
							}else if( tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ||
												tileData.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ){
								SupplyOnTile supplyItem = new SupplyOnTile(0, 0, textureCollection.get( TextureVar.TEXTURE_SUPPLY_PELLET_FOOD ));
								tile.attachSupplyOnTile(supplyItem);
							}
						}
					}
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

	public void fillItemOnTile(Tile data, String fillItemType){
		//Remove supply on tile image
		for(AbstractFarmTile tile:farmTileList){
			if( tile.getData().equals( data ) ){
				tile.detachSupplyOnTile();
			}
		}
		
		for( AbstractFarmTile fetchTile:farmTileList ){
			if( fetchTile.getData().equals( data ) ){
				TextureRegion supplyTexture = null;
				
				if( data.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ||
						data.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ||
						data.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ||
						data.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ||
						data.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
					if( fillItemType.equals( FILL_ITEM_TYPE_SUPPLY ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_WATER );
					}else if( fillItemType.equals( FILL_ITEM_TYPE_EXTRA_A ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_FERTILIZER_A );
					}else if( fillItemType.equals( FILL_ITEM_TYPE_EXTRA_B ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_FERTILIZER_B );
					}
				}else if( data.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
									data.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
									data.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
									data.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
									data.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
					if( fillItemType.equals( FILL_ITEM_TYPE_SUPPLY ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_STRAW );
					}else if( fillItemType.equals( FILL_ITEM_TYPE_EXTRA_A ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_VACCINE_A );
					}else if( fillItemType.equals( FILL_ITEM_TYPE_EXTRA_B ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_VACCINE_B );
					}
				}else if( data.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
									data.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
									data.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
									data.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ||
									data.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ){
					if( fillItemType.equals( FILL_ITEM_TYPE_SUPPLY ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_PELLET_FOOD );
					}else if( fillItemType.equals( FILL_ITEM_TYPE_EXTRA_A ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_MICROORGANISM_A );
					}else if( fillItemType.equals( FILL_ITEM_TYPE_EXTRA_B ) ){
						supplyTexture = textureCollection.get( TextureVar.TEXTURE_SUPPLY_MICROORGANISM_B );
					}
				}
				
				newFillItemOnTile = new FillItemOnTile(0, 0, supplyTexture, 
						textureCollection.get( TextureVar.TEXTURE_ICON_PLUS ));
				newFillItemOnTile.setOnFillItemListener( this );
				fetchTile.attachChild( newFillItemOnTile );
			}
		}
	}

	@Override
	public void onFillItemAnimationComplete(FillItemOnTile fillItemOnTile) {
		fillItemOnTile.detachSelf();
		tileListener.onFillItemComplete();
	}

	public void harvestTile(Tile data, ArrayList<ItemQuantityPair> receiveItem) {
		allHarvestItemTextureRegion = new ArrayList<TextureRegion>();
		
		for( AbstractFarmTile fetchTile:farmTileList ){
			if( fetchTile.getData().equals( data ) ){
				for( ItemQuantityPair fetchReceiveItem:receiveItem ){
					TextureRegion harvestItemTexture = null;
					
					if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_MORNING_GLORY_SEED ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_MORNING_GLORY_SEED );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_CHINESE_CABBAGE_SEED ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_CHINESE_CABBAGE_SEED );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_PUMPKIN_SEED ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_PUMPKIN_SEED );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_BABY_CORN_SEED ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_BABY_CORN_SEED );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_STRAW_MUSHROOMS_SEED ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_STRAW_MUSHROOMS_SEED );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_CHICKEN_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_CHICKEN_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_PIG_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_PIG_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_COW_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_COW_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_SHEEP_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_SHEEP_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_OSTRICH_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_OSTRICH_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_FISH_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_FISH_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_SQUID_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_SQUID_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_SCALLOPS_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_SCALLOPS_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_SHRIMP_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_SHEEP_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_OYSTER_BABY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_OYSTER_BABY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_MORNING_GLORY ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_MORNING_GLORY );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_CHINESE_CABBAGE ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_CHINESE_CABBAGE );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_PUMPKIN ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_PUMPKIN );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_BABY_CORN ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_BABY_CORN );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_STRAW_MUSHROOMS ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_STRAW_MUSHROOMS );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_CHICKEN ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_CHICKEN );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_PIG ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_PIG );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_COW ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_COW );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_SHEEP ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_SHEEP );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_OSTRICH ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_OSTRICH );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_FISH ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_FISH );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_SQUID ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_SQUID );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_SCALLOPS ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_SCALLOPS );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_SHRIMP ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_SHRIMP );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_OYSTER ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_OYSTER );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_PEARL ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_PEARL );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_GOLD ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_GOLD );
					}else if( fetchReceiveItem.getItem().getId().equals( ItemManager.ITEM_ID_DIAMOND ) ){
						harvestItemTexture = textureCollection.get( TextureVar.TEXTURE_ITEM_DIAMOND );
					}
					
					allHarvestItemTextureRegion.add( harvestItemTexture );
				}
				
				newHarvestItemPopUp = new HarvestItemPopUp(0, 0, textureCollection.get( TextureVar.TEXTURE_EMPTY_FARM ), 
						allHarvestItemTextureRegion);
				newHarvestItemPopUp.setOnHarvesItemListener( this );
				fetchTile.attachChild( newHarvestItemPopUp );
			}
		}
	}

	@Override
	public void onHarvestItemAnimationComplete(HarvestItemPopUp harvestItemPopUp) {
		harvestItemPopUp.detachSelf();
	}
	
}
