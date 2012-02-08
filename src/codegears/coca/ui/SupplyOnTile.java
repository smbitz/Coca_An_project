package codegears.coca.ui;

import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class SupplyOnTile extends Sprite {

	private static final int SET_ITEM_IMAGE_POSITION_X = 80;
	private static final int SET_ITEM_IMAGE_POSITION_Y = (-30);
	
	private static final int SET_ITEM_IMAGE_POSITION_Y_MOVE_1 = 15;
	
	private static final int SET_ITEM_MOVE_POSITION_TIME = 3;
	private static final float SET_ITEM_MOVE_POSITION_SPEED = (float) 1.3;
	
	private int itemImagePositionX;
	private int itemImagePositionY;
	
	public SupplyOnTile(float pX, float pY, TextureRegion textureSupplyRegion) {
		super(( pX + SET_ITEM_IMAGE_POSITION_X ), ( pY + SET_ITEM_IMAGE_POSITION_Y ), textureSupplyRegion);
		
		itemImagePositionX = (int) (pX + SET_ITEM_IMAGE_POSITION_X);
		itemImagePositionY = (int) (pY + SET_ITEM_IMAGE_POSITION_Y);
		
	  final Path path = new Path( SET_ITEM_MOVE_POSITION_TIME ).to(itemImagePositionX, itemImagePositionY)
	  	.to( itemImagePositionX, itemImagePositionY + SET_ITEM_IMAGE_POSITION_Y_MOVE_1 )
	  	.to( itemImagePositionX, itemImagePositionY);
		
		this.registerEntityModifier( new LoopEntityModifier( 
			new PathModifier(  SET_ITEM_MOVE_POSITION_SPEED, path ) ) );
	}
}
