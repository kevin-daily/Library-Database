package pos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class BookList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2513965892057429312L;
	private DefaultListModel<String> books;
	private JList<String> bookList;
	private JButton detailedView;
	
	public BookList() {
		setLayout(new BorderLayout());
		books = new DefaultListModel<String>();
		bookList = new JList<String>(books);
		bookList.setBackground(Color.white);
		
		JScrollPane scroll = new JScrollPane(bookList);
		scroll.setPreferredSize(new Dimension(400,500));
		scroll.setBackground(Color.white);
		add(scroll);
		
		books.addElement("<html>Title: History of Everything<br/>Author: Steven Savage<br/>Genre: Fantasy<br/>Year: 1974<br/>---------------</html>");
		books.addElement("<html>Title: The New Horse<br/>Author: Rick Wagner<br/>Genre: History<br/>Year: 1993<br/>---------------</html>");
		books.addElement("<html>Title: Doom: An Autobiography<br/>Author: Paul Shipper<br/>Genre: Romance<br/>Year: 1895<br/>---------------</html>");
		
		detailedView = new JButton("Book Details");
		ActionHandler ah = new ActionHandler();
		detailedView.addActionListener(ah);
		add(detailedView, BorderLayout.SOUTH);
		
				
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Book Details")) {
				//BookDetails bd = new BookDetails();
				//bd.setVisible(true);
			}
		}
	}

}
