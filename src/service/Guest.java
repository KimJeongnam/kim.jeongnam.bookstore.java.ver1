package service;

public interface Guest {
	
	public void wishList();    
	public void cartAdd() throws Exception;
	public void cartDel();
	
	public void buy() throws Exception;	
	public void buyAskList();
	public void nowBuy() throws Exception;
	public void refund();	// 환불
}
