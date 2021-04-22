package movieapp;

/**
 *
 * @author Wilderness
 */
public class User {

	String username;
	int userid;
	String password;

	public User(String username, int userid, String pw) {
		this.username = username;
		this.userid = userid;
		this.password = pw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserid() {
		return userid;
	}

}