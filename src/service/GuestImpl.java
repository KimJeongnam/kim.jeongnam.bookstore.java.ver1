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
	// 싱글턴 GuestImpl 객체 생성
	private static GuestImpl guestImpl = new GuestImpl();	
	// HashMap  key=유저의id  value=유저의pw
	private Map<String, String> users = new HashMap<String, String>();

	// 외부에서 생성 불가능하게함
	private GuestImpl() {}

	// GuestImpl 객체의 주소값을 던저주는 정적 메소드 
	public static GuestImpl getInstance() {
		return guestImpl;
	}

	// 유저 정보를 담고있는 Map 반환
	public Map<String, String> getUsers() {
		return users;
	}

	// 장바구니 목록 출력 메소드
	@Override
	public void wishList() {
		GuestMenu.getWish().getInfo();	// wish 의 getInfo 호출
	}

	// 장바구니 추가 메소드
	@Override
	public void cartAdd() throws Exception {
		// TODO Auto-generated method stub
		String strCode = "";
		HostImpl.getInstance().bookList();

		while (true) {
			System.out.print("장바구니에 담을 책의 코드를 입력하세요. [이전:0] : ");
			strCode = Console.input();

			if (strCode.equals("0"))	// 0이면 반복 종료 
				return;
			
			/*
			 * 숫자가 아니라면 에러메세지 출력후 반복문 재시작
			 */
			if (!Code.isNumeric(strCode)){			
                System.err.println("error 코드는 숫자입니다.!");
                continue;
            }

			int code = Integer.parseInt(strCode);

			// 책장에서 shelf를 가져온후 해당 map에 code가 없다면 반복문 재시작
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
			
			if(stock <0) {
				System.err.println("수량은 0이 될 수 없습니다.!");
				continue;
			}
			/*
			 * 책장에서 책의 정보가 들어있는 
			 * Map<Integer, Book> 타입을 반환하는 getShelf 호출 후 
			 * 입력받은 code 의 책의 정보를 가져와 book 에 주소값 저장
			 */
			Book book = Shelf.getShelf().get(code);
			
			// 책의 수량이 입력한 수량보다 적거나 0보다 작을시  
			if(book.getStock() < stock)
				throw new Exception("책의 수량이 모자랍니다.");
			else if(stock < 0)
				throw new Exception("수량은 0보다큰 양수입니다.");
			
			// GuestMenu에잇는 장바구니의 주소값 을 가져온다.
			Map<Integer, Integer> wishList = GuestMenu.getWish().getWishList();
			
			/*
			 * 만약 장바구니에 이미 해당 bookCode가 있다면 
			 * 해당 장바구니의 수량에 입력받은 수량을 더해 put 한다.
			 */
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
			
			if(stock <0) {
				System.err.println("수량은 0이 될 수 없습니다.!");
				continue;
			}
			
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
