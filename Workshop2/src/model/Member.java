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

	public Member(String name, String personalnumber) throws Exception{
		this.setName(name);
		this.setPersonalnumber(personalnumber);
	}

	public Member(String name, String personalnumber, int memberID) throws Exception {
		this.setName(name);
		this.setPersonalnumber(personalnumber);
		this.memberID = memberID;
	}

	public String getName() {
		return name;
	}

	public String getPersonalnumber() {
		return personalnumber;
	}

	public void setName(String name) throws Exception {
		if(name.matches(".*\\d+.*"))
			throw new Exception("Do not contain numbers!");
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
		return this.getBoatdata().get(index);
	}
}