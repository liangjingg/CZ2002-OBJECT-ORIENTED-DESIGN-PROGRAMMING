package Model;
/**
Contains the list of Tables in our Bistro.
@author Liang Jing/Karen
@version 1.0
@since 2019-04-18
*/
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class TableSeats implements Serializable {
	/**
	* The maximum number of Tables that will be generated
	*/
	private int max=30;
	/**
	* The ArrayList of Tables
	*/
	private Table[] tableList  = new Table[max];
	String rename = "";
//	private static final long serialVersionUID = 2558551049566880370L;
	
	/**
	* Creates a new List of Tables
	*/
	public TableSeats() {
		createTableSeats();
	}
	/**
	* Initializing table list with new Tables, with their Table ID and 
	* Table capacity.
	*/
	public void createTableSeats() {
		//Manual creation of table
		tableList[0] = new Table(1,2);
		tableList[1] = new Table(2,2);
		tableList[2] = new Table(3,2);
		tableList[3] = new Table(4,2);
		tableList[4] = new Table(5,2);
		tableList[5] = new Table(6,2);
		tableList[6] = new Table(7,2);
		tableList[7] = new Table(8,2);
		tableList[8] = new Table(9,2);
		tableList[9] = new Table(10,2);
						
		tableList[10] = new Table(11,4);
		tableList[11] = new Table(12,4);
		tableList[12] = new Table(13,4);
		tableList[13] = new Table(14,4);
		tableList[14] = new Table(15,4);
		tableList[15] = new Table(16,4);
		tableList[16] = new Table(17,4);
		tableList[17] = new Table(18,4);
		tableList[18] = new Table(19,4);
		tableList[19] = new Table(20,4);
						
		tableList[20] = new Table(21,8);
		tableList[21] = new Table(22,8);
		tableList[22] = new Table(23,8);
		tableList[23] = new Table(24,8);
		tableList[24] = new Table(25,8);
						
		tableList[25] = new Table(26,10);
		tableList[26] = new Table(27,10);
		tableList[27] = new Table(28,10);
		tableList[28] = new Table(29,10);
		tableList[29] = new Table(30,10);
	}
	/**
	* Print all the Table details, Table ID and Table Capacity.
	*/
	//print all table, not yet have reservation details  
	public void printTableSeats() {
		for(int i=0; i<tableList.length; i++) {
			System.out.println("ID: " + tableList[i].getTableID() + " Capacity: " + tableList[i].getCapacity() + " Occupied: " + tableList[i].isOccupied() );
		}
	}
	/**
	* Gets the table occupancy status of this Table by its Table ID.
	* @param table_index TableList's array index. 
	* @return tableList[table_index].isOccupied() false if empty, true if occupied.
	*/
	//check occupied, true=occupied, false=empty
	public boolean isOccupied(int table_index) {
		return tableList[table_index].isOccupied();
	}
	/**
	* Gets the capacity of this TableList index.
	* @return tableList[table_index].getCapacity()
	*/
	//get capacity for that table index
	public int getCapacity(int table_index) {
		return tableList[table_index].getCapacity();
	}
	/**
	* Gets the capacity
	* @return t.getCapacity()
	*/
	// New get capacity
	public int getCapacity() {
		Table t = new Table();
		return t.getCapacity();
	}
	/**
	* Gets the capacity of this TableList index.
	*/
	//print available table 
	public void allAvail() {
		
		//String leftAlignFormat = "| %-9s | %-9s | %-9s |%n";
		String leftAlignFormat = "| %-9s | %-9s |%n";
		
		//System.out.format("| TableID | Seats   |%n");
		System.out.format("+-----------+-----------+%n");
		for(int i=0; i<tableList.length; i++) {
			if (tableList[i].isOccupied() == false) {
				System.out.format(leftAlignFormat, "Table "+tableList[i].getTableID(), tableList[i].getCapacity()+" seats");
				//System.out.print(tableList[i].isOccupied());
			}
		}
		System.out.format("+-----------+-----------+%n");
		System.out.println();                      
	}
	
	/**
	* Assign the Table to an order, hence needing to change the occupancy status of the Table to true
	* @param table_index the Table's index in TableList
	*/
	//assign table 
	public void assignseating(int table_index) {
		tableList[table_index].assignTable();
	}
	/**
	* Unassign the Table from an order, hence needing to change the occupancy status of the Table to false
	* @param table_index the Table's index in TableList
	*/
	//unassign table 
	public void unassignseating(int table_index) {
		tableList[table_index].unassignTable();
	}
	/**
	* Assign the Table to a reservation, hence needing to change the reservation status of the Table to true
	* @param table_index the Table's index in TableList
	* @param cust_id the Table's customer number 
	*/
	//Reservation of tables
	public void setReserved(int table_index,String cust_id){
		tableList[table_index].setReservation();
		tableList[table_index].setCust(cust_id);
	}
	/**
	* Check the table reservation status of this Table by its Table index.
	* @param table_index TableList's array index. 
	* @return tableList[table_index].isReserved() false if not reserved, true if reserved.
	*/
	public boolean isReserved(int table_id){
		return tableList[table_id].isReserved();
	}
	/**
	* Returns the customer number of this Table by its Table index.
	* @param table_index TableList's array index. 
	* @return tableList[table_index].getCust() the Table's customer number
	*/
	public String getCust(int table_index){
		return tableList[table_index].getCust();
	}
	/**
	* Print the TableID and Customer ID if the Table index entered is reserved.
	* @param table_index TableList's array index. 
	* @return reserved the Table's reservation status
	*/
	public boolean printResevered(int table_index){
		boolean reserved=false;
		if (tableList[table_index].isReserved()) {
			System.out.println("Table ID "+tableList[table_index].getTableID()+" is reserved. Customer's contact number: "+ tableList[table_index].getCust()+"\n");
			reserved=true;
		}
		return reserved;
	}
	/**
	* Unassigning reservation status and customer's number by the Table index entered
	* @param table_index TableList's array index. 
	*/
	public void removeReservation(int table_index){
		tableList[table_index].unAssignReserve();
		tableList[table_index].removeCust();
	}
	
	/**
	* loop through the table list and 
	* print out those that are occupied.
	* 
	* @author Rachael
	*/
	public boolean viewOccupied() {
		String leftAlignFormat = "| %-9s |%n";
		System.out.format("+-----------+%n");
		System.out.format("| TableID   |%n");
		System.out.format("+-----------+%n");
		boolean hastable = false;
		for(int i=0; i<tableList.length; i++) {
			if (tableList[i].isOccupied() == true) {
				System.out.format(leftAlignFormat, "Table "+tableList[i].getTableID());
				System.out.format("+-----------+%n");
				hastable = true;
			}
		}
		if(hastable ==false) {
				System.out.println("No Table is Occupied");
		}
		return hastable;
	}
}
