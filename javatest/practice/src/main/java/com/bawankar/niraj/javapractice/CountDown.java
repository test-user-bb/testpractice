package com.bawankar.niraj.javapractice;

import java.util.concurrent.CountDownLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class CountDown implements Runnable {

	static private Logger logger = Logger.getLogger(CountDown.class.getName());
	
	private CountDownLatch cl;
	private int id; 

	public CountDown(CountDownLatch cl, int id) {
		this.cl=cl;
		this.id=id;
	}
	public void run() {
		logger.info("Starting thread :"+id);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("From thread "+ id + " The latch count is  " +cl.getCount());
		cl.countDown();
		logger.info("Ending  thread :"+id);
	}

	public static void main(String[] args){
		logger.info("Started test");
		
		ExecutorService ex = Executors.newFixedThreadPool(20);
		
		CountDownLatch cl = new CountDownLatch(201);
		
		for(int i = 0; i < 200 ; ++i){
			ex.submit(new CountDown(cl, i));
		}
		
		ex.shutdown();
		try {
			cl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("End of program");
		logger.info("From main" + " The latch count is  " +cl.getCount());
		
	}

}
