package codegears.coca.dialog;

import java.util.ArrayList;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Item;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

public class BuildDialog extends Activity implements OnClickListener {
	
	public static final String BUILDING_ID = "BUILDING_ID";
	private ArrayList<Item> buildItem;
	private Player currentPlayer;
	private MyApp app;
	
	private ImageButton closeButton;
	private HorizontalScrollView shopItemScrollView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.builddialog);
		app = (MyApp)this.getApplication();		
		buildItem = app.getItemManager().getBuildItem();
		currentPlayer = app.getCurrentPlayer();
		
		closeButton = (ImageButton) this.findViewById(R.id.plantCloseButton);
		closeButton.setOnClickListener(this);
		shopItemScrollView = (HorizontalScrollView) this.findViewById(R.id.shopItemScrollView);
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
}
