package com.niraj.code.bid;

public class Bid  implements Comparable<Double> {
	long bidId;
	long auctionId;
	String userLoginId;
	Double bidPrice;
	String itemId;
	public int compareTo(Double newBidPrice) {
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;
	    
	    if(bidPrice == newBidPrice ){
	    	return EQUAL;
	    }else  if(bidPrice < newBidPrice){
	    	return BEFORE;
	    }else
	    	return AFTER;
	    
	}
	
}
