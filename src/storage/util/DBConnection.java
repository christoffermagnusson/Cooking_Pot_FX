package storage.util;

import java.sql.*;
import log.Log;


public class DBConnection {

	private Log log = new Log();

	private static DBConnection instance;
	private static Connection connection;

	private static String hostname = "localhost";
	private static String service = "Cooking_Pot";
	private static String user = "postgres";
	private static String password = "bavaria88";
	private static String schema = "cooking_pot";

	private DBConnection(){
		connect();
	}

	public static DBConnection getInstance(){
		if(instance==null){
			instance = new DBConnection();
			return instance;
		}
		return instance;
	}

	private static void connect(){
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(String.format("jdbc:postgresql://%s:5432/%s?currentSchema=%s",hostname,service,schema),user,password);
			Log.write("Connection succesful");
		}
		catch(Exception e){
			e.printStackTrace();
			Log.write("Connection could not be established");
		}
	}
	public static void closeConnection() throws StorageException{
		try{
			connection.close();
		Log.write("Connection closed");
			}
			catch(SQLException se){
				throw new StorageException(se);
			}
		}

	public ResultSet execQuery(String query) throws StorageException{
		try{
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(query);
			Log.write("Query successful");
			return res;
		}
		catch(SQLException se){
			throw new StorageException(se);
		}
	}
	public void update(String query) throws StorageException{
		try{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			Log.write("Update successful");
		}
		catch(SQLException se){
			throw new StorageException(se);
		}
	}

}
