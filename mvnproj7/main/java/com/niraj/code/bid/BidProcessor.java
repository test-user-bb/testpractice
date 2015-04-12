package com.niraj.code.bid;

public interface BidProcessor {

	//Places the Bid in the bid List of that auction.
	// A bid is valid only if it is greater that prev bid, so will be stored in the Q or List.
	public void placeTheBid();
	
}
