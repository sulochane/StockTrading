package StockTradingServer;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String salt;
	private int roleId;
	private int statusId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		String out = "User: ";
		String delimiter = " ";
		String endOfString = "";

		out += "ID: " + this.id + delimiter;
		out += "FirstName: " + this.firstName + delimiter;
		out += "LastName: " + this.lastName + delimiter;
		out += "Email: " + this.email + delimiter;
		out += "Password: " + this.password + delimiter;
		out += "Salt: " + this.salt + delimiter;
		out += "RoleId: " + this.roleId + delimiter;
		out += "StatusId: " + this.statusId + delimiter;

		out += endOfString;

		return out;
	}

}
