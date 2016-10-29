package model.Search;

import java.util.ArrayList;
import model.Member;

public class ByAgeLessThan extends ByAgeEqualTo {

	public ByAgeLessThan(int age) {
		super(age);
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		for (Member m : list) {
			if (m.getAge() < age) {
				foundMembers.add(m);
			}
		}
		return foundMembers;
	}
}
