package ThreadsPractice;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingletonTest {

	@Test
	public void test() {
		
		Singleton.getInstance();
		
		assertEquals(1001, Singleton.ID);
		
		Singleton.getInstance();
		
		assertEquals(1001, Singleton.ID);
		
		
		Singleton.getInstance();
		
		assertNotEquals(1002, Singleton.ID);
		
	}

}
