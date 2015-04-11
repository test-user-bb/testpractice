package com.niraj.code.auction;

import java.util.List;

import com.niraj.code.bid.Bid;



public interface AuctionProcessor {
	public int createAuction(Auction auction);
	public List<Auction> getAllAuctions();
	public boolean auctionExpiry();
	public void notifyBidders();
	public List<Bid> getTopBids();
}
