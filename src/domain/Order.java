package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Order {
	/*
	 * 주문 목록을 저장하는 전역 변수 HashMap
	 * String => 주문 코드
	 * 
	 * Map<Object, Object> =
	 * 			id, Map<Integer, Integer> 타입의 주문리스트, 결제상태, 취소요청 의 정보를 담을수 있다.
	 */
	public static Map<String, Map<Object, Object>> orderList = new HashMap<String, Map<Object, Object>>();
	public static Set<String> buyCodeSet = new HashSet<String>();

	public static String addOrder(String id, Map<Integer, Integer> orderlsit) {

		Random random = new Random();
		int buyCode = random.nextInt(10000);
		String strBuyCode = Integer.toString(buyCode);

		while (orderList.containsKey(strBuyCode)) {
			buyCode = random.nextInt(10000);
			strBuyCode = Integer.toString(buyCode);
		}

		Map<Object, Object> order = new HashMap<Object, Object>();
		order.put("id", id);					// 요청자 
		order.put("orderList", orderlsit);		// 주문 목록<책코드, 수량>
		order.put("status", false);				// 결제 완료 상태
		order.put("refund", false);				// 환불 요청
		orderList.put(strBuyCode, order);

		return strBuyCode;
	}
	
	// 주문목록 출력 메소드 
	@SuppressWarnings("unchecked")
	private static void printOrder(String buyCode, Map<Object, Object> order) {
		System.out.println("요청인 : " + order.get("id") + " 구매 코드 : " + buyCode);
		Map<Integer, Integer> books = (Map<Integer, Integer>) order.get("orderList");
		
		if (books.isEmpty()) {
			System.err.println("pinrtOrder()->Error! orderlist is Empty");
			return;
		}
		
		System.out.println("{");
		System.out.printf("\t%s %10s %8s %8s \n", "책 코드", "제목", "가격", "수량");
		/*
		 * 주문목록 반복문
		 */
		for (int bookCode : books.keySet()) {			
			Book book = null;
			if (!Shelf.getShelf().containsKey(bookCode)) {
				System.out.println("pinrtOrder()->Shlef.get() Error!");
				break;
			}
			
			// 책 코드를 이용하여 책장에서 책을 꺼내어 정보 출력
			book = Shelf.getShelf().get(bookCode);
			System.out.printf("\t%d", bookCode);
			System.out.printf(" %8s", book.getBookName());
			System.out.printf(" %8d", book.getPrice());
			System.out.printf(" %8d", books.get(bookCode));
			System.out.println(",");
		}
		System.out.println("}");
	}

	/*
	 * 주문 리스트 전체 출력 메소드
	 */
	@SuppressWarnings("unchecked")
	public static void printOrderOnCallList() {
		// 주문리스트가 비어있다면 에러 출력 후 함수 죵료 
		if (orderList.isEmpty()) {
			System.err.println("주문 목록이 비어있습니다.");
			return;
		}

		/*
		 * 주문 리스트 에서 주문 코드를 꺼내어 buyCode에 대입하며 반복
		 */
		for (String buyCode : orderList.keySet()) {
			/*
			 * order가 가지고있는키 = {
			 * 		id : 요청자, 
			 * 		Map<Integer, Integer>orderList : 주문 목록<책코드, 수량>, 
			 * 		status : 결제완료 상태
			 * 		refund : 취소 요청
			 * }
			 * 
			 */
			Map<Object, Object> order = orderList.get(buyCode);	// 주문 내용을 꺼내  order에 담는다.

			/*
			 * 만약 status(결제완료 상태)가 false이면 
			 * printOrder 메소드를 호출하며 매개변수로 buyCode(주문 코드), order(주문내용)을 넘겨준다. 
			 */
			if (!(boolean) order.get("status")) {
				printOrder(buyCode, order);
			}
		}
	}
	

	/*
	 * 구매목록 반환 메소드 (매개변수  : 요청 아이디)
	 * 반환형 : ArrayList<Map<Object, Object>> 
	 */
	public static ArrayList<Map<Object, Object>> getBuyList(String id) {
		//구매목록을 생성한다.
		ArrayList<Map<Object, Object>> buyList = new ArrayList<Map<Object, Object>>();

		for (String buyCode : orderList.keySet()) {
			Map<Object, Object> order = orderList.get(buyCode);

			/*
			 * order에서 꺼낸 id와 매개변수의 id와 같고,
			 * order에서 꺼낸 status(결제완료상태)가 true 이며,
			 * order에서 꺼낸 refund(환불요청 상태)가  false 일때
			 * 
			 * 주문정보을 생성하고 주문코드와 주문목록을 담아 List에 추가한다.
			 */
			if (order.get("id").equals(id) && (boolean) order.get("status") && !(boolean)order.get("refund")) {
				Map<Object, Object> data = new HashMap<Object, Object>();
				data.put("buyCode", buyCode);
				data.put("orderList", order.get("orderList"));
				buyList.add(data);
			}
		}
		
		// 생성한 구매목록 주소값 반환
		return buyList;
	}

	/*
	 * 구매요청 목록 리스트 반환 메소드 (매개변수  : 요청 아이디)
	 * 반환형 : ArrayList<Map<Object, Object>>
	 */
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
	
	/*
	 * Host
	 * 취소 요청 목록 출력 메소드
	 */
	public static void printCancleAskList() {
		for(String buyCode : orderList.keySet()) {
			Map<Object, Object> order = orderList.get(buyCode);
			
			//취소 요청상태가 true면 
			if((boolean)order.get("refund")) {
				printOrder(buyCode, order);
			}
		}
	}

	/*
	 * Guest
	 * 구매 목록 모두 취소 요청 메소드 (매개변수 : 요청자 아이디) 
	 */
	public static void refundAskAllAction(String id) {
		for (String buyCode : orderList.keySet()) {
			refundAskAction(id, buyCode);
		}
	}

	/*
	 * Guest
	 * 구매 목록 취소 요청 메소드 (매개변수 : 요청자 아이디, 주문 코드) 
	 */
	public static void refundAskAction(String id, String buyCode) {
		// 주문목록에 해당 주문코드가 없다면 리턴
		if (!orderList.containsKey(buyCode)) {
			return;
		}
		Map<Object, Object> order = orderList.get(buyCode);
		
		// 주문취소 상태가 참이면 리턴
		if ((boolean) order.get("refund"))
			return;

		// 매개변수로 받은 요청자와 order에서 가져온 id가 다르다면 리턴
		String userId = (String) order.get("id");
		if (!userId.equals(id)) {
			return;
		}

		// 취소 요청상태 true 로 변경
		order.put("refund", true);
		System.out.println("구매코드 (" + buyCode + ") 취소 요청 완료");
	}
	
	
	/*
	 * Host
	 *  취소 요청이온 모든 주문목록 취소 메소드 
	 */
	public static void orderCancleAllAction() {
		for(String buyCode : orderList.keySet()) {
			orderCancleAction(buyCode);
		}
	}

	/*
	 * 	Host
	 *  취소 요청 취소처리 메소드(매개변수 : 주문코드)
	 */
	public static void orderCancleAction(String buyCode) {
		if(!orderList.containsKey(buyCode)) {
			return;
		}
		Map<Object, Object> data = orderList.get(buyCode);

		// 취소요청이 참이면
		if ((boolean) data.get("refund")) {
			@SuppressWarnings("unchecked")
			Map<Integer, Integer> orders = (Map<Integer, Integer>)data.get("orderList");
			if(!orderListCancle(orders)) {
				System.err.println("취소요청 cancle 코드 : "+buyCode);
				return;
			}
			orderList.remove(buyCode);
			System.out.println("주문번호 : "+buyCode+"취소 처리 완료");
		}
	}
	
	/*
	 * 주문목록 의 수량을 책장의 책목록에 다시 채우는 메소드
	 */
	private static boolean orderListCancle(Map<Integer, Integer> orders) {
		for(int bookCode: orders.keySet()) {
			if(!Shelf.getShelf().containsKey(bookCode)) {
				System.err.println("[Error] 해당 책코드의 목록이 책장에 없습니다. 책 코드 : "+bookCode);
				return false;
			}
			Book book = Shelf.getShelf().get(bookCode);
			int stock = orders.get(bookCode);
			book.setStock(book.getStock()+stock);
		}
		return true;
	}

	/*
	 * 총 판매량 반환 메소드
	 */
	@SuppressWarnings("unchecked")
	public static int saleTotal() {
		int total = 0;

		for (String buyCode : orderList.keySet()) {
			Map<Object, Object> data = orderList.get(buyCode);
			if ((boolean) data.get("status")) {				// 주문 완료상태가 참이면 실행
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
