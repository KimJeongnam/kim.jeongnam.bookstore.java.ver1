package domain;

import java.util.HashMap;
import java.util.Map;

public class Shelf {
	private static Map<Integer, Book> shelf = new HashMap<Integer, Book>();
	
	public static Map<Integer, Book> getShelf(){ return shelf; }
}
