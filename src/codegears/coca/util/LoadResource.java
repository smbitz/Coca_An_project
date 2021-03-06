package codegears.coca.util;

import java.io.IOException;
import java.util.HashMap;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.TextureVar;

public class LoadResource {
	
	public static final String STATUS_BAR_FONT = "STATUS_BAR_FONT";
	
	public static final String SOUND_BG = "SOUND_BG";
	public static final String SOUND_BONUS = "SOUND_BONUS";
	public static final String SOUND_BUTTON = "SOUND_BUTTON";
	public static final String SOUND_CHOOSE_CLICK = "SOUND_CHOOSE_CLICK";
	public static final String SOUND_COIN = "SOUND_COIN";
	public static final String SOUND_LAND_CLICK = "SOUND_LAND_CLICK";
	public static final String SOUND_LEVEL_UP = "SOUND_LEVEL_UP";
	public static final String SOUND_ON_OF = "SOUND_ON_OF";
	
	private static MyApp app;

	public static void loadTexture(Context context, Engine engine, HashMap<String, TextureRegion> textureCollection){
		app = (MyApp) context.getApplicationContext();
		
		BitmapTextureAtlas mDefaultFarmMapTextureAtlas = new BitmapTextureAtlas( 2048, 2048 );
		BitmapTextureAtlas mFarmMapTextureAtlas = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas mFarmMapNotOccupyTextureAtlas = new BitmapTextureAtlas( 2048, 1024 );
		BitmapTextureAtlas mCouponButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSpecialCodeButtonTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSoundButtonOnTextureAtlas = new BitmapTextureAtlas( 128, 64 );
		BitmapTextureAtlas mSoundButtonOffTextureAtlas = new BitmapTextureAtlas( 128, 64 );
		BitmapTextureAtlas mShopButtonTextureAtlas = new BitmapTextureAtlas( 1024, 256 );
		BitmapTextureAtlas mEmptyFarmTextureAtlas = new BitmapTextureAtlas( 512, 256 );
		BitmapTextureAtlas mLevelUpTextureAtlas = new BitmapTextureAtlas( 1024, 512 );
		BitmapTextureAtlas mCloseButtonTextureAtlas = new BitmapTextureAtlas( 64, 64 );
		BitmapTextureAtlas mBackgroundDialogTextureAtlas = new BitmapTextureAtlas( 16, 16 );
		BitmapTextureAtlas mSupplyItemWaterTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSupplyItemFertilizertATextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSupplyItemFertilizertBTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSupplyItemStrawTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSupplyItemVaccineATextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSupplyItemVaccineBTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSupplyItemPelletFoodTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSupplyItemMicroorganismATextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mSupplyItemMicroorganismBTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mIconPlusTextureAtlas = new BitmapTextureAtlas( 64, 64 );
		
		//Texture Item
		//Seed & Baby animal
		BitmapTextureAtlas mItemMorningGlorySeedTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemPumpkinSeedTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemChineseCabbageSeedTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemBabyCornSeedTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemStrawMushroomsSeedTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas mItemChickenBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemPigBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemCowBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemSheepBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemOstrichBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas mItemFishBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemSquidBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemScallopsBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemShrimpBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemOysterBabyTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		
		//Product
		BitmapTextureAtlas mItemMorningGloryTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemPumpkinTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemChineseCabbageTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemBabyCornTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemStrawMushroomsTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas mItemChickenTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemPigTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemCowTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemSheepTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemOstrichTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		
		BitmapTextureAtlas mItemFishTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemSquidTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemScallopsTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemShrimpTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemOysterTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		
		//Special
		BitmapTextureAtlas mItemPearlTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemGoldTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		BitmapTextureAtlas mItemDiamondTextureAtlas = new BitmapTextureAtlas( 128, 128 );
		
		//Status Bar
		BitmapTextureAtlas mStatusBarTextureAtlas = new BitmapTextureAtlas( 512, 128 );
		BitmapTextureAtlas mStatusBarMoneyTextureAtlas = new BitmapTextureAtlas( 256, 64 );
		BitmapTextureAtlas mStatusBarExpTextureAtlas = new BitmapTextureAtlas( 256, 8 );
		
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
				mFarmMapNotOccupyTextureAtlas, context, "tile_not_occupy.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_FARM_NOTOCCUPY, mFarmMapNotOccupyTextureRegion );
		TextureRegion mCouponButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mCouponButtonTextureAtlas, context, "button_coupon.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_COUPONBUTTON, mCouponButtonTextureRegion );
		TextureRegion mSpecialCodeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSpecialCodeButtonTextureAtlas, context, "button_code.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SPECIALCODEBUTTON, mSpecialCodeTextureRegion );
		TextureRegion mSoundOnTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mSoundButtonOnTextureAtlas, context, "button_opensound.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SOUNDBUTTON_ON, mSoundOnTextureRegion );
		TextureRegion mSoundOffTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSoundButtonOffTextureAtlas, context, "button_mute.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SOUNDBUTTON_OFF, mSoundOffTextureRegion );
		TextureRegion mShopTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mShopButtonTextureAtlas, context, "map_market.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SHOPBUTTON, mShopTextureRegion );
		TextureRegion mEmptyFarmTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mEmptyFarmTextureAtlas, context, "empty_farm_texture.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_EMPTY_FARM, mEmptyFarmTextureRegion );
		TextureRegion mLevelUpTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mLevelUpTextureAtlas, context, "popup_level_up.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_LEVEL_UP_DIALOG, mLevelUpTextureRegion );
		TextureRegion mCloseButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mCloseButtonTextureAtlas, context, "button_close.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_CLOSE_BUTTON, mCloseButtonTextureRegion );
		TextureRegion mBackgroundDialogTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBackgroundDialogTextureAtlas, context, "background_dialog.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_BACKGROUND_DIALOG, mBackgroundDialogTextureRegion );
		TextureRegion mSupplyItemWaterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemWaterTextureAtlas, context, "image_popup_plant01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_WATER, mSupplyItemWaterTextureRegion );
		TextureRegion mSupplyItemFertilizertATextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemFertilizertATextureAtlas, context, "image_popup_plant02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_FERTILIZER_A, mSupplyItemFertilizertATextureRegion );
		TextureRegion mSupplyItemFertilizertBTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemFertilizertBTextureAtlas, context, "image_popup_plant03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_FERTILIZER_B, mSupplyItemFertilizertBTextureRegion );
		TextureRegion mSupplyItemStrawTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemStrawTextureAtlas, context, "image_popup_animal01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_STRAW, mSupplyItemStrawTextureRegion );
		TextureRegion mSupplyItemVaccineATextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemVaccineATextureAtlas, context, "image_popup_animal02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_VACCINE_A, mSupplyItemVaccineATextureRegion );
		TextureRegion mSupplyItemVaccineBTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemVaccineBTextureAtlas, context, "image_popup_animal03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_VACCINE_B, mSupplyItemVaccineBTextureRegion );
		TextureRegion mSupplyItemPelletFoodTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemPelletFoodTextureAtlas, context, "image_popup_sea_animal01.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_PELLET_FOOD, mSupplyItemPelletFoodTextureRegion );
		TextureRegion mSupplyItemMicroorganismATextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemMicroorganismATextureAtlas, context, "image_popup_sea_animal02.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_MICROORGANISM_A, mSupplyItemMicroorganismATextureRegion );
		TextureRegion mSupplyItemMicroorganismBTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mSupplyItemMicroorganismBTextureAtlas, context, "image_popup_sea_animal03.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_SUPPLY_MICROORGANISM_B, mSupplyItemMicroorganismBTextureRegion );
		TextureRegion mIconPlusTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mIconPlusTextureAtlas, context, "icon_plus.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ICON_PLUS, mIconPlusTextureRegion );
		
		//Texture Item
		//Seed & Baby
		TextureRegion mItemMorningGlorySeedTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemMorningGlorySeedTextureAtlas, context, "itemid10.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_MORNING_GLORY_SEED, mItemMorningGlorySeedTextureRegion );
		TextureRegion mItemChineseCabbageSeedTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemChineseCabbageSeedTextureAtlas, context, "itemid20.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_CHINESE_CABBAGE_SEED, mItemChineseCabbageSeedTextureRegion );
		TextureRegion mItemPumpkinSeedTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemPumpkinSeedTextureAtlas, context, "itemid30.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_PUMPKIN_SEED , mItemPumpkinSeedTextureRegion );
		TextureRegion mItemBabyCornSeedTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemBabyCornSeedTextureAtlas, context, "itemid40.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_BABY_CORN_SEED, mItemBabyCornSeedTextureRegion );
		TextureRegion mItemStrawMushroomsSeedTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemStrawMushroomsSeedTextureAtlas, context, "itemid50.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_STRAW_MUSHROOMS_SEED, mItemStrawMushroomsSeedTextureRegion );
		
		TextureRegion mItemChickenBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemChickenBabyTextureAtlas, context, "itemid60.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_CHICKEN_BABY, mItemChickenBabyTextureRegion );
		TextureRegion mItemPigBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemPigBabyTextureAtlas, context, "itemid70.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_PIG_BABY, mItemPigBabyTextureRegion );
		TextureRegion mItemCowBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemCowBabyTextureAtlas, context, "itemid80.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_COW_BABY, mItemCowBabyTextureRegion );
		TextureRegion mItemSheepBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemSheepBabyTextureAtlas, context, "itemid90.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_SHEEP_BABY, mItemSheepBabyTextureRegion );
		TextureRegion mItemOstrichBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemOstrichBabyTextureAtlas, context, "itemid100.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_OSTRICH_BABY, mItemOstrichBabyTextureRegion );
		
		TextureRegion mItemFishBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemFishBabyTextureAtlas, context, "itemid110.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_FISH_BABY, mItemFishBabyTextureRegion );
		TextureRegion mItemSquidBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemSquidBabyTextureAtlas, context, "itemid120.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_SQUID_BABY, mItemSquidBabyTextureRegion );
		TextureRegion mItemScallopsBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemScallopsBabyTextureAtlas, context, "itemid130.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_SCALLOPS_BABY, mItemScallopsBabyTextureRegion );
		TextureRegion mItemShrimpBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemShrimpBabyTextureAtlas, context, "itemid140.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_SHRIMP_BABY, mItemShrimpBabyTextureRegion );
		TextureRegion mItemOysterBabyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemOysterBabyTextureAtlas, context, "itemid150.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_OYSTER_BABY, mItemOysterBabyTextureRegion );
		
		//Product
		TextureRegion mItemMorningGloryTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemMorningGloryTextureAtlas , context, "itemid160.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_MORNING_GLORY, mItemMorningGloryTextureRegion );
		TextureRegion mItemPumpkinTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemPumpkinTextureAtlas , context, "itemid170.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_CHINESE_CABBAGE, mItemPumpkinTextureRegion );
		TextureRegion mItemChineseCabbageTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemChineseCabbageTextureAtlas , context, "itemid180.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_PUMPKIN, mItemChineseCabbageTextureRegion );
		TextureRegion mItemBabyCornTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemBabyCornTextureAtlas , context, "itemid190.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_BABY_CORN, mItemBabyCornTextureRegion );
		TextureRegion mItemStrawMushroomsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemStrawMushroomsTextureAtlas , context, "itemid200.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_STRAW_MUSHROOMS, mItemStrawMushroomsTextureRegion );
		
		TextureRegion mItemChickenTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemChickenTextureAtlas, context, "itemid210.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_CHICKEN, mItemChickenTextureRegion );
		TextureRegion mItemPigTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemPigTextureAtlas, context, "itemid220.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_PIG, mItemPigTextureRegion );
		TextureRegion mItemCowTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemCowTextureAtlas, context, "itemid230.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_COW, mItemCowTextureRegion );
		TextureRegion mItemSheepTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemSheepTextureAtlas, context, "itemid240.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_SHEEP, mItemSheepTextureRegion );
		TextureRegion mItemOstrichTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemOstrichTextureAtlas, context, "itemid250.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_OSTRICH, mItemOstrichTextureRegion );
		
		TextureRegion mItemFishTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemFishTextureAtlas, context, "itemid260.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_FISH, mItemFishTextureRegion );
		TextureRegion mItemSquidTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemSquidTextureAtlas, context, "itemid270.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_SQUID, mItemSquidTextureRegion );
		TextureRegion mItemScallopsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemScallopsTextureAtlas, context, "itemid280.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_SCALLOPS, mItemScallopsTextureRegion );
		TextureRegion mItemShrimpTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemShrimpTextureAtlas, context, "itemid290.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_SHRIMP, mItemShrimpTextureRegion );
		TextureRegion mItemOysterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemOysterTextureAtlas, context, "itemid300.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_OYSTER, mItemOysterTextureRegion );
		
		//Special item
		TextureRegion mItemPearlTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemPearlTextureAtlas, context, "itemid340.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_PEARL, mItemPearlTextureRegion );
		TextureRegion mItemGoldTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemGoldTextureAtlas, context, "itemid350.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_GOLD, mItemGoldTextureRegion );
		TextureRegion mItemDiamondTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mItemDiamondTextureAtlas, context, "itemid360.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_ITEM_DIAMOND, mItemDiamondTextureRegion );
		
		//Texture Status Bar
		TextureRegion statusBar = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mStatusBarTextureAtlas, context, "controller_name.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_STATUS_BAR ,statusBar );
		TextureRegion statusBarMoney = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mStatusBarMoneyTextureAtlas, context, "controller_monney.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_STATUS_BAR_MONEY ,statusBarMoney );
		TextureRegion statusBarExp = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mStatusBarExpTextureAtlas, context, "bar_exp.png", 0, 0 );
		textureCollection.put( TextureVar.TEXTURE_STATUS_BAR_EXP ,statusBarExp );
		
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
		engine.getTextureManager().loadTexture( mSoundButtonOnTextureAtlas );
		engine.getTextureManager().loadTexture( mSoundButtonOffTextureAtlas );
		engine.getTextureManager().loadTexture( mShopButtonTextureAtlas );
		engine.getTextureManager().loadTexture( mEmptyFarmTextureAtlas );
		engine.getTextureManager().loadTexture( mLevelUpTextureAtlas );
		engine.getTextureManager().loadTexture( mCloseButtonTextureAtlas );
		engine.getTextureManager().loadTexture( mBackgroundDialogTextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemWaterTextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemFertilizertATextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemFertilizertBTextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemStrawTextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemVaccineATextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemVaccineBTextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemPelletFoodTextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemMicroorganismATextureAtlas );
		engine.getTextureManager().loadTexture( mSupplyItemMicroorganismBTextureAtlas );
		engine.getTextureManager().loadTexture( mIconPlusTextureAtlas );
		
