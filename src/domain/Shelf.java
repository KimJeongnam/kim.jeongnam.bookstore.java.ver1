package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shelf {
	private static ArrayList<Book> books = new ArrayList<Book>();
	private static Map<Integer, Book> shelf = new HashMap<Integer, Book>();
	
	public static ArrayList<Book> getBooks(){ return books; }
	public static Map<Integer, Book> getShelf(){ return shelf; }
}
