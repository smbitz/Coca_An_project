package codegears.coca.ui;

import codegears.coca.MyApp;
import codegears.coca.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BuildItem extends LinearLayout {

	private static final int TEXT_TIME_SIZE = 15;
	
	private TextView nameText;
	private TextView priceText;
	private TextView timeText;
	private ImageView buildItemImage;
	private String itemId;
	private MyApp app;
	
	public BuildItem( Context context ) {
		super( context );
		View.inflate( context, R.layout.builditem, this );
		
		app = (MyApp) context.getApplicationContext();
		
		nameText = (TextView) this.findViewById( R.id.itemName );
		priceText = (TextView) this.findViewById( R.id.itemPrice );
		timeText = (TextView) this.findViewById(R.id.itemTime);
		buildItemImage = (ImageView) this.findViewById(R.id.buildItemImage);

		nameText.setTextColor(Color.BLACK);
		priceText.setTextColor(Color.BLACK);
		timeText.setTextColor(Color.BLACK);
		nameText.setTypeface( app.getTextFont() );
		priceText.setTypeface( app.getTextFont() );
		timeText.setTypeface( app.getTextFont() );
		timeText.setTextSize( TEXT_TIME_SIZE );
		this.setClickable( true );
	}
	
	public String getItemName(){
		return nameText.getText().toString();
	}
	
	public String getItemId(){
		return itemId;
	}
	
	public void setItemId(String id){
		itemId = id;
	}
	
	public void setItemName(String name){
		nameText.setText( name );
	}
	
	public void setItemPrice(String price){
		priceText.setText( price );
	}
	
	public void setItemTime(String time){
		timeText.setText( time );
	}

	public void setItemImage(int resId){
		buildItemImage.setImageResource(resId);
	}
	
	public void setItemTimeColor( int setColor ){
		priceText.setTextColor( setColor );
	}
}
