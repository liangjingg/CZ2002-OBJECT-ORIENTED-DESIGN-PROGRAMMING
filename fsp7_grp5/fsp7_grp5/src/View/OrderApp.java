package View;
/**
The Boundary Class for Orders, a submenu of AppClass
@author Liang Jing
@version 1.0
@since 2019-04-18
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import Controller.OrderController;
import Model.OrderTable;
import Model.TableSeats;

public class OrderApp {
	public void orderListing(TableSeats ts) {
		/**
		* The number choice that user is going to enter.
		*/
		int orderC;
		OrderController oc = new OrderController();
		
		do {
			//Print menu item's menu
			System.out.format("+--------------------------------------------------+%n");
			System.out.format("| Order's functionalities                          |%n");
			System.out.println("|(1) View Existing Order                           |");
			System.out.println("|(2) Create New Order                              |");
			System.out.println("|(3) Add New Order Items                           |");
			System.out.println("|(4) Remove Existing Order Items                   |");
			System.out.println("|(5) Back to Main Menu Selection                   |");
			System.out.format("+--------------------------------------------------+%n");
			Scanner orderSC = new Scanner(System.in);
			System.out.print("Enter your choice: \n");
			orderC = orderSC.nextInt();
			if(orderC == 1) { //View Existing Order
				oc.viewOrder(ts);
			}else if(orderC == 2) { //Create New Order
				oc.createOrder(ts);
			}else if(orderC == 3) { //Add New Order Items 
				oc.addOrder(ts);
			}else if(orderC == 4) { //Remove Existing Order Items   
				oc.removeOrder(ts);
			}else if(orderC == 5) {
				break;	
			}else if(orderC > 10 && orderC != 10){
				System.out.println("Invalid Input, Try Again... \n");
			}
		}while(orderC != 5);
	}/**
	* Writing all the current order details on a text file
	* @param ts TableSeats object
    */
	public void writeOrder(TableSeats ts) throws IOException {
		OrderController oc = new OrderController();
		PrintWriter printWriter = new PrintWriter ("orders.txt");
	    printWriter.println (oc.writeOrder(ts));
	    printWriter.close ();
	}
}
