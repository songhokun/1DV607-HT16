package model;

import java.text.ParseException;
import java.time.Month;
import java.util.ArrayList;
import model.Boat.BoatType;

public class Registry {

	private ArrayList<Member> memberList;
	private ReadWriteFile readWriteFile;
	private int maxID = 0;
	public enum SimpleSearchMode {BY_NAME, OLD_THAN_AGE, BY_MONTH, BY_BOAT_TYPE, GRT_THAN_BOAT_LENGTH};
	
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

	public ArrayList<Member> simpleSearch(Object o) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		if (o instanceof String) {
			String name = (String) o;
			for (Member m : memberList) {
				if (m.getName().length() >= name.length()) {
					String substring = m.getName().substring(0, name.length());
					if (substring.equalsIgnoreCase(name))
						foundMembers.add(m);
				}
			}
		} else if (o instanceof Integer) {
			int age = (Integer) o;
			for (Member m : memberList) {
				if (m.getAge() > age)
					foundMembers.add(m);
			}
		} else if (o instanceof Month) {
			Month month = (Month) o;
			for (Member m : memberList) {
				if (m.getBirthMonth() == month.getValue())
					foundMembers.add(m);
			}
		} else if (o instanceof BoatType) {
			BoatType type = (BoatType) o;
			for (Member m : memberList) {
				for (Boat b : m.getBoatList()) {
					if (b.getType() == type)
						if(!foundMembers.contains(m))
							foundMembers.add(m);
				}
			}
		} else if (o instanceof Double) {
			double d = (double) o;
			for (Member m : memberList) {
				for (Boat b : m.getBoatList()) {
					if (b.getLength() > d)
						if(!foundMembers.contains(m))
							foundMembers.add(m);
				}
			}
		}
		return foundMembers;
	}

	public ArrayList<Member> complexSearch(ArrayList<Member> cp1, ArrayList<Member> cp2, boolean isAnd) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		if (isAnd) {
			for (int i = 0; i < cp1.size(); i++) {
				if (cp2.indexOf(cp1.get(i)) != -1)
					foundMembers.add(cp1.get(i));
			}
		} else {
			for (Member m : cp1) {
				if (foundMembers.indexOf(m) == -1)
					foundMembers.add(m);

			}
			for (Member m : cp2) {
				if (foundMembers.indexOf(m) == -1)
					foundMembers.add(m);

			}
		}
		return foundMembers;
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