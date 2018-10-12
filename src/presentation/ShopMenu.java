package presentation;

import domain.Code;
import domain.Login;
import service.HostImpl;
import service.Shop;

public class ShopMenu implements Menu {
	
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
    	Login login = null;
        printMenu();
        String option = "";
        int code = Code.SHOP_LOGIN;
        option = Console.input();
        
        switch (option) {
        case "1":
        	login = new Login(Code.GUEST_LOGIN);	// login 고객 모드
            login.tryLogin();
            new GuestMenu().execute();	// geustMenu 실행
            break;
        case "2":
            login = new Login(Code.HOST_LOGIN);		// login 호스트 모드
            login.tryLogin();
            code = Code.HOST_MENU;
            break;
        case "3":
        	HostImpl.getInstance().userAdd();		// 유저 추가 호출
            break;
        case "4":
        	code = Code.EXIT;
            break;
        default:
            throw new Exception("메뉴 선택 에러");
        }

        // Shop의 code를 Set함
        Shop.setCode(code);
    }

}
