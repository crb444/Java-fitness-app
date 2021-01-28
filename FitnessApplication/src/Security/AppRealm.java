package Security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import Objects.UserShiroTest;
import java.util.HashSet;
import java.util.Set;
public class AppRealm extends JdbcRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//identify account to log to
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();
		
		final UserShiroTest user = UserShiroTest.getUserShiroTest(username);
		if (user == null) {
			System.out.println("No user found for that username");
		}
		
		return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), user.getUsername());
	}
	
	@Override
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roles = new HashSet<>();
		if (principals.isEmpty()) {
			System.out.println("Given principals to authorize are empty");
			return null;
		}
		
		String username = (String) principals.getPrimaryPrincipal();
		final UserShiroTest user = UserShiroTest.getUserShiroTest(username);
		// workshop has ^^ username as an int??
		
		// add roles of the user according to its type
		roles.add(AppSession.AUTHOR_ROLE);
		
		return new SimpleAuthorizationInfo(roles);		
	}
}