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
	private String history0;
	private CheckoutView cv;
	private DatabaseSearch dbs;
	private int index;
	
	public BookDetails(DatabaseSearch dbsearch, CheckoutView chckVw, int index) {
		cv = chckVw;
		dbs = dbsearch;
		this.index = index;

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
		
		books.addElement(dbs.getDetails(index));
		
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
