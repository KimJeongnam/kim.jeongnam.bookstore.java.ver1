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

	/*
	 * (non-Javadoc)
	 * @see service.Host#bookList()
	 * 책장의 책목록 출력 메소드
	 */
	@Override
	public void bookList() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Menu.RESULT_HEADER + "도서 목록" + Menu.RESULT_HEADER);
		System.out.printf("%s %15s %8s %8s %8s", "번호", "도서명", "저자", "가격", "수량\n");
		System.out.println(Menu.RESULT_FOOTER);

		// 책장의 shelf 주소값 꺼냄
		Map<Integer, Book> shelf = Shelf.getShelf();

		// shelf가 비어있다면 Exception 발생
		if (shelf.isEmpty()) {
			throw new Exception("목록이 비어있습니다.");
		}

		/*
		 * shelf가 가지고있는 키 를 꺼내 code에 담으며 반복
		 */
		for (Integer code : shelf.keySet()) {
			Book book = shelf.get(code);
			System.out.printf("%d %8s %8s %8s %8s\n", code, book.getBookName(), book.getAuthor(),
					Integer.toString(book.getPrice()), Integer.toString(book.getStock()));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see service.Host#bookAdd()
	 * 책 추가 메소드
	 */
	@Override
	public void bookAdd() {
		System.out.println(Menu.RESULT_HEADER + "도서 등록" + Menu.RESULT_HEADER);
		System.out.print("책 제목 : ");

		String bookName = Console.input();
		while (bookName.isEmpty()) // 입력 값이 비어있다면 입력값이 있을때까지 반복.
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

		/*
		 *  Code.isNumeric(String str): 
		 *  	해당 문자열이 숫자인지 판별하는 메소드 숫자만 있다면 true 아니라면 false
		 * 
		 * 입력받은 가격, 수량이 숫자가 아니면 에러 출력후 함수 종료
		 */
		if (!Code.isNumeric(strStock) || !Code.isNumeric(strPrice)) {
			System.err.println("가격, 수량에는 숫자만 입력해주세요.");
			return;
		}
		int price = Integer.parseInt(strPrice);
		int stock = Integer.parseInt(strStock);

		if(price<0 || stock<0) {
			System.err.println("가격, 수량은 0이 될 수 없습니다.");
			return;
		}
		/*
		 * Shelf.bookAdd  Book 타입을 매개변수로 하며
		 * 자신이 가지고있는 Shelf에 책을 추가하고  등록된 책의 코드를 반환함
		 */
		int code = Shelf.bookAdd(new Book(bookName, author, price, stock));
		System.out.println("도서 등록 완료 (Boock Code) : " + code);
		System.out.println(Menu.RESULT_FOOTER);
	}

	/*
	 * (non-Javadoc)
	 * @see service.Host#bookUpdate()
	 * 
	 * 책 수정 메소드
	 */
	@Override
	public void bookUpdate() {
		// TODO Auto-generated method stub
		System.out.print("수정하려는 책의 코드를 입력하세요 이전[0] : ");
		String strCode = Console.input();
		
		while (strCode.isEmpty())
			strCode = Console.input();
		int code = 0;

		// strCode 가  0과 같다면  함수 종료
		if (strCode.equals("0"))
			return;

		// strCode가 숫자가 아니라면 함수종료  숫자라면 int형으로 변환 
		if (!Code.isNumeric(strCode)) {
			System.err.println("코드는 숫자입니다.");
			return;
		} else
			code = Integer.parseInt(strCode);

		// 책장  정보 가져오기.
		Map<Integer, Book> shelf = Shelf.getShelf();
		
		// 입력받은 code가 책장에 없다면 종료 
		if (!shelf.containsKey(code)) {
			System.err.println("책 목록에 없는 코드입니다.");
			return;
		}
		
		/*
		 * 도서 추가와 동일
		 */
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
		
		if(price<0 || stock<0) {
			System.err.println("가격, 수량은 0이 될 수 없습니다.");
			return;
		}
		
		shelf.remove(code);

		shelf.put(code, new Book(bookName, author, price, stock));
		System.out.println(Menu.RESULT_FOOTER);
	}

	/*
	 * (non-Javadoc)
	 * @see service.Host#bookDelete()
	 * 책 삭제 메소드
	 */
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

	/*
	 * (non-Javadoc)
	 * @see service.Host#orderList()
	 * 주문 목록 메소드 
	 */
	@Override
	public void orderList() {
		// TODO Auto-generated method stub
		System.out.println(Menu.RESULT_HEADER + "주문 목록" + Menu.RESULT_HEADER);

		Order.printOrderOnCallList();		// 주문 리스트 전체 출력 메소드
		System.out.println(Menu.RESULT_FOOTER);
	}

	/*
	 * (non-Javadoc)
	 * @see service.Host#orderConfirm()
	 * 주문 요청 승인 메소드
	 */
	@Override
	public void orderConfirm() {
		String buyCode = "";

		while (true) {
			System.out.println(Menu.RESULT_HEADER + "구매 승인" + Menu.RESULT_HEADER);
			Order.printOrderOnCallList();		// 주문 리스트 전체 출력 메소드		
			System.out.println(Menu.FOOTER_BAR);
			System.out.print("구매 승인할 코드를 입력하세요  [이전 0, 전체 승인 : 'all'] : ");
			buyCode = Console.input();

			if (buyCode.equals("0"))
				break;

			// <<<<<입력받은 문자열이 all일때
			if (buyCode.equals("all")) {
				for (String code : Order.orderList.keySet()) {
					Map<Object, Object> data = Order.orderList.get(code);	//주문 코드를 이용하여 해당 주문데이터 추출

					/*
					 *  주문데이터를 넘겨주며 stockSubConfirm() 호출 
					 *  
					 *  false가 반환 되면 승인 불가 처리
					 */
					data.put("status", true);
				}
				System.out.println("전체 승인 완료");
				System.out.println(Menu.RESULT_FOOTER);
				break;
			}
			//>>>>>

			if (!Order.orderList.containsKey(buyCode)) {
				System.err.println("해당 구매 코드가 없습니다. retry..");
				System.out.println(Menu.RESULT_FOOTER);
				continue;
			}

			Map<Object, Object> data = Order.orderList.get(buyCode);
			
			data.put("status", true);
			System.out.println("승인 완료");
			System.out.println(Menu.RESULT_FOOTER);
		}

	}


	/*
	 * (non-Javadoc)
	 * @see service.Host#orderCancle()
	 * 구매완료된 목록 환불 메소드
	 */
	@Override
	public void orderCancle() {
		// TODO Auto-generated method stub
		String buyCode = "";

		while (true) {
			System.out.println(Menu.RESULT_HEADER + "환불 요청 목록" + Menu.RESULT_HEADER);
			Order.printCancleAskList();
			System.out.println(Menu.RESULT_FOOTER);
			System.out.print("환불처리할 주문 코드를 입력하세요 [이전 : 0  전체 :'all'] : ");
			buyCode = Console.input();

			if (buyCode.equals("0"))
				break;

			if (buyCode.equals("all")) {
				Order.orderCancleAllAction();
				break;
			}

			if (!Order.orderList.containsKey(buyCode)) {
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
		System.out.println("결산 : " + Money.moneyToString(total) + "원");
	}

	@Override
	public void userAdd() {
		// TODO Auto-generated method stub
		Map<String, String> users = GuestImpl.getInstance().getUsers();

		System.out.println(Menu.RESULT_HEADER + Menu.RESULT_HEADER);
		System.out.println("\t회원가입");
		System.out.println(Menu.RESULT_FOOTER);
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
