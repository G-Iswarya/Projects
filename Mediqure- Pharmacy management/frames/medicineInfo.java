package frames;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import db.jdbc;

import javax.swing.border.MatteBorder;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

public class medicineInfo extends JFrame {
	public String mobilePattern = "^[0-9]*$";
	private JPanel contentPane;
	private JTextField medname;
	private JTable table;
	private JTextField medID;
	private JTextField costperunit;
	private JTextField quantityfield;
	private JTextField costfield;
	private JTextField supnamefield;
	private JTextField supunamefield;
	private String username;
	int  medmax;
	private JTextField supnametxt;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					medicineInfo frame = new medicineInfo();
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
	public medicineInfo(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1289, 689);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255,0));
		tabbedPane.setOpaque(false);
		tabbedPane.setBounds(198, -22, 1075, 674);
		contentPane.add(tabbedPane);
		
		JButton updatebtn = new JButton("UPDATE");
		updatebtn.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\updated.png"));
		updatebtn.setBackground(new Color(254, 250, 203));
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		updatebtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updatebtn.setBounds(10, 464, 156, 60);
		contentPane.add(updatebtn);
		
		
		JButton viewbtn = new JButton("VIEW");
		viewbtn.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\viewicon.png"));
		viewbtn.setBackground(new Color(254, 250, 203));
		viewbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		viewbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		viewbtn.setBounds(10, 304, 156, 60);
		contentPane.add(viewbtn);
		
		
		JButton addbtn = new JButton("ADD");
		addbtn.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\add-post.png"));
		addbtn.setBackground(new Color(254, 250, 203));
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		addbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addbtn.setBounds(10, 160, 156, 60);
		contentPane.add(addbtn);
		
		if(!username.equals("admin"))
		{
			addbtn.setEnabled(false);
			updatebtn.setEnabled(false);
		}
		
		JComboBox supname = new JComboBox();
		supname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(supname.getSelectedItem().toString().equals("select"))
					{
						supnametxt.setText("");
					}
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from suppliers where SupplierUserName='"+supname.getSelectedItem().toString()+"';");
					while(rs.next())
					{
						supnametxt.setText(rs.getString(1));
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		supname.setModel(new DefaultComboBoxModel(new String[] {"select"}));
		supname.setBounds(62, 295, 258, 35);
		
		try
		{
		Connection con = jdbc.getCon();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select distinct SupplierUserName from suppliers");
		while(rs.next())
		{
			supname.addItem(rs.getString(1));
		}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}
		
		
		JPanel viewpanel = new JPanel();
		viewpanel.setBackground(new Color(0,0,0,100));
		tabbedPane.addTab("VIEW", null, viewpanel, null);
		viewpanel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Order by medicine");
		btnNewButton_1.setBackground(new Color(254, 250, 203));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{ int sno = 1;
					DefaultTableModel dt = (DefaultTableModel) table.getModel();
					dt.setRowCount(0);
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from medicine order by MedicineName");
					while(rs.next())
					{
					dt.addRow(new Object[] {sno, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
					sno++;
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("Order by supplier");
		btnNewButton_1_1.setBackground(new Color(254, 250, 203));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{ int sno = 1;
					DefaultTableModel dt = (DefaultTableModel) table.getModel();
					dt.setRowCount(0);
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from medicine order by SupplierName");
					while(rs.next())
					{
					dt.addRow(new Object[] {sno, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
					sno++;
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		
		JButton btnBack_1_1 = new JButton("BACK");
		btnBack_1_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnBack_1_1.setBackground(new Color(254, 250, 203));
		btnBack_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!username.equals("admin"))
				{
					new pharmaoptions(username).setVisible(true);
					setVisible(false);
				}
				else
				{
					new adminoptions(username).setVisible(true);
					setVisible(false);
				}
				
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("VIEW MEDICINE");
		lblNewLabel_5.setForeground(new Color(254, 250, 203));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_5.setBounds(302, 10, 422, 62);
		viewpanel.add(lblNewLabel_5);
		btnBack_1_1.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack_1_1.setBounds(846, 11, 129, 49);
		viewpanel.add(btnBack_1_1);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(583, 571, 213, 43);
		viewpanel.add(btnNewButton_1_1);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(184, 571, 213, 43);
		viewpanel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(81, 83, 790, 395);
		viewpanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.NO.", "MEDICINE NAME", "MEDICINE ID", "QUANTITY", "COST PER TABLET", "SUPPLIER NAME", "SUPPLIER USER NAME"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(1).setMinWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setMinWidth(105);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setMinWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setMinWidth(105);
		table.getColumnModel().getColumn(5).setPreferredWidth(105);
		table.getColumnModel().getColumn(5).setMinWidth(105);
		table.getColumnModel().getColumn(6).setPreferredWidth(145);
		table.getColumnModel().getColumn(6).setMinWidth(145);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1_2 = new JButton("DELETE");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rep = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
				if(rep==JOptionPane.YES_OPTION)
				{
				DefaultTableModel dt = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(Integer.parseInt(table.getValueAt(row, 3).toString())<=0)
				{
				try
				{
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					st.executeUpdate("delete from medicine where MedicineID='"+table.getValueAt(row, 2)+"';");
					st.executeUpdate("delete from suppliers where MedicineID='"+table.getValueAt(row, 2)+"';");
					dt.removeRow(row);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
				else
				{
					JOptionPane.showMessageDialog(null, "Cannot delete. Medicines are not sold");
				}
				}
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_2.setBackground(new Color(254, 250, 203));
		btnNewButton_1_2.setBounds(411, 502, 146, 43);
		viewpanel.add(btnNewButton_1_2);
		DefaultTableModel dt = (DefaultTableModel) table.getModel();
		
		try
		{ int sno = 1;
			dt.setRowCount(0);
			Connection con = jdbc.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from medicine");
			while(rs.next())
			{
			dt.addRow(new Object[] {sno, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
			sno++;
			}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("ADD", null, panel, null);
		panel.setLayout(null);
		panel.setBackground(new Color(0,0,0,100));
		panel.add(supname);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(254, 250, 203));
		btnBack.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
				setVisible(false);
			}
		});
	
		
		JLabel lblCostPerUnit = new JLabel("COST PER UNIT");
		lblCostPerUnit.setForeground(new Color(254, 250, 203));
		lblCostPerUnit.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblCostPerUnit.setBounds(62, 376, 221, 35);
		panel.add(lblCostPerUnit);
		
		costperunit = new JTextField();
		costperunit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		costperunit.setColumns(10);
		costperunit.setBounds(62, 422, 258, 35);
		panel.add(costperunit);
		
		JLabel lblMedicineid = new JLabel("MEDICINE ID");
		lblMedicineid.setForeground(new Color(254, 250, 203));
		lblMedicineid.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblMedicineid.setBounds(507, 247, 221, 35);
		panel.add(lblMedicineid);
		
		JLabel validmedID = new JLabel("");
		validmedID.setBounds(775, 295, 129, 35);
		panel.add(validmedID);
		
		medID = new JTextField();
		medID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int checkuname;
				String medid = medID.getText();
				if(!medID.equals(""))
				{
					validmedID.setVisible(true);
					validmedID.setIcon(new ImageIcon("src\\images\\yesicon.png"));
        			validmedID.setText("");
        			checkuname = 0;
        			
        			try
        			{
        				Connection con = jdbc.getCon();
        				Statement st = con.createStatement();
        				ResultSet rs = st.executeQuery("select * from medicine where MedicineID = '"+medid+"'");
        				while(rs.next())
        				{
        					checkuname = 1;
        					validmedID.setIcon(new ImageIcon("src\\images\\wrongicon.png"));
        					validmedID.setText("");
        				}
        				
        			}
        			catch(Exception e1)
        			{
        				JOptionPane.showMessageDialog(null, e1);
        			}
				}
				else
				{
					validmedID.setVisible(false);
				}
			}
		});
		medID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		medID.setColumns(10);
		medID.setBounds(507, 293, 258, 35);
		panel.add(medID);
		btnBack.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack.setBounds(905, 35, 129, 49);
		panel.add(btnBack);
		
		JLabel lblMedicineName = new JLabel("MEDICINE NAME");
		lblMedicineName.setForeground(new Color(254, 250, 203));
		lblMedicineName.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblMedicineName.setBounds(62, 105, 221, 35);
		panel.add(lblMedicineName);
		
		medname = new JTextField();
		medname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!medname.getText().equals("")) {
					int flag=0;
				try
				{
					 Connection con = jdbc.getCon();
					 Statement st = con.createStatement();
					 ResultSet rs = st.executeQuery("select * from suppliers where MedicineName = '"+medname.getText()+"'");
					 while(rs.next())
					 {
						 flag=1;
						 medID.setText(rs.getString("MedicineID"));
						 medID.setEditable(false);
					 } 
					 if(flag==0)
					 {
						 medID.setText("");
					 }
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				}
				else
				{
					medID.setText("");
				}
			}
		});
		medname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		medname.setColumns(10);
		medname.setBounds(62, 151, 258, 35);
		panel.add(medname);
		
		JLabel lblNoOfMedicines = new JLabel("COUNT");
		lblNoOfMedicines.setForeground(new Color(254, 250, 203));
		lblNoOfMedicines.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblNoOfMedicines.setBounds(507, 105, 221, 35);
		panel.add(lblNoOfMedicines);
		
		JSpinner count = new JSpinner();
		count.setModel(new SpinnerNumberModel(0, 0, 250, 1));
		count.setFont(new Font("Tahoma", Font.BOLD, 14));
		count.setBounds(507, 151, 258, 35);
		panel.add(count);
		
		JLabel lblSupplier = new JLabel("SUPPLIER USERNAME");
		lblSupplier.setForeground(new Color(254, 250, 203));
		lblSupplier.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblSupplier.setBounds(62, 247, 221, 35);
		panel.add(lblSupplier);
		
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String med_name = medname.getText().replaceAll("\\s+","").toUpperCase();
				String suppname = supname.getSelectedItem().toString();
				try
				{
					int medcost = Integer.parseInt(costperunit.getText().replaceAll("\\s+",""));
					int med_ID = Integer.parseInt(medID.getText().replaceAll("\\s+",""));
					int medcount = Integer.parseInt(count.getValue().toString());
					
					if(med_name.equals("") || suppname.equals("select") || medcount==0 || medcost==0 || medID.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "All fields must be filled");
					}
					else
					{
						try
						{
							 Connection con = jdbc.getCon();
							 Statement st = con.createStatement();
							 ResultSet rs = st.executeQuery("select * from suppliers where MedicineName = '"+med_name+"'");
							 while(rs.next())
							 {
								 JOptionPane.showMessageDialog(null, "This medicine already exists");
							 } 
							 Statement st3 = con.createStatement();
							ResultSet rs1 = st3.executeQuery("select * from suppliers where SupplierUserName='"+suppname+"';");
							Statement st2 = con.createStatement();
							while(rs1.next())
							{
								st2.executeUpdate("insert into suppliers values('"+rs1.getString(1)+"','"+rs1.getString(2)+"','"+rs1.getString(3)+"','"+med_name+"','"+med_ID+"','"+rs1.getString(6)+"')");
							}
							Statement st1 = con.createStatement();
							st1.executeUpdate("insert into medicine (MedicineName,MedicineID,Quantity,CostPerUnit,SupplierName,SupplierUserName)values('"+med_name+"','"+med_ID+"','"+medcount+"','"+medcost+"','"+supnametxt.getText()+"','"+suppname+"')");
							
							JOptionPane.showMessageDialog(null, "Medicine added successfully");
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, e1);
						}
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Enter the correct values");
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\SAVEICON.png"));
		btnNewButton.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnNewButton.setBounds(383, 493, 129, 49);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ADD MEDICINES");
		lblNewLabel_1.setForeground(new Color(254, 250, 203));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(288, 19, 560, 60);
		panel.add(lblNewLabel_1);
		
		supnametxt = new JTextField();
		supnametxt.setEditable(false);
		supnametxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		supnametxt.setColumns(10);
		supnametxt.setBounds(507, 422, 258, 35);
		panel.add(supnametxt);
		
		JLabel lblSupplierName = new JLabel("SUPPLIER NAME");
		lblSupplierName.setForeground(new Color(254, 250, 203));
		lblSupplierName.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblSupplierName.setBounds(507, 376, 221, 35);
		panel.add(lblSupplierName);
		
		
		
		JPanel editpanel = new JPanel();
		editpanel.setBackground(new Color(255, 255, 255));
		editpanel.setBackground(new Color(0,0,0,100));
		tabbedPane.addTab("EDIT", null, editpanel, null);
		editpanel.setLayout(null);
		
		JLabel lblEditMedicine = new JLabel("UPDATE MEDICINE");
		lblEditMedicine.setForeground(new Color(254, 250, 203));
		lblEditMedicine.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditMedicine.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEditMedicine.setBounds(158, 68, 689, 64);
		editpanel.add(lblEditMedicine);
		
		
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setForeground(new Color(254, 250, 203));
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblQuantity.setBounds(81, 279, 131, 39);
		editpanel.add(lblQuantity);
		
		quantityfield = new JTextField();
		quantityfield.setEditable(false);
		quantityfield.setColumns(10);
		quantityfield.setBounds(288, 273, 201, 46);
		editpanel.add(quantityfield);
		
		JLabel lblMobilenumber = new JLabel("ADD");
		lblMobilenumber.setForeground(new Color(254, 250, 203));
		lblMobilenumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobilenumber.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblMobilenumber.setBounds(327, 355, 60, 39);
		editpanel.add(lblMobilenumber);
		
		JLabel lblCostPerUnit_1 = new JLabel("COST PER UNIT");
		lblCostPerUnit_1.setForeground(new Color(254, 250, 203));
		lblCostPerUnit_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostPerUnit_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblCostPerUnit_1.setBounds(105, 449, 131, 39);
		editpanel.add(lblCostPerUnit_1);
		
		costfield = new JTextField();
		costfield.setColumns(10);
		costfield.setBounds(288, 443, 201, 46);
		editpanel.add(costfield);
		
		JLabel supMedicineName_1 = new JLabel("SUPPLIER NAME");
		supMedicineName_1.setForeground(new Color(254, 250, 203));
		supMedicineName_1.setHorizontalAlignment(SwingConstants.CENTER);
		supMedicineName_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		supMedicineName_1.setBounds(615, 279, 166, 39);
		editpanel.add(supMedicineName_1);
		
		supnamefield = new JTextField();
		supnamefield.setEditable(false);
		supnamefield.setColumns(10);
		supnamefield.setBounds(812, 273, 201, 46);
		editpanel.add(supnamefield);
		
		JSpinner addquantity = new JSpinner();
		addquantity.setModel(new SpinnerNumberModel(0, 0, 200, 1));
//		SpinnerNumberModel spin = new SpinnerNumberModel(0,0,medmax,1);
		addquantity.addMouseListener(new MouseAdapter() {
			//@Override
//			public void mouseClicked(MouseEvent e) {
//				try
//				{
//					Connection con = jdbc.getCon();
//					Statement st = con.createStatement();
//					ResultSet rs = st.executeQuery("select * from medicine where MedicineName='"+medname+"';");
//					if(rs.next())
//					{
//					medmax = Integer.parseInt(rs.getString("Quantity"));
//					addquantity.setModel(new SpinnerNumberModel(0, 0, medmax, 1));
//					}
//				}
//				catch(Exception e1)
//				{
//					JOptionPane.showMessageDialog(null, e1);
//				}
			//}
		});
		addquantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		addquantity.setBounds(397, 355, 92, 32);
		
		editpanel.add(addquantity);
		
		JComboBox mednamesearch = new JComboBox();
		mednamesearch.setModel(new DefaultComboBoxModel(new String[] {"select"}));
		mednamesearch.setBounds(288, 145, 273, 39);
		try
		{
			Connection con = jdbc.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select MedicineName from medicine");
			while(rs.next())
			{
				mednamesearch.addItem(rs.getString(1));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		editpanel.add(mednamesearch);
		
		JButton medupdatebtn = new JButton("UPDATE");
		medupdatebtn.setBackground(new Color(254, 250, 203));
		medupdatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String medname = mednamesearch.getSelectedItem().toString().toUpperCase();

				String medcost = costfield.getText().replaceAll("\\s+","");
				String medsupname = supnamefield.getText();
				String medsupuname = supunamefield.getText();
				String add = addquantity.getValue().toString();
				
				int totquantity = 0;
				if(add.equals("0"))
				{
					if(!quantityfield.getText().equals(""))
					{
					totquantity = Integer.parseInt(quantityfield.getText());
					}
				}
				else
				{
					if(!quantityfield.getText().equals(""))
					{
					totquantity = Integer.parseInt(quantityfield.getText())+Integer.parseInt(add);
					}
					else
					{
						totquantity = Integer.parseInt(add);
					}
				}
				
				if(medname.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter the medicine name to be updated");
				}
				else if(medcost.equals("") || medsupname.equals("") || medsupuname.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Medicine name is incorrect");
				}
				else if(!medcost.matches(mobilePattern))
				{
					JOptionPane.showMessageDialog(null, "Cost should be an integer");
				}
				else if(!add.matches(mobilePattern))
				{
					JOptionPane.showMessageDialog(null, "Add quantity should be an integer");
				}
				else
				{
					try
					{
						Connection con = jdbc.getCon();
						PreparedStatement ps = con.prepareStatement("update medicine set MedicineName = '"+medname+"',Quantity = '"+totquantity+"',CostPerUnit = '"+medcost+"',SupplierName = '"+medsupname+"',SupplierUserName = '"+medsupuname+"' where MedicineName='"+medname+"';");
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Medicine updated successfully");
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
		medupdatebtn.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\SAVEICON.png"));
		medupdatebtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		medupdatebtn.setBounds(467, 521, 149, 39);
		editpanel.add(medupdatebtn);
		
		
		
		JButton searchbtn = new JButton("Search");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int checkmedexist = 0;
				String medname = mednamesearch.getSelectedItem().toString().toUpperCase();
				if(medname.equals("select"))
				{
					JOptionPane.showMessageDialog(null, "Select the medicine name to be updated");
				}
				else
				{
					try
					{
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from medicine where MedicineName = '"+medname+"'");
					while(rs.next())
					{
						quantityfield.setText(rs.getString("Quantity"));
						quantityfield.setEditable(false);
						costfield.setText(rs.getString("CostPerUnit"));
						supnamefield.setText(rs.getString("SupplierName"));
						supunamefield.setText(rs.getString("SupplierUserName"));
						checkmedexist = 1;
					}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
					if(checkmedexist == 0)
					{
						JOptionPane.showMessageDialog(null, "Medicine doesn't exists");
					}
				}
			}
		});
		searchbtn.setBackground(new Color(254, 250, 203));
		searchbtn.setIcon(new ImageIcon(medicineInfo.class.getResource("/images/magnifying-glass_2811806.png")));
		searchbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchbtn.setBounds(691, 143, 131, 39);
		editpanel.add(searchbtn);
		
		JLabel supMedicineName_1_1 = new JLabel("SUPPLIER USERNAME");
		supMedicineName_1_1.setForeground(new Color(254, 250, 203));
		supMedicineName_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		supMedicineName_1_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		supMedicineName_1_1.setBounds(602, 361, 179, 39);
		editpanel.add(supMedicineName_1_1);
		
		supunamefield = new JTextField();
		supunamefield.setColumns(10);
		supunamefield.setBounds(812, 355, 201, 46);
		supunamefield.setEditable(false);
		editpanel.add(supunamefield);
		
		
		JLabel lblNewLabel_6 = new JLabel("MEDICINE NAME");
		lblNewLabel_6.setForeground(new Color(254, 250, 203));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(112, 150, 118, 30);
		editpanel.add(lblNewLabel_6);
		
		JButton btnBack_1 = new JButton("BACK");
		btnBack_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnBack_1.setBackground(new Color(254, 250, 203));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
				setVisible(false);
			}
		});
		btnBack_1.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack_1.setBounds(884, 48, 129, 49);
		editpanel.add(btnBack_1);
		
		
		
		 CheckboxGroup cbg = new CheckboxGroup();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\Reporting on our Progress and ilestones (4).jpg"));
		lblNewLabel.setBounds(0, 0, 1273, 652);
		contentPane.add(lblNewLabel);
	}
}
