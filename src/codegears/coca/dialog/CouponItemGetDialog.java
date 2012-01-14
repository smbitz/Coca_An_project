package codegears.coca.dialog;

import codegears.coca.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class CouponItemGetDialog extends Activity implements OnClickListener {
	
	private ImageButton closeButton;
	private TextView couponCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.couponitemgetdialog );
		
		closeButton = (ImageButton) findViewById( R.id.couponItemGetCloseButton );
		closeButton.setOnClickListener( this );
		couponCode = (TextView) findViewById( R.id.couponItemGetTextCode );
		
		couponCode.setText( this.getIntent().getStringExtra( CouponItemExchangeDialog.PUT_COUPON_CODE ) );
		couponCode.setTextColor( Color.BLACK );
	}

	@Override
	public void onClick(View v) {
		if( v.equals( closeButton ) ){
			this.finish();
		}
	}
}
