package service;

public interface Host {
    public static final String ID = "host";
    public static final String PW = "1234";

    public void userAdd(); // 유저 추가
    
    public void bookList() throws Exception; // 책 목록
    public void bookAdd(); // 책 추가
    public void bookUpdate(); // 책 수정
    public void bookDelete(); // 책삭제
    
    public void orderList(); // 주문 목록
    public void orderConfirm(); // 결제 승인
    public void orderCancle(); // 결제 취소
    public void saleTotal(); // 결산
}
