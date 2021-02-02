/**
Model for AlacarteMenu, handles the data, attributes.
@author Roxas Abby Maurea Imus
@version 1.0
@since 2019-04-18
*/

package Model;

import java.util.ArrayList;
import java.util.List;
import Controller.MainMenuController;

public class PromotionalPackage extends MainMenu implements Comparable {
	
	
	/**
	* Attributes for identifying Package ID
	*/
	String packageID;
	
	/**
	* Constructor for PromotionalPackage that does not get the packageID
	*/
	public PromotionalPackage(String menuName, String menuDesc, Double menuPrice) {
		super(menuName, menuDesc, menuPrice);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	* Method to get packaged ID 
	*/
	public String getPackageID() {
		return packageID;
	}
	
	/**
	* Method to set packaged ID 
	*/

	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}

	
	/**
	* Constructor for PromotionalPackage that included PackageID for creation
	*/
	
	public PromotionalPackage(String menuName, String menuDesc, Double menuPrice, String packageID) {
		super(menuName, menuDesc, menuPrice);
		this.packageID = packageID;
	}

	public void addPromoPackage() {
		
		
		
		
	}
	
	

	/**
	*  Method used from the Comparable interface, to actually allow the system to sort the package ID according to ascending order.
	*/

	@Override
	public int compareTo(Object o) {
		PromotionalPackage typeCompare = (PromotionalPackage) o;
		 return getPackageID().compareTo(typeCompare.getPackageID());
	}
	


	
	//program to an interface, not to a direct class implementation.
	
	
	
	
	
	
}
