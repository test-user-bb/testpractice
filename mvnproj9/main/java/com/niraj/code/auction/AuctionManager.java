package com.niraj.code.auction;

import java.util.Date;

import com.niraj.code.bid.Bid;
import com.niraj.code.item.Item;
import com.niraj.code.user.User;


public interface AuctionManager {
	public void receiveNewAuctionfromUser(User user, Item item, Double askPrice,Date startDate,Date endDate);
	public void  createAuction(Auction auction);
	public String getAllAuctions();
	public int getAllAuctionsCount();
	public void startAuctionAddService();
	public void startAuctionExpiryService();
	public String getTopNBids(String itemId);
	public long getTotalBids(String itemId);
	public boolean validateAuction(long auctionId, Date bidDate, Double bidPrice) ;
	public boolean addNewBid(Bid bid);
	public void startBidAddService() ;
	public void shutDownBiddingEngine() ;
	public  boolean receieveNewBid(String itemId,long auctionId,String userLoginId, Double bidPrice );
	
}
