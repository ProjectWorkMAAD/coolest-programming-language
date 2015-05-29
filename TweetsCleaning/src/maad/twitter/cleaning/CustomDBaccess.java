package maad.twitter.cleaning;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import maad.twitter.access.DBaccess;
import maad.twitter.access.Tweet;

public class CustomDBaccess extends DBaccess {
	
	public Iterable<Object> select(Connection connection, String query, String table) {
		Collection<Object> result = null;		
		try {
			java.sql.Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				result.add(resultSet.getObject(table));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	public String generateSelectQuery(List<String> columns, List<String> tables){		
				
		return null;
		
	}

	public void delete(Tweet tweet) {
		
		String query = "DELETE FROM TWEET WHERE ID = %s";
		query = query.format(query, tweet.getId());
		
	}
	
	//while rs.next 
	
	
}
