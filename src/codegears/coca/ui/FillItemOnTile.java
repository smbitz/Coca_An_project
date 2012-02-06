package codegears.coca.ui;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.FadeOutModifier;
import org.anddev.andengine.entity.modifier.ParallelEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.IPathModifierListener;
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
	
	private FillItemListener listener;

	public FillItemOnTile(float pX, float pY, TextureRegion supplyTextureRegion, 
			TextureRegion iconPlusTextureRegion){
		super((pX+SET_ITEM_IMAGE_POSITION_X), (pY+SET_ITEM_IMAGE_POSITION_Y), supplyTextureRegion);
		
		itemImagePositionX = (int) (pX + SET_ITEM_IMAGE_POSITION_X);
		itemImagePositionY = (int) (pY + SET_ITEM_IMAGE_POSITION_Y);
		
		Sprite iconPlus = new Sprite(itemImagePositionX, itemImagePositionY, iconPlusTextureRegion);
		this.attachChild( iconPlus );
		
		final Path path = new Path( SET_ITEM_MOVE_POSITION_TIME ).to(itemImagePositionX, itemImagePositionY)
  	.to( itemImagePositionX, itemImagePositionY + SET_ITEM_IMAGE_POSITION_Y_MOVE_1);
	
		PathModifier p = new PathModifier(SET_ITEM_MOVE_POSITION_SPEED, path);
		p.setPathModifierListener( new IPathModifierListener(){

			@Override
			public void onPathFinished( PathModifier arg0, IEntity arg1 ) {
				if(listener != null){
					listener.onFillItemComplete();
				}
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
		this.registerEntityModifier( new ParallelEntityModifier( p,
					new FadeOutModifier(SET_ITEM_MOVE_POSITION_SPEED) ) );
	}

	public void setOnFillItemListener(FillItemListener listener){
		this.listener = listener;
	}
}