package ThreadsPractice;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

public class MultiThreadsSyncTest {

	MultiThreadsSync s = new MultiThreadsSync();
	//private static Logger logger = Logger.getLogger(MultiThreadsSync.class);
	@Test
	public void testSync() {
		
		
		
		long starttime= System.currentTimeMillis();
		Thread t1 = new Thread(s);
		Thread t2 = new Thread(s);
		
		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(2000, s.getSizeLits1());
		assertEquals(2000, s.getSizeLits2());
		long endTime= System.currentTimeMillis();
		long runTime=endTime-starttime;
		//logger.info("Total time for test is :" + runTime);
		
		System.out.println(runTime);
		System.out.println(s.getSizeLits1());
		System.out.println(s.getSizeLits2());
		
		
	//	assertEquals(1002, s.getSizeLits1());
	}

}
