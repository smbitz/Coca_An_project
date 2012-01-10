package codegears.coca.ui;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.os.AsyncTask;

import codegears.coca.data.TextureVar;
import codegears.coca.data.Tile;

public class AbstractFarmTile extends Sprite {

	private Tile data;
	private FarmTileListener listener;
	private boolean startTouch;
	private float touchX;
	private float touchY;

	public AbstractFarmTile( float pX, float pY, TextureRegion pTextureRegion ) {
		super( pX, pY, pTextureRegion );
		startTouch = false;
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
	
	private void processTouch(){
		if(data.getBuildingStatus() == Tile.BUILDING_NOTOCCUPY){
			listener.onPurchaseRequest( data );
		} else if(data.getBuildingStatus() == Tile.BUILDING_EMPTY){
			listener.onBuildRequest( data );
		} else if(data.getBuildingStatus() == Tile.BUILDING_PROCESS1){
			if(data.getSupply()<=0){
				listener.onSupplyRequest( data );
			}else{
				listener.onAddItemRequest( data );
			}
		} else if(data.getBuildingStatus() == Tile.BUILDING_PROCESS2){
			if(data.getSupply()<=0){
				listener.onSupplyRequest( data );
			}else{
				listener.onAddItemRequest( data );
			}
		} else if(data.getBuildingStatus() == Tile.BUILDING_COMPLETED){
			listener.onHarvestRequest( data );
		} else if(data.getBuildingStatus() == Tile.BUILDING_ROTTED){
			listener.onHarvestRequest( data );
		}
	}

	@Override
	public boolean onAreaTouched( TouchEvent pSceneTouchEvent, float pTouchAreaLocalX,
					float pTouchAreaLocalY ) {
		if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN){
			System.out.println("Touch Down Tile");
			startTouch = true;
			touchX = pSceneTouchEvent.getX();
			touchY = pSceneTouchEvent.getY();
		}
		if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP){
			System.out.println("Touch Up Tile");
			if((startTouch) && (30 >= Math.abs( touchX - pSceneTouchEvent.getX() )) &&
							(30 >= Math.abs( touchY - pSceneTouchEvent.getY() ))){
				processTouch();
				startTouch = false;
			}
		}	
		return false;
	}
	

}
