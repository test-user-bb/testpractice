package com.niraj.code.bid;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niraj.code.AuctionFactory;
import com.niraj.code.auction.AuctionManager;
import com.niraj.code.config.AuctionConfig;



public class BidManagerImpl implements BidManager{

	private static Logger log = Logger.getLogger(BidManagerImpl.class.getCanonicalName());
	
	static  BidManager bidManager;
	NewBidReceiveMultiQ bidMultiQueue;
	ExecutorService bidAddService;
	int bidAddThreadCount;
	Gson gson;
	
	public BidManagerImpl(){
		bidAddThreadCount= AuctionConfig.loadConfig().getBidAddThreadCount();
		bidAddService = Executors.newFixedThreadPool(bidAddThreadCount);
		bidMultiQueue = new NewBidReceiveMultiQ();
		gson = new GsonBuilder()
		.disableHtmlEscaping()
		.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
		.setPrettyPrinting()
		.serializeNulls()
		.create();

	}

	static {
		bidManager =  new BidManagerImpl();
	}
	
	public static BidManager getInstance(){
		return bidManager;
	}
	
	//This function will get called through a timer task.
	// This will submit the tasks which will internally call the 
	// createAuction() function which will pick up the auction data from the Q and create 
	// corresponding auction task.
			
	
	public void startBidAddService() {
		List<Bid> bidList;

		while ((bidList=bidMultiQueue.pickNewBidList()) != null && !bidList.isEmpty() )
			for (Bid bid : bidList){
				log.info(String.format("Bid is retrieved from the Q and submitted: auction id:  %d , bid ites: %s", bid.getAuctionId(),bid.getItemId()));
				bidAddService.submit(new CreateBid(bid));
			}
	}
	

	class CreateBid implements Callable<Boolean> {
		Bid bid ;
		CreateBid(Bid bid){
			this.bid=bid;
		}
		public Boolean call() throws Exception {
			Boolean isbidAdded = AuctionFactory.getAuctionmanager().addNewBid(bid);
			if(!isbidAdded){
				log.error(String.format("Could not process the bid because bid price is less than top auction price: %s",gson.toJson(bid) ));
			}else{
				blcokUserAmount(bid.userLoginId);
				log.info(String.format("Bid is succesfully added to the auction:%s",gson.toJson(bid)));
			}
			return isbidAdded;

		}
		
		
		/**
		 * 
		 * @param userLoginId
		 * We assume that this process make some HTTP call to central user manager webservice and blcoks the amount
		 * for the user.
		 */
		private void blcokUserAmount(String userLoginId) {

		}
	}


}
