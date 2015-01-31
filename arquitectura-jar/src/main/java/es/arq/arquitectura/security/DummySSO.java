package es.arq.arquitectura.security;

import java.util.HashMap;
import java.util.Map;

class SSOUser {
	private String username;
	private String password;
	private String[] profiles;

	public SSOUser() {
	}

	public SSOUser(String username, String password, String[] profiles) {
		super();
		this.username = username;
		this.password = password;
		this.profiles = profiles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}

	public String[] getProfiles() {
		return profiles;
	}

	public void setProfiles(String[] profiles) {
		this.profiles = profiles;
	}

}

public class DummySSO {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Map<String, SSOUser> users = new HashMap();
	static {
		String[] profiles1 = { "CLR 1", "CLR 2"};
		users.put("user1", new SSOUser("user1", "password", profiles1));
		String[] profiles2 = { "CLR 1" };
		users.put("user2", new SSOUser("user2", "password", profiles2));
		String[] profiles3 = { "CLR 1", "CLR 2", "CLR 3" };
		users.put("user3", new SSOUser("user3", "password", profiles3));
		String[] profiles4 = { "CLR 1 - CLR 1", "CLR 2 - CLR 1", "CLR 3 - CLR 1", "CLR 4 - CLR 1", "CLR 5 - CLR 1",
				"CLR 1 - CLR 2", "CLR 1 - CLR 3", "CLR 1 - CLR 4", "CLR 2 - CLR 2","CLR 2 - CLR 3", "CLR 2 - CLR 4",
				"CLR 3 - CLR 2","CLR 3 - CLR 3","CLR 3 - CLR 4","CLR 4 - CLR 2","CLR 4 - CLR 3","CLR 4 - CLR 4"};
		users.put("user4", new SSOUser("user4", "password", profiles4));
	}

	public static Map<String, SSOUser> getUsers() {
		return users;
	}

	public static void setUsers(Map<String, SSOUser> users) {
		DummySSO.users = users;
	}

	public static boolean validate(String username, String password) {
		boolean result = false;
		SSOUser u = users.get(username);
		if (u != null && u.getPassword().equals(password)) {
			result = true;
		}
		return result;
	}

}
