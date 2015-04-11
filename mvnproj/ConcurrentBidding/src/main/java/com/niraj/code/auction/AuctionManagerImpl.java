package com.niraj.code.auction;


import com.niraj.code.item.Item;
import com.niraj.code.user.User;


public class AuctionManagerImpl implements AuctionManager {

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
	 *  This will be directly called by some user utility or driver program 
	 */
	
	public void receiveNewAuctionfromUser(User user, Item item, Double askPrice) {

		auctionQueue.receieveNewAuction(user, item, askPrice);
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
	
}
