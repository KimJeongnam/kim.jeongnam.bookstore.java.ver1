package service;

public interface Guest {
	
	public void wishList();    // 장바구니 목록 
	public void cartAdd();    // 장바구니에 책 추가 
	public void cartDel();     // 장바구니 삭제
	
	public void buy() throws Exception;	   // 구매요청
	public void buyAskList();                     // 구매요청 목록
	public void nowBuy() throws Exception; // 바로구매
	public void refund();	                         // 환불
}
