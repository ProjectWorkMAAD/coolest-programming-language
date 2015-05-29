package maad.twitter.cleaning;

import java.sql.ResultSet;

import maad.twitter.access.*;

public class Cleaning {
	/**
	 * la funzione implementa il DB access delegate applica le logiche di
	 * cleaning al tweet passando uno alla volta gli elementi della tabella
	 * cleaning
	 * 
	 */
	public void runCleaning() {
		String table = "count_cleaning";
		CustomDBaccess db = new CustomDBaccess();		
		db.connectDB(new DBaccessDelegate() {
			@Override
			public void useIt(Tweet tweet) {
				checkTweet(new CleaningDelegate() {
					@Override
					public void check(String word) {
						// TODO implement the call to checkTweet + the function in the dbAccess that uses the interface
					}
				}, tweet, db.getWords());
			} 
		}, table);
	}
	


	public void checkTweet(CleaningDelegate delegate, Tweet tweet, ResultSet rs) {
		
		// mi deve passare una stringa alla volta della tabella cleaning del
		// database
		if (tweet.getText().toLowerCase().indexOf("") >= 1) {
			
		}

	}

}
