package pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import java.util.Observable;

public class DatabaseSearch extends Observable{
	// Fields
	//private String searchField;
	//private String searchWord;
	//private String authorName;
	//private String bookTitle;
	private String userName;
	//private String userPswd;
	private String userNmbr;
	//int bookYear;
	//String bookGenre;
	//long bookISBN;
	//int bookQuantity;
	//boolean checkedOut;
	private int bookNumber;
	//String bookLocation;
	private DefaultListModel<String> books;
	private String bookEntry;
	private int[] numberList = new int[1000];
	private int book_index = 0;
	private int[] cartBookNumber = new int[1000];
	private Connection connection;
	
	/*
	 * Constructor responsible for establish connection with database
	 */
	public DatabaseSearch() {
		try {
			// Connect to the database
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "butters", "pass");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * Method responsible for searching the database for an entry with matching username and 
	 * password
	 * @param userName Username provided by the user
	 * @param userPswd Password provided by the user
	 *
	 * @return	Boolean that is true if match was found and false if not
	 */
	public boolean userSearch(String userName, String pwd) {
		this.userName = userName;
		String userPswd = pwd;
		boolean loginStatus = true;
		int userNmbr = 0;
		
		try {
			Statement stmt = null;
			String query = "SELECT user_number FROM users_tbl WHERE username = '" + userName 
								+ "' AND password = '" + userPswd + "'";
								
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()) {
				userNmbr = rs.getInt("user_number");
			}
			
			if(userNmbr == 0) {
				loginStatus = false;
			}
			
			
			
		if(stmt != null) {
			stmt.close();
		}
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		
		return loginStatus;
		
	}
	
	/*
	 * Method is responsible for searching the database for matches to search terms
	 * @param sf Input from the field as given by the user
	 * @param sw Category to be searched e.g., Book Title, Book Author
	 */
	public void searchDB(String sf, String sw) {
		String searchField = sf;
		String searchWord = sw;
		

		books = new DefaultListModel<String>();
		books.clear();

		try{
			Statement stmt = null;
			String query = "";

			// Search the database for entries matching the user input depending on the 
			// category of the search
			//
			// LIKE allows for results that contain the search term as a substring for 
			// greater flexibility if the user does not know the exact entry in the 
			// database
			if(searchField.equalsIgnoreCase("genre")) {
				query = "SELECT * FROM books_tbl WHERE book_genre LIKE '%" + searchWord + "%'";
			}else if(searchField.equalsIgnoreCase("author")) {
				query = "SELECT * FROM books_tbl WHERE book_author LIKE '%" + searchWord + "%'";
			}else if(searchField.equalsIgnoreCase("title")) {
				query = "SELECT * FROM books_tbl WHERE book_title LIKE '%" + searchWord + "%'";
			}else if(searchField.equalsIgnoreCase("year")) {
				query = "SELECT * FROM books_tbl WHERE book_year LIKE '%" + searchWord + "%'";
			}

			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int index = 0;
			String authorName;
			String bookTitle;
			// For each found entry, author, title, unique number are saved
			// The author and title are used for display and unique number is 
			// used for later insertion as a checked out book
			while(rs.next()) {
				authorName = rs.getString("book_author");
				bookTitle = rs.getString("book_title");
				bookNumber = rs.getInt("book_number");

				numberList[index] = bookNumber;
				index++;

				bookEntry = "<html>Title: " + bookTitle + "<br/>Author: " + authorName + "<br/><br/></html>";
				setChanged();
				notifyObservers(bookEntry);
			}

			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
	}
	
	/*
	 * Method responsible for adding selected book to cart by saving unique number in array
	 */	
	public void addToCart() {
		cartBookNumber[book_index] = bookNumber;
		book_index++;
	}
	
	/*
	 * Method responsible for inserting checked out books to database for persistence. User name
	 * and unique book number are inserted to the database table user_checkout_tbl
	 */
	public void checkout() {
		try {
			String query = "INSERT INTO user_checkout_tbl (username, book_number) VALUES (?, ?)";
			
			// PreparedStatement is used with .addBatch() to allow for multiple
			// entries to be inserted at once into the database
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			for(int i = 0; i < book_index; i++) {
			pstmt.setString(1, userName);
			pstmt.setInt(2, cartBookNumber[i]);
			pstmt.addBatch();
			}
			
			// Insert all entries when finished adding the batch
			pstmt.executeBatch();
			
		}
		catch(SQLException sqle) {
			System.out.println(sqle);
		}
	}
	
	/*
	 * Method responsible for search the database for an entry and retrieving detailed information
	 * @param i index of array that holds the entry
	 *
	 * @return	The string with detailed information for displaying
	 */
	public String getDetails(int i) {

		try{
			Statement stmt = null;
			String query = "";

			query = "SELECT * FROM books_tbl WHERE book_number = '" + numberList[i] + "'";

			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			String authorName;
			String bookTitle;
			int bookYear;
			String bookGenre;
			long bookISBN;
			String bookLocation;
			// For each found entry, get author, title, year, genre, ISBN, 
			// unique number, and location
			while(rs.next()) {
				authorName = rs.getString("book_author");
				bookTitle = rs.getString("book_title");
				bookYear = rs.getInt("book_year");
				bookGenre = rs.getString("book_genre");
				bookISBN = rs.getLong("book_ISBN");
				bookNumber = rs.getInt("book_number");
				bookLocation = rs.getString("book_location");
				
				// Create string with detailed book information
				bookEntry = "<html>Title: " + bookTitle + 
						"<br/>Author: " + authorName + 
						"<br/>Genre: " + bookGenre +
						"<br/>Year: " + bookYear +
						"<br/>ISBN: " + bookISBN + 
						"<br/>-------------------------" +
						"<br/>" + bookLocation +
						"<br/>..........................<br/></html>";
			}

			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return bookEntry;
	}
}
