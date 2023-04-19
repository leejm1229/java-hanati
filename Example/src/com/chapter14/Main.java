package com.chapter14;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		calendar.setTime(nowDate);

		int day = calendar.get(calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, day+100);
		
		Date future = calendar.getTime();
		String date = format.format(future);
		
		System.out.println(date);	
	}
}
