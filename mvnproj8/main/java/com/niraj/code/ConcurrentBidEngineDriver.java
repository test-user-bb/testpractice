package com.niraj.code;

import java.util.*;

import org.apache.log4j.Logger;

import com.niraj.code.auction.AuctionManager;
import com.niraj.code.item.Item;
import com.niraj.code.user.User;

public class ConcurrentBidEngineDriver {


	Timer auctionAddService,auctionExpiryService,bidAddService;
	private static Logger log = Logger.getLogger(ConcurrentBidEngineDriver.class.getCanonicalName());
	AuctionManager auctionManager;
	public ConcurrentBidEngineDriver() {
		auctionAddService = new Timer();
		auctionExpiryService = new Timer();
		bidAddService = new Timer();
		auctionManager = AuctionFactory.getAuctionmanager();

		
	}
	
	public void  scheduleConcurrentBidEngine(int seconds){
		auctionAddService.scheduleAtFixedRate(new AuctionAddServiceTask(auctionManager), 10, 1000*seconds);
		auctionExpiryService.scheduleAtFixedRate(new BidAddServiceTask(auctionManager), 10, 1000*seconds);
		bidAddService.scheduleAtFixedRate(new AuctionExpiryServiceTask(auctionManager), 10, 1000*seconds);
	}

	public static void main(String args[]) {
		
		User user1 = new User("user1","bawankar1","niraj1");
		User user2 = new User("user2","bawankar2","niraj2");
		
		User user3 = new User("user3","bawankar3","niraj3");
		
		User user4 = new User("user4","bawankar4","niraj4");
		
		Item item1 = new Item("i1","CDPlayer","Brand New CD player");
		Item item2 = new Item("i2","Radio","Brand New Radio player");
		Item item3 = new Item("i3","Mobile","Brand New Mobile player");
		Item item4  = new Item("i4","Camcorder","Brand New Camcorder player");
		Item item5 = new Item("i5","Camera","Brand New Camera player");
		AuctionManager aum = AuctionFactory.getAuctionmanager();
		
		aum.receiveNewAuctionfromUser(user1, item1, 100.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user1, item2, 200.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user1, item3, 300.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user1, item4, 400.0,new Date(), new Date());
		aum.receiveNewAuctionfromUser(user1, item5, 400.0,new Date(), new Date());
		
		
		log.info("Starting the ConcurrentBidEngineDriver");
		new ConcurrentBidEngineDriver().scheduleConcurrentBidEngine(2);		
	}
	
	
	class AuctionAddServiceTask extends TimerTask {
		AuctionManager auctionManager;
		AuctionAddServiceTask(AuctionManager auctionManager){
			this.auctionManager=auctionManager;
		}
		public void run() {
			log.info("Starting the AuctionAddServiceTask");
			auctionManager.startAuctionAddService();
		}
	}

	class BidAddServiceTask extends TimerTask {
		AuctionManager auctionManager;
		BidAddServiceTask(AuctionManager auctionManager){
			this.auctionManager=auctionManager;
		}
		public void run() {
			log.info("Starting the BidAddServiceTask");
			//auctionManager.startBidAddService();
		}
	}


	class AuctionExpiryServiceTask extends TimerTask {
		AuctionManager auctionManager;
		AuctionExpiryServiceTask(AuctionManager auctionManager){
			this.auctionManager=auctionManager;
		}
		public void run() {
			log.info("Starting the AuctionExpiryServiceTask");
			auctionManager.startAuctionExpiryService();
		}
	}

	
}

/*

public class BidEngineDemoDriver {
   public static void main(String[] args) {
      // creating timer task, timer
      TimerTask tasknew = new TimerScheduleFixedRateDelay();
      Timer timer = new Timer();

      // scheduling the task at fixed rate delay
      timer.scheduleAtFixedRate(tasknew,500,1000);      
   }
   // this method performs the task
   public void run() {
      System.out.println("working at fixed rate delay");      
   }    
}
 */