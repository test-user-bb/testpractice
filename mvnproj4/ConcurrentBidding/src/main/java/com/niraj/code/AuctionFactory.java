package com.niraj.code;

import com.niraj.code.auction.AuctionManager;
import com.niraj.code.auction.AuctionManagerImpl;
import com.niraj.code.auction.AuctionProcessor;
import com.niraj.code.auction.AuctionProcessorImpl;

public class AuctionFactory {

	public static AuctionManager getAuctionmanager(){
		return AuctionManagerImpl.getInstance();
	}
	
	public static AuctionProcessor getAuctionProcessor(){
		return AuctionProcessorImpl.getInstance();
	}
}
