package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import domain.Book;
import domain.Code;
import domain.Login;
import domain.Order;
import domain.Shelf;
import domain.Wish;
import presentation.Console;
import presentation.GuestMenu;
import presentation.Menu;

public class GuestImpl implements Guest {
	private static GuestImpl guestImpl = new GuestImpl();
	private Map<String, String> users = new HashMap<String, String>();

	private GuestImpl() {
	}

	public static GuestImpl getInstance() {
		return guestImpl;
	}

	public Map<String, String> getUsers() {
		return users;
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

		while (true) {
			System.out.print("장바구니에 담을 책의 코드를 입력하세요. [이전:0] : ");
			strCode = Console.input();

			if (strCode.equals("0"))
				return;
			
			if (!Code.isNumeric(strCode)){
                System.err.println("error 코드는 숫자입니다.!");
                continue;
            }


			int code = Integer.parseInt(strCode);

			if (!Shelf.getShelf().containsKey(code)){
			    System.err.println("error 책 목록에  없는 코드입니다.!");
			    continue;
			}

			System.out.print("수량을 입력하세요 : ");
			String strStock = Console.input();
			if (!Code.isNumeric(strStock)){
                System.err.println("error 수량은 숫자입니다.!");
                continue;
            }

			int stock = Integer.parseInt(strStock);
			
			Book book = Shelf.getShelf().get(code);
			if(book.getStock() < stock)
				throw new Exception("책의 수량이 모자랍니다.");
			else if(stock < 0)
				throw new Exception("수량은 0보다큰 양수입니다.");
			
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
				System.err.println("[Error] 코드는 숫자만 포함합니다.");
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
		String option = "";
		
		while(true) {
			System.out.print("구매할 책의 코드를 입력 하세요  [이전 0: 모두구매 'all'] : ");
			option = Console.input();
			Map<Integer, Integer> buylist=null;
			String id = Login.getSession().getMap().get("id");
			if(option.equals("0")) break;
			if(option.equals("all")) {
				buylist = GuestMenu.getWish().getWishList();
				GuestMenu.initWish();
				Wish.getUserWish().put(id, GuestMenu.getWish());
			}else {
				if(!Code.isNumeric(option)) {
				    System.err.println("[Error] 코드는 숫자만 포함합니다.");
	                continue;
				}
				int code = Integer.parseInt(option);
				
				if(!GuestMenu.getWish().getWishList().containsKey(code)){
				    System.err.println("[Error] 장바구니 목록에 없습니다.");
				    continue;
				}
				
				int stock = GuestMenu.getWish().getWishList().get(code);
				buylist = new HashMap<Integer, Integer>();
				buylist.put(code,  stock);
				GuestMenu.getWish().getWishList().remove(code);
			}
			
			String buyCode = Order.addOrder(id, buylist);
			System.out.println("구매요청완료 요청코드 : "+buyCode);
		}
		
	}
	
	@Override
	public void buyAskList() {
		String id = Login.getSession().getMap().get("id");
		ArrayList<Map<Object, Object>> buyAsklist = Order.getBuyAskList(id);
		
		if(buyAsklist.isEmpty()) {
			System.out.println("구매요청 건이 없습니다.");
			return;
		}
		System.out.println(Menu.RESULT_HEADER+"구매 요청 목록"+Menu.RESULT_HEADER);
		printBuyList(buyAsklist);
	}

	@Override
	public void nowBuy() throws Exception{
		// TODO Auto-generated method stub
		String strBookcode = "";
		
		while(true) {
			String id = Login.getSession().getMap().get("id");
			HostImpl.getInstance().bookList();
			System.out.print("구매할 책 코드 입력 [이전 : 0] : ");
			strBookcode = Console.input();
			
			if(strBookcode.equals("0")) break;
			
			if(!Code.isNumeric(strBookcode)) {
			    System.err.println("[Error] 코드는 숫자만 포함합니다.");
                continue;
			}
			int code = Integer.parseInt(strBookcode);
			
			if (!Shelf.getShelf().containsKey(code)){
			    System.err.println("[Error] 책 목록에  없는 코드입니다.!");
                continue;
			}
			
			System.out.print("수량을 입력하세요 : ");
			String strStock = Console.input();
			if (!Code.isNumeric(strStock)){
                System.err.println("error 수량은 숫자입니다.!");
                continue;
            }

			int stock = Integer.parseInt(strStock);
			
			Map<Integer, Integer> buyitem = new HashMap<Integer, Integer>();
			buyitem.put(code, stock);
			
			String buyCode = Order.addOrder(id, buyitem);
			System.out.println("구매요청완료 요청코드 : "+buyCode);
		}
		
		
	}

	@Override
	public void refund() {
		// TODO Auto-generated method stub
		System.out.println(Menu.RESULT_HEADER+"구매 완료 목록"+Menu.RESULT_HEADER);
		String id = Login.getSession().getMap().get("id");
		
		
		String buyCode = "";
		
		while(true) {
			ArrayList<Map<Object, Object>> buyList = Order.getBuyList(id);
			
			if(buyList.isEmpty()) {
				System.out.println("구매 완료된 목록이 없습니다.");
				System.out.println(Menu.RESULT_FOOTER);
				return;
			}
			printBuyList(buyList);
			
			System.out.print("환불 요청할 구매 코드를 입력하세요 [이전 :0 전체:'all'] :");
			buyCode = Console.input();
			
			if(buyCode.equals("0")) break;
			
			if(buyCode.equals("all")) {
				Order.refundAskAllAction(id);
				return;
			}
			
			if(!Order.orderList.containsKey(buyCode)) {
				System.err.println("목록에 없는 주문코드 입니다.");
				continue;
			}
			Order.refundAskAction(id, buyCode);
		}
		
	}
	
	@SuppressWarnings("unchecked")
    private void printBuyList(ArrayList<Map<Object, Object>> bufList) {
		for(Map<Object, Object> data: bufList) {
			System.out.print("구매 코드 : "+data.get("buyCode"));
			Map<Integer, Integer> list = (Map<Integer, Integer>)data.get("orderList");
			
			if(!list.isEmpty()) {
				System.out.println("{");
				System.out.printf("\t%s %10s %8s %8s \n","책 코드", "제목", "가격", "수량");
				for(int bookCode : list.keySet()) {
					Book book = null;
					if(Shelf.getShelf().containsKey(bookCode))
						book = Shelf.getShelf().get(bookCode);
					System.out.printf("\t%d",bookCode);
					System.out.printf(" %8s", book.getBookName());
					System.out.printf(" %8d", book.getPrice());
					System.out.printf(" %8d", list.get(bookCode));
					System.out.println(",");
				}
				System.out.println("}");
			}else
				System.out.println();
			
		}
		System.out.println(Menu.RESULT_FOOTER);
	}

}
