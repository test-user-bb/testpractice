package com.niraj.code.bid;

public class Bid  implements Comparable<Double> {
	long bidId;
	long auctionId;
	String userLoginId;
	Double bidPrice;
	String itemId;
	
	public Bid(String itemId,long auctionId,String userLoginId, Double bidPrice ){
		this.auctionId=auctionId;
		this.itemId=itemId;
		this.userLoginId=userLoginId;
		this.bidPrice=bidPrice;
	}
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
	/**
	 * @return the bidId
	 */
	public long getBidId() {
		return bidId;
	}
	/**
	 * @param bidId the bidId to set
	 */
	public void setBidId(long bidId) {
		this.bidId = bidId;
	}
	/**
	 * @return the auctionId
	 */
	public long getAuctionId() {
		return auctionId;
	}
	/**
	 * @param auctionId the auctionId to set
	 */
	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}
	/**
	 * @return the userLoginId
	 */
	public String getUserLoginId() {
		return userLoginId;
	}
	/**
	 * @param userLoginId the userLoginId to set
	 */
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	/**
	 * @return the bidPrice
	 */
	public Double getBidPrice() {
		return bidPrice;
	}
	/**
	 * @param bidPrice the bidPrice to set
	 */
	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
}
