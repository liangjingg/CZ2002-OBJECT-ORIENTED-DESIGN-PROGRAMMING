/**
The Controller Class for the Menu : AlaCartes as well as Promotional Packages.
@author Roxas Abby Maurea Imus
@version 1.0
@since 2019-04-18
*/

package Controller;

import Model.MainMenu;
import Model.AlacarteMenu;
import Model.PromotionalPackage;
import Model.StaffList;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import java.util.Collections;
import java.util.Comparator;
import java.text.DecimalFormat;


//Handles all your Menu Methods - Promotional Package - A la Cartes.
public class MainMenuController {
	
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");

	private String menuID;

	
	/**
	 * Creation of Static Because there should only be 1 Memory Allocation for these on runtime.
	 * Usage of ArrayList to Store AlaCartes as well as Promotional Packages.
	 */
	
	static ArrayList<AlacarteMenu> mm = new ArrayList<AlacarteMenu>();
	/**
	* Arraylist for thepromotional Package
	*/
	static ArrayList<PromotionalPackage> pp = new ArrayList<PromotionalPackage>();
	
	/**
	* Method to get the AlacarteMenu
	*/

	public ArrayList<AlacarteMenu> getAlacarteMenu() {
		ArrayList<AlacarteMenu> mmForOrders = new ArrayList<AlacarteMenu>();
		try {
			FileInputStream fis = new FileInputStream("menuData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			mmForOrders.clear();
			mmForOrders = (ArrayList<AlacarteMenu>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			//System.out.println("No menu items found!");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			System.out.format("| Menu ID    | Description                           | Price | Type         |%n");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return mmForOrders;
	}

	/**
	* Method to get the Promotional Package Menu
	*/
	public ArrayList<PromotionalPackage> getPromotionalPackage() {
		ArrayList<PromotionalPackage> ppForOrders = new ArrayList<PromotionalPackage>();
		try {
			FileInputStream fis = new FileInputStream("promoData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ppForOrders.clear();
			ppForOrders = (ArrayList<PromotionalPackage>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			System.out.println("No promotions items found!");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return ppForOrders;
	}
	
	/**
	 * The method to view both the Promotional Menu as well as the Alacarte Menu.
	 * It'll check if there is an existing byte file, if there is it will print the contents if not it will print an empty table.
	 * It is sorted according to their Different Types of Food : Appetizers : Drink : Main : Sides.
	 */
	

	public void viewMenu() {
		try {
			FileInputStream fis = new FileInputStream("menuData");
			ObjectInputStream ois = new ObjectInputStream(fis);

			mm.clear();

			mm = (ArrayList<AlacarteMenu>) ois.readObject();

			String leftAlignFormat = "| %-10s | %-37s | %-5s | %-12s | %n";
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			System.out.format("| Menu ID    | Description                           | Price | Type         |%n");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			
			
			Collections.sort(mm);  
			for (AlacarteMenu item : mm) {
				
				 
				
				System.out.format(leftAlignFormat, item.getMenuName(), item.getMenuDesc(), item.getMenuPrice(),
						item.getMenuType());
			}

			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			ois.close();
			fis.close();
		} catch (IOException e) {

			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			System.out.format("| Menu ID    | Description                           | Price | Type         |%n");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			//System.out.println("No menu items found!");
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}
	/**
	 * Method to view Promotional Packages only from the byte stream.
	 */

	public void viewPromoPackage() {
		try {
			FileInputStream fis = new FileInputStream("promoData");
			ObjectInputStream ois = new ObjectInputStream(fis);

			pp.clear();

			pp = (ArrayList<PromotionalPackage>) ois.readObject();

			String leftAlignFormat = "| %-10s | %-37s | %-5s | %-12s | %n";

		
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			System.out.format("| Package ID | Description                           | Price | Menu ID      |%n");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			Collections.sort(pp);  
			for (PromotionalPackage item : pp) {
				System.out.format(leftAlignFormat, item.getPackageID(), item.getMenuDesc(), item.getMenuPrice(),
						item.getMenuName());
			}
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");

			ois.close();
			fis.close();
		} catch (IOException e) {
			//System.out.println("No promotions found!");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			System.out.format("| Package ID | Description                           | Price | Menu ID      |%n");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}
	
	
	/**
	 * Method to Add AlaCarteMenu into the byte file.
	 * Let User Enter the Menu ID
	 * Let the User enter the MenuType : 1, 2, 3, 4, Appetizers, Main, Sides, Drinks respectively.
	 * 
	 * .
	 */

	public void addAlaCarteMenu() {
		boolean isRunning = true;

		while (isRunning) {
			Scanner scan = new Scanner(System.in);
			int menuTypeNo;
			String menuType = " ";
			// When the user enters 1,2,3,4 - > The type gets automatically added.
			System.out.println(
					"Please Enter Menu Type(-1 to Complete): \n1 : Appetizers \n2 : Main \n3 : Sides \n4 : Drinks");

			menuTypeNo = scan.nextInt();
			
			do {
				if (menuTypeNo == -1) {
					isRunning = false;
					break;
				} else if (menuTypeNo == 1) {
					menuType = "Appetizers";
				} else if (menuTypeNo == 2) {
					menuType = "Main";
				} else if (menuTypeNo == 3) {
					menuType = "Sides";
				} else if (menuTypeNo == 4) {
					menuType = "Drink";
				} else {
					System.out.println("Invalid Input, Try Again..");
					break;
				}

				boolean idChecker = true;
				
				// Retrieve menuData to validate
				File f = new File("menuData");

				do {
					if (f.isFile()) {
						try {
							FileInputStream fis = new FileInputStream("menuData");
							ObjectInputStream ois = new ObjectInputStream(fis);
							mm.clear();
							mm = (ArrayList<AlacarteMenu>) ois.readObject();
							ois.close();
							fis.close();
						} catch (IOException e) {

							System.out.format("+------------+---------------------------------------+-------+--------------+%n");
							System.out.format("| Menu ID    | Description                           | Price | Type         |%n");
							System.out.format("+------------+---------------------------------------+-------+--------------+%n");
							//System.out.println("No menu items found!");
						} catch (ClassNotFoundException c) {
							c.printStackTrace();
						}
						
						System.out.print("Please Enter Menu ID: \nM");
						
						if (idChecker) {
							scan.nextLine();
							menuID = "M" + scan.nextLine();
						}
						else if (!idChecker) {
							menuID = "M" + scan.next();
							scan.nextLine();
						}
						
						for (final AlacarteMenu amItem: mm) {
							if (amItem.getMenuName().equalsIgnoreCase(menuID)) {
								idChecker = false;
								System.out.println("Duplicated ID found!");
								break;
							} else {
								idChecker = true;
							}
						}
					}
					else if (!f.isFile()) {
						System.out.print("Please Enter Menu ID: \nM");
						 scan.nextLine(); 
						menuID = "M" + scan.nextLine();
						idChecker = true;
					}
				}
				while (!idChecker);
				
				System.out.println("Please Enter Menu Item Description: ");
				String menuDesc = scan.nextLine();
				
				System.out.println("Please Enter Menu Item Price: ");
				double menuPrice = scan.nextDouble();

				AlacarteMenu aam = new AlacarteMenu(menuID, menuDesc, menuPrice, menuType);
				mm.add(aam);

				try {
					FileOutputStream fos = new FileOutputStream("menuData");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(mm);
					oos.close();
					fos.close();
				} catch (IOException e) {
					System.out.println("Failed to add menu item.");
				}
			} while (menuTypeNo > 5 && menuTypeNo != -1);
		}
	}
	
	/**
	 * Method to Delete The Menu Item
	 */
	
	public void deleteMenuItem() {
		viewMenu();

		//Clean arraylist so that you can neatly add data from saved file
		mm.clear();
		
		String delID;

		Scanner delSC = new Scanner(System.in);

		System.out.println("Menu ID to Delete : ");
		delID = delSC.next();
		
		try 
		{
			// Get data contents from saved file
			FileInputStream fis = new FileInputStream("menuData");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            mm = (ArrayList<AlacarteMenu>) ois.readObject();
            mm.removeIf(menuItem -> menuItem.getMenuName().equals(delID.toUpperCase()));
            
            ois.close();
            fis.close();
            
            try {
            	FileOutputStream fos = new FileOutputStream("menuData");
				ObjectOutputStream oos =  new ObjectOutputStream(fos);
				oos.writeObject(mm);
				oos.close();
				fos.close();
            }
            catch (IOException e) {
    			System.out.println("Error deleting menu item!");
    			return;
    		}
		} 
		catch (IOException e) {
			//System.out.println("No menu items found!");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			System.out.format("| Menu ID    | Description                           | Price | Type         |%n");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			return;
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}
	
	/**
	 * Method to Edit The Menu Item
	 * Allowing the User to edit which column of the given MENU ID that they would want to edit.
	 * Changing of Menu ID is not allowed.
	 */

	public void editMenuItem() {
		viewMenu();

		// Clean arraylist so that you can neatly add data from saved file
		mm.clear();
		
		String menuEdit;
		int editC;

		System.out.println("Which one do you want to edit?");

		String toChange;
		Double changePrice;
		Scanner menuE = new Scanner(System.in);
		menuEdit = menuE.next();

		try 
		{
			// Get data contents from saved file
			FileInputStream fis = new FileInputStream("menuData");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            mm = (ArrayList<AlacarteMenu>) ois.readObject();
            
            ois.close();
            fis.close();
            
            try {
            	for (int i = 0; i < mm.size(); i++) {
        			if (mm.get(i).getMenuName().equals(menuEdit.toUpperCase())) {
        				System.out.println(
        						"Edit Option \n 1 : Menu Description \n 2 : Menu Price \n 3 : Menu Type ");
        				editC = menuE.nextInt();

        				if (editC == 1) {
        					menuE.nextLine();
        					System.out.println("Change in Menu Description : ");
        					toChange = menuE.nextLine();
        					mm.get(i).setMenuDesc(toChange);
        					break;
        				}
        				else if (editC == 2) {
        					System.out.println("Change in Menu Price : ");
        					changePrice = menuE.nextDouble();
        					mm.get(i).setMenuPrice(changePrice);
        					break;
        				} 
        				else if (editC == 3) {
        					System.out.println("Change in Menu Type : \n1 : Appetizers \n2 : Main \n3 : Sides \n4 : Drinks");
        					changePrice = menuE.nextDouble();
        					if (changePrice == 1) {
        						mm.get(i).setMenuType("Appetizers");
        						break;
        					}
        					else if (changePrice == 2) {
        						mm.get(i).setMenuType("Main");
        						break;
        					}
        					else if (changePrice == 3) {
        						mm.get(i).setMenuType("Sides");
        						break;
        					} 
        					else if (changePrice == 4) {
        						mm.get(i).setMenuType("Drinks");
        						break;
        					}
        				}
        			}
        		}
            	
            	FileOutputStream fos = new FileOutputStream("menuData");
				ObjectOutputStream oos =  new ObjectOutputStream(fos);
				oos.writeObject(mm);
				oos.close();
				fos.close();
            }
            catch (IOException e) {
    			System.out.println("Error editing menu item!");
    			return;
    		}
            ois.close();
            fis.close();
		} 
		catch (IOException e) {
			//System.out.println("No menu items found!");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			System.out.format("| Menu ID    | Description                           | Price | Type         |%n");
			System.out.format("+------------+---------------------------------------+-------+--------------+%n");
			return;
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}

	
	/**
	 * Method to add PromotionalPackage
	 * By default, the promotional packages are 20 percent off total bill.
	 * Loop a scanner to allow the user to add whichever menu item that they want into the promotional package.
	 * Press -1 to terminate.
	 */
	public void addPromotionalPackage() {
		viewMenu();
		
		String menuPPID;
		Scanner ppSC = new Scanner(System.in);
		
		boolean idChecker = true;
		String packageID = "";
		
		File f = new File("promoData");
		
		do 
		{
			if (f.isFile()) {
				try {
					FileInputStream fis = new FileInputStream("promoData");
					ObjectInputStream ois = new ObjectInputStream(fis);
					pp.clear();
					pp = (ArrayList<PromotionalPackage>) ois.readObject();
					ois.close();
					fis.close();
				} catch (IOException e) {
					System.out.println("No promo items found!");
				} catch (ClassNotFoundException c) {
					c.printStackTrace();
				}
				
				System.out.print("Promotional Package ID: \nP");
				
				if (idChecker) {
					String userInput = ppSC.nextLine();
					packageID = "P" + userInput;
				}
				else if (!idChecker) {
					packageID = "P" + ppSC.next();
					ppSC.nextLine();
				}
				
				for (final PromotionalPackage ppItem: pp) {
					if (ppItem.getPackageID().equalsIgnoreCase(packageID)) {
						idChecker = false;
						System.out.println("Duplicated ID found!");
						break;
					} else {
						idChecker = true;
					}
				}
			}
			else if (!f.isFile()) {
				System.out.print("Promotional Package ID: \nP");
				packageID = "P" + ppSC.nextLine();
				idChecker = true;
			}
		} 
		while (!idChecker);

		boolean isRunning = true;
		
		System.out.println("Menu ID to add: ");
		menuPPID = ppSC.next();

		for (int i = 0; i < mm.size();) {
			if (mm.get(i).getMenuName().equals(menuPPID.toUpperCase())) {
				String menuDesc = mm.get(i).getMenuDesc();
				Double menuPrice = mm.get(i).getMenuPrice();
				String menuName = mm.get(i).getMenuName();

				Double promoPrice = Math.round((0.75 * menuPrice) * 100.0) / 100.0;
				
				PromotionalPackage pPackage = new PromotionalPackage(menuName, menuDesc, promoPrice, packageID.toUpperCase());

				i = 0;
				System.out.println("Menu ID to add (-1 to Complete): ");
				menuPPID = ppSC.next();
				pp.add(pPackage);
			}
			else if (menuPPID.equals("-1")) {
				isRunning = false;
				break;
			} else {
				i++;
			}
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("promoData");
			ObjectOutputStream oos =  new ObjectOutputStream(fos);
			oos.writeObject(pp);
			oos.close();
			fos.close();
		}
		catch (IOException e) 
		{
			System.out.println("Failed to add promotions.");
		}
	}

	
	/**
	 * Method to Delete The Promotional Package Items through the Package ID.
	 */
	public void deletePromo() {
		viewPromoPackage();
		
		Scanner dlSC = new Scanner(System.in);
		pp.clear();
		
		boolean checkRemove = true;
		boolean isRunning = true;
		
		while (isRunning) {
			do 
			{
				try {
					// Get data contents from saved file
					FileInputStream fis = new FileInputStream("promoData");
		            ObjectInputStream ois = new ObjectInputStream(fis);
		            
		            pp = (ArrayList<PromotionalPackage>) ois.readObject();
		            
		            ois.close();
		            fis.close();
		            
		            System.out.println("Promotional Package to Delete (-1 to Complete):");
		    		String promoPackID = dlSC.next();
		            
		            checkRemove = pp.removeIf(menuItem -> menuItem.getPackageID().equals(promoPackID.toUpperCase()));
		            
		            if (promoPackID.equals("-1")) {
						isRunning = false;
						break;
		            }
		            
		            if (checkRemove) {
		            	try {
		                	FileOutputStream fos = new FileOutputStream("promoData");
		    				ObjectOutputStream oos =  new ObjectOutputStream(fos);
		    				oos.writeObject(pp);
		    				oos.close();
		    				fos.close();
		                }
		                catch (IOException e) {
		        			System.out.println("Failed to delete promotion!");
		        			return;
		        		}
		            	break;
		            }
		            
		            checkRemove = false;
		            System.out.println("Promo Package not found. Please try again!");
				} 
				catch (IOException e) {
					System.out.println("No promotions to delete!");
					return;
				}
				catch (ClassNotFoundException c) {
					c.printStackTrace();
					return;
				}
			}
			while (!checkRemove);
		}
		
	}
	
	
	/**
	 * Method to Edit The Promotional Item
	 * Allows only to add or delete an item from the promotional packages, one at a time.
	 */

	public void editPromotionMenu() {
		viewPromoPackage();

		String ppE = "";

		Scanner edP = new Scanner(System.in);

		int edChoice;

		boolean doesItExist = false;
		boolean isRunning = true;
		
		while (isRunning) {
			
			System.out.println("Promotional Package to Edit (-1 to Complete): ");
			ppE = edP.next();
			ppE.toUpperCase();
			
			if (ppE.equalsIgnoreCase("-1")) {
				isRunning = false;
				break;
			}
			else {
				do 
				{
					try {
						FileInputStream fis = new FileInputStream("promoData");
						ObjectInputStream ois = new ObjectInputStream(fis);
						pp.clear();
						pp = (ArrayList<PromotionalPackage>) ois.readObject();
						for (PromotionalPackage item : pp) {
							if (item.getPackageID().equals(ppE.toUpperCase())) {
								doesItExist = true;
								break;
							} else {
								doesItExist = false;
							}
						}
						ois.close();
						fis.close();
					} catch (IOException e) {
						//System.out.println("No promotions found!");
						System.out.format("+------------+---------------------------------------+-------+--------------+%n");
						System.out.format("| Package ID | Description                           | Price | Type         |%n");
						System.out.format("+------------+---------------------------------------+-------+--------------+%n");
						return;
					} catch (ClassNotFoundException c) {
						c.printStackTrace();
						return;
					}
					
					if (doesItExist == true) {
						System.out.println("What do you want to do? 1) Add Menu 2) Delete Item Menu (-1 to Complete)");
						edChoice = edP.nextInt();
						if (edChoice == 1) {
							viewMenu();
							
							System.out.println("Menu ID to add: ");
							String menuToAdd = edP.next();

							try 
							{
								FileInputStream fis = new FileInputStream("menuData");
					            ObjectInputStream ois = new ObjectInputStream(fis);
					            mm.clear();
					            mm = (ArrayList<AlacarteMenu>) ois.readObject();
					            ois.close();
					            fis.close();
							} 
							catch (IOException e) {
								//System.out.println("No menu items found!");

								System.out.format("+------------+---------------------------------------+-------+--------------+%n");
								System.out.format("| Menu ID    | Description                           | Price | Type         |%n");
								System.out.format("+------------+---------------------------------------+-------+--------------+%n");
								return;
							}
							catch (ClassNotFoundException c) {
								c.printStackTrace();
								return;
							}
							
							for (MainMenu t : mm) {
								if (t.getMenuName().equals(menuToAdd)) {
									String menuDesc = t.getMenuDesc();
									Double menuPrice = t.getMenuPrice();
									String menuName = t.getMenuName();
									Double promoPrice = Math.round((0.75 * menuPrice) * 100.0) / 100.0;
									PromotionalPackage pPackage = new PromotionalPackage(menuName, menuDesc, promoPrice, ppE.toUpperCase());
									pp.add(pPackage);
									break;
								}
							}
						}
						else if (edChoice == 2) {
							System.out.println("Menu ID To Delete: ");
							String toDelete = edP.next();
							for (int i = 0; i < pp.size(); i++) {
								if ((pp.get(i).getMenuName().equals(toDelete.toUpperCase())
										&& (pp.get(i).getPackageID().equals(ppE.toUpperCase())))) {
									pp.remove(pp.get(i));
								}
							}
						}

						try {
							FileOutputStream fos = new FileOutputStream("promoData");
							ObjectOutputStream oos =  new ObjectOutputStream(fos);
							oos.writeObject(pp);
							oos.close();
							fos.close();
						} 
						catch (IOException e) 
						{
							System.out.println("Failed to edit promotion.");
						}
						
						break;
						
					} else if (!doesItExist) {
						System.out.println("Unable to find promo package entered! \n");
					}
				} while(doesItExist);
			}
		}
	}
}
