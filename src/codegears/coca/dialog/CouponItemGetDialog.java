package codegears.coca.dialog;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.ItemManager;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CouponItemGetDialog extends Activity implements OnClickListener {
	
	private static final String FONT_POSITION = "font/DB_HelvethaicaMon_X_Med_v3.2.ttf";
	private static final String EXCHANGE_TEXT_COUPON_1 = "สามารถนำไปแลก\n";
	private static final String EXCHANGE_TEXT_COUPON_2 = "ได้ที่โคคาสุกี้";
	
	private ImageButton closeButton;
	private ImageView couponImage;
	private TextView couponCode;
	private TextView couponName;
	private TextView couponDetail;
	private String couponItemId; 
	
	private MyApp app;
	private ItemManager iManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.couponitemgetdialog );
		
		app = (MyApp) this.getApplicationContext();
		iManager = app.getItemManager();
		
		couponItemId = this.getIntent().getStringExtra( CouponItemExchangeDialog.EXTRA_COUPON_ID );
		
		closeButton = (ImageButton) findViewById( R.id.couponItemGetCloseButton );
		closeButton.setOnClickListener( this );
		couponImage = (ImageView) findViewById(R.id.couponItemGetImage);
		couponCode = (TextView) findViewById( R.id.couponItemGetTextCode );
		couponName = (TextView) findViewById(R.id.couponItemGetName);
		couponDetail = (TextView) findViewById(R.id.couponItemGetRequireQuantity);
		
		couponCode.setText( this.getIntent().getStringExtra( CouponItemExchangeDialog.EXTRA_COUPON_CODE ) );
		
		Typeface setFont = Typeface.createFromAsset(getAssets(), FONT_POSITION);
		
		couponCode.setTypeface( setFont );
		couponName.setTypeface( setFont );
		couponDetail.setTypeface( setFont );
		
		couponCode.setTextColor( Color.BLACK );
		couponName.setTextColor( Color.BLACK );
		couponDetail.setTextColor( Color.BLACK );
		
		couponName.setTextSize(20);
		couponCode.setTextSize(30);
		
		if( couponItemId.equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_MORNING_GLORY ).getName();
			couponImage.setImageResource( R.drawable.itemid160 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_CHINESE_CABBAGE ).getName();
			couponImage.setImageResource( R.drawable.itemid170 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_PUMPKIN ).getName();
			couponImage.setImageResource( R.drawable.itemid180 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_BABY_CORN ).getName();
			couponImage.setImageResource( R.drawable.itemid190 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_STRAW_MUSHROOMS ).getName();
			couponImage.setImageResource( R.drawable.itemid200 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_CHICKEN ).getName();
			couponImage.setImageResource( R.drawable.itemid210 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_PIG ).getName();
			couponImage.setImageResource( R.drawable.itemid220 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_COW_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_COW ).getName();
			couponImage.setImageResource( R.drawable.itemid230 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SHEEP ).getName();
			couponImage.setImageResource( R.drawable.itemid240 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_OSTRICH ).getName();
			couponImage.setImageResource( R.drawable.itemid250 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_FISH ).getName();
			couponImage.setImageResource( R.drawable.itemid260 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SQUID ).getName();
			couponImage.setImageResource( R.drawable.itemid270 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SCALLOPS ).getName();
			couponImage.setImageResource( R.drawable.itemid280 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SHRIMP ).getName();
			couponImage.setImageResource( R.drawable.itemid290 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_OYSTER ).getName();
			couponImage.setImageResource( R.drawable.itemid300 );
			couponName.setText( itemName );
			couponDetail.setText( EXCHANGE_TEXT_COUPON_1+itemName+EXCHANGE_TEXT_COUPON_2 );
		}
	}

	@Override
	public void onClick(View v) {
		if( v.equals( closeButton ) ){
			this.finish();
		}
	}
}