	  //Texture Item
		//Seed & baby
		engine.getTextureManager().loadTexture( mItemMorningGlorySeedTextureAtlas );
		engine.getTextureManager().loadTexture( mItemPumpkinSeedTextureAtlas );
		engine.getTextureManager().loadTexture( mItemChineseCabbageSeedTextureAtlas );
		engine.getTextureManager().loadTexture( mItemBabyCornSeedTextureAtlas );
		engine.getTextureManager().loadTexture( mItemStrawMushroomsSeedTextureAtlas );
		
		engine.getTextureManager().loadTexture( mItemChickenBabyTextureAtlas );
		engine.getTextureManager().loadTexture( mItemPigBabyTextureAtlas );
		engine.getTextureManager().loadTexture( mItemCowBabyTextureAtlas );
		engine.getTextureManager().loadTexture( mItemSheepBabyTextureAtlas );
		engine.getTextureManager().loadTexture( mItemOstrichBabyTextureAtlas );
		
		engine.getTextureManager().loadTexture( mItemFishBabyTextureAtlas );
		engine.getTextureManager().loadTexture( mItemSquidBabyTextureAtlas );
		engine.getTextureManager().loadTexture( mItemScallopsBabyTextureAtlas );
		engine.getTextureManager().loadTexture( mItemShrimpBabyTextureAtlas );
		engine.getTextureManager().loadTexture( mItemOysterBabyTextureAtlas );
		
