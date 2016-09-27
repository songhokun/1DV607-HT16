package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.Boat.BoatType;

/**
 * @author songhokun
 *
 */
public class Registry {

	private ArrayList<Member> registry = new ArrayList<Member>();
	private int maxID = 0;

	public Registry() throws FileNotFoundException {
		new ReadFile(this);
	}

	public Registry(ArrayList<Member> memberList) {
		this.registry = memberList;
	}

	public ArrayList<Member> getRegistry() {
		return registry;
	}

	public int getMaxID() {
		return maxID;
	}

	public void setMaxID(int maxID) {
		this.maxID = maxID;
	}
	
	public void createMember(String name, String personalNumber) {
		this.registry.add(new Member(name, personalNumber, ++maxID));
	}

	public void updateMember(Member m) {
		for (Member member : registry)
			if (member.getMemberID() == m.getMemberID()) {
				member = m;
				break;
			}
	}
	
	public void deleteMember(Member m) {
		this.registry.remove(m);
	}
	
	public void registerBoat(Member m, double length, BoatType type) {
		m.getBoatdata().add(new Boat(length, type));
	}
		
	public void updateBoat(Member m, Boat boat) {	
		int index = m.getBoatdata().indexOf(boat);
		m.getBoatdata().get(index).setLength(boat.getLength());
		m.getBoatdata().get(index).setType(boat.getType());
		
	}
	
	public void deleteBoat(Member m, Boat boat) {
		m.getBoatdata().remove(boat);
		
	}
	/********************Below methods I am not using**************************************************************************/
	
	
	public void deleteMember(int ID) {
		this.registry.remove(lookUpMember(ID));
	}

	public void updateMember(Member inMember, String name, String personalNumber) {
		if (!name.isEmpty())
			inMember.setName(name);
		if (!personalNumber.isEmpty())
			inMember.setPersonalnumber(personalNumber);
	}

	private Member lookUpMember(String personalNumber) {
		for (Member m : this.registry) {
			if (m.getPersonalnumber().equals(personalNumber))
				return m;
		}
		return null;
	}

	public Member lookUpMember(int ID) {
		for (Member m : this.registry) {
			if (m.getMemberID() == ID)
				return m;
		}
		return null;
	}

	public boolean isMemberExist(String personalNumber) {
		return lookUpMember(personalNumber) != null;
	}

	public boolean isMemberExist(int ID) {
		return lookUpMember(ID) != null;
	}
}