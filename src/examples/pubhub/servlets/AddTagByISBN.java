package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class AddTagByISBN
 */
public class AddTagByISBN extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		Book tempBook = (Book) session.getAttribute("book");
		String isbn13 = tempBook.getIsbn13();
		
		String newTagName = capitalize(request.getParameter("newTagName")); // FIXME is null
		
		request.setAttribute("newTagName", newTagName);
		
		TagDAO tagDatabase = DAOUtilities.getTagDAO();
		
		boolean success = tagDatabase.addTag(newTagName, isbn13);
		
		if (success) {
			request.getSession().setAttribute("message", "Tag successfully added.");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		} else {
			// addTag() = false
			request.getSession().setAttribute("message", "There was a problem adding the tag: tag already exists.");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
		
	}

}
