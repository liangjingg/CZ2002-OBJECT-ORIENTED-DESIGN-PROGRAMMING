package Model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.GregorianCalendar;
/**
Represents the ReservationList at our Bistro
@author Bog/Abby
@version 1.0
@since 2019-04-18
*/
public class ReservationBooking implements Serializable {
	/**
	* A Customer object array of 30
	*/
	static Customer[] c = new Customer[30];
	/**
	* A Customer object
	*/
	private Customer cust;
	/**
	* A Reservation object array of 30
	*/
	static Reservation[] r = new Reservation[30]; // 1 month
	
	/**
	* A GregorianCalendar object
	*/
	private GregorianCalendar today = new GregorianCalendar();
	/**
	* The current year
	*/
	private int todayYear = today.get(today.YEAR);
	/**
	* The current month
	*/
	private int todayMonth = today.get(today.MONTH) +1;
	/**
	* The current day
	*/
	private int todayDay = today.get(today.DAY_OF_MONTH); //Today Information
	/**
	* The reservation ID
	*/
	private int reservationID;
	/**
	* The number of pax for the reservation
	*/
	private int noOfPax;
	/**
	* The Customer ID
	*/
	private int customerID;
	/**
	* The session for the reservation
	*/
	private String session;
	/**
	* If it was possible for the reservation to be made
	*/
	private boolean possible;
	private String status;
	
	//private DateTime gracePeriod;
	
	/**
	* Creation of the object array for customer object and reservation
	*/
	public ReservationBooking(){
		for(int i=0;i<c.length;i++)
			c[i] = new Customer(i);
		for(int i=0;i<r.length;i++)
			r[i] = new Reservation();
	}
	
