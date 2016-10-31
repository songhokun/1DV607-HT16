package model.Search;

import java.util.ArrayList;
import model.Boat;
import model.Boat.BoatType;
import model.Member;

class ByBoatType implements ISimpleSearchStrategy {

	private BoatType type;

	public ByBoatType(BoatType type) {
		this.type = type;
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : list) {
			for (Boat b : m.getBoatList()) {
				if (b.getType() == type) {
					foundMembers.add(m);
					break;
				}
			}
		}
		return foundMembers;
	}
}
