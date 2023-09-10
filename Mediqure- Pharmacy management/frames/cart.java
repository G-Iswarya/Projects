package frames;
import java.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import db.jdbc;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.text.SimpleDateFormat;
public class cart extends JFrame {
	static int billID=102;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JTextField medname;
	private JLabel lblQuantity;
	private JLabel lblCostPerUnit;
	private JTextField cost;
	private JLabel lblTotalPrice;
	private JTextField total;
	private JLabel lblNewLabel_1;
	private JButton addtocartbtn;
	private JTable carttable;
	private JButton btnNewButton;
	private JTextField totalprice;
	ArrayList <String> alname = new ArrayList<String>();
	ArrayList <Integer> alcost = new ArrayList<Integer>();
	ArrayList <Integer> alquant = new ArrayList<Integer>();
	ArrayList <Integer> altot = new ArrayList<Integer>();
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					cart frame = new cart();
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
	public cart(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1552, 732);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 223, 162));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(47, 112, 490, 548);
		contentPane.add(scrollPane);
		
		JSpinner quantity = new JSpinner();
		
		quantity.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String q = quantity.getValue().toString();
				String tot = total.getText();
				String c = cost.getText();
				 int tott = Integer.parseInt(q)*Integer.parseInt(c);
					total.setText(String.valueOf(tott));
			}
		});
		((JSpinner.DefaultEditor)quantity.getEditor()).addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String q = quantity.getValue().toString();
				String tot = total.getText();
				String c = cost.getText();
				 int tott = Integer.parseInt(q)*Integer.parseInt(c);
					total.setText(String.valueOf(tott));
			}
		});
		quantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		quantity.setBounds(915, 76, 141, 34);
		contentPane.add(quantity);
		
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dt1 = (DefaultTableModel)table.getModel();
				int row = table.convertRowIndexToModel(table.getSelectedRow());
				String dt1name = dt1.getValueAt(row, 1).toString();
				String dt1cost = dt1.getValueAt(row, 2).toString();
				//int totalPrice = Integer.parseInt(total.getText());
				try
				{
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from medicine where (MedicineName='"+dt1name+"') and (CostPerUnit='"+dt1cost+"');");
					while(rs.next())
					{
						medname.setText(dt1name);
						cost.setText(rs.getString("CostPerUnit"));
						quantity.setModel(new SpinnerNumberModel(0,0, Integer.parseInt(rs.getString("Quantity")),1));
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.NO.", "Medicines", "Price", ""
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(1).setMinWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setMinWidth(105);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(textField.getText().equals(""))
				{
					medname.setText("");
					quantity.setValue(0);
					cost.setText("");
					total.setText("");
				}
				search(textField.getText().toUpperCase());
			}
		});
		textField.setBounds(147, 38, 310, 48);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("Medicine name");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(614, 38, 183, 34);
		contentPane.add(lblNewLabel);
		
		medname = new JTextField();
		medname.setEditable(false);
		medname.setBounds(614, 77, 232, 34);
		contentPane.add(medname);
		medname.setColumns(10);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(0, 0, 0));
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantity.setBounds(915, 38, 183, 34);
		contentPane.add(lblQuantity);
		
		lblCostPerUnit = new JLabel("Cost per unit");
		lblCostPerUnit.setForeground(new Color(0, 0, 0));
		lblCostPerUnit.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCostPerUnit.setBounds(614, 122, 183, 34);
		contentPane.add(lblCostPerUnit);
		
		cost = new JTextField();
		cost.setEditable(false);
		cost.setColumns(10);
		cost.setBounds(614, 167, 232, 34);
		contentPane.add(cost);
		
		lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setForeground(new Color(0, 0, 0));
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalPrice.setBounds(915, 121, 183, 34);
		contentPane.add(lblTotalPrice);
		
		total = new JTextField();
		total.setFont(new Font("Tahoma", Font.BOLD, 14));
		total.setText("0");
		total.setEditable(false);
		total.setColumns(10);
		total.setBounds(915, 166, 216, 34);
		contentPane.add(total);
		
		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(49, 45, 88, 34);
		contentPane.add(lblNewLabel_1);
		
		addtocartbtn = new JButton("Add to Cart");
		addtocartbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String medicinename = medname.getText();
				int medicinecost = Integer.parseInt(cost.getText());
				int medicinetotal = Integer.parseInt(total.getText());
				int medicinequantity = Integer.parseInt(quantity.getValue().toString());
				DefaultTableModel model = (DefaultTableModel)carttable.getModel();
		
				int totval=0,medincart=0,quantexceed=0;
				try
				{
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs1 = st.executeQuery("select * from medicine where medicinename='"+medicinename+"';");
					rs1.next();
					if(quantexceed == 0)
					{
					Statement st1 = con.createStatement();
					ResultSet rs = st1.executeQuery("select * from cart where medicinename='"+medicinename+"';");
					while(rs.next())
					{
						medincart = 1;
						int quant = medicinequantity+Integer.parseInt(rs.getString("quantity"));
						if(quant>Integer.parseInt(rs1.getString("Quantity")))
						{
							quantexceed = 1;
							JOptionPane.showMessageDialog(null, "More than available quantity");
						}
						else
						{
						int tot = quant*medicinecost;
						Statement st2 = con.createStatement();
						st2.executeUpdate("update cart set quantity='"+quant+"', total='"+tot+"' where medicinename='"+medicinename+"';");
						displaycart(medicinename,quant,tot);
						}
					}
					if(medincart==0)
					{
						try
						{
							if(medicinequantity>Integer.parseInt(rs1.getString("Quantity")))
							{
								quantexceed = 1;
								JOptionPane.showMessageDialog(null, "More than available quantity");
							}
							con = jdbc.getCon();
							Statement st3 = con.createStatement();
							st3.executeUpdate("insert into cart (medicinename,costperunit,quantity,total) values('"+medicinename+"','"+medicinecost+"','"+medicinequantity+"','"+medicinetotal+"');");
							model.addRow(new Object[] {medicinename,medicinecost, medicinequantity, medicinetotal});
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, e1);
						}
						
					}
					
				}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				for(int i=0;i<model.getRowCount();i++)
				{
					totval += Integer.parseInt(model.getValueAt(i, 3).toString());
					totalprice.setText(String.valueOf(totval));
				}
				
			}

			private void displaycart(String medicinename, int quant, int tot) {

				DefaultTableModel model = (DefaultTableModel)carttable.getModel();
				for(int i=0;i<model.getRowCount();i++)
				{
					if(model.getValueAt(i, 0).equals(medicinename))
					{
						model.setValueAt(quant, i, 2);
						model.setValueAt(tot, i, 3);
					}
				}
				
			}
		});
		addtocartbtn.setIcon(new ImageIcon(cart.class.getResource("/images/MEDICINEICON.png")));
		addtocartbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addtocartbtn.setBounds(996, 221, 162, 41);
		contentPane.add(addtocartbtn);

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(614, 273, 628, 218);
		contentPane.add(scrollPane_1);
		
		JSpinner qtyspinner = new JSpinner();
		qtyspinner.setFont(new Font("Tahoma", Font.BOLD, 13));
		qtyspinner.setBounds(956, 502, 65, 34);
		contentPane.add(qtyspinner);
		
		carttable = new JTable();
		carttable.setShowVerticalLines(false);
		carttable.setShowHorizontalLines(false);
		carttable.setShowGrid(false);
		carttable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) carttable.getModel();
				if(carttable.getSelectedRowCount()==1)
				{
					  qtyspinner.setValue(model.getValueAt(carttable.getSelectedRow(), 2));
				}
			}
		});
		carttable.setFont(new Font("Tahoma", Font.BOLD, 13));
		carttable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Medicine Name", "Cost per unit", "Quantity", "Total cost"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		carttable.getColumnModel().getColumn(0).setPreferredWidth(105);
		carttable.getColumnModel().getColumn(0).setMinWidth(105);
		carttable.getColumnModel().getColumn(1).setPreferredWidth(105);
		carttable.getColumnModel().getColumn(1).setMinWidth(105);
		carttable.getColumnModel().getColumn(2).setPreferredWidth(105);
		carttable.getColumnModel().getColumn(2).setMinWidth(105);
		carttable.getColumnModel().getColumn(3).setMinWidth(105);
		scrollPane_1.setViewportView(carttable);
		
