package maad.twitter.cleaning;

import java.sql.ResultSet;
import java.sql.SQLException;
import maad.twitter.access.*;

public class Cleaning {

	private String table;
	private CustomDBaccess db = new CustomDBaccess();
	int counter = 0;

	public void runCleaning() {

		db.dbConnection();
		int from = db.getLastId(table);
		int to = db.selectMaxId();
		ResultSet tweets = db.getTweets(from, to);

		db.delegateTweet(new DBaccessDelegate() {
			@Override
			public void useIt(Tweet t) {
				checkTweet(t, db.getWords());
			}
		}, tweets);
		db.updateLastId(to, table);
	}

	/**
	 * @param tweet
	 * @param words
	 * @throws SQLException
	 */
	public void checkTweet(Tweet tweet, ResultSet rs) {

		try {
			while (rs.next()) {
				if (wordIsIn(tweet.getText().toLowerCase(),
						rs.getString("words"))) {
					db.delete(tweet.getId());
					counter++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean wordIsIn(String stringa, String sottostringa) {
		boolean cerca = false;

		int max = stringa.length() - sottostringa.length();

		test: for (int i = 0; i <= max; i++) {
			int n = sottostringa.length();
			int j = i;
			int k = 0;
			while (n-- != 0) {
				if (stringa.charAt(j++) != sottostringa.charAt(k++)) {
					continue test;
				}
			}
			cerca = true;
			break test;
		}
		return cerca;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int getCounter() {
		return counter;
	}
}
