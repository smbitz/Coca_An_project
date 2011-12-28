package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Item;
import codegears.coca.data.Player;
import codegears.coca.ui.BuildItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class BuildDialog extends Activity implements OnClickListener {
	
	public static final String BUILDING_ID = "BUILDING_ID";
	private ArrayList<Item> buildItem;
	private Player currentPlayer;
	private MyApp app;
	
	private ImageButton closeButton;
	private LinearLayout itemLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.builddialog);
		app = (MyApp)this.getApplication();		
		buildItem = app.getItemManager().getBuildItem();
		currentPlayer = app.getCurrentPlayer();
		
		closeButton = (ImageButton) this.findViewById(R.id.plantCloseButton);
		closeButton.setOnClickListener(this);
		LinearLayout itemLayout = (LinearLayout)this.findViewById( R.id.plantItem );
		BuildItem test = new BuildItem(this);
		test.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) );
		test.setItemName("Hello Item Name");
		BuildItem hello = new BuildItem(this);
		hello.setLayoutParams( new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) );
		itemLayout.addView( test );
		itemLayout.addView( hello );

	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
	
	private class GridAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 12;
		}

		@Override
		public Object getItem( int position ) {
			return position;
		}

		@Override
		public long getItemId( int position ) {
			return position;
		}

		@Override
		public View getView( int position, View convertView, ViewGroup parent ) {
			Button b = new Button(BuildDialog.this);
			b.setLayoutParams( new GridView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT) );
			b.setText( "test grid" );
			return b;
		}
	}
}
