package View;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import Controller.MainMenuController;
import Model.Invoice;
import Model.SalesReport;
import Model.StaffList;
import Model.TableSeats;

public class AppClass {
	//View is where you print all the "Menus".
	//Here is where you call your methods.
	
	
	
	/**
	* This whole menu is for the user to choose submenus for their intended purpose.
	*/

	public static void main(String[] args) throws IOException {
		//**load today's reservation into the current TableSeats
		//**ask for session as well
		//create tables
		TableSeats ts = new TableSeats();
		//create staffs
		StaffList s=new StaffList();
		s.createStaff();
		PrintWriter printWriter = new PrintWriter ("staff.txt");
	    printWriter.println (s.printStaff());
	    printWriter.close ();
		
		int choice;
		Scanner sc = new Scanner(System.in);
		do{
			System.out.format("+--------------------------------------------------+%n");
			System.out.format("| Bistro 5's RRPSS                                 |%n");
			System.out.format("+--------------------------------------------------+%n");
			System.out.println("|(1) Add/Update/View/Delete Menu Items             |");
			System.out.println("|(2) Check Table Availability                      |");
			System.out.println("|(3) Create/View/Edit Order                        |");
			System.out.println("|(4) Create/Check/Remove Reservation               |");
			System.out.println("|(5) Print Invoice                                 |");
			System.out.println("|(6) Print Sales Revenue                           |");
			System.out.println("|(7) Exit                                          |");
			System.out.format("+--------------------------------------------------+%n");
			System.out.print("Enter your choice: \n");
		choice = sc.nextInt();
		
		if(choice == 1) {
			MenuApp ma = new MenuApp();
			ma.menuListing(s.manager());
		}else if (choice == 2) {
			ts.allAvail();
		}else if (choice == 3) {
			OrderApp oa = new OrderApp();
			oa.orderListing(ts);
		}else if (choice == 4) {
			ReservationApp ra = new ReservationApp();
			ra.reservationListing(ts);
		}else if (choice == 5) {
			OrderApp oa = new OrderApp();
			oa.writeOrder(ts);
			Invoice i = new Invoice();
			i.printInvoice(ts,s);
			
		}else if(choice == 6) {
			SalesReport sr = new SalesReport();
			sr.operation(s);
			
		}else if(choice > 6 && choice != 7){
			System.out.println("Invalid Input, Try Again... \n");
		}
		
		}while(choice != 7);
		System.out.print("Programme Terminating...");
	}
	
}


