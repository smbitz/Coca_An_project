package codegears.coca.dialog;

import codegears.coca.MyApp;

import codegears.coca.R;
import codegears.coca.data.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PurchaseDialog extends Activity implements OnClickListener {

	private Player currentPlayer;
	private MyApp app;
	private TextView moneyText;
	private TextView levelText;
	private ImageButton okButton;
	private ImageButton closeButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.purchasedialog);
    
    app = (MyApp)this.getApplication();
    currentPlayer = app.getCurrentPlayer();
    
    moneyText = (TextView) this.findViewById( R.id.MoneyText );
    levelText = (TextView) this.findViewById( R.id.LevelText );
    okButton = (ImageButton) this.findViewById( R.id.purchaseOkButton );
    okButton.setOnClickListener( this );
    closeButton = (ImageButton) this.findViewById( R.id.purchaseCloseDialog );
    closeButton.setOnClickListener( this );
    
    if(currentPlayer.isAllowToPurchase()){
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
