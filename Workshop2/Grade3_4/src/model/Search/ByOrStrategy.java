package model.Search;

import java.util.ArrayList;
import model.Member;

class ByOrStrategy implements IComplexSearchStrategy {

	@Override
	public ArrayList<Member> complexSearch(ArrayList<Member> firstList, ArrayList<Member> secondList) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		foundMembers.addAll(firstList);
		for (Member m : secondList) {
			if (!foundMembers.contains(m))
				foundMembers.add(m);
		}
		return foundMembers;
	}
}
