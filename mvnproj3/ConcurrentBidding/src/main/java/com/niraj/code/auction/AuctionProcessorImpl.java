package com.niraj.code.auction;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niraj.code.auction.AuctionManagerImpl.CreateAuction;
import com.niraj.code.bid.Bid;
import com.niraj.code.bid.BidLists;
import com.niraj.code.config.AuctionConfig;




public class AuctionProcessorImpl implements AuctionProcessor {

	private static Logger log = Logger.getLogger(AuctionProcessorImpl.class.getCanonicalName());

	/**
	 * Does not throw concurrent modifcation exception and proceeed with other concurrent operation,
	 * so we can iterate over and generate the list of auction any time and return in the form of json.
	 * 
	 */
	ConcurrentMap<Integer, Auction> auctionMap;
	ConcurrentLinkedDeque<Auction> auctiondq;
	ConcurrentNavigableMap<String,Auction> navMap ;//= new ConcurrentSkipListMap();
	ExecutorService auctionExpiryService;
	int expiryProcessAuctionThreadCount;
	

	
	int auctionId;
	static AuctionProcessor auctionProcessor;

	static{
		auctionProcessor = new AuctionProcessorImpl();
		}

	private AuctionProcessorImpl(){
		auctionMap = new ConcurrentHashMap<Integer, Auction>(); 
		auctiondq = new ConcurrentLinkedDeque<Auction>();
		navMap = new ConcurrentSkipListMap<String,Auction>();
		expiryProcessAuctionThreadCount = AuctionConfig.loadConfig().getExpiryProcessAuctionThreadCount();
		auctionExpiryService = Executors.newFixedThreadPool(expiryProcessAuctionThreadCount);
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
		//Need to update the logic here, if there we receive the multiple auction to trigger 
		//at exact same second.
		
		int keyAppend= auctionId %1000;  // 999 - after crossing the 9999 numbers only, there can be overalpp
										  // for the same second, which is almost impossible
										  // This means we are executing 3600*1000 auctions per second.
		
		auction.setNavigableKey(auction.generateNavigableKey()+keyAppend);
		navMap.putIfAbsent(auction.getNavigableKey(),auction);
		
		log.info(String.format("Created the new auction object with the auction id: %d", auctionId));

		return auctionId;

	}


	/**
	 * Using iterator on auctiondq we return the json text:
	 * 
	 */
	public String getAllAuctions() {

		Gson gson = new GsonBuilder()
		.disableHtmlEscaping()
		.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
		.setPrettyPrinting()
		.serializeNulls()
		.setExclusionStrategies(new SkipBidList())
		.create();

		Iterator< Auction > itr = auctiondq.descendingIterator();

		//StringBuilder sb = new StringBuilder();

		List<Auction> aList = new ArrayList<Auction>();
		while(itr.hasNext()) {
			//Auction auction = itr.next();
			//System.out.println(gson.toJson(auction));
			aList.add(itr.next());
		};

		return gson.toJson(aList);
	}
	/**
	 * Using iterator on auctiondq we return the json text:
	 * 
	 */
	public int getAllAuctionsCount() {
		Iterator< Auction > itr = auctiondq.descendingIterator();
		int count=0;
		while(itr.hasNext()) {
			++count;
			itr.next();
		}
		return count;
	}



	public boolean auctionExpiry(int auctionId) {
		
		return false;
	}


	public void notifyBidders() {
		// TODO Auto-generated method stub

	}


	public List<Bid> getTopBids(int auctionId) {
		// TODO Auto-generated method stub
		return null;
	}

	class SkipBidList implements ExclusionStrategy {

		public boolean shouldSkipClass(Class<?> arg0) {
			return false;
		}

		public boolean shouldSkipField(FieldAttributes f) {
			return (f.getDeclaringClass() == Auction.class && f.getName().equals("bidList"));
		}

	}
	
	class ProcessAuctionEnd implements Callable<Object>{

		private int id;
		
		public ProcessAuctionEnd(int id){
			this.id=id;
		}
		public Object call() throws Exception {
			// TODO Auto-generated method stub
			log.info("My id is:"+id);
			return null;
		}
		
	}

	

	public void startAuctionExpiryService() {
		for(int i = 0; i < expiryProcessAuctionThreadCount; ++i){
			auctionExpiryService.submit(new ExpireAuction(i));
		}

		try {
			auctionExpiryService.awaitTermination(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		
	}

	public void auctionExpiry() {
		// TODO Auto-generated method stub
		
	}
	
	public class ExpireAuction implements Callable<String> {
		int threadNo;
		ExpireAuction(int i){
			threadNo=i;
		}
		public String call() throws Exception {
			log.info(String.format("Running the expiry aution thread No: %s",threadNo));
			auctionExpiry();
			return null;
		}
	}


}
