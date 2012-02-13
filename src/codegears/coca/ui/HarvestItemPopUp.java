package codegears.coca.ui;

import java.util.ArrayList;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.FadeOutModifier;
import org.anddev.andengine.entity.modifier.ParallelEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class HarvestItemPopUp extends Sprite {
	
	private static final float SET_ITEM_MOVE_POSITION_SPEED = 0.7f;
	private static final int SET_ITEM_IMAGE_POSITION_Y_MOVE_1 = -75;
	private static final int SET_ITEM_MOVE_POSITION_TIME = 2;
	private static final int SET_ITEM_IMAGE_POSITION_X = 95;
	private static final int SET_ITEM_IMAGE_POSITION_Y = 50;
	
	private HarvestItemListener listener;
	private int countAnimationLoop;
	private int countAllAnimation;
	private int itemImagePositionX;
	private int itemImagePositionY;
	
	private ArrayList<Path> arrayOfPath;
	
	public HarvestItemPopUp(float pX, float pY, TextureRegion pTextureRegion,
			ArrayList<TextureRegion> allHarvestItemTextureRegion) {
		super(pX, pY, pTextureRegion);
		
		arrayOfPath = new ArrayList<Path>();
		
		countAnimationLoop = 0;
		countAllAnimation = allHarvestItemTextureRegion.size();
		
		itemImagePositionX = (int) (pX + SET_ITEM_IMAGE_POSITION_X);
		itemImagePositionY = (int) (pY + SET_ITEM_IMAGE_POSITION_Y);
		
		Path path1 = new Path( 3 ).to( itemImagePositionX, itemImagePositionY )
  	.to( itemImagePositionX +50 , itemImagePositionY - 75 )
  	.to( itemImagePositionX +60 , itemImagePositionY - 65 );
		arrayOfPath.add( path1 );
		
		Path path2 = new Path( 3 ).to( itemImagePositionX, itemImagePositionY )
  	.to( itemImagePositionX -50 , itemImagePositionY - 75 )
  	.to( itemImagePositionX -60 , itemImagePositionY - 65 );
		arrayOfPath.add( path2 );
		
		for( TextureRegion fetchHarvestImageItem:allHarvestItemTextureRegion ){
			Sprite newSprite = new Sprite(itemImagePositionX, itemImagePositionY, fetchHarvestImageItem);
			
			PathModifier newPathModifier = new PathModifier(SET_ITEM_MOVE_POSITION_SPEED, arrayOfPath.get((int) (Math.random()*2)));
			newPathModifier.setPathModifierListener( new IPathModifierListener(){
				@Override
				public void onPathFinished( PathModifier arg0, IEntity arg1 ) {
					if( countAnimationLoop==(countAllAnimation-1) ){
						listener.onHarvestItemAnimationComplete( HarvestItemPopUp.this );
					}
					countAnimationLoop++;
				}

				@Override
				public void onPathStarted( PathModifier arg0, IEntity arg1 ) {
				}

				@Override
				public void onPathWaypointFinished( PathModifier arg0, IEntity arg1, int arg2 ) {
				}

				@Override
				public void onPathWaypointStarted( PathModifier arg0, IEntity arg1, int arg2 ) {
				}
			});
			
			newSprite.registerEntityModifier( newPathModifier );
			this.attachChild(newSprite);
		}
	}
	
	public void setOnHarvesItemListener( HarvestItemListener setListener ){
		this.listener = setListener;
	}
}
