package model;

import java.io.IOException;
import java.text.ParseException;
import java.time.Month;
import java.util.ArrayList;

import model.Boat.BoatType;

public class Registry {

	private ArrayList<Member> memberList;
	private ReadWriteFile readWriteFile;
	private int maxID = 0;
	public enum SearchMode {BY_NAME, OLD_THAN_AGE, BY_MONTH, BY_BOAT_TYPE, GRT_THAN_BOAT_LENGTH};

	public Registry() {
		memberList = new ArrayList<Member>();
	}

	public void readFiles() throws Exception {
		readWriteFile = new ReadWriteFile();
		memberList = readWriteFile.readFile();
		maxID = readWriteFile.getMaxID();
	}

	public ArrayList<Member> getMemberList() {
		return new ArrayList<Member>(memberList);
	}

	public void registerMember(String name, String personalNumber) throws ParseException {
		this.memberList.add(new Member(name, personalNumber, ++maxID)); // incrementing maxID generates a new unique member ID.
	}

	/**
	 * @param inMember
	 * @throws ParseException
	 */
	public void updateMember(Member inMember, String name, String personalNumber) throws ParseException {
		inMember.setName(name);
		inMember.setPersonalnumber(personalNumber);
	}

	public void deleteMember(Member m) {
		this.memberList.remove(m);
	}

	/**
	 * The method is used in view side to select a member based on console input
	 * 
	 * @param ID of member in integer form
	 * @return a member from present member list. null if member does not exist
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
	 * 
	 * @throws IOException
	 */
	public void saveRegistry() throws IOException {
		// This maxID in argument is current maximum value of user's ID. This is
		// because userID is generated based on increment of maxID.
		readWriteFile.writeFile(memberList, maxID);
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
					if (b.getType() == type) {
						foundMembers.add(m);
						break;
					}
				}
			}
		} else if (o instanceof Double) {
			double d = (double) o;
			for (Member m : memberList) {
				for (Boat b : m.getBoatList()) {
					if (b.getLength() > d) {
						foundMembers.add(m);
						break;
					}
				}
			}
		}
		return foundMembers;
	}

	public ArrayList<Member> complexSearch(ArrayList<Member> firstList, ArrayList<Member> secondList, boolean isAnd) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		if (isAnd) {
			for (Member m : firstList) {
				if (secondList.contains(m))
					foundMembers.add(m);
			}
		} else {
			foundMembers.addAll(firstList);
			for (Member m : secondList) {
				if (!foundMembers.contains(m))
					foundMembers.add(m);
			}
		}
		return foundMembers;
	}
}