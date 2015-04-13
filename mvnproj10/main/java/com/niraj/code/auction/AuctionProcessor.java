package com.niraj.code.auction;

import java.util.Date;

import com.niraj.code.bid.Bid;



public interface AuctionProcessor {
	public long createAuction(Auction auction);
	public String getAllAuctions();
	public Bid auctionExpiry(Auction auction);
	public void notifyBidders();
	public String getTopNBids(String itemId);
	public int getAllAuctionsCount() ;
	public String startAuctionExpiryService() ;
	public boolean validateAuction(long auctionId, Date bidDate, Double bidPrice);
	public boolean addNewBid(Bid bid);
	public long getTotalBids(String itemId);
	public void shutDownAuctionExpiryService() ;
}
