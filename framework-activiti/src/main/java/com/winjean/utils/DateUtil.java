package com.winjean.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	/**
	 * 判断日期格式是否合法
	 * @param date
	 * @return
	 */
	public static boolean isValidDate(String date) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.setLenient(false);
			format.parse(date);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}
	
}
