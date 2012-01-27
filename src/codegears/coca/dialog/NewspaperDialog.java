package codegears.coca.dialog;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import codegears.coca.MyApp;
import codegears.coca.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class NewspaperDialog extends Activity implements OnClickListener{
	
	private static final String GET_NEWSPAPER_URL = "NEWSPAPER_MOBILE";
	
	private ImageButton closeButton;
	private ImageView newspaperImage;
	private MyApp app;
	private Bitmap mIcon_val;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newspaperdialog);
		
		app = (MyApp) this.getApplication();
		
		closeButton = (ImageButton) findViewById( R.id.newspaperCloseButton );
		closeButton.setOnClickListener(this);
		newspaperImage = (ImageView) findViewById( R.id.newspaperImage );
		
		URL newurl = null;
		try {
			newurl = new URL( app.getConfig().get( GET_NEWSPAPER_URL ).toString() );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		newspaperImage.setImageBitmap(mIcon_val);
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
}
