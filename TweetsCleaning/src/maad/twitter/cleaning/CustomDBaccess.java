package maad.twitter.cleaning;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import maad.twitter.access.DBaccess;
import maad.twitter.access.Tweet;

public class CustomDBaccess extends DBaccess {
	
	public ResultSet executeQuery(Connection connection, String query) {
		ResultSet result = null;		
		try {
			java.sql.Statement statement = connection.createStatement();
			result = statement.executeQuery(query);			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	public String generateSelectQuery(List<String> tables, List<String> columns){			
		return null;	
	}

	public void generateDeleteQuery(Tweet tweet) {
		
		String query = "DELETE FROM TWEET WHERE ID = %s";
		query = query.format(query, tweet.getId());
		
	}
	
	public void executeNonQuery(Connection connection, String query) {
		
	}

	public void delete(Tweet tweet) {
		// TODO Auto-generated method stub
		
	}

	public ResultSet getWords() {		
		List<String> columns = null;
		String column = "";
		List<String> tables = null;
		String table = "";
		columns.add(column);
		tables.add(table);
		String query = generateSelectQuery(tables, columns);
		
		return null;
	}
	
	
	//void function(delegate, rs)
		//while rs.next(){
		//delegate.getWord(rs.tostring()); }
	  
	
}
