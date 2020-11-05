  <!-- Header -->
  <jsp:include page="header.jsp" />
  
  <!-- JSTL includes -->
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  
  <!--  -->
  <header>
    <div class="container">
    <c:choose>
      <c:when test="${not empty message }">
        <p class="alert ${messageClass }">${message }</p>
        <%
          session.setAttribute("message", null);
          session.setAttribute("messageClass", null);
        %>
      </c:when>
    </c:choose>
    
      <h1>PUBHUB <small>Book Tag Search</small></h1>
      <!-- insert <hr class=?> tag here?-->
      <form action="BooksWithTag" method="get" class="form-horizontal">
        <div class="form-group">
          <label for="tagName" class="col-sm-4 control-label">Tag Name:</label>
          <div class="col-sm-5">
            <input type="text" class="form-control" id="tagName" name="tagName" required="required" value="${tag.tagName }">
          </div>
        </div>
        <div class="form-group">
        <div class="col-sm-offset-4 col-sm-1">
          <button type="submit" class="btn btn-info">Search</button>
        </div>
    </div>
      </form>
    </div>
  </header>
  
  <table class="table table-striped table-hover table-responsive pubhub-datatable">
      <thead>
        <tr>
          <td>ISBN-13:</td>
          <td>Title:</td>
          <td>Author:</td>
          <td>Publish Date:</td>
          <td>Price:</td>
          <td></td>
          <td></td>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="book" items="${books}">
          <tr>
            <td><c:out value="${book.isbn13}" /></td>
            
            <td><c:out value="${book.title}" /></td>
            <td><c:out value="${book.author}" /></td>
            <td><c:out value="${book.publishDate}" /></td>
            <td><fmt:formatNumber value="${book.price }" type="CURRENCY"/></td>
            <td><form action="DownloadBook" method="get">
                <input type="hidden" name="isbn13" value="${book.isbn13}">
                <button class="btn btn-success">Download</button>
              </form></td>
            <td><form action="ViewBookDetails?isbn=${book.isbn13}" method="get">
                <input type="hidden" name="isbn13" value="${book.isbn13}">
                <button class="btn btn-primary">Details</button>
              </form></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    

  <!-- Footer -->
  <jsp:include page="footer.jsp" />
  