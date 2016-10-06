package model;

import java.util.ArrayList;
import model.Boat.BoatType;

public class Registry {

	private ArrayList<Member> memberList;
	private ReadWriteFile readWriteFile;
	//maxID
	private int maxID = 0;
	
	
	public Registry() throws Exception {
		readWriteFile = new ReadWriteFile();
		memberList = readWriteFile.readFile();
		maxID = readWriteFile.getMaxID();
	}

	public ArrayList<Member> getMemberList() {
		return new ArrayList<Member>(memberList);
	}

	public void createMember(String name, String personalNumber) {
		//incrementing maxID generates a new unique member ID.
		this.memberList.add(new Member(name, personalNumber, ++maxID));
	}
	/**
	 * 
	 * @param inMember
	 * @param name ; if it is "", only personalNumber will be updated.
	 * @param personalNumber ; if it is "", only name will be updated.
	 */
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
	/**
	 * Updates given boat as an argument.
	 * @param length; 0 when user does not update length.
	 * @param type; null when user does not update boat type
	 * 
	 * @param boat
	 */
	public void updateBoat(double length, BoatType type, Boat boat) {
		if (length != 0)
			boat.setLength(length);
		if (type != null)
			boat.setType(type);
	}

	public void deleteBoat(Member m, Boat boat) {
		m.getBoatList().remove(boat);
	}
	/**
	 * The method is used in view side to select a member based on console input
	 * 
	 * @param ID of member in integer form
	 * @return a member from present member list.
	 * 			null if member does not exist
	 */
	public Member lookUpMember(int ID) {
		for (Member m : this.memberList) {
			if (m.getMemberID() == ID)
				return m;
		}
		return null;
	}
	
	/**
	 * Saves the current data into file.
	 * Note: called on console's quit sequence
	 * Note: on GUI it is called whenever change is occurred. 
	 */
	public void saveRegistry() {
		//This maxID in argument is current maximum value of user's ID.
		//This is because userID is generated based on increment of maxID.
		readWriteFile.writeFile(memberList, maxID);
	}
}