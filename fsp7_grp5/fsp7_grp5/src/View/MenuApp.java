/**
The Boundary Class for MenuApp, a submenu of AppClass
@author Roxas Abby Maurea Imus
@version 1.0
@since 2019-04-18
*/
package View;
import java.util.Scanner;

import Controller.MainMenuController;

public class MenuApp {
	
	public void menuListing(boolean manager) {
		
		/**
		* The number choice that user is going to enter.
		* According to the different choices input by the user, different functionalities.
		*/
		int menuC;
		
		if(manager==true) {
			//Print menu item's menu
			System.out.format("+--------------------------------------------------+%n");
			System.out.format("| Menu Item's functionalities                      |%n");
			System.out.println("|(1) View Existing Menu                            |");
			System.out.format("+--------------------------------------------------+%n");
			System.out.format("| A la Carte Menu                                  |%n");
			System.out.format("+--------------------------------------------------+%n");
			System.out.println("|(2) View all A la Carte Menu                      |");
			System.out.println("|(3) Add New Menu Item                             |");
			System.out.println("|(4) Edit Menu Item                                |");
			System.out.println("|(5) Delete Menu Item                              |");
			System.out.format("+--------------------------------------------------+%n");
			System.out.format("| Promotional Package                              |%n");
			System.out.format("+--------------------------------------------------+%n");
			System.out.println("|(6) View all Promotional Packages          	   |");
			System.out.println("|(7) Add New Promotional Packages                  |");
			System.out.println("|(8) Edit Promotional Packages                     |");
			System.out.println("|(9) Delete Promotional Packages      	    	   |");
			System.out.format("+--------------------------------------------------+%n");
			System.out.println("|(10) Back to Main Menu Selection                  |");
			System.out.format("+--------------------------------------------------+%n");
		}else {
			//Print menu item's menu
			System.out.format("+--------------------------------------------------+%n");
			System.out.format("| Menu Item's functionalities                      |%n");
			System.out.println("|(1) View Existing Menu                            |");
			System.out.println("|(2) View all A la Carte Menu                      |");
			System.out.println("|(6) View all Promotional Packages          	   |");
			System.out.format("+--------------------------------------------------+%n");
			System.out.println("|(10) Back to Main Menu Selection                  |");
			System.out.format("+--------------------------------------------------+%n");
		}
		
		MainMenuController mmc = new MainMenuController();
		
		do {
			Scanner menuSC = new Scanner(System.in);
			System.out.print("Enter your choice: \n");
			menuC = menuSC.nextInt();
		
		if(menuC == 1) { //Print whole menu
			mmc.viewMenu();
			mmc.viewPromoPackage();
		}
		/** MAIN MENU **/
		else if(menuC == 2) { //View all ala carte Menu
			mmc.viewMenu();
		}else if(menuC == 3) { //add ala carte Menu
			if(manager==true) {
				mmc.addAlaCarteMenu();
			}else{
				System.out.println("You are not allowed to change the menu");
			}
		}else if(menuC == 4) { //edit ala carte menu
			if(manager==true) {
				mmc.editMenuItem();
			}else{
				System.out.println("You are not allowed to change the menu");
			}
		}else if(menuC == 5) { //delete ala carte menu
			if(manager==true) {
				mmc.deleteMenuItem();
			}else{
				System.out.println("You are not allowed to change the menu");
			}
		}
		/** PROMOTIONAL PACKAGES **/
		else if(menuC == 6) {
			mmc.viewPromoPackage();
		}else if(menuC == 7) {
			if(manager==true) {
				mmc.addPromotionalPackage();
			}else{
				System.out.println("You are not allowed to change the menu");
			}
		}else if(menuC == 8) {
			if(manager==true) {
				mmc.editPromotionMenu();
			}else{
				System.out.println("You are not allowed to change the menu");
			}
		}else if(menuC == 9) {
			if(manager==true) {
				mmc.deletePromo();
			}else{
				System.out.println("You are not allowed to change the menu");
			}
		}else if(menuC == 10) {
			break;	
		}else if(menuC > 10 && menuC != 10){
			System.out.println("Invalid Input, Try Again... \n");
		}
	}while(menuC != 10);
		
	}
}
