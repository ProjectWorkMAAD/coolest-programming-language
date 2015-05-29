package maad.twitter.analysis;
import maad.twitter.access.DBaccess;
import maad.twitter.access.DBaccessDelegate;
import maad.twitter.access.Tweet;
import java.util.List;

public class Analysis {
	public void run() {
		
		String tableName = "count_analysis";

		DBaccess db = new DBaccess();
		//applico la magia nera per prendere i tweet
		db.getTweet(new DBaccessDelegate() {
			public void useIt(Tweet tweet, List<String> languages) {
				System.out.println(tweet.getId());
				
				analizzaTweet(tweet, languages);
				
			}			
		}, tableName);
	}
	
	public void analizzaTweet(Tweet t, List<String> languages){

		DBaccess db = new DBaccess();
		
		for(String s : languages){
			if(wordIsIn(t.getText().toString(), s)){
				if(db.languagesInDb(s)){   //se il linguaggio è gia nel DB
					System.out.println("ciao");
					//aumento i contatori dei linguaggi
				}
				else{
					//inserisco il nuovo linguaggio nel DB
				}
			}
		}
	}
	
	public boolean wordIsIn(String stringa, String sottostringa) {		
	    boolean cerca = false;

	    int max = stringa.length() - sottostringa.length();

	    test:
	    for (int i = 0; i <= max; i++) {
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
	
}
