package service;

import java.util.Map;

import domain.Book;
import domain.Code;
import domain.Money;
import domain.Order;
import domain.Shelf;
import presentation.Console;
import presentation.Menu;

public class HostImpl implements Host {
	public static HostImpl hostimpl = new HostImpl();

	private HostImpl() {
	}

	public static HostImpl getInstance() {
		return hostimpl;
	}

	@Override
	public void bookList() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Menu.RESULT_HEADER + "도서 목록" + Menu.RESULT_HEADER);
		System.out.printf("%s %15s %8s %8s %8s", "번호", "도서명", "저자", "가격", "수량\n");
		System.out.println(Menu.RESULT_FOOTER);

		Map<Integer, Book> shelf = Shelf.getShelf();

		if (shelf.isEmpty()) {
			throw new Exception("목록이 비어있습니다.");
		}

		for (Integer code : shelf.keySet()) {
			Book book = shelf.get(code);
			System.out.printf("%d %8s %8s %8s %8s\n", code, book.getBookName(), book.getAuthor(),
					Integer.toString(book.getPrice()), Integer.toString(book.getStock()));
		}
	}

	public void bookAdd(){
	    System.out.println(Menu.RESULT_HEADER + "도서 등록" + Menu.RESULT_HEADER);
		System.out.print("책 제목 : ");
		String bookName = Console.input();
		while (bookName.isEmpty())
			bookName = Console.input();

		System.out.print("책 저자 : ");
		String author = Console.input();
		while (author.isEmpty())
			author = Console.input();

		System.out.print("책 가격 : ");
		String strPrice = Console.input();
		while (strPrice.isEmpty())
			strPrice = Console.input();

		System.out.print("책 수량 : ");
		String strStock = Console.input();
		while (strStock.isEmpty())
			strStock = Console.input();

		if (!Code.isNumeric(strStock) || !Code.isNumeric(strPrice)) {
			System.err.println("가격, 수량에는 숫자만 입력해주세요.");
			return;
		}
		int price = Integer.parseInt(strPrice);
		int stock = Integer.parseInt(strStock);

		int code = Shelf.bookAdd(new Book(bookName, author, price, stock));
		System.out.println("도서 등록 완료 (Boock Code) : "+code);
		System.out.println(Menu.RESULT_FOOTER);
	}


	@Override
	public void bookUpdate() {
		// TODO Auto-generated method stub
		System.out.print("수정하려는 책의 코드를 입력하세요 이전[0] : ");
		String strCode = Console.input();
		while (strCode.isEmpty())
			strCode = Console.input();
		int code = 0;

		if (strCode.equals("0"))
			return;

		if (!Code.isNumeric(strCode)) {
			System.err.println("코드는 숫자입니다.");
			return;
		} else
			code = Integer.parseInt(strCode);

		Map<Integer, Book> shelf = Shelf.getShelf();
		if (!shelf.containsKey(code)) {
			System.err.println("책 목록에 없는 코드입니다.");
			return;
		}
		System.out.println(Menu.RESULT_HEADER + "도서 수정" + Menu.RESULT_HEADER);
		System.out.print("책 제목 : ");
		String bookName = Console.input();
		while (bookName.isEmpty())
			bookName = Console.input();

		System.out.print("책 저자 : ");
		String author = Console.input();
		while (author.isEmpty())
			author = Console.input();

		System.out.print("책 가격 : ");
		String strPrice = Console.input();
		while (strPrice.isEmpty())
			strPrice = Console.input();

		System.out.print("책 수량 : ");
		String strStock = Console.input();
		while (strStock.isEmpty())
			strStock = Console.input();

		if (!Code.isNumeric(strStock) || !Code.isNumeric(strPrice)) {
			System.err.println("가격, 수량에는 숫자만 입력해주세요.");
			return;
		}
		int price = Integer.parseInt(strPrice);
		int stock = Integer.parseInt(strStock);

		shelf.remove(code);

		shelf.put(code, new Book(bookName, author, price, stock));
		System.out.println(Menu.RESULT_FOOTER);
	}

	@Override
	public void bookDelete() {
		// TODO Auto-generated method stub
		System.out.println(Menu.RESULT_HEADER + "도서 삭제" + Menu.HEADER_BAR);
		System.out.print("삭제하려는 책의 코드를 입력하세요 이전[0] : ");

		String strCode = Console.input();
		int code = 0;

		if (strCode.equals("0"))
			return;

		if (!Code.isNumeric(strCode)) {
			System.err.println("코드는 숫자입니다.");
			return;
		} else
			code = Integer.parseInt(strCode);

		Map<Integer, Book> shelf = Shelf.getShelf();
		if (!shelf.containsKey(code)) {
			System.err.println("책 목록에 없는 코드입니다.");
			return;
		}

		shelf.remove(code);

		System.out.println("\t" + code + "번 도서가 삭제되었습니다.");
		System.out.println(Menu.RESULT_FOOTER);
	}

	@Override
	public void orderList() {
		// TODO Auto-generated method stub
		System.out.println(Menu.RESULT_HEADER + "주문 목록" + Menu.RESULT_HEADER);
		Order.printOrderOnCallList();
		System.out.println(Menu.RESULT_FOOTER);
	}

	@Override
	public void orderConfirm() {
		String buyCode = "";

		while (true) {
			System.out.println(Menu.RESULT_HEADER + "구매 승인" + Menu.RESULT_HEADER);
			Order.printOrderOnCallList();
			System.out.println(Menu.FOOTER_BAR);
			System.out.print("구매 승인할 코드를 입력하세요  [이전 0, 전체 승인 : 'all'] : ");
			buyCode = Console.input();

			if (buyCode.equals("0"))
				break;

			if (buyCode.equals("all")) {
				for (String code : Order.orderList.keySet()) {
					Map<Object, Object> data = Order.orderList.get(code);

					if(!stockSubConfirm(data)) {
						System.err.println("승인 불가 구매코드 : "+code);
					}
				}
				System.out.println("전체 승인 완료");
				System.out.println(Menu.RESULT_FOOTER);
				break;
			}

			if (!Order.orderList.containsKey(buyCode)) {
				System.err.println("해당 구매 코드가 없습니다. retry..");
				System.out.println(Menu.RESULT_FOOTER);
				continue;
			}

			Map<Object, Object> data = Order.orderList.get(buyCode);
			if(stockSubConfirm(data)) System.out.println("승인 완료");
			System.out.println(Menu.RESULT_FOOTER);
		}

	}
	
	@SuppressWarnings("unchecked")
	private boolean stockSubConfirm(Map<Object, Object> data) {
		Map<Integer, Integer> list = (Map<Integer, Integer>) data.get("orderList");
		boolean noPass = false;
		
		for (int bookCode : list.keySet()) {
			int stock = list.get(bookCode);
			Book book = Shelf.getShelf().get(bookCode);

			int bookStock = book.getStock();
			if (bookStock < stock) {
				System.err.println(
						"책 코드 :" + bookCode + " 의 수량이 부족합니다 남은 수량 : " + book.getStock() + " 요청 수량 : " + stock);
				noPass = true;
			}
			/* book.setStock(book.getStock()-stock); */
		}
		if (noPass) {
			System.err.println("책의 수량을 확인 후 승인하세요.");
			return false;
		}

		for (int bookCode : list.keySet()) {
			int stock = list.get(bookCode);
			Book book = Shelf.getShelf().get(bookCode);

			int bookStock = book.getStock();
			book.setStock(bookStock - stock);
		}
		
		data.put("status", true);
		return true;
	}

	@Override
	public void orderCancle() {
		// TODO Auto-generated method stub
		String buyCode ="";
		
		while(true) {
			System.out.println(Menu.RESULT_HEADER+"환불 요청 목록"+Menu.RESULT_HEADER);
			Order.printCancleAskList();
			System.out.println(Menu.RESULT_FOOTER);
			System.out.print("환불처리할 주문 코드를 입력하세요 [이전 : 0  전체 :'all'] : ");
			buyCode = Console.input();
			
			if(buyCode.equals("0")) break;
			
			if(buyCode.equals("all")) {
				Order.orderCancleAllAction();
				break;
			}
			
			if(!Order.orderList.containsKey(buyCode)) {
				System.err.println("[Error] 목록에 없는 주문 코드입니다.!\n");
				continue;
			}
			Order.orderCancleAction(buyCode);
		}
	}

	@Override
	public void saleTotal() {
		// TODO Auto-generated method stub
		int total = Order.saleTotal();
		System.out.println("결산 : "+Money.moneyToString(total)+"원");
	}

	@Override
	public void userAdd() {
		// TODO Auto-generated method stub
		Map<String, String> users = GuestImpl.getInstance().getUsers();

		System.out.print("ID : ");
		String id = Console.input();
		while (id.isEmpty()) {
			id = Console.input();
		}

		if (users.containsKey(id)) {
			System.err.println("이미 등록되어있는 id 입니다.");
			return;
		}

		System.out.print("PW : ");
		String pw = Console.input();
		while (pw.isEmpty()) {
			pw = Console.input();
		}

		users.put(id, pw);
		System.out.println("\t회원가입 완료");
		System.out.println(Menu.RESULT_FOOTER);
	}
}
