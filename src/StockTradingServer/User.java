package StockTradingServer;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String ssn;
	private String password;
	private String salt;
	private int roleId;
	private int statusId;
	private int brokerFirmId;


	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
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

	public int getBrokerFirmId() {
		return brokerFirmId;
	}

	public void setBrokerFirmId(int brokerFirmId) {
		this.brokerFirmId = brokerFirmId;
	}

	public Validator validate() {

		InputValidation iv = new InputValidation();
		Validator vResult = new Validator();
		Validator vFirstName, vLastName, vEmail, vSsn, vPassword, vStatusId, vFirmId;

		Boolean verified = true;
		String status = "";

		// 1. validate first name
		vFirstName = iv.validateString(this.getFirstName(), "First Name");
		verified &= vFirstName.isVerified();
		status += vFirstName.getStatus();

		// 2. validate last name
		vLastName = iv.validateString(this.getLastName(), "First Name");
		verified &= vLastName.isVerified();
		status += vLastName.getStatus();

		// 3. validate email
		vEmail = iv.validateEmail(this.getEmail(), "Email");
		verified &= vEmail.isVerified();
		status += vEmail.getStatus();

		// 4. validate ssn
		vSsn = iv.validateString(this.getSsn(), "Ssn");
		verified &= vSsn.isVerified();
		status += vSsn.getStatus();

		// 5. validate password
		// vPassword = iv.validateString(this.getPassword(), "Password");
		// verified &= vPassword.isVerified();
		// status += vPassword.getStatus();

		// 6. status id
		vStatusId = iv.validateIntGeneral(this.getStatusId(), "StatusId");
		verified &= vStatusId.isVerified();
		status += vStatusId.getStatus();

		// 7. firm id
		vFirmId = iv.validateIntGeneral(this.getBrokerFirmId(), "FirmId");
		verified &= vFirmId.isVerified();
		status += vFirmId.getStatus();

		vResult.setVerified(verified);
		vResult.setStatus(status);

		return vResult;
	}

	@Override
	public String toString() {
		String out = "User: ";
		String delimiter = " ";
		String endOfString = "\n";

		out += "ID: " + this.getId() + delimiter;
		out += "FirstName: " + this.getFirstName() + delimiter;
		out += "LastName: " + this.getLastName() + delimiter;
		out += "Email: " + this.getEmail() + delimiter;
		out += "SSN: " + this.getSsn() + delimiter;
		out += "Password: " + this.getPassword() + delimiter;
		out += "Salt: " + this.getSalt() + delimiter;
		out += "RoleId: " + this.getRoleId() + delimiter;
		out += "StatusId: " + this.getStatusId() + delimiter;
		out += "FirmId: " + this.getBrokerFirmId() + delimiter;

		out += endOfString;

		return out;
	}

}
