package pos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

import java.util.Observable;
import java.util.Observer;

public class LibraryMainView extends JFrame implements Observer{
	/**
	 * Class is responsible for drawing the main program window which
	 * allows the user to search for books and displays the search
	 * panel and search result panel
	 */
	private static final long serialVersionUID = -6539806486055104819L;
	// SearchView Fields
	private JTextField titleField;
	private JTextField authorField;
	private JTextField yearField;
	private JTextField genreField;
	//private JLabel titleLabel;
	//private JLabel authorLabel;
	//private JLabel yearLabel;
	//private JLabel genreLabel;
	//private JButton findBook;

	// BookList Fields
	private DefaultListModel<String> books;
	private JList<String> bookList;
	//private JButton detailedView;
	//private JTextArea bookSearchList;
	
	//private String searchTerm;
	//public static JList<String> checkoutList;
	//public static DefaultListModel<String> checkedout;
	
	private CheckoutView cv;
	private DatabaseSearch dbs;
	

	/*
	 * Constructor that sets up the main window and combines
	 * the various panels to create the main view
	 * @param dbs DatabaseSearch class created in LoginView
	 */
	public LibraryMainView(DatabaseSearch dbs) {
		this.dbs = dbs;
		setSize(1000,600);
		setTitle("Welcome to the Library");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// Menu creation
		JMenuBar jmb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_E);
		JMenuItem logOut = new JMenuItem("Logout");
		logOut.setMnemonic(KeyEvent.VK_L);
		JMenuItem viewCart = new JMenuItem("View Cart");
		viewCart.setMnemonic(KeyEvent.VK_V);
		fileMenu.add(logOut);
		fileMenu.add(exitItem);

		// Add the menu items
		jmb.add(fileMenu);
		jmb.add(viewCart);
		
		//BookList bl = new BookList();
		//SearchView sv = new SearchView();	
		
		// Add components to the main window
		getContentPane().add(jmb, BorderLayout.NORTH);
		getContentPane().add(BookList(), BorderLayout.EAST);
		getContentPane().add(SearchView(), BorderLayout.CENTER);
		
		
		// Instantiate action handler and add elements
		ActionHandler ah = new ActionHandler();
		exitItem.addActionListener(ah);
		logOut.addActionListener(ah);
		viewCart.addActionListener(ah);

		// Set size of window
		setSize(999,599);
		setSize(1000,600);
		
		// Instantiate new CheckoutView class and pass 
		// DataBaseSearch class as parameter
		cv = new CheckoutView(dbs);
		
