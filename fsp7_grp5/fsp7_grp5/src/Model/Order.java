package Model;
/**
Represents an Order at our Bistro
@author Liang Jing
@version 1.0
@since 2019-04-18
*/
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable{
	/**
	* The list of individual order items for the Order
	*/
	ArrayList<String> orderItems = new ArrayList<String>();
	/**
	* The ID of the Staff that created the order
	*/
	private int staffID;
	/**
	* The ID of the Table the Order is from
	*/
	private int tableID;
	
	public Order() {
		
	}
	/**
	* Set the Table ID of this Order.
	* @param tableID This Order's table ID.  
	*/
	public void setTableID(int tableID) {
		this.tableID=tableID;
	}
	/**
	* Get the Table ID of this Order.
	* @return tableID This Order's table ID.  
	*/
	public int getTableID() {
		return tableID;
	}
	/**
	* Set the Staff ID of this Order.
	* @param staffID This Order's Staff ID.  
	*/
	public void setStaffID(int staffID) {
		this.staffID=staffID;
	}
	/**
	* Get the Staff ID of this Order.
	* @return staffID This Order's Staff ID.  
	*/
	public int getStaffID() {
		return staffID;
	}
	/**
	* Set the individual order items of this Order.
	* @param itemID individual order item ID 
	*/
	public void setOrderItems(String itemID) {
		orderItems.add(itemID);
	}
	/**
	* Set the individual order items of this Order.
	* @param itemID individual order item ID 
	*/
	public void addOrderItems(String itemID) {
		orderItems.add(itemID);
	}
	/**
	* Get the ArrayList of individual order items of this Order.
	* @return orderItems This Order's ArrayList of individual order items.  
	*/
	public ArrayList<String> getOrderItems() {
		return orderItems;
	}
}
