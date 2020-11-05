/**
 * 
 */
package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.*;

/**
 * @author bacir
 *
 */
public class TestDAOImpl {

	static TagDAO tagDAO = new TagDAOImpl();
	static BookDAO bookDAO = new BookDAOImpl();
	static List<Book> books;
	static List<Tag> tags;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		books = bookDAO.getAllBooks();
		Book book = books.get(0);
		System.out.println(book.toString());
		

		List<Tag> tags = tagDAO.getTagsFromBook(book);
		if (tags.size() == 0) {
			System.out.println("No results found.");
			return;
		}
		
		addTag("Fiction", book.getIsbn13());
		printTags(tags);
		
	}

	
	private static void addTag(String tagName, String isbn13) {
		
		if (tagDAO.addTag(tagName, isbn13)) {
			System.out.println("Successfully added tag ");
		} else {
			System.out.println("Failed to add tag ");
		}
		System.out.println(tagName + " to " + bookDAO.getBookByISBN(isbn13));
	}
	
	public final static void printTags(List<Tag> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).toString();
		}
	}
	
	public final void printBooks(List<Book> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).toString();
		}
	}
}
