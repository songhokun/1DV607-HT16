package model.Search;

import java.util.ArrayList;
import model.Member;
import model.Search.SearchMode.SimpleSearchMode;

class ByAge implements ISimpleSearchStrategy {

	private int age;
	private ArrayList<Member> foundMembers;
	private SimpleSearchMode mode;

	public ByAge(int age, SimpleSearchMode mode) {
		this.age = age;
		this.mode = mode;
		foundMembers = new ArrayList<Member>();
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		for (Member m : list) {

			switch (mode) {
			case BY_AGE_GREATER_THAN:
				if (m.getAge() > age) {
					foundMembers.add(m);
				}
				break;

			case BY_AGE_LESS_THAN:
				if (m.getAge() < age) {
					foundMembers.add(m);
				}
				break;

			case BY_AGE_EQUAL_TO:
				if (m.getAge() == age) {
					foundMembers.add(m);
				}
			
			default:
				break;
			}
		}
		return foundMembers;
	}
}