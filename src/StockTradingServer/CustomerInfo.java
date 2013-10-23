package StockTradingServer;

/*
 * Dmitriy Karmazin
 * CSCI-6545
 */

public class CustomerInfo {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private int statusId;

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

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Validator validate() {

		InputValidation iv = new InputValidation();
		Validator vResult = new Validator();
		Validator vFirstName, vLastName, vEmail, vPhone, vStatusId;

		Boolean verified = true;
		String status = "";

		// 1. first name
		vFirstName = iv.validateString(this.getFirstName(), "First Name");
		verified &= vFirstName.isVerified();
		status += vFirstName.getStatus();

		// 2. last name
		vLastName = iv.validateString(this.getLastName(), "Last Name");
		verified &= vLastName.isVerified();
		status += vLastName.getStatus();

		// 3. email
		vEmail = iv.validateEmail(this.getEmail(), "Email");
		verified &= vEmail.isVerified();
		status += vEmail.getStatus();

		// 4. phone
		vPhone = iv.validateString(this.getPhone(), "Phone");
		verified &= vPhone.isVerified();
		status += vPhone.getStatus();

		// 5. phone
		vStatusId = iv.validateIntGeneral(this.getStatusId(), "StatusId");
		verified &= vStatusId.isVerified();
		status += vStatusId.getStatus();

		vResult.setVerified(verified);
		vResult.setStatus(status);

		return vResult;
	}

	@Override
	public String toString() {
		String out = "CustomerInfo: ";
		String delimiter = " ";
		String endOfString = "\n";

		out += "ID: " + this.getId() + delimiter;
		out += "FistName: " + this.getFirstName() + delimiter;
		out += "LastName: " + this.getLastName() + delimiter;
		out += "Email: " + this.getEmail() + delimiter;
		out += "Phone: " + this.getPhone() + delimiter;
		out += "StatusId: " + this.getStatusId() + delimiter;

		out += endOfString;

		return out;
	}

}