		//Product
		engine.getTextureManager().loadTexture( mItemMorningGloryTextureAtlas );
		engine.getTextureManager().loadTexture( mItemPumpkinTextureAtlas );
		engine.getTextureManager().loadTexture( mItemChineseCabbageTextureAtlas );
		engine.getTextureManager().loadTexture( mItemBabyCornTextureAtlas );
		engine.getTextureManager().loadTexture( mItemStrawMushroomsTextureAtlas );
		
		engine.getTextureManager().loadTexture( mItemChickenTextureAtlas );
		engine.getTextureManager().loadTexture( mItemPigTextureAtlas );
		engine.getTextureManager().loadTexture( mItemCowTextureAtlas );
		engine.getTextureManager().loadTexture( mItemSheepTextureAtlas );
		engine.getTextureManager().loadTexture( mItemOstrichTextureAtlas );
		
		engine.getTextureManager().loadTexture( mItemFishTextureAtlas );
		engine.getTextureManager().loadTexture( mItemSquidTextureAtlas );
		engine.getTextureManager().loadTexture( mItemScallopsTextureAtlas );
		engine.getTextureManager().loadTexture( mItemShrimpTextureAtlas );
		engine.getTextureManager().loadTexture( mItemOysterTextureAtlas );
		
		//Special
		engine.getTextureManager().loadTexture( mItemPearlTextureAtlas );
		engine.getTextureManager().loadTexture( mItemGoldTextureAtlas );
		engine.getTextureManager().loadTexture( mItemDiamondTextureAtlas );
		
