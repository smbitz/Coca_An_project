package codegears.coca.ui;

import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.os.AsyncTask;

import codegears.coca.GameActivity;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Tile;

public class AbstractFarmTile extends Sprite {

	private Tile data;
	private FarmTileListener listener;
	private boolean startTouch;
	private float touchX;
	private float touchY;
	private int state;
	private float tilePositionX;
	private float tilePositionY;

	public AbstractFarmTile( float pX, float pY, TextureRegion pTextureRegion ) {
		super( pX, pY, pTextureRegion );
		startTouch = false;
		state = GameActivity.STATE_NORMAL;
		tilePositionX = pX;
		tilePositionY = pY;
	}

	public void setFarmTileListener(FarmTileListener l){
		listener = l;
	}
	
	public void setData( Tile t ) {
		data = t;
	}

	public Tile getData() {
		return data;
	}
	
	public void setMoveState(){
		state = GameActivity.STATE_MOVE;
	}
	
	public void setNormalState(){
		state = GameActivity.STATE_NORMAL;
	}
	
	private void processTouch(){
		if(data.getBuildingStatus() == Tile.BUILDING_NOTOCCUPY){
			listener.onPurchaseRequest( data );
		} else if(data.getBuildingStatus() == Tile.BUILDING_EMPTY){
			listener.onBuildRequest( data );
		} else if(data.getBuildingStatus() == Tile.BUILDING_PROCESS1){
			if(data.getSupply()<=0){
				listener.onSupplyRequest( data, tilePositionX, tilePositionY  );
			}else{
				listener.onAddItemRequest( data );
			}
		} else if(data.getBuildingStatus() == Tile.BUILDING_PROCESS2){
			if(data.getSupply()<=0){
				listener.onSupplyRequest( data, tilePositionX, tilePositionY );
			}else{
				listener.onAddItemRequest( data );
			}
		} else if(data.getBuildingStatus() == Tile.BUILDING_COMPLETED){
			listener.onHarvestRequest( data );
		} else if(data.getBuildingStatus() == Tile.BUILDING_ROTTED){
			listener.onHarvestRequest( data );
		}
	}

	protected boolean touchOnPreferedRegion(float localX, float localY){
		System.out.println("Abstract Farm Touch");
		/*
		Line l1 = new Line(30, 115, 145, 50);
		l1.setColor( 1, 0, 0 );
		this.attachChild( l1 );
		Line l2 = new Line(145, 50, 260, 115);
		l2.setColor( 1, 0, 0 );
		this.attachChild( l2 );
		*/
		float scale = this.getParent().getScaleX();
		float x = localX;
		float y = localY;
		/*
		Line l3 = new Line(x, y, x, y+5);
		l3.setColor( 0, 0, 1 );
		l3.setLineWidth( 5 );
		this.attachChild( l3 );
		 */
		if(y > 50){
			if(( y > (-0.5 * x) + 130 ) && ( y < (-0.5 * x) + 245 ) && 
							(y < ( 0.55 * x) + 98 ) && (y > ( 0.55 * x) - 17 )){
				return true;
			}
			return false;
		}
		return false;
	}
	
	@Override
	public boolean onAreaTouched( TouchEvent pSceneTouchEvent, float pTouchAreaLocalX,
					float pTouchAreaLocalY ) {
		if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN){
			if(touchOnPreferedRegion(pTouchAreaLocalX, pTouchAreaLocalY)){
				startTouch = true;
				touchX = pSceneTouchEvent.getX();
				touchY = pSceneTouchEvent.getY();				
			}
		}
		if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP){
			if((startTouch) && (30 >= Math.abs( touchX - pSceneTouchEvent.getX() )) &&
							(30 >= Math.abs( touchY - pSceneTouchEvent.getY() ))){
				if(state == GameActivity.STATE_NORMAL){
					processTouch();
				} else if(state == GameActivity.STATE_MOVE){
					listener.onMoveRequest(data);
				}
				startTouch = false;
			}
		}	
		return false;
	}
	

}
