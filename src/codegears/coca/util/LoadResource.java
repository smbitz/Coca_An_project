package codegears.coca.util;

import java.util.HashMap;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import codegears.coca.data.TextureVar;

public class LoadResource {

	public static void loadTexture(Context context, Engine engine, HashMap<String, TextureRegion> textureCollection){
		BitmapTextureAtlas mDefaultFarmMapTextureAtlas = new BitmapTextureAtlas( 2048, 2048 );
		BitmapTextureAtlas mFarmMapTextureAtlas = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas mFarmMapNotOccupyTextureAtlas = new BitmapTextureAtlas( 32, 32 );
		BitmapTextureAtlas mCouponButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSpecialCodeButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSoundButtonTextureAtlas = new BitmapTextureAtlas( 128, 64 );
		BitmapTextureAtlas mShopButtonTextureAtlas = new BitmapTextureAtlas( 1024, 256 );
		
		//Texture tile.
		//Vege
		BitmapTextureAtlas tileAtlasMorningGlory01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasMorningGlory02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasMorningGlory03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasMorningGlory04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasChineseCabbage01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChineseCabbage02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChineseCabbage03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChineseCabbage04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasPumpkin01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPumpkin02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPumpkin03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPumpkin04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasBabyCorn01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasBabyCorn02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasBabyCorn03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasBabyCorn04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasStrawMushrooms01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasStrawMushrooms02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasStrawMushrooms03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasStrawMushrooms04 = new BitmapTextureAtlas( 512, 256 );
		
		//Land Animal
		BitmapTextureAtlas tileAtlasCorralLandAnimal = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasChicken01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChicken02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChicken03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasChicken04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasPig01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPig02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPig03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasPig04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasCow01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasCow02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasCow03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasCow04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasSheep01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasSheep02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasSheep03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasSheep04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasOstrich01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasOstrich02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasOstrich03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasOstrich04 = new BitmapTextureAtlas( 512, 256 );
		
		//Sea Animal
		BitmapTextureAtlas tileAtlasCorralSeaAnimal = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasFish01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasFish02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasFish03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasFish04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasSquid01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasSquid02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasSquid03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasSquid04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasScallops01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasScallops02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasScallops03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasScallops04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasShrimp01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasShrimp02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasShrimp03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasShrimp04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlas tileAtlasOyster01 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasOyster02 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasOyster03 = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas tileAtlasOyster04 = new BitmapTextureAtlas( 512, 256 );
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath( "gfx/" );
		TextureRegion mDefaultFarmMapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mDefaultFarmMapTextureAtlas, context, "defaultFarmSprite.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM_MAP_DEFAULT, mDefaultFarmMapTextureRegion );
		TextureRegion mFarmMapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mFarmMapTextureAtlas, context, "T08.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM, mFarmMapTextureRegion );
		TextureRegion mFarmMapNotOccupyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mFarmMapNotOccupyTextureAtlas, context, "farmNotOccupy.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM_NOTOCCUPY, mFarmMapNotOccupyTextureRegion );
		TextureRegion mCouponButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mCouponButtonTextureAtlas, context, "button_coupon.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_COUPONBUTTON, mCouponButtonTextureRegion );
		TextureRegion mSpecialCodeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSpecialCodeButtonTextureAtlas, context, "button_code.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SPECIALCODEBUTTON, mSpecialCodeTextureRegion );
		TextureRegion mSoundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSoundButtonTextureAtlas, context, "button_opensound.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SOUNDBUTTON, mSoundTextureRegion );
		TextureRegion mShopTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mShopButtonTextureAtlas, context, "map_market.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SHOPBUTTON, mShopTextureRegion );
		
	  //Texture tile.
		//Vege
		TextureRegion tileRegionMorningGlory01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasMorningGlory01, context, "T01_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_MORNING_GLORY_01 ,tileRegionMorningGlory01 );
		TextureRegion tileRegionMorningGlory02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasMorningGlory02, context, "T01_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_MORNING_GLORY_02 ,tileRegionMorningGlory02 );
		TextureRegion tileRegionMorningGlory03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasMorningGlory03, context, "T01_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_MORNING_GLORY_03 ,tileRegionMorningGlory03 );
		TextureRegion tileRegionMorningGlory04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasMorningGlory04, context, "T01_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_MORNING_GLORY_04 ,tileRegionMorningGlory04 );
		
		TextureRegion tileRegionChineseCabbage01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChineseCabbage01, context, "T02_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_01 ,tileRegionChineseCabbage01 );
		TextureRegion tileRegionChineseCabbage02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChineseCabbage02, context, "T02_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_02 ,tileRegionChineseCabbage02 );
		TextureRegion tileRegionChineseCabbage03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChineseCabbage03, context, "T02_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_03 ,tileRegionChineseCabbage03 );
		TextureRegion tileRegionChineseCabbage04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChineseCabbage04, context, "T02_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHINESE_CABBAGE_04 ,tileRegionChineseCabbage04 );
		
		TextureRegion tileRegionPumpkin01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPumpkin01, context, "T03_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PUMPKIN_01 ,tileRegionPumpkin01 );
		TextureRegion tileRegionPumpkin02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPumpkin02, context, "T03_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PUMPKIN_02 ,tileRegionPumpkin02 );
		TextureRegion tileRegionPumpkin03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPumpkin03, context, "T03_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PUMPKIN_03 ,tileRegionPumpkin03 );
		TextureRegion tileRegionPumpkin04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPumpkin04, context, "T03_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PUMPKIN_04 ,tileRegionPumpkin04 );
		
		TextureRegion tileRegionBabyCorn01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasBabyCorn01, context, "T04_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_BABY_CORN_01 ,tileRegionBabyCorn01 );
		TextureRegion tileRegionBabyCorn02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasBabyCorn02, context, "T04_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_BABY_CORN_02 ,tileRegionBabyCorn02 );
		TextureRegion tileRegionBabyCorn03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasBabyCorn03, context, "T04_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_BABY_CORN_03 ,tileRegionBabyCorn03 );
		TextureRegion tileRegionBabyCorn04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasBabyCorn04, context, "T04_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_BABY_CORN_04 ,tileRegionBabyCorn04 );
		
		TextureRegion tileRegionStrawMushrooms01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasStrawMushrooms01, context, "T05_01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_01 ,tileRegionStrawMushrooms01 );
		TextureRegion tileRegionStrawMushrooms02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasStrawMushrooms02, context, "T05_02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_02 ,tileRegionStrawMushrooms02 );
		TextureRegion tileRegionStrawMushrooms03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasStrawMushrooms03, context, "T05_03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_03 ,tileRegionStrawMushrooms03 );
		TextureRegion tileRegionStrawMushrooms04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasStrawMushrooms04, context, "T05_04.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_STRAW_MUSHROOMS_04 ,tileRegionStrawMushrooms04 );
		
		//Land Animal
		TextureRegion tileRegionCorralLandAnimal = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCorralLandAnimal, context, "T06.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CORRAL_LAND_ANIMAL ,tileRegionCorralLandAnimal );
		
		TextureRegion tileRegionChicken01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChicken01, context, "t_60_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHIKEN_01 ,tileRegionChicken01 );
		TextureRegion tileRegionChicken02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChicken02, context, "t_60_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHIKEN_02 ,tileRegionChicken02 );
		TextureRegion tileRegionChicken03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChicken03, context, "t_60_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHIKEN_03 ,tileRegionChicken03 );
		TextureRegion tileRegionChicken04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasChicken04, context, "t_60_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CHIKEN_04 ,tileRegionChicken04 );
		
		TextureRegion tileRegionPig01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPig01, context, "t_70_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PIG_01 ,tileRegionPig01 );
		TextureRegion tileRegionPig02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPig02, context, "t_70_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PIG_02 ,tileRegionPig02 );
		TextureRegion tileRegionPig03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPig03, context, "t_70_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PIG_03 ,tileRegionPig03 );
		TextureRegion tileRegionPig04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasPig04, context, "t_70_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_PIG_04 ,tileRegionPig04 );
		
		TextureRegion tileRegionCow01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCow01, context, "t_80_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_COW_01 ,tileRegionCow01 );
		TextureRegion tileRegionCow02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCow02, context, "t_80_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_COW_02 ,tileRegionCow02 );
		TextureRegion tileRegionCow03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCow03, context, "t_80_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_COW_03 ,tileRegionCow03 );
		TextureRegion tileRegionCow04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCow04, context, "t_80_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_COW_04 ,tileRegionCow04 );
		
		TextureRegion tileRegionSheep01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSheep01, context, "t_90_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHEEP_01 ,tileRegionSheep01 );
		TextureRegion tileRegionSheep02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSheep02, context, "t_90_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHEEP_02 ,tileRegionSheep02 );
		TextureRegion tileRegionSheep03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSheep03, context, "t_90_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHEEP_03 ,tileRegionSheep03 );
		TextureRegion tileRegionSheep04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSheep04, context, "t_90_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHEEP_04 ,tileRegionSheep04 );
		
		TextureRegion tileRegionOstrich01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOstrich01, context, "t_100_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OSTRICH_01 ,tileRegionOstrich01 );
		TextureRegion tileRegionOstrich02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOstrich02, context, "t_100_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OSTRICH_02 ,tileRegionOstrich02 );
		TextureRegion tileRegionOstrich03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOstrich03, context, "t_100_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OSTRICH_03 ,tileRegionOstrich03 );
		TextureRegion tileRegionOstrich04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOstrich04, context, "t_100_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OSTRICH_04 ,tileRegionOstrich04 );

		//Sea Animal
		TextureRegion tileRegionCorralSeaAnimal = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasCorralSeaAnimal, context, "T07.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_CORRAL_SEA_ANIMAL ,tileRegionCorralSeaAnimal );
		
		TextureRegion tileRegionFish01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasFish01, context, "t_110_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_FISH_01 ,tileRegionFish01 );
		TextureRegion tileRegionFish02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasFish02, context, "t_110_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_FISH_02 ,tileRegionFish02 );
		TextureRegion tileRegionFish03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasFish03, context, "t_110_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_FISH_03 ,tileRegionFish03 );
		TextureRegion tileRegionFish04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasFish04, context, "t_110_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_FISH_04 ,tileRegionFish04 );
		
		TextureRegion tileRegionSquid01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSquid01, context, "t_120_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SQUID_01 ,tileRegionSquid01 );
		TextureRegion tileRegionSquid02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSquid02, context, "t_120_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SQUID_02 ,tileRegionSquid02 );
		TextureRegion tileRegionSquid03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSquid03, context, "t_120_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SQUID_03 ,tileRegionSquid03 );
		TextureRegion tileRegionSquid04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasSquid04, context, "t_120_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SQUID_04 ,tileRegionSquid04 );
		
		TextureRegion tileRegionScallops01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasScallops01, context, "t_130_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SCALLOPS_01 ,tileRegionScallops01 );
		TextureRegion tileRegionScallops02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasScallops02, context, "t_130_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SCALLOPS_02 ,tileRegionScallops02 );
		TextureRegion tileRegionScallops03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasScallops03, context, "t_130_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SCALLOPS_03 ,tileRegionScallops03 );
		TextureRegion tileRegionScallops04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasScallops04, context, "t_130_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SCALLOPS_04 ,tileRegionScallops04 );
		
		TextureRegion tileRegionShrimp01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasShrimp01, context, "t_140_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHRIMP_01 ,tileRegionShrimp01 );
		TextureRegion tileRegionShrimp02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasShrimp02, context, "t_140_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHRIMP_02 ,tileRegionShrimp02 );
		TextureRegion tileRegionShrimp03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasShrimp03, context, "t_140_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHRIMP_03 ,tileRegionShrimp03 );
		TextureRegion tileRegionShrimp04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasShrimp04, context, "t_140_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_SHRIMP_04 ,tileRegionShrimp04 );
		
		TextureRegion tileRegionOyster01 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOyster01, context, "t_150_1_0_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OYSTER_01 ,tileRegionOyster01 );
		TextureRegion tileRegionOyster02 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOyster02, context, "t_150_1_1_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OYSTER_02 ,tileRegionOyster02 );
		TextureRegion tileRegionOyster03 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOyster03, context, "t_150_1_2_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OYSTER_03 ,tileRegionOyster03 );
		TextureRegion tileRegionOyster04 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tileAtlasOyster04, context, "t_150_1_3_hd.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_TILE_OYSTER_04 ,tileRegionOyster04 );
		
		engine.getTextureManager().loadTexture( mDefaultFarmMapTextureAtlas );
		engine.getTextureManager().loadTexture( mFarmMapTextureAtlas );
		engine.getTextureManager().loadTexture( mFarmMapNotOccupyTextureAtlas );
		engine.getTextureManager().loadTexture( mCouponButtonTextureAtlas );
		engine.getTextureManager().loadTexture( mSpecialCodeButtonTextureAtlas );
		engine.getTextureManager().loadTexture( mSoundButtonTextureAtlas );
		engine.getTextureManager().loadTexture( mShopButtonTextureAtlas );
		
		//Texture tile.
		//Vege
		engine.getTextureManager().loadTexture( tileAtlasMorningGlory01 );
		engine.getTextureManager().loadTexture( tileAtlasMorningGlory02 );
		engine.getTextureManager().loadTexture( tileAtlasMorningGlory03 );
		engine.getTextureManager().loadTexture( tileAtlasMorningGlory04 );
		
		engine.getTextureManager().loadTexture( tileAtlasChineseCabbage01 );
		engine.getTextureManager().loadTexture( tileAtlasChineseCabbage02 );
		engine.getTextureManager().loadTexture( tileAtlasChineseCabbage03 );
		engine.getTextureManager().loadTexture( tileAtlasChineseCabbage04 );

		engine.getTextureManager().loadTexture( tileAtlasPumpkin01 );
		engine.getTextureManager().loadTexture( tileAtlasPumpkin02 );
		engine.getTextureManager().loadTexture( tileAtlasPumpkin03 );
		engine.getTextureManager().loadTexture( tileAtlasPumpkin04 );
		
		engine.getTextureManager().loadTexture( tileAtlasBabyCorn01 );
		engine.getTextureManager().loadTexture( tileAtlasBabyCorn02 );
		engine.getTextureManager().loadTexture( tileAtlasBabyCorn03 );
		engine.getTextureManager().loadTexture( tileAtlasBabyCorn04 );
		
		engine.getTextureManager().loadTexture( tileAtlasStrawMushrooms01 );
		engine.getTextureManager().loadTexture( tileAtlasStrawMushrooms02 );
		engine.getTextureManager().loadTexture( tileAtlasStrawMushrooms03 );
		engine.getTextureManager().loadTexture( tileAtlasStrawMushrooms04 );
		
		//Land Animal
		engine.getTextureManager().loadTexture( tileAtlasCorralLandAnimal );
		
		engine.getTextureManager().loadTexture( tileAtlasChicken01 );
		engine.getTextureManager().loadTexture( tileAtlasChicken02 );
		engine.getTextureManager().loadTexture( tileAtlasChicken03 );
		engine.getTextureManager().loadTexture( tileAtlasChicken04 );
		
		engine.getTextureManager().loadTexture( tileAtlasPig01 );
		engine.getTextureManager().loadTexture( tileAtlasPig02 );
		engine.getTextureManager().loadTexture( tileAtlasPig03 );
		engine.getTextureManager().loadTexture( tileAtlasPig04 );
		
		engine.getTextureManager().loadTexture( tileAtlasCow01 );
		engine.getTextureManager().loadTexture( tileAtlasCow02 );
		engine.getTextureManager().loadTexture( tileAtlasCow03 );
		engine.getTextureManager().loadTexture( tileAtlasCow04 );
		
		engine.getTextureManager().loadTexture( tileAtlasSheep01 );
		engine.getTextureManager().loadTexture( tileAtlasSheep02 );
		engine.getTextureManager().loadTexture( tileAtlasSheep03 );
		engine.getTextureManager().loadTexture( tileAtlasSheep04 );
		
		engine.getTextureManager().loadTexture( tileAtlasOstrich01 );
		engine.getTextureManager().loadTexture( tileAtlasOstrich02 );
		engine.getTextureManager().loadTexture( tileAtlasOstrich03 );
		engine.getTextureManager().loadTexture( tileAtlasOstrich04 );
		
		//Sea Animal
		engine.getTextureManager().loadTexture( tileAtlasCorralSeaAnimal );
		
		engine.getTextureManager().loadTexture( tileAtlasFish01 );
		engine.getTextureManager().loadTexture( tileAtlasFish02 );
		engine.getTextureManager().loadTexture( tileAtlasFish03 );
		engine.getTextureManager().loadTexture( tileAtlasFish04 );
		
		engine.getTextureManager().loadTexture( tileAtlasSquid01 );
		engine.getTextureManager().loadTexture( tileAtlasSquid02 );
		engine.getTextureManager().loadTexture( tileAtlasSquid03 );
		engine.getTextureManager().loadTexture( tileAtlasSquid04 );
		
		engine.getTextureManager().loadTexture( tileAtlasScallops01 );
		engine.getTextureManager().loadTexture( tileAtlasScallops02 );
		engine.getTextureManager().loadTexture( tileAtlasScallops03 );
		engine.getTextureManager().loadTexture( tileAtlasScallops04 );
		
		engine.getTextureManager().loadTexture( tileAtlasShrimp01 );
		engine.getTextureManager().loadTexture( tileAtlasShrimp02 );
		engine.getTextureManager().loadTexture( tileAtlasShrimp03 );
		engine.getTextureManager().loadTexture( tileAtlasShrimp04 );
		
		engine.getTextureManager().loadTexture( tileAtlasOyster01 );
		engine.getTextureManager().loadTexture( tileAtlasOyster02 );
		engine.getTextureManager().loadTexture( tileAtlasOyster03 );
		engine.getTextureManager().loadTexture( tileAtlasOyster04 );
		
	}
	
	public static void loadFont(Context context, Engine engine, HashMap<String, Font> fontCollection){
		Texture mFontTexture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Font font = new Font(mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.WHITE);
		fontCollection.put( "TEST_FONT", font );
		engine.getTextureManager().loadTexture( mFontTexture );
		engine.getFontManager().loadFont(font);
	}
}
