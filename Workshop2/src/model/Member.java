package model;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Member {

	private String name;
	private String personalnumber;
	private int memberID;
	private ArrayList<Boat> boatList = new ArrayList<Boat>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd");

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

	public int getBirthYear() {
		return LocalDate.parse(personalnumber.substring(0, 8), formatter).getYear();
	}

	public int getBirthMonth() {
		return LocalDate.parse(personalnumber.substring(0, 8), formatter).getMonthValue();
	}

	public int getBirthDate() {
		return LocalDate.parse(personalnumber.substring(0, 8), formatter).getDayOfMonth();
	}

	public int getAge() {
		return Year.now().getValue() - getBirthYear();
	}

	public Boat lookUpBoat(int index) {
		return this.boatList.get(index);
	}
}