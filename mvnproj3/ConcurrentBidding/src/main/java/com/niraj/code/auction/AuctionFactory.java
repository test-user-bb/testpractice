package com.niraj.code.auction;

public class AuctionFactory {

	public static AuctionManager getAuctionmanager(){
		return AuctionManagerImpl.getInstance();
	}
	
	public static AuctionProcessor getAuctionProcessor(){
		return AuctionProcessorImpl.getInstance();
	}
}
