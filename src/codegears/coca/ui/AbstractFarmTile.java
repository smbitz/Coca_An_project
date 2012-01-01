package codegears.coca.ui;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import codegears.coca.data.TextureVar;
import codegears.coca.data.Tile;

public class AbstractFarmTile extends Sprite {

	private Tile data;
	private FarmTileListener listener;

	public AbstractFarmTile( float pX, float pY, TextureRegion pTextureRegion ) {
		super( pX, pY, pTextureRegion );
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

	@Override
	public boolean onAreaTouched( TouchEvent pSceneTouchEvent, float pTouchAreaLocalX,
					float pTouchAreaLocalY ) {
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
		return false;
	}
}
