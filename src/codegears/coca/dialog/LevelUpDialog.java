package codegears.coca.dialog;

import codegears.coca.MyApp;
import codegears.coca.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class LevelUpDialog extends Activity implements OnClickListener {
	
	private ImageButton closeButton;
	private int level;
	private MyApp app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.levelupdialog );
		
		app = (MyApp) this.getApplication();
		level = app.getCurrentPlayer().getLevel();
		
		closeButton = (ImageButton) findViewById( R.id.levelUpCloseButton );
		closeButton.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		if( v.equals( closeButton ) ){
			this.finish();
		}
	}
}
