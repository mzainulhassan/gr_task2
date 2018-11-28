package entity;

public class Account {
	private int id;
	private String name;
	private String email;
	private String timezone;
	private String password;

	public Account() {
	}

	public Account(String name, String email, String timezone, String password) {
		this.name = name;
		this.email = email;
		this.timezone = timezone;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}