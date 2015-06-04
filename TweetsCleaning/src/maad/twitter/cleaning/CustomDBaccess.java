package maad.twitter.cleaning;

import java.sql.*;

import maad.twitter.access.DBaccess;

public class CustomDBaccess extends DBaccess {

	public ResultSet getWords() {
		String query = "SELECT words FROM cleaning";
		ResultSet words = executeQuery(query);
		return words;
	}

	@SuppressWarnings("static-access")
	public void delete(int id) throws SQLException {
		String query = "DELETE FROM TWEET WHERE ID = %s";
		query = query.format(query, id);		
		try {
			java.sql.Statement statement = connection.createStatement();
			statement.executeUpdate(query);			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			e.printStackTrace();			
		}
	
	}

}
