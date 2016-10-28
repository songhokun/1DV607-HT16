package model.Search;

import java.util.ArrayList;
import model.Boat;
import model.Boat.BoatType;
import model.Member;

public class ByBoatType implements ISimpleSearchStrategy {

	private BoatType type;
	private ArrayList<Member> foundMembers;

	public ByBoatType(BoatType type) {
		this.type = type;
		foundMembers = new ArrayList<Member>();
	}

	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
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
