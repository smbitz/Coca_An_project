package codegears.coca.ui;

import codegears.coca.MyApp;
import codegears.coca.R;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShopItem extends LinearLayout {
	
	private static final int TEXT_TIME_SIZE = 15;
	
	private String itemId;
	private TextView nameText;
	private TextView priceText;
	private TextView timeText;
	private ImageView shopItemImage;
	private ImageButton shopButton;
	private FrameLayout shopItemBackground;
	private MyApp app;

	public ShopItem(Context context) {
		super( context );
		View.inflate(context, R.layout.shopitem, this);
		
		app = (MyApp) context.getApplicationContext();
		
		nameText = (TextView) findViewById(R.id.shopItemName);
		priceText = (TextView) findViewById(R.id.shopItemPrice);
		timeText = (TextView) findViewById(R.id.shopItemTime);
		shopItemImage = (ImageView) findViewById(R.id.shopItemImage);
		shopButton = (ImageButton) findViewById(R.id.shopButton);
		shopItemBackground = (FrameLayout) findViewById(R.id.shopItemBackground);

		nameText.setTextColor(Color.BLACK);
		priceText.setTextColor(Color.BLACK);
		timeText.setTextColor(Color.BLACK);
		nameText.setTypeface( app.getTextFont() );
		priceText.setTypeface( app.getTextFont() );
		timeText.setTypeface( app.getTextFont() );
		timeText.setTextSize( TEXT_TIME_SIZE );
	}
	
	public String getItemId(){
		return itemId;
	}
	
	public String getItemName(){
		return nameText.getText().toString();
	}
	
	public ImageButton getShopButton(){
		return shopButton;
	}
	
	public void setItemName(String name){
		nameText.setText( name );
	}
	
	public void setItemId(String id){
		itemId = id;
	}
	
	public void setItemPrice(String price){
		priceText.setText( price );
	}
	
	public void setItemTime(String time){
		timeText.setText( time );
	}
	
	public void setItemImage( int resId ){
		shopItemImage.setImageResource(resId);
	}

	public void setItemBackground( int shopItemBackgroundId ){
		shopItemBackground.setBackgroundResource( shopItemBackgroundId );
	}
	
	public void setShopButton( int shopButtonImageId ){
		shopButton.setImageResource( shopButtonImageId );
	}
	
	public void setItemTimeColor( int setColor ){
		timeText.setTextColor( setColor );
	}
	
}
