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

public class LibraryMainView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6539806486055104819L;
	// SearchView Fields
	private JTextField titleField;
	private JTextField authorField;
	private JTextField yearField;
	private JTextField genreField;
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JLabel yearLabel;
	private JLabel genreLabel;
	private JButton findBook;

	// BookList Fields
	private DefaultListModel<String> books;
	private JList<String> bookList;
	private JButton detailedView;
	//private JTextArea bookSearchList;
	
	private String searchTerm;
	public static JList<String> checkoutList;
	public static DefaultListModel<String> checkedout;
	
	private CheckoutView cv;

	public LibraryMainView() {
		setSize(1000,600);
		setTitle("Welcome to the Library");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
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

		jmb.add(fileMenu);
		jmb.add(viewCart);
		
		//BookList bl = new BookList();
		//SearchView sv = new SearchView();	
		
		getContentPane().add(jmb, BorderLayout.NORTH);
		getContentPane().add(BookList(), BorderLayout.EAST);
		getContentPane().add(SearchView(), BorderLayout.CENTER);
		
		
		ActionHandler ah = new ActionHandler();
		exitItem.addActionListener(ah);
		logOut.addActionListener(ah);
		viewCart.addActionListener(ah);

		setSize(999,599);
		setSize(1000,600);
		
		cv = new CheckoutView();
	}

	public JPanel SearchView() {
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout());
		JLabel background = new JLabel(makeIcon("pfw-logo.jpg",400,300));
		searchPanel.add(background, BorderLayout.PAGE_START);
		
		background.setLayout(new BorderLayout());
		
		// Title text field and label
		titleField = new JTextField(50);
		titleLabel = new JLabel("Book Title: ");
		titleLabel.setLabelFor(titleField);

		// Author text field and label
		authorField = new JTextField(50);
		authorLabel = new JLabel("Book Author: ");
		authorLabel.setLabelFor(authorField);

		// Year text field and label
		yearField = new JTextField(50);
		yearLabel = new JLabel("Book Publication Year: ");
		yearLabel.setLabelFor(yearField);

		// Genre text field and label
		genreField = new JTextField(50);
		genreLabel = new JLabel("Book Genre: ");
		genreLabel.setLabelFor(genreField);
		
		findBook = new JButton("Search Library");
		ButtonHandler bh = new ButtonHandler();
		findBook.addActionListener(bh);
		
		JPanel textPanel = new JPanel();
		GridBagLayout gridBag = new GridBagLayout();
		
		textPanel.setLayout(gridBag);
		
		JLabel[] labels = {titleLabel, authorLabel, yearLabel, genreLabel};
		JTextField[] textFields = {titleField, authorField, yearField, genreField};
		addLabelTextRows(labels, textFields, gridBag, textPanel);
		
		searchPanel.add(textPanel, BorderLayout.CENTER);
		searchPanel.add(findBook, BorderLayout.SOUTH);
		
		return searchPanel;
	}
	
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
	
	public JPanel BookList() {
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		books = new DefaultListModel<String>();
		bookList = new JList<String>(books);
		bookList.setBackground(Color.white);
		
		JScrollPane scroll = new JScrollPane(bookList);
		scroll.setPreferredSize(new Dimension(400,500));
		scroll.setBackground(Color.white);
		
		detailedView = new JButton("Book Details");

		ButtonHandler bh = new ButtonHandler();
		detailedView.addActionListener(bh);

		listPanel.add(scroll, BorderLayout.CENTER);
		listPanel.add(scroll, BorderLayout.CENTER);
		listPanel.add(detailedView, BorderLayout.SOUTH);	
		
		return listPanel;
	}
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Book Details")) {
				int index = bookList.getSelectedIndex(); // Index starts at 0 for first element
				BookDetails bd = new BookDetails(index, searchTerm, cv);
				bd.setVisible(true);
			}else if(e.getActionCommand().equals("Search Library")) {
					books.clear();
				if(titleField.getText().equalsIgnoreCase("History")) {
					books.addElement("<html>Title: History in Action: A Look back at the 2570s<br/>Author: Moonunit Ziffer<br/>Genre: Fantasy<br/>Year: 2974<br/>---------------</html>");
					books.addElement("<html>Title: History of Everything<br/>Author: Steven Savage<br/>Genre: Fantasy<br/>Year: 1974<br/>---------------</html>");
					books.addElement("<html>Title: Unicorns or Rhinos?: A True History<br/>Author: Dweezil High<br/>Genre: Autobiography<br/>Year: 1963<br/>---------------</html>");
					books.addElement("<html>Title: Untold History of the Comma<br/>Author: Phyllis Stenbacker<br/>Genre: Non-Fiction<br/>Year: 1928<br/>---------------</html>");
					
					searchTerm = titleField.getText();
					titleField.setText("");
				}else if(authorField.getText().equalsIgnoreCase("Smith")) {
					books.addElement("<html>Title: The Neo-Age<br/>Author: Hugo Smith<br/>Genre: Science Fiction<br/>Year: 1991<br/>---------------</html>");
					books.addElement("<html>Title: Reprogram Yourself to a Better You<br/>Author: Hugo Smith<br/>Genre: Science Fiction<br/>Year: 1998<br/>---------------</html>");
					books.addElement("<html>Title: The White Rabbit<br/>Author: Hugo Smith<br/>Genre: Science Fiction<br/>Year: 1993<br/>---------------</html>");

					searchTerm = authorField.getText();
					authorField.setText("");
				}else if(yearField.getText().equalsIgnoreCase("1875")) {
					books.addElement("<html>Title: How to Plow Your Field and Make Friends<br/>Author: Gayle Hall<br/>Genre: Self-Help<br/>Year: 1875<br/>---------------</html>");
					books.addElement("<html>Title: Lincoln: President or Vampire?<br/>Author: Definitely Human<br/>Genre: Science Fiction<br/>Year: 1875<br/>---------------</html>");
					books.addElement("<html>Title: How to Learn English Gooder<br/>Author: Nom Chimskey<br/>Genre: Thriller<br/>Year: 1875<br/>---------------</html>");

					searchTerm = yearField.getText();
					yearField.setText("");
				}else if(genreField.getText().equalsIgnoreCase("Romance")) {
					books.addElement("<html>Title: Shirtless Man Gazers<br/>Author: Seetay Hom Mader<br/>Genre: Romance<br/>Year: 1995<br/>---------------</html>");
					books.addElement("<html>Title: Only the Flashiest Win<br/>Author: Ima Bird<br/>Genre: Romance<br/>Year: 2075<br/>---------------</html>");
					
					searchTerm = genreField.getText();
					genreField.setText("");
				}else {
					books.addElement("No books match your search terms");
					
					titleField.setText("");
					authorField.setText("");
					yearField.setText("");
					genreField.setText("");
				}
			}
		}
	}
	
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Exit")) {
				System.exit(0);
			}else if(e.getActionCommand().equals("Logout")) {
				LoginView loginFrame = new LoginView();	
				loginFrame.setVisible(true);
				dispose();
			}else if(e.getActionCommand().equals("View Cart")) {
				cv.setVisible(true);
			}
		}
	}
	
	private ImageIcon makeIcon(String img, int i, int j) {
		// The process of scaling an image
		ImageIcon ico = new ImageIcon(img);
		Image image = ico.getImage(); // transform it
		Image newimg = image.getScaledInstance(i, j, Image.SCALE_SMOOTH); // scale it the smooth way
		return new ImageIcon(newimg); // transform it ack
	}
}
