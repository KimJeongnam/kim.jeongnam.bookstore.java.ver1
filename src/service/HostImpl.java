package service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import domain.Book;
import domain.Code;
import domain.Order;
import domain.Shelf;
import presentation.Console;
import presentation.Menu;

public class HostImpl implements Host{
    public static HostImpl hostimpl = new HostImpl();
    
    private HostImpl(){}
    
    public static HostImpl getInstance(){ return hostimpl; }
    
    @Override
    public void bookList() throws Exception {
        // TODO Auto-generated method stub
    	System.out.println(Menu.RESULT_HEADER + "도서 목록" + Menu.RESULT_HEADER);
		System.out.printf("%s %15s %8s %8s %8s", "번호", "도서명", "저자", "가격", "수량\n");
		System.out.println(Menu.RESULT_FOOTER);
		
    	Map<Integer, Book> shelf = Shelf.getShelf();
        
        
        if(shelf.isEmpty()){
            throw new Exception("목록이 비어있습니다.");
        }
        
        for(Integer code: shelf.keySet()){
        	Book book = shelf.get(code);
            System.out.printf("%d %8s %8s %8s %8s\n", 
            		code, 
            		book.getBookName(), 
            		book.getAuthor(),
            		Integer.toString(book.getPrice()),
            		Integer.toString(book.getStock()));
        }
    }
    
    public void InputbookAdd() throws Exception{
    	System.out.print("책 제목 : ");
    	String bookName = Console.input();
    	while(bookName.isEmpty()) bookName = Console.input();
    	
    	System.out.print("책 저자 : ");
    	String author = Console.input();
    	while(author.isEmpty()) author = Console.input();
    	
    	System.out.print("책 가격 : ");
    	String strPrice = Console.input();
    	while(strPrice.isEmpty()) strPrice = Console.input();
    	
    	System.out.print("책 수량 : ");
    	String strStock = Console.input();
    	while(strStock.isEmpty()) strStock = Console.input();
    	
    	if(!Code.isNumeric(strStock) || !Code.isNumeric(strPrice)) {
    		System.err.println("가격, 수량에는 숫자만 입력해주세요.");
    		return;
    	}
    	int price = Integer.parseInt(strPrice);
    	int stock = Integer.parseInt(strStock);
    	
    	bookAdd(new Book(bookName, author, price, stock));
    }

    @Override
    public void bookAdd(Book book) {
        // TODO Auto-generated method stub
        Map<Integer, Book> shelf = Shelf.getShelf();
        
        Random random = new Random();
        int code = random.nextInt(10000);
        
        while(shelf.containsKey(code))
        	code = random.nextInt(10000);
        
        shelf.put(code, book);
    }

    @Override
    public void bookUpdate() {
        // TODO Auto-generated method stub
        System.out.print("수정하려는 책의 코드를 입력하세요 이전[0] : ");
        String strCode = Console.input();
        while(strCode.isEmpty()) strCode = Console.input();
        int code = 0;
        
        if(strCode.equals("0")) return;
        
        if(!Code.isNumeric(strCode)) {
        	System.err.println("코드는 숫자입니다.");
        	return;
        }else code = Integer.parseInt(strCode);
        
        
        Map<Integer, Book> shelf = Shelf.getShelf();
        if(!shelf.containsKey(code)) {
        	System.err.println("책 목록에 없는 코드입니다.");
        	return;
        }
        System.out.println(Menu.RESULT_HEADER+"도서 수정"+Menu.RESULT_HEADER);
        System.out.print("책 제목 : ");
    	String bookName = Console.input();
    	while(bookName.isEmpty()) bookName = Console.input();
    	
    	System.out.print("책 저자 : ");
    	String author = Console.input();
    	while(author.isEmpty()) author = Console.input();
    	
    	System.out.print("책 가격 : ");
    	String strPrice = Console.input();
    	while(strPrice.isEmpty()) strPrice = Console.input();
    	
    	System.out.print("책 수량 : ");
    	String strStock = Console.input();
    	while(strStock.isEmpty()) strStock = Console.input();
    	
        if(!Code.isNumeric(strStock) || !Code.isNumeric(strPrice)) {
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
    	System.out.println(Menu.RESULT_HEADER+"도서 삭제"+Menu.HEADER_BAR);
    	System.out.print("삭제하려는 책의 코드를 입력하세요 이전[0] : ");
    	
    	String strCode = Console.input();
        int code = 0;
        
        if(strCode.equals("0")) return;
        
        if(!Code.isNumeric(strCode)) {
        	System.err.println("코드는 숫자입니다.");
        	return;
        }else code = Integer.parseInt(strCode);
        
        Map<Integer, Book> shelf = Shelf.getShelf();
        if(!shelf.containsKey(code)) {
        	System.err.println("책 목록에 없는 코드입니다.");
        	return;
        }
        
        shelf.remove(code);
        
        System.out.println("\t"+code+"번 도서가 삭제되었습니다.");
        System.out.println(Menu.RESULT_FOOTER);
    }

    @Override
    public void orderList() {
        // TODO Auto-generated method stub
        System.out.println(Menu.RESULT_HEADER+"주문 목록"+Menu.RESULT_HEADER);
        Order.printOrderOnCallList();
        System.out.println(Menu.RESULT_FOOTER);
    }

    @Override
    public void orderConfirm() {
    	orderList();
    	System.out.println(Menu.HEADER_BAR+"구매 승인"+Menu.HEADER_BAR);
    	Order.printOrderOnCallList();
    	System.out.println(Menu.FOOTER_BAR);
    	
        System.out.print("구매 승인할 코드를 입력하세요  [이전 0, 전체 결제 : 'all'] : ");
        
        
    }

    @Override
    public void orderCancle() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void saleTotal() {
        // TODO Auto-generated method stub
        
    }

	@Override
	public void userAdd() {
		// TODO Auto-generated method stub
		Map<String,String> users = GuestImpl.getInstance().getUsers();
		
		System.out.print("ID : ");
		String id = Console.input();
		while(id.isEmpty()) {
			id = Console.input();
		}
		
		if(users.containsKey(id)) {
			System.err.println("이미 등록되어있는 id 입니다.");
			return;
		}
		
		System.out.print("PW : ");
		String pw = Console.input();
		while(pw.isEmpty()) {
			pw = Console.input();
		}
		
		users.put(id, pw);
		ArrayList<Order> list = new ArrayList<Order>();
		GuestImpl.getInstance().getOrderMap().put(id, list);
		System.out.println("\t회원가입 완료");
		System.out.println(Menu.RESULT_FOOTER);
	}
    
   /* public static void main(String[] args) {
    	String str = "111111";
    	String str1 = "22221s";
    	
    	System.out.println(Book.isNumeric(str1));
    	System.out.println(Book.isNumeric(str));
	}*/
}
