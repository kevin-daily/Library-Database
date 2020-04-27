<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.*"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Book Store</title>
	<style>
	*{
		box-sizing: border-box;
	}	
	body
	{
		background-image: url("pfw-logo.jpg");
		background-repeat: no-repeat;
		background-position: 50% 50%;
		background-color: black;
	}
	#topmid{
		border:0px solid black;
		color:#FFFFFF;
		text-align: center;
		font-weight: bold;
		margin-top: 0px;
		padding-left: 45%;
		font-style: oblique;
		font-size:28px;
	}
	a:link{
		color: #085C11;
		text-decoration: none;
	}
	a:visited{
		color: #AD1F65;
	}
	a:hover{
		color:red;
	}
	
	.tab{
		border:1px black solid;
		background-color: #C28E0E;
		margin-left: 68%;
		width:450px;
		color:white;
		font-weight: bold;
		font-style:normal;
		text-align:center;
		font-size: 22px;
		margin-bottom:10px;
		padding:10px;
	}
	.home{
		border:1px black solid;
		background-color: #C28E0E;
		margin-left: 1%;
		width:200px;
		color:white;
		font-weight: bold;
		font-style:italic;
		text-align:center;
		font-size: 25px;
		margin-bottom:10px;
		padding:10px;
		float:left;
		clear:both;
	}
	.yel{
	color:yellow;
	}
	.red{
		color:red;
	}
	.green{
		color:green;
	}
	.gold{
		color:#C28E0E;
	}
	.hd{
		font-size:25px;
	}
	.align{
		text-align:left;
	}
	.brown{
		color:brown;
	}
</style>
</head>

<body>
	<div id="topmid">
		<h1>Welcome to Online Book Store</h1>
	</div>
	


	<div class="home">
		<a href="index.html">Logout</a>
	</div>
	
	<%
        try {
			Class.forName("com.mysql.jdbc.Driver");	
	    Connection	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore","root","root");
            Statement st=con.createStatement();
           out.println("<div class=\"tab\">Books Available In Our Store</div>");
			out.println("<div class=\"tab\">\r\n" + 
					"		<table>\r\n" + 
					"			<tr>\r\n" + 
					"				\r\n" + 
					"				<th>Book Code</th>\r\n" + 
					"				<th>Book Name</th>\r\n" + 
					"				<th>Book Author</th>\r\n" + 
					"				<th>Book Price</th>\r\n" + 
					"				<th>Quantity</th><br/>\r\n" + 
					"			</tr>");
                        ResultSet rs=st.executeQuery("select * from books");
			while(rs.next())
			{
				String bCode = rs.getString(1);
				String bName = rs.getString(2);
				String bAuthor = rs.getString(3);
				int bPrice = rs.getInt(4);
				int bQty = rs.getInt(5);
				out.println("<tr><td>"+bCode+"</td>");
				out.println("<td>"+bName+"</td>");
				out.println("<td>"+bAuthor+"</td>");
				out.println("<td>"+bPrice+"</td>");
				out.println("<td>"+bQty+"</td></tr>");
			}
			out.println("</table>\r\n" + 
					"	</div>");
			//out.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        %>
	

</body>
</html>