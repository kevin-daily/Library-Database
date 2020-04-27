package pos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class BookDetails extends JFrame{
	/**
	 * Class is responsible for drawing a new window that displays greater details about
	 * a book selected by the user
	 */

	// Fields
	private static final long serialVersionUID = 2937124186837721077L;
	//private JPanel mainPanel;
	//private JPanel buttonGroup;
	//private JList<String> bookList;
	private DefaultListModel<String> books;
	//private JButton addToCheckout;
	//private JButton cancelView;
	private CheckoutView cv;
	private DatabaseSearch dbs;
	private int index;
	
	/*
	 * Constructor responsible for setting up the main window
	 * @param dbs DatanaseSearch used for searching the database
	 * @param cv CheckoutView used to receive details for checkout
	 * @param index Index of the selected book in the result list
	 */
	public BookDetails(DatabaseSearch dbs, CheckoutView cv, int index) {
		this.cv = cv;
		this.dbs = dbs;
		this.index = index;

		setSize(800,400);
		setTitle("Detailed Information");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Set up the main window
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JLabel background = new JLabel(makeIcon("pfw-logo.jpg",200,200));
		mainPanel.add(background, BorderLayout.LINE_START);
		
		
		// List to hold the results of the search
		books = new DefaultListModel<String>();
		JList<String> bookList = new JList<String>(books);
		bookList.setBackground(Color.white);
		
		// Add the results of the search to the list
		books.addElement(dbs.getDetails(index));
		
		// Add buttons
		JButton addToCheckout = new JButton("Add to Cart");
		JButton cancelView = new JButton("Cancel");
		
		// Add buttons to actionhandler
		ActionHandler ah = new ActionHandler();
		addToCheckout.addActionListener(ah);
		cancelView.addActionListener(ah);
		
		// Setup panel to hold the buttons
		JPanel buttonGroup = new JPanel();
		buttonGroup.setLayout(new BorderLayout());
		
		// Add buttons to panel
		buttonGroup.add(addToCheckout, BorderLayout.WEST);
		buttonGroup.add(cancelView, BorderLayout.EAST);
		
		// Construct main panel
		mainPanel.add(bookList, BorderLayout.CENTER);
		
		// Construct main window
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(buttonGroup, BorderLayout.SOUTH);
		
	}
	
	// Handles input from the user in the form of the buttons
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Send book details to CheckoutView for display, add 
			// book to running total number of books, make the 
			// CheckoutView window visible, add the book to the
			// running list for later insertion into database,
			// close the current window
			if(e.getActionCommand().equals("Add to Cart")) {
				cv.setBook(books.getElementAt(0));
				cv.addToTotal();
				cv.setVisible(true);
				dbs.addToCart();
				dispose();
			// Close the current window without doing anything
			// further
			}else if(e.getActionCommand().equals("Cancel")) {
				dispose();
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
		return new ImageIcon(newimg); // transform it back
	}
}
