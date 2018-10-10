package service;

import java.util.HashMap;
import java.util.Map;

import domain.Book;
import domain.Code;
import presentation.GuestMenu;
import presentation.HostMenu;
import presentation.HostStockMenu;
import presentation.Menu;
import presentation.ShopMenu;

public class Shop {
	private static int code;
	
	public static void setCode(int code) {
		Shop.code = code;
	}
	
	public Shop(boolean debug) {
		Map<Integer, Menu> map = new HashMap<Integer, Menu>();
		map.put(Code.SHOP_LOGIN, new ShopMenu());
		map.put(Code.HOST_MENU, new HostMenu());
		map.put(Code.HOST_STOCK_MENU, new HostStockMenu());
		map.put(Code.GUEST_MENU, new GuestMenu());
		
		//test Book
		Book book1 = new Book();
        book1.setBookName("나의라임오렌지나무");
        book1.setAuthor("홍길동");
        book1.setPrice(35000);
        book1.setStock(250);
        HostImpl.getInstance().bookAdd(book1);
		
		for(code = Code.SHOP_LOGIN; map.containsKey(code);) {
			try {
			map.get(code).execute();
			
			if(code == Code.ERROR) {
				System.err.println("Error!");
			}
			}catch(Exception e) {
				if(debug)
					e.printStackTrace();
				System.err.println(e.getMessage());
				code = Code.SHOP_LOGIN;
			}
			if(debug)
			    System.out.println(code);
		}
		
		System.out.println("프로그램 종료");
	}
}
