package com.niraj.code.auction;

import com.niraj.code.bid.BidLists;
import com.niraj.code.item.Item;
import com.niraj.code.user.User;



public class Auction {

	int id;
	BidLists bidList;
	User user;
	Item item;
	Double origianlAskPrice;


	Auction(User user, Item item, Double askPrice,BidLists bidList){
		this.user=user;
		this.item=item;
		this.origianlAskPrice=askPrice;
		this.bidList=bidList;

	}
	
	Auction(User user, Item item, Double askPrice){
		this.user=user;
		this.item=item;
		this.origianlAskPrice=askPrice;
		this.bidList=bidList;

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
		this.id = id;
	}
}
