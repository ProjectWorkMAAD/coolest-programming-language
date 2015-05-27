package maad.twitter.analysis;
import maad.twitter.access.DBaccess;
import maad.twitter.access.DBaccessDelegate;
import maad.twitter.access.Tweet;


public class Analysis {
	public void run() {
		
		DBaccess db = new DBaccess();
		//applico la magia nera per prendere i tweet
		db.connectDB(new DBaccessDelegate() {
			public void useIt(Tweet tweet) {
				System.out.println(tweet.getId());
			}			
		});
	}
}
