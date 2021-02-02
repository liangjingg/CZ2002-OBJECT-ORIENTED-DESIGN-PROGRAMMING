package Model;
/**
Randomly generate the list of the staffs and contain the generate list of staffs working at our Bistro. 
@author Liang Jing/Karen
@version 1.0
@since 2019-04-18
*/
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class StaffList {
	/**
	* The maximum number of staffs that will be generated
	*/
	private int max=20;
	/**
	* The arraylist of the Staffs created
	*/
	private Staff[] staffList  = new Staff[max];
	/**
	* The array of random first names to be generated later on
	*/
	private String[] firstNameList = {"Cynthia","Ashwin","Gary","Bonita","Carla","Kevin","Eliza",
										"James","Anna","Anastasia","Joseph","Edward","Jean","Carlis",
										"Daria","Gregory","Berlin","Louis","Jana","Reza"};
	/**
	* The array of random last names to be generated later on
	*/
	private String[] lastNameList = {"Yeo","Mohammad","Tay","Cruz","Lewis","Lim","Chia","Chua","Ong",
									"Villa","Teo","Jacobs","Ang","Wong","Nur","Wong","Brown","Evans"};
	/**
	* The ID number of the Staff generated
	*/
	int staffID;
	/**
	* The first name and last name of the Staff generated
	*/
	String firstname, lastname;
	/**
	* The role of the Staff generated (w/c/m)
	*/
	char role;
	/**
	* The gender of the Staff generated (m/f)
	*/
	char gender;
	/**
	* A Random object
	*/
	Random rand = new Random();

	/**
	* Creates a new StaffList with 20 staffs randomly generated.
	*/
	public StaffList() {createStaff();}
	
	/**
	* Gets the Staff of the Staff ID entered.
	* @param id This Staff's id 
	* @return this Staff
	*/
	public Staff getStaff(int id){
		return staffList[id-1];
	}
	/**
	* Randomly generate the role, gender, first name and last name
	* of each of the staffs. Staff ID will be given accordingly to order.
	* Staff ID 1 to 3 are designated Managers
	* Staff ID 4 to 6 are designated Chefs
	* Staff ID 6 to 20 are designated Waiters.
	*/
	public void createStaff() {
	    for(int i=0;i<max;i++) {
	      staffID=(i+1);
	      if(staffID > 0 && staffID < 4) {
	        role='M';//manager
	      }else if(staffID > 3 && staffID < 6) {
	        role='C';//chef
	      }else if (staffID > 5) { 
	        role='W';//waiter
	      }
	      int f=randomFirstNames();
	      int l=randomLastNames();
	      if(randomGender()==0) {
	        gender='M';
	      }else{
	        gender='F';
	      }
	      staffList[i]=new Staff(staffID,firstNameList[f],lastNameList[l],gender,role);
	    }
	  }
	/**
	* Randomly generate a index of the array of first names
	* @return rand.nextInt(firstNameList.length)
	*/
	public int randomFirstNames() {
		return rand.nextInt(firstNameList.length);
	}
	/**
	* Randomly generate a index of the array of last names
	* @return return rand.nextInt(lastNameList.length)
	*/
	public int randomLastNames() {
		return rand.nextInt(lastNameList.length); //0-17, 18=size
	}
	/**
	* Randomly generate a number between 0-1
	* @return return rand.nextInt(2)
	*/
	public int randomGender() {
		return rand.nextInt(2); //0-1
	}
	/**
	* Randomly generate a number between 0-5
	* @return rand.nextInt(6)
	*/
	public int randomRole() {
		return rand.nextInt(6); //0-5
	}
	/**
	* Check if the Staff is manager or not
	* @return manager if this Staff's role is manager or not
	*/
	public boolean manager() {
		System.out.println("Please enter your StaffID: ");
		Scanner scan = new Scanner(System.in);
		int staffID=scan.nextInt();
		boolean manager=false;
		
		for(int i=0;i<max;i++) {
			if(staffList[i].getStaffID()==staffID && staffList[i].getRole()=='M') {
				manager=true;
			}
		}
		return manager;
	}
	/**
	* Gets the staffID
	* @param staff_index This Staff's index in the StaffList 
	* @return staffList[staff_index].getStaffID() this Staff's ID.
	*/
	public int getStaffID(int staff_index) {
		return staffList[staff_index].getStaffID();
	}
	/**
	* Prints all the Staff's details: Staff ID, Staff's first name and last name, Staff's gender and Staff's role
	*/
	public void viewStaff() {
		for(int i=0;i<max;i++) {
			System.out.println("Staff ID: "+staffList[i].getStaffID()+", Name: "+staffList[i].getFirstName()+" "+staffList[i].getLastName()+
					", Gender: "+staffList[i].getGender()+", Role: "+staffList[i].getRole());
		}
	}
	/**
	* Gets all the Staff's details: Staff ID, Staff's first name and last name, Staff's gender and Staff's role
	* @return details the whole list of Staff details.
	*/
	public String printStaff() {
		String details="";
		for(int i=0;i<max;i++) {
			details+=("Staff ID: "+staffList[i].getStaffID()+" | Name: "+staffList[i].getFirstName()+" "+staffList[i].getLastName()+
					" | Gender: "+staffList[i].getGender()+" | Role: "+staffList[i].getRole()+"\n");
		}
		return details;
	}
}
