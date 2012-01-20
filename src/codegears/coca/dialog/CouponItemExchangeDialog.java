package codegears.coca.dialog;

import java.util.HashMap;

import org.w3c.dom.Document;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.ItemExchangeItem;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CouponItemExchangeDialog extends Activity implements OnClickListener, NetworkThreadListener {

	public static final String COUPON_ITEM_ID = "COUPON_ITEM_ID";
	private static final String TEXT_IN_COUPON = "ต้องการไอเทม\n";
	private static final String FONT_POSITION = "font/DB_HelvethaicaMon_X_Med_v3.2.ttf";
	private static final String GENERATE_COUPON_URL = "GENERATE_COUPON_URL";
	public static final String EXTRA_COUPON_CODE = "PUT_COUPON_CODE";
	public static final String EXTRA_COUPON_ID = "PUT_COUPON_ID";
	public static final int COUPON_EXCHANGE_SUCCESS = 0;
	
	private ImageView couponItemImage;
	private TextView couponItemCurrentQuantity;
	private TextView couponItemName;
	private TextView couponItemRequireQuantity;
	
	private ImageButton couponCancleButton;
	private ImageButton couponOkButton;
	
	private MyApp app;
	private ItemManager iManager;
	private Player currentPlayer;
	private String couponItemId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.couponitemexchange);
		
		app = (MyApp) this.getApplicationContext();
		iManager = app.getItemManager();
		currentPlayer = app.getCurrentPlayer();
		
		couponItemId = this.getIntent().getExtras().get( COUPON_ITEM_ID ).toString();
		
		couponItemImage = (ImageView) findViewById( R.id.couponItemExchangeImage );
		couponItemCurrentQuantity = (TextView) findViewById( R.id.couponItemExchangeCurrentQuantity );
		couponItemName = (TextView) findViewById( R.id.couponItemExchangeName );
		couponItemRequireQuantity = (TextView) findViewById( R.id.couponItemExchangeRequireQuantity );
		couponCancleButton = (ImageButton) findViewById( R.id.couponItemExchangeCanclebutton );
		couponCancleButton.setOnClickListener( this );
		couponOkButton = (ImageButton) findViewById( R.id.couponItemExchangeOkButton );
		couponOkButton.setOnClickListener( this );
		
		Typeface setFont = Typeface.createFromAsset(getAssets(), FONT_POSITION);
		couponItemCurrentQuantity.setTextColor(Color.GREEN);
		couponItemCurrentQuantity.setTypeface( setFont );
		couponItemName.setTextColor(Color.BLACK);
		couponItemName.setTypeface( setFont );
		couponItemRequireQuantity.setTextColor(Color.BLACK);
		couponItemRequireQuantity.setTypeface( setFont );
		
		ItemExchangeItem exchangeItem = iManager.getMatchItem(couponItemId).getExchangeItem().get(0);
		int searchResult = currentPlayer.searchBackpackItem(exchangeItem.getId());
		if( searchResult>=0 ){
			int textCurrentQuantity = currentPlayer.getBackpack().get( searchResult ).getQuantity();
			couponItemCurrentQuantity.setText( "x "+textCurrentQuantity );
		}
		
		if( couponItemId.equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_MORNING_GLORY ).getName();
			couponItemImage.setImageResource( R.drawable.itemid160 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_CHINESE_CABBAGE ).getName();
			couponItemImage.setImageResource( R.drawable.itemid170 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_PUMPKIN ).getName();
			couponItemImage.setImageResource( R.drawable.itemid180 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_BABY_CORN ).getName();
			couponItemImage.setImageResource( R.drawable.itemid190 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_STRAW_MUSHROOMS ).getName();
			couponItemImage.setImageResource( R.drawable.itemid200 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_CHICKEN ).getName();
			couponItemImage.setImageResource( R.drawable.itemid210 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_PIG ).getName();
			couponItemImage.setImageResource( R.drawable.itemid220 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_COW_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_COW ).getName();
			couponItemImage.setImageResource( R.drawable.itemid230 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SHEEP ).getName();
			couponItemImage.setImageResource( R.drawable.itemid240 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_OSTRICH ).getName();
			couponItemImage.setImageResource( R.drawable.itemid250 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_FISH ).getName();
			couponItemImage.setImageResource( R.drawable.itemid260 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SQUID ).getName();
			couponItemImage.setImageResource( R.drawable.itemid270 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SCALLOPS ).getName();
			couponItemImage.setImageResource( R.drawable.itemid280 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SHRIMP ).getName();
			couponItemImage.setImageResource( R.drawable.itemid290 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}else if( couponItemId.equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
			String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_OYSTER ).getName();
			couponItemImage.setImageResource( R.drawable.itemid300 );
			couponItemName.setText( itemName );
			couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
		}
	}
	
	@Override
	public void onClick(View v) {
		if( v.equals( couponOkButton ) ){
			HashMap< String, String > dataMap = new HashMap<String, String>();
			dataMap.put( "facebook_id", app.getFacebookId() );
			dataMap.put( "item_id", couponItemId );
			String postData = NetworkUtil.createPostData( dataMap );
			NetworkThreadUtil.getRawData( app.getConfig().get( GENERATE_COUPON_URL ).toString(),
					postData, this );
			this.finish();
		}else if( v.equals( couponCancleButton ) ){
			this.setResult( Activity.RESULT_CANCELED );
			this.finish();
		}
	}

	@Override
	public void onNetworkDocSuccess(String urlString, Document document) {

	}

	@Override
	public void onNetworkRawSuccess(String urlString, String result) {
		// if return success
		if ( !result.equals( "fail" ) ) {
			String[] returnData = result.split( "," );
			String couponCode = returnData[0];
			String couponId = returnData[1];
			//set player for received coupon
			//currentPlayer......(couponId);
			currentPlayer.onExchangeReply(couponId);
			this.setResult( COUPON_EXCHANGE_SUCCESS );
			this.finish();
			
			Intent newIntent = new Intent( this, CouponItemGetDialog.class );
			newIntent.putExtra( EXTRA_COUPON_CODE, couponCode);
			newIntent.putExtra( EXTRA_COUPON_ID, couponId);
			startActivity( newIntent );
		} else if ( result.equals( "fail" ) ) {
			// else if return fail
			// display error message
		}
	}

	@Override
	public void onNetworkFail(String urlString) {
		// TODO Auto-generated method stub
		
	}

}
