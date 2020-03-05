package pos;
import javax.swing.*;

import java.awt.event.*;

public class LoginView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6879670742767337620L;
	JButton blogin = new JButton("Login");
	JPanel panel = new JPanel();
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	JLabel userLabel = new JLabel("Username: ");
	JLabel passwdLabel = new JLabel("Password: ");
	
	// TO-DO
	// View Cart menuitem
	// Go back to login when checkout
	// fix the history rhino book
	//
	
	public static void main(String[] args) {
	LoginView loginFrame = new LoginView();
	loginFrame.setVisible(true);
	}

	public LoginView(){
	super("Login Authentification");
	setSize(300,200);
	setLocation(500,280);
	panel.setLayout (null);

	userLabel.setLabelFor(txuser);
	passwdLabel.setLabelFor(pass);

	txuser.setBounds(70,30,150,20);
	userLabel.setBounds(70,15,150,20);
	pass.setBounds(70,65,150,20);
	passwdLabel.setBounds(70,50,150,20);
	blogin.setBounds(110,100,80,20);

	panel.add(blogin);
	panel.add(userLabel);
	panel.add(txuser);
	panel.add(passwdLabel);
	panel.add(pass);

	getContentPane().add(panel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	actionlogin();
	}

	public void actionlogin(){
		ActionHandler ah = new ActionHandler();
		
		blogin.addActionListener(ah);
			
	}
	
	
	private class ActionHandler implements ActionListener {	
	
		public void actionPerformed(ActionEvent ae) {

				String puname = txuser.getText();
				char[] passwd = pass.getPassword();
				String password = new String(passwd);
	
				if(puname.equals("user") && password.equals("login")) {
					LibraryMainView libMain = new LibraryMainView();
					libMain.setVisible(true);
					dispose();
				} else {

					JOptionPane.showMessageDialog(null,"Wrong Password / Username");
					txuser.setText("");
					pass.setText("");
					txuser.requestFocus();
				}

		}
	}
	}
