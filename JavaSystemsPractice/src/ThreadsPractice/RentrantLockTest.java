package ThreadsPractice;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//:2:54 PM
public class RentrantLockTest {

	int count=0;
	
	 private Lock rlock = new ReentrantLock();
	
	Condition cnd = rlock.newCondition();

	public void incrementNumber() throws InterruptedException{
		for(int i=0; i < 1000; ++i){
			++count;	
			Thread.sleep(10);
			
		}
	}

	public void firstThread() throws InterruptedException{
		
		System.out.println("Started the first thread");
		rlock.lock();
		System.out.println("Received the lock, waiting on to the condition");
		cnd.await();
		incrementNumber();
		rlock.unlock();
		
	}



	public void secondThread() throws InterruptedException{
		Thread.sleep(1000);
		System.out.println("Starting the second thread");
		System.out.println("Hit enter");
		new Scanner(System.in).nextLine();
		rlock.lock();
		cnd.signal();
		incrementNumber();
		rlock.unlock();
		
		
		
		
	}

	public static void main(String[] args) throws InterruptedException{

		RentrantLockTest rts = new RentrantLockTest();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					rts.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}); t1.start();

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					rts.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}); t2.start();

		t1.join();
		t2.join();

		System.out.println("Count is:"+ rts.getCount());
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

}

//3:21 PM