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
	 * 
	 */
	private static final long serialVersionUID = 2937124186837721077L;
	private JPanel mainPanel;
	private JPanel buttonGroup;
	private JList<String> bookList;
	private DefaultListModel<String> books;
	private JButton addToCheckout;
	private JButton cancelView;
	private int index;
	private String searchTerm;
	private String history0;
	private String history1;
	private String history2;
	private String history3;
	private String author0;
	private String author1;
	private String author2;
	private String year0;
	private String year1;
	private String year2;
	private String genre0;
	private String genre1;
	private CheckoutView cv;
	
	public BookDetails(int indx, String srchTrm, CheckoutView chckVw) {
		index = indx;
		searchTerm = srchTrm;
		cv = chckVw;

		setSize(800,400);
		setTitle("Detailed Information");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JLabel background = new JLabel(makeIcon("pfw-logo.jpg",200,200));
		mainPanel.add(background, BorderLayout.LINE_START);
		
		
		books = new DefaultListModel<String>();
		bookList = new JList<String>(books);
		bookList.setBackground(Color.white);
		
		history0 = "<html>Title: History in Action: A Look back at the 2570s<br/>"
									+ "Author: Moonunit Ziffer"
									+ "<br/>Genre: Fantasy"
									+ "<br/>Year: 2974"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 5"
									+ "<br/>Location: Aisle 5, Section 4"
									+ "<br/>----------------------------------------------</html>";
		
		history1 = "<html>Title: History of Everything"
									+ "<br/>Author: Steven Savage"
									+ "<br/>Genre: Fantasy"
									+ "<br/>Year: 1974"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 3"
									+ "<br/>Location: Aisle 5, Section 2"
									+ "<br/>----------------------------------------------<html>";
		
		history2 = "<html>Title: Unicorns or Rhinos?: A True History"
									+ "<br/>Author: Dweezil High"
									+ "<br/>Genre: Autobiography"
									+ "<br/>Year: 1963"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 7"
									+ "<br/> Location: Aisle 1, Section 8"
									+ "</br/>----------------------------------------------</html>";
		
		history3 = "<html>Title: Untold History of the Comma"
									+ "<br/>Author: Phyllis Stenbacker"
									+ "<br/>Genre: Non-Fiction"
									+ "<br/>Year: 1928"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 2"
									+ "<br/>Location: Aisle 3, Section 10"
									+ "<br/>----------------------------------------------</html>";
		
		author0 = "<html>Title: The Neo-Age"
									+ "<br/>Author: Hugo Smith"
									+ "<br/>Genre: Science Fiction"
									+ "<br/>Year: 1991"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 9"
									+ "<br/>Location: Aisle 6, Section 10"
									+ "<br/>----------------------------------------------</html>";
		
		author1 = "<html>Title: Reprogram Yourself to a Better You"
									+ "<br/>Author: Hugo Smith"
									+ "<br/>Genre: Science Fiction"
									+ "<br/>Year: 1998"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 6"
									+ "<br/>Location: Aisle 6, Section 10"
									+ "<br/>----------------------------------------------</html>";
		
		author2 = "<html>Title: The White Rabbit"
									+ "<br/>Author: Hugo Smith"
									+ "<br/>Genre: Science Fiction"
									+ "<br/>Year: 1993"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 4"
									+ "<br/>Location: Aisle 6, Section 10"
									+ "<br/>----------------------------------------------</html>";
		
		year0 = "<html>Title: How to Plow Your Field and Make Friends"
									+ "<br/>Author: Gayle Hall"
									+ "<br/>Genre: Self-Help"
									+ "<br/>Year: 1875"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 10"
									+ "<br/>Location: Aisle 1, Section 2"
									+ "<br/>----------------------------------------------</html>";
		
		year1 = "<html>Title: Lincoln: President or Vampire?"
									+ "<br/>Author: Definitely Human"
									+ "<br/>Genre: Science Fiction"
									+ "<br/>Year: 1875"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 33"
									+ "<br/>Location: Aisle 6, Section 6"
									+ "<br/>----------------------------------------------</html>";
		
		year2 = "<html>Title: How to Learn English Gooder"
									+ "<br/>Author: Nom Chimskey"
									+ "<br/>Genre: Thriller"
									+ "<br/>Year: 1875"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 58"
									+ "<br/>Location: Aisle 15, Section 3"
									+ "<br/>----------------------------------------------</html>";
		
		genre0 = "<html>Title: Shirtless Man Gazers"
									+ "<br/>Author: Seetay Hom Mader"
									+ "<br/>Genre: Romance"
									+ "<br/>Year: 1995"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 22"
									+ "<br/>Location: Aisle 13, Section 5"
									+ "<br/>----------------------------------------------</html>";
		
		genre1 = "<html>Title: Only the Flashiest Win"
									+ "<br/>Author: Ima Bird"
									+ "<br/>Genre: Romance"
									+ "<br/>Year: 2075"
									+ "<br/>---------------"
									+ "<br/>Available Copies: 18"
									+ "<br/>Location: Aisle 13, Section 1"
									+ "<br/>----------------------------------------------</html>";
		
		if(searchTerm.equalsIgnoreCase("history")) {
			if(index == 0) {
				books.addElement(history0);
			}else if(index == 1) {
				books.addElement(history1);
			}else if(index == 2) {
				books.addElement(history2);
			}else if(index == 3) {
				books.addElement(history3);
			}
		}if(searchTerm.equalsIgnoreCase("smith")) {
			if(index == 0) {
				books.addElement(author0);
			}else if(index == 1) {
				books.addElement(author1);
			}else if(index == 2) {
				books.addElement(author2);
			}
		}else if(searchTerm.equalsIgnoreCase("1875")) {
			if(index == 0) {
				books.addElement(year0);
			}else if(index == 1) {
				books.addElement(year1);
			}else if(index == 2) {
				books.addElement(year2);
			}
		}else if(searchTerm.equalsIgnoreCase("romance")) {
			if(index == 0) {
				books.addElement(genre0);
			}else if(index == 1) {
				books.addElement(genre1);
			}
		}
		
		//detailArea = new JTextArea();
		addToCheckout = new JButton("Add to Cart");
		cancelView = new JButton("Cancel");
		
		ActionHandler ah = new ActionHandler();
		addToCheckout.addActionListener(ah);
		cancelView.addActionListener(ah);
		
		buttonGroup = new JPanel();
		buttonGroup.setLayout(new BorderLayout());
		
		buttonGroup.add(addToCheckout, BorderLayout.WEST);
		buttonGroup.add(cancelView, BorderLayout.EAST);
		
		mainPanel.add(bookList, BorderLayout.CENTER);
		
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(buttonGroup, BorderLayout.SOUTH);
		
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Add to Cart")) {
				System.out.println(books.getElementAt(0));
				cv.setBook(books.getElementAt(0));
				cv.addToTotal();
				cv.setVisible(true);
				dispose();
			}else if(e.getActionCommand().equals("Cancel")) {
				dispose();
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
