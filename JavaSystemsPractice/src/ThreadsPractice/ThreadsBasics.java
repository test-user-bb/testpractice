package ThreadsPractice;

public class ThreadsBasics  extends Thread  {

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		for (int i = 0 ; i < 10;++i){
			System.out.println( Thread.currentThread().getName() + "  running thread: " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main (String[] args){
		Thread t1 = new ThreadsBasics();
		t1.start();
		Thread t2 = new ThreadsBasics();
		t2.start();
	}

}
