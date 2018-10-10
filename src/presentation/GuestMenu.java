package presentation;

import domain.Login;
import domain.Wish;

public class GuestMenu implements Menu{
	private static Wish wish = null;
	
	public static Wish getWish() { return wish; }

	public GuestMenu() {
		
		String id = "";
		if(Login.getSession() != null)
			id = Login.getSession().getSession().get("id");
		if(!Wish.getUserWish().containsKey(id)) {
			wish = new Wish();
			Wish.getUserWish().put(id, wish);
		}
		else 
			wish = Wish.getUserWish().get(id);
	}
	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		System.out.println(Menu.HEADER_BAR+"고객 메뉴"+Menu.HEADER_BAR);
		System.out.printf("%8s%8s%8s%8s\n", "1.장바구니", "2.구매", "3.환불", "4.로그아웃");
		System.out.println(Menu.FOOTER_BAR);
		System.out.print(Menu.INPUT_STR);
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		String option = "";
		
		while(!option.equals("4")) {
			printMenu();
			option = Console.input();
			
			switch(option) {
			case "1":
				new GuestWishMenu().execute();
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				Login.getSession().getSession().clear();
				break;
				default:
					System.err.println("Error 메뉴 번호 입력 에러!");
					break;
			}
		}
	}

}
