package service;

import domain.Book;
import domain.Shelf;

public class HostImpl implements Host{
    public static HostImpl hostimpl = new HostImpl();
    
    private HostImpl(){}
    
    public static HostImpl getInstance(){ return hostimpl; }
    
    @Override
    public void bookList() throws Exception {
        // TODO Auto-generated method stub
        Book book = new Book();
        book.setCode("0001");
        book.setBookName("나의라임오렌지나무");
        book.setAuthor("홍길동0");
        book.setPrice(35000);
        book.setStock(250);
        Shelf.getBooks().add(book);
        
        if(Shelf.getBooks().isEmpty()){
            throw new Exception("목록이 비어있습니다.");
        }
        
        for(Book book1: Shelf.getBooks()){
            System.out.println(book1.toString());
        }
    }

    @Override
    public void bookAdd(Book book) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void bookUpdate() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void bookDelete(Book book) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void orderList() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void orderConfirm() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void orderCancle() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void saleTotal() {
        // TODO Auto-generated method stub
        
    }

}
