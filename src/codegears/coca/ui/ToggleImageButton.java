package codegears.coca.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class ToggleImageButton extends Button {

	private int uncheckedResource;
	private int checkedResource;
	private boolean checked;
	
	public ToggleImageButton( Context context, AttributeSet attrs ) {
		super( context, attrs );
		checked = false;
	}

	public void setResource(int checked, int unchecked){
		checkedResource = checked;
		uncheckedResource = unchecked;
		setChecked(this.checked);
	}
	
	public void setChecked(boolean checked){
		this.checked = checked;
		if(checked){
			this.setBackgroundResource( checkedResource );
		} else {
			this.setBackgroundResource( uncheckedResource );
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		if(event.getAction() == MotionEvent.ACTION_UP){
			this.setChecked(!checked);
		}
		return super.onTouchEvent( event );
	}
}
