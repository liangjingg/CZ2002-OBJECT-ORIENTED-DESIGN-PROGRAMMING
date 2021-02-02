/**
Represents the sales made in terms of daily, 
monthly or annually in a table format.
@author Rachael Neoh
@version 1.0
@since 2019-04-12
*/

package Model;

import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Controller.MainMenuController;

public class SalesReport
{
	/**
	* The start date of the period Manager want to view.
	*/
	private Date startDate;
	
	/**
	* The end date of the period Manager want to view.
	* if endDate is null means it will print the report for today
	*/
	private Date endDate;
	
	/**
	* MainMenuController object to get ordered items' details.
	*  E.g. menu description, promotion description and price.
	*/
	MainMenuController mmc = new MainMenuController();
	
	/**
	* List down the types of report for selection.
	* (Today, Monthly, Yearly)
	* 
	* Only Manager role is able to view reports.
	* 
	* Other roles such as Chef and Waiter unable to view it
	* hence generate unauthorized error message.
	* 
	* After validating role, prompt user to enter the period 
	* he/she want to view the sales report from and to.
	* 
	* Then pass startDate, endDate and staff to printReport().
	* 
	* @param staff contains the list of staff to get 
	* 				the staff's id and staff's name.
	*/
	public void operation(StaffList staff)
	{
		if(!staff.manager()) {
			System.out.println("Only authorized personnel able to view following information.");
			return;
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		int option;
		System.out.format("+--------------------------------------------------+%n");
		System.out.format("| View By                                          |%n");
		System.out.format("+--------------------------------------------------+%n");
		System.out.println("|(1) Daily                                         |");
		System.out.println("|(2) Monthly                                       |");
		System.out.println("|(3) Yearly                                        |");
		System.out.format("+--------------------------------------------------+%n");
		System.out.println("Enter your choice:");
		System.out.println("<Press -1 to back>");
		do
		{
			option = sc.nextInt();
			switch (option)
			{
				case 1:
					Date cur = new Date();
					startDate = cur;
					endDate = null;
					printReport(cur, endDate, staff);
					break;
				case 2:
					System.out.println("Enter start month period (MM/yyyy): ");
					String startM = sc.next();
					startM = "01/" + startM;
					try 
					{
						startDate = df.parse(startM);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						 System.out.println("* Please enter correct date format and valid month range (1-12) (MM/yyyy) *");
					}
					
					System.out.println("Enter end month period (MM/yyyy):");
					String endM = sc.next();
					int monthM = Integer.parseInt(endM.substring(0, 2));
					int yearM = Integer.parseInt(endM.substring(3,7));
					YearMonth ymM = YearMonth.of(yearM,monthM);
					Integer dayM = ymM.lengthOfMonth();
					try 
					{
						endDate = df.parse(dayM.toString() + "/" + endM);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					printReport(startDate, endDate, staff);
					break;
				case 3:
					System.out.println("Enter start year (yyyy):");
					String startY = sc.next();
					startY = "01/01/" + startY;
					try {
						startDate = df.parse(startY);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						 System.out.println("* Please enter correct date format and valid month range (1-12) (MM/yyyy) *");
					}
					
					System.out.println("Enter end year (yyyy):");
					String endY = sc.next();
					int yearY = Integer.parseInt(endY.substring(0,3));
					YearMonth ymY = YearMonth.of(yearY, 12);
					Integer dayY = ymY.lengthOfMonth();
					try {
						endDate = df.parse(dayY.toString() + "/12/" +  endY);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					printReport(startDate, endDate, staff);
					break;
				default:
					System.out.println("Invalid Input");
			}
		}while (option > 3 && option < 1);
		return;
	}
	
	/**
	* Print various type of report based on user selection in operation()
	* 
	* Daily report will only contains current date sales. 
	* E.g. current date is 12/04/2019. All sales made on this day will be 
	* printed out with following details: time, invoice no., ordered items, 
	* total price of each order, staff name who created the order. 
	* Ending it with total revenue made and No of transaction.
	* 
	* Similarly for Monthly and Annual reports. 
	* Just that it has an additional column called date.
	* 
	* @param startDate start period of the report
	* @param endDate end period of the report, if null means the report printed will be for the day itself.
	* @param staff This contains the list of staff to get 
	* 				the staff's id and staff's name.
	*/
	public void printReport(Date startDate, Date endDate, StaffList staff) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat tf = new SimpleDateFormat("HH:mm:ss");
		DecimalFormat dc = new DecimalFormat("0.00");
		double revenue = 0;
		int countSales = 0;
		
		ArrayList<Invoice> orderRecord = null;
		if(readSerializedObject("OrderRecord.db")==null)
            orderRecord = new ArrayList<Invoice>();
        else
            orderRecord =(ArrayList) readSerializedObject("OrderRecord.db");
		
		if(endDate==null) 
		{
			System.out.println("+--------------------------------------------------------------------------------------------------------+");
			System.out.println("|                                                                                                        |");
			System.out.format("|%56s                                                |%n","Bistro 5");
			System.out.format("|%58s                                              |%n", "Daily Report");
			System.out.format("|                                                                                    %-20s|%n", "Date: " + df.format(startDate));
			System.out.println("|                                                                                                        |");
			System.out.println("+-----------+------------+---------------------------------------------------+-----------+---------------+");
			System.out.format("| %-10s| %-10s| %-50s| %-10s|%-15s|%n", "Time", "Invoice No.", "Order", "Price ($)", "Staff Name");
			System.out.println("+-----------+------------+---------------------------------------------------+-----------+---------------+");
			for (Invoice i : orderRecord)
			{
				if (i.invoiceDate.getDay()==startDate.getDay() && i.invoiceDate.getMonth()==startDate.getMonth() && i.invoiceDate.getYear()==startDate.getYear())
					{
					ArrayList<String> orderS = new ArrayList<String>();
					ArrayList<String> uniqueS = new ArrayList<String>();
					orderS = i.o.getOrderItems();
					uniqueS = i.getUnqiueString(orderS);
					boolean hasmenu = false;
					for(String us:orderS) {
						
						String menuName = "";
						for (AlacarteMenu m : mmc.getAlacarteMenu()){
							if(m.getMenuName().equals(us)){
								menuName=m.getMenuDesc();
								if(hasmenu==false)
									System.out.format("|%-10s | %-11s| %-50s| %-10s|%-15s|%n", tf.format(i.invoiceDate), i.invoiceID, menuName  + (" Ala Carte"), dc.format(i.totalPrice), staff.getStaff(i.o.getStaffID()).firstname);
								else
									System.out.format("|%-10s | %-11s| %-50s| %-10s|%-15s|%n", "", "", menuName  + (" Ala Carte"), "", "");
								hasmenu=true;
								
							}
						}
						if(us.charAt(0)=='P')
						{
								if(hasmenu==false)
									System.out.format("|%-10s | %-11s| %-50s| %-10s|%-15s|%n", tf.format(i.invoiceDate), i.invoiceID, "Promotional Package " + us.substring(1), dc.format(i.totalPrice), staff.getStaff(i.o.getStaffID()).firstname);
								else
									System.out.format("|%-10s | %-11s| %-50s| %-10s|%-15s|%n", "", "", "Promotional Package " + us.substring(1), "", "");
								hasmenu=true;
						}
					}
					System.out.println("+-----------+------------+---------------------------------------------------+-----------+---------------+");
					countSales++;
					revenue += i.totalPrice;
				}
			}
			System.out.format("|%91s %-12s|%n", "No of Transaction: ", countSales);
			System.out.format("|%91s %-12s|%n", "Total Revenue: ", "$" + dc.format(revenue));
			System.out.println("+--------------------------------------------------------------------------------------------------------+");
		}
		else
		{
			System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
			System.out.println("|                                                                                                                    |");
			System.out.format("|%58s                                                          |%n","Bistro 5");
			
			if (endDate.getMonth() - startDate.getMonth() < 12)
				System.out.format("|%62s                                                      |%n","Monthly Report");
			else
				System.out.format("|%62s                                                      |%n","Annual Report");

			System.out.format("|                                                                                  %-34s|%n", "Period: " + df.format(startDate) + " - " + df.format(endDate));
			System.out.println("|                                                                                                                    |");
			System.out.println("+-----------+-----------+------------+---------------------------------------------------+-----------+---------------+");
			System.out.format("| %-10s| %-10s| %-10s| %-50s| %-10s|%-15s|%n", "Date", "Time", "Invoice No.", "Order", "Amount", "Staff Name");
			System.out.println("+-----------+-----------+------------+---------------------------------------------------+-----------+---------------+");
			for (Invoice i : orderRecord)
			{
				if ((i.invoiceDate.after(startDate) && i.invoiceDate.before(endDate)) ||(df.format(i.invoiceDate).equals(df.format(startDate))) ||(df.format(i.invoiceDate).equals(df.format(endDate))))
				{
					ArrayList<String> orderS = new ArrayList<String>();
					ArrayList<String> uniqueS = new ArrayList<String>();
					orderS = i.o.getOrderItems();
					uniqueS = i.getUnqiueString(orderS);
					boolean hasmenu = false;
					for(String us:orderS) {
						
						String menuName = "";
						for (AlacarteMenu m : mmc.getAlacarteMenu()){
							if(m.getMenuName().equals(us)){
								menuName=m.getMenuDesc();
								if(hasmenu==false)
									System.out.format("|%-10s | %-10s| %-11s| %-50s| %-10s|%-15s|%n", df.format(i.invoiceDate), tf.format(i.invoiceDate), i.invoiceID, menuName  + (" Ala Carte"), dc.format(i.totalPrice), staff.getStaff(i.o.getStaffID()).firstname);
								else
									System.out.format("|%-10s | %-10s| %-11s| %-50s| %-10s|%-15s|%n", "", "", "", menuName  + (" Ala Carte"), "", "");
								hasmenu=true;
							}
						}

						if(us.charAt(0)=='P')
						{
								if(hasmenu==false)
									System.out.format("|%-10s | %-10s| %-11s| %-50s| %-10s|%-15s|%n", df.format(i.invoiceDate),tf.format(i.invoiceDate), i.invoiceID, "Promotional Package " + us.substring(1), dc.format(i.totalPrice), staff.getStaff(i.o.getStaffID()).firstname);
								else
									System.out.format("|%-10s | %-10s| %-11s| %-50s| %-10s|%-15s|%n", "", "", "", "Promotional Package " + us.substring(1), "", "");
								hasmenu=true;
						}
					}
					System.out.println("+-----------+-----------+------------+---------------------------------------------------+-----------+---------------+");
					countSales++;
					revenue += i.totalPrice;
				}
			}
			System.out.format("|%103s %-12s|%n", "No of Transaction: ", countSales);
			System.out.format("|%103s %-12s|%n", "Total Sales: ", "$"+dc.format(revenue));
			System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
		}
	}
	
	/**
	* It will read the filename and deserialized the file into the object class.
	* catch FileNotFoundException - return null if file is not found
	* catch IOException - print error message
	* catch ClassNotFoundException- print error message
	* @param filename contains order record
	* @return pDetails This is an List which will be return when the file is read/deserialized successfully
	*/
	public static List readSerializedObject(String filename) {
		
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		}catch (FileNotFoundException ex){
			return (ArrayList)pDetails;
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}finally {  }
		return pDetails;
	}

	/**
	* This will take in the List to be Serialized
	* The Serialized name will be the param filename
	* @param filename the filename of the SerializedObject
	* @param list the ArrayList to be Serialized
	*/
	public static void writeSerializedObject(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
