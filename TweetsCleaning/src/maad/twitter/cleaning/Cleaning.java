package maad.twitter.cleaning;

import maad.twitter.access.*;

public class Cleaning {
	/**
	 * la funzione implementa il DB access delegate applica le logiche di
	 * cleaning al tweet passando uno alla volta gli elementi della tabella
	 * cleaning
	 * 
	 */
	public void runCleaning() {
		/*
		 * String table = "count_cleaning"; CustomDBaccess db = new
		 * CustomDBaccess(); db.connectDB(new DBaccessDelegate() { public void
		 * useIt(Tweet tweet) { checkTweet(new DBaccessDelegate() {
		 * 
		 * } }, tweet); } }, table);
		 */

		String table = "count_cleaning";
		CustomDBaccess db = new CustomDBaccess();
		db.connectDB(new DBaccessDelegate() {
			@Override
			public void useIt(Tweet tweet) {
				checkTweet(new CleaningDelegate() {
					@Override
					public void check(String word) {
						// TODO Auto-generaDBaccessDelegateSted method stub
					}
				}, tweet, db);
			}
		}, table);
	}

	public void checkTweet(CleaningDelegate delegate, Tweet tweet, CustomDBaccess db) {

		String text = tweet.getText();
		// mi deve passare una stringa alla volta della tabella cleaning del
		// database
		String search = "";

		if (text.toLowerCase().indexOf(search) >= 1) {
			db.delete(tweet);
		}

	}

}
