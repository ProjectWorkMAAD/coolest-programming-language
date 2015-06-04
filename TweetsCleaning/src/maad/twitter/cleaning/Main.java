package maad.twitter.cleaning;

public class Main {

	public static void main(String[] args) {
		Cleaning cleaning = new Cleaning();
		cleaning.setTable("count_cleaning");

		cleaning.runCleaning();		

		String s = String.format("%d rows deleted from the database",
				cleaning.getCounter());

		System.out.println(s);
	}
}
