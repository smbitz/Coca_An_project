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
import android.widget.Button;

public class ShopDialog extends Activity implements OnClickListener {
	
	private ArrayList<Item> shopItem;
	private Player currentPlayer;
	private MyApp app;
	private Button closeButton;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.shopdialog);
    
    app = (MyApp)this.getApplication();
    shopItem = app.getItemManager().getItemByType(ItemManager.ITEM_TYPE_NORMAL);
    currentPlayer = app.getCurrentPlayer();
    
    closeButton = (Button) this.findViewById(R.id.shopCloseButton);
    closeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
}
