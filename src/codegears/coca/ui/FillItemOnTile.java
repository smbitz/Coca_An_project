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
	
	private static final int SET_ICON_PLUS_1_POSITION_X = 60;
	private static final int SET_ICON_PLUS_1_POSITION_Y = -25;
	private static final int SET_ICON_PLUS_2_POSITION_X = 75;
	private static final int SET_ICON_PLUS_2_POSITION_Y = -50;
	
	private static final float SET_SCALE_ICON_PLUS_2 = 0.5f;
	
	private int itemImagePositionX;
	private int itemImagePositionY;
	
	private FillItemListener listener;

	public FillItemOnTile(float pX, float pY, TextureRegion supplyTextureRegion, 
			TextureRegion textureIconPlusRegion){
		super((pX+SET_ITEM_IMAGE_POSITION_X), (pY+SET_ITEM_IMAGE_POSITION_Y), supplyTextureRegion);
		
		itemImagePositionX = (int) (pX + SET_ITEM_IMAGE_POSITION_X);
		itemImagePositionY = (int) (pY + SET_ITEM_IMAGE_POSITION_Y);
		
		Sprite iconPlus1 = new Sprite(SET_ICON_PLUS_1_POSITION_X, SET_ICON_PLUS_1_POSITION_Y, textureIconPlusRegion);
		Sprite iconPlus2 = new Sprite(SET_ICON_PLUS_2_POSITION_X, SET_ICON_PLUS_2_POSITION_Y, textureIconPlusRegion);
		
		iconPlus2.setScale( SET_SCALE_ICON_PLUS_2 );
		
		iconPlus1.registerEntityModifier( new FadeOutModifier(SET_ITEM_MOVE_POSITION_SPEED) );
		iconPlus2.registerEntityModifier( new FadeOutModifier(SET_ITEM_MOVE_POSITION_SPEED) );
		
		this.attachChild(iconPlus1);
		this.attachChild(iconPlus2);
		
		final Path path = new Path( SET_ITEM_MOVE_POSITION_TIME ).to(itemImagePositionX, itemImagePositionY)
  	.to( itemImagePositionX, itemImagePositionY + SET_ITEM_IMAGE_POSITION_Y_MOVE_1);
	
		PathModifier p = new PathModifier(SET_ITEM_MOVE_POSITION_SPEED, path);
		p.setPathModifierListener( new IPathModifierListener(){

			@Override
			public void onPathFinished( PathModifier arg0, IEntity arg1 ) {
				if(listener != null){
					listener.onFillItemAnimationComplete( FillItemOnTile.this );
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