package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.DefaultVar;
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
import android.widget.LinearLayout;
import android.widget.TextView;

public class CouponDialog extends Activity implements OnClickListener {
	
	private ArrayList<Item> couponItem;
	private Player currentPlayer;
	private MyApp app;
	private ImageButton closeButton;
	private HorizontalScrollView couponItemScrollView;
	private Gallery gallery;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.coupondialog);
    
    app = (MyApp)this.getApplication();
    couponItem = app.getItemManager().getItemByType(ItemManager.ITEM_TYPE_COUPON);
    currentPlayer = app.getCurrentPlayer();
    
    closeButton = (ImageButton) this.findViewById(R.id.couponCloseButton);
    closeButton.setOnClickListener(this);
    couponItemScrollView = (HorizontalScrollView) this.findViewById(R.id.couponItemScrollView);
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
				data[i] = "Hello Gallery";
			}
		}
		@Override
		public int getCount() {
			return data.length / 2;
		}

		@Override
		public Object getItem( int position ) {
			return data[position];
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
			TextView text1 = new TextView(CouponDialog.this);
			text1.setText( data[position / 2] );
			TextView text2 = new TextView(CouponDialog.this);
			text2.setText( data[position / 2 + 1] );
			layout.addView( text1 );
			layout.addView( text2 );
			return layout;
		}
	}
}
