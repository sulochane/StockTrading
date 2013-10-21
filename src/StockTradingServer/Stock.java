package StockTradingServer;

public class Stock {
	private int id;
	private String name;
	private int amount;
	private int price;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
