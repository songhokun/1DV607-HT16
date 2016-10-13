package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;

import model.Boat.BoatType;

public class Member {

	private String name;
	private String personalnumber;
	private int memberID;
	private ArrayList<Boat> boatList = new ArrayList<Boat>();


	public Member(String name, String personalnumber, int memberID) throws ParseException {
		this.name = name;
		setPersonalnumber(personalnumber);
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

	public void setPersonalnumber(String personalnumber) throws ParseException {
		String pn = personalnumber.substring(0, 8);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		df.setLenient(false);
		df.parse(pn);
		this.personalnumber = personalnumber;
	}


	public int getNumberOfBoats() {
		return boatList.size();
	}

	public ArrayList<Boat> getBoatList() {
		return new ArrayList<Boat>(boatList);
	}

	public int getMemberID() {
		return this.memberID;
	}

	public int getBirthYear() {
		return Integer.parseInt(personalnumber.substring(0, 4));
		//return LocalDate.parse(personalnumber.substring(0, 8), formatter).getYear();
	}

	public int getBirthMonth() {
		return Integer.parseInt(personalnumber.substring(4,6));
		//return LocalDate.parse(personalnumber.substring(0, 8), formatter).getMonthValue();
	}

	public int getBirthDate() {
		return Integer.parseInt(personalnumber.substring(6,8));
		//return LocalDate.parse(personalnumber.substring(0, 8), formatter).getDayOfMonth();
	}

	public int getAge() {
		return Year.now().getValue() - getBirthYear();
	}

	public void registerBoat(double length, BoatType type) {
		this.boatList.add(new Boat(length, type));
	}
	public void updateBoat(double length, BoatType type, Boat boat) {
		if (length != 0)
			boat.setLength(length);
		if (type != null)
			boat.setType(type);
	}

	public void deleteBoat(Boat boat) {
		this.boatList.remove(boat);
	}
	public Boat lookUpBoat(int index) {
		return this.boatList.get(index);
	}
}
