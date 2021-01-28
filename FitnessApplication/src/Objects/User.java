package Objects;

public abstract class User {
	protected String firstName; 
	protected String lastName; 
	protected String contactNumber; 
	protected String username;
	protected String password;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

}
