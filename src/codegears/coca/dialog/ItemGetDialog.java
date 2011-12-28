package codegears.coca.dialog;

import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.data.ItemManager;
import codegears.coca.data.Player;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemGetDialog extends Activity implements OnClickListener {
	
	private static final String GET_EXTRA_ITEM_ID = "ITEM_ID";
	private static final String GET_EXTRA_ITEM_QUANTITY = "ITEM_QUANTITY";
	
	private static String TEXT_FERTILIZER_A = "คุณได้รับ ปุ๋ยA 1 ea";
	private static String TEXT_FERTILIZER_B = "คุณได้รับ ปุ๋ยB 1 ea";
	private static String TEXT_VACCINE_A = "คุณได้รับ วัคซีนA 1 ea";
	private static String TEXT_VACCINE_B= "คุณได้รับ วัคซีนB 1 ea";
	private static String TEXT_MICROORGANISM_A = "คุณได้รับ จุลินทรีย์A 1 ea";
	private static String TEXT_MICROORGANISM_B = "คุณได้รับ จุลินทรีย์B 1 ea";
	
	private ImageView imageFertilizerA;
	private ImageView imageFertilizerB;
	private ImageView imageVaccineA;
	private ImageView imageVaccineB;
	private ImageView imageMicroorganismA;
	private ImageView imageMicroorganismB;
	
	private TextView getItemText;

	private ImageButton closeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
    setContentView(R.layout.itemget);

    Intent intent = this.getIntent();
    String itemID = intent.getStringExtra( GET_EXTRA_ITEM_ID );
    int itemQuantity = intent.getIntExtra( GET_EXTRA_ITEM_QUANTITY, 0 );
    
    closeButton = (ImageButton) this.findViewById( R.id.itemGetCloseButton );
    closeButton.setOnClickListener( this );
    
    imageFertilizerA = (ImageView) findViewById(R.id.itemGetFertilizerA);
    imageFertilizerB = (ImageView) findViewById(R.id.itemGetFertilizerB);
    imageVaccineA = (ImageView) findViewById(R.id.itemGetVaccineA);
    imageVaccineB = (ImageView) findViewById(R.id.itemGetVaccineB);
    imageMicroorganismA = (ImageView) findViewById(R.id.itemGetMicroorganismA);
    imageMicroorganismB = (ImageView) findViewById(R.id.itemGetMicroorganismB);
    
    getItemText = (TextView) findViewById(R.id.itemGetTextView);
    Typeface setFont = Typeface.createFromAsset(getAssets(), "font/DB_HelvethaicaMon_X_Med_v3.2.ttf");
    getItemText.setTextColor(Color.BLACK);
    getItemText.setTypeface(setFont);
    getItemText.setTextSize(20);
    
    if( itemID.equals(ItemManager.ITEM_ID_FERTILIZER_A) ){
    	imageFertilizerA.setVisibility(imageFertilizerA.VISIBLE);
    	getItemText.setText(TEXT_FERTILIZER_A);
    }else if( itemID.equals(ItemManager.ITEM_ID_FERTILIZER_B) ){
    	imageFertilizerB.setVisibility(imageFertilizerB.VISIBLE);
    	getItemText.setText(TEXT_FERTILIZER_B);
    }else if( itemID.equals(ItemManager.ITEM_ID_VACCINE_A) ){
    	imageVaccineA.setVisibility(imageVaccineA.VISIBLE);
    	getItemText.setText(TEXT_VACCINE_A);
    }else if( itemID.equals(ItemManager.ITEM_ID_VACCINE_B) ){
    	imageVaccineB.setVisibility(imageVaccineB.VISIBLE);
    	getItemText.setText(TEXT_VACCINE_B);
    }else if( itemID.equals(ItemManager.ITEM_ID_MICROORGANISM_A) ){
    	imageMicroorganismA.setVisibility(imageMicroorganismA.VISIBLE);
    	getItemText.setText(TEXT_MICROORGANISM_A);
    }else if( itemID.equals(ItemManager.ITEM_ID_MICROORGANISM_B) ){
    	imageMicroorganismB.setVisibility(imageMicroorganismB.VISIBLE);
    	getItemText.setText(TEXT_MICROORGANISM_B);
    }
	}

	@Override
	public void onClick( View v ) {
		if(v.equals( closeButton )){
			this.finish();
		}
	}
}
