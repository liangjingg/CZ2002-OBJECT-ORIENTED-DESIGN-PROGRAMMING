package Model;
/**
Represents the List of Order at our Bistro
@author Liang Jing
@version 1.0
@since 2019-04-18
*/
import java.util.ArrayList;

public class OrderTable {
	/**
	* The maximum number of current orders is equal to maximum amount of tables
	*/
	private int max=30;
	/**
	* The arraylist of the Orders
	*/
	private Order[] orderList  = new Order[max];
	/**
	* Creates a new OrderTable which creates the arraylist of Orders
	*/
	public OrderTable() {
		for(int i=0;i<max;i++) {
			orderList[i]= new Order();
		}
	}
	/**
	* Set the staff ID and table ID of this order in OrderList.
	* @param staffID Order's new staff ID.
	* @param tableID Order's new table ID.  
	*/
	//1st ask for staff id & table id
	public boolean createOrder(int staffID, int tableID) {
		orderList[tableID-1].setStaffID(staffID);
		orderList[tableID-1].setTableID(tableID);
		return true;
	}
	
	/**
	* Add the individual order item ID to this order in OrderList.
	* @param tableID Order's table ID.
	* @param itemID Order's new individual order item ID.  
	*/
	//continue input the tableID and ask for each itemID
	public void addOrder(int tableID,String itemID) {
		orderList[tableID-1].setOrderItems(itemID);
	}
	public void removeOrder(int tableID,String itemID) {
		orderList[tableID-1].setOrderItems(itemID);
	}
	/**
	* Print the individual order item IDs from the table ID
	* @param tableID Order's table ID.  
	*/
	public void printOrder(int tableID) { //**nicer format 
		ArrayList<String> orderItems = new ArrayList<String>();
		orderItems=orderList[tableID-1].getOrderItems();
		for(String details:orderItems) {   
			System.out.println(details);
		}  
	}
	/**
	* Get all the individual order item IDs from the table ID
	* @param tableID Order's table ID.  
	*/
	public ArrayList<String> getOrderItem(int tableID){
		return orderList[tableID-1].getOrderItems();
	}
	/**
	* Get the arraylist of order item IDs from the table ID
	* @param tableID Order's table ID.  
	*/
	public Order getOrder(int tableID) {
		return orderList[tableID-1];
	}
	/**
	* Get the Order from the table ID and unassign seating for the table ID
	* @param tableID Order's table ID.  
	* @param ts TableSeats object
	*/
	//for invoice, to get Order object
	public Order getInvoice(int tableID,TableSeats ts) {
		ts.unassignseating(tableID-1);
		return orderList[tableID-1];
	}
}
