package Objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Business.Workout;
import Patterns.IdentityMap;
import Database.DBConnection;
import Patterns.UnitOfWork;
import Business.FitnessGoal;


public class Client extends User {
	private int clientId; 
	private int trainerId; 
	private FitnessGoal goal; 
	 
	public Client(int clientId, String firstName, String lastName, String contactNumber, FitnessGoal goal) {
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.contactNumber = contactNumber; 
		this.goal = goal;  
		finder(clientId);  
		findTrainerId(this.clientId);  
		UnitOfWork.getCurrent().registerAsClean(this);
	}  
	
	public Client() {
		
	}
	
//	//identity map pattern 
//	public Client finderSingleton(int id) {
//		Client client = new Client(); 
//		IdentityMap<Client> map = IdentityMap.getInstance(client); 
//		client = map.get(id); 
//			
//		if(client == null) {
//			Client clientNew = new Client();
//			Record record = new Record(gateway.find(id));  
//			clientNew.setClientId(record.getId()); 
//			clientNew.setTrainerId(record.getTrainerId());
//			clientNew.setFirstName(record.getFirstName());
//			clientNew.setLastName(record.getLastName());  
//			clientNew.setContactNumber(record.getContactNumber());  
//			clientNew.setGoal(record.getGoal()); 
//			map.put(id, clientNew); 
//			return clientNew;
//		} else {
//			return client; 
//		}
//	}
	
	public void findTrainerId(int clientId) {/*
		String sql = "SELECT trainerId FROM client WHERE id=" + clientId; 
		PreparedStatement stmt = DBConnection.prepare(sql);
		try {
			ResultSet rs = stmt.executeQuery(); 
			if(rs != null) {
				int dbId = rs.getInt(2);
				this.trainerId = dbId; 
			} 
		} catch (SQLException e) {
			System.out.println(e); 
		}*/
	}
	
	public void finder(int clientId) {
		String sql = "SELECT id FROM client WHERE firstName=" + getFirstName() + " AND lastName=" + getLastName(); 
		
		try {
			PreparedStatement stmt = DBConnection.prepare(sql);
			ResultSet rs = stmt.executeQuery(); 
			if(rs != null) {
				int dbId = rs.getInt(1);
				this.clientId = dbId; 
			} else {
				this.clientId = clientId;  
			}
		} catch (Exception e) {
			System.out.println(e); 
		}
	} 
	
	public void setClientId(int id) {
		this.clientId = id;  
		//UnitOfWork.getCurrent().registerAsDirty(this);
	} 
	
	public void setTrainerId(int id) {
		this.trainerId = id;  
		//UnitOfWork.getCurrent().registerAsDirty(this);
	} 
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;  
		//UnitOfWork.getCurrent().registerAsDirty(this);
	} 
	
	public void setLastName(String lastName) {
		this.lastName = lastName;  
		//UnitOfWork.getCurrent().registerAsDirty(this);
	} 
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;  
		//UnitOfWork.getCurrent().registerAsDirty(this);
	} 
	
	public void setGoal(FitnessGoal goal) {
		this.goal = goal; 
	}
	
	public int getClientId() {
		return clientId; 
	}
	
	public int getTrainerId() {
		return trainerId; 
	}
	
	public String getFirstName() {
		return firstName; 
	}
	
	public String getLastName() {
		return lastName; 
	}
	
	public String getContactNumber() {
		return contactNumber; 
	}
	
	public FitnessGoal getGoal() {
		return goal; 
	}
}
