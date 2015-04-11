package com.niraj.code.auction;

import com.niraj.code.item.Item;
import com.niraj.code.user.User;


public interface AuctionManager {
 public void receiveNewAuctionfromUser(User user, Item item, Double askPrice);
 public void  createAuction();
}
