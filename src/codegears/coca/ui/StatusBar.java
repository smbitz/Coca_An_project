package codegears.coca.ui;

import java.util.HashMap;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import codegears.coca.data.TextureVar;
import codegears.coca.util.LoadResource;

public class StatusBar extends Sprite {

	private static final String TEXT_AFTER_NAME = "'s Land";
	
	private String farmName = "Test Farm Name";
	private int level;
	private float exp;	//0 - 1
	private int money;
	
	private BitmapNumber levelBitmap;
	private Sprite statusBarBitmap;
	private Sprite moneyBarBitmap;
	private ChangeableText nameText;
	private ChangeableText moneyText;
	private ExpBar expBar;
	
	public StatusBar( float pX, float pY, HashMap<String, TextureRegion> textureCollection, 
							HashMap<String, Font> fontCollection, 
							HashMap<String, TiledTextureRegion> tiledTextureCollection ) {
		super( pX, pY, textureCollection.get( TextureVar.TEXTURE_STATUS_BAR ) );
		//create status bar
		//statusBarBitmap = new Sprite(0, 0, textureCollection.get( TextureVar.TEXTURE_STATUS_BAR ));
		//this.attachChild( statusBarBitmap );
		//create status bar
		moneyBarBitmap = new Sprite(300, 15, textureCollection.get( TextureVar.TEXTURE_STATUS_BAR_MONEY ));
		this.attachChild( moneyBarBitmap );
		//create level bitmap
		//levelBitmap = new Sprite(0, 0, textureCollection.get( TextureVar.TEXTURE_TILE_BABY_CORN_01 ));
		//this.attachChild( levelBitmap );
		//create farmName text
		nameText = new ChangeableText( 150, 20, fontCollection.get( LoadResource.STATUS_BAR_FONT ), farmName);
		this.attachChild( nameText );
		
		//Money text
		moneyText = new ChangeableText(370, 30, fontCollection.get( LoadResource.STATUS_BAR_FONT ), farmName);
		this.attachChild( moneyText );
		
		//create exp bar
		expBar = new ExpBar(127, 58, textureCollection.get( TextureVar.TEXTURE_STATUS_BAR_EXP ));
		this.attachChild( expBar );
		
		//Level number
		levelBitmap = new BitmapNumber( 14, 24, textureCollection.get( TextureVar.TEXTURE_EMPTY_FARM ), tiledTextureCollection.get( TextureVar.TILEDTEXTURE_STATUS_BAR_NUMBER_LEVEL ), 3);
		this.attachChild( levelBitmap );
	}
	
	public void setFarmName(String name){
		this.farmName = name+TEXT_AFTER_NAME;
		nameText.setText( name+TEXT_AFTER_NAME );
	}
	
	public void setLevel(int level){
		this.level = level;
		levelBitmap.setData( level, BitmapNumber.ALIGN_CENTER, -6);
	}
	
	public void setExp(float exp){
		this.exp = exp;
		expBar.setExpPercent( exp );
	}
	
	public void setMoney(int money){
		this.money = money;
		moneyText.setText( String.valueOf( money ) );
	}

}