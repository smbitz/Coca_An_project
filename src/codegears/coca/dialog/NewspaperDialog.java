package codegears.coca.dialog;

import codegears.coca.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class NewspaperDialog extends Activity implements OnClickListener{
	private ImageButton closeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newspaperdialog);
		
		closeButton = (ImageButton) findViewById(R.id.newspaperCloseButton);
		closeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.finish();
		}
	}
}
