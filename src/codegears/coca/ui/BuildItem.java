package codegears.coca.ui;

import codegears.coca.R;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BuildItem extends LinearLayout {

	private TextView nameText;
	private TextView priceText;
	private ImageView buildItemImage;
	
	public BuildItem( Context context ) {
		super( context );
		View.inflate( context, R.layout.builditem, this );
		nameText = (TextView)this.findViewById( R.id.ItemName );
		priceText = (TextView)this.findViewById( R.id.ItemPrice );
		buildItemImage = (ImageView) this.findViewById(R.id.buildItemImage);
	}
	
	public void setItemName(String name){
		nameText.setText( name );
	}
	
	public void setItemPrice(String price){
		priceText.setText( price );
	}

	public void setItemImage(int resId){
		buildItemImage.setImageResource(resId);
	}
}
