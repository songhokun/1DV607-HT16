package model;

import java.util.ArrayList;

public class Member {
	
	private String name;
	private String personalnumber;
	private int memberID;
	private ArrayList<Boat> boatList = new ArrayList<Boat>();

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
		return boatList.size();
	}

	public ArrayList<Boat> getBoatList() {
		return boatList;
	}

	public int getMemberID() {
		return this.memberID;
	}

	public Boat lookUpBoat(int index){
		return this.boatList.get(index);
	}
}