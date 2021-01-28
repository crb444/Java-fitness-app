package Security;

import org.apache.shiro.SecurityUtils;
import Objects.UserShiroTest;

public class AppSession {
	public static final String USER_ATTRIBUTE_NAME = "user";
	public static final String CUSTOMER_ROLE = "customer";
	public static final String AUTHOR_ROLE = "author";
	
	public static boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}
	
	public static boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}
	
	public static void init(UserShiroTest user) {
		SecurityUtils.getSubject().getSession().setAttribute(USER_ATTRIBUTE_NAME, user);
	}
	
	public static UserShiroTest getUser() {
		return (UserShiroTest) SecurityUtils.getSubject().getSession().getAttribute(USER_ATTRIBUTE_NAME);
	}
	
}