	/**
	* Set the customer id and name
	* @param ID customer id
	* @param customerName name of the customer
	*/
	public void setCustomerName( int ID, String customerName)
	{
		c[ID].setCustomerName(customerName);
	}
	/**
	* Set the customer's contact number
	* @param ID customer id
	* @param customerNo customer's contact number
	*/
	public void setCustomerNo(int ID, String customerNo)
	{
		c[ID].setCustomerNo(customerNo);
	}
	/**
	* Set the number of pax for the reservation
	* @param ID customer id
	* @param pax number of pax for the reservation
	*/
	public void setNoOfPax(int ID, int pax)
	{
		c[ID].setNoOfPax(pax);
	}
	/**
	* Check the year, month and day for the reservation
	* @param year year of the reservation
	* @param month  month of the reservation
	* @param day  day of the reservation
	* @return possible if it is possible to make a reservation or not
	*/
	public boolean checkDate(int year, int month, int day)
	{
		if(year==todayYear)
		{
			if(month == todayMonth+1)
			{
				if(day < todayDay)
					possible = true;
				else
					possible = false;
			}
			
			else if (month == todayMonth )
			{
				if(day > todayDay)
					possible = true;
				else
					possible = false;
			}
		}
		
		else if(year == todayYear+1)
		{
	
			if((month == 1) && (todayMonth==12))
			{				
				if(day <= todayDay)
					possible = true;
				else
					possible = false;				
			}
			
			else
				possible = false;
		}
		
		else
			possible = false;
		
		return possible;
	}
	/**
	* Set the year, month and day for the reservation
	* @param year year of the reservation
	* @param month  month of the reservation
	* @param day  day of the reservation
	*/
	public void setReservationDate(int ID, int year, int month, int day)
	{
		c[ID].setReservationDate(year, month, day);
	}
	/**
	* Set the session and time for the reservation
	* @param ID ID of the reservation
	* @param ap  session of the reservation
	* @param time  time of the reservation
	*/
	public void setTime(int ID, String ap, int time)
	{
		c[ID].setReservationTime(ap,time);
	}
	/**
	* Check if the reservation is valid or not
	* @param day day of the reservation
	* @param ap  session of the reservation
	* @param pax number of pax for the reservation
	* @param cust_id the customer id for the reservation
	* @return the table id or -1 if there is no table available to reserve
	*/
	public int checkReservation(int day, String ap, int pax,int cust_id){
		TableSeats t = null;
		
		// Populate t and r
		try {
			FileInputStream fis = new FileInputStream("tableSeatsData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			t = (TableSeats) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException f) {
			try {
				FileOutputStream fos = new FileOutputStream("tableSeatsData");
				ObjectOutputStream oos =  new ObjectOutputStream(fos);
				oos.writeObject(t);
				oos.close();
				fos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Cannot locate tableSeatsData!");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		try {
			FileInputStream fis = new FileInputStream("reservationData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			r = null;
			r = (Reservation[]) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException f) {
			try {
				FileOutputStream fos = new FileOutputStream("reservationData");
				ObjectOutputStream oos =  new ObjectOutputStream(fos);
				oos.writeObject(r);
				oos.close();
				fos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Cannot locate reservationData!");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		if(ap.equals("AM") || ap.equals("am")) {
			t=r[day-1].getAMTable(); //get tableseats for THAT DAY & that session
		}else{
			t=r[day-1].getPMTable(); //get tableseats for THAT DAY & that session
		} 
		
		for(int a=0; a<30; a++){//loop for thru tableseats t for a table id
			if(t.getCapacity(a)>=pax){ //find if the table index 'a' has enough capacity
				if(t.isReserved(a)==false){//table index a not reserved
						cust=new Customer(cust_id);
						return a;
				}	
			}
		}
		return -1;
	}
	/**
	* Creation of the reservation
	* @param day day of the reservation
	* @param ap  session of the reservation
	* @param pax number of pax for the reservation
	* @param a the table id
	* @param custNum the customer's contact number
	*/
	public void createReservation(int day, String ap, int pax,int a,String custNum) {
		TableSeats t;
		if(ap.equals("AM")) {
			t=r[day-1].getAMTable(); //get tableseats for THAT DAY & that session
		}else{
			t=r[day-1].getPMTable(); //get tableseats for THAT DAY & that session
		}
		t.setReserved(a,custNum);//update tableseats for that session
		if(ap.equals("AM")) {
			r[day-1].setAMTable(t);
		}else {
			r[day-1].setPMTable(t);
		}
		
		// Saving of entire reservation object
		try {
			// Save Reservation data
			FileOutputStream fos = new FileOutputStream("reservationData");
			ObjectOutputStream oos =  new ObjectOutputStream(fos);
			oos.writeObject(r);
			oos.close();
			fos.close();
			// Save Customer data
			FileOutputStream fos2 = new FileOutputStream("customerData");
			ObjectOutputStream oos2 =  new ObjectOutputStream(fos2);
			oos2.writeObject(c);
			oos2.close();
			fos2.close();
			// Save table seats data
			FileOutputStream fos3 = new FileOutputStream("tableSeatsData");
			ObjectOutputStream oos3 =  new ObjectOutputStream(fos3);
			oos3.writeObject(t);
			oos3.close();
			fos3.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to save reservation.");
		}
	}
	/**
	* Display of the reservation
	* @param day day of the reservation
	* @param ap  session of the reservation
	*/
	public void printReservation(int day,String ap){
		// Populate generic TableSeats to use
		TableSeats t = null;
		r = null;
		
		try {
			FileInputStream fis = new FileInputStream("tableSeatsData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			t = (TableSeats) ois.readObject();
			ois.close();
			fis.close();
			
			FileInputStream fis2 = new FileInputStream("reservationData");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			r = (Reservation[]) ois2.readObject();
			ois2.close();
			fis.close();
			
			if(ap.equals("AM")) {
				t=r[day-1].getAMTable();
				System.out.println("Day "+day+"'s AM reservation list");
				for(int i = 0; i<30; i++){
					t.printResevered(i);
				}
			}else {
				t=r[day-1].getPMTable();
				System.out.println("Day "+day+"'s PM reservation list");
				for(int i=0; i<30; i++){
					t.printResevered(i);
				}
			}
		} catch (IOException e) {
			System.out.println("Cannot locate tableSeatsData/reservationData!");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}	
	}
	/**
	* Removal of the reservation
	* @param day day of the reservation
	* @param ap  session of the reservation
	* @param custNum the customer's contact number
	*/
	public void removeReservation(int day,String ap,String contactNumber){
		// Populate generic TableSeats to use
		TableSeats t = null;
		r = null;
		
		try {
			FileInputStream fis = new FileInputStream("tableSeatsData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			t = (TableSeats) ois.readObject();
			ois.close();
			fis.close();
			
			FileInputStream fis2 = new FileInputStream("reservationData");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			r = (Reservation[]) ois2.readObject();
			ois2.close();
			fis.close();
			
			if(ap.equals("AM")) {
//				TableSeats am=r[day-1].getAMTable();
				t = r[day-1].getAMTable();
				for(int i = 0; i<30; i++){
					if(t.getCust(i)!=null) {
						if(t.getCust(i).equals(contactNumber)) {
							t.removeReservation(i);
//							System.out.println("Reservation removed");
//							return;
						}
					}
				}
			}else {
//				TableSeats pm=r[day-1].getPMTable();
				t = r[day-1].getPMTable();
				for(int i=0; i<30; i++){
					if(t.getCust(i).equals(contactNumber)) {
						t.removeReservation(i);
//						System.out.println("Reservation removed");
//						return;
					}
				}
			}
			
			// Once done deleting, stage changes onto necessary data files
			try {
				FileOutputStream fos3 = new FileOutputStream("tableSeatsData");
				ObjectOutputStream oos3 =  new ObjectOutputStream(fos3);
				oos3.writeObject(t);
				oos3.close();
				fos3.close();
				FileOutputStream fos4 = new FileOutputStream("reservationData");
				ObjectOutputStream oos4 =  new ObjectOutputStream(fos4);
				oos4.writeObject(r);
				oos4.close();
				fos4.close();
				System.out.println("Reservation removed");
			} 
			catch (IOException e) 
			{
				System.out.println("Failed to remove reservation.");
			}
			
		} catch (IOException e) {
//			System.out.println("Cannot locate tableSeatsData/reservationData!");
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}
	/*public void printReservation(){
		for(int day=0;day<30;day++) {
			TableSeats am=r[day].getAMTable();
			System.out.println("Day "+day+"'s AM reservation list");
			for(int c = 0; c<30; c++){
				am.printResevered(c);
			}
		}
	}*/
			
}

