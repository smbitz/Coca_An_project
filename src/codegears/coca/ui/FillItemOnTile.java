package codegears.coca.ui;

import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.FadeOutModifier;
import org.anddev.andengine.entity.modifier.ParallelEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class FillItemOnTile extends Sprite {
	
	private static final int SET_ITEM_IMAGE_POSITION_X = 80;
	private static final int SET_ITEM_IMAGE_POSITION_Y = (-30);
	
	private static final int SET_ITEM_IMAGE_POSITION_Y_MOVE_1 = 75;
	
	private static final int SET_ITEM_MOVE_POSITION_TIME = 2;
	private static final float SET_ITEM_MOVE_POSITION_SPEED = (float) 0.7;
	
	private int itemImagePositionX;
	private int itemImagePositionY;

	public FillItemOnTile(float pX, float pY, TextureRegion pTextureRegion){
		super((pX+SET_ITEM_IMAGE_POSITION_X), (pY+SET_ITEM_IMAGE_POSITION_Y), pTextureRegion);
		
		itemImagePositionX = (int) (pX + SET_ITEM_IMAGE_POSITION_X);
		itemImagePositionY = (int) (pY + SET_ITEM_IMAGE_POSITION_Y);
		
		final Path path = new Path( SET_ITEM_MOVE_POSITION_TIME ).to(itemImagePositionX, itemImagePositionY)
  	.to( itemImagePositionX, itemImagePositionY + SET_ITEM_IMAGE_POSITION_Y_MOVE_1);
		
		this.registerEntityModifier( new ParallelEntityModifier( new PathModifier(SET_ITEM_MOVE_POSITION_SPEED, path),
					new FadeOutModifier(SET_ITEM_MOVE_POSITION_SPEED) ) );
	}

}
