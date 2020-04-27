<%@page import="sql.IBookConstants"%>
<%@page import="java.sql.DriverManager"%>
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
	<div class="tab hd brown">User Login Successful !</div><br/>
	<h2>Welcome To User::::<%=session.getAttribute("uid").toString()%></h2>

	<%
        try {
			Class.forName("com.mysql.jdbc.Driver");	
	    Connection	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore","root","root");
            Statement st=con.createStatement();
			//ArrayList<Books> al = new ArrayList<Books>();
			PreparedStatement ps = con.prepareStatement("select * from books");
			ResultSet rs = ps.executeQuery();
			int i = 0;
			//RequestDispatcher rd = req.getRequestDispatcher("ViewBooks.html");
			//rd.include(req, res);
			out.println("<div class=\"tab\">You Successfully Paid for Following Books</div>");
			out.println(
					"<div class=\"tab\">\r\n" + "		<table>\r\n" + "			<tr>\r\n" + "				\r\n"
							+ "				<th>Book Code</th>\r\n" + "				<th>Book Name</th>\r\n"
							+ "				<th>Book Author</th>\r\n" + "				<th>Book Price</th>\r\n"
							+ "				<th>Quantity</th><br/>\r\n" + "				<th>Amount</th><br/>\r\n" + "			</tr>");
			double total = 0.0;
			while (rs.next()) {
				int bPrice = rs.getInt(IBookConstants.COLUMN_PRICE);
				String bCode = rs.getString(IBookConstants.COLUMN_BARCODE);
				String bName = rs.getString(IBookConstants.COLUMN_NAME);
				String bAuthor = rs.getString(IBookConstants.COLUMN_AUTHOR);
				int bQty = rs.getInt(IBookConstants.COLUMN_QUANTITY);
				i = i + 1;

				String qt = "qty" + Integer.toString(i);
				int quantity = Integer.parseInt(request.getParameter(qt));
				try {
					String check1 = "checked" + Integer.toString(i);
					String getChecked = request.getParameter(check1);
					if (bQty < quantity) {
						out.println(
								"</table><div class=\"tab\">Please Select the Qty less than Available Books Quantity</div>");
						break;
					}

					if (getChecked.equals("pay")) {
						out.println("<tr><td>" + bCode + "</td>");
						out.println("<td>" + bName + "</td>");
						out.println("<td>" + bAuthor + "</td>");
						out.println("<td>" + bPrice + "</td>");
						out.println("<td>" + quantity + "</td>");
						int amount = bPrice * quantity;
						total = total + amount;
						out.println("<td>" + amount + "</td></tr>");
						bQty = bQty - quantity;
						System.out.println(bQty);
						PreparedStatement ps1 = con.prepareStatement("update " + IBookConstants.TABLE_BOOK + " set "
								+ IBookConstants.COLUMN_QUANTITY + "=? where " + IBookConstants.COLUMN_BARCODE + "=?");
						ps1.setInt(1, bQty);
						ps1.setString(2, bCode);
						ps1.executeUpdate();
					}
				} catch (Exception e) {
				}
			}
			out.println("</table><br/><div class='tab'>Total Paid Amount: " + total + "</div>");
			String fPay = request.getParameter("f_pay");
		} catch (Exception e) {
			e.printStackTrace();
		}
        %>
		
	

</body>
</html>