package examples.pubhub.model;

/**
 * @author bacir
 *
 */
public class Tag {
	
	private String isbn13;
	private String tagName;
	
	public Tag() {
		this.isbn13 = null;
		this.tagName = null;
	}
	
	// Constructor using isbn number
	public Tag(String isbn13) {
		this.isbn13 = isbn13;
		this.tagName = null;
	}
	
	// Constructor using isbn number with tag name
	public Tag(String isbn13, String tagName) {
		this.isbn13 = isbn13;
		this.tagName = tagName;
	}
	
	// Constructor using Book object
	public Tag(Book b) {
		this.isbn13 = b.getIsbn13();
		this.tagName = null;
	}
	
	// Constructor using Book object with tag name
	public Tag(Book b, String tagName) {
		this.isbn13 = b.getIsbn13();
		this.tagName = tagName;
	}
	
	// get functions
	public final String getIsbn13() {
		return isbn13;
	}
	
	public final String getTagName() {
		return tagName;
	}
	
	public final void printTag() {
		System.out.println(this.getTagName() + " " + this.getIsbn13());
	}
	
	@Override
	public String toString() {
		return this.getTagName() + " " + this.getIsbn13();
	}

/*------------------------------------------------------------------------------------------------*/
	
	
	// set functions
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	
	
}
