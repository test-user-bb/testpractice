package com.niraj.code.auction;



public interface AuctionProcessor {
	public long createAuction(Auction auction);
	public String getAllAuctions();
	public void auctionExpiry(Auction auction);
	public void notifyBidders();
	public String getTopBids(String itemId);
	public int getAllAuctionsCount() ;
	public void startAuctionExpiryService() ;
}
