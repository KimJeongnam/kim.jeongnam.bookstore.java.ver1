package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Order {
	public static Map<String, Map<Object, Object>> orderList = new HashMap<String, Map<Object, Object>>();
	public static Set<String> buyCodeSet = new HashSet<String>();
	private static Map<Object, Object> order = null;

	public static String addOrder(String id, Map<Integer, Integer> orderlsit) {

		Random random = new Random();
		int buyCode = random.nextInt(10000);
		String strBuyCode = Integer.toString(buyCode);

		while (orderList.containsKey(strBuyCode)) {
			buyCode = random.nextInt(10000);
			strBuyCode = Integer.toString(buyCode);
		}

		order = new HashMap<Object, Object>();
		order.put("id", id);
		order.put("orderList", orderlsit);
		order.put("status", false);
		order.put("refund", false);
		orderList.put(strBuyCode, order);

		return strBuyCode;
	}
	
	@SuppressWarnings("unchecked")
	private static void printOrder(String buyCode, Map<Object, Object> order) {
		System.out.println("요청인 : " + order.get("id") + " 구매 코드 : " + buyCode);
		Map<Integer, Integer> books = (Map<Integer, Integer>) order.get("orderList");
		if (books.isEmpty()) {
			System.err.println("Error! orderlist is Empty");
			return;
		}
		System.out.println("{");
		System.out.printf("\t%s %10s %8s %8s \n", "책 코드", "제목", "가격", "수량");
		for (int bookCode : books.keySet()) {
			Book book = null;
			if (Shelf.getShelf().containsKey(bookCode))
				book = Shelf.getShelf().get(bookCode);
			System.out.printf("\t%d", bookCode);
			System.out.printf(" %8s", book.getBookName());
			System.out.printf(" %8d", book.getPrice());
			System.out.printf(" %8d", books.get(bookCode));
			System.out.println(",");
		}
		System.out.println("}");
	}

	@SuppressWarnings("unchecked")
	public static void printOrderOnCallList() {
		if (orderList.isEmpty()) {
			System.err.println("주문 목록이 비어있습니다.");
			return;
		}

		for (String buyCode : orderList.keySet()) {
			Map<Object, Object> order = orderList.get(buyCode);

			if (!(boolean) order.get("status")) {
				
				printOrder(buyCode, order);
			}
		}
	}
	

	public static ArrayList<Map<Object, Object>> getBuyList(String id) {
		ArrayList<Map<Object, Object>> buyList = new ArrayList<Map<Object, Object>>();

		for (String buyCode : orderList.keySet()) {
			Map<Object, Object> order = orderList.get(buyCode);

			if (order.get("id").equals(id) && (boolean) order.get("status") && !(boolean)order.get("refund")) {
				Map<Object, Object> data = new HashMap<Object, Object>();
				data.put("buyCode", buyCode);
				data.put("orderList", order.get("orderList"));
				buyList.add(data);
			}
		}

		return buyList;
	}

	public static ArrayList<Map<Object, Object>> getBuyAskList(String id) {
		ArrayList<Map<Object, Object>> buyAsklist = new ArrayList<Map<Object, Object>>();

		for (String buyCode : orderList.keySet()) {
			Map<Object, Object> order = orderList.get(buyCode);

			if (order.get("id").equals(id) && !(boolean) order.get("status")) {
				Map<Object, Object> data = new HashMap<Object, Object>();
				data.put("buyCode", buyCode);
				data.put("orderList", order.get("orderList"));
				buyAsklist.add(data);
			}
		}

		return buyAsklist;
	}
	
	public static void printCancleAskList() {
		for(String buyCode : orderList.keySet()) {
			Map<Object, Object> order = orderList.get(buyCode);
			
			if((boolean)order.get("refund")) {
				printOrder(buyCode, order);
			}
		}
	}

	public static void refundAskAllAction(String id) {
		for (String buyCode : orderList.keySet()) {
			refundAskAction(id, buyCode);
		}
	}

	public static void refundAskAction(String id, String buyCode) {
		if (!orderList.containsKey(buyCode)) {
			return;
		}
		Map<Object, Object> order = orderList.get(buyCode);

		if ((boolean) order.get("refund"))
			return;

		String userId = (String) order.get("id");
		if (!userId.equals(id)) {
			return;
		}

		order.put("refund", true);
		System.out.println("구매코드 (" + buyCode + ") 취소 요청 완료");
	}
	
	public static void orderCancleAllAction() {
		for(String buyCode : orderList.keySet()) {
			orderCancleAction(buyCode);
		}
	}

	public static void orderCancleAction(String buyCode) {
		if(!orderList.containsKey(buyCode)) {
			return;
		}
		Map<Object, Object> data = orderList.get(buyCode);

		if ((boolean) data.get("refund")) {
			@SuppressWarnings("unchecked")
			Map<Integer, Integer> orders = (Map<Integer, Integer>)data.get("orderList");
			orderListCancle(orders);
			orderList.remove(buyCode);
			System.out.println("주문번호 : "+buyCode+"취소 완료");
		}
	}
	
	private static void orderListCancle(Map<Integer, Integer> orders) {
		for(int bookCode: orders.keySet()) {
			Book book = Shelf.getShelf().get(bookCode);
			int stock = orders.get(bookCode);
			book.setStock(book.getStock()+stock);
		}
	}

	@SuppressWarnings("unchecked")
	public static int saleTotal() {
		int total = 0;

		for (String buyCode : orderList.keySet()) {
			Map<Object, Object> data = orderList.get(buyCode);
			if ((boolean) data.get("status")) {
				int listTotal = 0;

				Map<Integer, Integer> orders = (Map<Integer, Integer>) data.get("orderList");
				for (int bookCode : orders.keySet()) {
					int stock = orders.get(bookCode);
					int booksPrice = stock * Shelf.getShelf().get(bookCode).getPrice();
					listTotal += booksPrice;
				}
				total += listTotal;
			}
		}
		return total;
	}
}
