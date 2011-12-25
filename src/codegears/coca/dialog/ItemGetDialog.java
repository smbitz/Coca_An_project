package codegears.coca.dialog;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.Player;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ItemGetDialog extends Activity implements OnClickListener {

	private Button closeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
    setContentView(R.layout.itemget);

    Intent intent = this.getIntent();
    //get item and quantity from intent
    closeButton = (Button)this.findViewById( R.id.CloseButton );
    closeButton.setOnClickListener( this );
	}

	@Override
	public void onClick( View v ) {
		if(v.equals( closeButton )){
			this.finish();
		}
	}
}
