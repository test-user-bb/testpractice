package com.niraj.code.auction;


import java.util.Date;

import org.apache.log4j.Logger;

import com.niraj.code.item.Item;
import com.niraj.code.user.User;


public class AuctionManagerImpl implements AuctionManager {

	private static Logger log = Logger.getLogger(AuctionManagerImpl.class.getCanonicalName());
	NewAuctionReceiveQ auctionQueue;

	private AuctionManagerImpl(){
		auctionQueue = new NewAuctionReceiveQ();
	}

	static  AuctionManager  auctionManager;
	static{
		auctionManager = new AuctionManagerImpl();
	}

	/**
	 * These singleton will be used by drivers and threads to access the object.
	 * 
	 */
	public static AuctionManager getInstance(){
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
	public void createAuction() {
		Auction auction = auctionQueue.pickNewAuction();
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
	public void startAuctionManager() {

		
	}

}
