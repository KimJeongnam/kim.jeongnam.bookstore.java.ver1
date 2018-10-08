package domain;

import java.util.ArrayList;

public class Shelf {
	private static ArrayList<Book> books = new ArrayList<Book>();
	
	public static ArrayList<Book> getBooks(){ return books; }
}
