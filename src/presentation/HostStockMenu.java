package presentation;

import domain.Code;
import service.HostImpl;
import service.Shop;

public class HostStockMenu implements Menu {

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		System.out.println(Menu.HEADER_BAR + "제고 관리" + Menu.HEADER_BAR);
		System.out.printf("%8s %8s %8s %8s %8s\n", "1.목록", "2.추가", "3.수정", "4.삭제", "5.이전");
		System.out.println(Menu.FOOTER_BAR);
		System.out.print(Menu.INPUT_STR);
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		printMenu();

		String option = Console.input();
		int code = Code.HOST_STOCK_MENU;

		switch (option) {
		case "1":
			HostImpl.getInstance().bookList();
			break;
		case "2":
			AddBookHeader();
			HostImpl.getInstance().InputbookAdd();
			System.out.println(Menu.RESULT_FOOTER);
			break;
		case "3":
			HostImpl.getInstance().bookUpdate();
			break;
		case "4":
			HostImpl.getInstance().bookDelete();
			break;
		case "5":
			code = Code.HOST_MENU;
			break;
		default:
			System.err.println("Error 메뉴 번호 입력 에러!");
			break;
		}

		Shop.setCode(code);
	}

	public void AddBookHeader() {
		System.out.println(Menu.RESULT_HEADER + "도서 등록" + Menu.RESULT_FOOTER);
	}

}
