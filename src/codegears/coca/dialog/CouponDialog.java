package codegears.coca.dialog;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Item;
import codegears.coca.data.ItemExchangeItem;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import codegears.coca.ui.CouponItem;
import codegears.coca.ui.ToggleImageButton;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;
import codegears.coca.util.NetworkUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CouponDialog extends Activity implements OnClickListener, NetworkThreadListener {
	
	public static final int REQUEST_COUPONITEMEXCHANGE = 3;
	private static final String VIEW_COUPON_URL = "VIEW_COUPON_URL";
	private static final String EXTRA_COUPON_CODE = "PUT_COUPON_CODE";
	private static final String EXTRA_COUPON_ID = "PUT_COUPON_ID";
	
	private ArrayList<CouponItem> allCouponList;
	private ArrayList<CouponItem> availableList;
	private ArrayList<CouponItem> unavailableList;
	private ArrayList<CouponItem> myCouponList;
	
	private Player currentPlayer;
	private MyApp app;
	private ImageButton closeButton;
	private Gallery gallery;
	private GalleryAdapter adapter;
	private ToggleImageButton allButton;
	private ToggleImageButton availableButton;
	private ToggleImageButton unavailableButton;
	private ToggleImageButton myCouponButton;
	
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.coupondialog);
    
    app = (MyApp)this.getApplication();
    currentPlayer = app.getCurrentPlayer();
    ArrayList<Item> couponItemList = app.getItemManager().getItemByType(ItemManager.ITEM_TYPE_COUPON);
    allCouponList = new ArrayList<CouponItem>();
    availableList = new ArrayList<CouponItem>();
    unavailableList = new ArrayList<CouponItem>();
    myCouponList = new ArrayList<CouponItem>();
    for(Item coupon:couponItemList){
    	CouponItem cItem = new CouponItem(this);
    	boolean enoughItemToExchange = true;
    	for(ItemExchangeItem exchangeItem:coupon.getExchangeItem()){
    		int searchBackpackId = currentPlayer.searchBackpackItem( exchangeItem.getId() );
    		if(searchBackpackId>=0){
    			if(currentPlayer.getBackpack().get( searchBackpackId ).getQuantity() < exchangeItem.getQuantity()){
      			enoughItemToExchange = false;
      		}
    		}else{
    			enoughItemToExchange = false;
    		}
    	}
    	if(currentPlayer.searchBackpackItem( coupon.getId() ) > 0){
    		cItem.setData( coupon.getId(), CouponItem.STATE_MYCOUPON );
    		myCouponList.add( cItem );
    	} else if(enoughItemToExchange){
    		cItem.setData( coupon.getId(), CouponItem.STATE_AVAILABLE );
    		availableList.add( cItem );
    	} else {
    		cItem.setData( coupon.getId(), CouponItem.STATE_UNAVAILABLE );
    		unavailableList.add( cItem );
    	}
    	allCouponList.add( cItem );
    }
    closeButton = (ImageButton) this.findViewById(R.id.couponCloseButton);
    closeButton.setOnClickListener(this);
    gallery = (Gallery)this.findViewById( R.id.Gallery );
    adapter = new GalleryAdapter( );
    adapter.setData(allCouponList);
    gallery.setAdapter( adapter );
    
    allButton = (ToggleImageButton)this.findViewById( R.id.couponAllCouponButton );
    allButton.setResource( R.drawable.text_allcoupons_animation02, R.drawable.text_allcoupons_animation01 );
    allButton.setOnClickListener( this );
    allButton.setChecked( true );
    availableButton = (ToggleImageButton)this.findViewById( R.id.couponAvilableButton );
    availableButton.setResource( R.drawable.text_available_animation02, R.drawable.text_available_animation01 );
    availableButton.setOnClickListener( this );
    unavailableButton = (ToggleImageButton)this.findViewById( R.id.couponUnavialbleButton );
    unavailableButton.setResource( R.drawable.text_unavialble_animation02, R.drawable.text_unavialble_animation01 );
    unavailableButton.setOnClickListener( this );
    myCouponButton = (ToggleImageButton)this.findViewById( R.id.couponMycouponsButton );
    myCouponButton.setResource( R.drawable.text_mycoupons_animation02, R.drawable.text_mycoupons_animation01 );
    myCouponButton.setOnClickListener( this );
	}
	
	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.setResult( Activity.RESULT_CANCELED );
			this.finish();
		} else if(v.equals( allButton )){
			allButton.setChecked( true );
			availableButton.setChecked( false );
			unavailableButton.setChecked( false );
			myCouponButton.setChecked( false );
	    adapter.setData(allCouponList);
	    adapter.notifyDataSetChanged();
		} else if(v.equals( unavailableButton )){
			allButton.setChecked( false );
			availableButton.setChecked( false );
			unavailableButton.setChecked( true );
			myCouponButton.setChecked( false );
	    adapter.setData(unavailableList);
	    adapter.notifyDataSetChanged();
		} else if(v.equals( availableButton )){
			allButton.setChecked( false );
			availableButton.setChecked( true );
			unavailableButton.setChecked( false );
			myCouponButton.setChecked( false );
	    adapter.setData(availableList);
	    adapter.notifyDataSetChanged();
		} else if(v.equals( myCouponButton )){
			allButton.setChecked( false );
			availableButton.setChecked( false );
			unavailableButton.setChecked( false );
			myCouponButton.setChecked( true );
	    adapter.setData(myCouponList);
	    adapter.notifyDataSetChanged();
		} else {
			if(v instanceof CouponItem){
				CouponItem item = (CouponItem)v;
				if(item.getState() == CouponItem.STATE_AVAILABLE){
					//exchange
					Intent newIntent = new Intent( this, CouponItemExchangeDialog.class );
					newIntent.putExtra( CouponItemExchangeDialog.COUPON_ITEM_ID, item.getCouponId() );
					this.startActivityForResult( newIntent, REQUEST_COUPONITEMEXCHANGE );
				} else if(item.getState() == CouponItem.STATE_MYCOUPON){
					//view code
					HashMap< String, String > dataMap = new HashMap<String, String>();
					dataMap.put( "facebook_id", app.getFacebookId() );
					dataMap.put( "item_id", item.getCouponId() );
					String postData = NetworkUtil.createPostData( dataMap );
					NetworkThreadUtil.getRawData( app.getConfig().get( VIEW_COUPON_URL ).toString(),
							postData, this );
				}
			}
		}
	}
	
	private class GalleryAdapter extends BaseAdapter {

		private ArrayList<CouponItem> data;
		
		public GalleryAdapter(){
		}
		
		public void setData(ArrayList<CouponItem> data){
			this.data = data;
		}
		
		@Override
		public int getCount() {
			return (data.size() + 3) / 4;
		}

		@Override
		public Object getItem( int position ) {
			return data.get( position);
		}

		@Override
		public long getItemId( int position ) {
			return position;
		}

		@Override
		public View getView( int position, View convertView, ViewGroup parent ) {
			LinearLayout layout = new LinearLayout(CouponDialog.this);
			layout.setLayoutParams( new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT) );
			layout.setOrientation( LinearLayout.HORIZONTAL );
			
			LinearLayout layoutInner1 = new LinearLayout(CouponDialog.this);
			layoutInner1.setOrientation( LinearLayout.VERTICAL );
			layoutInner1.setLayoutParams( new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT) );
			
			LinearLayout layoutInner2 = new LinearLayout(CouponDialog.this);
			layoutInner2.setOrientation( LinearLayout.VERTICAL );
			layoutInner2.setLayoutParams( new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT) );
			
			layout.addView( layoutInner1 );
			layout.addView( layoutInner2 );
			
			CouponItem item1 = new CouponItem(CouponDialog.this, data.get(position * 4));
			item1.setOnClickListener( CouponDialog.this );
			layoutInner1.addView( item1 );
			if(position * 4 + 1 < data.size()){
				CouponItem item2 = new CouponItem(CouponDialog.this, data.get(position * 4 + 1));
				item2.setOnClickListener( CouponDialog.this );
				layoutInner1.addView( item2 );
			}
			if(position * 4 + 2 < data.size()){
				CouponItem item2 = new CouponItem(CouponDialog.this, data.get(position * 4 + 2));
				item2.setOnClickListener( CouponDialog.this );
				layoutInner2.addView( item2 );
			}
			if(position * 4 + 3 < data.size()){
				CouponItem item2 = new CouponItem(CouponDialog.this, data.get(position * 4 + 3));
				item2.setOnClickListener( CouponDialog.this );
				layoutInner2.addView( item2 );
			}
			return layout;
		}

	}

	@Override
	public void onNetworkDocSuccess(String urlString, Document document) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkRawSuccess(String urlString, String result) {
		if ( !result.equals( "fail" ) ) {
			String[] returnData = result.split( "," );
			String couponCode = returnData[0];
			String couponId = returnData[1];
			
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