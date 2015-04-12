package com.niraj.code.auction;


import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import com.niraj.code.config.AuctionConfig;
import com.niraj.code.item.Item;
import com.niraj.code.user.User;


public class AuctionManagerImpl implements AuctionManager {

	private static Logger log = Logger.getLogger(AuctionManagerImpl.class.getCanonicalName());
	NewAuctionReceiveQ auctionQueue;
	ExecutorService auctionAddService;
	int createAuctionThreadCount;

	private AuctionManagerImpl(){
		createAuctionThreadCount=AuctionConfig.loadConfig().getAuctionCreateThreadsCount();
		auctionQueue = new NewAuctionReceiveQ();
		auctionAddService = Executors.newFixedThreadPool(createAuctionThreadCount);
	}

	static  AuctionManager  auctionManager;
	static{
		auctionManager = new AuctionManagerImpl();
	}

	/**
	 * These singleton will be used by drivers and threads to access the object.
	 * 
	 */
	static AuctionManager getInstance(){
		return auctionManager;
	}

	/**
	 *  This will be directly called by some user utility or driver program to submit the auction
	 */

	public void receiveNewAuctionfromUser(User user, Item item, Double askPrice,Date auctionStartTime,Date auctionEndTime) {
		auctionQueue.receieveNewAuction(user, item, askPrice,auctionStartTime,auctionEndTime);
		log.info(String.format("Received item: %s from user %s for auction",item.getItemName(),user.getLoginId()));
	}


	/**
	 *  This will be called from inside the executor threads which are 
	 *  polling the raw auction queue.
	 *  This provides the decoupling scalability  
	 */
	public void createAuction(Auction auction) {
			AuctionProcessorImpl.getInstance().createAuction(auction);
	}

	public String getAllAuctions() {
		return AuctionProcessorImpl.getInstance().getAllAuctions();
	}
	public int getAllAuctionsCount() {
		return AuctionProcessorImpl.getInstance().getAllAuctionsCount();
	}


	/**
	 * Here we create multiple threads which will poll the reciveQ for the new auction 
	 * requests and will create corresponding auctions.
	 * We assume that there is certain layer above which do the required validation for the
	 * user and item etc.
	 */

	public void startAuctionAddService() {
		//This function will get called through a timer task.
		// This will submit the tasks which will internally call the 
		// createAuction() function which will pick up the auction data from the Q and create 
		// corresponding auction task.
		Auction auction;
		while ((auction=auctionQueue.pickNewAuction()) != null)
			auctionAddService.submit(new CreateAuction(auction));
		}

	 

	/**
	 * This will get invoke at the certain time frequency through a timer task and 
	 * it will make call to auction processor auction end task which will submit the 
	 * expired auctions to its own excutor pool thread, the threads will be configurable.
	 * 
	 */
	
	public void startAuctionExpiryService() {
		AuctionFactory.getAuctionProcessor().startAuctionExpiryService();
	}

	class CreateAuction implements Callable<String> {
		Auction auction ;
		CreateAuction(Auction auction ){
			this.auction=auction;
		}
		public String call() throws Exception {
			createAuction(auction);
			return null;
		}
	 }
	


}
