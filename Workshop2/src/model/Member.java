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
	
	public Member(String name, String personalnumber, int memberID, ArrayList<Boat> list) {
		this.name = name;
		this.personalnumber = personalnumber;
		this.memberID = memberID;
		this.boatdata = list;
	}
	
	public Member(String name, String personalnumber, ArrayList<Boat> list) {
		this.name = name;
		this.personalnumber = personalnumber;
		this.boatdata = list;
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
	// TODO : Do we still need an unique ID even when social security number is
	// unique enough?

	public int getMemberID() {
		return this.memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
}