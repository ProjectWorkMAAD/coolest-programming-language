package maad.twitter.cleaning;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import maad.twitter.access.*;

public class Cleaning {
		
	public void runCleaning() {
		
		String table = "count_cleaning";	
		CustomDBaccess db = new CustomDBaccess();		
		Connection connection = db.dbConnection();		
		int from = db.getLastId(connection, table);		
		int to = db.selectMaxId(connection);		
		ResultSet tweets = db.getTweets(connection, from, to);		
		ResultSet words = db.getWords(connection);	
		
		db.delegateTweet(new DBaccessDelegate() {
			@Override
			public void useIt(Tweet t) {
				checkTweet(t, words);			
			}				
		}, tweets);		
		
		//se la pulizia va a buon fine aggiornare count_cleaning
		
	}	
	
	/**
	 * 
	 * @param tweet
	 * @param words
	 */
	public void checkTweet(Tweet tweet, ResultSet words) {				
		
		try {
			while(words.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
