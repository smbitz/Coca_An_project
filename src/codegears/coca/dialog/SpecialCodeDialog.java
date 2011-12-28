package codegears.coca.dialog;

import java.util.HashMap;

import org.w3c.dom.Document;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.ItemQuantityPair;
import codegears.coca.data.Player;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;
import codegears.coca.util.NetworkUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SpecialCodeDialog extends Activity implements OnClickListener, NetworkThreadListener {

	private static final String USE_SPECIAL_CODE_URL = "USE_SPECIAL_CODE_URL";

	private Player currentPlayer;
	private MyApp app;
	private EditText codeField;
	private ImageButton okButton;
	private ImageButton cancelButton;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.specialcodedialog );
		codeField = (EditText) this.findViewById( R.id.SpecialCodeField );
		okButton = (ImageButton) this.findViewById( R.id.specialCodeOkButton );
		okButton.setOnClickListener( this );
		cancelButton = (ImageButton) this.findViewById( R.id.specialCodeCancelButton );
		cancelButton.setOnClickListener( this );

		app = (MyApp) this.getApplication();
		currentPlayer = app.getCurrentPlayer();
	}

	@Override
	public void onClick( View v ) {
		if ( v.equals( okButton ) ) {
			String code = codeField.getText().toString();
			HashMap< String, String > dataMap = new HashMap();
			dataMap.put( "special_code", code );
			dataMap.put( "facebook_id", app.getFacebookId() );
			String postData = NetworkUtil.createPostData( dataMap );
			NetworkThreadUtil.getRawData( app.getConfig().get( USE_SPECIAL_CODE_URL ).toString(),
							postData, this );
		} else if ( v.equals( cancelButton ) ) {
			this.setResult( Activity.RESULT_CANCELED );
			this.finish();
		}
	}

	@Override
	public void onNetworkDocSuccess( String urlString, Document document ) {
	}

	@Override
	public void onNetworkRawSuccess( String urlString, String result ) {
		// if return success
		if ( !result.equals( "fail" ) ) {
			String[] returnData = result.split( "," );
			String returnItemId = returnData[0];
			int returnItemQuantity = Integer.parseInt( returnData[1] );
			Intent intent = new Intent();
			intent.putExtra( "ITEM_ID", returnItemId );
			intent.putExtra( "ITEM_QUANTITY", returnItemQuantity );
			this.setResult( Activity.RESULT_OK, intent );
			this.finish();
		} else if ( result.equals( "fail" ) ) {
			// else if return fail
			// display error message
		}
	}

	@Override
	public void onNetworkFail( String urlString ) {
	}
}