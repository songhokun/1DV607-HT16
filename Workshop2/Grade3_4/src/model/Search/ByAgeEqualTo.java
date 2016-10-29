package model.Search;

import java.util.ArrayList;
import model.Member;

class ByAgeEqualTo implements ISimpleSearchStrategy {

	protected int age;
	protected ArrayList<Member> foundMembers;

	public ByAgeEqualTo(int age) {
		this.age = age;
		foundMembers = new ArrayList<Member>();
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		for (Member m : list) {
			if (m.getAge() == age) {
				foundMembers.add(m);
			}
		}
		return foundMembers;
	}
}