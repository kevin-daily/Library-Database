package pos;
import javax.swing.*;

import java.awt.event.*;

public class LoginView extends JFrame {
	/**
	 * Class is responsible for presenting a login screen for the user
	 * Also searches the mysql database to ensure the username and password
	 * match before allowing access to the program. Responsible for creating
	 * the DatabaseSearch class
	 */
	private static final long serialVersionUID = -6879670742767337620L;

	// Field instantiation
	private DatabaseSearch dbs = new DatabaseSearch();
	private JButton blogin = new JButton("Login");
	//JPanel panel = new JPanel();
	private JTextField user = new JTextField(15);
	private JPasswordField pass = new JPasswordField(15);
	//JLabel userLabel = new JLabel("Username: ");
	//JLabel passwdLabel = new JLabel("Password: ");
	
	// Main method for instantiating the login window and making it visible
	public static void main(String[] args) {
	LoginView loginFrame = new LoginView();
	loginFrame.setVisible(true);
	}

	// Constructor
	public LoginView(){
	//Setup the window
	super("Login Authentification");
	setSize(300,200);
	setLocation(500,280);
	JPanel panel = new JPanel();
	JLabel userLabel = new JLabel("Username: ");
	JLabel passwdLabel = new JLabel("Password: ");
	panel.setLayout (null);

	// Connect the labels with the fields
	userLabel.setLabelFor(user);
	passwdLabel.setLabelFor(pass);

	// Set the sizes of the elements
	user.setBounds(70,30,150,20);
	userLabel.setBounds(70,15,150,20);
	pass.setBounds(70,65,150,20);
	passwdLabel.setBounds(70,50,150,20);
	blogin.setBounds(110,100,80,20);

	// Add elements to a panel in the window
	panel.add(blogin);
	panel.add(userLabel);
	panel.add(user);
	panel.add(passwdLabel);
	panel.add(pass);

	getContentPane().add(panel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	actionlogin();
	}

	// Method to designation action on login
	public void actionlogin(){
		ActionHandler ah = new ActionHandler();
		
		blogin.addActionListener(ah);
			
	}
	
	
	// Action handler which specifies actions taken when user clicks the login button
	private class ActionHandler implements ActionListener {	
	
		public void actionPerformed(ActionEvent ae) {

				// Fields
				String puname = user.getText();
				char[] passwd = pass.getPassword();
				String password = new String(passwd);
				
				// Search the database for an entry containing both the
				// given username and password
				// puname Username provided by user
				// password Password provided by user
				// returns boolean specifying if the search was 
				// successful or not
				boolean loginStatus = dbs.userSearch(puname, password);
	
				// If true, the main program view is created and made visible
				// and this window is closed. 
				// If false, a popup window tells the user the password or username
				// is wrong and clears the fields for another attempt. 
				if(loginStatus) {
					LibraryMainView libMain = new LibraryMainView(dbs);
					libMain.setVisible(true);
					dispose();
				} else {

					JOptionPane.showMessageDialog(null,"Wrong Username / Password");
					user.setText("");
					pass.setText("");
					user.requestFocus();
				}

		}
	}
	}
