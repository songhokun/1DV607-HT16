/**
 * 
 */
package model;

import java.util.ArrayList;
import model.Boat.BoatType;

/**
 * @author songhokun
 *
 */
public class Member {
	private String name;
	private String personalnumber;
	private int memberID;
	private ArrayList<Boat> boatdata = new ArrayList<Boat>();
	private ArrayList<IMemberUpdateObserver> subscribers = new ArrayList<IMemberUpdateObserver>();

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
	// TODO : Do we still need an unique ID even when social security number is
	// unique enough?

	public int getMemberID() {
		return this.memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public void addSubscriber(IMemberUpdateObserver sub) {
		subscribers.add(sub);
	}

	public void registerBoat(double length, int type) {
		this.getBoatdata().add(new Boat(length, BoatType.values()[type - 1]));
		for (IMemberUpdateObserver obs : subscribers)
			obs.memberInformationChanged();
	}

	public void updateBoat(double length, int type, int index) {
		if (length != -1)
			this.getBoatdata().get(index - 1).setLength(length);
		if (type != -1)
			this.getBoatdata().get(index - 1).setType(BoatType.values()[type - 1]);

		for (IMemberUpdateObserver obs : subscribers)
			obs.memberInformationChanged();
	}

	public void deleteBoat(int index) {
		this.getBoatdata().remove(index - 1);
		for (IMemberUpdateObserver obs : subscribers)
			obs.memberInformationChanged();
	}
}