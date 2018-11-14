package com.tsl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static Date getNow() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String now = df.format(new Date());
		Date date=null;
		try {
			date = df.parse(now);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
