//7:33 PM
package DeadLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class SimulateDeadlock {

	int count1 = 100000;
	int count2 = 100000;

	private void moveCount(int value){
		count1 -=value;
		count2 +=value;
	}

	Lock lock1 = new ReentrantLock();
	Lock lock2 = new ReentrantLock();

	private boolean acquireLock() throws InterruptedException{

		while(true){
			if ( lock1.tryLock(100,TimeUnit.MILLISECONDS) ){
				if(lock2.tryLock(100,TimeUnit.MILLISECONDS)){
					return true;
				}else{
					lock1.unlock();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException{

		final SimulateDeadlock d1 = new SimulateDeadlock();
		final Random rand1 = new Random();
		final Random rand2 = new Random();


		Thread t1 = new Thread (new Runnable() {
			public void run() {

				for (int i = 0; i < 200; ++i){
					try{
						try {
							d1.acquireLock();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						d1.moveCount(rand1.nextInt(100));
					}finally{
						d1.lock1.unlock();
						d1.lock2.unlock();
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}); 

		Thread t2 = new Thread (new Runnable() {
			public void run() {

				for (int i = 0; i < 500; ++i){
					try{
						try {
							d1.acquireLock();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						d1.moveCount(rand2.nextInt(100));
					}finally{
						d1.lock2.unlock();
						d1.lock1.unlock();
					}

					
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
		});

		t1.start();
		t2.start();
		

		t1.join();
		t2.join();
		

		System.out.println(d1.count1+d1.count2);
		
	}
}

//8:02 PM