		// Add this window as an observer to DataBaseSearch for
		// displaying results of database searches
		dbs.addObserver(this);
	}

	/*
	 * Panel responsible for holding the search components of the program
	 * @return	JPanel for use by the main window 
	 */
	public JPanel SearchView() {
		// Instantiate the panel, set the layout, and the background image
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout());
		JLabel background = new JLabel(makeIcon("pfw-logo.jpg",400,300));
		searchPanel.add(background, BorderLayout.PAGE_START);
		
		background.setLayout(new BorderLayout());
		
		// Title text field and label
		titleField = new JTextField(50);
		JLabel titleLabel = new JLabel("Book Title: ");
		titleLabel.setLabelFor(titleField);

		// Author text field and label
		authorField = new JTextField(50);
		JLabel authorLabel = new JLabel("Book Author: ");
		authorLabel.setLabelFor(authorField);

		// Year text field and label
		yearField = new JTextField(50);
		JLabel yearLabel = new JLabel("Book Publication Year: ");
		yearLabel.setLabelFor(yearField);

		// Genre text field and label
		genreField = new JTextField(50);
		JLabel genreLabel = new JLabel("Book Genre: ");
		genreLabel.setLabelFor(genreField);
		
		// Button for searching database
		JButton findBook = new JButton("Search Library");
		ButtonHandler bh = new ButtonHandler();
		findBook.addActionListener(bh);
		
		JPanel textPanel = new JPanel();
		GridBagLayout gridBag = new GridBagLayout();
		
		textPanel.setLayout(gridBag);
		
		JLabel[] labels = {titleLabel, authorLabel, yearLabel, genreLabel};
		JTextField[] textFields = {titleField, authorField, yearField, genreField};
		addLabelTextRows(labels, textFields, gridBag, textPanel);
		
		// Add subpanels to the main panel
		searchPanel.add(textPanel, BorderLayout.CENTER);
		searchPanel.add(findBook, BorderLayout.SOUTH);
		
		return searchPanel;
	}
	
	/*
	 * Private method to match labels with the fields for user search input terms
	 *
	 * @param labels Array of JLabel elements
	 * @param textFields Array of JTextField elements
	 * @param gridBad GridBadLayout element
	 * @param container Container element for holding labels and fields
	 */
	private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, GridBagLayout gridBag, Container container) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		int numLabels = labels.length;
		
		for(int i = 0; i < numLabels; i++) {
			c.gridwidth = GridBagConstraints.RELATIVE;
			c.fill= GridBagConstraints.NONE;
			c.weightx = 0.0;
			c.insets = new Insets(20,20,20,20);
			container.add(labels[i], c);
			
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1.0;
			c.insets = new Insets(20,20,20,20);
			container.add(textFields[i], c);
		}
	}
	
	/*
	 * Method responsible for creating a JPanel that holds the elements making up the 
	 * display of the results of the database search
	 *
	 * @return	JPanel that makes up the search result display for the main window
	 */
	public JPanel BookList() {
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		books = new DefaultListModel<String>();
		bookList = new JList<String>(books);
		bookList.setBackground(Color.white);
		
		JScrollPane scroll = new JScrollPane(bookList);
		scroll.setPreferredSize(new Dimension(400,500));
		scroll.setBackground(Color.white);
		
		JButton detailedView = new JButton("Book Details");

		ButtonHandler bh = new ButtonHandler();
		detailedView.addActionListener(bh);

		listPanel.add(scroll, BorderLayout.CENTER);
		listPanel.add(scroll, BorderLayout.CENTER);
		listPanel.add(detailedView, BorderLayout.SOUTH);	
		
		return listPanel;
	}
	
	// Used to control reactions to presses of buttons by user
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e){

			// If user clicks "Book Details" button, get the index of the selected JList
			// element, and pass the index, DataBaseSearch class, and CheckoutView class
			// as arguments to a newly instantiated BookDetails class.
			//
			// Then make the BookDetails window visible to the user
			if(e.getActionCommand().equals("Book Details")) {
				int index = bookList.getSelectedIndex(); // Index starts at 0 for first element
				BookDetails bd = new BookDetails(dbs, cv, index);
				bd.setVisible(true);
			}

			// If user clicks the Search Library button, pull the input from each of the
			// search fields. Check which isn't empty and send the input and the field
			// label to the DataBaseSearch searchDB() method for searching. 
			if(e.getActionCommand().equals("Search Library")) {
				books.clear();

				String genre = genreField.getText();
				String author = authorField.getText();
				String title = titleField.getText();
				String year = yearField.getText();

				if(!genreField.getText().isEmpty()) {
					dbs.searchDB("genre", genre);
					genreField.setText("");
				}else if(!authorField.getText().isEmpty()) {
					dbs.searchDB("author", author);
					authorField.setText("");
				}else if(!titleField.getText().isEmpty()) {
					dbs.searchDB("title", title);
					titleField.setText("");
				}else if(!yearField.getText().isEmpty()) {
					dbs.searchDB("year", year);
					yearField.setText("");
				}

					
				}

		}
	}
	
	
	// Used to control reactions to menu items selected by user
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Exits the program
			if(e.getActionCommand().equals("Exit")) {
				System.exit(0);
			// Instantiates a new login, makes it visible, and closes
			// this window
			}else if(e.getActionCommand().equals("Logout")) {
				LoginView loginFrame = new LoginView();	
				loginFrame.setVisible(true);
				dispose();
			// Opens the checkout view without adding a book
			}else if(e.getActionCommand().equals("View Cart")) {
				cv.setVisible(true);
			}
		}
	}
	
	/*
	 * Method designed to take an image and size it as an icon
	 * @param img String containing the path to the image 
	 * @param i desired height of the icon
	 * @param j desired width of the icon
	 *
	 * @return	ImageIcon created by method
	 */
	private ImageIcon makeIcon(String img, int i, int j) {
		// The process of scaling an image
		ImageIcon ico = new ImageIcon(img);
		Image image = ico.getImage(); // transform it
		Image newimg = image.getScaledInstance(i, j, Image.SCALE_SMOOTH); // scale it the smooth way
		return new ImageIcon(newimg); // transform it ack
	}

	@Override
	public void update(Observable arg0, Object bookEntry) {
		// TODO Auto-generated method stub
		books.addElement((String) bookEntry);
		
	}
}
