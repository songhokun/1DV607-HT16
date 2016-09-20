package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author songhokun
 *
 */
public class Registry {

	private ArrayList<Member> registry = new ArrayList<Member>();
	private Query query = new Query();

	public Registry() {
		fetchRegistry();
	}

	public void addMember(Member member) {
		registry.add(member);
		query.addToDatabase(member);
	
	}

	
	
	
	public ArrayList<Member> getRegistry() {
		return registry;
	}

	public void setRegistry(ArrayList<Member> registry) {
		this.registry = registry;
	}

	private void fetchRegistry() {
		ResultSet rs = query.fetchQuery("SELECT * from boatDB.members");
		try {
			while (rs.next()) {
				registry.add(new Member(rs.getString("name"), rs.getString("personalnumber")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}