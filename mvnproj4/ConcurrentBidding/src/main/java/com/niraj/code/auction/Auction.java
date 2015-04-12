package com.niraj.code.auction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.niraj.code.bid.BidLists;
import com.niraj.code.item.Item;
import com.niraj.code.user.User;



public class Auction {
	AuctionState state;
	long auctionId;
	BidLists bidList;
	User user;
	Item item;
	Double origianlAskPrice;
	//Before accepting each bid, it will be compared against the auctionEndTime and auctionStartTime and also 
	// with the last N big bids and if everything passes it will be accpted.
	
	Date auctionStartTime;
	Date auctionEndTime;
	
	String navigableKey;
	
	
	//Auction end time and start time are configurable and user provided parameters
	/*Auction(User user, Item item, Double askPrice,BidLists bidList){
		this.user=user;
		this.item=item;
		this.origianlAskPrice=askPrice;
		this.bidList=bidList;
		this.state=AuctionState.UNPICKED;

	}*/
	
	Auction(User user, Item item, Double askPrice,Date auctionStartTime,Date auctionEndTime){
		this.user=user;
		this.item=item;
		this.origianlAskPrice=askPrice;
		this.auctionStartTime=auctionStartTime;
		this.auctionEndTime=auctionEndTime;
		this.state=AuctionState.UNPICKED;
		
	}

	/**
	 * @return the bidList
	 */
	public BidLists getBidList() {
		return bidList;
	}

	/**
	 * @param bidList the bidList to set
	 */
	public void setBidList(BidLists bidList) {
		this.bidList = bidList;
	}
	public void setAuctionId(int id) {
		this.auctionId = id;
	}
	
	public String generateNavigableKey(){
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(auctionEndTime);
	}

	/**
	 * @return the navigableKey
	 */
	public String getNavigableKey() {
		return navigableKey;
	}

	/**
	 * @param navigableKey the navigableKey to set
	 */
	public void setNavigableKey(String navigableKey) {
		this.navigableKey = navigableKey;
	}

	/**
	 * @return the auctionId
	 */
	public long getAuctionId() {
		return auctionId;
	}
}
