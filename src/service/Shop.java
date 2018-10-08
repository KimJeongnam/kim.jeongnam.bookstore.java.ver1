package service;

import java.util.HashMap;
import java.util.Map;

import domain.Code;
import presentation.HostLoginMenu;
import presentation.HostMenu;
import presentation.ShopMenu;
import presentation.Menu;

public class Shop {
	private static int option;
	
	public static void setOption(int option) {
		Shop.option = option;
	}
	
	public Shop(boolean debug) {
		Map<Integer, Menu> map = new HashMap<Integer, Menu>();
		map.put(Code.SHOP_LOGIN, new ShopMenu());
		map.put(Code.HOST_LOGIN, new HostLoginMenu());
		map.put(Code.HOST_MENU, HostMenu.getInstance());
		
		
		for(option = Code.SHOP_LOGIN; map.containsKey(option);) {
			try {
			map.get(option).execute();
			}catch(Exception e) {
				if(debug)
					e.printStackTrace();
				System.err.println(e.getMessage());
				option = Code.SHOP_LOGIN;
			}
			System.out.println(option);
		}
		
		System.out.println("프로그램 종료");
	}
}
