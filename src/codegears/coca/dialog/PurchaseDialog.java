package codegears.coca.dialog;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PurchaseDialog extends Activity implements OnClickListener {

	private Player currentPlayer;
	private MyApp app;
	private TextView moneyText;
	private TextView levelText;
	private Button okButton;
	private Button closeButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.purchasedialog);
    
    app = (MyApp)this.getApplication();
    currentPlayer = app.getCurrentPlayer();
    
    moneyText = (TextView)this.findViewById( R.id.MoneyText );
    levelText = (TextView)this.findViewById( R.id.LevelText );
    okButton = (Button)this.findViewById( R.id.OkButton );
    okButton.setOnClickListener( this );
    closeButton = (Button)this.findViewById( R.id.CloseButton );
    closeButton.setOnClickListener( this );
    
    if(true/*allow to purchase*/){
    	okButton.setVisibility( View.VISIBLE );
    } else {
    	okButton.setVisibility( View.INVISIBLE );
    }
	}

	@Override
	public void onClick( View v ) {
		if(v.equals( okButton )){
			this.setResult( Activity.RESULT_OK );
			this.finish();
		} else if(v.equals( closeButton )){
			this.setResult( Activity.RESULT_CANCELED );
			this.finish();
		}
	}
}
