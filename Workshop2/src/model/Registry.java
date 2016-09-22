package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry implements IMemberUpdateObserver{

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
		new WriteFile(this);
	}

	@Override
	public void memberInformationChanged() {
		new WriteFile(this);	
	}
}