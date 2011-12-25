package codegears.coca;

//import com.facebook.android.DialogError;
//import com.facebook.android.Facebook;
//import com.facebook.android.Facebook.DialogListener;
//import com.facebook.android.FacebookError;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadActivity extends Activity implements LoadListener /*, DialogListener*/ {

//	public Facebook facebook;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate( savedInstanceState );
		setContentView(R.layout.load);
		MyApp app = (MyApp)this.getApplication();
		app.load();
		app.setLoadListener(this);
		
//		facebook = new Facebook("164010340367745");
//		facebook.authorize( this, this );

	}
	
	public void onLoadComplete(Object obj){
		//start GameActivity
		Intent i = new Intent(this, GameActivity.class);
		startActivity(i);
	}
}