package com.niraj.code.bid;

import java.util.List;

public interface BidManager {
	/**
	 * Receives a new Bid and places it into the Auction Q.
	 */
	public void receiveNewBid();  
	public void validateBid();
	public List<Bid> getTopBids();
}
