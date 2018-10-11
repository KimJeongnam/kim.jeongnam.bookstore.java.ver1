package presentation;

import service.HostImpl;

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
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			default:
				System.err.println("Error 메뉴 번호 입력 에러!");
				break;
			}
		}
	}

}
