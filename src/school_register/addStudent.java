// GUI for adding a new student to the register

package school_register;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class addStudent extends JFrame {

	private JPanel contentPane;
	private JTextField NameTextField;
	private JTextField SurnameTextField;
	private JTextField IDtextField;
	static JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addStudent window = new addStudent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;


	public addStudent() {
		frame = new JFrame();
		frame.setResizable(false);
		
		connection = Implementation.student_data();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NameLabel = new JLabel("Name:");
		NameLabel.setBounds(77, 53, 76, 14);
		contentPane.add(NameLabel);
		NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		NameTextField = new JTextField();
		NameTextField.setBounds(178, 47, 129, 20);
		contentPane.add(NameTextField);
		NameTextField.setColumns(10);
		
		JLabel SurnameLabel = new JLabel("Surname:");
		SurnameLabel.setBounds(77, 80, 76, 14);
		contentPane.add(SurnameLabel);
		SurnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		SurnameTextField = new JTextField();
		SurnameTextField.setBounds(178, 74, 129, 20);
		contentPane.add(SurnameTextField);
		SurnameTextField.setColumns(10);
		
		JLabel IDlabel = new JLabel("ID:");
		IDlabel.setBounds(77, 105, 76, 14);
		contentPane.add(IDlabel);
		IDlabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//only digits are allowed
		IDtextField = new JTextField();
		IDtextField.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent evt) {
			char c = evt.getKeyChar();
			if(!Character.isDigit(c) && !evt.isAltDown())
				evt.consume();
			}
		});
		
		IDtextField.setBounds(178, 105, 129, 20);
		contentPane.add(IDtextField);
		IDtextField.setColumns(10);
		
		JButton AddBtn = new JButton("Add");
		AddBtn.setBounds(92, 219, 91, 31);
		contentPane.add(AddBtn);
		AddBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "insert into Students (Name,Surname,ID) values (?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,  NameTextField.getText() );
					pst.setString(2,  SurnameTextField.getText() );
					pst.setString(3,  IDtextField.getText() );					

					pst.execute();
					JOptionPane.showMessageDialog(null, "Data saved");
			//		refresh();	
					pst.close();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		
		JButton BackBtn = new JButton("Back");
		BackBtn.setBounds(243, 219, 91, 31);
		contentPane.add(BackBtn);
		BackBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.dispose();
					register RegisterWindow = new register();
					RegisterWindow.frame.setVisible(true);
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}
}
