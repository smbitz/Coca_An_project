package codegears.coca.dialog;

import java.util.HashMap;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Player;
import codegears.coca.util.NetworkUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SpecialCodeDialog extends Activity implements OnClickListener {
	
	private static final String USE_SPECIAL_CODE_URL = "USE_SPECIAL_CODE_URL";
	
	private Player currentPlayer;
	private MyApp app;
	private EditText codeField;
	private Button okButton;
	private Button cancelButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.specialcodedialog);
    codeField = (EditText)this.findViewById( R.id.SpecialCodeField );
    okButton = (Button)this.findViewById( R.id.specialCodeOkButton );
    okButton.setOnClickListener( this );
    cancelButton = (Button)this.findViewById( R.id.specialCodeCancelButton );
    cancelButton.setOnClickListener( this );
    
    app = (MyApp)this.getApplication();
    currentPlayer = app.getCurrentPlayer();
	}

	@Override
	public void onClick( View v ) {
		if(v.equals( okButton )){
			String code = codeField.getText().toString();
			
			//send code to server
			HashMap<String, String> dataMap = new HashMap();
			dataMap.put("special_code", code);
			dataMap.put("facebook_id", app.getFacebookId());
			String postData = NetworkUtil.createPostData(dataMap);
			String returnMessage = NetworkUtil.getRawData(app.getConfig().get(USE_SPECIAL_CODE_URL).toString(), postData);
				
			//if return success
			if(!returnMessage.equals("fail")){
				//pack data (item and quantity) to intent
				//finish activity RESULT_OK
			}else if(returnMessage.equals("fail")){
			//else if return fail
				//display error message
			}
		} else if(v.equals( cancelButton )){
			this.setResult( Activity.RESULT_CANCELED );
			this.finish();
		}
	}
}