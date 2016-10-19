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
	public enum SearchMode {BY_NAME, OLDER_THAN_AGE, YOUNGER_THAN_AGE, EQUAL_TO_AGE, 
							BY_MONTH, BY_BOAT_TYPE, GREATER_THAN_BOAT_LENGTH, SMALLER_THAN_BOAT_LENGTH, 
							EQUAL_TO_BOAT_LENGTH};				
	public enum SearchOperator {AND, OR};

	
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
		//Incrementing maxID generates a new unique ID
		this.memberList.add(new Member(name, personalNumber, ++maxID));
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
	 * @param ID
	 *            of member in integer form
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

	public ArrayList<Member> simpleSearch(Object o, SearchMode searchMode) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();

		switch (searchMode) {
		case BY_NAME:
			String name = (String) o;
			for (Member m : memberList) {
				if (m.getName().length() >= name.length()) {
					String substring = m.getName().substring(0, name.length());
					if (substring.equalsIgnoreCase(name))
						foundMembers.add(m);
				}
			}
			break;
		case OLDER_THAN_AGE:
			int oldAge = (Integer) o;
			for (Member m : memberList) {
				if (m.getAge() > oldAge)
					foundMembers.add(m);
			}
			break;
		case YOUNGER_THAN_AGE:
			int youngAge = (Integer) o;
			for (Member m : memberList) {
				if (m.getAge() < youngAge)
					foundMembers.add(m);
			}
			break;
		case EQUAL_TO_AGE:
			int age = (Integer) o;
			for (Member m : memberList) {
				if (m.getAge() == age)
					foundMembers.add(m);
			}
			break;
		case BY_MONTH:
			Month month = (Month) o;
			for (Member m : memberList) {
				if (m.getBirthMonth() == month.getValue())
					foundMembers.add(m);
			}
			break;
		case BY_BOAT_TYPE:
			BoatType type = (BoatType) o;
			for (Member m : memberList) {
				for (Boat b : m.getBoatList()) {
					if (b.getType() == type) {
						foundMembers.add(m);
						break;
					}
				}
			}
			break;
		case GREATER_THAN_BOAT_LENGTH:
			double grt = (double) o;
			for (Member m : memberList) {
				for (Boat b : m.getBoatList()) {
					if (b.getLength() > grt) {
						foundMembers.add(m);
						break;
					}
				}
			}
			break;
		case SMALLER_THAN_BOAT_LENGTH:
			double sml = (double) o;
			for (Member m : memberList) {
				for (Boat b : m.getBoatList()) {
					if (b.getLength() < sml) {
						foundMembers.add(m);
						break;
					}
				}
			}
			break;
		case EQUAL_TO_BOAT_LENGTH:
			double eql = (double) o;
			for (Member m : memberList) {
				for (Boat b : m.getBoatList()) {
					if (b.getLength() == eql) {
						foundMembers.add(m);
						break;
					}
				}
			}
			break;
		}
		return foundMembers;
	}

	public ArrayList<Member> complexSearch(ArrayList<Member> firstList, ArrayList<Member> secondList, SearchOperator operator) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		
		switch(operator){
		case AND:
			for (Member m : firstList) {
				if (secondList.contains(m))
					foundMembers.add(m);
			}
			break;
		case OR:
			foundMembers.addAll(firstList);
			for (Member m : secondList) {
				if (!foundMembers.contains(m))
					foundMembers.add(m);
			}
			break;
		}
		return foundMembers;
	}
}