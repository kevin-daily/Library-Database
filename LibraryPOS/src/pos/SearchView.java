package pos;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2068802620683022014L;
	private JTextField titleField;
	private JTextField authorField;
	private JTextField yearField;
	private JTextField genreField;
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JLabel yearLabel;
	private JLabel genreLabel;

	public SearchView() {
		setLayout(new BorderLayout());

		JButton findBook = new JButton("Search Library");

		// Title text field and label
		titleField = new JTextField(50);
		titleLabel = new JLabel("Book Title: ");
		titleLabel.setLabelFor(titleField);

		// Author text field and label
		authorField = new JTextField(50);
		authorLabel = new JLabel("Book Author: ");
		authorLabel.setLabelFor(authorField);

		// Barcode text field and label
		yearField = new JTextField(50);
		yearLabel = new JLabel("Book Publication Year: ");
		yearLabel.setLabelFor(yearField);

		// Genre text field and label
		genreField = new JTextField(50);
		genreLabel = new JLabel("Book Genre: ");
		genreLabel.setLabelFor(genreField);

		
		JPanel textPanel = new JPanel();
		GridBagLayout gridBag = new GridBagLayout();
		
		textPanel.setLayout(gridBag);
		
		JLabel[] labels = {titleLabel, authorLabel, yearLabel, genreLabel};
		JTextField[] textFields = {titleField, authorField, yearField, genreField};
		addLabelTextRows(labels, textFields, gridBag, textPanel);
		
		add(textPanel, BorderLayout.CENTER);
		add(findBook, BorderLayout.SOUTH);

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
}
