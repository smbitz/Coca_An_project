package codegears.coca.dialog;

import java.util.HashMap;

import codegears.coca.GameActivity;
import codegears.coca.MyApp;
import codegears.coca.R;
import codegears.coca.util.LoadResource;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialDialog extends Activity implements OnClickListener {
	public static final int TUTORIAL_ACTIVITY_CLOSE = 0;
	
	private static final String TUTORIAL_TEXT_1 = "สวัสดีครับ/ค่ะ ยินดีต้อนรับเข้าสู่ COCA LAND!\nพวกเราจะมาแนะนำวิธีการเล่นเกมส์";
	private static final String TUTORIAL_TEXT_2 = "เราสามารถเริ่มต้นสร้างฟาร์มของเราง่าย ๆ\nด้วยการคลิกที่พื้นที่เพื่อเลือกพืชหรือสัตว์ที่ต้องการ";
	private static final String TUTORIAL_TEXT_3 = "หลังจากนั้น เราต้องดูแล พืชผักและสัตว์เลี้ยงของเรา\nด้วยการรดน้ำหรือให้อาหาร เพื่อให้ได้ผลผลิตที่ดีที่สุด";
	private static final String TUTORIAL_TEXT_4 = "จากนั้น เมื่อพืชและสัตว์เลี้ยงของเราโตเต็มที่\nก็จะมีผลผลิตมากมายให้แก่เรา";
	private static final String TUTORIAL_TEXT_5 = "ผลผลิตที่ได้ เราสามารถนำไปขายได้ที่ Coca Market\nหรือจะเก็บไว้แลกคูปองก็ได้นะ";
	private static final String TUTORIAL_TEXT_6 = "คูปองจากในเกม สามารถนำไปแลกอาหาร\nได้หลากหลายที่โคคาสุกี้อีกด้วย";
	
	private static final int TUTORIAL_STATE_1 = 0;
	private static final int TUTORIAL_STATE_2 = 1;
	private static final int TUTORIAL_STATE_3 = 2;
	private static final int TUTORIAL_STATE_4 = 3;
	private static final int TUTORIAL_STATE_5 = 4;
	private static final int TUTORIAL_STATE_6 = 5;
	
	private static final int TUTORIAL_TEXT_SIZE = 20;
	
	private ImageButton closeButton;
	private ImageButton nextButton;
	private ImageButton backButton;
	
	private MyApp app;
	
	private TextView tutorialText;
	
	private ImageView imageTutorialPage1;
	private ImageView imageTutorialPage2;
	private ImageView imageTutorialPage3;
	private ImageView imageTutorialPage4;
	private ImageView imageTutorialPage5;
	private ImageView imageTutorialPage6;
	
	private int currentTutorialState;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorialdialog);
		
		app = (MyApp) this.getApplication();
		
		closeButton = (ImageButton) findViewById(R.id.tutorialCloseButton);
		closeButton.setOnClickListener(this);
		nextButton = (ImageButton) findViewById(R.id.tutorialNextButton);
		nextButton.setOnClickListener(this);
		backButton = (ImageButton) findViewById(R.id.tutorialBackButton);
		backButton.setOnClickListener(this);
		
		tutorialText = (TextView) findViewById(R.id.tutorialText);
		tutorialText.setTextColor(Color.BLACK);
		tutorialText.setTypeface( app.getTextFont() );
		tutorialText.setTextSize( TUTORIAL_TEXT_SIZE );
		tutorialText.setText(TUTORIAL_TEXT_1);
		
		currentTutorialState = 0;
		
		imageTutorialPage1 = (ImageView) findViewById(R.id.tutrialPage1);
		imageTutorialPage2 = (ImageView) findViewById(R.id.tutrialPage2);
		imageTutorialPage3 = (ImageView) findViewById(R.id.tutrialPage3);
		imageTutorialPage4 = (ImageView) findViewById(R.id.tutrialPage4);
		imageTutorialPage5 = (ImageView) findViewById(R.id.tutrialPage5);
		imageTutorialPage6 = (ImageView) findViewById(R.id.tutrialPage6);
	}

	@Override
	public void onClick(View v) {
		if( v.equals(closeButton) ){
			this.setResult( TUTORIAL_ACTIVITY_CLOSE );
			this.finish();
		}else if( v.equals(nextButton) ){
			if( currentTutorialState==TUTORIAL_STATE_1 ){
				imageTutorialPage1.setVisibility(imageTutorialPage1.INVISIBLE);
				imageTutorialPage2.setVisibility(imageTutorialPage2.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_2);
				backButton.setVisibility(backButton.VISIBLE);
				currentTutorialState = TUTORIAL_STATE_2;
			}else if( currentTutorialState==TUTORIAL_STATE_2 ){
				imageTutorialPage2.setVisibility(imageTutorialPage2.INVISIBLE);
				imageTutorialPage3.setVisibility(imageTutorialPage3.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_3);
				currentTutorialState = TUTORIAL_STATE_3;
			}else if( currentTutorialState==TUTORIAL_STATE_3 ){
				imageTutorialPage3.setVisibility(imageTutorialPage3.INVISIBLE);
				imageTutorialPage4.setVisibility(imageTutorialPage4.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_4);
				currentTutorialState = TUTORIAL_STATE_4;
			}else if( currentTutorialState==TUTORIAL_STATE_4 ){
				imageTutorialPage4.setVisibility(imageTutorialPage4.INVISIBLE);
				imageTutorialPage5.setVisibility(imageTutorialPage5.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_5);
				currentTutorialState = TUTORIAL_STATE_5;
			}else if( currentTutorialState==TUTORIAL_STATE_5 ){
				imageTutorialPage5.setVisibility(imageTutorialPage5.INVISIBLE);
				imageTutorialPage6.setVisibility(imageTutorialPage6.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_6);
				nextButton.setVisibility(nextButton.INVISIBLE);
				currentTutorialState = TUTORIAL_STATE_6;
			}
		}else if( v.equals(backButton) ){
			if( currentTutorialState==TUTORIAL_STATE_6 ){
				imageTutorialPage6.setVisibility(imageTutorialPage6.INVISIBLE);
				imageTutorialPage5.setVisibility(imageTutorialPage5.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_5);
				nextButton.setVisibility(nextButton.VISIBLE);
				currentTutorialState = TUTORIAL_STATE_5;
			}else if( currentTutorialState==TUTORIAL_STATE_5 ){
				imageTutorialPage5.setVisibility(imageTutorialPage5.INVISIBLE);
				imageTutorialPage4.setVisibility(imageTutorialPage4.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_4);
				currentTutorialState = TUTORIAL_STATE_4;
			}else if( currentTutorialState==TUTORIAL_STATE_4 ){
				imageTutorialPage4.setVisibility(imageTutorialPage4.INVISIBLE);
				imageTutorialPage3.setVisibility(imageTutorialPage3.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_3);
				currentTutorialState = TUTORIAL_STATE_3;
			}else if( currentTutorialState==TUTORIAL_STATE_3 ){
				imageTutorialPage3.setVisibility(imageTutorialPage3.INVISIBLE);
				imageTutorialPage2.setVisibility(imageTutorialPage2.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_2);
				currentTutorialState = TUTORIAL_STATE_2;
			}else if( currentTutorialState==TUTORIAL_STATE_2 ){
				imageTutorialPage2.setVisibility(imageTutorialPage2.INVISIBLE);
				imageTutorialPage1.setVisibility(imageTutorialPage1.VISIBLE);
				tutorialText.setText(TUTORIAL_TEXT_1);
				backButton.setVisibility(backButton.INVISIBLE);
				currentTutorialState = TUTORIAL_STATE_1;
			}
		}
	}
}
