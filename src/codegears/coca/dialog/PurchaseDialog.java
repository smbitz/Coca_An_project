package codegears.coca.dialog;

import codegears.coca.MyApp;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;

public class PurchaseDialog extends Activity {
	
	private Player currentPlayer;
	private MyApp app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (MyApp)this.getApplication();		
		currentPlayer = app.getCurrentPlayer();
	}
}
