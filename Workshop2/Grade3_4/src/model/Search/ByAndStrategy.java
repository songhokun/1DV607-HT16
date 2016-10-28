package model.Search;

import java.util.ArrayList;
import model.Member;

class ByAndStrategy implements IComplexSearchStrategy {

	@Override
	public ArrayList<Member> complexSearch(ArrayList<Member> firstList, ArrayList<Member> secondList) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : firstList) {
			if (secondList.contains(m))
				foundMembers.add(m);
		}
		return foundMembers;
	}
}
