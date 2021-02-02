/**
Model for Main Menu, handles the attributes, getters and setters, is also the class where Alacarte and PromotionalPackage inherits from..
@author Roxas Abby Maurea Imus
@version 1.0
@since 2019-04-18
*/
package Model;

import java.io.Serializable;
import java.util.Comparator;

//Variables / constructors // getters and setters.
public class MainMenu implements Serializable {
	
	
	
	/**
	* Attribute to menuName
	*/
	private String menuName;
	
	/**
	* Attribute to menuDesc
	*/
	private String menuDesc;
	//try changes
	
	/**
	* Attribute to menuPrice
	*/
	private Double menuPrice;
	
	
	/**
	* Method to get menu description
	*/
	public String getMenuDesc() {
		return menuDesc;
	}
	
	/**
	* Method to set menu description
	*/

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	
	/**
	* Constructor for MainMenu
	*/

	public MainMenu(String menuName, String menuDesc, Double menuPrice) {
		super();
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.menuPrice = menuPrice;
	}
	

	/**
	* Method to get menu name
	*/

	public String getMenuName() {
		return menuName;
	}
	/**
	* Method to set menu name
	*/

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	
	/**
	* Method to get menu price
	*/
	public Double getMenuPrice() {
		return menuPrice;
	}

	
	/**
	* Method to set menu name
	*/
	public void setMenuPrice(Double menuPrice) {
		this.menuPrice = menuPrice;
	}

	

	

}
