package com.niraj.code.auction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
	ConcurrentMap<Long, Auction> auctionMapById;
	//ConcurrentLinkedDeque<Auction> auctiondq;
	ConcurrentNavigableMap<String,Auction> navigableAuctionMap ;

	ExecutorService auctionExpiryService;
	int expiryProcessAuctionThreadCount;

	int auctionId;
	static AuctionProcessor auctionProcessor;

	static{
		auctionProcessor = new AuctionProcessorImpl();
	}

	private AuctionProcessorImpl(){
		auctionMapById = new ConcurrentHashMap<Long, Auction>(); 
		//auctiondq = new ConcurrentLinkedDeque<Auction>();
		navigableAuctionMap = new ConcurrentSkipListMap<String,Auction>();
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
	public long createAuction(Auction auction) {
		auction.setBidList(new BidLists());

		//Can be called from multiple threads so synchronizing on the singleton object and saving 
		// the value immedaitley since it can change by other thread after the block
		synchronized (auctionProcessor) {
			++auctionId;
			auction.setAuctionId(auctionId);
		}
		auctionMapById.put(auction.getAuctionId(), auction);
		//auctiondq.add(auction);
		//Need to update the logic here, if there we receive the multiple auction to trigger 
		//at exact same second.

		long keyAppend= auction.getAuctionId()  %10000;  // 999 - after crossing the 999 numbers only, there can be overalpp
		// for the same second, which is almost impossible
		// This means we are executing 3600*1000 auctions per second.

		auction.setNavigableKey(auction.generateNavigableKey()+keyAppend);
		navigableAuctionMap.putIfAbsent(auction.getNavigableKey(),auction);

		log.info(String.format("Thread Id: %s Created the new auction object with the auction id: %d and navigable key %s", Thread.currentThread().getId()+"",auction.getAuctionId(),auction.getNavigableKey()));

		return auction.getAuctionId();

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
		List<Auction> aList = new ArrayList<Auction>();
		for(Auction auction :auctionMapById.values() ){
			aList.add(auction);	
		}
		return gson.toJson(aList);
	}

	/**
	 * Using iterator on auctiondq we return the json text:
	 * 
	 */
	public int getAllAuctionsCount() {
		return auctionMapById.size();
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


	class ProcessAuctionEnd implements Callable<Object>{

		private int id;

		public ProcessAuctionEnd(int id){
			this.id=id;
		}
		public Object call() throws Exception {
			log.info("My id is:"+id);
			return null;
		}

	}


	/**
	 * Here first we invoke the headmap with current time stamp+9999 as key and get 
	 * all the auction objects which are smaller than i.e from the time which is lapsed
	 * and then we navigate through the map and pass the object to the auction expiery service.
	 * 
	 * After that we again navigate through all the objects and remove them from all the three maps.
	 * 
	 */

	public void startAuctionExpiryService() {

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDateTime=df.format(new Date());
		currentDateTime+="9999";
		Map<String,Auction> expirymap=navigableAuctionMap.headMap(currentDateTime,true);

		for(Auction auction : expirymap.values()){
			auctionExpiryService.submit(new ExpireAuction(auction));
		}

		try {
			auctionExpiryService.awaitTermination(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for(Auction auction : expirymap.values()){
			expirymap.remove(auction.getNavigableKey());
			auctionMapById.remove(auction.getAuctionId());
			log.info(String.format("Auction id %d : Removed the auction from the auction system",auction.getAuctionId()));
		}

	}
	//This receives the aution object from the map, first it changes the state to the 
	// processing and then to the processed.
	//Later all the processed objects from the head map will be removed.

	public void auctionExpiry(Auction auction) {
		auction.state=AuctionState.PROCESSING;
		log.info(String.format("Auction id %d :changed state to processing",auction.getAuctionId()));
		auction.state=AuctionState.PROCESSED;
		log.info(String.format("Auction id %d :changed state to processed",auction.getAuctionId()));
	}

	public class ExpireAuction implements Callable<String> {
		Auction auction;
		ExpireAuction(Auction auction){
			this.auction =auction;

		}
		public String call() throws Exception {
			log.info(String.format("Thread Id: %s Starting the  auction expiry thread for the auction id: %d and navigable key %s", Thread.currentThread().getId()+"",auction.getAuctionId(),auction.getNavigableKey()));
			auctionExpiry(auction);
			return null;
		}
	}

	class SkipBidList implements ExclusionStrategy {

		public boolean shouldSkipClass(Class<?> arg0) {
			return false;
		}

		public boolean shouldSkipField(FieldAttributes f) {
			return (f.getDeclaringClass() == Auction.class && f.getName().equals("bidList"));
		}

	}


}
