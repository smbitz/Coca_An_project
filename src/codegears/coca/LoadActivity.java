package codegears.coca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadActivity extends Activity implements LoadListener {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate( savedInstanceState );
		setContentView(R.layout.load);
		MyApp app = (MyApp)this.getApplication();
		app.load();
		app.setLoadListener(this);
		
		onLoadComplete(null);
	}
	
	public void onLoadComplete(Object obj){
		//start GameActivity
		Intent intent = new Intent(this, GameActivity.class);
		this.startActivity( intent );
	}
}