package domain;

import java.util.HashMap;
import java.util.Map;

public class Wish {
	private static Map<String, Wish> userWish = new HashMap<String, Wish>();
	public static Map<String, Wish> getUserWish(){ return userWish; }
	
	private Map<Integer, Integer> wishList = new HashMap<Integer, Integer>();
	
	public void setWishList(Map<Integer, Integer> wishList) {
		this.wishList = wishList;
	}
	public Map<Integer, Integer> getWishList(){ return wishList; }
	
	
	
	// 장바구니 리스트 출력
	public void getInfo() {
		for(int key : wishList.keySet()) {
			Book book = Shelf.getShelf().get(key);
			System.out.printf("%d %8s %8s %8s %8s\n", 
					key,
					book.getBookName(),
					book.getAuthor(),
					Integer.toString(book.getPrice()), 
					Integer.toString(wishList.get(key)));
		}
		System.out.println("총 금액 : "+getTotal());
	}
	
	public void buyBook() throws Exception{
		
	}
	
	public void buyTotal(Wish wish) {
		
	}
	
	// 장바구니의 총 금액 추출
	public int getTotal() {
		int total = 0;
		for(int key : wishList.keySet()) {
			total += Shelf.getShelf().get(key).getPrice()* wishList.get(key);
		}
		return total;
	}
	
}
