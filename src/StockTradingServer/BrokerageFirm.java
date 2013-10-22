package StockTradingServer;

public class BrokerageFirm {

	private int id;
	private String name;
	private String addressStreet;
	private String addressCity;
	private String addressState;
	private String addressZip;
	private String licenceNumber;
	private int status;

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

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String out = "BrokerageFirm: ";
		String delimiter = " ";
		String endOfString = "";

		out += "ID: " + this.id + delimiter;
		out += "Name: " + this.name + delimiter;
		out += "Street: " + this.addressStreet + delimiter;
		out += "City: " + this.addressCity + delimiter;
		out += "State: " + this.addressState + delimiter;
		out += "Zip: " + this.addressZip + delimiter;
		out += "Licence: " + this.licenceNumber + delimiter;
		out += "Status: " + this.status + delimiter;

		out += endOfString;

		return out;
	}

	public Validator validate() {

		InputValidation iv = new InputValidation();
		Validator vResult = new Validator();
		Validator vName, vAddressStreet, vAddressCity, vAddressState, vAddressZip, vLicenceNumber, vStatus;

		Boolean verified = true;
		String status = "";

		// 1. validate name
		vName = iv.validateString(this.getName(), "Name");
		verified &= vName.isVerified();
		status += vName.getStatus();

		// 2. validate addressStreet
		vAddressStreet = iv.validateString(this.getAddressStreet(),
				"Address Street");
		verified &= vAddressStreet.isVerified();
		status += vAddressStreet.getStatus();

		// 3. validate addressCity
		vAddressCity = iv.validateString(this.getAddressCity(), "Address City");
		verified &= vAddressCity.isVerified();
		status += vAddressCity.getStatus();

		// 4. validate addressState
		vAddressState = iv.validateString(this.getAddressState(),
				"Address State");
		verified &= vAddressState.isVerified();
		status += vAddressState.getStatus();

		// 5. validate addressZip
		vAddressZip = iv.validateString(this.getAddressZip(), "Address Zip");
		verified &= vAddressZip.isVerified();
		status += vAddressZip.getStatus();

		// 6. license number
		vLicenceNumber = iv.validateString(this.getLicenceNumber(),
				"Licence Number");
		verified &= vLicenceNumber.isVerified();
		status += vLicenceNumber.getStatus();

		// 7. status
		vStatus = iv.validateInt(this.getStatus(), "Status");
		verified &= vStatus.isVerified();
		status += vStatus.getStatus();

		vResult.setVerified(verified);
		vResult.setStatus(status);

		return vResult;

	}

}
