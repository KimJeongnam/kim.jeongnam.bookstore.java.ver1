package service;

import java.util.HashMap;
import java.util.Map;

import domain.Code;
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
		map.put(Code.HOST_MENU, HostMenu.getInstance());
		map.put(Code.HOST_STOCK_MENU, new HostStockMenu(Code.HOST_STOCK_MENU));
		
		
		for(code = Code.SHOP_LOGIN; map.containsKey(code);) {
			try {
			map.get(code).execute();
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
