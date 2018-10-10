package service;

public interface Guest {
	
	public void wishList();
	public void cartAdd();
	public void cartDel();
	
	public void buy();	
	public void nowBuy();
	public void orderList();	
	public void refund();	// 환불
}
