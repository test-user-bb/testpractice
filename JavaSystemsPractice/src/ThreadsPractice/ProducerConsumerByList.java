//12:13 PM
package ThreadsPractice;

import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumerByList {

	private static LinkedList<Integer> list = new LinkedList<Integer>();
	private static int MAX=20;

	
	private static Object lock = new Object();

	private static void producer() throws InterruptedException{
		int num=0;
		while(true){
			synchronized (lock) {
				while(list.size() == MAX){
					lock.wait();
				}
				list.addFirst(num++);
				lock.notify();
			}
			
			Thread.sleep(30);
		}
	}

	private static void consumer() throws InterruptedException{
		int value=0;
		Random rand = new Random();
		while(true){
			synchronized (lock) {
				
				while(list.size() == 0){
					lock.wait();
				}
				System.out.print("The size of the list is " + list.size() + ";");
				value = list.removeLast();
				System.out.println("Value removed : "+ value);
				lock.notify();
			}
			Thread.sleep(rand.nextInt(100));
		}

	}

	public static void main(String[] args){
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
	

	new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}).start();
}


}

//12:38