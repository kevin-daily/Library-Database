# Library Database
### What this is
This program was built for CS 560 at Purdue University Fort Wayne Spring semester 2020. Any suggestions and improvements are welcome!

***

### Overview
This program is a portal for searching and renting books from a library. Books are stored on a SQL database using [MariaDB](https://wiki.archlinux.org/index.php/MariaDB) (a drop-in replacement of MySQL) and the portal is programmed in the Java programming language. There are two separate portals connected by the SQL database. One of the portals is an online webpage that users and admin will be able to access to manage accounts, renewals, book additions and removals from the database, as well as checking rental histories. The second portal is a console program designed to run on computers in the library for users to access and self-checkout books.

***

### Prerequisites
Ensure that you have installed [tomcat7](https://tomcat.apache.org/download-70.cgi) and MySQL or MariaDB. These are used to host the webpage and create and manage the database, respectively. If you choose MySQL, use the documentation to adjust variables and instructions if needed. 

***

## Deployment
The steps to create the database and tables can be found in the *web* folder in *CreateDatabase.sql* but are repeated here for ease of reference. 

**NOTE:** Instructions were written for MariaDB so check the documentation of your database system to ensure instructions match.

***

### **Create and Setup the Database**
Start MySQL - `# mysql`  
Create the database `CREATE DATABASE library_books;`  
Switch to database `USE library_books;`  
Create the table to hold the book information:
```
CREATE TABLE books_tbl ( book_number INT NOT NULL AUTO_INCREMENT,book_author VARCHAR(50) NOT NULL, book_title VARCHAR(100) NOT NULL, book_year SMALLINT NOT NULL, book_genre VARCHAR(20) NOT NULL, book_ISBN BIGINT NOT NULL, book_quantity SMALLINT unsigned NOT NULL, is_checked BOOLEAN NOT NULL, book_location VARCHAR(50) NOT NULL, PRIMARY KEY ( book_number,book_title,book_ISBN ) );
```
Create the table to hold the user information:
```
CREATE TABLE users_tbl ( user_number INT NOT NULL AUTO_INCREMENT, username VARCHAR(50) NOT NULL, password VARCHAR(100) NOT NULL, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, address VARCHAR(100) NOT NULL, phone BIGINT unsigned NOT NULL, email VARCHAR(100) NOT NULL, PRIMARY KEY( user_number,username,email ) );
```
Create the table to hold the checked out books by users:
```
CREATE TABLE user_checkout_tbl ( user_number INT NOT NULL, book_number INT NOT NULL );
```

**NOTE:** Default access is given to user `root` and the default password is blank for MariaDB. You can optionally create a new user for access to the database. Look at the MySQL or MariaDB documentation for how to do this. The program defaults to user `butters` and password `pass` so add this user to use the default. 

### **Webpage Instructions**
#### Build the .war file
Using Eclipse, open the project *Library_Database_Online*. Adjust any neccessary variables to match your SQL user and database. Most of these variables will be found in the *\*.jsp* files in the *web* folder. Look for where the connection to the database is made to see where to make your adjustments. 

After this, open the *build.xml* file in the main project folder and choose Run. It will ask which configuration you would like to launch. Choose the first and then click ok. Once the project is successfully built, you can find the *.war* file in the *dist* folder. 

#### Deploy the webpage
Find the installation folder of tomcat7 and maneuver your way to it. Inside will be a folder name *webapps*. Copy the *Library_Databse_Online.war* file into this folder. If you have setup tomcat7 with autodeploy, it should automatically deploy. 

Go to your preferred browser and type `http://localhost:8080/manager/html/` into the URL field. Be sure to setup a manager username and password with tomcat7 first. After entering the URL, it will ask for your credentials. Enter them and you will be taken to the tomcat7 manager page. 

You will see a list of applications, most of the default from the tomcat7 installation. Find `/Library_Database_Online`, and if not already started, start it. Look under the *Running* column to see if it has started. Then click on `/Library_Database_Online` to the left to deploy the webpage locally. You should now be able to use the website. 

***

### **Console Instruction**
Using your IDE, open the Library_POS as a project. Adjust the variables for user access to your database as needed. This will only need to be done in *DatabaseSearch.java*. From *LoginView.java*, run the program and the login window should appear. 

**NOTE:** The database will be searched for a matching entry with both the username and password, so be sure to either manually enter a username and password into the database, or go through the registration on the webpage. The database will also be empty of books, so either manually enter a few or add them through admin access on the webpage. 

***
### License
MIT License

Copyright (c) 2020 kevin-daily

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
