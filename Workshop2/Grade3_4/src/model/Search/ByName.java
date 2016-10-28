package model.Search;

import java.util.ArrayList;
import model.Member;

class ByName implements ISimpleSearchStrategy {

	private String name;
	private ArrayList<Member> foundMembers;

	public ByName(String name) {
		this.name = name;
		foundMembers = new ArrayList<Member>();
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		for (Member m : list) {
			if (m.getName().toLowerCase().startsWith(name.toLowerCase())) {
				foundMembers.add(m);
			}
		}
		return foundMembers;
	}
}
