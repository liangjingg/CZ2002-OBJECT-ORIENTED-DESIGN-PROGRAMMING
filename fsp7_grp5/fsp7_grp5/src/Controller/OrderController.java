package Controller;
/**
Control Class for Orders
@author Liang Jing
@version 1.0
@since 2019-04-18
*/
import java.util.ArrayList;
import java.util.Scanner;

import Model.AlacarteMenu;
import Model.Order;
import Model.OrderTable;
import Model.PromotionalPackage;
import Model.StaffList;
import Model.TableSeats;

public class OrderController {
	/**
	* An OrderTable object
	*/
	static OrderTable ot= new OrderTable();
	/**
	* A StaffList object
	*/
	static StaffList s = new StaffList();
	/**
	* An Order object
	*/
	static Order o = new Order();
	/**
	* A MainMenuController object
	*/
	MainMenuController mmc = new MainMenuController();
	/**
	* A Scanner object
	*/
	Scanner scan = new Scanner(System.in);
	
	/**
	* Gets the OrderTable object
	* @return ot OrderTable object
	*/
	public static OrderTable getOt() {
		return ot;
	}
	
	/**
	* Creating the order by taking in tableSeats object to assign the order to the
	* current tableSeat object.
	* @param ts TableSeats object
	*/
	public void createOrder(TableSeats ts) { //choice 1
		int staffID;
		int tableID=0;
		System.out.println("Any reservation made? (true/false)");
		boolean reserved = scan.nextBoolean();
		if(reserved) {
			System.out.println("Enter customer's contact number:");
			String contactNumber = scan.next();
			tableID=checkReserved(ts,contactNumber);
			if(tableID==-1) {
				System.out.println("No reservation found.");
				return;
			}
		}else {
			int pax=validatePax();
			tableID= checkAvailSeats(pax,ts);
			if(tableID==-1) {
				System.out.println("Sorry, restaurant is full. Try again later.");
				return;
			}
		}
		staffID=validateStaff();
		if(staffID!=-1) {
			ot.createOrder(staffID, tableID);
		}else {
			return;
		}
		validateAddOrder(tableID);
	}
	/**
	* Print the order of the table ID that the user input if it is not occupied, error message will be shown
	* @param ts TableSeats object
	*/
	public void viewOrder(TableSeats ts) { //choice 2
		int tableID=validateTable();
		if(ts.isOccupied(tableID-1)) {
			printOrder(tableID);
		}else {
			System.out.println("You cannot view Table "+tableID+" order as it is not occupied.");
		}
	}
	/**
	* Add order to the table ID that the user input if it is not occupied, error message will be shown
	* @param ts TableSeats object
	*/
	public void addOrder(TableSeats ts) { //choice 3
		int tableID=validateTable();
		if(ts.isOccupied(tableID-1)) {
			validateAddOrder(tableID);
		}else {
			System.out.println("You cannot add order for Table "+tableID+"  as it is not occupied.");
		}
	}
	/**
	* Remove the order from the table ID that the user input if it is not occupied, error message will be shown
	* @param ts TableSeats object
	*/
	public void removeOrder(TableSeats ts) { //choice 4
		int tableID=validateTable();
		boolean removed=false;
		if(ts.isOccupied(tableID-1)) {
			do {
				printOrder(tableID);
				System.out.println("Enter the menu item id to remove:");
				String orderItem = scan.next();
				ArrayList<String> orderItemList=ot.getOrderItem(tableID);
				for(int i = 0; i < orderItemList.size(); i++) {
					if(orderItemList.get(i).equals(orderItem)) {
						orderItemList.remove(i);
						removed=true;
					}
				}
			}while(removed==false);
		}else {
			System.out.println("You cannot remove order item from Table "+tableID+"  as it is not occupied.");
		}
	}
	/**
	* Return the orders from the all the tables that are occupied
	* @param ts TableSeats object
	* @return details contains all the order details from each occupied table
	*/
	public String writeOrder(TableSeats ts) { //print to text
		String details="";
		for(int tableID=1;tableID<31;tableID++) {
			if(ts.isOccupied(tableID-1)) {
				details+="TableID: "+tableID+" | ";
				o=ot.getOrder(tableID);//order object
				ArrayList<String> orderItems = o.getOrderItems();
				for (int i = 0; i < orderItems.size(); i++) {
					if(i!=0) {
						details+=" , ";
					}
					details+="Order Item ID"+orderItems.get(i)+" , ";
				}
			}
		}
		return details;
	}
	/**
	* Print the individual order items from the table id given
	* @param tableID Table ID of the particular order
	*/
	public void printOrder(int tableID) { //**SHOW THE DESCRIPTION OF ORDER ITEM INSTEAD**//
		o=ot.getOrder(tableID);//order object
		ArrayList<String> orderItems = o.getOrderItems();
		String leftAlignFormat = "| %-8s | %-19s | %n";

		System.out.format("+----------+---------------------+%n");
		System.out.format("| Table ID | Order Item ID       |%n");
		System.out.format("+----------+---------------------+%n");
		for (int i = 0; i < orderItems.size(); i++) {
			System.out.format(leftAlignFormat, tableID,orderItems.get(i));
		}
		System.out.format("+----------+---------------------+%n");
	}
	/**
	* Return the empty table ID for the customers to be seated at
	* @param pax the number of people to be seated
	* @param ts TableSeats object
	* @return tableID if there is empty table, -1 if all the tables are occupied
	*/
	public int checkAvailSeats(int pax,TableSeats ts) {
		for(int i=0;i<30;i++) {
			if(!ts.isOccupied(i)&&!ts.isReserved(i)&&ts.getCapacity(i)>=pax) {
				System.out.println("Please be seated at Table "+(i+1));
				ts.assignseating(i);
				return (i+1);
			}
		}
		return -1;
	}
	/**
	* Return the table ID reserved for that customer
	* @param ts TableSeats object
	* @param contactNumber the customer's contact number that has made a reservation 
	* @return tableID if there is reservation, -1 if reservation is not found
	*/
	public int checkReserved(TableSeats ts,String contactNumber) {
		for(int i=0;i<30;i++) {
			if(ts.isReserved(i)&&ts.getCust(i).equals(contactNumber)) {
				System.out.println("Please be seated at Table "+(i+1));
				ts.removeReservation(i);
				ts.assignseating(i);
				return (i+1);
			}
		}
		return -1;
	}
	/**
	* Validate if adding of order is possible and if it is, allow them to do so
	* @param tableID the ID of the Table object
	*/
	public void validateAddOrder(int tableID) {
		boolean validOrder=false;
		ArrayList <AlacarteMenu> mm=mmc.getAlacarteMenu();
		ArrayList <PromotionalPackage> pp=mmc.getPromotionalPackage();
		mmc.viewMenu();
		mmc.viewPromoPackage();
		System.out.println("Please enter the Order Item:");
		String orderItem = scan.next();
		do{
			validOrder=false;
			for (int i = 0; i < mm.size(); i++) {
				if(mm.get(i).getMenuName().equals(orderItem)) {
					ot.addOrder(tableID, orderItem);
					validOrder=true;
				}
			}for (int i = 0; i < pp.size(); i++) {
				if(pp.get(i).getPackageID().equals(orderItem)) {
					ot.addOrder(tableID, orderItem);
					i=pp.size();
					validOrder=true;
				}
			}
			if(!validOrder) {
				System.out.println("Invalid Order Item");
			}
			System.out.println("Please enter the Order Item (-1 to exit):");
			orderItem = scan.next();
		}while(!orderItem.equals("-1"));
	}
	/**
	* Validate if the staff ID entered exist or not
	*/
	public int validateStaff() {
		int staffID;
		do {
			System.out.println("Please enter your staff ID:");
			staffID = scan.nextInt();
			for(int i=0;i<20;i++) {
				if(s.getStaffID(i)==staffID){
					return staffID; //if the input match the staffID record
				}
			}
			System.out.println("Incorrect input, please check your staff ID. Enter -1 to quit");
		}while(staffID!=-1);
		return staffID;
	}
	/**
	* Validate if the Table ID entered exist or not
	*/
	public int validateTable() {
		int tableID;
		do{
			System.out.println("Please enter the table ID:");
			tableID = scan.nextInt();
			if(tableID<1 || tableID>30) {
				System.out.println("Please enter a valid table ID(1-30)");
			}
		}while(tableID<1 || tableID>30);
		return tableID;
	}
	/**
	* Validate if the number of pax entered is valid or not
	*/
	public int validatePax() {
		int pax;
		do {
			System.out.println("Please enter the number of pax:");
			pax = scan.nextInt();
			if(pax<1 || pax>10) {
				System.out.println("Sorry, we can only hold 1-10 people per table.");
			}
		}while(pax<1 || pax>10);
		return pax;
	}
}
