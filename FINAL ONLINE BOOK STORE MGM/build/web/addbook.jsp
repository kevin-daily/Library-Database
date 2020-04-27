<%-- 
    Document   : userreg
    Created on : 4 Mar, 2020, 10:29:31 PM
    Author     : Lunar

    Edited by  : Kevin Daily
    Edited on  : 26 Apr, 2020
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <% // Start of Java coding using scriplets
	   // TODO 
	   // Shift away from scriplets. Makes debugging difficult
	   // Any SQL error causes blank page to display instead of error.
        
        try{
        	
	    // Fields for user input
            String ISBN,b_title,b_author,b_year,b_genre,b_qnty;
	    // Get input from html fields
            ISBN=request.getParameter("ISBN");
            b_title=request.getParameter("Title");
            b_author=request.getParameter("Author");
            b_year=request.getParameter("Year");
            b_genre=request.getParameter("Genre");
            b_qnty=request.getParameter("Quantity");

	    // Check if any field was left empty. Will cause SQL error so redirect with warning if empty
            if(ISBN.isEmpty() || b_title.isEmpty() || b_author.isEmpty() || b_year.isEmpty() || b_genre.isEmpty() || b_qnty.isEmpty()) {
                response.sendRedirect("AddBookFail.html?message=unsuccess");
            }

	    // Connect to the database
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "butters", "pass");

	    // Query for insertion into the database
	    String query = "INSERT INTO books_tbl (book_author, book_title, book_year, book_genre, book_ISBN, book_quantity, is_checked, book_location) " +
		   			"VALUES ('" + b_author + "', '" 
		   						+ b_title + "', '" 
		   						+ b_year + "', '" 
		   						+ b_genre + "', '" 
		   						+ ISBN + "', '" 
		   						+ b_qnty 
		   						+ "', '0', 'Location: Aisle 7, Section 15')";
		    
            Statement st=con.createStatement();

	    // If insertion was successful, redirect to success screen. Otherwise, redirect to fail screen. 
            int k=st.executeUpdate(query);
            if(k>0)
            {
                response.sendRedirect("AddBookSuccess.html?message=success");
            }
            else
                response.sendRedirect("AddBookFail.html?message=unsuccess");
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        %>
    </body>
</html>
