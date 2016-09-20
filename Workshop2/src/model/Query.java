package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query {

	private DatabaseConnection db;

	public Query() {

	}

	public ResultSet fetchQuery(String sqlQuery) {
		db = new DatabaseConnection();
		ResultSet rs = null;

		try {
			PreparedStatement stat = db.getConnection().prepareStatement(sqlQuery);
			stat.execute();
			rs = stat.getResultSet();
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet executeQuery(String sqlQuery) {
		db = new DatabaseConnection();
		ResultSet rs = null;

		try {
			PreparedStatement stat = db.getConnection().prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stat.execute();
			rs = stat.getResultSet();
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ArrayList<Member> toMembers(ResultSet rs){
		return null;
		
		
	}
}
