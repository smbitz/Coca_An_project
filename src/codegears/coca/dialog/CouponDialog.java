package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.TextureVar;
import codegears.coca.data.Item;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CouponDialog extends Activity implements OnClickListener {
	
	private ArrayList<Item> couponItem;
	private Player currentPlayer;
	private MyApp app;
	private ImageButton closeButton;
	private Gallery gallery;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.coupondialog);
    
    app = (MyApp)this.getApplication();
    couponItem = app.getItemManager().getItemByType(ItemManager.ITEM_TYPE_COUPON);
    currentPlayer = app.getCurrentPlayer();
    
    closeButton = (ImageButton) this.findViewById(R.id.couponCloseButton);
    closeButton.setOnClickListener(this);
    gallery = (Gallery)this.findViewById( R.id.Gallery );
    gallery.setAdapter( new GalleryAdapter( ));
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
	
	private class GalleryAdapter extends BaseAdapter {

		private String[] data;
		
		public GalleryAdapter(){
			data = new String[20];
			for(int i = 0; i < 20 ; i++){
				data[i] = "Hello Gallery : "+i;
			}
		}
		
		@Override
		public int getCount() {
			return data.length / 2;
			//return couponItem.size() / 2;
		}

		@Override
		public Object getItem( int position ) {
			return data[position];
			//return couponItem.get(position);
		}

		@Override
		public long getItemId( int position ) {
			return position;
		}

		@Override
		public View getView( int position, View convertView, ViewGroup parent ) {
			LinearLayout layout = new LinearLayout(CouponDialog.this);
			layout.setLayoutParams( new Gallery.LayoutParams(parent.getWidth() / 2, LayoutParams.FILL_PARENT) );
			layout.setOrientation( LinearLayout.VERTICAL );
			
			//Set data Item1
		  Item fetchCouponItem1 = couponItem.get( position / 2 );
			ImageView newImageView1 = new ImageView(CouponDialog.this);
			
			if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5010_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5020_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5030_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5040_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5050_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5060_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5070_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_COW_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5080_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m5090_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m50100_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m50110_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m50120_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m50130_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m50140_hd);
			}else if( fetchCouponItem1.getId().equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
				newImageView1.setImageResource(R.drawable.m50150_hd);
			}
			
			layout.addView( newImageView1 );
			
		  //Set data Item2
			Item fetchCouponItem2 = couponItem.get( position / 2 + 1 );
			ImageView newImageView2 = new ImageView(CouponDialog.this);
			
			if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_MORNING_GLORY_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5010_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_CHINESE_CABBAGE_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5020_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_PUMPKIN_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5030_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_BABY_CORN_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5040_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_STRAW_MUSHROOMS_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5050_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_CHICKEN_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5060_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_PIG_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5070_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_COW_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5080_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_SHEEP_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m5090_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_OSTRICH_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m50100_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_FISH_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m50110_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_SQUID_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m50120_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_SCALLOPS_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m50130_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_SHRIMP_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m50140_hd);
			}else if( fetchCouponItem2.getId().equals(ItemManager.ITEM_ID_OYSTER_COUPON ) ){
				newImageView2.setImageResource(R.drawable.m50150_hd);
			}
			
			layout.addView( newImageView2 );
			
			/*TextView text1 = new TextView(CouponDialog.this);
			text1.setText( data[position / 2] );
			TextView text2 = new TextView(CouponDialog.this);
			text2.setText( data[position / 2 + 1] );
			layout.addView( text1 );
			layout.addView( text2 );*/
			
			return layout;
		}
	}
}
