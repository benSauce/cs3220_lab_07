package cs3220.model;

public class User {

	static int idSeed = 1;

	private int id;

	private String name;
	private String username;
	private char[] password;

	public User(String name, String username, char[] password) {
		this.id = idSeed++;
		this.name = name;
		this.username = username;
		this.password = password;

	}
	
	public User() {
		this.id = 0;
		this.name = "default";
		this.username = "default";
		this.password = new char[0];

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return this.password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

}
