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
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class medicineInfo extends JFrame {
	public String mobilePattern = "^[0-9]*$";
	private JPanel contentPane;
	private JTextField medname;
	private JTable table;
	private JTextField medID;
	private JTextField costperunit;
	private JTextField mednamesearch;
	private JTextField quantityfield;
	private JTextField costfield;
	private JTextField supnamefield;
	private JTextField supunamefield;
	private String username;
	int  medmax;

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
		setBounds(100, 100, 1451, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(198, 0, 1075, 689);
		contentPane.add(tabbedPane);
		
		JButton updatebtn = new JButton("UPDATE");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		updatebtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updatebtn.setBounds(10, 464, 156, 60);
		contentPane.add(updatebtn);
		
		JButton viewbtn = new JButton("VIEW");
		viewbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{ int sno = 1;
					DefaultTableModel dt = (DefaultTableModel) table.getModel();
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
				
				tabbedPane.setSelectedIndex(0);
			}
		});
		viewbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		viewbtn.setBounds(10, 304, 156, 60);
		contentPane.add(viewbtn);
		
		JButton addbtn = new JButton("ADD");
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
		
		
		JPanel viewpanel = new JPanel();
		viewpanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("VIEW", null, viewpanel, null);
		viewpanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 98, 776, 464);
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
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 11, 1258, 639);
		viewpanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\vendor.png"));
		lblNewLabel_4.setBounds(813, 11, 422, 574);
		viewpanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("VIEW MEDICINE");
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_5.setBounds(307, 11, 422, 62);
		viewpanel.add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Order by medicine");
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
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(170, 575, 213, 43);
		viewpanel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Order by supplier");
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
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(503, 573, 213, 43);
		viewpanel.add(btnNewButton_1_1);
		
		JButton btnBack_1_1 = new JButton("BACK");
		btnBack_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!username.equals("admin"))
				{
					new pharmaoptions(username).setVisible(true);
				}
				else
				{
					new adminoptions(username).setVisible(true);
				}
				setVisible(false);
			}
		});
		btnBack_1_1.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack_1_1.setBounds(859, 36, 129, 49);
		viewpanel.add(btnBack_1_1);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("ADD", null, panel, null);
		panel.setLayout(null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
			}
		});
		
		JLabel lblCostPerUnit = new JLabel("COST PER UNIT");
		lblCostPerUnit.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblCostPerUnit.setBounds(25, 376, 221, 35);
		panel.add(lblCostPerUnit);
		
		costperunit = new JTextField();
		costperunit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		costperunit.setColumns(10);
		costperunit.setBounds(25, 422, 258, 35);
		panel.add(costperunit);
		
		JLabel lblMedicineid = new JLabel("MEDICINE ID");
		lblMedicineid.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblMedicineid.setBounds(395, 249, 221, 35);
		panel.add(lblMedicineid);
		
		medID = new JTextField();
		medID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		medID.setColumns(10);
		medID.setBounds(395, 295, 258, 35);
		panel.add(medID);
		btnBack.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack.setBounds(905, 35, 129, 49);
		panel.add(btnBack);
		
		JLabel lblMedicineName = new JLabel("MEDICINE NAME");
		lblMedicineName.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblMedicineName.setBounds(25, 105, 221, 35);
		panel.add(lblMedicineName);
		
		medname = new JTextField();
		medname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		medname.setColumns(10);
		medname.setBounds(25, 151, 258, 35);
		panel.add(medname);
		
		JLabel lblNoOfMedicines = new JLabel("COUNT");
		lblNoOfMedicines.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblNoOfMedicines.setBounds(395, 105, 221, 35);
		panel.add(lblNoOfMedicines);
		
		JSpinner count = new JSpinner();
		count.setModel(new SpinnerNumberModel(0, 0, 250, 1));
		count.setFont(new Font("Tahoma", Font.BOLD, 14));
		count.setBounds(395, 151, 258, 35);
		panel.add(count);
		
		JLabel lblSupplier = new JLabel("SUPPLIER");
		lblSupplier.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblSupplier.setBounds(25, 247, 221, 35);
		panel.add(lblSupplier);
		
		JComboBox supname = new JComboBox();
		supname.setModel(new DefaultComboBoxModel(new String[] {"select"}));
		supname.setBounds(25, 295, 258, 35);
		panel.add(supname);
		
		try
		{
		Connection con = jdbc.getCon();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from suppliers");
		while(rs.next())
		{
			supname.addItem(rs.getString("name"));
		}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String med_name = medname.getText().toUpperCase();
				String suppname = supname.getSelectedItem().toString();
				try
				{
					int medcost = Integer.parseInt(costperunit.getText());
					int med_ID = Integer.parseInt(medID.getText());
					int medcount = Integer.parseInt(count.getValue().toString());
					
					if(med_name.equals("") || suppname.equals("select"))
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
							st.executeUpdate("insert into medicine values('"+med_name+"','"+med_ID+"','"+medcount+"','"+medcost+"','"+suppname+"')");
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
		btnNewButton.setBounds(309, 525, 129, 49);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\bg.png"));
		lblNewLabel_2.setBounds(10, 11, 1060, 639);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(350, 27, 560, 60);
		panel.add(lblNewLabel_1);
		
		JPanel editpanel = new JPanel();
		editpanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("EDIT", null, editpanel, null);
		editpanel.setLayout(null);
		
		JLabel lblEditMedicine = new JLabel("UPDATE MEDICINE");
		lblEditMedicine.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditMedicine.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEditMedicine.setBounds(158, 68, 689, 64);
		editpanel.add(lblEditMedicine);
		
		mednamesearch = new JTextField();
		mednamesearch.setColumns(10);
		mednamesearch.setBounds(240, 143, 410, 46);
		editpanel.add(mednamesearch);
		
		
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblQuantity.setBounds(81, 279, 131, 39);
		editpanel.add(lblQuantity);
		
		quantityfield = new JTextField();
		quantityfield.setColumns(10);
		quantityfield.setBounds(288, 273, 201, 46);
		editpanel.add(quantityfield);
		
		JLabel lblMobilenumber = new JLabel("ADD");
		lblMobilenumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobilenumber.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblMobilenumber.setBounds(327, 355, 60, 39);
		editpanel.add(lblMobilenumber);
		
		JLabel lblCostPerUnit_1 = new JLabel("COST PER UNIT");
		lblCostPerUnit_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostPerUnit_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblCostPerUnit_1.setBounds(105, 449, 131, 39);
		editpanel.add(lblCostPerUnit_1);
		
		costfield = new JTextField();
		costfield.setColumns(10);
		costfield.setBounds(288, 443, 201, 46);
		editpanel.add(costfield);
		
		JLabel supMedicineName_1 = new JLabel("SUPPLIER NAME");
		supMedicineName_1.setHorizontalAlignment(SwingConstants.CENTER);
		supMedicineName_1.setFont(new Font("Sitka Text", Font.BOLD, 14));
		supMedicineName_1.setBounds(615, 279, 166, 39);
		editpanel.add(supMedicineName_1);
		
		supnamefield = new JTextField();
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
		
		JButton medupdatebtn = new JButton("UPDATE");
		medupdatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String medname = mednamesearch.getText().toUpperCase();

				String medcost = costfield.getText();
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
						PreparedStatement ps = con.prepareStatement("update medicine set MedicineName = '"+medname+"',Quantity = '"+totquantity+"',CostPerUnit = '"+medcost+"',SupplierName = '"+medsupname+"',SupplierUserName = '"+medsupuname+"' where MedicineName='"+mednamesearch.getText()+"';");
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
				String medname = mednamesearch.getText().toUpperCase();
				if(medname.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter the medicine name to be updated");
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
		searchbtn.setBackground(new Color(128, 128, 128));
		searchbtn.setIcon(new ImageIcon(medicineInfo.class.getResource("/images/magnifying-glass_2811806.png")));
		searchbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchbtn.setBounds(691, 143, 131, 39);
		editpanel.add(searchbtn);
		
		JLabel supMedicineName_1_1 = new JLabel("SUPPLIER USERNAME");
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
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(112, 150, 118, 30);
		editpanel.add(lblNewLabel_6);
		
		JButton btnBack_1 = new JButton("BACK");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
			}
		});
		btnBack_1.setFont(new Font("Calibri Light", Font.BOLD, 15));
		btnBack_1.setBounds(884, 48, 129, 49);
		editpanel.add(btnBack_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 0, 188, 689);
		contentPane.add(lblNewLabel);
	}
}
