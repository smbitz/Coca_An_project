package codegears.coca.ui;

import codegears.coca.R;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShopItem extends LinearLayout {
	
	private TextView nameText;
	private TextView priceText;
	private TextView timeText;
	private ImageView shopItemImage;

	public ShopItem(Context context) {
		super( context );
		View.inflate(context, R.layout.shopitem, this);
		nameText = (TextView) findViewById(R.id.shopItemName);
		priceText = (TextView) findViewById(R.id.shopItemPrice);
		timeText = (TextView) findViewById(R.id.shopItemTime);
		shopItemImage = (ImageView) findViewById(R.id.shopItemImage);
		
		nameText.setTextColor(Color.BLACK);
		priceText.setTextColor(Color.BLACK);
		timeText.setTextColor(Color.BLACK);
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
		shopItemImage.setImageResource(resId);
	}
}
