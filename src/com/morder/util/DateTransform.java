package com.morder.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransform {
	
	// �r���ഫ���
	public static Timestamp translatedToTimestamp(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Date date = null;
		try {
			// get java.util.Date
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// use java.util.Date.getTime to translate to Timestamp
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
	
	// ����ഫ�r��
	public static String TimeStampTranslatedToString(Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(timestamp.getTime()));
	}
}
