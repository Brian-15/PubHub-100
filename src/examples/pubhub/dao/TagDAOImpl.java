/**
 * 
 */
package examples.pubhub.dao;

import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author bacir
 * implementation for TagDAO
 */
public class TagDAOImpl implements TagDAO {
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	/*------------------------------------------------------------------------------------------------*/
	
	
	@Override
	public List<Book> getAllBooks(String tagName) {
		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM books INNER JOIN \"BOOK_TAGS\" ON (books.isbn_13 = \"BOOK_TAGS\".isbn_13) AND tag_name LIKE ?";
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			stmt.setString(1, tagName);
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Book object with info for each row from our query result
				Book book = new Book();

				// Each variable in our Book object maps to a column in a row from our results.
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				// Finally we add it to the list of Book objects returned by this query.
				books.add(book);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Book objects populated by the DB.
		return books;
		
	}
	
	@Override
	public List<Book> getAllBooks(Tag tag) {
		return getAllBooks(tag.getTagName());
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public List<Tag> getTagsFromBook(Book b) {
		return getTagsFromBook(b.getIsbn13());
	}
	
	@Override
	public List<Tag> getTagsFromBook(String isbn13) {
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM \"BOOK_TAGS\" WHERE isbn_13 LIKE ?";	// Note the ? in the query
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, isbn13);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTagName(rs.getString("tag_name"));
				
				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}
	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean addTag(String tagName, String isbn13) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO \"BOOK_TAGS\" VALUES (?, ?)"; // Were using a lot of ?'s here...
			stmt = connection.prepareStatement(sql);
			
			// But that's okay, we can set them all before we execute
			stmt.setString(1, isbn13);
			stmt.setString(2, tagName);
			
			// If we were able to add our tag to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean addTag(String tagName, Book book) {
		return addTag(tagName, book.getIsbn13());
	}
	
	/*------------------------------------------------------------------------------------------------*/

	
	// imported from BookDAOImpl.java
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}


	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean removeTag(String tagName, Book b) {
		return removeTag(tagName, b.getIsbn13());
	}


	@Override
	public boolean removeTag(String tagName, String isbn13) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM \"BOOK_TAGS\" WHERE isbn_13 LIKE ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn13);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	
	/*------------------------------------------------------------------------------------------------*/

	
	// print all data
	public void printResults(PreparedStatement stmt) {
		try {
			ResultSet r = stmt.executeQuery();
			
			while (r.next()) {
				System.out.println(r.getString("tag_name") + ", " + "ISBN13 " + r.getString("isbn_13"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<Tag> getAllTags() {
		
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM \"BOOK_TAGS\"";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTagName(rs.getString("tag_name"));
				
				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}


	


	

}
