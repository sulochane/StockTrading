package StockTradingServer;

import java.util.Date;



public class Order {
	private int orderId;
	private int brokerId;
	private int stockId;
	private int amount;
	private Date dateIssued;
	private Date dateExpiration;
	private int statusId;
	private int typeId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
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

	public Date getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		String out = "Order: ";
		String delimiter = " ";
		String endOfString = "";

		out += "OrderId: " + this.orderId + delimiter;
		out += "BrokerId: " + this.brokerId + delimiter;
		out += "StockId: " + this.stockId + delimiter;
		out += "Amount: " + this.amount + delimiter;
		out += "DateIssued: " + this.dateIssued + delimiter;
		out += "DateExpiration: " + this.dateExpiration + delimiter;
		out += "DateExpiration: " + this.dateExpiration + delimiter;
		out += "StatusId: " + this.statusId + delimiter;
		out += "TypeId: " + this.typeId + delimiter;
		out += endOfString;

		return out;
	}

}
