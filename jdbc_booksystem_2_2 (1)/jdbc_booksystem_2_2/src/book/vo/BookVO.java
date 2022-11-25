package book.vo;

public class BookVO {
	private int isbn;
	private String name;
	private String publish;
	private String author;
	private int price;
	private String categoryName;
	
	@Override
	public String toString() {
		return "BookVO [isbn=" + isbn + ", name=" + name + ", publish=" + publish + ", author=" + author + ", price="
				+ price + ", categoryName=" + categoryName + "]";
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
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
	
	
}
