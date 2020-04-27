<%-- 
    Document   : userreg
    Created on : 4 Mar, 2020, 10:29:31 PM
    Author     : Lunar
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
            // Fields 
            String username,password;
	    // Get input from fields
            username=request.getParameter("username");
            password=request.getParameter("password");
            
	    // If either field is empty, redirect to failed screen
            if(username.isEmpty() || password.isEmpty()) {
                response.sendRedirect("UserLoginFail.html?message=unsuccess");
            }

	    // Connect to the database
         	Class.forName("org.mariadb.jdbc.Driver");	
	    	Connection	con = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "butters", "pass");   


	    // Construct the query to check if username and password match a databsase entry
			String query = "SELECT user_number FROM users_tbl WHERE username = '" 
								+ username 
								+ "' AND password = '" 
								+ password 
								+ "'";

	    // Send query to the database
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

	    // If user number is 0, no match and found and user is redirected to the fail screen. 
	    // Otherwise, user is logged in, username is set for the session, and user is 
	    // redirected to the user langing page. 
            int userNmbr = 0;
			while(rs.next()) {
				userNmbr = rs.getInt("user_number");
			}
			
			if(userNmbr == 0) {
                response.sendRedirect("UserLoginFail.html?message=unsuccess");
            }
            else
                session.setAttribute("uid", username);
                response.sendRedirect("userhome.jsp?message=success");
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }

        %>
	
    </body>
</html>
