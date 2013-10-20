package StockTradingServer;

public class BrokerageFirm {

	private int id;
	private String name;
	private String addressStreet;
	private String addressCity;
	private String addressState;
	private String addressZip;
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		String out = "BrokerageFirm: ";
		String delimiter = " ";
		String endOfString = "\n";

		out += "ID: " + this.id + delimiter;
		out += "Name: " + this.name + delimiter;

		out += endOfString;

		return out;
	}

}
