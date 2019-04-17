// Window with basic information about an usage of the application

package school_register;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoBox extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoBox frame = new InfoBox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfoBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel InfoLbl = new JLabel("INFO");
		InfoLbl.setForeground(Color.RED);
		InfoLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		InfoLbl.setBounds(190, 11, 46, 14);
		contentPane.add(InfoLbl);
		
		JLabel Info1Lbl = new JLabel("1. In order to select a student, use a yellow box.");
		Info1Lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		Info1Lbl.setBounds(22, 36, 377, 14);
		contentPane.add(Info1Lbl);
		
		JLabel Info2Lbl = new JLabel("<html>\r\n2. To add  marks and presence values, update Presence and Marks text fields with proper values, then click EDIT Button ( Make sure that the appropriate student is being edited).\r\n</html>");
		Info2Lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		Info2Lbl.setBounds(22, 61, 402, 85);
		contentPane.add(Info2Lbl);
		
		JLabel Info3Lbl = new JLabel("3. Separate each new mark with \", \" (comma and space) !");
		Info3Lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		Info3Lbl.setBounds(22, 144, 402, 24);
		contentPane.add(Info3Lbl);
		
		JLabel Info4Lbl = new JLabel("<html>\r\n4. Fill presence values (0 - absent, 1- present) without any delimiter (spaces, commas, etc.)!\r\n</html>");
		Info4Lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		Info4Lbl.setBounds(22, 182, 402, 34);
		contentPane.add(Info4Lbl);
		
		JButton CloseBtn = new JButton("Close");
		CloseBtn.setForeground(Color.RED);
		CloseBtn.setBounds(169, 227, 89, 23);
		contentPane.add(CloseBtn);
		CloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}
}
