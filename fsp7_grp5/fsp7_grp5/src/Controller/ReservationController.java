package Controller;
/**
Control Class for Reservation
@author Liang Jing/Bog
@version 1.0
@since 2019-04-18
*/
import java.util.Scanner;
import Model.ReservationBooking;
import Model.TableSeats;

public class ReservationController {
	/**
	* Reservation id
	*/
	int id =0;
	/**
	* A Scanner object
	*/
	Scanner sc = new Scanner(System.in);
	/**
	* A Reservation booking object
	*/
	static ReservationBooking rb = new ReservationBooking();
			
	/**
	* Creation of Reservation with validations on the date entered
	* , on the session and timing entered and lastly checking if there
	* is table available for the customer to reserve
	*/
	public void createBooking() {
		String ap;
		int hour,day,month, pax;
		System.out.println("When do you want to reserve?");
		System.out.print("Year : ");
		int year = sc.nextInt();
		do {
			System.out.print("Month : ");
			month = sc.nextInt();
		}while(month<1||month>12);
		do {
			System.out.print("Day : ");
			day = sc.nextInt();
		}while(day<1||day>30);
		boolean reservePossible = rb.checkDate(year, month, day);
		
		if(reservePossible == true){
			rb.setReservationDate(id, year, month, day); 
			do {
				System.out.print("Which Session? (AM)/(PM): ");
				ap = sc.next();
				ap.toUpperCase();
			}while(!ap.equals("AM")&&!ap.equals("PM"));
			if(ap.equals("AM")) {
				do {
					System.out.print("What time do you want to reserve? (11am to 2pm) ");
					hour = sc.nextInt();
				}while(hour!=11 && hour!=12 && hour!=1 && hour!=2);
			}else {
				do {
					System.out.println("What time do you want to reserve? (6pm-9pm) ");
					hour = sc.nextInt();
				}while(hour!=6 && hour!=7 && hour!=8 && hour!=9);
			}
			rb.setTime(id,ap,hour);
			do {
				System.out.print("How many people do you want to reserve? ");
				pax = sc.nextInt();
			}while(pax<1||pax>10);
			rb.setNoOfPax(id, pax);
			int r = rb.checkReservation(day, ap, pax, id);
			
			if(r != -1){
				System.out.print("What is your name? ");
				String name = sc.next();
				rb.setCustomerName(id, name);
				System.out.print("What is your contact number? ");
				String contactnumber = sc.next();
				rb.setCustomerNo(id, contactnumber);
				rb.createReservation(day, ap, pax, r, contactnumber);
				
				System.out.println("Your reservation is made!\n");
				id++;
			}else
				System.out.println("Sorry! You can't reserve.\n");
			
		}else
			System.out.println("Sorry! You can only reserve 1 month in advance.\n");
	}
	
	/**
	* It prints the details for the booking that the user want to see
	* which requires the input of day and session while the system
	* show the user the details of each booking for that session
	*/
	public void viewBooking() {
		int day;
		String ap;
		do {
			System.out.print("Day : ");
			day = sc.nextInt();
		}while(day<1&&day>30);
		do {
			System.out.print("Session (AM/PM): ");
			ap = sc.next();
			//ap.toUpperCase();
			
		}while(!ap.equals("AM")&&!ap.equals("PM"));
		rb.printReservation(day,ap);
		//rb.printReservation();
	}
	/**
	* It removes the booking made with the input of
	* day and session
	*/
	public void removeBooking() {
		String ap, contactNumber;
		int day;
		do {
			System.out.print("Which Day Was The Booking Made For: ");
			day = sc.nextInt();
		}while(day<1||day>30);
		do {
			System.out.print("Which Session? (AM)/(PM): ");
			ap = sc.next();
			ap.toUpperCase();
		}while(!ap.equals("AM")&&!ap.equals("PM"));
		System.out.print("Enter Customer's Contact Number: ");
		contactNumber = sc.next();
		rb.removeReservation(day, ap,contactNumber);
	}
	
}
