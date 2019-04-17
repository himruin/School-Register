//School Register Window GUI + internal queries

package school_register;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql. *;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing. *;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class register  {

	private JPanel contentPane;
	private static JTable table;
	static JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static Connection connection = null;
	static JButton RefreshBtn;
	private JLabel PresenceLabel;
	private JTextField PresenceTextField;
	private JLabel MarksLabel;
	private JTextField MarksTextField;
	private JButton EditBtn;
	private JComboBox<String> StudentCBox;
	private JButton FinalMarkBtn;
	private JTextField AverageTextField;
	private JTextField AvgPresenceTextField;
	private JTextField FinalMarkTextField;
	private JLabel TimeLbl;
	private JLabel DateLbl;
	private JButton HelpButton;
	static JButton addNewStudentBtn;
	
	
	public void clock() {
		
		Thread clock = new Thread() {
			public void run() {
				try {
					for(;;) {
					Calendar cal = new GregorianCalendar();
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);
					
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR_OF_DAY);	
					
					TimeLbl.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second));
					DateLbl.setText(String.format("%02d",day )+ "-" + String.format("%02d",month) + "-" + year);
					
					sleep(1000);
					}
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
	}
	
	public static void fillStudentTable() {
		try {
			String query = "select * from Students" + " ORDER BY " + 2 + " ASC";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static void newStudentBtn() {
		try {
			frame.dispose();
			addStudent addS = new addStudent();
			addS.frame.setVisible(true);
			frame.dispose();
			}
		catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public static void refresh() {
		try {
			fillStudentTable();
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void fillComboBox()
	{
		try {			
			refresh();

			String query = "select * from Students" + " ORDER BY " + 2 + " ASC";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				String StudentInfo;
				StudentInfo=rs.getString("Surname") + ", " + rs.getString("ID");
				StudentCBox.addItem(StudentInfo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	
	public void updateFinalStudent()
	{
		try {			
			double AverageMark = 0.0;
			double AveragePresence = 0.0;
			String StudentInfo;
			String[] StudentTemp;
			String delimeter = ", ";
			String MarksInfo;
			String[] MarksTemp;
			
			String PresenceInfo;
			String[] PresenceTemp;
			

			String query = "select Presence, Marks from Students where ID = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			
			StudentInfo = (String) StudentCBox.getSelectedItem();
			

			
			StudentTemp = StudentInfo.split(delimeter);
			pst.setString(1, StudentTemp[1]);
			ResultSet rs = pst.executeQuery();

			
			while(rs.next())
			{
				PresenceTextField.setText(rs.getString("Presence"));
				MarksTextField.setText(rs.getString("Marks"));
				
				//Average Mark
				if (rs.getString("Marks") == null) {
					AverageMark = 0;
				}
				else {
					MarksInfo = rs.getString("Marks");
					MarksTemp = MarksInfo.split(delimeter);
					double sum = 0.0;
					for (int i=0; i<=MarksTemp.length-1;i++)
						sum += Double.parseDouble(MarksTemp[i]);
				
					AverageMark = sum / MarksTemp.length;
				}
				DecimalFormat formatter = new DecimalFormat("##.##"); //
				formatter.setRoundingMode(RoundingMode.UP);
				String FormattedAvgMark = formatter.format(AverageMark);
				FormattedAvgMark = FormattedAvgMark.replaceAll(",",".");
				AverageTextField.setText(FormattedAvgMark);
				
				//Average Presence
				if (rs.getString("Presence") == null) {
					AveragePresence = 0;
				}
				else {
					PresenceInfo = rs.getString("Presence");
					PresenceTemp = PresenceInfo.split("");
					double PresenceSum = 0.0;
					for (int i=0; i<=PresenceTemp.length-1;i++)
						PresenceSum += Double.parseDouble(PresenceTemp[i]);
					
					AveragePresence = PresenceSum / PresenceTemp.length * 100;
				}
					DecimalFormat dc = new DecimalFormat("0");
					String FormattedAvgPresence = dc.format(AveragePresence);
					AvgPresenceTextField.setText(FormattedAvgPresence + "%");						
			}
			
			pst.close();
			}
		
		catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	
	public register() {
		//school register window
		frame = new JFrame();
		frame.setResizable(false);
		connection = Implementation.student_data();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 750, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//table
		JScrollPane StudentTable = new JScrollPane();
		StudentTable.setEnabled(false);
		StudentTable.setBounds(10, 45, 495, 441);
		contentPane.add(StudentTable);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setBackground(Color.LIGHT_GRAY);
		table.setEnabled(false);
		StudentTable.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		fillStudentTable();

		
		//new Student Button
		JButton addNewStudentBtn = new JButton("add new Student");
		addNewStudentBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addNewStudentBtn.setBounds(10, 524, 152, 23);
		contentPane.add(addNewStudentBtn);		
		addNewStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newStudentBtn();
			}
		}); 
		
		//Refresh student list Button
		RefreshBtn = new JButton("Refresh");
		RefreshBtn.setBounds(10, 11, 89, 23);
		contentPane.add(RefreshBtn);
		RefreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					refresh();
			}
		});
		
		
		//Combo Box
		StudentCBox = new JComboBox<String>();
		StudentCBox.setBackground(Color.ORANGE);
		StudentCBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		StudentCBox.setBounds(543, 45, 191, 20);
		contentPane.add(StudentCBox);
		StudentCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					updateFinalStudent();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		PresenceLabel = new JLabel("Presence:");
		PresenceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PresenceLabel.setBounds(519, 92, 76, 14);
		contentPane.add(PresenceLabel);
		
		
		PresenceTextField = new JTextField();
		PresenceTextField.setColumns(10);
		PresenceTextField.setBounds(605, 92, 129, 20);
		contentPane.add(PresenceTextField);
		PresenceTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				
				char c = evt.getKeyChar();
				if(c!='0' && c!='1'  && !evt.isAltDown())
					evt.consume();
			}
		});

		
		MarksLabel = new JLabel("Marks:");
		MarksLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MarksLabel.setBounds(519, 123, 76, 14);
		contentPane.add(MarksLabel);
		
		MarksTextField = new JTextField();
		MarksTextField.setColumns(10);
		MarksTextField.setBounds(605, 123, 129, 20);
		contentPane.add(MarksTextField);
		MarksTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(Character.isLetter(c) && !evt.isAltDown())
					evt.consume();	
			}
		});
		
		AvgPresenceTextField = new JTextField();
		AvgPresenceTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
		AvgPresenceTextField.setEditable(false);
		AvgPresenceTextField.setColumns(10);
		AvgPresenceTextField.setBounds(643, 226, 52, 20);
		contentPane.add(AvgPresenceTextField);
		
		
		EditBtn = new JButton("Edit");
		EditBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		EditBtn.setBounds(643, 160, 91, 31);
		contentPane.add(EditBtn);
		EditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String StudentInfo;
					String delimeter = ", ";
					String[] temp;
					
					StudentInfo = (String) StudentCBox.getSelectedItem();				
					temp = StudentInfo.split(delimeter);
					String query = "UPDATE Students SET Presence='"+PresenceTextField.getText()+"', Marks='"+MarksTextField.getText()+"' WHERE ID = '"+temp[1]+"'	";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data updated");
					
					refresh();
					updateFinalStudent();
					pst.close();
					
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});						
			
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		LogOutBtn.setBounds(643, 524, 91, 23);
		contentPane.add(LogOutBtn);
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.dispose();
					login window = new login();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		
		AverageTextField = new JTextField();
		AverageTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
		AverageTextField.setEditable(false);
		AverageTextField.setColumns(10);
		AverageTextField.setBounds(643, 257, 52, 20);
		contentPane.add(AverageTextField);
		
		JLabel AverageLabel = new JLabel("Average:");
		AverageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		AverageLabel.setBounds(556, 224, 76, 23);
		contentPane.add(AverageLabel);
		
	
		FinalMarkTextField = new JTextField();	
		FinalMarkTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		FinalMarkTextField.setColumns(10);
		FinalMarkTextField.setBounds(583, 314, 66, 31);
		contentPane.add(FinalMarkTextField);
		FinalMarkTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(Character.isLetter(c) && !evt.isAltDown())
					evt.consume();
			}
		});
		
		
		FinalMarkBtn = new JButton("Final Mark");
		FinalMarkBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		FinalMarkBtn.setBounds(556, 356, 127, 31);
		contentPane.add(FinalMarkBtn);
		FinalMarkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {			
					refresh();
					String StudentInfo = (String) StudentCBox.getSelectedItem();
					String delimeter = ", ";
					String[]StudentInfoTemp = StudentInfo.split(delimeter);
					
					String query = "UPDATE Students SET FinalMark='"+FinalMarkTextField.getText()+"' WHERE ID = '"+StudentInfoTemp[1]+"'	";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "The final mark of the student (ID:  " + StudentInfoTemp[1] + " ) is updated.");
					
					refresh();	
					pst.close();
				}
				catch (Exception e) {
					e.printStackTrace();	
				}
			}
		});
		
		
		TimeLbl = new JLabel("Time");
		TimeLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		TimeLbl.setBounds(570, 11, 69, 14);
		contentPane.add(TimeLbl);
		
		DateLbl = new JLabel("Date");
		DateLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		DateLbl.setBounds(649, 11, 76, 14);
		contentPane.add(DateLbl);
		
		HelpButton = new JButton("HELP");
		HelpButton.setForeground(Color.RED);
		HelpButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		HelpButton.setBounds(135, 11, 89, 23);
		contentPane.add(HelpButton);
		HelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoBox Info = new InfoBox();
				Info.setVisible(true);
			}
		});
		
		
		clock();
		fillComboBox();
		refresh();
		updateFinalStudent();
		
	}
}
