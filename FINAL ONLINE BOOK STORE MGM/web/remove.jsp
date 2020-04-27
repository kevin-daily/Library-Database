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
    

        <% // Start Java
        
        try{
            
	    // Field
            String ISBN;
	    // Get input from field
            ISBN = request.getParameter("isbn");
            
	    // If empty, redirect to fail message
            if(ISBN.isEmpty()) {
                response.sendRedirect("RemoveBooksFail.html?message=unsuccess");
            }

	    // Start the connection to the database
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "butters", "pass");          
			
            // Construct the query message to the database using attribute from field
	    String removal = "DELETE FROM books_tbl WHERE book_ISBN = '" + ISBN + "'";
            
            Statement st=con.createStatement();

	    // Send query to database. If returns 0, deletion was successful and user
	    // is redirected to the success page. Otherwise, redirected to the fail page.
            int k=st.executeUpdate(removal);
            if(k>0)
            {
                response.sendRedirect("RemoveBooksSuccess.html?message=success");
            }
            else
                response.sendRedirect("RemoveBooksFail.html?message=unsuccess");
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        %>
    </body>
</html>
