package codegears.coca.ui;

import codegears.coca.R;
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
	
	public void setData(String id, int state){
		this.id = id;
		this.state = state;
		this.setBackgroundResource( R.drawable.m5010_hd );
	}

}
