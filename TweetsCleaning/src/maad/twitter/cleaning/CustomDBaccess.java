package maad.twitter.cleaning;
import java.sql.Connection;
import java.sql.ResultSet;

import maad.twitter.access.DBaccess;

public class CustomDBaccess extends DBaccess{

	public ResultSet getWords(Connection connection) {		
		String query = "SELECT * FROM cleaning";		
		ResultSet words = executeQuery(connection, query);	
		return words;
	}
	
}
