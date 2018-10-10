package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import domain.Code;
import domain.Order;
import domain.Shelf;
import domain.Wish;
import presentation.Console;
import presentation.GuestMenu;
import presentation.Menu;

public class GuestImpl implements Guest {
	private static GuestImpl guestImpl = new GuestImpl();
	private Map<String, String> users = new HashMap<String, String>();
	private Map<String, ArrayList<Order>> orderMap = new HashMap<String, ArrayList<Order>>();

	private GuestImpl() {
	}

	public static GuestImpl getInstance() {
		return guestImpl;
	}

	public Map<String, String> getUsers() {
		return users;
	}

	public Map<String, ArrayList<Order>> getOrderMap() {
		return orderMap;
	}

	@Override
	public void wishList() {
		Wish wish = GuestMenu.getWish();
		wish.getInfo();
	}

	@Override
	public void cartAdd() throws Exception {
		// TODO Auto-generated method stub
		String strCode = "";
		HostImpl.getInstance().bookList();

		while (!strCode.equals("0")) {
			System.out.print("장바구니에 담을 책의 코드를 입력하세요. [이전:0] : ");
			strCode = Console.input();

			if (strCode.equals("0"))
				return;

			int code = Integer.parseInt(strCode);

			if (!Shelf.getShelf().containsKey(code))
				throw new Exception("error 책 목록에  없는 코드입니다.!");

			System.out.print("수량을 입력하세요 : ");
			String strStock = Console.input();
			if (!Code.isNumeric(strStock))
				throw new Exception("error 수량은 숫자입니다.!");

			int stock = Integer.parseInt(strStock);

			Map<Integer, Integer> wishList = GuestMenu.getWish().getWishList();

			if (wishList.containsKey(code)) {
				int getsotck = wishList.get(code);
				wishList.put(code, getsotck += stock);
			} else
				wishList.put(code, stock);
		}
	}

	@Override
	public void cartDel() {
		// TODO Auto-generated method stub
		String strCode = "";

		while (!strCode.equals("0")) {
			System.out.print("삭제하려는 책의 코드를 입력하세요. [이전:0] : ");
			strCode = Console.input();

			if (!Code.isNumeric(strCode)) {
				System.err.println("error 코드는 숫자만 포함합니다.");
				continue;
			}
			int code = Integer.parseInt(strCode);
			Map<Integer, Integer> wishList = GuestMenu.getWish().getWishList();

			if (wishList.containsKey(code)) {
				wishList.remove(code);
				System.out.println(Menu.RESULT_FOOTER);
				System.out.println("\t목록에서 삭제되었습니다.");
				System.out.println(Menu.RESULT_FOOTER);
			} else if (!strCode.equals("0"))
				System.err.println("error 장바구니에 없는 코드 입니다.");
		}
	}

	@Override
	public void buy() {
		// TODO Auto-generated method stub
		System.out.println("구매할 책의 코드를 입력 하세요 : ");
	}

	@Override
	public void nowBuy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void orderList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refund() {
		// TODO Auto-generated method stub

	}

}
