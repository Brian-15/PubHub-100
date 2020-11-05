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
 * Servlet implementation class removeTagFromBook
 */
public class removeTagFromBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Book tempBook = (Book) session.getAttribute("book");
		
		String tagName = capitalize((String) request.getParameter("removedTagName"));
		request.setAttribute("removedTagName", tagName);
		
		String isbn13 = tempBook.getIsbn13();
		request.setAttribute("isbn13", isbn13);
		
		
		BookDAO bookDAO = DAOUtilities.getBookDAO();
		Book book = bookDAO.getBookByISBN(isbn13);
		request.setAttribute("book", book);
		
		TagDAO dao = DAOUtilities.getTagDAO();
		
		boolean success = dao.removeTag(tagName, isbn13);
		
		if (success) {
			request.getSession().setAttribute("message", "Tag successfully removed.");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		} else {
			request.getSession().setAttribute("message", "There was a problem removing the entered tag.");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}

}
