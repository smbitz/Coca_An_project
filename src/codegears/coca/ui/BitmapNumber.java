package codegears.coca.ui;

import java.util.ArrayList;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class BitmapNumber extends Sprite {

	public static final int ALIGN_CENTER = 1;
	public static final int ALIGN_LEFT = 2;
	public static final int ALIGN_RIGHT = 3;
	private int number;
	private ArrayList<TiledSprite> spriteList;
	private TiledTextureRegion numberTexture;
	private int maxDigit;
	
	public BitmapNumber( float pX, float pY, TextureRegion pTextureRegion, TiledTextureRegion numberTexture, int maxDigit ) {
		super( pX, pY, pTextureRegion );
		this.numberTexture = numberTexture;
		spriteList = new ArrayList<TiledSprite>();
		for(int i = 0; i < maxDigit; i++){
			TiledSprite tSprite = new TiledSprite(0, 0, numberTexture.deepCopy());
			spriteList.add( tSprite );
			this.attachChild( tSprite );
		}
		this.maxDigit = maxDigit;
	}
	
	private int getCorrectTileIndex(int number){
		if(number == 0){
			return 9;
		} else {
			return number - 1;
		}
	}
	
	public void setData(int number, int align, int spaceAdjust){
		this.number = number;
		int digitCount = 0;
		int copyNumber = number;
		int charWidth = (int)spriteList.get( 0 ).getWidth() + spaceAdjust;
		while(copyNumber > 0){
			copyNumber /= 10;
			digitCount++;
		}
		for(int i = 0; i < maxDigit; i++){
			TiledSprite s = spriteList.get( i );
			if(number > 0){
				int singleDigit = number % 10;
				s.setVisible( true );
				s.setCurrentTileIndex( getCorrectTileIndex( singleDigit ) );
				if(align == ALIGN_LEFT){
					s.setPosition( (digitCount - i ) * charWidth, 0 );
				} else if(align == ALIGN_CENTER){
					s.setPosition( (digitCount - i ) * charWidth - (charWidth * digitCount) / 2, 0 );
				} else if(align == ALIGN_RIGHT){
					s.setPosition( (0 - i ) * charWidth, 0 );
				}
				number = number / 10;
			} else {
				s.setVisible( false );
			}
		}
	}
}