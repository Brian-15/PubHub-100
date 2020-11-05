  <!-- Header -->
  <jsp:include page="header.jsp" />
  
  <!-- JSTL includes -->
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  
<!--   Just some stuff you need -->
  <header>
    <div class="container">
    
  <c:choose>
  <c:when test="${not empty message }">
    <p class="alert ${messageClass}">${message }</p>
  <%
    session.setAttribute("message", null);
    session.setAttribute("messageClass", null);
  %>
  </c:when>
  </c:choose>
  
    <h1>PUBHUB <small>Book Details - ${book.isbn13 }</small></h1>
    <hr class="book-primary">
    
    <form action="UpdateBook" method="post" class="form-horizontal">
      
      <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
      
      <div class="form-group">
        <label for="title" class="col-sm-4 control-label">Title</label>
        <div class="col-sm-5">
          <input type="text" class="form-control" id="title" name="title" placeholder="Title" required="required" value="${book.title }" />
        </div>
      </div>
      <div class="form-group">
        <label for="author" class="col-sm-4 control-label">Author</label>
        <div class="col-sm-5">
          <input type="text" class="form-control" id="author" name="author" placeholder="Author" required="required" value="${book.author }" />
        </div>
      </div>
      <div class="form-group">
        <label for="price" class="col-sm-4 control-label">Price</label>
        <div class="col-sm-5">
          <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required="required" value="${book.price }" />
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-4 col-sm-1">
          <button type="submit" class="btn btn-info">Update</button>
        </div>
      </div>
    </form>
    
    

    </div>
  </header>
  
  
  <section>
    <div class="container">

    <!-- Add Tag form -->
    <form action="AddTag" method="post" class="form-horizontal" >
      <div class="form-group">
        <label for="newTagName" class="col-sm-4 control-label">Tag Name</label>
          <div class="col-sm-5">
            <input type="text" class="form-control" id="newTagName" name="newTagName" placeholder="E.g. Adventure" required="required" value="${newTagName }" />
          </div>
        </div>
        <!-- Add Tag button -->
        <div class="form-group">
          <div class="col-sm-offset-4 col-sm-1">
            <button type="submit" class="btn btn-info" >Add tag</button>
          </div>
        </div>
    </form>
    </div>

  </section>
  
  <section>
  <div class="container">
    <!-- Remove Tag form -->
    <form action="removeTag" method="post" class="form-horizontal" >
      <div class="form-group">
      	<small>Remove tag:</small>
        <label for="removedTagName" class="col-sm-4 control-label">Tag Name</label>
        <div class="col-sm-5">
          <input type="text" class="form-control" id="removedTagName" name="removedTagName" placeholder="Any existent tag name" required="required" value="${removedTagName }" />
        </div>
      </div>
      <!-- Remove Tag button -->
      <div class="form-group">
        <div class="col-sm-offset-4 col-sm-1">
          <button type="submit" class="btn btn-info">Remove Tag</button>
        </div>
      </div>
    </form>
    </div>
  </section>
  
  <!-- Footer -->
  <jsp:include page="footer.jsp" />