//		JComboBox combo = new JComboBox();
//		combo.addItem(1);
//		combo.addItem(2);
//		TableColumn col = carttable.getColumnModel().getColumn(2);
//		col.setCellEditor(new DefaultCellEditor(combo));
		
		btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new pharmaoptions(username).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(1100, 24, 118, 48);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("TOTAL");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(1009, 506, 105, 34);
		contentPane.add(lblNewLabel_2);
		
		totalprice = new JTextField();
		totalprice.setEditable(false);
		totalprice.setHorizontalAlignment(SwingConstants.RIGHT);
		totalprice.setFont(new Font("Tahoma", Font.BOLD, 14));
		totalprice.setText("0");
		totalprice.setBounds(1124, 502, 118, 43);
		contentPane.add(totalprice);
		totalprice.setColumns(10);
		
		JButton placeorder = new JButton("PLACE ORDER");
		placeorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int val= JOptionPane.showConfirmDialog(null, "Do you want to confirm your order?", "Place order",JOptionPane.YES_NO_OPTION);
					if(val == JOptionPane.YES_OPTION)
					{
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					DefaultTableModel model = (DefaultTableModel)carttable.getModel();
					for(int i=0;i<model.getRowCount();i++)
					{
						int q = Integer.parseInt(carttable.getValueAt(i, 2).toString());
						String mn = carttable.getValueAt(i, 0).toString();
						st.executeUpdate(" update medicine set Quantity=Quantity- '"+q+"' where MedicineName='"+mn+"';");
					}
					
					//st.executeUpdate(" update medicine set Quantity=Quantity-(select quantity from cart where medicine.MedicineName=cart.medicinename);");
					ResultSet rs = st.executeQuery("select * from cart;");
					while(rs.next())
					{
						 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
						 Date date = new Date();
						 alname.add(rs.getString("medicinename"));
						 alcost.add(rs.getInt("costperunit"));
						 alquant.add(rs.getInt("quantity"));
						 altot.add(rs.getInt("total"));
						Statement st1 = con.createStatement();
						st1.executeUpdate("insert into saleshistory(billID, time, medicinename, costperunit, quantity, total) values('"+billID+"','"+formatter.format(date)+"','"+rs.getString("medicinename")+"','"+rs.getString("costperunit")+"', '"+rs.getString("quantity")+"','"+rs.getString("total")+"');");
						Statement st2 = con.createStatement();
						st2.executeUpdate("delete from cart;");
					}
					billID++;
					}
					JOptionPane.showMessageDialog(null, "Order placed successfully. Kindly view receipt");
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
			}
		});
		placeorder.setFont(new Font("Tahoma", Font.BOLD, 14));
		placeorder.setBounds(915, 600, 149, 48);
		contentPane.add(placeorder);
		
		
		JButton updatebtn = new JButton("UPDATE");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int totval = 0,quantexceed=0;
				String medicinename = medname.getText();
				int medicinecost = Integer.parseInt(carttable.getValueAt(carttable.getSelectedRow(),1).toString());
				int medicinetotal = Integer.parseInt(total.getText());
				int medicinequantity = Integer.parseInt(qtyspinner.getValue().toString());
				
				
				try {
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs1 = st.executeQuery("select * from medicine where medicinename='"+medicinename+"';");
					while(rs1.next())
					{
						if(medicinequantity>Integer.parseInt(rs1.getString("Quantity")))
						{
							quantexceed = 1;
							JOptionPane.showMessageDialog(null, "More than available quantity");
						}
					}
				} catch (NumberFormatException | HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(quantexceed==0)
				{
				DefaultTableModel model = (DefaultTableModel) carttable.getModel();
				model.setValueAt(qtyspinner.getValue(), carttable.getSelectedRow(), 2);
				model.setValueAt(Integer.parseInt(qtyspinner.getValue().toString())*medicinecost, carttable.getSelectedRow(), 3);
				for(int i=0;i<model.getRowCount();i++)
				{
					totval += Integer.parseInt(model.getValueAt(i, 3).toString());
				}
				totalprice.setText(String.valueOf(totval));
				
				try
				{
					Connection con = jdbc.getCon();
					Statement st1 = con.createStatement();
					st1.executeUpdate("update cart set quantity= '"+qtyspinner.getValue()+"', total= '"+Integer.parseInt(qtyspinner.getValue().toString())*medicinecost+"' where medicinename='"+carttable.getValueAt(carttable.getSelectedRow(), 0)+"';");
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				}
			}
		});
		updatebtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updatebtn.setBounds(596, 609, 105, 31);
		contentPane.add(updatebtn);
		
		JButton deletebtn = new JButton("DELETE");
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Do you want to delete the data?");
				try
				{
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					int rs = st.executeUpdate("delete from cart where medicinename='"+carttable.getValueAt(carttable.getSelectedRow(),0)+"';");
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				DefaultTableModel model = (DefaultTableModel)carttable.getModel();
				model.removeRow(carttable.getSelectedRow());
			}
		});
		deletebtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deletebtn.setBounds(733, 609, 105, 31);
		contentPane.add(deletebtn);
		
		JLabel lblNewLabel_3 = new JLabel("Update quantity");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(810, 504, 136, 31);
		contentPane.add(lblNewLabel_3);
		
		JButton btnReceipt = new JButton("RECEIPT");
		btnReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
				 Date date = new Date();
				new bill(billID, formatter.format(date),alname, alcost, alquant, altot, Integer.parseInt(totalprice.getText())).setVisible(true);
			}
		});
		btnReceipt.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReceipt.setBounds(1093, 600, 149, 48);
		contentPane.add(btnReceipt);
		
		
		
		
		DefaultTableModel obj = (DefaultTableModel)table.getModel();
		try
		{
			int sno = 1;
			Connection con = jdbc.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from medicine");
			while(rs.next())
			{
			obj.addRow(new Object[] {sno, rs.getString("MedicineName"), rs.getString("CostPerUnit")});
			sno++;
			}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}
	}
		
		public void search(String str)
		{
			DefaultTableModel obj = (DefaultTableModel)table.getModel();
			TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
			table.setRowSorter(obj1);
			obj1.setRowFilter(RowFilter.regexFilter(str));
		}
}
