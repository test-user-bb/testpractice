package com.niraj.code.config;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AuctionConfig {
 int auctionCreateThreadsCount;
 int expiryProcessAuctionThreadCount;

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
}
