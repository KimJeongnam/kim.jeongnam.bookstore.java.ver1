package presentation;

import domain.Code;
import service.HostImpl;
import service.Shop;

public class HostOrderMenu implements Menu {
	private static HostOrderMenu hostOrderMenu = new HostOrderMenu();

	public static HostOrderMenu getInstance() {
		return hostOrderMenu;
	}

	@Override
	public void printMenu() {
		System.out.println(Menu.HEADER_BAR + "주문 관리" + Menu.HEADER_BAR);
		System.out.printf("%8s %8s %8s %8s %8s\n", "1.주문목록", "2.결제 승인", "3.결제취소", "4.결산", "5.이전");
		System.out.println(Menu.FOOTER_BAR);
		System.out.print(Menu.INPUT_STR);
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		String option = "";

		while (!option.equals("5")) {
			printMenu();

			option = Console.input();

			switch (option) {
			case "1":
				HostImpl.getInstance().orderList();
				break;
			case "2":
				HostImpl.getInstance().orderConfirm();
				break;
			case "3":
				HostImpl.getInstance().orderCancle();
				break;
			case "4":
				HostImpl.getInstance().saleTotal();
				break;
			case "5":
				break;
			default:
				System.err.println("Error 메뉴 번호 입력 에러!");
				break;
			}
		}
		Shop.setCode(Code.HOST_MENU);
	}

}
