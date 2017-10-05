package de.fancyusername.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	public static final SimpleDateFormat YMDHMS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static Date parseHrMin(String hrMinStr) {
		try {
			return YMDHMS_FORMAT.parse("2001-01-01 " + hrMinStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
