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
	
	private String memberID;
	
	private ArrayList<Boat> boatdata = new ArrayList<Boat>();
	
	
	public Member(){
	}
	
	public Member(String name, String personnumber){
		this.name=name;
		this.personnumber=personnumber;
		this.memberID=this.personnumber;
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
	//TO-DO : Do we still need an unique ID even when social security number is unique enough? 
	
	public String getMemberID(){
		return this.memberID;
	}
	public void setMemberID(String memberID){
		this.memberID=memberID;
	}
}
