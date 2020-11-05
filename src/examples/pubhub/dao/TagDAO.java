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
public interface TagDAO {
	public List<Book> getAllBooks(Tag tag);
	public List<Book> getAllBooks(String tagName);
	public List<Tag> getTagsFromBook(Book b);
	public List<Tag> getTagsFromBook(String isbn13);
	public List<Tag> getAllTags();
	
	public boolean addTag(String tagName, Book b);
	public boolean addTag(String tagName, String isbn13);
	
	public boolean removeTag(String tagName, Book b);
	public boolean removeTag(String tagName, String isbn13);
}
