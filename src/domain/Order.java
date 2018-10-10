package domain;

public class Order {
	private String buyCode;
	private Wish wish;
	private boolean status;
	
	public Order(String buyCode, Wish wish) {
		this.buyCode = buyCode;
		this.wish = wish;
	}
	
	public String getBuyCode() {
		return buyCode;
	}

	public void setBuyCode(String buyCode) {
		this.buyCode = buyCode;
	}

	public Wish getWish() {
		return wish;
	}

	public void setWish(Wish wish) {
		this.wish = wish;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}

