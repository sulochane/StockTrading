package StockTradingServer;

public class Stock {
	private int id;
	private String name;
	private int amount;
	private double price;
	private int statusId;

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
		Validator vName, vAmount, vPrice, vStatus;

		Boolean verified = true;
		String status = "";

		// 1. validate name
		vName = iv.validateString(this.getName(), "Name");
		verified &= vName.isVerified();
		status += vName.getStatus();

		// 2. amount
		vAmount = iv.validateIntGeneral(this.getAmount(), "Amount");
		verified &= vAmount.isVerified();
		status += vAmount.getStatus();

		// 3. price
		vPrice = iv.validateDoubleGeneral(this.getPrice(), "Price");
		verified &= vPrice.isVerified();
		status += vPrice.getStatus();

		// 4. status id
		vStatus = iv.validateIntGeneral(this.getStatusId(), "Status");
		verified &= vStatus.isVerified();
		status += vStatus.getStatus();

		vResult.setVerified(verified);
		vResult.setStatus(status);

		return vResult;
	}

	@Override
	public String toString() {
		String out = "Stock: ";
		String delimiter = " ";
		String endOfString = "";

		out += "ID: " + this.id + delimiter;
		out += "name: " + this.name + delimiter;
		out += "amount: " + this.amount + delimiter;
		out += "price: " + this.price + delimiter;

		out += endOfString;

		return out;
	}

}
