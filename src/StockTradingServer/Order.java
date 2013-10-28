package StockTradingServer;


import java.sql.Timestamp;




public class Order {
	private int orderId;
	private int typeId;
	private int brokerId;
	private int customerId;
	private int stockId;
	private int amount;
	private double price;
	private Timestamp dateIssued;
	private Timestamp dateExpiration;
        private String displaySummary;


	private int statusId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
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

	public Timestamp getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(Timestamp dateIssued) {
		this.dateIssued = dateIssued;
	}

	public Timestamp getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Timestamp dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

        public String getDisplaySummary() {
            return displaySummary;
        }

        public void setDisplaySummary(String displaySummary) {
            this.displaySummary = displaySummary;
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
		Validator vTypeId, vBrokerId, vCustomerId, vStockId, vAmount, vPrice, vDateIssued, vDateExpiration, vStatusId;

		Boolean verified = true;
		String status = "";

		// 1. validate orderId
		vTypeId = iv.validateIntGeneral(this.getTypeId(), "Type");
		verified &= vTypeId.isVerified();
		status += vTypeId.getStatus();

		// 2. broker id
		vBrokerId = iv.validateIntGeneral(this.getBrokerId(), "Broker");
		verified &= vBrokerId.isVerified();
		status += vBrokerId.getStatus();

		// 3. customer id
		vCustomerId = iv.validateIntGeneral(this.getCustomerId(), "Customer");
		verified &= vCustomerId.isVerified();
		status += vCustomerId.getStatus();

		// 4. stock id
		vStockId = iv.validateIntGeneral(this.getStockId(), "Stock");
		verified &= vStockId.isVerified();
		status += vStockId.getStatus();

		// 5. amount
		vAmount = iv.validateDoubleGeneral(this.getAmount(), "Amount");
		verified &= vAmount.isVerified();
		status += vAmount.getStatus();

		// 6. price
		vPrice = iv.validateDoubleGeneral(this.getPrice(), "Price");
		verified &= vPrice.isVerified();
		status += vPrice.getStatus();

		// 7. date issued
		vDateIssued = iv.validateTimestampGeneral(this.getDateIssued(),
				"Date Issued");
		verified &= vDateIssued.isVerified();
		status += vDateIssued.getStatus();

		// 8. date issued
		vDateExpiration = iv.validateTimestampGeneral(this.getDateExpiration(),
				"Date Expiration");
		verified &= vDateExpiration.isVerified();
		status += vDateExpiration.getStatus();

		// 9. status id
		vStatusId = iv.validateIntGeneral(this.getStatusId(), "Status Id");
		verified &= vStatusId.isVerified();
		status += vStatusId.getStatus();

		vResult.setVerified(verified);
		vResult.setStatus(status);

		return vResult;
	}

	@Override
	public String toString() {
		String out = "Order: ";
		String delimiter = " ";
		String endOfString = "\n";

		out += "OrderId: " + this.getOrderId() + delimiter;
		out += "TypeId: " + this.getTypeId() + delimiter;
		out += "BrokerId: " + this.getBrokerId() + delimiter;
		out += "CustomerId: " + this.getCustomerId() + delimiter;
		out += "StockId: " + this.getStockId() + delimiter;
		out += "Amount: " + this.getAmount() + delimiter;
		out += "Price: " + this.getPrice() + delimiter;
		out += "DateIssued: " + this.getDateIssued() + delimiter;
		out += "DateExpiration: " + this.getDateExpiration() + delimiter;
		out += "StatusId: " + this.getStatusId() + delimiter;
		out += endOfString;

		return out;
	}
}
