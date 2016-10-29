package model.Search;

import java.util.ArrayList;
import model.Member;

class ByAgeGreaterThan extends ByAgeEqualTo {

	public ByAgeGreaterThan(int age) {
		super(age);
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		for (Member m : list) {
			if (m.getAge() > age) {
				foundMembers.add(m);
			}
		}
		return foundMembers;
	}
}
