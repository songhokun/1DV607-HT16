package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DatabaseConnection;

public class programme {

	public static void main(String[] args) {

		DatabaseConnection db = new DatabaseConnection();
		ResultSet rd = db.fetch("SELECT * from boatDB.members");
		try {
			while(rd.next()){
				System.out.println(rd.getString("personalnumber"));
				System.out.println(rd.getString("name"));
				System.out.println(rd.getString("uniqueid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
