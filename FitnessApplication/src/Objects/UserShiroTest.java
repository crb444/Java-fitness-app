package Objects;

public class UserShiroTest {
	private String username;
	private String password;
	private int id;
	
	private static int count = 0;
	
	public UserShiroTest(String username, String password, int id) {
		this.username = username;
		this.password = password;
		this.id = id;
		
	}
	
	public static UserShiroTest getUserShiroTest(String username) {
		if (username != "badusername")
			return new UserShiroTest(username, "somepassword", count++);
		else 
			return null;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getId() {
		return id;
	}
}