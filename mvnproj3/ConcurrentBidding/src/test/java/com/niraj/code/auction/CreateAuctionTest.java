package com.niraj.code.auction;

import static org.junit.Assert.*;

import org.junit.Test;

import com.niraj.code.item.Item;
import com.niraj.code.user.User;

public class CreateAuctionTest {

	@Test
	public void auctionCreateTest() {
		User user1 = new User("user1","bawankar","niraj");
		Item item1 = new Item("i1","CDPlayer","Brand New CD player");
		AuctionManager aum = AuctionManagerImpl.getInstance();
		
		/*
		 * This we have simulated calls from the timer task.
		 * but create auction call will be synchronous.
		 */
		
		aum.startAuctionAddService();
		
		aum.startAuctionExpiryService();
		
		aum.startAuctionAddService();
		
		aum.startAuctionExpiryService();
	
	
		/*
		aum.receiveNewAuctionfromUser(user1, item1, 100.0);
		aum.createAuction();
		Item item2 = new Item("i2","Radio","Brand New Radio player");
		Item item3 = new Item("i3","Mobile","Brand New Mobile player");
		Item item4  = new Item("i4","Camcorder","Brand New Camcorder player");
		Item item5 = new Item("i5","Camera","Brand New Camera player");
		aum.receiveNewAuctionfromUser(user1, item2, 200.0);
		aum.createAuction();
		
		aum.receiveNewAuctionfromUser(user1, item3, 300.0);
		aum.createAuction();
		
		aum.receiveNewAuctionfromUser(user1, item4, 400.0);
		aum.createAuction();
		
		System.out.println(aum.getAllAuctions());
		assertEquals(4, aum.getAllAuctionsCount());
		*/
	}
	
	
}
