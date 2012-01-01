package codegears.coca.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MilliSecToHour {
	
	public static String getConvert(int getMilliSec){
		int Hours = getMilliSec / (1000*60*60);
		int Minutes = (getMilliSec % (1000*60*60)) / (1000*60);
		int Seconds = ((getMilliSec % (1000*60*60)) % (1000*60)) / 1000;
		
		return checkLength(Hours)+":"+checkLength(Minutes)+":"+checkLength(Seconds);
	}
	
	private static String checkLength(int checkValue){
		String strCheckValue = String.valueOf(checkValue);
		
		if(strCheckValue.length()<2){
			return "0"+checkValue;
		}
		
		return strCheckValue;
	}
	
}
