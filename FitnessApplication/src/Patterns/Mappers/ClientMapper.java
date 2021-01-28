package Patterns.Mappers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Business.FitnessGoal;
import Database.DBConnection;
import Objects.Client;

/**
 * 
 * @author Dhilan Chandrasekara, Chamira Balasuriya
 * 
 * Responsible for querying the database and returning Client instances, filled in with
 * information retrived from the database 
 *
 */
public class ClientMapper   {
	
	public static ArrayList<Client> allClients;
	
	@Override
	public String toString() {
		return "This is the Client Mapper toString() method";
		//TODO: Catch the nullpointer exception thrown when there's no internet connection
	}
	
	public static ArrayList<Client> getAllClients() {
    	ArrayList<Client> clients = new ArrayList<>();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM client");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Client client = getClientInstanceFromResultSet(rs);
				//System.out.println(client.getLastName());
				
				clients.add(client);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		allClients = clients;
		return clients;
	}
	
	public static Client getClientFromId(int id) {
		Client client = new Client();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM client WHERE memberid=" +
					Integer.toString(id) + ";");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("rs next was true once");
				try {
				System.out.println("getClientFromId() method: Client name is" + rs.getString(2));
				} catch (NullPointerException e) {
					System.out.println("Null pointer exception :( ");
				}
				
				client.setClientId(id);
				client.setFirstName(rs.getString(2));
				client.setLastName(rs.getString(3));
				client.setContactNumber(rs.getString(4));
				client.setGoal(FitnessGoal.WEIGHTLOSS); //TODO: Change to reflect DB
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return client;
	}
	
	public static Client getClientInstanceFromResultSet(ResultSet rs) throws SQLException {
		Client client = new Client();
		client.setClientId(rs.getInt(1));
		client.setFirstName(rs.getString(2));
		client.setLastName(rs.getString(3));
		client.setContactNumber(rs.getString(4));
		client.setGoal(FitnessGoal.WEIGHTLOSS); //TODO: Change to reflect DB
		
		return client;
	}
	
	public static void removeClientById(int id) {
		try {
			PreparedStatement s = DBConnection.prepare("DELETE FROM client WHERE memberid=" +
			Integer.toString(id) + ";COMMIT;");
			s.executeUpdate();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addNewClient(Client client) {
		
		String id = Integer.toString(client.getClientId());
		String firstName = client.getFirstName();
		String lastName = client.getLastName();
		String goal = "WEIGHTLOSS"; // TODO: change later
		String contactNumber = client.getContactNumber();
		String trainerId = "3"; // TODO: Perhaps change later
		
		String sqlStatement = "INSERT INTO client VALUES"
				+ " (" + id +  ", '" + firstName + "', '"
				+ lastName + "', '"
						+ contactNumber + "', '"
								+ goal + "', "
										+ trainerId  +");" + "COMMIT;";
		System.out.println("The sql statement is: " + sqlStatement);
		try {
			PreparedStatement s = DBConnection.prepare(sqlStatement);
			s.executeUpdate();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Client createNewClientInstance(int id, String firstName, String lastName, FitnessGoal goal, String contactNumber) {
		
		// Put the parameter values into a new Client instance
		Client client = new Client();
		client.setClientId(id);
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setGoal(goal);
		client.setContactNumber(contactNumber);
		client.setTrainerId(3); //TODO: Change this to reflect which trainer, maybe?
		
		return client;		
	}
	
	public static Client checkUsernameAndPassword(String username, String password) {
		Client client = new Client();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM client WHERE username='" +
					username + "' AND password='" + password + "';");
					
			ResultSet rs = stmt.executeQuery();
			
			if (!rs.next() ) {
			    return null;
			} else {
				System.out.println("going into client creation CHECKUSERPASSMETHOD");
				try {
				System.out.println("checkUsernameAndPassword() method: Client name is" + rs.getString(2));
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				client.setClientId(rs.getInt(1));
				client.setFirstName(rs.getString(2));
				client.setLastName(rs.getString(3));
				client.setContactNumber(rs.getString(4));
				client.setGoal(FitnessGoal.WEIGHTLOSS); //TODO: Change to reflect DB
				client.setTrainerId(rs.getInt(6));
				client.setUsername(rs.getString(7));
				client.setPassword(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return client;
	}
}
