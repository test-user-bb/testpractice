package com.niraj.code.config;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AuctionConfig {
 int auctionCreateThreadsCount;
 int expiryProcessAuctionThreadCount;
 int maxTopBids;
 int concurrentBidRecvQ;
 int bidAddThreadCount;
 int auctionAddServiceFreq;
 int bidAddServiceFreq;
 int auctionExpiryServiceFreq;

 public static  AuctionConfig loadConfig(){
	 String result="";
	 	try {
		    result = IOUtils.toString(AuctionConfig.class.getClassLoader().getResourceAsStream("AunctionConfig.json"));
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	 	Gson gson = new GsonBuilder()
		.disableHtmlEscaping()
		.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
		.setPrettyPrinting()
		.serializeNulls()
		.create();
	 	return gson.fromJson(result, AuctionConfig.class);
	 
 }

/**
 * @return the auctionCreateThreadsCount
 */
public int getAuctionCreateThreadsCount() {
	return auctionCreateThreadsCount;
}

/**
 * @param auctionCreateThreadsCount the auctionCreateThreadsCount to set
 */
public void setAuctionCreateThreadsCount(int auctionCreateThreadsCount) {
	this.auctionCreateThreadsCount = auctionCreateThreadsCount;
}

/**
 * @return the expiryProcessAuctionThreadCount
 */
public int getExpiryProcessAuctionThreadCount() {
	return expiryProcessAuctionThreadCount;
}

/**
 * @param expiryProcessAuctionThreadCount the expiryProcessAuctionThreadCount to set
 */
public void setExpiryProcessAuctionThreadCount(
		int expiryProcessAuctionThreadCount) {
	this.expiryProcessAuctionThreadCount = expiryProcessAuctionThreadCount;
}

/**
 * @return the maxTopBids
 */
public int getMaxTopBids() {
	return maxTopBids;
}

/**
 * @param maxTopBids the maxTopBids to set
 */
public void setMaxTopBids(int maxTopBids) {
	this.maxTopBids = maxTopBids;
}

/**
 * @return the concurrentBidRecvQ
 */
public int getConcurrentBidRecvQ() {
	return concurrentBidRecvQ;
}

/**
 * @param concurrentBidRecvQ the concurrentBidRecvQ to set
 */
public void setConcurrentBidRecvQ(int concurrentBidRecvQ) {
	this.concurrentBidRecvQ = concurrentBidRecvQ;
}

/**
 * @return the bidAddThreadCount
 */
public int getBidAddThreadCount() {
	return bidAddThreadCount;
}

/**
 * @param bidAddThreadCount the bidAddThreadCount to set
 */
public void setBidAddThreadCount(int bidAddThreadCount) {
	this.bidAddThreadCount = bidAddThreadCount;
}

/**
 * @return the auctionAddServiceFreq
 */
public int getAuctionAddServiceFreq() {
	return auctionAddServiceFreq;
}

/**
 * @param auctionAddServiceFreq the auctionAddServiceFreq to set
 */
public void setAuctionAddServiceFreq(int auctionAddServiceFreq) {
	this.auctionAddServiceFreq = auctionAddServiceFreq;
}

/**
 * @return the bidAddServiceFreq
 */
public int getBidAddServiceFreq() {
	return bidAddServiceFreq;
}

/**
 * @param bidAddServiceFreq the bidAddServiceFreq to set
 */
public void setBidAddServiceFreq(int bidAddServiceFreq) {
	this.bidAddServiceFreq = bidAddServiceFreq;
}

/**
 * @return the auctionExpiryServiceFreq
 */
public int getAuctionExpiryServiceFreq() {
	return auctionExpiryServiceFreq;
}

/**
 * @param auctionExpiryServiceFreq the auctionExpiryServiceFreq to set
 */
public void setAuctionExpiryServiceFreq(int auctionExpiryServiceFreq) {
	this.auctionExpiryServiceFreq = auctionExpiryServiceFreq;
}
}
