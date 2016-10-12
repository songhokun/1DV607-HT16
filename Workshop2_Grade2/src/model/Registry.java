package model;

import java.text.ParseException;
import java.util.ArrayList;

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

	public void createMember(String name, String personalNumber) throws ParseException {
		//incrementing maxID generates a new unique member ID.
		this.memberList.add(new Member(name, personalNumber, ++maxID));
	}
	/**
	 * 
	 * @param inMember
	 * @param name ; if it is "", only personalNumber will be updated.
	 * @param personalNumber ; if it is "", only name will be updated.
	 * @throws ParseException 
	 */
	public void updateMember(Member inMember, String name, String personalNumber) throws ParseException {
		if (!name.isEmpty())
			inMember.setName(name);
		if (!personalNumber.isEmpty())
			inMember.setPersonalnumber(personalNumber);
	}

	public void deleteMember(Member m) {
		this.memberList.remove(m);
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