package codegears.coca;

import android.app.Activity;
import android.os.Bundle;

public class LoadActivity extends Activity implements LoadListener {

	@Override
	public void onCreate(Bundle savedInstanceState){
		MyApp app = (MyApp)this.getApplication();
		app.load();
		app.setLoadListener(this);
	}
	
	public void onLoadComplete(Object obj){
		//start GameActivity
	}
}