package domain;

import java.util.HashMap;
import java.util.Map;

public class Wish {
	/*
	 * 유저 id 와 장바구니를 저장할 전역 변수
	 */
	private static Map<String, Wish> userWish = new HashMap<String, Wish>();
	public static Map<String, Wish> getUserWish(){ return userWish; }
	
	/*
	 * 도서 코드와 구매할 수량을 저장할 wishList
	 */
	private Map<Integer, Integer> wishList = new HashMap<Integer, Integer>();
	
	
	/*
	 * wisiList getter setter
	 */
	public void setWishList(Map<Integer, Integer> wishList) {
		this.wishList = wishList;
	}
	public Map<Integer, Integer> getWishList(){ return wishList; }
	
	
	
	// 장바구니 리스트 출력
	public void getInfo() {
		// foreach wishList key값을 꺼내며 반복
		for(int key : wishList.keySet()) {
			Book book = Shelf.getShelf().get(key);		//해당 책 코드로 책장에 있는 책의 정보를 가져옴
			System.out.printf("%d %8s %8s %8s %8s\n", 	//책코드, 책 제목, 저자, 가격, 구매수량 출력 
					key,
					book.getBookName(),
					book.getAuthor(),
					Integer.toString(book.getPrice()), 
					Integer.toString(wishList.get(key)));
		}
		System.out.println("총 금액 : "+getTotal());	//	장바구니의 총 금액 출력
	}
	
	
	// 장바구니의 총 금액 추출
	public int getTotal() {
		int total = 0;
		// foreach wishList key값을 꺼내며 반복
		for(int key : wishList.keySet()) {
			// 책장에저장된 책의 가격과 구매희망하는 수량을 곱한후 total에 더함
			total += Shelf.getShelf().get(key).getPrice()* wishList.get(key);	
		}
		return total;
	}
	
}
