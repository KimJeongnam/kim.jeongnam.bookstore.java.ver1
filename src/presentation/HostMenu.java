package presentation;

import domain.Code;
import service.Shop;

public class HostMenu implements Menu {
	private static HostMenu hostmenu = new HostMenu();

	public static HostMenu getInstance() {
		return hostmenu;
	}


	@Override
	public void printMenu() {
		System.out.println("----------------관리자 메뉴---------------");
		System.out.println("\t1.재고관리\t2.주문관리\t3.로그아웃");
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

			break;
		case "3":
			code = Code.SHOP_LOGIN;
			break;
		default:

			break;

		}

		Shop.setCode(code);
	}
}
