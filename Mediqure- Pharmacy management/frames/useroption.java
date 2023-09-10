package frames;
import java.sql.*;
import java.util.Vector;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.text.DateFormatter;

import db.jdbc;

import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class useroption extends JFrame {
	public String mailpattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
	public String mobilePattern = "^[0-9]*$";
	public String pwdpattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{6,20}$";
	public int checkuname;

	DefaultTableModel model;
	private JPanel contentPane;
	private JTextField namegiven;
	private JTextField emailgiven;
	private JTextField unamegiven;
	private JPasswordField pwdgiven;
	private JTextField mobgiven;
	private JTable table;
	private JTextField dt1nametext;
	private JTextField dt1mobtext;
	private JTextField dt1unametext;
	private JTextField dt1emailtext;
	private JTextField searchtxt;
	private JPasswordField repwd;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					useroption frame = new useroption();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
//		Vector v=new Vector();
//		v.add(1);
//		v.add(name);
//		v.add(mobile);
//		v.add(email);
//		v.add(uname);
//		dt.addRow(v);
//		
//	}

	/**
	 * Create the frame.
	 */
	
	public useroption(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1317, 724);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(0,0,0,100));
		tabbedPane.setOpaque(false);
		tabbedPane.setBounds(258, -22, 1011, 677);
		contentPane.add(tabbedPane);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0,20));
        panel.setOpaque(false);
        tabbedPane.addTab("ADD", null, panel, null);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ADD USERS");
        
        lblNewLabel.setForeground(new Color(233, 228, 150));
        lblNewLabel.setBackground(new Color(240, 240, 240));
        lblNewLabel.setBounds(157, 24, 689, 64);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblNewLabel);
        
        JLabel roleselected = new JLabel("ROLE");
        roleselected.setForeground(new Color(233, 228, 150));
        roleselected.setBounds(29, 111, 83, 25);
        roleselected.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(roleselected);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(862, 48, -854, 20);
        panel.add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(870, 86, -879, 2);
        panel.add(separator_1);
        
        JComboBox<String> rolecomboBox = new JComboBox<String>();
        rolecomboBox.setBounds(29, 144, 378, 37);
        rolecomboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rolecomboBox.setModel(new DefaultComboBoxModel(new String[] {"select", "PHARMACIST"}));
        panel.add(rolecomboBox);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(502, 553, -11, -489);
        panel.add(separator_2);
        
        JLabel namee = new JLabel("NAME");
        namee.setForeground(new Color(233, 228, 150));
        namee.setBounds(29, 211, 83, 25);
        namee.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(namee);
        
        namegiven = new JTextField();
        namegiven.setBounds(29, 242, 378, 37);
        panel.add(namegiven);
        namegiven.setColumns(10);
        
        JLabel mobilegiven = new JLabel("MOBILE NUMBER");
        mobilegiven.setForeground(new Color(233, 228, 150));
        mobilegiven.setBounds(29, 310, 149, 25);
        mobilegiven.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(mobilegiven);
        
        JLabel email_1 = new JLabel("EMAIL");
        email_1.setForeground(new Color(233, 228, 150));
        email_1.setBounds(29, 408, 149, 25);
        email_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(email_1);
        
        emailgiven = new JTextField();
        emailgiven.setBounds(29, 444, 378, 37);
        emailgiven.setColumns(10);
        panel.add(emailgiven);
        
        JLabel usernamevalid = new JLabel("");
        
        usernamevalid.setBounds(875, 127, 121, 69);
        usernamevalid.setHorizontalAlignment(SwingConstants.LEFT);
        usernamevalid.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel.add(usernamevalid);
        
        JLabel unamee = new JLabel("USERNAME");
        unamee.setForeground(new Color(233, 228, 150));
        unamee.setBounds(482, 111, 149, 25);
        unamee.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(unamee);
        
        unamegiven = new JTextField();
        unamegiven.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		String uname = unamegiven.getText();
        		if(!uname.equals(""))
        		{
        			usernamevalid.setVisible(true);
        			usernamevalid.setIcon(new ImageIcon("src\\images\\yesicon.png"));
        			usernamevalid.setText("");
        			checkuname = 0;
        			
        			try
        			{
        				Connection con = jdbc.getCon();
        				Statement st = con.createStatement();
        				ResultSet rs = st.executeQuery("select * from pharma where username = '"+uname+"'");
        				while(rs.next())
        				{
        					checkuname = 1;
        					usernamevalid.setIcon(new ImageIcon("src\\images\\wrongicon.png"));
        				}
        				
        			}
        			catch(Exception e1)
        			{
        				JOptionPane.showMessageDialog(null, e1);
        			}
        		}
        		else
        		{
        			usernamevalid.setVisible(false);
        		}
        	}
        });
        unamegiven.setBounds(482, 146, 378, 37);
        unamegiven.setColumns(10);
        panel.add(unamegiven);
        
        JLabel pwd_1 = new JLabel("PASSWORD");
        pwd_1.setForeground(new Color(233, 228, 150));
        pwd_1.setBounds(482, 211, 149, 25);
        pwd_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(pwd_1);
        
        pwdgiven = new JPasswordField();
        pwdgiven.setBounds(482, 242, 378, 37);
        panel.add(pwdgiven);
        
        mobgiven = new JTextField();
        mobgiven.setBounds(29, 344, 378, 37);
        mobgiven.setColumns(10);
        panel.add(mobgiven);
        
        JButton btnBack = new JButton("BACK");
        btnBack.setIcon(new ImageIcon(useroption.class.getResource("/images/backicon.png")));
        btnBack.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnBack.setBackground(new Color(208, 236, 231));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnBack.setBackground(new Color(222,223,162));
        	}
        });
        btnBack.setForeground(new Color(0, 0, 0));
        btnBack.setBackground(new Color(222,223,162));
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		new adminoptions(username).setVisible(true);
        	}
        });
        btnBack.setBounds(773, 18, 136, 50);
        panel.add(btnBack);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        JButton btnClear = new JButton("CLEAR");
        btnClear.setIcon(new ImageIcon(useroption.class.getResource("/images/eraser.png")));
        btnClear.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnClear.setBackground(new Color(208, 236, 231));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnClear.setBackground(new Color(222,223,162));
        	}
        });
        btnClear.setBackground(new Color(222, 223, 162));
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rolecomboBox.setSelectedItem("select");
        		namegiven.setText("");
        		mobgiven.setText("");
        		emailgiven.setText("");
        		unamegiven.setText("");
        		pwdgiven.setText("");
        	}
        });
        
        
        JButton btnNewButton = new JButton("SAVE");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnNewButton.setBackground(new Color(208, 236, 231));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnNewButton.setBackground(new Color(222,223,162));
        	}
        });
        btnNewButton.setBackground(new Color(222, 223, 162));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String userRole = (String) rolecomboBox.getSelectedItem();
        		String name = namegiven.getText().replaceAll("\\s+","");
        		String mobile = mobgiven.getText().replaceAll("\\s+","");
        		String email = emailgiven.getText().replaceAll("\\s+","");
        		String uname = unamegiven.getText().replaceAll("\\s+","");
        		String pwd = pwdgiven.getText().replaceAll("\\s+","");
        		if(userRole.equals("select"))
        		{
        			JOptionPane.showMessageDialog(null, "Select a userRole");
        		}
        		else if(name.equals("") || mobile.equals("") || email.equals("") || uname.equals("") || pwd.equals(""))
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
        		else if(checkuname==1)
        		{
        			JOptionPane.showMessageDialog(null, "Username already exists");
        		}
        		else if(!pwd.matches(pwdpattern))
        		{
        			JOptionPane.showMessageDialog(null, "Password must contain 6 to 20 characters with atleast 1 uppercase, 1 lowercase, 1 number and a special character");
        		}
        		else if(!pwdgiven.getText().equals(repwd.getText()))
        		{
        			JOptionPane.showMessageDialog(null, "Password doesn't match");
        		}
        		else
        		{
        				  
        				try
        				{
        					
        					Connection con = jdbc.getCon();
        					Statement st = con.createStatement();
        					
        					String query="insert into pharma (name,mobileNumber,email,username,password) values ('"+name+"','"+mobile+"','"+email+"','"+uname+"','"+pwd+"');";
        					st.executeUpdate(query);
        					JOptionPane.showMessageDialog(null, "User added successfully");
//							addtable(name,mobile,email,uname);
//			                namegiven.setText("");
//							mobgiven.setText("");
//							emailgiven.setText("");
//							unamegiven.setText("");
//							pwdgiven.setText("");
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
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setBounds(308, 526, 136, 50);
        panel.add(btnNewButton);
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClear.setBounds(540, 526, 136, 50);
        panel.add(btnClear);
        
        JLabel pwd_1_1 = new JLabel("RETYPE PASSWORD");
        pwd_1_1.setForeground(new Color(233, 228, 150));
        pwd_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        pwd_1_1.setBounds(482, 313, 180, 25);
        panel.add(pwd_1_1);
        
        repwd = new JPasswordField();
        repwd.setBounds(482, 344, 378, 37);
        panel.add(repwd);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("Show password");
        chckbxNewCheckBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (chckbxNewCheckBox.isSelected()) 
				{
					pwdgiven.setEchoChar((char)0);
					repwd.setEchoChar((char)0);
				} 
				else 
				{
					pwdgiven.setEchoChar('*');
					repwd.setEchoChar('*');
				}
        	}
        });
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chckbxNewCheckBox.setBounds(875, 242, 121, 37);
        panel.add(chckbxNewCheckBox);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0,0,0,20));
        panel_1.setOpaque(false);
        panel_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dt1nametext.setText("");
        		dt1mobtext.setText("");
        		dt1emailtext.setText("");
        		dt1unametext.setText("");
        	}
        });
        tabbedPane.addTab("VIEW", null, panel_1, null);
        panel_1.setLayout(null);
        
        JLabel lblViewUsers = new JLabel("VIEW AND UPDATE USERS");
        lblViewUsers.setForeground(new Color(222, 220, 133));
        lblViewUsers.setBounds(140, 11, 661, 39);
        lblViewUsers.setHorizontalAlignment(SwingConstants.CENTER);
        lblViewUsers.setFont(new Font("Tahoma", Font.BOLD, 28));
        panel_1.add(lblViewUsers);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        scrollPane.setBounds(66, 118, 870, 250);
        panel_1.add(scrollPane);
        
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		DefaultTableModel dt1 = (DefaultTableModel)table.getModel();
        		int row = table.convertRowIndexToModel(table.getSelectedRow());
        		String dt1name = dt1.getValueAt(row, 1).toString();
        		String dt1mob = dt1.getValueAt(row, 2).toString();
        		String dt1email = dt1.getValueAt(row, 3).toString();
        		String dt1uname = dt1.getValueAt(row, 4).toString();
        		
        		dt1nametext.setText(dt1name);
        		dt1mobtext.setText(dt1mob);
        		dt1emailtext.setText(dt1email);
        		dt1unametext.setText(dt1uname);
        	}
        });
        table.setColumnSelectionAllowed(true);
        table.setShowGrid(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setToolTipText("");
        table.setShowHorizontalLines(false);
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"S.NO.", "NAME", "MOBILE NO.", "EMAIL", "USERNAME"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        table.getColumnModel().getColumn(1).setPreferredWidth(105);
        table.getColumnModel().getColumn(1).setMinWidth(105);
        table.getColumnModel().getColumn(4).setPreferredWidth(105);
        table.getColumnModel().getColumn(4).setMinWidth(105);
        //		table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                
                JLabel lblNewLabel_1_4 = new JLabel("NAME");
                lblNewLabel_1_4.setForeground(new Color(222, 220, 133));
                lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblNewLabel_1_4.setBounds(50, 405, 130, 39);
                panel_1.add(lblNewLabel_1_4);
                
                dt1nametext = new JTextField();
                dt1nametext.setColumns(10);
                dt1nametext.setBounds(253, 400, 237, 52);
                panel_1.add(dt1nametext);
                
                JLabel lblNewLabel_1_1_1 = new JLabel("MOBILE NUMBER");
                lblNewLabel_1_1_1.setForeground(new Color(222, 220, 133));
                lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblNewLabel_1_1_1.setBounds(50, 482, 130, 39);
                panel_1.add(lblNewLabel_1_1_1);
                
                dt1mobtext = new JTextField();
                dt1mobtext.setColumns(10);
                dt1mobtext.setBounds(253, 482, 237, 52);
                panel_1.add(dt1mobtext);
                
                JLabel lblNewLabel_1_2_1 = new JLabel("USERNAME");
                lblNewLabel_1_2_1.setForeground(new Color(222, 220, 133));
                lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblNewLabel_1_2_1.setBounds(572, 415, 130, 39);
                panel_1.add(lblNewLabel_1_2_1);
                
                dt1unametext = new JTextField();
                dt1unametext.setEnabled(false);
                dt1unametext.setFont(new Font("Tahoma", Font.BOLD, 13));
                dt1unametext.setHorizontalAlignment(SwingConstants.CENTER);
                dt1unametext.setText("cannot be changed");
                dt1unametext.setColumns(10);
                dt1unametext.setBounds(736, 399, 237, 52);
                panel_1.add(dt1unametext);
                
                JLabel lblNewLabel_1_3_1 = new JLabel("EMAIL");
                lblNewLabel_1_3_1.setForeground(new Color(222, 220, 133));
                lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblNewLabel_1_3_1.setBounds(572, 482, 130, 39);
                panel_1.add(lblNewLabel_1_3_1);
                
                dt1emailtext = new JTextField();
                dt1emailtext.setColumns(10);
                dt1emailtext.setBounds(736, 477, 237, 52);
                panel_1.add(dt1emailtext);
                
                JButton btnNewButton_2_2 = new JButton("UPDATE");
                btnNewButton_2_2.setBackground(new Color(222, 220, 133));
                btnNewButton_2_2.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseEntered(MouseEvent e) {
                		btnNewButton_2_2.setBackground(new Color(208, 236, 231));
                	}
                	@Override
                	public void mouseExited(MouseEvent e) {
                		btnNewButton_2_2.setBackground(new Color(222, 223, 162));
                	}
                });
                btnNewButton_2_2.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\SAVEICON.png"));
                btnNewButton_2_2.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		DefaultTableModel dt1 = (DefaultTableModel) table.getModel();
				if(table.getSelectedRowCount()==1)
				{
					try
					{
						String name = dt1nametext.getText();
						String mob = dt1mobtext.getText();
						String email = dt1emailtext.getText();
						Connection con = jdbc.getCon();
						Statement st = con.createStatement();
						if(!mob.matches(mobilePattern) || mob.length() != 10)
						{
							JOptionPane.showMessageDialog(null, "Invalid mobile number");
						}
						else if(!email.matches(mailpattern))
						{
							JOptionPane.showMessageDialog(null, "Invalid email");
						}
						else
						{
						int rs = st.executeUpdate("update pharma set name = '"+dt1nametext.getText()+"', mobileNumber='"+dt1mobtext.getText()+"',email='"+dt1emailtext.getText()+"' where username='"+dt1unametext.getText()+"'");
						
						dt1.setValueAt(name, table.getSelectedRow(), 1);
						dt1.setValueAt(mob, table.getSelectedRow(), 2);
						dt1.setValueAt(email, table.getSelectedRow(), 3);
						
						JOptionPane.showMessageDialog(null, "Updated successfully");
						dt1unametext.setText("");
                				dt1nametext.setText("");
                				dt1mobtext.setText("");
                				dt1emailtext.setText("");
						}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				else if(table.getRowCount()==0)
				{
					JOptionPane.showMessageDialog(null, "Table is empty");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Select 1 row to update");
				}
                	}
                });
                btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
                btnNewButton_2_2.setBounds(372, 545, 146, 39);
                panel_1.add(btnNewButton_2_2);
                
                JButton btnNewButton_2_1_1 = new JButton("DELETE");
                btnNewButton_2_1_1.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseEntered(MouseEvent e) {
                		btnNewButton_2_1_1.setBackground(new Color(208, 236, 231));
                	}
                	@Override
                	public void mouseExited(MouseEvent e) {
                		btnNewButton_2_1_1.setBackground(new Color(222,223,162));
                	}
                });
                btnNewButton_2_1_1.setBackground(new Color(222, 220, 133));
                btnNewButton_2_1_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\wrongicon.png"));
                btnNewButton_2_1_1.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		DefaultTableModel dt1 = (DefaultTableModel) table.getModel();
                		if(!dt1unametext.getText().equals(username) && !dt1unametext.getText().equals("admin"))
                		{
                		try
                		{
                			Connection con = jdbc.getCon();
                			Statement st = con.createStatement();
                			int rs = st.executeUpdate("delete from pharma where username='"+dt1unametext.getText()+"'");
                			
                			if(table.getSelectedRowCount()>0)
                			{
                				dt1.removeRow(table.getSelectedRow());
                				JOptionPane.showMessageDialog(null, "User deleted successfully");
                				dt1unametext.setText("");
                				dt1nametext.setText("");
                				dt1mobtext.setText("");
                				dt1emailtext.setText("");
                			}
                			else
                			{
                				JOptionPane.showMessageDialog(null, "Select a data to delete");
                			}
                		}
                		catch(Exception e1)
                		{
                			JOptionPane.showMessageDialog(null, e1);
                		}
                		}
                		else if(dt1unametext.getText().equals("admin"))
                		{
                			JOptionPane.showMessageDialog(null, "Admin cannot be removed");
                		}
                		else
                		{
                			JOptionPane.showMessageDialog(null, "You cannot remove yourself");
                		}
                	}
                });
                btnNewButton_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
                btnNewButton_2_1_1.setBounds(556, 545, 146, 39);
                panel_1.add(btnNewButton_2_1_1);
                
                JButton btnNewButton_2 = new JButton("BACK");
                btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
                btnNewButton_2.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseEntered(MouseEvent e) {
                		btnNewButton_2.setBackground(new Color(208, 236, 231));
                	}
                	@Override
                	public void mouseExited(MouseEvent e) {
                		btnNewButton_2.setBackground(new Color(222,223,162));
                	}
                });
                btnNewButton_2.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		setVisible(false);
                		new adminoptions(username).setVisible(true);
                	}
                });
                btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
                btnNewButton_2.setBounds(797, 30, 136, 52);
                btnNewButton_2.setBackground(new Color(222,223,162));
                panel_1.add(btnNewButton_2);
                
                searchtxt = new JTextField();
                searchtxt.addKeyListener(new KeyAdapter() {
                	@Override
                	public void keyReleased(KeyEvent e) {
                		String str = searchtxt.getText();
                		DefaultTableModel obj = (DefaultTableModel)table.getModel();
    			TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
    			table.setRowSorter(obj1);
    			obj1.setRowFilter(RowFilter.regexFilter(str));
                	}
                });
                searchtxt.setBounds(279, 61, 289, 39);
                panel_1.add(searchtxt);
                searchtxt.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("ADD USER");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\add-post.png"));
		btnNewButton_1.setBackground(new Color(254, 250, 203));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setFont(new Font("SimSun", Font.BOLD, 15));
		btnNewButton_1.setBounds(22, 183, 191, 64);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("VIEW AND EDIT USERS");
		btnNewButton_1_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\updated.png"));
		btnNewButton_1_1.setBackground(new Color(254, 250, 203));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try
					{ int sno = 1;
						DefaultTableModel dt = (DefaultTableModel) table.getModel();
						dt.setRowCount(0);
						Connection con = jdbc.getCon();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from pharma where username <> 'admin';");
						while(rs.next())
						{
						dt.addRow(new Object[] {sno, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
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
		btnNewButton_1_1.setFont(new Font("SimSun", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(22, 378, 226, 89);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(useroption.class.getResource("/images/Reporting on our Progress and ilestones (1).jpg")));
		lblNewLabel_2.setBounds(0, 0, 1283, 654);
		contentPane.add(lblNewLabel_2);
		
		
	}
}