		//Texture Status Bar
		engine.getTextureManager().loadTexture( mStatusBarTextureAtlas );
		engine.getTextureManager().loadTexture( mStatusBarMoneyTextureAtlas );
		engine.getTextureManager().loadTexture( mStatusBarExpTextureAtlas );
		
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
		Font statusBarFont = new Font(mFontTexture, app.getTextFont(), 30, true, Color.BLACK);
		fontCollection.put( STATUS_BAR_FONT, statusBarFont );
		engine.getTextureManager().loadTexture( mFontTexture );
		engine.getFontManager().loadFont(font);
		engine.getFontManager().loadFont(statusBarFont);
	}
	
	public static void loadMusic(Context context, Engine engine, HashMap<String, Music> musicCollection){
		MusicFactory.setAssetBasePath( "mfx/" );
		try {
			Music mBG = MusicFactory.createMusicFromAsset( engine.getMusicManager(), context, "bg.mp3" );
			mBG.setLooping( true );
			musicCollection.put( SOUND_BG, mBG );
			Music mBonus = MusicFactory.createMusicFromAsset( engine.getMusicManager(), context, "bonus.mp3" );
			mBonus.setLooping( false );
			musicCollection.put( SOUND_BONUS, mBonus );
			Music mButton = MusicFactory.createMusicFromAsset( engine.getMusicManager(), context, "button.mp3" );
			mButton.setLooping( false );
			musicCollection.put( SOUND_BUTTON, mButton );
			Music mChooseClick = MusicFactory.createMusicFromAsset( engine.getMusicManager(), context, "choose_click.mp3" );
			mChooseClick.setLooping( false );
			musicCollection.put( SOUND_CHOOSE_CLICK, mChooseClick );
			Music mCoin = MusicFactory.createMusicFromAsset( engine.getMusicManager(), context, "coin.mp3" );
			mCoin.setLooping( false );
			musicCollection.put( SOUND_COIN, mCoin );
			Music mLandClick = MusicFactory.createMusicFromAsset( engine.getMusicManager(), context, "Land_click.mp3" );
			mLandClick.setLooping( false );
			musicCollection.put( SOUND_LAND_CLICK, mLandClick );
			Music mLevelUp = MusicFactory.createMusicFromAsset( engine.getMusicManager(), context, "level_up.mp3" );
			mLevelUp.setLooping( false );
			musicCollection.put( SOUND_LEVEL_UP, mLevelUp );
			Music mOnOff = MusicFactory.createMusicFromAsset( engine.getMusicManager(), context, "on_off.mp3" );
			mOnOff.setLooping( false );
			musicCollection.put( SOUND_ON_OF, mOnOff );
		} catch ( IllegalStateException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {	
			e.printStackTrace();
		}
	}
	
	public static void loadTiledTexture(Context context, Engine engine, HashMap<String, TiledTextureRegion> titedTextureCollection){
		BitmapTextureAtlas mStatusNumberLevelTextureAtlas = new BitmapTextureAtlas( 512, 64 );
		BitmapTextureAtlas mNumberLevelUpTextureAtlas = new BitmapTextureAtlas( 1024, 128 );
		BitmapTextureAtlas mButterflyAnimateTextureAtlas = new BitmapTextureAtlas( 1024, 1024 );
		
		TiledTextureRegion statusBarNumberLevel = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mStatusNumberLevelTextureAtlas, context, "number_controller_level.png", 0, 0, 10, 1 );
		titedTextureCollection.put( TextureVar.TILEDTEXTURE_STATUS_BAR_NUMBER_LEVEL ,statusBarNumberLevel );
		TiledTextureRegion numberLevelUp = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mNumberLevelUpTextureAtlas, context, "number_popup_level.png", 0, 0, 10, 1 );
		titedTextureCollection.put( TextureVar.TILEDTEXTURE_NUMBER_LEVEL_UP ,numberLevelUp );
		TiledTextureRegion mButterflyAnimateTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
				mButterflyAnimateTextureAtlas, context, "butterfly_animation.png", 0, 0, 4, 9 );
		titedTextureCollection.put( TextureVar.TEXTURE_BUTTERFLY_ANIMATE ,mButterflyAnimateTextureRegion );
		
		engine.getTextureManager().loadTexture( mStatusNumberLevelTextureAtlas );
		engine.getTextureManager().loadTexture( mNumberLevelUpTextureAtlas );
		engine.getTextureManager().loadTexture( mButterflyAnimateTextureAtlas );
	}
}
