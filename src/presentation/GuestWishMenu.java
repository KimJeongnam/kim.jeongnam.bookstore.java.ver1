package presentation;

import service.GuestImpl;

public class GuestWishMenu implements Menu {

	@Override
	public void printMenu() {
		System.out.println(Menu.RESULT_HEADER + "장바구니 목록" + Menu.RESULT_HEADER);
		System.out.printf("%8s %8s %8s %8s %8s\n", "번호", "도서명", "저자", "가격", "수량");
		System.out.println(Menu.RESULT_FOOTER);
		GuestImpl.getInstance().wishList();
		System.out.println(Menu.HEADER_BAR + "장바구니" + Menu.HEADER_BAR);
		System.out.printf("%8s%8s%8s%8s\n", "1.추가", "2.삭제", "3.구매", "4.이전");
		System.out.println(Menu.FOOTER_BAR);
		System.out.print(Menu.INPUT_STR);
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		String option = "";

		while (!option.equals("4")) {
			printMenu();
			option = Console.input();
			
			switch (option) {
			case "1":
				GuestImpl.getInstance().cartAdd();
				break;
			case "2":
				GuestImpl.getInstance().cartDel();
				break;
			case "3":
				break;
			case "4":
				break;
			default:
				System.err.println("Error 메뉴 번호 입력 에러!");
				break;
			}
		}

	}

}
