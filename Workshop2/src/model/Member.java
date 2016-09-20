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
	
	
	public Member(){
	}
	
	public Member(String name, String personnumber){
		this.name=name;
		this.personnumber=personnumber;
	}
	public Member(String name, String personnumber, int ID){
		this.name=name;
		this.personnumber=personnumber;
		this.memberID = ID;
	}
	
	
	public String getName() {
		return name;
	}
	public String getPersonnumber() {
		return personnumber;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
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
}
