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

        <% // Start of Java
        
        try {
	    // Fields
            String username,password;
	    // Get the user input from fields
            username=request.getParameter("username");
            password=request.getParameter("password");

	    // If ay field left empty, redirect to admin login
	    if(username.isEmpty() || password.isEmpty()) {
                response.sendRedirect("AdminLogin.html?message=unsuccess");
	    }
            
	    // If the username is not admin, redirect to user login
            if(!username.equalsIgnoreCase("admin")) {
                response.sendRedirect("UserLogin.html?message=unsuccess");
            }

	    // Connect to the database
         	Class.forName("org.mariadb.jdbc.Driver");	
	    	Connection	con = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "butters", "pass");   


			String query = "SELECT user_number FROM users_tbl WHERE username = '" + username 
								+ "' AND password = '" + password + "'";


	    // Create statement and send query to the database
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

	    // If the database does not find the matching name and password, redirect to login as failure. 
	    // Otherwise, set the username to admin and redirect to successfull login landing page
            int userNmbr = 0;
			while(rs.next()) {
				userNmbr = rs.getInt("user_number");
			}
			
			if(userNmbr == 0) {
                response.sendRedirect("AdminLogin.html?message=unsuccess");
                out.println("Login Unsuccessful. Please Try Again.");
            }
            else
                session.setAttribute("uid", username);
                response.sendRedirect("adminhome.html?message=success");
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        %>
    </body>
</html>
