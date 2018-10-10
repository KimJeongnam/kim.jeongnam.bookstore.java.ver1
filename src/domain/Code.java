package domain;

public interface Code {
	public static final int ERROR = -1;
	public static final int EXIT = 0;
	public static final int SHOP_LOGIN = 999;
	public static final int HOST_LOGIN = 101;
	public static final int GUEST_LOGIN = 201;
	
	// Host
	public static final int HOST_MENU = 100;	
	public static final int HOST_STOCK_MENU = 110;	// 제고 매뉴
	public static final int HOST_BOOK_LIST = 111;	// 제고 리스트
	public static final int HOST_BOOK_ADD = 112;	// 제고 추가
	public static final int HOST_BOOK_UPDATE = 113;	// 제고 수정
	
	public static final int HOST_ORDER_MENU = 120;	// 주문 메뉴
	public static final int HOST_ORDER_LIST = 121;	// 주문 목록
	public static final int HOST_ORDER_CONFIRM = 122; // 결제 승인
	public static final int HOST_ORDER_CANCEL = 123; // 결제 취소
	public static final int HOST_SALE_TOTAL = 124; 	// 결산
	
	
	public static final int GUEST_MENU = 200;
	public static final int GUEST_WISH_LIST = 210;	//장바구니 리스트
	public static final int GUEST_CART_ADD = 211;	//장바구니 담기
	public static final int GUEST_CART_DEL = 212;	//장바구니 삭제
	
	public static final int GUEST_BUY = 213; 		// 구매
	public static final int GUEST_NOWBUY = 220;		// 바로구매
	public static final int GUEST_ORDER_LIST = 221; // 장바구니 내부 목록
	public static final int GUEST_REFUND = 230;		// 환불
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}
