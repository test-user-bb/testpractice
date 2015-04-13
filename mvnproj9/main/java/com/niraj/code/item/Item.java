package com.niraj.code.item;

public class Item {
String  itemId;
String itemName;
String itemDescription;
//String itemPrice;
//String itemQuantityforAuction;  // Can be used or not
public Item(String id, String itemName,String itemDescription ){
	this.itemId=id;
	this.itemName=itemName;
	this.itemDescription=itemDescription;
}
/**
 * @return the itemId
 */
public String getItemId() {
	return itemId;
}
/**
 * @param itemId the itemId to set
 */
public void setItemId(String itemId) {
	this.itemId = itemId;
}
/**
 * @return the itemName
 */
public String getItemName() {
	return itemName;
}
/**
 * @param itemName the itemName to set
 */
public void setItemName(String itemName) {
	this.itemName = itemName;
}
/**
 * @return the itemDescription
 */
public String getItemDescription() {
	return itemDescription;
}
/**
 * @param itemDescription the itemDescription to set
 */
public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
}

}
