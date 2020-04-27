<%@page import="java.sql.DriverManager"%>
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
		background-image: url("purdue-fort-wayne-logo.jpg");
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
		<h1>Renew Your Books</h1>
	</div>
	
	<div class="home">
		<a href="userhome.jsp">Home</a>
	</div>

	<div class="home">
		<a href="viewbook_1_1.jsp">View Books</a>
	</div>

	<div class="home">
		<a href="index.html">Logout</a>
	</div>
	
	<table class="tabl hd red">
		<tr>
			<td>Book Successfully Renewed</td>
		</tr>
	</table>

	<table class="tab align" border="0px" cellpadding="10" cellspacing="10" text-color="red">
		<tr>
			<td>
				<a href="AddBook.html">Renew Another Book</a>	
			</td>
		</tr>
	</table>
	
	<table class="tab black">
		<tr>
			<td><a href="userhome.jsp">Back to Home Page</a></td>
		</tr>
	</table>
	

</body>
</html>