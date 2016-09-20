/**
 * 
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry {
	private ArrayList<Member> memberdata = new ArrayList<Member>();
	private Query query;

	public Registry() {
		query = new Query();
		ResultSet rs = query.fetchQuery("SELECT * from boatDB.members");
		try {
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addMember(Member in) {
		memberdata.add(in);
	}

	public void registerBoat(Member member, Boat boat) {

		for (Member i : memberdata) {
			if (i.equals(member)) {
				i.getBoatdata().add(boat);
				return;
			}
		}
		throw new IllegalArgumentException("The provided member is not registered in our registry");
	}

}
