package com.niraj.code.auction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentLinkedDeque;




import com.niraj.code.bid.Bid;
import com.niraj.code.bid.BidLists;




public class AuctionProcessorImpl implements AuctionProcessor {

	/**
	 * Does not throw concurrent modifcation exception and proceeed with other concurrent operation,
	 * so we can iterate over and generate the list of auction any time and return in the form of json.
	 * 
	 */
	ConcurrentMap<Integer, Auction> auctionMap;
	ConcurrentLinkedDeque<Auction> auctiondq;
	
	
	
	List<Auction> auctionList;
    int auctionId;
	
	static AuctionProcessor auctionProcessor;
	
	
	static{
		auctionProcessor = new AuctionProcessorImpl();
	}
	
	private AuctionProcessorImpl(){
		auctionMap = new ConcurrentHashMap<Integer, Auction>(); 
		auctiondq = new ConcurrentLinkedDeque<Auction>();
	}

	/**
	 * These singleton will be used by drivers and threads to access the object.
	 * 
	 */
	
	public static  AuctionProcessor getInstance(){
		return auctionProcessor;
	}
	
		/**
	 * Here we will create a new auction object and place it in the auction map
	 * The raw data for this object will be received from the Auction receive Q.
	 * 
	 * Here we assign a unique ID to each auction and shoudl return that.
	 */
	public int createAuction(Auction auction) {
		auction.setBidList(new BidLists());
		
		//Can be called from multiple threads so synchronizing on the singleton object.
		synchronized (auctionProcessor) {
			++auctionId;	
		}
		auction.setAuctionId(auctionId);
		auctionMap.put(auctionId, auction);
		auctiondq.add(auction);
		
		return auctionId;
		
	}


  /**
   * Using iterator on auctiondq we return the json text:
   * 
   */
	public List<Auction> getAllAuctions() {
		
		Iterator< Auction > itr = auctiondq.descendingIterator();
		
		 while(itr.hasNext()) {
			 Auction element = itr.next();
	         System.out.print(element + " ");
	      };
		 
		
		// TODO Auto-generated method stub
		return null;
	}


	public boolean auctionExpiry() {
		// TODO Auto-generated method stub
		return false;
	}


	public void notifyBidders() {
		// TODO Auto-generated method stub

	}


	public List<Bid> getTopBids() {
		// TODO Auto-generated method stub
		return null;
	}


}
