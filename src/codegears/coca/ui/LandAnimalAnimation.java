package codegears.coca.ui;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.DelayModifier;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.MoveByModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.modifier.IModifier;

import codegears.coca.data.Tile;

public class LandAnimalAnimation extends Sprite {

	private static final int SET_ITEM_MOVE_POSITION_TIME = 5;
	private static final int SET_ITEM_IMAGE_POSITION_X_MOVE_1 = 15;
	private static final int SET_ITEM_IMAGE_POSITION_Y_MOVE_1 = 15;
	private static final int SET_ITEM_IMAGE_POSITION_X_MOVE_2 = 30;
	private static final int SET_ITEM_IMAGE_POSITION_Y_MOVE_2 = 30;
	private static final float SET_ITEM_MOVE_POSITION_SPEED = 5f;
	
	public LandAnimalAnimation(float pX, float pY, TextureRegion pTextureRegion, int tileStatus) {
		super(pX, pY, pTextureRegion);
		
		if( tileStatus!=Tile.BUILDING_ROTTED ){
			Path path = new Path( SET_ITEM_MOVE_POSITION_TIME ).to(pX, pY)
	  	.to( pX, pY + SET_ITEM_IMAGE_POSITION_Y_MOVE_1 )
	  	.to( pX - SET_ITEM_IMAGE_POSITION_X_MOVE_1, pY - SET_ITEM_IMAGE_POSITION_Y_MOVE_1 )
	  	.to( pX+ SET_ITEM_IMAGE_POSITION_X_MOVE_2, pY )
	  	.to( pX, pY);
			
			PathModifier newPathModifier = new PathModifier(  SET_ITEM_MOVE_POSITION_SPEED, path );
			newPathModifier.setPathModifierListener( new IPathModifierListener() {
				
				@Override
				public void onPathWaypointStarted(PathModifier arg0, IEntity arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPathWaypointFinished(PathModifier arg0, IEntity arg1, int arg2) {
					// TODO Auto-generated method stub
					if( arg2==1 ){
						LandAnimalAnimation.this.setFlippedHorizontal( false );
					}else if( arg2==2 ){
						LandAnimalAnimation.this.setFlippedHorizontal( true );
					}
				}
				
				@Override
				public void onPathStarted(PathModifier arg0, IEntity arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPathFinished(PathModifier arg0, IEntity arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			
			this.registerEntityModifier( new LoopEntityModifier( new SequenceEntityModifier ( 
					new DelayModifier( (float) (Math.random()*1) ),
					newPathModifier ) ) );
		}
	}

}
