package com.niraj.code.bid;

public interface BidManager {
	/**
	 * Receives a new Bid and places it into the Auction Q.
	 */
	public void startBidAddService() ;  
	public void shutDownBidAddService() ;
	public  boolean receieveNewBid(String itemId,long auctionId,String userLoginId, Double bidPrice );
	
}
