/**
Model for AlacarteMenu, handles the data, attributes.
@author Roxas Abby Maurea Imus
@version 1.0
@since 2019-04-18
*/

package Model;
import java.io.Serializable;

public class AlacarteMenu extends MainMenu implements Comparable {
	
	/**
	* Attribute to identify menuType
	*/
	private String menuType;
	
	
	/**
	* Getter Method for MenuType
	*/
	
	public String getMenuType() {
		return menuType;
	}
	
	/**
	* Setter Method for MenuType
	*/

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	/**
	* Constructor for Alacarte which inherits MainMenu.
	*/
	
	public AlacarteMenu(String menuName, String menuDesc, Double menuPrice, String menuType) {
		super(menuName, menuDesc, menuPrice);
		this.menuType = menuType;
		// TODO Auto-generated constructor stub
	}

	
	/**
	*  Method used from the Comparable interface, to actually allow the system to sort the menu according to menuType (A-Z) : Appetizer to Drinks.
	*/
	@Override
	public int compareTo(Object o) {
		AlacarteMenu typeCompare = (AlacarteMenu) o;
		 return getMenuType().compareTo(typeCompare.getMenuType());
	
	}
	       
}