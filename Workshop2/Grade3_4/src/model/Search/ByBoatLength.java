package model.Search;

import java.util.ArrayList;
import model.Boat;
import model.Member;

class ByBoatLength implements ISimpleSearchStrategy {

	private double length;
	private ArrayList<Member> foundMembers;

	public ByBoatLength(double length) {
		this.length = length;
		foundMembers = new ArrayList<Member>();
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
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
