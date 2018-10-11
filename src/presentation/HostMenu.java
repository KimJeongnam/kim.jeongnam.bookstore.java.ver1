package presentation;

import domain.Code;
import service.Shop;

public class HostMenu implements Menu {

	@Override
	public void printMenu() {
		System.out.println("----------------관리자 메뉴---------------");
		System.out.printf("%8s %8s %8s\n", "1.재고관리", "2.주문관리", "3.로그아웃");
		System.out.println("-------------------------------------");
		System.out.print(Menu.INPUT_STR);
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		String option = null;

		int code = Code.HOST_MENU;

		printMenu();

		option = Console.input();

		switch (option) {
		case "1":
			code = Code.HOST_STOCK_MENU;
			break;
		case "2":
			code = Code.HOST_ORDER_MENU;
			break;
		case "3":
			code = Code.SHOP_LOGIN;
			break;
		default:
		    System.err.println("메뉴선택 에러. 목록에없는 메뉴입니다.");
		    break;
		}

		Shop.setCode(code);
	}
}
