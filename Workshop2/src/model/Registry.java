package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry implements IMemberUpdateObserver {

	private ArrayList<Member> registry = new ArrayList<Member>();
	private int maxID = 0;

	public Registry() throws FileNotFoundException {
		new ReadFile(this);
		for (Member m : registry)
			m.addSubscriber(this);
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
	
	@Override
	public void memberInformationChanged() {
		new WriteFile(this);
	}

	public void createMember(String name, String personalNumber) {
		this.registry.add(new Member(name, personalNumber, ++maxID));
		this.memberInformationChanged();
	}

	public void updateMember(Member m) {
		for (Member member : registry)
			if (member.getMemberID() == m.getMemberID()) {
				member = m;
				break;
			}
		this.memberInformationChanged();
	}
	
	public void deleteMember(Member m) {
		this.registry.remove(m);
		this.memberInformationChanged();
	}
	
	/********************Below methods I am not using**************************************************************************/
	
	
	public void deleteMember(int ID) {
		this.registry.remove(lookUpMember(ID));
		this.memberInformationChanged();
	}

	public void updateMember(int memberID, String name, String personalNumber) {
		if (!name.isEmpty())
			lookUpMember(memberID).setName(name);
		if (!personalNumber.isEmpty())
			lookUpMember(memberID).setPersonalnumber(personalNumber);

		this.memberInformationChanged();
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