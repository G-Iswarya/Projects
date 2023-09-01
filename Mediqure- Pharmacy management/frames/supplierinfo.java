package frames;
import frames.useroption;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import db.jdbc;

import javax.swing.border.MatteBorder;

public class supplierinfo extends JFrame {
	public int checkuname;
	public String mailpattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
	public String mobilePattern = "^[0-9]*$";
	private JPanel contentPane;
	private JTextField sup_name;
	private JTextField sup_email;
	private JTextField sup_mob;
	private JTextField sup_medname;
	private JTextField sup_medID;
	private JTable table1;
	private JTextField supname;
	private JTextField supmobile;
	private JTextField supmedname;
	private JTextField supemail;
	private JTextField supunamesearchtext;
	private JTextField sup_uname;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					supplierinfo frame = new supplierinfo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public supplierinfo(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1278, 687);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 128));
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(214, 11, 1040, 628);
		contentPane.add(tabbedPane);
		
		JPanel panel1 = new JPanel();
		tabbedPane.addTab("ADD", null, panel1, null);
		panel1.setBackground(new Color(255, 255, 255));
		panel1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("VIEW ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{ int sno = 1;
					DefaultTableModel dt = (DefaultTableModel) table1.getModel();
					dt.setRowCount(0);
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from suppliers");
					while(rs.next())
					{
					dt.addRow(new Object[] {sno, rs.getString(1),rs.getString(6),rs.getString(4),rs.getString(2),rs.getString(3)});
					sno++;
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnNewButton_1.setBounds(53, 299, 137, 54);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("ADD");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		btnNewButton_1_2.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnNewButton_1_2.setBounds(53, 162, 137, 54);
		contentPane.add(btnNewButton_1_2);
		
		JLabel unamevalid = new JLabel("");
		unamevalid.setBounds(482, 410, 99, 50);
		panel1.add(unamevalid);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(482, 310, 149, 25);
		panel1.add(lblUsername);
		
		sup_uname = new JTextField();
		sup_uname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String supuname = sup_uname.getText().toLowerCase();
				if(!supuname.equals(""))
				{
					checkuname = 0;
					unamevalid.setVisible(true);
					unamevalid.setIcon(new ImageIcon("src\\images\\yesicon.png"));
					unamevalid.setText("");
					
					try
					{
						Connection con = jdbc.getCon();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from suppliers where SupplierUserName = '"+supuname+"'");
						while(rs.next())
						{
							checkuname = 1;
							unamevalid.setIcon(new ImageIcon("src\\images\\wrongicon.png"));
						}
						
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				else
				{
					unamevalid.setVisible(false);
				}
				
			}
		});
		sup_uname.setColumns(10);
		sup_uname.setBounds(482, 346, 378, 37);
		panel1.add(sup_uname);
		
		JLabel lblAddSupplier = new JLabel("ADD SUPPLIER");
		lblAddSupplier.setBounds(124, 24, 689, 64);
		lblAddSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSupplier.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel1.add(lblAddSupplier);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(862, 48, -854, 20);
		panel1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(870, 86, -879, 2);
		panel1.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(502, 553, -11, -489);
		panel1.add(separator_2);
		
		JLabel namee = new JLabel("NAME");
		namee.setBounds(29, 111, 83, 25);
		namee.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel1.add(namee);
		
		sup_name = new JTextField();
		sup_name.setBounds(29, 144, 378, 37);
		sup_name.setColumns(10);
		panel1.add(sup_name);
		
		JLabel mobilegiven = new JLabel("MOBILE NUMBER");
		mobilegiven.setBounds(29, 211, 149, 25);
		mobilegiven.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel1.add(mobilegiven);
		
		JLabel email = new JLabel("EMAIL");
		email.setBounds(482, 111, 149, 25);
		email.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel1.add(email);
		
		sup_email = new JTextField();
		sup_email.setBounds(482, 144, 378, 37);
		sup_email.setColumns(10);
		panel1.add(sup_email);
		
		sup_mob = new JTextField();
		sup_mob.setBounds(29, 247, 378, 37);
		sup_mob.setColumns(10);
		panel1.add(sup_mob);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = sup_name.getText();
				String mobile = sup_mob.getText();
				String email = sup_email.getText();
				String medname = sup_medname.getText().toUpperCase();
				String medID = sup_medID.getText();
				String supuname = sup_uname.getText();
				if(name.equals("") || mobile.equals("") || email.equals("") || medname.equals("") || medID.equals("") || supuname.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter all the details");
				}
				else if(!mobile.matches(mobilePattern) || mobile.length() != 10)
				{
					JOptionPane.showMessageDialog(null, "Invalid mobile number");
				}
				else if(!email.matches(mailpattern))
				{
					JOptionPane.showMessageDialog(null, "Invalid email");
				}
				else if(checkuname == 1)
				{
					JOptionPane.showMessageDialog(null, "This username is already taken");
				}
				
				else
				{
						  
						try
						{
							
							Connection con = jdbc.getCon();
							Statement st = con.createStatement();
							st.executeQuery("select * from medicine where MedicineName = '"+medname+"'");
							String query="insert into suppliers (name,mobileNumber,email, MedicineName,MedicineID,SupplierUserName) values ('"+name+"','"+mobile+"','"+email+"','"+medname+"','"+medID+"','"+supuname.toLowerCase()+"');";
							st.executeUpdate(query);
							JOptionPane.showMessageDialog(null, "Supplier added successfully");
							sup_name.setText("");
							sup_mob.setText("");
							sup_email.setText("");
							sup_medname.setText("");
							sup_medID.setText("");
							sup_uname.setText("");
							st.executeUpdate("insert into medicine (MedicineNAme, MedicineID, SupplierName,SupplierUsername)values('"+medname+"','"+medID+"','"+name+"','"+supuname+"')");
							setVisible(false);
							new adminoptions(username).setVisible(true);
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, e1);
						}
						
						
				}
			}
		});
		
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\SAVEICON.png"));
		btnNewButton.setBounds(406, 518, 136, 50);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel1.add(btnNewButton);
		
		JLabel lblMedicineName = new JLabel("MEDICINE NAME");
		lblMedicineName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicineName.setBounds(29, 310, 149, 25);
		panel1.add(lblMedicineName);
		
		sup_medname = new JTextField();
		sup_medname.setColumns(10);
		sup_medname.setBounds(29, 346, 378, 37);
		panel1.add(sup_medname);
		
		JLabel lblMedicineId = new JLabel("MEDICINE ID");
		lblMedicineId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicineId.setBounds(482, 211, 149, 25);
		panel1.add(lblMedicineId);
		
		sup_medID = new JTextField();
		sup_medID.setColumns(10);
		sup_medID.setBounds(482, 247, 378, 37);
		panel1.add(sup_medID);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new adminoptions(username).setVisible(true);
			}
		});
		btnBack.setBounds(794, 14, 136, 50);
		panel1.add(btnBack);
		btnBack.setBackground(new Color(255, 255, 128));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\bg.png"));
		lblNewLabel_1.setBounds(0, 0, 1045, 616);
		panel1.add(lblNewLabel_1);
		
		
		
		
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("VIEW", null, panel3, null);
		panel3.setLayout(null);
		
		JLabel lblViewSupplier = new JLabel("VIEW SUPPLIER");
		lblViewSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewSupplier.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblViewSupplier.setBounds(196, 11, 689, 64);
		panel3.add(lblViewSupplier);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(31, 90, 822, 403);
		panel3.add(scrollPane);
		
		JButton btnNewButton_1_1 = new JButton("UPDATE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton_1_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(53, 446, 137, 54);
		contentPane.add(btnNewButton_1_1);
		
		table1 = new JTable();
		table1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.NO.", "Supplier Name", "Supplier Username", "Medicine name", "MobileNumber", "Email"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table1.getColumnModel().getColumn(0).setMinWidth(10);
		table1.getColumnModel().getColumn(1).setPreferredWidth(105);
		table1.getColumnModel().getColumn(1).setMinWidth(105);
		table1.getColumnModel().getColumn(2).setPreferredWidth(105);
		table1.getColumnModel().getColumn(2).setMinWidth(105);
		table1.getColumnModel().getColumn(3).setPreferredWidth(105);
		table1.getColumnModel().getColumn(3).setMinWidth(105);
		table1.getColumnModel().getColumn(4).setPreferredWidth(105);
		table1.getColumnModel().getColumn(4).setMinWidth(105);
		table1.getColumnModel().getColumn(5).setPreferredWidth(135);
		table1.getColumnModel().getColumn(5).setMinWidth(135);
		scrollPane.setViewportView(table1);
		
		JButton btnNewButton_3 = new JButton("ORDER BY SUPPLIERS");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{ int sno = 1;
					DefaultTableModel dt = (DefaultTableModel) table1.getModel();
					dt.setRowCount(0);
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from suppliers order by name");
					while(rs.next())
					{
					dt.addRow(new Object[] {sno, rs.getString(1),rs.getString(6),rs.getString(4),rs.getString(2),rs.getString(3)});
					sno++;
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(214, 518, 192, 35);
		panel3.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("ORDER BY MEDICINES");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{ int sno = 1;
					DefaultTableModel dt = (DefaultTableModel) table1.getModel();
					dt.setRowCount(0);
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from suppliers order by MedicineName");
					while(rs.next())
					{
					dt.addRow(new Object[] {sno, rs.getString(1),rs.getString(6),rs.getString(4),rs.getString(2),rs.getString(3)});
					sno++;
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3_1.setBounds(621, 518, 192, 35);
		panel3.add(btnNewButton_3_1);
		
		JButton btnBack_1 = new JButton("BACK");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
			}
		});
		btnBack_1.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack_1.setBounds(882, 26, 129, 49);
		panel3.add(btnBack_1);
		
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("EDIT", null, panel2, null);
		panel2.setLayout(null);
		
		JLabel lblEditSupplier = new JLabel("EDIT SUPPLIER");
		lblEditSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditSupplier.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEditSupplier.setBounds(135, 24, 689, 64);
		panel2.add(lblEditSupplier);
		
		JLabel name = new JLabel("Name");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Sitka Text", Font.BOLD, 14));
		name.setBounds(58, 235, 131, 39);
		panel2.add(name);
		
		supname = new JTextField();
		supname.setColumns(10);
		supname.setBounds(265, 229, 201, 46);
		panel2.add(supname);
		
		JLabel supMobilenumber = new JLabel("MobileNumber");
		supMobilenumber.setHorizontalAlignment(SwingConstants.CENTER);
		supMobilenumber.setFont(new Font("Sitka Text", Font.BOLD, 14));
		supMobilenumber.setBounds(58, 325, 131, 39);
		panel2.add(supMobilenumber);
		
		supmobile = new JTextField();
		supmobile.setColumns(10);
		supmobile.setBounds(265, 319, 201, 46);
		panel2.add(supmobile);
		
		JLabel supMedicineName_1 = new JLabel("Medicine name");
		supMedicineName_1.setHorizontalAlignment(SwingConstants.CENTER);
		supMedicineName_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		supMedicineName_1.setBounds(571, 235, 131, 39);
		panel2.add(supMedicineName_1);
		
		supmedname = new JTextField();
		supmedname.setColumns(10);
		supmedname.setBounds(789, 229, 201, 46);
		panel2.add(supmedname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblEmail.setBounds(82, 405, 131, 39);
		panel2.add(lblEmail);
		
		supemail = new JTextField();
		supemail.setColumns(10);
		supemail.setBounds(265, 399, 201, 46);
		panel2.add(supemail);
		
		supunamesearchtext = new JTextField();
		supunamesearchtext.setBounds(217, 99, 410, 46);
		panel2.add(supunamesearchtext);
		supunamesearchtext.setColumns(10);
		
		JButton searchbtn = new JButton("Search");
		
		JButton btnNewButton_2 = new JButton("UPDATE");
		
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!supunamesearchtext.getText().equals(""))
				{
					int supplierexist = 0;
					String supuname = supunamesearchtext.getText();
					try
					{
						Connection con = jdbc.getCon();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from suppliers where SupplierUserName = '"+supuname+"';");
						if(rs.next())
						{
							supplierexist = 1;
							supname.setText(rs.getString(1));
							supmobile.setText(rs.getString(2));
							supemail.setText(rs.getString(3));
							supmedname.setText(rs.getString(4));
							
						}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
					if(supplierexist == 0)
					{
						JOptionPane.showMessageDialog(null, "Supplier doesn't exist");
						supname.setText("");
						supmobile.setText("");
						supemail.setText("");
						supmedname.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Search a data to update");
				}
			}
		});
		searchbtn.setIcon(new ImageIcon(supplierinfo.class.getResource("/images/magnifying-glass_2811806.png")));
		searchbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchbtn.setBounds(668, 99, 131, 39);
		panel2.add(searchbtn);
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String suppname = supname.getText();
				String suppmob = supmobile.getText();
				String suppemail = supemail.getText();
				String suppmedname = supmedname.getText();
				String supuname = supunamesearchtext.getText();
				if(!suppname.equals("") && !suppmob.equals("") && !suppemail.equals("") && !suppmedname.equals(""))
				{
				if(!suppmob.matches(mobilePattern) || suppmob.length() != 10)
				{
					JOptionPane.showMessageDialog(null, "Invalid mobile number");
				}
				else if(!suppemail.matches(mailpattern))
				{
					JOptionPane.showMessageDialog(null, "Invalid email");
				}
				else
				{
					try
					{
						Connection con = jdbc.getCon();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from suppliers where (MedicineName = '"+suppmedname+"') and (SupplierUserName <> '"+supuname+"');");
						if(rs.next())
						{
							JOptionPane.showMessageDialog(null, "Medicine already exists");
						}
						 rs = st.executeQuery("select * from suppliers where (mobileNumber = '"+suppmob+"') and (SupplierUserName <> '"+supuname+"');");
						 if(rs.next())
						{
							JOptionPane.showMessageDialog(null, "User with this mobile number already exists");
						}
						 rs = st.executeQuery("select * from suppliers where (email = '"+suppemail+"') and (SupplierUserName <> '"+supuname+"');");
						 if(rs.next())
						{
							JOptionPane.showMessageDialog(null, "User with this email already exists");
						}
						else
						{
						st.executeUpdate("update suppliers set name = '"+suppname+"',mobileNumber = '"+suppmob+"',email = '"+suppemail+"',MedicineName = '"+suppmedname+"' where SupplierUserName = '"+supuname+"';");
						JOptionPane.showMessageDialog(null, "Supplier has been updated successfully");
						supname.setText("");
						supmobile.setText("");
						supemail.setText("");
						supmedname.setText("");
						
						}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			} 
			else
			{
				JOptionPane.showMessageDialog(null, "Enter the data to update");
			}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(444, 477, 149, 39);
		panel2.add(btnNewButton_2);
		
		JButton btnBack_2 = new JButton("BACK");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
			}
		});
		btnBack_2.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack_2.setBounds(850, 24, 129, 49);
		panel2.add(btnBack_2);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(47, 105, 142, 30);
		panel2.add(lblNewLabel);
	}
}
