package pos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CheckoutView extends JFrame{
	/**
	 * Class responsible for displaying the cart and telling the database to
	 * add the cart to database as checked out
	 */

	// Fields
	private static final long serialVersionUID = 1L;
	//private JPanel mainPanel;
	//private JPanel buttonGroup;
	//private JList<String> bookList;
	private DefaultListModel<String> books;
	//private JButton continueButton;
	//private JButton checkoutButton;
	//private JLabel totalLabel;
	private JTextField totalField;
	private static int totalBooks = 0;
	private DatabaseSearch dbs;
	
	/*
	 * Constructor that sets up the main window
	 * @param dbs DatabaseSearch class for adding checked out books to database
	 */
	public CheckoutView(DatabaseSearch dbs) {
		this.dbs = dbs;
		setSize(800,400);
		setTitle("Checkout");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Setup main panel and background
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JLabel background = new JLabel(makeIcon("pfw-logo.jpg",200,200));
		
		// Instantiate panels for either side of window
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BorderLayout());
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout());
		
		// Setup buttons
		JPanel buttonGroup = new JPanel();
		buttonGroup.setLayout(new BorderLayout());
		JButton checkoutButton = new JButton("Checkout");
		JButton continueButton = new JButton("Continue Searching");
		
		// Add actionhandler to buttons
		ActionHandler ah = new ActionHandler();
		checkoutButton.addActionListener(ah);
		continueButton.addActionListener(ah);
		
		// Set up list to hold book information
		books = new DefaultListModel<String>();
		JList<String> bookList = new JList<String>(books);
		bookList.setBackground(Color.white);

		// Set up display of total books in cart
		JLabel totalLabel = new JLabel("Total Books: ");
		totalField = new JTextField("");
		totalField.setText(" " + totalBooks);

		// Set up display of books in cart
		JScrollPane scroll = new JScrollPane(bookList);
		scroll.setPreferredSize(new Dimension(400,500));
		scroll.setBackground(Color.white);
		
		// Instantiate main panel
		JPanel combinedPanel = new JPanel();
		combinedPanel.setLayout(new BorderLayout());
		
		// Combine label subpanels
		buttonGroup.add(totalLabel, BorderLayout.WEST);
		buttonGroup.add(totalField, BorderLayout.EAST);
		westPanel.add(checkoutButton, BorderLayout.SOUTH);
		westPanel.add(background, BorderLayout.CENTER);

		// Combine button subpanels 
		combinedPanel.add(buttonGroup, BorderLayout.WEST);
		combinedPanel.add(continueButton, BorderLayout.EAST);

		// combine subpanels
		eastPanel.add(scroll, BorderLayout.CENTER);
		eastPanel.add(combinedPanel, BorderLayout.SOUTH);

		// Combine main panels
		mainPanel.add(westPanel, BorderLayout.WEST);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	/*
	 * Method responsible for adding book info to list
	 * @param str Book details
	 */
	public void setBook(String str) {
		books.addElement(str);
	}

	/*
	 * Method responsible for holding and displaying total number
	 * of books in the cart
	 */
	public void addToTotal() {
		totalBooks++;
		totalField.setText(" " + totalBooks);
	}
	
	// Action handler for buttons
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Close current window
			if(e.getActionCommand().equals("Continue Searching")) {
				dispose();
			// Inform DatabaseSearch of checkout, display confirmation, and exit
			}else if(e.getActionCommand().equals("Checkout")) {
				dbs.checkout();
				JOptionPane.showMessageDialog(null, "Books Successfully Checked Out");
				System.exit(0);
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
	
}
