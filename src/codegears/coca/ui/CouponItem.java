package codegears.coca.ui;

import codegears.coca.R;
import codegears.coca.data.ItemManager;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class CouponItem extends LinearLayout {

	public static final int STATE_AVAILABLE = 1;
	public static final int STATE_UNAVAILABLE = 2;
	public static final int STATE_MYCOUPON = 3;
	
	private String id;
	private int state;
	
	public CouponItem( Context context ) {
		super( context );
		View.inflate(context, R.layout.couponitem, this);
	}
	
	public CouponItem( Context context, CouponItem clone){
		this( context );
		this.setData( clone.id, clone.state );
	}
	
	public int getState(){
		return state;
	}
	
	public void setData(String id, int state){
		this.id = id;
		this.state = state;
		
		if( state == STATE_AVAILABLE ){
			if( id.equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5010_hd);
			}else if( id.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
				this.setBackgroundResource(R.drawable.a5020_hd);
			}else if( id.equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
				this.setBackgroundResource(R.drawable.a5030_hd);
			}else if( id.equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
				this.setBackgroundResource(R.drawable.a5040_hd);
			}else if( id.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
				this.setBackgroundResource(R.drawable.a5050_hd);
			}else if( id.equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
				this.setBackgroundResource(R.drawable.a5060_hd);
			}else if( id.equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
				this.setBackgroundResource(R.drawable.a5070_hd);
			}else if( id.equals(ItemManager.ITEM_ID_COW_COUPON ) ){
				this.setBackgroundResource(R.drawable.a5080_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
				this.setBackgroundResource(R.drawable.a5090_hd);
			}else if( id.equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
				this.setBackgroundResource(R.drawable.a50100_hd);
			}else if( id.equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
				this.setBackgroundResource(R.drawable.a50110_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
				this.setBackgroundResource(R.drawable.a50120_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
				this.setBackgroundResource(R.drawable.a50130_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
				this.setBackgroundResource(R.drawable.a50140_hd);
			}else if( id.equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
				this.setBackgroundResource(R.drawable.a50150_hd);
			}
		}else if( state == STATE_UNAVAILABLE ){
			if( id.equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5010_hd);
			}else if( id.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5020_hd);
			}else if( id.equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5030_hd);
			}else if( id.equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5040_hd);
			}else if( id.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5050_hd);
			}else if( id.equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5060_hd);
			}else if( id.equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5070_hd);
			}else if( id.equals(ItemManager.ITEM_ID_COW_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5080_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5090_hd);
			}else if( id.equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50100_hd);
			}else if( id.equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50110_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50120_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50130_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50140_hd);
			}else if( id.equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50150_hd);
			}
		}else if( state == STATE_MYCOUPON ){
			if( id.equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5010_hd);
			}else if( id.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5020_hd);
			}else if( id.equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5030_hd);
			}else if( id.equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5040_hd);
			}else if( id.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5050_hd);
			}else if( id.equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5060_hd);
			}else if( id.equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5070_hd);
			}else if( id.equals(ItemManager.ITEM_ID_COW_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5080_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
				this.setBackgroundResource(R.drawable.m5090_hd);
			}else if( id.equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50100_hd);
			}else if( id.equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50110_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50120_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50130_hd);
			}else if( id.equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50140_hd);
			}else if( id.equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
				this.setBackgroundResource(R.drawable.m50150_hd);
			}
		} 
	}

}
