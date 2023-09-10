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
import javax.swing.UIManager;
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
import java.sql.SQLException;
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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JTextField sup_uname;
	private JTable table;

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
		
		
		UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(214, 11, 1040, 639);
		tabbedPane.setBackground(new Color(0,0,8,12));
		tabbedPane.setOpaque(false);
		tabbedPane.setFocusable(false);
		contentPane.add(tabbedPane);
		
		JButton btnNewButton_1 = new JButton("VIEW ");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\viewicon.png"));
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
		btnNewButton_1_2.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\add-post.png"));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		btnNewButton_1_2.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnNewButton_1_2.setBounds(53, 162, 137, 54);
		contentPane.add(btnNewButton_1_2);
		
		JPanel panel1 = new JPanel();
		panel1.setForeground(new Color(254, 250, 203));
		tabbedPane.addTab("ADD", null, panel1, null);
		panel1.setBackground(new Color(0, 0, 0,100));
		panel1.setLayout(null);
		
		JLabel unamevalid = new JLabel("");
		unamevalid.setBounds(873, 335, 99, 50);
		panel1.add(unamevalid);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(new Color(254, 250, 203));
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
		lblAddSupplier.setForeground(new Color(254, 250, 203));
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
		namee.setForeground(new Color(254, 250, 203));
		namee.setBounds(29, 111, 83, 25);
		namee.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel1.add(namee);
		
		sup_name = new JTextField();
		sup_name.setBounds(29, 144, 378, 37);
		sup_name.setColumns(10);
		panel1.add(sup_name);
		
		JLabel mobilegiven = new JLabel("MOBILE NUMBER");
		mobilegiven.setForeground(new Color(254, 250, 203));
		mobilegiven.setBounds(29, 211, 149, 25);
		mobilegiven.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel1.add(mobilegiven);
		
		JLabel email_1 = new JLabel("EMAIL");
		email_1.setForeground(new Color(254, 250, 203));
		email_1.setBounds(482, 111, 149, 25);
		email_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel1.add(email_1);
		
		sup_email = new JTextField();
		sup_email.setBounds(482, 144, 378, 37);
		sup_email.setColumns(10);
		panel1.add(sup_email);
		
		sup_mob = new JTextField();
		sup_mob.setBounds(29, 247, 378, 37);
		sup_mob.setColumns(10);
		panel1.add(sup_mob);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBackground(new Color(222, 220, 133));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = sup_name.getText().replaceAll("\\s+","");
				String mobile = sup_mob.getText().replaceAll("\\s+","");
				String email = sup_email.getText().replaceAll("\\s+","");
				String medname = sup_medname.getText().replaceAll("\\s+","").toUpperCase();
				String medID = sup_medID.getText().replaceAll("\\s+","");
				String supuname = sup_uname.getText().replaceAll("\\s+","");
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
							int flag=0;
							Connection con = jdbc.getCon();
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery("select * from medicine where MedicineName = '"+medname+"'");
							while(rs.next())
							{
								flag=1;
								JOptionPane.showMessageDialog(null, "This medicine already exists");
							}
							if(flag==0) {
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
		lblMedicineName.setForeground(new Color(254, 250, 203));
		lblMedicineName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicineName.setBounds(29, 310, 149, 25);
		panel1.add(lblMedicineName);
		
		sup_medname = new JTextField();
		sup_medname.setColumns(10);
		sup_medname.setBounds(29, 346, 378, 37);
		panel1.add(sup_medname);
		
		JLabel lblMedicineId = new JLabel("MEDICINE ID");
		lblMedicineId.setForeground(new Color(254, 250, 203));
		lblMedicineId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicineId.setBounds(482, 211, 149, 25);
		panel1.add(lblMedicineId);
		
		JLabel validmedid = new JLabel("");
		validmedid.setBounds(881, 247, 91, 37);
		panel1.add(validmedid);
		
		
		sup_medID = new JTextField();
		sup_medID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int checkuname;
				String medid = sup_medID.getText();
				if(!sup_medID.equals(""))
				{
					validmedid.setVisible(true);
					validmedid.setIcon(new ImageIcon("src\\images\\yesicon.png"));
        			validmedid.setText("");
        			checkuname = 0;
        			
        			try
        			{
        				Connection con = jdbc.getCon();
        				Statement st = con.createStatement();
        				ResultSet rs = st.executeQuery("select * from medicine where MedicineID = '"+medid+"'");
        				while(rs.next())
        				{
        					checkuname = 1;
        					validmedid.setIcon(new ImageIcon("src\\images\\wrongicon.png"));
        					validmedid.setText("");
        				}
        				
        			}
        			catch(Exception e1)
        			{
        				JOptionPane.showMessageDialog(null, e1);
        			}
				}
				else
				{
					validmedid.setVisible(false);
				}
			}
		});
		sup_medID.setColumns(10);
		sup_medID.setBounds(482, 247, 378, 37);
		panel1.add(sup_medID);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new adminoptions(username).setVisible(true);
			}
		});
		btnBack.setBounds(794, 14, 136, 50);
		panel1.add(btnBack);
		btnBack.setBackground(new Color(222, 220, 133));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		
		
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(0,0,0,100));
		tabbedPane.addTab("VIEW", null, panel3, null);
		panel3.setLayout(null);
		
		JLabel lblViewSupplier = new JLabel("VIEW SUPPLIER");
		lblViewSupplier.setForeground(new Color(254, 250, 203));
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
		btnNewButton_1_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\updated.png"));
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
		btnNewButton_3.setBackground(new Color(254, 250, 203));
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
		btnNewButton_3.setBounds(210, 543, 192, 35);
		panel3.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("ORDER BY MEDICINES");
		btnNewButton_3_1.setBackground(new Color(254, 250, 203));
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
		btnNewButton_3_1.setBounds(609, 543, 192, 35);
		panel3.add(btnNewButton_3_1);
		
		JButton btnBack_1 = new JButton("BACK");
		btnBack_1.setBackground(new Color(254, 250, 203));
		btnBack_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
				setVisible(false);
			}
		});
		btnBack_1.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack_1.setBounds(882, 26, 129, 49);
		panel3.add(btnBack_1);
		
		JButton btnNewButton_3_2 = new JButton("DELETE");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rep = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
				if(rep==JOptionPane.YES_OPTION)
				{
				DefaultTableModel dt = (DefaultTableModel)table1.getModel();
				int row = table1.getSelectedRow();
				Connection con;
				try {
					int flag = 0;
					con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from medicine where MedicineName='"+table1.getValueAt(row, 3)+"';");
					while(rs.next())
					{
						if(Integer.parseInt(rs.getString("Quantity").toString())<=0)
						{
							flag = 1;
						}
					}
					if(flag==1)
					{
					con = jdbc.getCon();
					st = con.createStatement();
					st.executeUpdate("delete from suppliers where MedicineName='"+table1.getValueAt(row, 3)+"';");
					st.executeUpdate("delete from medicine where MedicineName='"+table1.getValueAt(row, 3)+"';");
					dt.removeRow(row);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Cannot delete. Medicines are not sold");
					}
				}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
			}
			
		}});
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3_2.setBackground(new Color(254, 250, 203));
		btnNewButton_3_2.setBounds(436, 510, 134, 35);
		panel3.add(btnNewButton_3_2);
		
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("EDIT", null, panel2, null);
		panel2.setBackground(new Color(0,0,0,100));
		panel2.setLayout(null);
		
		JLabel lblEditSupplier = new JLabel("EDIT SUPPLIER");
		lblEditSupplier.setForeground(new Color(254, 250, 203));
		lblEditSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditSupplier.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEditSupplier.setBounds(153, 6, 689, 64);
		panel2.add(lblEditSupplier);
		
		JLabel name = new JLabel("NAME");
		name.setForeground(new Color(254, 250, 203));
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		name.setFont(new Font("Sitka Text", Font.BOLD, 14));
		name.setBounds(96, 235, 131, 39);
		panel2.add(name);
		
		supname = new JTextField();
		supname.setColumns(10);
		supname.setBounds(265, 229, 201, 46);
		panel2.add(supname);
		
		JLabel supMobilenumber = new JLabel("MOBILE NUMBER");
		supMobilenumber.setForeground(new Color(254, 250, 203));
		supMobilenumber.setHorizontalAlignment(SwingConstants.RIGHT);
		supMobilenumber.setFont(new Font("Sitka Text", Font.BOLD, 14));
		supMobilenumber.setBounds(96, 325, 131, 39);
		panel2.add(supMobilenumber);
		
		supmobile = new JTextField();
		supmobile.setColumns(10);
		supmobile.setBounds(265, 319, 201, 46);
		panel2.add(supmobile);
		
		JLabel supMedicineName_1 = new JLabel("MEDICINE NAME");
		supMedicineName_1.setForeground(new Color(254, 250, 203));
		supMedicineName_1.setHorizontalAlignment(SwingConstants.RIGHT);
		supMedicineName_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		supMedicineName_1.setBounds(618, 235, 131, 39);
		panel2.add(supMedicineName_1);
		
		supmedname = new JTextField();
		supmedname.setColumns(10);
		supmedname.setBounds(789, 229, 201, 46);
		panel2.add(supmedname);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setForeground(new Color(254, 250, 203));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblEmail.setBounds(96, 405, 131, 39);
		panel2.add(lblEmail);
		
		supemail = new JTextField();
		supemail.setColumns(10);
		supemail.setBounds(265, 399, 201, 46);
		panel2.add(supemail);
		
		JComboBox supunamesearchtext = new JComboBox();
		supunamesearchtext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		supunamesearchtext.setModel(new DefaultComboBoxModel(new String[] {"select"}));
		supunamesearchtext.setBounds(333, 97, 260, 41);
		try 
		{	
			Connection con = jdbc.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct SupplierUserName from suppliers");
			while(rs.next())
			{
				supunamesearchtext.addItem(rs.getString(1));
			}
		} 
		catch (Exception e) {
			
		}
		panel2.add(supunamesearchtext);
		
		JButton searchbtn = new JButton("Search");
		searchbtn.setBackground(new Color(254, 250, 203));
		
		JButton btnNewButton_2 = new JButton("UPDATE");
		btnNewButton_2.setBackground(new Color(254, 250, 203));
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\SAVEICON.png"));
		
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dt = (DefaultTableModel)table.getModel();
				dt.setRowCount(0);
				if(!supunamesearchtext.getSelectedItem().toString().equals("select"))
				{
					int supplierexist = 0;
					String supuname = supunamesearchtext.getSelectedItem().toString();
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
							
							Statement st1 = con.createStatement();
							ResultSet rs1 = st1.executeQuery("select * from medicine where SupplierUserName='"+supuname+"';");
							while(rs1.next())
							{
								dt.addRow(new Object[] {rs1.getString(1),rs1.getString(2),rs1.getString(3)});
							}
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
				
				DefaultTableModel dt = (DefaultTableModel)table.getModel();
				
				String supuname = supunamesearchtext.getSelectedItem().toString();
				if(!suppname.equals("") && !suppmob.equals("") && !suppemail.equals(""))
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
						
						 ResultSet rs = st.executeQuery("select * from suppliers where (mobileNumber = '"+suppmob+"') and (SupplierUserName <> '"+supuname+"');");
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
						st.executeUpdate("update suppliers set name = '"+suppname+"',mobileNumber = '"+suppmob+"',email = '"+suppemail+"' where SupplierUserName = '"+supuname+"';");
						JOptionPane.showMessageDialog(null, "Supplier has been updated successfully");
						supname.setText("");
						supmobile.setText("");
						supemail.setText("");
						
						dt.setRowCount(0);
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
		btnNewButton_2.setBounds(439, 561, 149, 39);
		panel2.add(btnNewButton_2);
		
		JButton btnBack_2 = new JButton("BACK");
		btnBack_2.setBackground(new Color(254, 250, 203));
		btnBack_2.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
				setVisible(false);
			}
		});
		btnBack_2.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack_2.setBounds(850, 24, 129, 49);
		panel2.add(btnBack_2);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setForeground(new Color(254, 250, 203));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(181, 101, 142, 30);
		panel2.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(618, 300, 362, 155);
		panel2.add(scrollPane_1);
		
		table = new JTable();
		
		scrollPane_1.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Medicine", "Medicine ID", "Quantity"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnNewButton_4 = new JButton("DELETE");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dt = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				int resp = JOptionPane.showConfirmDialog(null, "Do you want to delete");
				if(resp==JOptionPane.YES_OPTION)
				{
				if(Integer.valueOf(dt.getValueAt(row, 2).toString())<=0)
				{
					try
					{
						Connection con = jdbc.getCon();
						Statement st = con.createStatement();
						int flag=0;
						
						st.executeUpdate("delete from suppliers where MedicineID='"+table.getValueAt(row, 1)+"';");
						st.executeUpdate("delete from medicine where MedicineID='"+table.getValueAt(row, 1)+"';");
						JOptionPane.showMessageDialog(null, "Medicine deleted successfully");
						dt.removeRow(row);
						
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Medicine is in stock. Cannot be deleted");
				}
				}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_4.setBounds(745, 486, 119, 30);
		panel2.add(btnNewButton_4);
		table.getColumnModel().getColumn(0).setPreferredWidth(105);
		table.getColumnModel().getColumn(0).setMinWidth(105);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(supplierinfo.class.getResource("/images/Reporting on our Progress and ilestones (4).jpg")));
		lblNewLabel_1.setBounds(0, 0, 1283, 650);
		contentPane.add(lblNewLabel_1);
		
	}
}
