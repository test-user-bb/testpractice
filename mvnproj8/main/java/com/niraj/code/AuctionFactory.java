package com.niraj.code;

import com.niraj.code.auction.AuctionManager;
import com.niraj.code.auction.AuctionManagerImpl;
import com.niraj.code.auction.AuctionProcessor;
import com.niraj.code.auction.AuctionProcessorImpl;
import com.niraj.code.bid.BidManager;
import com.niraj.code.bid.BidManagerImpl;
import com.niraj.code.config.AuctionConfig;

public class AuctionFactory {

	public static AuctionManager getAuctionmanager(){
		return AuctionManagerImpl.getInstance();
	}
	
	public static AuctionProcessor getAuctionProcessor(){
		return AuctionProcessorImpl.getInstance();
	}
	
	public static AuctionConfig getAuctionConfig(){
		return AuctionConfig.loadConfig();
	}
	
	public static BidManager getBidManager(){
		return BidManagerImpl.getInstance();
	}
}
