package codegears.coca.dialog;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SpecialCodeDialog extends Activity implements OnClickListener {
	
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
    okButton = (Button)this.findViewById( R.id.OkButton );
    okButton.setOnClickListener( this );
    cancelButton = (Button)this.findViewById( R.id.CancelButton );
    cancelButton.setOnClickListener( this );
    
    app = (MyApp)this.getApplication();
    currentPlayer = app.getCurrentPlayer();
	}

	@Override
	public void onClick( View v ) {
		if(v.equals( okButton )){
			String code = codeField.getText().toString();
			//send code to server
			//if return success
				//pack data (item and quantity) to intent
				//finish activity RESULT_OK
			//else if return fail
				//display error message
		} else if(v.equals( cancelButton )){
			this.setResult( Activity.RESULT_CANCELED );
			this.finish();
		}
	}
}