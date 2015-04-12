package com.niraj.code.auction;

import java.util.List;

import com.niraj.code.bid.Bid;



public interface AuctionProcessor {
	public long createAuction(Auction auction);
	public String getAllAuctions();
	public void auctionExpiry(Auction auction);
	public void notifyBidders();
	public List<Bid> getTopBids(int auctionId);
	public int getAllAuctionsCount() ;
	public void startAuctionExpiryService() ;
}
