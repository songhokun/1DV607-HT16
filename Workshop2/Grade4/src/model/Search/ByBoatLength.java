package model.Search;

import java.util.ArrayList;
import model.Boat;
import model.Member;

class ByBoatLength implements ISimpleSearchStrategy {

	private double length;

	public ByBoatLength(double length) {
		this.length = length;
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : list) {
			for (Boat b : m.getBoatList()) {
				if (b.getLength() == length) {
					foundMembers.add(m);
					break;
				}
			}
		}
		return foundMembers;
	}
}
