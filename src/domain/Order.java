package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
	public static ArrayList<Map<Object, Object>> orderList = new ArrayList<Map<Object, Object>>();
	
	private static Map<Object, Object> order =null;
	
	public static void addOrder(String id, String buyCode, 
			Map<Integer, Integer>orderlsit) {
		order = new HashMap<Object, Object>();
		order.put("id", id);
		order.put("buyCode", buyCode);
		order.put("orderList", orderlsit);
		order.put("status", false);
		order.put("cancle", false);
		orderList.add(order);
	}
	
	@SuppressWarnings("unchecked")
	public static void printOrderOnCallList(){
		if(orderList.isEmpty()) {
			System.err.println("주문 목록이 비어있습니다.");
			return;
		}
		
		for(Map<Object, Object> order : orderList) {
			if( !(boolean)order.get("status")) {
				System.out.println("요청인 : "+order.get("id")+" 구매 코드 : "+order.get("buyCode"));
				Map<Integer, Integer> books = (Map<Integer, Integer>)order.get("orderList");
				if(books.isEmpty()) {
					System.err.println("Error! orderlist is Empty");
					return;
				}
				System.out.println("{");
				System.out.printf("\t%s %10s %8s %8s \n","책 코드", "제목", "가격", "수량");
				for(int bookCode : books.keySet()) {
					Book book = null;
					if(Shelf.getShelf().containsKey(bookCode))
						book = Shelf.getShelf().get(bookCode);
					System.out.printf("\t%d",bookCode);
					System.out.printf(" %8s", book.getBookName());
					System.out.printf(" %8d", book.getPrice());
					System.out.printf(" %8d", books.get(bookCode));
					System.out.println(",");
				}
				System.out.println("}");
				
			}
		}
	}
	
	public static ArrayList<Map<Object, Object>> getBuyAskList(String id) {
		ArrayList<Map<Object, Object>> buyAsklist= new ArrayList<Map<Object, Object>>();
		
		for(Map<Object,Object> order: orderList) {
			if(order.get("id").equals(id) && !(boolean)order.get("status")) {
				Map<Object,Object> data = new HashMap<Object,Object>();
				data.put("buyCode", order.get("buyCode"));
				data.put("orderList", order.get("orderList"));
				buyAsklist.add(data);
			}
		}
		
		return buyAsklist;
	}
}

