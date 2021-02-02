=================================INTRODUCTION=================================
This application is divided into Model, Controller and View. Model contains 
all the classes while controller is the logic for the main features we have.
View contain different menu printing. Reservation, menu item and order have
its individual menu. 
To run this application, run the appClass class under the view folder.


=====================================GUIDE=====================================
The main application has 6 sections, while each sections might have sub sections.
To check which staff id is manager, please open the staff.txt file.
eg. Staff ID: 1 | Name: Daria Ang | Gender: M | Role: M - is a manager
eg. Staff ID: 6 | Name: Cynthia Yeo | Gender: M | Role: W - is a waiter 

==1.MENU ITEMS==
User have to enter their staff ID as it checks if the user is manager or not.
If they are not manager, they are limited to certain actions only.
While manager is able to, view, add, edit and delete both individual menu items
and promotional packages.

==2.TABLE AVAILABLITY==
It will show all the table unoccupied at that moment.

==3.ORDER==
Users are able to view, create, add or remove order in this section.

==4.RESERVATION==
Users are able to view, create and remove reservation in this section.

==5.INVOICE==
User will have to enter the tableid and confirm that they want to bill it.
After the invoice is shown, the order will be removed and the table's status
will change to unoccupied.

==6.SALES==
User have to enter their staff ID as it checks if the user is manager or not.
If they are not manager, they are not able to check the sales report.
In the sales section managers are able to check the daily, monthly and 
yearly sales.