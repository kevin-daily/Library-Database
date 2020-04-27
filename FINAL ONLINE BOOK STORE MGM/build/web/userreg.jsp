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
        
        <% // Started Java
        try{
            // Fields 
            String username,password,firstname,lastname,address,phone,email;
	    // Get user input from fields
            username=request.getParameter("username");
            password=request.getParameter("password");
            firstname=request.getParameter("firstname");
            lastname=request.getParameter("lastname");
            address=request.getParameter("address");
            phone=request.getParameter("phone");
            email=request.getParameter("email");
            
	    // If any fields are empty, redirect to fail page
            if(username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            	response.sendRedirect("UserRegisterFail.html?message=unsuccess");
            }

	    // Create connection to the database
            Class.forName("org.mariadb.jdbc.Driver");	
	    	Connection	con = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "butters", "pass");

	    // Send query to the database for insertion into users table
            Statement st=con.createStatement();
            int k=st.executeUpdate("INSERT INTO users_tbl ( username, password, first_name, last_name, address, phone, email) VALUES " +
            						"('"+username+"','"+password+"','"+firstname+"','"+lastname+"','"+address+"','"+phone+"','"+email+"')");

	    // If query returns a number less than 0, it was successfully inserted and the user is redirected to the success page
	    // Otherwise, the user is redirected to the fail page. 
            if(k>0)
            {
                response.sendRedirect("UserLoginRegisterSuccess.html?message=success");
            }
            else
                response.sendRedirect("UserRegisterFail.html?message=unsuccess");
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        %>
    </body>
</html>
