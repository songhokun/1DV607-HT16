/**
 * 
 */
package model;

import java.util.ArrayList;
/**
 * @author songhokun
 *
 */
public class Member {
	private String name;
	private String personalnumber;
	private int memberID;
	private ArrayList<Boat> boatdata = new ArrayList<Boat>();

	public Member() {

	}

	public Member(String name, String personalnumber) {
		this.name = name;
		this.personalnumber = personalnumber;
	}

	public Member(String name, String personalnumber, int memberID) {
		this.name = name;
		this.personalnumber = personalnumber;
		this.memberID = memberID;
	}

	public String getName() {
		return name;
	}

	public String getPersonalnumber() {
		return personalnumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPersonalnumber(String personalnumber) {
		this.personalnumber = personalnumber;
	}

	public int getNumberOfBoats() {
		return boatdata.size();
	}

	public ArrayList<Boat> getBoatdata() {
		return boatdata;
	}

	public void setBoatdata(ArrayList<Boat> boatdata) {
		this.boatdata = boatdata;
	}

	public int getMemberID() {
		return this.memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	
	public Boat lookUpBoat(int index){
		return this.getBoatdata().get(index-1);
	}

	/*
	public void updateBoat(Boat boat) {	
		int index = this.boatdata.indexOf(boat);
		this.boatdata.get(index).setLength(boat.getLength());
		this.boatdata.get(index).setType(boat.getType());
	}
	
	public void deleteBoat(Boat boat) {
		this.boatdata.remove(boat);
	}
	public void registerBoat(double length, BoatType type) {
		this.boatdata.add(new Boat(length, type));
	}
		public void updateBoat(double length, BoatType type, int index) {
		if (length != -1)
			this.getBoatdata().get(index - 1).setLength(length);
		if (type != null)
			this.getBoatdata().get(index - 1).setType(type);
	}
	public void deleteBoat(int index) {
		this.getBoatdata().remove(index - 1);
	}
	*
	*/

}