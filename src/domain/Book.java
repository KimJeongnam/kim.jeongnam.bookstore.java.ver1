package domain;

public class Book {
	private String bookName;
	private String author;
	private int price;
	private int stock;
	
	public Book() {}
	public Book(String bookName, String author, int price, int stock) {
		this.bookName = bookName;
		this.author = author;
		this.price = price;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "제목 : " + bookName + "\t저자 : " + author + "\t 가격 : " + price + "\t수량 : " + stock;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
