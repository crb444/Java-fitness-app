package Database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;


public class DBConnection {
	
	// JDBC driver name and database URL
	
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/exerciseapp6";
	
	private static final String HEROKU_STRING = "jdbc:postgresql://ec2-54-83-33-14.compute-1.amazonaws.com:5432/d7gcv8i5opq0t9?sslmode=require&user=ystpwvxtztpnap&password=73c82b66bdbc2edb309a69b7698c9942e3d2842b301cff627501810be3feaa08";
	
	private static final String HEROKU_STRING2 = "postgres://ystpwvxtztpnap:73c82b66bdbc2edb309a69b7698c9942e3d2842b301cff627501810be3feaa08@ec2-54-83-33-14.compute-1.amazonaws.com:5432/d7gcv8i5opq0t9";
	
	private static final String HEROKU_STRING_TEMPDEPLOY = "postgres://kfkdzqeyijycil:8772261f653936c4ee37967f9c3d273e87f79141fe4bfa29e8da1c2fb8a81a7c@ec2-23-21-156-171.compute-1.amazonaws.com:5432/d36ler5f8dc6m5";
	// Database credentials
	
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "QwelloPello!12";
	
	static Connection dbConnection = null;
	
	public static PreparedStatement prepare(String stm) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			Connection dbConnection = getHerokuConnection2();
			preparedStatement = dbConnection.prepareStatement(stm);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return preparedStatement;
	}
	
	private static Connection getHerokuConnection() throws URISyntaxException, SQLException {
		DriverManager.registerDriver(new org.postgresql.Driver());
	    String dbUrl = System.getenv(HEROKU_STRING);
	    return DriverManager.getConnection(dbUrl);
	}
	
	private static Connection getHerokuConnection2() throws URISyntaxException, SQLException {
		DriverManager.registerDriver(new org.postgresql.Driver());
		URI dbUri = new URI(HEROKU_STRING_TEMPDEPLOY);

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	private static Connection getDBConnection() {
		try {
			// Register JBDC driver
			DriverManager.registerDriver(new org.postgresql.Driver());
			
			// Open a connection
			dbConnection = DriverManager.getConnection(DB_CONNECTION, 
					DB_USER, DB_PASSWORD);
			dbConnection.setAutoCommit(false);
			
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Connection problem");
		return null;
	}

}

