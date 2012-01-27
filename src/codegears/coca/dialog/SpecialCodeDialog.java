package codegears.coca.dialog;

import java.util.HashMap;

import org.w3c.dom.Document;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Player;
import codegears.coca.util.NetworkThreadUtil;
import codegears.coca.util.NetworkThreadUtil.NetworkThreadListener;
import codegears.coca.util.NetworkUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SpecialCodeDialog extends Activity implements OnClickListener, NetworkThreadListener {

	private static final String USE_SPECIAL_CODE_URL = "USE_SPECIAL_CODE_URL";
	private static final int TEXT_FAIL_SIZE = 18;
	private static final int TEXT_CODE_SIZE = 20;
	private static final String DEFAULT_TEXT_FAIL = "* รหัสคูปอง 8 หลัก";
	private static final String TEXT_FAIL = "รหัสคูปองไม่ถูกต้อง กรุณาลองอีกครั้ง";
	
	public  static final String PUT_EXTRA_ITEM_ID = "ITEM_ID";
	public static final String PUT_EXTRA_ITEM_QUANTITY = "ITEM_QUANTITY";

	private Player currentPlayer;
	private MyApp app;
	private EditText codeField;
	private ImageButton okButton;
	private ImageButton cancelButton;
	private TextView failText;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.specialcodedialog );

		app = (MyApp) this.getApplication();
		
		codeField = (EditText) this.findViewById( R.id.SpecialCodeField );
		okButton = (ImageButton) this.findViewById( R.id.specialCodeOkButton );
		okButton.setOnClickListener( this );
		cancelButton = (ImageButton) this.findViewById( R.id.specialCodeCancelButton );
		cancelButton.setOnClickListener( this );
		failText = (TextView) findViewById( R.id.SpecialCodeFail );
		
		codeField.setTextColor( this.getResources().getColor( R.color.dark_blue ) );
		codeField.setTextSize( TEXT_CODE_SIZE );
		
		failText.setTypeface( app.getTextFont() );
		failText.setTextColor( this.getResources().getColor( R.color.dark_blue ) );
		failText.setTextSize( TEXT_FAIL_SIZE );
		failText.setText( DEFAULT_TEXT_FAIL );
		
		app = (MyApp) this.getApplication();
		currentPlayer = app.getCurrentPlayer();
	}

	@Override
	public void onClick( View v ) {
		if ( v.equals( okButton ) ) {
			String code = codeField.getText().toString();
			HashMap< String, String > dataMap = new HashMap<String, String>();
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
			intent.putExtra( PUT_EXTRA_ITEM_ID, returnItemId );
			intent.putExtra( PUT_EXTRA_ITEM_QUANTITY, returnItemQuantity );
			this.setResult( Activity.RESULT_OK, intent );
			this.finish();
		} else if ( result.equals( "fail" ) ) {
			// else if return fail
			// display error message
			this.runOnUiThread(new Runnable(){
						public void run(){
							failText.setTextColor( SpecialCodeDialog.this.getResources().getColor( R.color.orange ) );
							failText.setText( TEXT_FAIL );
							
						}
					}
			);
		}
	}

	@Override
	public void onNetworkFail( String urlString ) {
	}
}