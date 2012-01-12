package codegears.coca.ui;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.ItemExchangeItem;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CouponItem extends LinearLayout {

	public static final int STATE_AVAILABLE = 1;
	public static final int STATE_UNAVAILABLE = 2;
	public static final int STATE_MYCOUPON = 3;
	
	private static final String FONT_POSITION = "font/DB_HelvethaicaMon_X_Med_v3.2.ttf";
	private static final String TEXT_IN_COUPON = "ต้องการไอเทม\n";
	
	private LinearLayout couponBackground;
	private ImageView couponStatusImage;
	private ImageView couponItemImage;
	private TextView couponItemName;
	private TextView couponItemCurrentQuantity;
	private TextView couponItemRequireQuantity;
	
	private MyApp app;
	private ItemManager iManager;
	private Player currentPlayer;
	
	private String id;
	private int state;
	
	public CouponItem( Context context ) {
		super( context );
		View.inflate(context, R.layout.couponitem, this);
		
		app = (MyApp) context.getApplicationContext();
		iManager = app.getItemManager();
		currentPlayer = app.getCurrentPlayer();
		
		couponBackground = (LinearLayout) findViewById(R.id.couponItemBackground);
		couponStatusImage = (ImageView) findViewById(R.id.couponItemStatusImage);
		couponItemImage = (ImageView) findViewById(R.id.couponItemImage);
		couponItemName = (TextView) findViewById(R.id.couponItemName);
		couponItemCurrentQuantity = (TextView) findViewById(R.id.couponItemCurrentQuantity);
		couponItemRequireQuantity = (TextView) findViewById(R.id.couponItemRequireQuantity);
		
		Typeface setFont = Typeface.createFromAsset(context.getAssets(), FONT_POSITION);
		couponItemName.setTextColor(Color.BLACK);
		couponItemName.setTypeface(setFont);
		couponItemCurrentQuantity.setTextColor(Color.GREEN);
		couponItemCurrentQuantity.setTypeface(setFont);
		couponItemRequireQuantity.setTextColor(Color.BLACK);
		couponItemRequireQuantity.setTypeface(setFont);
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
		
		ItemExchangeItem exchangeItem = iManager.getMatchItem(id).getExchangeItem().get(0);
		int searchResult = currentPlayer.searchBackpackItem(exchangeItem.getId());
		if( searchResult>=0 ){
			int textCurrentQuantity = currentPlayer.getBackpack().get( searchResult ).getQuantity();
			couponItemCurrentQuantity.setText( "x "+textCurrentQuantity );
		}
		
		if( state == STATE_AVAILABLE ){
			couponBackground.setBackgroundResource(R.drawable.coupons_avialble_hd);
			couponStatusImage.setImageResource( R.drawable.text_available_hd );
			couponStatusImage.setAlpha(80);
			
			if( id.equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_MORNING_GLORY ).getName();
				couponItemImage.setImageResource( R.drawable.itemid160 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_CHINESE_CABBAGE ).getName();
				couponItemImage.setImageResource( R.drawable.itemid170 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_PUMPKIN ).getName();
				couponItemImage.setImageResource( R.drawable.itemid180 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_BABY_CORN ).getName();
				couponItemImage.setImageResource( R.drawable.itemid190 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_STRAW_MUSHROOMS ).getName();
				couponItemImage.setImageResource( R.drawable.itemid200 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_CHICKEN ).getName();
				couponItemImage.setImageResource( R.drawable.itemid210 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_PIG ).getName();
				couponItemImage.setImageResource( R.drawable.itemid220 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_COW_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_COW ).getName();
				couponItemImage.setImageResource( R.drawable.itemid230 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SHEEP ).getName();
				couponItemImage.setImageResource( R.drawable.itemid240 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_OSTRICH ).getName();
				couponItemImage.setImageResource( R.drawable.itemid250 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_FISH ).getName();
				couponItemImage.setImageResource( R.drawable.itemid260 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SQUID ).getName();
				couponItemImage.setImageResource( R.drawable.itemid270 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SCALLOPS ).getName();
				couponItemImage.setImageResource( R.drawable.itemid280 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SHRIMP ).getName();
				couponItemImage.setImageResource( R.drawable.itemid290 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_OYSTER ).getName();
				couponItemImage.setImageResource( R.drawable.itemid300 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}
		}else if( state == STATE_UNAVAILABLE ){
			couponItemCurrentQuantity.setTextColor(Color.BLACK);
			
			if( id.equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_MORNING_GLORY ).getName();
				couponItemImage.setImageResource( R.drawable.itemid160 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_CHINESE_CABBAGE ).getName();
				couponItemImage.setImageResource( R.drawable.itemid170 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_PUMPKIN ).getName();
				couponItemImage.setImageResource( R.drawable.itemid180 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_BABY_CORN ).getName();
				couponItemImage.setImageResource( R.drawable.itemid190 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_STRAW_MUSHROOMS ).getName();
				couponItemImage.setImageResource( R.drawable.itemid200 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_CHICKEN ).getName();
				couponItemImage.setImageResource( R.drawable.itemid210 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_PIG ).getName();
				couponItemImage.setImageResource( R.drawable.itemid220 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_COW_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_COW ).getName();
				couponItemImage.setImageResource( R.drawable.itemid230 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SHEEP ).getName();
				couponItemImage.setImageResource( R.drawable.itemid240 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_OSTRICH ).getName();
				couponItemImage.setImageResource( R.drawable.itemid250 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_FISH ).getName();
				couponItemImage.setImageResource( R.drawable.itemid260 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SQUID ).getName();
				couponItemImage.setImageResource( R.drawable.itemid270 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SCALLOPS ).getName();
				couponItemImage.setImageResource( R.drawable.itemid280 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_SHRIMP ).getName();
				couponItemImage.setImageResource( R.drawable.itemid290 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}else if( id.equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
				String itemName = iManager.getMatchItem( ItemManager.ITEM_ID_OYSTER ).getName();
				couponItemImage.setImageResource( R.drawable.itemid300 );
				couponItemName.setText( itemName );
				couponItemRequireQuantity.setText( TEXT_IN_COUPON+itemName+" x "+exchangeItem.getQuantity() );
			}
		}else if( state == STATE_MYCOUPON ){
			couponBackground.setBackgroundResource(R.drawable.coupons_my_hd);
			
			if( id.equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
				
			}else if( id.equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_PIG_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_COW_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_FISH_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){

			}else if( id.equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){

			}
		} 
	}

}
