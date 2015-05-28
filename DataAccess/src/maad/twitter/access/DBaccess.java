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



public class DBaccess{
	
	public void connectDB(DBaccessDelegate delegate, String tableName){		
		try{
			String driverName = "org.postgresql.Driver";
			String databaseURL = "jdbc:postgresql://localhost/db_twitter";
			Class driverClass = Class.forName(driverName);
					
			Driver driver = (Driver) driverClass.newInstance();
			Connection connection = DriverManager.getConnection(databaseURL, "maad","password");
			
			int idInizio = 0;
			
			//recupero ultimoId 
			idInizio = recuperaUltimoId(connection, tableName);
			
			//seleziona maxId
			int ultimoId = selezionaMaxId(connection);
			
			String query = "SELECT * FROM tweet WHERE id > %s AND id <= %s";
			query = query.format(query, idInizio, ultimoId);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
				
			while(rs.next()) {				
				Tweet t = new Tweet();
				mappingTweet(t,rs);
			 	delegate.useIt(t);
			}			
			//salvo l'ultimo id analizzato
			salvoUltimoId(connection, ultimoId, tableName);			
			connection.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void mappingTweet(Tweet t,ResultSet rs) throws SQLException{
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
	
	public int recuperaUltimoId(Connection connection, String tableName){
		try{
			int idInizio = 0;
			String idMax = "SELECT max_id  FROM %s";
			idMax = idMax.format(idMax, tableName);
			Statement statementUltimoId = connection.createStatement();
			
			ResultSet rsUltimoId = statementUltimoId.executeQuery(idMax);	
		
			while(rsUltimoId.next())
				idInizio = rsUltimoId.getInt("max_id");
			return idInizio;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	public int selezionaMaxId(Connection connection){
		try{
			String queryId = "SELECT MAX(id) as max FROM tweet";			
			Statement statementId = connection.createStatement();				
			ResultSet rsId = statementId.executeQuery(queryId);	
			int ultimoId = 0;
			while(rsId.next())  
				ultimoId = rsId.getInt("max");
			return ultimoId;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	public void salvoUltimoId(Connection connection, int ultimoId, String tableName){
		try{
			String idSave = "UPDATE %s SET max_id = %s";
			idSave = idSave.format(idSave, tableName, ultimoId);
			Statement statementIdSave = connection.createStatement();				
			statementIdSave.executeUpdate(idSave);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
