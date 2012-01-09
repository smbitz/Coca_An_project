package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Item;
import codegears.coca.data.ItemExchangeItem;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import codegears.coca.ui.CouponItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CouponDialog extends Activity implements OnClickListener {
	
	private ArrayList<CouponItem> allCouponList;
	private ArrayList<CouponItem> availableList;
	private ArrayList<CouponItem> unavailableList;
	private ArrayList<CouponItem> myCouponList;
	
	private Player currentPlayer;
	private MyApp app;
	private ImageButton closeButton;
	private Gallery gallery;
	private GalleryAdapter adapter;
	
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
    		if(currentPlayer.searchBackpackItem( exchangeItem.getId() ) < exchangeItem.getQuantity()){
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
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
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
			layoutInner1.addView( item1 );
			if(position * 4 + 1 < data.size()){
				CouponItem item2 = new CouponItem(CouponDialog.this, data.get(position * 4 + 1));
				layoutInner1.addView( item2 );
			}
			if(position * 4 + 2 < data.size()){
				CouponItem item2 = new CouponItem(CouponDialog.this, data.get(position * 4 + 2));
				layoutInner2.addView( item2 );
			}
			if(position * 4 + 3 < data.size()){
				CouponItem item2 = new CouponItem(CouponDialog.this, data.get(position * 4 + 3));
				layoutInner2.addView( item2 );
			}
			return layout;
		}
	}
}