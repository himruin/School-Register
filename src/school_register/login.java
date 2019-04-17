// Login Window GUI

package school_register;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql. *;
import javax.swing. *;
import java.awt.Font;
import java.awt.Image;

public class login {

	static JFrame frame;
	static JTextField IdField;
	static JPasswordField passwordField;
	static JButton LoginButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the application. 
	 */
	public login() throws Exception {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 239);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lDLabel = new JLabel("ID");
		lDLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lDLabel.setBounds(122, 111, 32, 24);
		panel.add(lDLabel);
		
		IdField = new JTextField();
		IdField.setBounds(164, 111, 144, 27);
		panel.add(IdField);
		IdField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(164, 146, 144, 27);
		panel.add(passwordField);
		
		JLabel lDPassword = new JLabel("Password");
		lDPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lDPassword.setBounds(76, 146, 86, 24);
		panel.add(lDPassword);
		
		LoginButton = new JButton("Log in");
		Image img = new ImageIcon(this.getClass().getResource("/enter_icon.png")).getImage();
		LoginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		LoginButton.setIcon(new ImageIcon(img));
		LoginButton.setBounds(260, 184, 130, 44);
		panel.add(LoginButton);
		
		
		JLabel LoginIcon = new JLabel("");
		Image login_img = new ImageIcon(this.getClass().getResource("/login_icon.png")).getImage();
		LoginIcon.setIcon(new ImageIcon(login_img));
		LoginIcon.setBounds(164, 11, 95, 89);
		panel.add(LoginIcon);
		
	}

}
