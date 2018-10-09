package domain;

public class Book {
	private String code;
	private String bookName;
	private String author;
	private int price;
	private int stock;

	@Override
	public String toString() {
		return "Code : " + code + "\t제목 : " + bookName + "\t저자 : " + author + "\t 가격 : " + price + "\t수량 : " + stock;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
