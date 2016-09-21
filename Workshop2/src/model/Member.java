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
	private String personnumber;
	private int memberID;
	private ArrayList<Boat> boatdata = new ArrayList<Boat>();

	public Member() {
	
	}

	public Member(String name, String personnumber) {
		this.name = name;
		this.personnumber = personnumber;
	}
	
	public Member(String name, String personnumber, int memberID) {
		this.name = name;
		this.personnumber = personnumber;
		this.memberID = memberID;
	}
	
	public Member(String name, String personnumber, int memberID, ArrayList<Boat> list) {
		this.name = name;
		this.personnumber = personnumber;
		this.memberID = memberID;
		this.boatdata = list;
	}
	
	public Member(String name, String personnumber, ArrayList<Boat> list) {
		this.name = name;
		this.personnumber = personnumber;
		this.boatdata = list;
	}

	public String getName() {
		return name;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
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