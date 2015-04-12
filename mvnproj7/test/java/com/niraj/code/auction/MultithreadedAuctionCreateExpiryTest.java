package com.niraj.code.auction;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.niraj.code.AuctionFactory;
import com.niraj.code.item.Item;
import com.niraj.code.user.User;
/**
 * @author niraj
 * 
 * Test Objective : We are having concurrent threads to process the auction create request and 
 *                  then to process auction expirary request.
 *                  Having correct number of auctions getting created and then all getting processed 
 *                  at the end, ensures that our concurrent processing is happening perfectly. 
 *
 */

public class MultithreadedAuctionCreateExpiryTest {
	private static Logger log = Logger.getLogger(MultithreadedAuctionCreateExpiryTest.class.getCanonicalName());
	@Test
	public void auctionCreateTest() {

		AuctionManager aum = AuctionFactory.getAuctionmanager();
		
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
		
		Item item6 = new Item("i6","CDPlayer","Brand New CD player");
		Item item7 = new Item("i7","Radio","Brand New Radio player");
		Item item8 = new Item("i8","Mobile","Brand New Mobile player");
		Item item9  = new Item("i9","Camcorder","Brand New Camcorder player");
		Item item10 = new Item("i10","Camera","Brand New Camera player");
		
		
		aum.receiveNewAuctionfromUser(user2, item6, 100.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user2, item7, 200.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user2, item8, 300.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user2, item9, 400.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user2, item10, 400.0,new Date(), new Date());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Item item11 = new Item("i11","CDPlayer","Brand New CD player");
		Item item12 = new Item("i12","Radio","Brand New Radio player");
		Item item13 = new Item("i13","Mobile","Brand New Mobile player");
		Item item14  = new Item("i14","Camcorder","Brand New Camcorder player");
		Item item15 = new Item("i15","Camera","Brand New Camera player");
		
		aum.receiveNewAuctionfromUser(user3, item11, 100.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user3, item12, 200.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user3, item13, 300.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user3, item14, 400.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user3, item15, 400.0,new Date(), new Date());
		
		
		//This should add 15 aucton items
		aum.startAuctionAddService();
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		log.info(String.format("Display all the auction: \n %s",aum.getAllAuctions()) ) ;
		log.info(String.format("Total Number of auctions got created: %d",aum.getAllAuctionsCount()));
		
		assertEquals(15, aum.getAllAuctionsCount());
		
		log.info(String.format("Display top bids for the item: \n %s",aum.getTopNBids(item1.getItemId())));
		log.info(String.format("Display top bids for the item: \n %s",aum.getTopNBids(item10.getItemId())));
		log.info(String.format("Display top bids for the item: \n %s",aum.getTopNBids(item15.getItemId())));
		
		
		
		
		//This should process all the  15 expired auctions
		
		aum.startAuctionExpiryService();
		
		log.info(String.format("Display all the auction: \n %s",aum.getAllAuctions()) ) ;
		log.info(String.format("Total Number of auctions remaining after expiry: %d",aum.getAllAuctionsCount()));
		
		//The Actual Test
		assertEquals(0, aum.getAllAuctionsCount());
		
	}
	
	
}
