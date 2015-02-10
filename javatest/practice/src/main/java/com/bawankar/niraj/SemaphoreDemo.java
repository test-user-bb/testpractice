package com.bawankar.niraj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//8:28 Pm
public class SemaphoreDemo {

	int connections=0;

	Semaphore s = new Semaphore(10);
	private void  showConnection(){
		System.out.println(connections);
	}

	public void getConnections(){
		++connections;
	}


	public static void main(String[] args) throws InterruptedException{
		final SemaphoreDemo sem = new SemaphoreDemo();

		ExecutorService ex = Executors.newCachedThreadPool();

		for(int i = 0; i < 50;++i){
			ex.submit(new Runnable() {

				public void run() {
					try {
						sem.s.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized (sem) {
						sem.getConnections();
						sem.showConnection();
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					synchronized (sem) {
						sem.leaveConnections();

					}
					sem.s.release();
					//System.out.println(sem.s.availablePermits());

				}
			});


		}
		ex.shutdown();
		ex.awaitTermination(1, TimeUnit.DAYS);
	}

	protected void leaveConnections() {
		--connections;

	}
}
//8:43