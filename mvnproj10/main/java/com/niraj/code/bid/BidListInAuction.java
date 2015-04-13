package com.niraj.code.bid;


import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niraj.code.AuctionFactory;



/**
 * 
 * @author niraj
 *Here we store the bid list for an auction and provide the necessary methods to manipulate that list.
 *The BidLists object will be created at the new auction and will be stored in the Auction. 
 *
 */
public class BidListInAuction {

	//Same object will be stored in the list and map and will be accessed. The highest bid object will be at the end.
	long bidId;
	ConcurrentMap<Long,Bid> bidsMap;  // Storing all the bids with the user ID as key, so if same user submits bid again
	// it will be replaced with previous bid
	Bid[] topBidList;  // Storing all the top N bids as per the config, bids will be compared by bid price
	// It will be in the descending order.
	Gson gson;

	public BidListInAuction(){
		this.bidsMap= new ConcurrentHashMap<Long,Bid>();
		this.topBidList=new Bid[AuctionFactory.getAuctionConfig().getMaxTopBids()];
		gson = new GsonBuilder()
		.disableHtmlEscaping()
		.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
		.setPrettyPrinting()
		.serializeNulls()
		.create();

	}

	public Bid getWinningBid(){
		return topBidList[topBidList.length-1];
	}

	/**
	 * one of the criteria for Bid validity is bid price should be greater than the top bid. 
	 * 
	 */
	public boolean checkIsvalidBid(Bid bid){
		return bid.bidPrice > topBidList[topBidList.length-1].bidPrice;
	}

	/**
	 * Bids will be only put into the Queue if the auction is valid, which will be checked by the
	 * auction end date-time or state of the aucton.
	 * A user can enter multiple bids and each one will be treated separately.
	 * 
	 */

	public boolean addNewBidandSort(Bid bid){
		long bidIdtmp=0;
		
		synchronized (this) {
			++bidId;
			bidIdtmp = bidId;
		}
		
		bidsMap.put(bidIdtmp, bid);
		bid.setBidId(bidIdtmp);
		
		//Check the validity of the bid, by bid price and then putting into smallest place 
		// and sorting back the arry.
		synchronized (topBidList) {
			if(bid.bidPrice < topBidList[topBidList.length-1].bidPrice){
				return false;
			}
			topBidList[0] = bid;
			Arrays.sort(topBidList);
		}
		return true;
	}

	public String getTopNBids(){
		return gson.toJson(topBidList);
	}

	public void expireAllBids() {

		for( Bid bid : bidsMap.values()){
			unBlockUserAmount(bid);
		}

	}
	/**
	 * This is suppose to call the some central webservice which manages user accounts.
	 * 
	 */
	private boolean unBlockUserAmount(Bid bid){
		return true;
	}

	public long getBidCount() {
		return bidsMap.size();
	}

}
