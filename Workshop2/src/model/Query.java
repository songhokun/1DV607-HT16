package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public void addToDatabase(Object o) {
		StringBuilder sb = new StringBuilder();
		if (o instanceof Member) {
			sb.append("INSERT INTO `boatDB`.`members` (`name`, `personalnumber`, `uniqueid`) VALUES ('");
			sb.append(((Member) o).getName() + "', '");
			sb.append(((Member) o).getPersonnumber() + "', '");
			sb.append(((Member) o).getMemberID() + "');");
		} else if (o instanceof Boat) {
			sb.append("INSERT INTO `boatDB`.`boats` (`length`, `boattype`, `owner`) VALUES ('");
			sb.append(((Boat) o).getLength() + "', '");
			sb.append(((Boat) o).getType().toString() + "', '");
			// sb.append(((Boat) o).ge + "');");
		}
		else System.err.println("The give object is not insertable.");
		executeQuery(sb.toString());
	}
}