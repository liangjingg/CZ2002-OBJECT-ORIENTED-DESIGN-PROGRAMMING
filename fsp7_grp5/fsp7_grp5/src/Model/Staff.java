package Model;
/**
Represents a staff working at our Bistro
@author Liang Jing/Karen
@version 1.0
@since 2019-04-18
*/
public class Staff {
	/**
	* The ID number of this Staff
	*/
	int staffID;
	/**
	* The first and last name of this Staff
	*/
	String firstname, lastname;
	/**
	* The gender of this Staff
	*/
	char gender;
	/**
	* The role of this Staff (chef-c, manager-m & waiter-w)
	*/
	char role;
	
	public Staff() {}
	
	/**
	* Creates a new Staff with the given variables.
	* The variables should include staffID, first name, last name
	* gender and the role.
	*/
	public Staff(int staffID, String firstname, String lastname, char gender, char role) {
		this.staffID=staffID;
		this.firstname=firstname;
		this.lastname=lastname;
		this.gender=gender;
		this.role=role;
	}
	/**
	* Gets the staff ID of this Staff.
	* @return this Staff's ID.
	*/
	public int getStaffID() { //0-19
		return staffID;
	}
	/**
	* Gets the first name of this Staff.
	* @return this Staff's first name.
	*/
	public String getFirstName() {
		return firstname;
	}
	/**
	* Gets the last name of this Staff.
	* @return this Staff's last name.
	*/
	public String getLastName() {
		return lastname;
	}
	/**
	* Gets the gender of this Staff.
	* @return this Staff's gender.
	*/
	public char getGender() {
		return gender;
	}
	/**
	* Gets the role of this Staff.
	* @return this Staff's role.
	*/
	public char getRole() {
		return role;
	}
	
}
