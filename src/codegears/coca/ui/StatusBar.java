package codegears.coca.ui;

import java.util.HashMap;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.TextureVar;

public class StatusBar extends Sprite {

	private String farmName = "Test Farm Name";
	private int level;
	private float exp;	//0 - 1
	private int money;
	
	private Sprite levelBitmap;
	private Sprite statusBarBitmap;
	private Sprite moneyBarBitmap;
	private ChangeableText nameText;
	private ChangeableText moneyText;
	private Sprite expBar;
	
	public StatusBar( float pX, float pY, HashMap<String, TextureRegion> textureCollection, HashMap<String, Font> fontCollection ) {
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
		nameText = new ChangeableText( 150, 20, fontCollection.get( "TEST_FONT" ), farmName);
		nameText.setColor(0, 0, 0);
		this.attachChild( nameText );
		
		//Money text
		moneyText = new ChangeableText(370, 30, fontCollection.get( "TEST_FONT" ), farmName);
		moneyText.setColor(0, 0, 0);
		this.attachChild( moneyText );
		
		//create exp bar
		expBar = new Sprite(120, 60, textureCollection.get( TextureVar.TEXTURE_STATUS_BAR_EXP ));
		this.attachChild( expBar );
		
		//Level number
		levelBitmap = new Sprite( 10, 20, textureCollection.get( TextureVar.TEXTURE_STATUS_BAR_NUMBER_LEVEL ));
		this.attachChild( levelBitmap );
	}
	
	public void setFarmName(String name){
		this.farmName = name;
		nameText.setText( name );
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setExp(float exp){
		this.exp = exp;
	}
	
	public void setMoney(int money){
		this.money = money;
		moneyText.setText( String.valueOf( money ) );
	}

}