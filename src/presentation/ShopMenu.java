package presentation;

import domain.Code;
import service.HostImpl;
import service.Login;
import service.Shop;

public class ShopMenu implements Menu {

	public void userAddMenu() {
		System.out.println(Menu.RESULT_HEADER+Menu.RESULT_HEADER);
		System.out.println("\t회원가입");
		System.out.println(Menu.RESULT_FOOTER);
	}
	
	@Override
    public void printMenu() {
        System.out.println(Menu.HEADER_BAR+"로그인"+Menu.HEADER_BAR);
        System.out.printf("%8s %8s %8s %8s\n","1.고객", "2.관리자", "3.회원가입", "4.종료");
        System.out.println(Menu.FOOTER_BAR);
        System.out.print(Menu.INPUT_STR);
    }

    @Override
    public void execute() throws Exception {
        // TODO Auto-generated method stub
        printMenu();
        String option = "";
        int code = Code.SHOP_LOGIN;
        option = Console.input();

        switch (option) {
        case "1":
        	code = Code.GUEST_MENU;
            break;
        case "2":
            Login login = new Login(Code.HOST_LOGIN);
            login.tryLogin();
            code = Code.HOST_MENU;
            break;
        case "3":
        	userAddMenu();
        	HostImpl.getInstance().userAdd();
            break;
        case "4":
        	code = Code.EXIT;
            break;
        default:
            throw new Exception("메뉴 선택 에러");
        }

        Shop.setCode(code);
    }

}
