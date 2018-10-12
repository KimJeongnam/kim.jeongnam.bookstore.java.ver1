package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Shelf {
	/*
	 * 책장 정적변수
	 * 
	 * 북코드를 와 Book 정보를 담을수 있는 shelf Map 변수
	 */
	private static Map<Integer, Book> shelf = new HashMap<Integer, Book>();
	
	// shelf getter
	public static Map<Integer, Book> getShelf(){ return shelf; }
	
	// Book 객체를 받아 책장에 추가하는 함수
	public static int bookAdd(Book book) {
        Random random = new Random();		// 랜덤 객체 생성
        int code = random.nextInt(10000);	// 0~9999 의 난수 생성

        while (shelf.containsKey(code))		// 책장에 해당 난수가 없을때까지 계속난수생성
            code = random.nextInt(10000);

        shelf.put(code, book);				// 책장에 북코드와 책 삽입
        return code;						// 북코드 반환
    }
}
