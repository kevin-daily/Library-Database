package pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import java.util.Observable;

public class DatabaseSearch extends Observable{
	private String searchField;
	private String searchWord;
	private String authorName;
	private String bookTitle;
	int bookYear;
	String bookGenre;
	long bookISBN;
	int bookQuantity;
	boolean checkedOut;
	int bookNumber;
	String bookLocation;
	DefaultListModel<String> books;
	String bookEntry;
	int[] numberList = new int[1000];
	Connection connection;
	
	public DatabaseSearch() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void searchDB(String sf, String sw) {
		searchField = sf;
		searchWord = sw;
		

		books = new DefaultListModel<String>();
		books.clear();

		try{
			Statement stmt = null;
			String query = "";

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
	
	public String getDetails(int i) {

		try{
			Statement stmt = null;
			String query = "";

			query = "SELECT * FROM books_tbl WHERE book_number = '" + numberList[i] + "'";

			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()) {
				authorName = rs.getString("book_author");
				bookTitle = rs.getString("book_title");
				bookYear = rs.getInt("book_year");
				bookGenre = rs.getString("book_genre");
				bookISBN = rs.getLong("book_ISBN");
				bookQuantity = rs.getInt("book_quantity");
				checkedOut = rs.getBoolean("is_checked");
				bookNumber = rs.getInt("book_number");
				bookLocation = rs.getString("book_location");
				
				bookEntry = "<html>Title: " + bookTitle + 
						"<br/>Author: " + authorName + 
						"<br/>Genre: " + bookGenre +
						"<br/>Year: " + bookYear +
						"<br/>Quantity: " + bookQuantity +
						"<br/>ISBN: " + bookISBN + 
						"<br/>-------------------------" +
						"<br/>" + bookLocation
						+ "<br/>..........................<br/></html>";
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
