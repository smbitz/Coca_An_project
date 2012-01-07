package codegears.coca.ui;

import codegears.coca.R;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShopItem extends LinearLayout {
	
	private String itemId;
	private TextView nameText;
	private TextView priceText;
	private TextView timeText;
	private ImageView shopItemImage;
	private ImageButton shopButton;
	private LinearLayout shopItemBackground;

	public ShopItem(Context context) {
		super( context );
		View.inflate(context, R.layout.shopitem, this);
		nameText = (TextView) findViewById(R.id.shopItemName);
		priceText = (TextView) findViewById(R.id.shopItemPrice);
		timeText = (TextView) findViewById(R.id.shopItemTime);
		shopItemImage = (ImageView) findViewById(R.id.shopItemImage);
		shopButton = (ImageButton) findViewById(R.id.shopButton);
		shopItemBackground = (LinearLayout) findViewById(R.id.shopItemBackground);

		nameText.setTextColor(Color.BLACK);
		priceText.setTextColor(Color.BLACK);
		timeText.setTextColor(Color.BLACK);
	}
	
	public void setItemId(String id){
		itemId = id;
	}
	
	public String getItemId(){
		return itemId;
	}
	
	public void setItemName(String name){
		nameText.setText( name );
	}
	
	public String getItemName(){
		return nameText.getText().toString();
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

	public void setItemBackground( int resId ){
		shopItemBackground.setBackgroundResource(resId);
	}
	
	public void setShopButton( int resId ){
		shopButton.setImageResource(resId);
	}
	
}
