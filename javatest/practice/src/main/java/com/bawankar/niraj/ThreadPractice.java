package com.bawankar.niraj;

import org.apache.log4j.Logger;

public class ThreadPractice implements Runnable {
	
	static private Logger logger = Logger.getLogger(ThreadPractice.class.getName());
	public void run() {
		
	}
	
	public static void main(String[] args){
		logger.info("test");
	}

}
