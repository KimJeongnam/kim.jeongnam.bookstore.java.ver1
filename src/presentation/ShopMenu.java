package presentation;

import domain.Code;
import service.Login;
import service.Shop;

public class ShopMenu implements Menu {
    private final int code = Code.SHOP_LOGIN;

    public int getCode() {
        return code;
    }

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
        int result;
        option = Console.input();

        switch (option) {
        case "1":
            result = Code.GUEST_MENU;
            break;
        case "2":
            Login login = new Login(Code.HOST_LOGIN);
            login.tryLogin();
            result = Code.HOST_MENU;
            break;
        case "3":
            result = 0;
            break;
        case "4":
            result = 0;
            break;
        default:
            throw new Exception("메뉴 선택 에러");
        }

        Shop.setCode(result);
    }

}
