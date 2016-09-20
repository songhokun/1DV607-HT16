/**
 * 
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Boat.BoatType;

/**
 * @author songhokun
 *
 */
public class Member {
	private String name;
	private String personnumber;
	
	private int memberID;
	
	private ArrayList<Boat> boatdata = new ArrayList<Boat>();
	private Query query;
	
	public Member(){
	}
	
	public Member(String name, String personnumber, int memberID){
		this.name=name;
		this.personnumber=personnumber;
		this.memberID=memberID;
		
		fetchBoats();
	}
	
	private void fetchBoats(){
		query = new Query();
		ResultSet rs = query.fetchQuery("SELECT id, length, boattype FROM boatDB.boats where owner='"+this.personnumber+"';");
		try {
			while (rs.next()) {
				Boat temp = new Boat();
				temp.setLength(rs.getDouble("length"));
				temp.setType(rs.getString("type"));
				temp.setOwner(this);
				temp.setBoatID(rs.getInt("id"));
				this.boatdata.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String getName() {
		return name;
	}
	public String getPersonnumber() {
		return personnumber;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public int getNumberOfBoats() {
		return boatdata.size();
	}
	
	public ArrayList<Boat> getBoatdata() {
		return boatdata;
	}

	public void setBoatdata(ArrayList<Boat> boatdata) {
		this.boatdata = boatdata;
	}
	//TODO : Do we still need an unique ID even when social security number is unique enough? 
	
	public int getMemberID(){
		return this.memberID;
	}
	public void setMemberID(int memberID){
		this.memberID=memberID;
	}
}
