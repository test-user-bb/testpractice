package com.niraj.code.bid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import com.niraj.code.auction.Auction;

/**
 * 
 * @author niraj
 *Here we store the bid list for an auction and provide the necessary methods to manipulate that list.
 *The BidLists object will be created at the new auction and will be stored in the Auction. 
 *
 */
public class BidLists {
 
//Same object will be stored in the list and map and will be accessed. The highest bid object will be at the end.

ConcurrentMap<String,Bid> bidMap;  // Storing all the bids with the user login ID as key
ConcurrentLinkedQueue<Bid> bidList;  // Storing all the top N auctions configured in the json

public BidLists(){
	this.bidMap= new ConcurrentHashMap<String,Bid>();
	this.bidList=new ConcurrentLinkedQueue<Bid>();
}


}
