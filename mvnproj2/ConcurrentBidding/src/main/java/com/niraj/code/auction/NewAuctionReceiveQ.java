package com.niraj.code.auction;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.niraj.code.item.Item;
import com.niraj.code.user.User;

public class NewAuctionReceiveQ {

	
	private static Logger log = Logger.getLogger(NewAuctionReceiveQ.class.getCanonicalName());

	BlockingQueue<RawAuctionData> newAuctionQ;
	
	NewAuctionReceiveQ(){
		newAuctionQ = new LinkedBlockingQueue<RawAuctionData>();	
	}


	public  void receieveNewAuction(User user, Item item, Double askPrice,Date auctionStartTime,Date auctionEndTime  ){
		newAuctionQ.add(new RawAuctionData(user,item,askPrice,auctionStartTime,auctionEndTime));
	}

	public  Auction pickNewAuction( ){
		try {
			RawAuctionData a = newAuctionQ.take();
			return new Auction (a.user,a.item,a.askPrice,a.auctionStartTime,a.auctionEndTime);
			
		} catch (InterruptedException e) {

			log.error(e.getMessage(),e);
			return null;
		}
		
	}
	
	class RawAuctionData{
		User user;
		Item item;
		Double askPrice;
		Date auctionStartTime;
		Date auctionEndTime;
		
		RawAuctionData(User user, Item item, Double askPrice,Date auctionStartTime,Date auctionEndTime){
			this.user=user;
			this.item=item;
			this.askPrice=askPrice;
			this.auctionStartTime=auctionStartTime;
			this.auctionEndTime=auctionEndTime;
		}
	}
	
}
