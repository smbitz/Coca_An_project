package codegears.coca;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadActivity extends Activity implements LoadListener , DialogListener {

	private static final String FACEBOOK_APP_ID = "164010340367745";
	
	public Facebook facebook;
	private MyApp app;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate( savedInstanceState );
		setContentView(R.layout.load);
		app = (MyApp)this.getApplication();
		
		facebook = new Facebook( FACEBOOK_APP_ID );
		facebook.authorize( this, this );
	}
	
	public void onLoadComplete(Object obj){
		//start GameActivity
		Intent i = new Intent(this, GameActivity.class);
		startActivity(i);
	}

	@Override
	public void onComplete(Bundle values) {
		//facebook.
		JSONObject me;
		String facebook_id = null;
		String facebook_name = null;
		
		try {
			me = new JSONObject( facebook.request("me") );
			facebook_id = me.getString("id");
			facebook_name = me.getString("first_name");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		app.setFacebookId( facebook_id );
		app.setFacebookName( facebook_name );
		app.load();
		app.setLoadListener( this );
	}

	@Override
	public void onFacebookError(FacebookError e) {
		
	}

	@Override
	public void onError(DialogError e) {
		
	}

	@Override
	public void onCancel() {
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		facebook.authorizeCallback(requestCode, resultCode, data);
	}
}