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
</head>

<body>
	<div id="topmid">
		<h1>Welcome to the Library</h1>
	</div>
	
	<div class="home">
		<a href="index.html">Logout</a>
	</div>

	<div class="tab hd brown">User Login Successful!</div><br/>
	<h2>Welcome To User::::<%=session.getAttribute("uid").toString()%></h2>

	
				
	<div class="tab"><a href="viewbook_1_1.jsp">View Books</a></div>;
	<div class='tab'><a href="renewbook.jsp">Renew Books</a></div>
		
	

</body>
</html>