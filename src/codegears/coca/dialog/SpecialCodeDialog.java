package codegears.coca.dialog;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;

public class SpecialCodeDialog extends Activity {
	
	private Player currentPlayer;
	private MyApp app;
	
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.specialcodedialog);
    
    app = (MyApp)this.getApplication();
    currentPlayer = app.getCurrentPlayer();
	}
}
