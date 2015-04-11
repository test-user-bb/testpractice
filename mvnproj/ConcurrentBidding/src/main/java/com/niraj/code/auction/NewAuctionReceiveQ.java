package com.niraj.code.auction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.niraj.code.item.Item;
import com.niraj.code.user.User;

public class NewAuctionReceiveQ {

	static Logger log = Logger.getLogger(NewAuctionReceiveQ.class.getCanonicalName());

	BlockingQueue<RawAuctionData> newAuctionQ;
	class RawAuctionData{
		User user;
		Item item;
		Double askPrice;

		RawAuctionData(User user, Item item, Double askPrice){
			this.user=user;
			this.item=item;
			this.askPrice=askPrice;
		}
	}

	NewAuctionReceiveQ(){
		newAuctionQ = new LinkedBlockingQueue<RawAuctionData>();	
	}


	public  void receieveNewAuction(User user, Item item, Double askPrice  ){
		newAuctionQ.add(new RawAuctionData(user,item,askPrice));
	}

	public  Auction pickNewAuction( ){
		try {
			RawAuctionData a = newAuctionQ.take();
			return new Auction (a.user,a.item,a.askPrice);
			
		} catch (InterruptedException e) {

			log.error(e.getMessage(),e);
			return null;
		}
		
	}

}
