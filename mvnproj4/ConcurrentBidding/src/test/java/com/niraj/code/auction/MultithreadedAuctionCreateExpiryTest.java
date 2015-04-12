package com.niraj.code.auction;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.niraj.code.item.Item;
import com.niraj.code.user.User;

public class MultithreadedAuctionCreateExpiryTest {

	@Test
	public void auctionCreateTest() {

		AuctionManager aum = AuctionManagerImpl.getInstance();
		
		User user1 = new User("user1","bawankar1","niraj1");
		User user2 = new User("user2","bawankar2","niraj2");
		
		User user3 = new User("user3","bawankar3","niraj3");
		
		User user4 = new User("user4","bawankar4","niraj4");
		
		Item item1 = new Item("i1","CDPlayer","Brand New CD player");
		Item item2 = new Item("i2","Radio","Brand New Radio player");
		Item item3 = new Item("i3","Mobile","Brand New Mobile player");
		Item item4  = new Item("i4","Camcorder","Brand New Camcorder player");
		Item item5 = new Item("i5","Camera","Brand New Camera player");
		
		aum.receiveNewAuctionfromUser(user1, item1, 100.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user1, item2, 200.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user1, item3, 300.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user1, item4, 400.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user1, item5, 400.0,new Date(), new Date());
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		aum.receiveNewAuctionfromUser(user2, item1, 100.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user2, item2, 200.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user2, item3, 300.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user2, item4, 400.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user2, item5, 400.0,new Date(), new Date());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		aum.receiveNewAuctionfromUser(user3, item1, 100.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user3, item2, 200.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user3, item3, 300.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user3, item4, 400.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user3, item5, 400.0,new Date(), new Date());
		
		
		//This should add 15 aucton items
		aum.startAuctionAddService();
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println(aum.getAllAuctions());
		System.out.println(aum.getAllAuctionsCount());
		assertEquals(15, aum.getAllAuctionsCount());
		
		//This should delete 15 auction items
		 aum.startAuctionExpiryService();
		
		System.out.println(aum.getAllAuctions());
		System.out.println(aum.getAllAuctionsCount());
		assertEquals(0, aum.getAllAuctionsCount());
		
	}
	
	
}
