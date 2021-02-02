package View;
/**
The Boundary Class for Reservation, a submenu of AppClass
@author Liang Jing/Bog
@version 1.0
@since 2019-04-18
*/
import java.util.Scanner;

import Controller.ReservationController;
import Model.TableSeats;

public class ReservationApp {
	public void reservationListing(TableSeats ts) {
		/**
		* The number choice that user is going to enter.
		*/
		int reservationC;
		ReservationController rc = new ReservationController();
		
		do {
			//Print menu item's menu
			System.out.format("+--------------------------------------------------+%n");
			System.out.format("| Reservation's functionalities                    |%n");
			System.out.println("|(1) View Existing Reservation                     |");
			System.out.println("|(2) Create New Reservation                        |");
			System.out.println("|(3) Remove Existing Reservation                   |");
			System.out.println("|(4) Back to Main Menu Selection                   |");
			System.out.format("+--------------------------------------------------+%n");
			Scanner reservationSC = new Scanner(System.in);
			System.out.print("Enter your choice: \n");
			reservationC = reservationSC.nextInt();
			if(reservationC == 1) { //View Existing Reservation 
				rc.viewBooking();
			}else if(reservationC == 2) { //Create New Reservation 
				rc.createBooking();
			}else if(reservationC == 3) { //Remove Existing Reservation
				rc.removeBooking();
			}else if(reservationC == 4) {  
				break;
			}else if(reservationC > 5 && reservationC != 5){
				System.out.println("Invalid Input, Try Again... \n");
			}
		}while(reservationC != 5);
	}
}
