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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel buttonGroup;
	private JList<String> bookList;
	private DefaultListModel<String> books;
	private JButton continueButton;
	private JButton checkoutButton;
	private JLabel totalLabel;
	private JTextField totalField;
	private static int totalBooks = 0;
	
	public CheckoutView() {
		setSize(800,400);
		setTitle("Checkout");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JLabel background = new JLabel(makeIcon("pfw-logo.jpg",300,300));
		//mainPanel.add(background, BorderLayout.CENTER);
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BorderLayout());
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout());
		
		buttonGroup = new JPanel();
		buttonGroup.setLayout(new BorderLayout());
		checkoutButton = new JButton("Checkout");
		continueButton = new JButton("Continue Searching");
		
		ActionHandler ah = new ActionHandler();
		checkoutButton.addActionListener(ah);
		continueButton.addActionListener(ah);
		
		books = new DefaultListModel<String>();
		bookList = new JList<String>(books);
		bookList.setBackground(Color.white);

		totalLabel = new JLabel("Total Books: ");
		totalField = new JTextField("");
		totalField.setText(" " + totalBooks);

		JScrollPane scroll = new JScrollPane(bookList);
		scroll.setPreferredSize(new Dimension(400,500));
		scroll.setBackground(Color.white);
		
		JPanel combinedPanel = new JPanel();
		combinedPanel.setLayout(new BorderLayout());
		

		buttonGroup.add(totalLabel, BorderLayout.WEST);
		buttonGroup.add(totalField, BorderLayout.EAST);
		westPanel.add(checkoutButton, BorderLayout.SOUTH);
		westPanel.add(background, BorderLayout.CENTER);

		//mainPanel.add(scroll, BorderLayout.EAST);
		//mainPanel.add(buttonGroup, BorderLayout.SOUTH);
		
		combinedPanel.add(buttonGroup, BorderLayout.WEST);
		combinedPanel.add(continueButton, BorderLayout.EAST);

		eastPanel.add(scroll, BorderLayout.CENTER);
		eastPanel.add(combinedPanel, BorderLayout.SOUTH);

		mainPanel.add(westPanel, BorderLayout.WEST);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	public void setBook(String str) {
		books.addElement(str);
	}

	public void addToTotal() {
		totalBooks++;
		totalField.setText(" " + totalBooks);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Continue Searching")) {
				dispose();
			}else if(e.getActionCommand().equals("Checkout")) {
				JOptionPane.showMessageDialog(null, "Books Successfully Checked Out");
				System.exit(0);
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
