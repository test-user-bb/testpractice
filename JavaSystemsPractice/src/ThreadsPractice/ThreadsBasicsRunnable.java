package ThreadsPractice;

public class ThreadsBasicsRunnable  implements  Runnable  {

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	
	public void run() {
		for (int i = 0 ; i < 10;++i){
			System.out.println( Thread.currentThread().getName() + "  running thread: " + i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main (String[] args){
		Thread t1 = new Thread(new ThreadsBasicsRunnable());
		t1.start();
		Thread t2 = new Thread(new ThreadsBasicsRunnable());
		t2.start();
		
	}

}
