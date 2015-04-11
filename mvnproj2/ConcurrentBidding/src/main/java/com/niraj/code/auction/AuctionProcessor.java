package com.niraj.code.auction;

import java.util.List;

import com.niraj.code.bid.Bid;



public interface AuctionProcessor {
	public int createAuction(Auction auction);
	public String getAllAuctions();
	public boolean auctionExpiry(int auctionId);
	public void notifyBidders();
	public List<Bid> getTopBids(int auctionId);
	public int getAllAuctionsCount() ;
}
