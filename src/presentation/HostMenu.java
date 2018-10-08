package presentation;

import domain.Code;
import service.Shop;

public class HostMenu implements Menu{
	private static HostMenu hostmenu = new HostMenu();

	public static HostMenu getInstance() { return hostmenu;}

	public void printMenu() {
		System.out.println("----------------관리자 메뉴-------------");
		System.out.println("\t1.재고관리\t2.주문관리\t3.로그아웃");
		System.out.println("-------------------------------------");
		System.out.print("\t메뉴 번호를 입력하세요. : ");
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		String option = null;
		
		printMenu();
		
		option  = Console.input();
		
		switch(option) {
		case "1":
		case "2":
		case "3":
			default:
				
		}
		
		Shop.setOption(Code.ERROR);
	}
}
