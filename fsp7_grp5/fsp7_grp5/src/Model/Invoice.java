/**
Represents a paying procedure from a unpaid order to paid order.
It handle the printing of invoices and implements Serializable 
so that it can serialize/deserialize the class.
@author Rachael Neoh
@version 1.0
@since 2019-04-12
*/

package Model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import Controller.MainMenuController;
//import Controller.SeriailizeDB;
import Controller.OrderController;

public class Invoice implements Serializable
{
	/**
	* o to access information in Order class.
	* E.g. orders, staffid.
	*/
	public Order o;
	
	/**
	* invoice ID to identify each invoice.
	*/
	public int invoiceID;
	
	/**
	* When the invoice is created, 
	* the invoice date is set to current date.  
	* Use to print it on invoice.
	*/
	public Date invoiceDate;
	
	/**
	* totalPrice to calculate each order.
	*/
	public double totalPrice;
	
	/**
	* orderlist is a arraylist which contains all the ordered items.
	*/
	public ArrayList<String[]> orderlist = new ArrayList<String[]>();
	
	/**
	* count use to auto increment invoice ID is set to 0 for the start.
	*/
	public static final AtomicInteger count = new AtomicInteger(0);
	
	/**
	* print invoice when a table asks for bill
	* 
	* @param ts contains the arraylist of table that the restaurant have. 
	* @param staff contains the list of staff to get 
	* 				staff's name based on the ID.
	*/
	public void printInvoice(TableSeats ts,StaffList staff)
	{
		ArrayList<Invoice> orderRecord = null;
		if(readSerializedObject("OrderRecord.db")==null)
            orderRecord = new ArrayList<Invoice>();
        else
            orderRecord =(ArrayList) readSerializedObject("OrderRecord.db");
		if(orderRecord != null) {
			count.set(orderRecord.size());
		}

		MainMenuController mmc = new MainMenuController();
		boolean success = true;
		Scanner sc = new Scanner(System.in);
		do 
		{
			if(!ts.viewOccupied()) {
				System.out.println();	
				return;
			}
			System.out.println("Which table going to bill now?");
			System.out.println("<Press -1 to back>");
			int tableID = sc.nextInt();
			if(tableID==-1){
				return;
			}
			
			o = OrderController.getOt().getInvoice(tableID, ts);
			
			if (o.orderItems.isEmpty() == true)
			{
				System.out.println("No Order from table " + tableID);
				return;
			}
			
			System.out.println("Confirm? (true/false)");
			success = sc.nextBoolean();
			if(success)
			{
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat tf = new SimpleDateFormat("HH:mm:ss");
				DecimalFormat dc = new DecimalFormat("0.00");
				invoiceDate = new Date();
				invoiceID = count.incrementAndGet();
				System.out.println("+------------------------------------------------------------------+");
				System.out.println("|                                                                  |");
				System.out.format("|%37s                             |%n","Bistro 5");
				System.out.format("|%45s                     |%n", "NTU North Spine #01-06");
				System.out.format("|%50s                |%n", "76 Nanyang Dr Singapore 637331");
				System.out.format("|%45s                     |%n", "Telephone: +65 5555 5555");
				System.out.println("|                                                                  |");
				System.out.format("| %-45s %-19s|%n", "Served by: " + staff.getStaff(o.getStaffID()).firstname, "Date: " + df.format(invoiceDate));
				System.out.format("| %-46s %-18s|%n", "Table No.: " + tableID, "Time: " + tf.format(invoiceDate));
				System.out.format("| %-65s|%n", "Invoice No.: " + invoiceID);
				System.out.println("|                                                                  |");
				System.out.format("|%39s                           |%n","TAX INVOICE");
				System.out.println("|                                                                  |");
				System.out.format("|       %-34s  %-10s %-12s|%n", "Description", "Qty", "Amount");
				System.out.println("|------------------------------------------------------------------|");
				System.out.println("|                                                                  |");

				ArrayList<String> orderS = o.getOrderItems();
				ArrayList<String> unqiueString = getUnqiueString(orderS);
				
				boolean header = false;
				
				for(String us:unqiueString) {
					boolean hasmenu = false;
					String menuName = "";
					double price = 0;
					for (AlacarteMenu m : mmc.getAlacarteMenu()){
						if(m.getMenuName().equals(us)){
							menuName = m.getMenuDesc();
							price = m.getMenuPrice();
							hasmenu=true;
						}
					}
					int count=0;
					for(String s :orderS) {
						if(us.equals(s))
						{
							count++;
						}
					}
					if(hasmenu)
					{
						System.out.format("|       %-35s  %-10s %-11s|%n", menuName + (" Ala Carte"), count, "$" + dc.format(price*count));
						header=true;
					}
				}
				if(header)
					System.out.println("|                                                                  |");
				
				for(String us:unqiueString) 
				{
					ArrayList<String> PromoMenuID = new ArrayList<String>();
					String packageName="";
					int promocount=0;
					double price = 0;
					for (PromotionalPackage p : mmc.getPromotionalPackage())
					{
						if(p.packageID.equals(us))
						{
							price += p.getMenuPrice();
							PromoMenuID.add(p.getMenuName());
							promocount=0;
							for (String s: orderS)
							{
								if (us.contentEquals(s))
								{
									promocount++;
								}
							}
						}
					}
					
					if(!PromoMenuID.isEmpty())
					{
						System.out.format("|       %-36s %-10s %-11s|%n","Package " + us.substring(1), promocount, "$" + dc.format(price*promocount));
					}
					
					for(String s : PromoMenuID){
						for (AlacarteMenu m : mmc.getAlacarteMenu())
						{
							if(m.getMenuName().equals(s))
							{
								System.out.format("|         %-57s|%n", "- " + m.getMenuDesc());
							} 
						}
						
					}
					
				}
				
				System.out.println("|                                                                  |");
				System.out.println("|------------------------------------------------------------------|");
				
				double subtotal = 0;
				for(String s:orderS) 
				{
					for (AlacarteMenu m : mmc.getAlacarteMenu())
					{
						if (m.getMenuName().equals(s))
							subtotal += m.getMenuPrice();
					}
					for (PromotionalPackage m : mmc.getPromotionalPackage())
					{
						if(m.packageID.equals(s)){
							subtotal += m.getMenuPrice();
						}
					}
				}
				System.out.format("|%54s %-11s|%n", "SUBTOTAL: ", "$" +dc.format(subtotal));
				
				double service = 0.1 * subtotal;
				System.out.format("|%54s %-11s|%n", "10% SERVICE CHRG: ", "$" + dc.format(service));
				
				double gst = 0.07 * subtotal;
				System.out.format("|%54s %-11s|%n", "7% GST: ", "$" + dc.format(gst));
				
				totalPrice = subtotal + gst + service;
				System.out.println("|------------------------------------------------------------------|");
				System.out.format("|%54s %-11s|%n", "TOTAL: ", "$" + dc.format(totalPrice));
				System.out.println("|==================================================================|");
				System.out.format("|%44s                      |%n", "* * * THANK YOU * * *");
				System.out.format("|%48s                  |%n", "* * * Please Come Again * * *");
				System.out.println("+------------------------------------------------------------------+\n");
				

				orderRecord.add(this);
				writeSerializedObject("OrderRecord.db", orderRecord);
			}
		}while(!success);
	}
	
	/**
	* read db file 
	* @param filename contains order record
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
		}finally {  
			
		}
		return pDetails;
	}

	/**
	* write into db file
	* @param filename update db
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
	
	/**
	* loop through the orderS of arraylist then produce unique string found.
	* 
	* @param ArrayList<String> orderS
	* @return uniqueString The unique String in the order such as M01, P01, M02.
	*/
	public ArrayList<String> getUnqiueString( ArrayList<String> orderS) {
		ArrayList<String> uniqueString = new ArrayList<String>();
		
		boolean isunqiue = true;
		for(String s :orderS) {
			isunqiue = true;
			for(String us:uniqueString) {
				if(us.equals(s))
				{
					isunqiue=false;
				}
			}
			if(isunqiue)
				uniqueString.add(s);
		}
		return uniqueString;

	}
}
