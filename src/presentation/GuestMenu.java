package presentation;

import domain.Code;
import domain.Login;
import domain.Wish;
import service.GuestImpl;
import service.Shop;

public class GuestMenu implements Menu{
	private static Wish wish = null;       // 장바구니 정적 변수
	
	public static void initWish() { wish = new Wish();}    // 장바구니 초기화
	public static Wish getWish() { return wish; }
	
	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		System.out.println(Menu.HEADER_BAR+"고객 메뉴"+Menu.HEADER_BAR);
		System.out.printf("%8s%8s%8s%8s%8s\n", "1.장바구니", "2.구매", " 3.구매요청 목록", "4.환불", "5.로그아웃");
		System.out.println(Menu.FOOTER_BAR);
		System.out.print(Menu.INPUT_STR);
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
	    String id = "";
	    // 로그인에 세션이 null 값이 아닐때의 처리
        if(Login.getSession() != null){ 
            id = Login.getSession().getMap().get("id"); // session에서 id를 불러온다.
            
            if(!Wish.getUserWish().containsKey(id)) {  // user의 id가 해당 맵에 없다면
                wish = new Wish();                              // 새로운 장바구니 생성 후 put
                Wish.getUserWish().put(id, wish);          
            }
            else 
                wish = Wish.getUserWish().get(id);
        }
            
        
        
		String option = "";
		
		while(!option.equals("5")) {
			printMenu();
			option = Console.input();
			
			switch(option) {
			case "1":
				new GuestWishMenu().execute();      //장바구니 메뉴 실행
				break;
			case "2":
				GuestImpl.getInstance().nowBuy();   //바로구매
				break;
			case "3":
				GuestImpl.getInstance().buyAskList();   //구매 요청 목록
				break;
			case "4":
				GuestImpl.getInstance().refund();       // 환불
				break;
			case "5":
				Login.getSession().getMap().clear();    // 세션 로그아웃
				Shop.setCode(Code.SHOP_LOGIN);
				break;
				default:
					System.err.println("Error 메뉴 번호 입력 에러!");
					break;
			}
		}
	}

}
