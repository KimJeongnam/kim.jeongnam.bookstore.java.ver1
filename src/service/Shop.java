package service;

import java.util.HashMap;
import java.util.Map;

import domain.Book;
import domain.Code;
import domain.Shelf;
import presentation.GuestMenu;
import presentation.HostMenu;
import presentation.HostOrderMenu;
import presentation.HostStockMenu;
import presentation.Menu;
import presentation.ShopMenu;

public class Shop {
    private static int code;

    // 외부에서 코드를 셋할수 있도록 setter 정의
    public static void setCode(int code) {
        Shop.code = code;
    }

    public Shop(boolean debug) {
        // key 코드 value = 메뉴
        Map<Integer, Menu> map = new HashMap<Integer, Menu>();

        // map 에 shopmenu, hostmenu, hostStockMenu, GuestMenu 추가
        map.put(Code.SHOP_LOGIN, new ShopMenu());
        map.put(Code.HOST_MENU, new HostMenu());
        map.put(Code.HOST_STOCK_MENU, new HostStockMenu());
        map.put(Code.HOST_ORDER_MENU, new HostOrderMenu());
        map.put(Code.GUEST_MENU, new GuestMenu());

        // test Book
        Book book1 = new Book();
        book1.setBookName("나의라임오렌지나무");
        book1.setAuthor("홍길동");
        book1.setPrice(35000);
        book1.setStock(250);
        Shelf.bookAdd(book1);

        // 반복문 > 코드가 map 에 있으면 실행 없다면 종료.
        for (code = Code.SHOP_LOGIN; map.containsKey(code);) {
            try {
                map.get(code).execute(); // Menu Interface 의 execute() 실행

            } catch (Exception e) {
                if (debug)
                    e.printStackTrace();
                System.err.println(e.getMessage());
                code = Code.SHOP_LOGIN;
            }
            if (debug)
                System.out.println(code);
        }

        System.out.println("프로그램 종료");
    }
}
