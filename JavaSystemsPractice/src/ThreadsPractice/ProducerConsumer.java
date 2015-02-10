//9:00 AM
package ThreadsPractice;

import java.util.Random;
import java.util.concurrent.*;
public class ProducerConsumer {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(20);

	private static void producer() throws InterruptedException{
		Random rand = new Random();
		while(true){
			queue.put(rand.nextInt(100));
			Thread.sleep(100);
		}
	}

	private static void consumer() throws InterruptedException{
		Random rand = new Random();
		while(true){
			if(rand.nextInt(10)==9){
				System.out.println("Element removed is "+ queue.take());
				System.out.println("Queue Size is : "+queue.size());
			}
			Thread.sleep(10);
		}

	}

	public static void main(String[] args){
		Thread producer = new Thread (new Runnable() {

			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}); 

		Thread consumer = new Thread (new Runnable() {

			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}); 

		producer.start();
		consumer.start();



	}
}
//9:13
