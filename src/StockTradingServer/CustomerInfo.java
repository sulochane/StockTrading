package StockTradingServer;

public class CustomerInfo {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		String out = "CustomerInfo: ";
		String delimiter = " ";
		String endOfString = "";

		out += "ID: " + this.id + delimiter;
		out += "FistName: " + this.firstName + delimiter;
		out += "LastName: " + this.lastName + delimiter;
		out += "Email: " + this.email + delimiter;
		out += "Phone: " + this.phone + delimiter;

		out += endOfString;

		return out;
	}

}
