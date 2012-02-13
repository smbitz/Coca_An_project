package codegears.coca.ui;

import java.util.HashMap;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.BuildingManager;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Tile;

public class FarmTileBuilder {

	private static final int TILE_OCCUPY_SET_X = 160;
	private static final int TILE_OCCUPY_SET_Y = 25;
	private static final int TILE_BUILDING_SET_Y = 7;
	private static final int TILE_SEA_ANIMAL_SET_Y = 10;
	private static final int TILE_ANIMAL_SET_Y = 10;
	
	public static AbstractFarmTile createFarmTile(int pX, int pY, Tile tile, HashMap<String, TextureRegion> textureCollection){

		TextureRegion tr = null;

		if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ){
			pY-=TILE_BUILDING_SET_Y;
		}
		
		//if meat
		if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_LAND_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_CHIKEN_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_CHIKEN_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_CHIKEN_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_CHIKEN_04 );
			}
			LandAnimalAnimation s = new LandAnimalAnimation(0, 0-TILE_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			MeatFarmTile newFarmTile = new MeatFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_LAND_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_PIG_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_PIG_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_PIG_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_PIG_04 );
			}
			LandAnimalAnimation s = new LandAnimalAnimation(0, 0-TILE_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			MeatFarmTile newFarmTile = new MeatFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_LAND_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_COW_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_COW_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_COW_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_COW_04 );
			}
			LandAnimalAnimation s = new LandAnimalAnimation(0, 0-TILE_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			MeatFarmTile newFarmTile = new MeatFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_LAND_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SHEEP_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SHEEP_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SHEEP_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SHEEP_04 );
			}
			LandAnimalAnimation s = new LandAnimalAnimation(0, 0-TILE_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			MeatFarmTile newFarmTile = new MeatFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_LAND_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_OSTRICH_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_OSTRICH_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_OSTRICH_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_OSTRICH_04 );
			}
			LandAnimalAnimation s = new LandAnimalAnimation(0, 0-TILE_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			MeatFarmTile newFarmTile = new MeatFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_MORNING_GLORY_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_MORNING_GLORY_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_MORNING_GLORY_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_MORNING_GLORY_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_PUMPKIN_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_PUMPKIN_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_PUMPKIN_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_PUMPKIN_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		} else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_SEA_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_FISH_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_FISH_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_FISH_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_FISH_04 );
			}
			SeaAnimalAnimation s = new SeaAnimalAnimation(0, 0+TILE_SEA_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			SeaFarmTile newFarmTile = new SeaFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_SEA_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SQUID_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SQUID_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SQUID_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SQUID_04 );
			}
			SeaAnimalAnimation s = new SeaAnimalAnimation(0, 0+TILE_SEA_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			SeaFarmTile newFarmTile = new SeaFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_SEA_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SCALLOPS_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SCALLOPS_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SCALLOPS_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SCALLOPS_04 );
			}
			SeaAnimalAnimation s = new SeaAnimalAnimation(0, 0+TILE_SEA_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			SeaFarmTile newFarmTile = new SeaFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_SEA_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SHRIMP_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SHRIMP_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SHRIMP_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_SHRIMP_04 );
			}
			SeaAnimalAnimation s = new SeaAnimalAnimation(0, 0+TILE_SEA_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			SeaFarmTile newFarmTile = new SeaFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_SEA_ANIMAL );
			TextureRegion animalTr = null;
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_OYSTER_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_OYSTER_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_OYSTER_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				animalTr = textureCollection.get( TextureVar.TEXTURE_TILE_OYSTER_04 );
			}
			SeaAnimalAnimation s = new SeaAnimalAnimation(0, 0+TILE_SEA_ANIMAL_SET_Y, animalTr, tile.getBuildingStatus());
			SeaFarmTile newFarmTile = new SeaFarmTile(pX, pY, tr);
			newFarmTile.attachChild(s);
			
			return newFarmTile;
		} else if( tile.getIsOccupy() ){
			return new FarmTile(pX, pY, textureCollection.get( TextureVar.TEXTURE_EMPTY_FARM ));
		} else{
			//debug
			pX-=TILE_OCCUPY_SET_X;
			pY+=TILE_OCCUPY_SET_Y;
			return new PurchaseTile(pX, pY, textureCollection.get( TextureVar.TEXTURE_FARM_NOTOCCUPY ));
		}

	}

	public static AbstractFarmTile createExtraFarmTile(int pX, int pY, Tile tile, HashMap<String, TextureRegion> textureCollection){

		TextureRegion tr = null;

		if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ){
			pY-=TILE_BUILDING_SET_Y;
		}
		
		//if meat
		if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_CHICKEN ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_PIG ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_COW ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SHEEP ) ||
				tile.getBuildingId().equals( BuildingManager.BUILDING_ID_OSTRICH ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_LAND_ANIMAL );
			MeatFarmTile newFarmTile = new MeatFarmTile(pX, pY, tr);
			
			return newFarmTile;
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_MORNING_GLORY ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_MORNING_GLORY_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_MORNING_GLORY_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_MORNING_GLORY_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_MORNING_GLORY_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_CHINESE_CABBAGE ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_PUMPKIN ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_PUMPKIN_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_PUMPKIN_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_PUMPKIN_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_PUMPKIN_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_BABY_CORN ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		}else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_STRAW_MUSHROOMS ) ){
			if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS1 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_01 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_PROCESS2 ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_02 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_COMPLETED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_03 );
			}else if( tile.getBuildingStatus() == Tile.BUILDING_ROTTED ){
				tr = textureCollection.get( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_04 );
			}
			
			return new VegeFarmTile(pX, pY, tr);
		} else if( tile.getBuildingId().equals( BuildingManager.BUILDING_ID_FISH ) ||
							 tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SQUID ) ||
							 tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SCALLOPS ) ||
							 tile.getBuildingId().equals( BuildingManager.BUILDING_ID_SHRIMP ) ||
							 tile.getBuildingId().equals( BuildingManager.BUILDING_ID_OYSTER ) ){
			tr = textureCollection.get( TextureVar.TEXTURE_TILE_CORRAL_SEA_ANIMAL );
			SeaFarmTile newFarmTile = new SeaFarmTile(pX, pY, tr);
			
			return newFarmTile;
		} else {
			return null;
		}
	}
}
