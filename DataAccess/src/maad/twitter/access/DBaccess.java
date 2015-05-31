package maad.twitter.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class DBaccess {
	
	//ELIMINATE TUTTE LE LOGICHE DAL DATA ACCESS!!!	

	/**
	 * @param delegate
	 * @param tableName
	 *            questo metodo chiama il delegate passandogli un tweet alla
	 *            volta dal result set
	 */
	// IMPLEMENTARE METODO NELLA CLASSE ESTESA, CHE USA delegateTweet
	// con le logiche del caso.

	public void delegateTweet(DBaccessDelegate delegate, ResultSet tweets) {

		// IMPLEMENTALO NELLA CLASSE ESTESA!!!
		// seleziono linguaggi di programmazione
		// List<String> languages = selectLanguages(connection);

		// ho modificato il delegate adesso non funzionerà piu il metodo, devi
		// implementare la logica nella classe estesa
		try {
			while (tweets.next()) {
				Tweet t = new Tweet();
				mappingTweet(t, tweets);
				delegate.useIt(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param connection
	 * @param tableName
	 * @return result set contentente i tweet
	 */
	public ResultSet getTweets(Connection connection, int from, int to) {
		/*
		 * int idInizio = 0; // recupero ultimoId idInizio =
		 * getLastId(connection, tableName); // seleziona maxId int ultimoId =
		 * selectMaxId(connection);
		 */
		String query = "SELECT * FROM tweet WHERE id > %s AND id <= %s";
		query = query.format(query, from, to);
		ResultSet rs = executeQuery(connection, query);
		return rs;
	}
	public Connection dbConnection() {
		Connection connection = null;
		try {
			String driverName = "org.postgresql.Driver";
			String databaseURL = "jdbc:postgresql://localhost/db_twitter";
			Class driverClass = Class.forName(driverName);

			Driver driver = (Driver) driverClass.newInstance();

			connection = DriverManager.getConnection(databaseURL, "maad",
					"password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

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

	public void mappingTweet(Tweet t, ResultSet rs) throws SQLException {
		try {
			t.setId(rs.getInt("id"));
			t.setCreated_at(rs.getTimestamp("created_at"));
			t.setText(rs.getString("text"));
			t.setLang(rs.getString("lang"));
			t.setPlace(rs.getString("place"));
			t.setUsername(rs.getString("username"));
			t.setUser_location(rs.getString("user_location"));
			t.setTime_zone(rs.getString("time_zone"));
			t.setFriends_count(rs.getString("friends_count"));
			t.setFollowers_count(rs.getString("followers_count"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getLastId(Connection connection, String tableName) {
		try {
			int idInizio = 0;
			String idMax = "SELECT last_id  FROM %s";
			idMax = idMax.format(idMax, tableName);
			ResultSet rsUltimoId = executeQuery(connection, idMax);
			while (rsUltimoId.next())
				idInizio = rsUltimoId.getInt("last_id");
			return idInizio;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	public int selectMaxId(Connection connection) {
		try {
			String queryId = "SELECT MAX(id) as max FROM tweet";
			ResultSet rsId = executeQuery(connection, queryId);
			int ultimoId = 0;
			while (rsId.next())
				ultimoId = rsId.getInt("max");
			return ultimoId;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	public void updateLastId(Connection connection, int ultimoId,
			String tableName) {
		try {
			String idSave = "UPDATE %s SET last_id = %s";
			idSave = idSave.format(idSave, tableName, ultimoId);
			Statement statementIdSave = connection.createStatement();

			statementIdSave.executeUpdate(idSave);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// IMPLEMENTALO NELLA CLASSE ESTESA
	/*
	 * public List<String> selectLanguages(Connection connection) {
	 * 
	 * List<String> languages = new ArrayList<String>(); try { String query =
	 * "SELECT * FROM languages"; Statement statement =
	 * connection.createStatement(); ResultSet rs =
	 * statement.executeQuery(query); while (rs.next()) {
	 * languages.add(rs.getString("language")); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return languages; }
	 */

	// IMPLEMENTALO NELLA CLASSE ESTESA
	/*
	 * public boolean languagesInDb(String s) { 
	 * 
	 * boolean ris = false; try {
	 * Connection connection = dbConnection(); String query =
	 * "SELECT * FROM languages"; Statement statement =
	 * connection.createStatement(); ResultSet rs =
	 * statement.executeQuery(query); while (rs.next()) { if (s ==
	 * rs.toString()) ris = true; }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return ris; }
	 */
}
