package Model;
/**
Represents a table at our Bistro
@author Liang Jing/Karen
@version 1.0
@since 2019-04-18
*/
import java.io.Serializable;

public class Table implements Serializable {
			/**
			* The ID number of this Table
			*/
			private int tableID;
			/**
			* The capacity of this Table
			*/
			private int tableCapacity;
			/**
			* The occupancy status of this Table
			*/
			private boolean tableOccupied;
			/**
			* The reservation status of this Table
			*/
			private boolean tableReserved;
			/**
			* The customer's contact number that reserved this Table
			*/
			private String customerNum;
			
			public Table() {
			}
			
			/**
			* Creates a new Table with the given id and capacity.
			* With no occupancy nor reservation.
			* @param table_id This Table's new table id.
			* @param table_capacity This Table's new table capacity.
			*/
			//methods
			public Table(int table_id, int table_capacity) {
				this.tableID = table_id;
				this.tableCapacity = table_capacity;
				this.tableOccupied = false;
				this.tableReserved = false;
			}
			/**
			* Gets the table ID of this Table.
			* @return this Table's ID.
			*/
			//get capacity 
			public int getTableID() {
				return tableID;
			}
			/**
			* Gets the Capacity of this Table.
			* @return this Table's Capacity.
			*/
			public int getCapacity() {
				return tableCapacity;
			}
			/**
			* Gets the Occupancy status of this Table.
			* @return this Table's Occupancy status.
			*/
			//Check if there is ppl seating at the table at the moment 
			public boolean isOccupied() {
				return tableOccupied;
			}
			/**
			* Gets the Reservation status of this Table.
			* @return this Table's Occupancy status.
			*/
			//check if the seat is reserved for a specific session (that may be define during reservation)
			public boolean isReserved() {
				return tableReserved;
			}
			/**
			* Set the table occupancy status to true for this Table.
			*/
			//assign seat for walk in customers (set method)
			public void assignTable() {
				tableOccupied = true;	
			}
			/**
			* Set the table occupancy status to false for this Table.
			*/
			public void unassignTable() {
				tableOccupied = false;
			}
			/**
			* Set the table reservation status to true for this Table.
			*/
			//set reservation 
			public void setReservation() {
				tableReserved = true;
			}
			/**
			* Set the table reservation status to false for this Table.
			*/
			public void unAssignReserve() {
				tableReserved = false;
			}
			/**
			 * Gets the Customer number of this Table Reservation.
			 * @return this Table's Customer number.
			 */
			public String getCust()
			{
				return customerNum;
			}
			/**
			* Set the name of this Table's customer number.
			* @param customerNum This Table's customer number.  
			*/	
			public void setCust(String customerNum)
			{
				this.customerNum = customerNum;
			}
			/**
			* Setting the name of this Table's customer number to empty.  
			*/
			public void removeCust()
			{
				this.customerNum = "";
			}

}
