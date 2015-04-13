package com.niraj.code.bid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.niraj.code.AuctionFactory;
import com.niraj.code.auction.Auction;


public class NewBidReceiveMultiQ {
	private static Logger log = Logger.getLogger(NewBidReceiveMultiQ.class.getCanonicalName());
    static NewBidReceiveMultiQ newBidReceiveMultiQ;
    
    static{
    	newBidReceiveMultiQ = new NewBidReceiveMultiQ(); 
    }

    static public NewBidReceiveMultiQ getInstance(){
    	return newBidReceiveMultiQ;
    }

	
	Map<Long,BlockingQueue<Bid>> newRecvQMap;
	long concurrentBidRecvQ;

	
	NewBidReceiveMultiQ(){
		newRecvQMap = new ConcurrentHashMap<Long, BlockingQueue<Bid>>();
		concurrentBidRecvQ=AuctionFactory.getAuctionConfig().getConcurrentBidRecvQ();
		for(long i = 0; i <concurrentBidRecvQ;++i){
			newRecvQMap.put(i, new LinkedBlockingQueue<Bid>());
		}
	}
    
	/**
	 * First Validate bid with the auction end time or aucion state.
	 * ie. If auction state is expired i.e. not a Valid then we dont't accept bid and return false.
	 * 
	 * If bid accepted then return true, and put the bid in the correct Q.
	 * 
	 * However, it may happen that there may be another bid in the state of processing which may not be 
	 * yet added to the system and there we may rejsct this bid.
	 * 
	 */

	public  boolean receieveNewBid(String itemId,long auctionId,String userLoginId, Double bidPrice ){

		Date currentDate = new Date();
		Boolean goodBid = AuctionFactory.getAuctionmanager().validateAuction(auctionId, currentDate,bidPrice);

		if(goodBid){
			long mapId = auctionId % concurrentBidRecvQ;
			BlockingQueue<Bid>  bidQueue = newRecvQMap.get(mapId);
			Bid newBid = new Bid(itemId,auctionId,userLoginId,bidPrice );
			bidQueue.add(newBid);
			return true;
		}
		return false;
	}

	public  List<Bid> pickNewBidList( ){
		List<Bid> bidList = new ArrayList<Bid>();
		for(BlockingQueue<Bid> q : newRecvQMap.values()){
			Bid bid = q.poll();
			log.info(String.format("Picked Up new bid from Q for item: %f",bid.getItemId()));
			if(bid != null){
				bidList.add(bid);
			}
		}
		return bidList;
	}
}
