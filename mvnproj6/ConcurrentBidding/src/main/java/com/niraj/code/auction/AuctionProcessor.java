package com.niraj.code.auction;

import com.niraj.code.bid.Bid;



public interface AuctionProcessor {
	public long createAuction(Auction auction);
	public String getAllAuctions();
	public Bid auctionExpiry(Auction auction);
	public void notifyBidders();
	public String getTopBids(String itemId);
	public int getAllAuctionsCount() ;
	public String startAuctionExpiryService() ;
}
