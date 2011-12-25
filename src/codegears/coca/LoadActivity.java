package codegears.coca;

<<<<<<< HEAD
/*import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;*/
=======
//import com.facebook.android.DialogError;
//import com.facebook.android.Facebook;
//import com.facebook.android.Facebook.DialogListener;
//import com.facebook.android.FacebookError;
>>>>>>> 03f71383703b17d7d01e6ad10f18515e770c1682

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

<<<<<<< HEAD
public class LoadActivity extends Activity implements LoadListener{//, DialogListener {

	//public Facebook facebook;
=======
public class LoadActivity extends Activity implements LoadListener /*, DialogListener*/ {

//	public Facebook facebook;
>>>>>>> 03f71383703b17d7d01e6ad10f18515e770c1682
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate( savedInstanceState );
		setContentView(R.layout.load);
		MyApp app = (MyApp)this.getApplication();
		app.load();
		app.setLoadListener(this);
		
<<<<<<< HEAD
		//facebook = new Facebook("164010340367745");
		//facebook.authorize( this, this );
		onLoadComplete(null);
=======
//		facebook = new Facebook("164010340367745");
//		facebook.authorize( this, this );
>>>>>>> 03f71383703b17d7d01e6ad10f18515e770c1682
	}
	
	public void onLoadComplete(Object obj){
		//start GameActivity
<<<<<<< HEAD
		Intent i = new Intent(this, GameActivity.class);
		startActivity(i);
	}

	/*@Override
=======
		Intent intent = new Intent(this, GameActivity.class);
		this.startActivity( intent );		
	}
/*
	@Override
>>>>>>> 03f71383703b17d7d01e6ad10f18515e770c1682
	public void onComplete( Bundle values ) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, GameActivity.class);
		this.startActivity( intent );		
	}

	@Override
	public void onFacebookError( FacebookError e ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError( DialogError e ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
<<<<<<< HEAD
	}*/
=======
	}
*/
>>>>>>> 03f71383703b17d7d01e6ad10f18515e770c1682
}