<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.*"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Online Library</title>
	<style>
	*{
		box-sizing: border-box;
	}	
	body
	{
		background-image: url("purdue-fort-wayne-logo.png");
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

	<% // Start Java

	// Initialize array of strings to hold query results
		String[] books = new String[10];
		String bookPrint = "No Books Found";
        try {
	// Create connection to the database
			Class.forName("org.mariadb.jdbc.Driver");	
	    	Connection	con = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "butters", "pass");

            Statement st=con.createStatement();
            
	    // Get the user name for the current session and use it to create query statement
            String username = session.getAttribute("uid").toString();
            String checkout_query = "SELECT * FROM user_checkout_tbl WHERE username = 'scotch'";// + username + "'";

            ResultSet rs_checkout = st.executeQuery(checkout_query);

	    // Set the index as 0 for array traversal
            int index = 0;
	    	int[] number_list = new int[100];
			while(rs_checkout.next())
			{
				// Get the unique book number from the query
				int bookNumber = rs_checkout.getInt("book_number");
				
				number_list[index] = bookNumber;

				index++;


            	            	
	            
			}
			
			rs_checkout.close();

			int book_index = 0;
			for(int j = 0; j < index; j++) {
	    		// Use unique book number to send a second query to the database
            	String book_query = "SELECT * FROM books_tbl WHERE book_number = '" + number_list[j] + "'";
				ResultSet rs_book = st.executeQuery(book_query);
	        	while(rs_book.next()) {
				    // Retrieve the title, author, and ISBN of the book found in the second query 
	        		String b_title = rs_book.getString("book_title");
	        		String b_author = rs_book.getString("book_author");
	        		int ISBN = rs_book.getInt("book_ISBN");
	            		
		    		// Create a string holding the information
		    		// Store the string in an array and increase the index by one for the next book retrieval
	
	        		String book_info = "Title: " + b_title + " Author: " + b_author + " ISBN: " + ISBN + "\n"; 
	        		books[book_index] = book_info;
	        		book_index++;
	        }
			}

			//String delimiter = "\n";
			//String bookPrint = String.join(delimiter, books);
			bookPrint = "";
			for(int i = 0; i < book_index; i++) {
				bookPrint += books[i];
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        %>

		<script>
        function array_print()
        {
			var books = "<%=bookPrint%>";
        	
        	//document.getElementById("book_list").readOnly = true;
        	document.getElementById("book_list").value = books;
			//var array_size = "<&index&>;
			//document.getElementById("book_list").value = "INFORMATION";
			//textarea.value = books_array.join("\n");
			//textarea.value = "Maybe this will work";
		}
        </script>

</head>

<body>
	<div id="topmid">
		<h1>View Your Books</h1>
	</div>
	
	<div class="home">
		<a href="userhome.jsp">Home</a>
	</div>

	<div class="home">
		<a href="renewbook.jsp">Renew Books</a>
	</div>

	<div class="home">
		<a href="index.html">Logout</a>
	</div>	
	
	<textarea name="comments" id="book_list" style="width:70%;padding:1%;height:550px;margin-left:10%;font:26px/34px cursive;border:10px double gold;">
				
	</textarea>

	<button type="button" onclick="array_print()">View Books</button>	
			
</body>
</html>
