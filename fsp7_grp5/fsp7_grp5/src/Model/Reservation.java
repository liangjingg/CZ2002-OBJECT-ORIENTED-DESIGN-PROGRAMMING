package Model;
/**
Represents a Reservation at our Bistro
@author Liang Jing/Bog
@version 1.0
@since 2019-04-18
*/
import java.io.Serializable;

public class Reservation implements Serializable {
	/**
	* A TableSeat object for AM session
	*/
	TableSeats am; //alrdy an array
	/**
	* A TableSeat object for PM session
	*/
	TableSeats pm; //alrdy an array
	/**
	* Creation of new TableSeats object for each session
	*/
	public Reservation() {
		am = new TableSeats();
		pm = new TableSeats();
	}
	/**
	* Update of the TableSeats object for each session
	* @param am TableSeats object for AM session
	* @param pm TableSeats object for PM session
	*/
	public Reservation(TableSeats am,TableSeats pm){
		this.am = am;
		this.pm = pm;
	}
	/**
	* Get theTableSeats object for AM session
	* @return am TableSeats object for AM session
	*/
	public TableSeats getAMTable (){
		return am;
	}
	/**
	* Set theTableSeats object for AM session
	* @param this Reservation am TableSeats object for AM session
	*/
	public void setAMTable(TableSeats am){
		this.am = am;
	}
	/**
	* Get theTableSeats object for PM session
	* @return am TableSeats object for PM session
	*/
	public TableSeats getPMTable (){
		return pm;
	}
	/**
	* Set theTableSeats object for PM session
	* @param this Reservation pm TableSeats object for PM session
	*/
	public void setPMTable(TableSeats pm){
		this.pm = pm;
	}
	
}
