package examples.pubhub.servlets;	

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

/**
 * Servlet implementation class ViewBooksWithTag
 */
@WebServlet("/BooksWithTag")
public class ViewBooksWithTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String tagName = capitalize(request.getParameter("tagName"));
		
		TagDAO dao = DAOUtilities.getTagDAO();
		
		List<Book> books = dao.getAllBooks(tagName);
		
		if (books == null) {
			response.sendRedirect("tagSearchEngine.jsp");
		} else {
			session.setAttribute("books", books);
			
			request.getRequestDispatcher("tagSearchResults.jsp").forward(request, response);
			
			//response.sendRedirect("tagSearchResults.jsp");
		}
	}

	

}
