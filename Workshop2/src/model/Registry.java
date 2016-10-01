package model;

import java.util.ArrayList;
import model.Boat.BoatType;

public class Registry {

	private ArrayList<Member> memberList;
	private ReadWriteFile readWriteFile;
	private int maxID = 0;

	public Registry() throws Exception{
		readWriteFile = new ReadWriteFile();
		memberList = readWriteFile.readFile();
		maxID = readWriteFile.getMaxID();
	}

	public ArrayList<Member> getMemberList() {
		return memberList;
	}
	
	public void createMember(String name, String personalNumber) {
		this.memberList.add(new Member(name, personalNumber, ++maxID));
	}

	public void updateMember(Member inMember, String name, String personalNumber) {
		if (!name.isEmpty())
			inMember.setName(name);
		if (!personalNumber.isEmpty())
			inMember.setPersonalnumber(personalNumber);
	}

	public void deleteMember(Member m) {
		this.memberList.remove(m);
	}

	public void registerBoat(Member m, double length, BoatType type) {
		m.getBoatList().add(new Boat(length, type));
	}

	public void updateBoat(double length, BoatType type, Boat boat) {
		if (length != 0)
			boat.setLength(length);
		if (type != null)
			boat.setType(type);
	}

	public void deleteBoat(Member m, Boat boat) {
		m.getBoatList().remove(boat);
	}

	public Member lookUpMember(int ID) {
		for (Member m : this.memberList) {
			if (m.getMemberID() == ID)
				return m;
		}
		return null;
	}

	public void saveRegistry() {
		readWriteFile.writeFile(memberList, maxID);
	}
}