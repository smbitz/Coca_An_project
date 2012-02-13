package codegears.coca.ui;

import org.anddev.andengine.entity.modifier.DelayModifier;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.Tile;

public class SeaAnimalAnimation extends Sprite {
	
	private static final int SET_ITEM_IMAGE_POSITION_Y_MOVE_1 = 15;
	private static final int SET_ITEM_MOVE_POSITION_TIME = 3;
	private static final float SET_ITEM_MOVE_POSITION_SPEED = 1.3f;
	
	private float itemMoveSpeed;
	private int itemMoveY1;
	
	public SeaAnimalAnimation(float pX, float pY, TextureRegion pTextureRegion, int tileStatus) {
		super(pX, pY, pTextureRegion);
		
		itemMoveSpeed = SET_ITEM_MOVE_POSITION_SPEED;
		itemMoveY1 = SET_ITEM_IMAGE_POSITION_Y_MOVE_1;
		
		if( tileStatus==Tile.BUILDING_ROTTED ){
			itemMoveSpeed+=2.5;
			itemMoveY1-=10;
		}
		
		final Path path = new Path( SET_ITEM_MOVE_POSITION_TIME ).to(pX, pY)
  	.to( pX, pY + itemMoveY1 )
  	.to( pX, pY);
		
		this.registerEntityModifier( new LoopEntityModifier( new SequenceEntityModifier (
				new DelayModifier( (float) (Math.random()*1) ),
				new PathModifier(  itemMoveSpeed, path )  ) ) );
	}
}
