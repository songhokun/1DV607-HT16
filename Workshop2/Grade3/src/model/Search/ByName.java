package model.Search;

import java.util.ArrayList;
import model.Member;

class ByName implements ISimpleSearchStrategy {

	private String name;

	public ByName(String name) {
		this.name = name;
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : list) {
			if (m.getName().toLowerCase().startsWith(name.toLowerCase())) {
				foundMembers.add(m);
			}
		}
		return foundMembers;
	}
}
