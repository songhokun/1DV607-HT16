package model.Search;

import java.util.ArrayList;
import model.Member;

class ByMonth implements ISimpleSearchStrategy {

	private int month;
	private ArrayList<Member> foundMembers;

	public ByMonth(int month) {
		this.month = month;
		foundMembers = new ArrayList<Member>();
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		for (Member m : list) {
			if (m.getBirthMonth() == month) {
				foundMembers.add(m);
			}
		}
		return foundMembers;
	}
}
