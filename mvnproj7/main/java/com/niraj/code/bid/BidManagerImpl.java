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
import com.niraj.code.config.AuctionConfig;



public class BidManagerImpl {

	private static Logger log = Logger.getLogger(BidManagerImpl.class.getCanonicalName());

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

	//This function will get called through a timer task.
	// This will submit the tasks which will internally call the 
	// createAuction() function which will pick up the auction data from the Q and create 
	// corresponding auction task.
			
	
	public void startBidAddService() {
		List<Bid> bidList;

		while ((bidList=bidMultiQueue.pickNewBidList()) != null && !bidList.isEmpty() )
			for (Bid bid : bidList){
				bidAddService.submit(new CreateBid(bid));
			}
	}

	class CreateBid implements Callable<Boolean> {
		Bid bid ;
		CreateBid(Bid bid){
			this.bid=bid;
		}
		public Boolean call() throws Exception {
			Boolean b = AuctionFactory.getAuctionmanager().addNewBid(bid);
			if(!b){
				log.error(String.format("Could not process the bid because bid price is less than top auction price: %s",gson.toJson(bid) ));
			}else{
				blcokUserAmount(bid.userLoginId);
			}
			return b;

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
