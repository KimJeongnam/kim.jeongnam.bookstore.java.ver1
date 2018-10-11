package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Shelf {
	private static Map<Integer, Book> shelf = new HashMap<Integer, Book>();
	
	public static Map<Integer, Book> getShelf(){ return shelf; }
	
	public static int bookAdd(Book book) {
        // TODO Auto-generated method stub

        Random random = new Random();
        int code = random.nextInt(10000);

        while (shelf.containsKey(code))
            code = random.nextInt(10000);

        shelf.put(code, book);
        return code;
    }
}
