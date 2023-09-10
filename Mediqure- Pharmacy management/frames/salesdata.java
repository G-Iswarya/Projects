package frames;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import db.jdbc;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class salesdata extends JFrame {
	static int billno = 101;

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					salesdata frame = new salesdata();
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
	public salesdata(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1363, 725);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 223, 162));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(105, 106, 795, 480);
		scrollPane.setBackground(new Color(255, 255, 255));;
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setOpaque(false);
		table.setBackground(new Color(222, 223, 162));
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TIME & DATE", "BILL ID", "MEDICINE NAME", "QUANTITY", "COST PER UNIT", "TOTAL"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setMinWidth(105);
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
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("ORDER BY DATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{ 
					DefaultTableModel dt = (DefaultTableModel) table.getModel();
					dt.setRowCount(0);
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from saleshistory order by time desc");
					while(rs.next())
					{
					dt.addRow(new Object[] {rs.getString("time"),rs.getString("billID"), rs.getString("medicinename"), rs.getString("quantity"), rs.getString("costperunit"), rs.getString("total")});
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(413, 597, 153, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(947, 35, 132, 44);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel = new JLabel("SALES HISTORY");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(392, 35, 314, 34);
		contentPane.add(lblNewLabel);
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		try
		{
			Connection con = jdbc.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from saleshistory");
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getString("time"),rs.getString("billID"), rs.getString("medicinename"), rs.getString("quantity"), rs.getString("costperunit"), rs.getString("total")});
			}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}
	}
}
