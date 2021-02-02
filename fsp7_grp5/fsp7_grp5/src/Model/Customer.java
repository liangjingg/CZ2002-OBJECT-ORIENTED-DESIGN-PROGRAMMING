package Model;
/**
Represent a customer in our Bistro
@author Liang Jing/Bog
@version 1.0
@since 2019-04-18
*/
import java.io.Serializable;

public class Customer implements Serializable {
	/**
	* Customer ID created when there is a Reservation
	*/
	private int customerID;
	/**
	* Customer name
	*/
	private String customerName;
	/**
	* Customer's contact number
	*/
	private String customerNo;
	/**
	* The year that the reservation is made for
	*/
	private int reservationYear;
	/**
	* The month that the reservation is made for
	*/
	private int reservationMonth;
	/**
	* The day that the reservation is made for
	*/
	private int reservationDay;
	/**
	* The session that the reservation is made for
	*/
	private String reservationAP;
	/**
	* The time that the reservation is made for
	*/
	private int reservationHour;
	private int reservationMinute;
	/**
	* The amount of pax that the reservation is made for
	*/
	private int pax;
	
	/**
	* The creation of a customer
	* @param customerID Customer ID created when there is a Reservation
	*/
	Customer(int customerID)
	{
		this.customerID = customerID;
	}
	/**
	* Set the name of the customer
	* @param customerName Customer's name
	*/
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}
	/**
	* Set the contact number of the customer
	* @param customerNo Customer's contact number
	*/
	public void setCustomerNo(String customerNo)
	{
		this.customerNo = customerNo;
	}
	/**
	* Get the customer's contact number
	* @return customerNo Customer's contact number
	*/
	public String getCustomerNum(int customerID)
	{
		return customerNo;
	}
	/**
	* Get the customer's name
	* @return customerName Customer's name
	*/
	public String getCustomerName()
	{
		return customerName;
	}
	/**
	* Set the customer's reservation details; year, month and day
	* @param year year of the reservation
	* @param month month of the reservation
	* @param day day of the reservation
	*/
	public void setReservationDate(int year, int month, int day)
	{
		reservationYear = year;
		reservationMonth = month;
		reservationDay = day;
	}
	/**
	* Set the customer's reservation details; session and hour
	* @param ap session of the reservation
	* @param hour hour of the reservation
	*/
	public void setReservationTime(String ap, int hour)
	{
		reservationAP = ap;
		reservationHour = hour;
	}
	/**
	* Set the customer's reservation detail; pax
	* @param pax number of pax for the reservation
	*/
	public void setNoOfPax(int pax)
	{
		this.pax = pax;
	}
	
}
