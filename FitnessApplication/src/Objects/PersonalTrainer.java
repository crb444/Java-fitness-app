package Objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Patterns.IdentityMap;

public class PersonalTrainer extends User {
	private int trainerId; 
	private String firstName; 
	private String lastName; 
	private String contactNumber;  
	
	public PersonalTrainer(int trainerId, String firstName, String lastName, String contactNumber) {
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.contactNumber = contactNumber;  
		//finder(trainerId); 
	} 
	
	public PersonalTrainer() {
		
	}
	
	
//	//identity map pattern 
//	public PersonalTrainer finderSingleton(int id) {
//		PersonalTrainer pt = new PersonalTrainer(); 
//		IdentityMap<PersonalTrainer> map = IdentityMap.getInstance(pt); 
//		pt = map.get(id); 
//			
//		if(pt == null) {
//			PersonalTrainer ptNew = new PersonalTrainer();
//			Record record = new Record(gateway.find(id));  
//			ptNew.setTrainerId(record.getTrainerId()); 
//			ptNew.setFirstName(record.getFirstName());
//			ptNew.setLastName(record.getLastName());  
//			ptNew.setContactNumber(record.getContactNumber());  
//			map.put(id, ptNew); 
//			return ptNew;
//		} else {
//			return pt; 
//		}
//	}
	/*
	public int finder(int trainerId) {
		String sql = "SELECT id FROM personaltrainer WHERE firstName=" + getFirstName() + " AND lastName=" + getLastName(); 
		PreparedStatement stmt = DBConnection.prepare(sql);
		try {
			ResultSet rs = stmt.executeQuery(); 
			if(rs != null) {
				int dbId = rs.getInt(1);
				this.trainerId = dbId; 
			} else {
				this.trainerId = trainerId; 
			}
		} catch (SQLException e) {
			System.out.println(e); 
		}
	} */
	
	public void setTrainerId(int id) {
		this.trainerId = id; 
	} 
	
	public void setFirstName(String firstName) {
		this.firstName = firstName; 
	} 
	
	public void setLastName(String lastName) {
		this.lastName = lastName; 
	}  
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber; 
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

}
