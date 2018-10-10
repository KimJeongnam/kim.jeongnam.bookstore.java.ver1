package service;

public interface Guest {
	
	public void wishList();
	public void cartAdd() throws Exception;
	public void cartDel();
	
	public void wishBuy();
	public void buy();	
	public void nowBuy();
	public void orderList();	
	public void refund();	// 환불
}
