package com.niraj.code.auction;

import java.util.Date;

import com.niraj.code.item.Item;
import com.niraj.code.user.User;


public interface AuctionManager {
 public void receiveNewAuctionfromUser(User user, Item item, Double askPrice,Date startDate,Date endDate);
 public void  createAuction();
 public String getAllAuctions();
 public int getAllAuctionsCount();
 public void startAuctionAddService();
 public void startAuctionExpiryService();
}
