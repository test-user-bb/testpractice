package com.niraj.code.bid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author niraj
 *Here we store the bid list for an auction and provide the necessary methods to manipulate that list.
 *The BidLists object will be created at the new auction and will be stored in the Auction. 
 *
 */
public class BidLists {
 
//Same object will be stored in the list and map and will be accessed. The highest bid object will be at the end.
Map<String,Bid> bidMap ;
List<Bid> bidList;

public BidLists(){
	this.bidMap= new HashMap<String, Bid>();
	this.bidList=new ArrayList<Bid>();
}


}
