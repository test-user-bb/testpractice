package com.niraj.code.utils;

import java.util.Date;

public class Utilities {
	public static Date getFutureDateAfterSeconds(int seconds) {
		long futureDateMS = System.currentTimeMillis() + ((long)seconds) *1000;
		Date futureDate = new Date();
		futureDate.setTime(futureDateMS);
		return futureDate;
	}   

	public static void sleepForMS(long sleepMs){
		try {
			Thread.sleep(sleepMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
