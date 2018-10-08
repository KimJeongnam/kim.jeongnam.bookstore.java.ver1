package presentation;

import domain.Code;
import service.Shop;

public class ShopMenu implements Menu{
	private final int code = Code.SHOP_LOGIN;

	public int getCode() {
		return code;
	}

	public void Header() {
		System.out.println("----------------------로그인----------------------");
		System.out.println("\t1.고객\t2.관리자\t3.회원가입\t4.종료");
		System.out.println("-------------------------------------------------");
		System.out.print("\t메뉴 번호를 입력하세요 : ");
	}
	
	@Override
	public void execute() throws Exception{
		// TODO Auto-generated method stub
		Header();
		String option="";
		int result;
		option = Console.input();
		
		switch(option) {
		case "1":	result =  Code.GUEST_MENU; break;
		case "2":	result = Code.HOST_LOGIN; break;
		case "3":	result =  0; break;
		case "4":
			result = 0; break;
		default:
			throw new Exception("메뉴 선택 에러");
		}
		

		Shop.setOption(result);
	}

}
