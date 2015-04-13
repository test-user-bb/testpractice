package com.niraj.code.auction;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.niraj.code.AuctionFactory;
import com.niraj.code.bid.BidManagerImpl;
import com.niraj.code.config.AuctionConfig;
import com.niraj.code.item.Item;
import com.niraj.code.user.User;

public class MultithreadedAuctionBidAddTests {
	private static Logger log = Logger.getLogger(MultithreadedAuctionBidAddTests.class.getCanonicalName());
	AuctionManager auctionManager; 
	@Before
	public void setUp(){
	auctionManager = AuctionFactory.getAuctionmanager();
	}
	
	/**
	 * Test new bids from new user and update of any existing bid from the user who have already submitted 
	 * the bid  
	 */
	@Test
	public void bidAdditionTest() {
	
		User user1 = new User("user1","bawankar1","niraj1");
		User user2 = new User("user2","bawankar2","niraj2");
		User user3 = new User("user3","bawankar3","niraj3");
		User user4 = new User("user4","bawankar4","niraj4");
		
		
		Item item1 = new Item("i1","CDPlayer","Brand New CD player");
		Item item2 = new Item("i2","Radio","Brand New Radio player");
		
		auctionManager.receiveNewAuctionfromUser(user1, item1, 100.0,new Date(), new Date());
		auctionManager.receiveNewAuctionfromUser(user1, item2, 100.0,new Date(), new Date());
		
		letsSleep(1000);

		auctionManager.startAuctionAddService();
		
		letsSleep(2000);
		
		assertEquals(2, auctionManager.getAllAuctionsCount());
		
		auctionManager.receieveNewBid(item1.getItemId(),1, user2.getLoginId(), 102.0);
		
		auctionManager.startBidAddService();
		
		letsSleep(5000);
		
		assertEquals(1, auctionManager.getTotalBids(item1.getItemId()));
		
		
		
		
		//auctionManager.shutDownBiddingEngine();
		
		
		
		
	}
	public void letsSleep(long sleepMs){
		try {
			Thread.sleep(sleepMs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
