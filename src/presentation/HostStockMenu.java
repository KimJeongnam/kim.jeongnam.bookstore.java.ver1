package presentation;

import domain.Code;
import service.HostImpl;
import service.Shop;

public class HostStockMenu implements Menu {
	private int thisCode;

	public HostStockMenu(int thisCode) {
		this.thisCode = thisCode;
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		System.out.println(Menu.HEADER_BAR + "제고 관리" + Menu.HEADER_BAR);
		System.out.println("\t1.목록\t2.추가\t3.수정\t4.삭제\t5.이전");
		System.out.println(Menu.FOOTER_BAR);
		System.out.print(Menu.INPUT_STR);
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		printMenu();

		String option = Console.input();
		int code = thisCode;

		switch (option) {
		case "1":
			printBookListMenu();
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

	public void printBookListMenu() {
		System.out.println(Menu.RESULT_HEADER + "도서 목록" + Menu.RESULT_HEADER);
		System.out.printf("%5s %5s %5s %5s %5s", "번호", "도서명", "저자", "가격", "수량\n");
		System.out.println(Menu.RESULT_FOOTER);
	}

	public void AddBookHeader() {
		System.out.println(Menu.RESULT_HEADER + "도서 등록" + Menu.RESULT_FOOTER);
	}

}
