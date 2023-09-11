package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class bill extends JFrame {

	private JPanel contentPane;
	private JTable billtable;
	private JTextField datefield;
	private JLabel lblNewLabel_1;
	private JTextField billid;
	private JLabel lblthanksForVisiting;
	private JTextField totbill;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					bill frame = new bill();
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
	public bill(int billID, String date, ArrayList<String> name, ArrayList<Integer> cost, ArrayList<Integer> quant, ArrayList<Integer> tot,int totalamt) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 731);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 175, 776, 376);
		contentPane.add(scrollPane);
		
		billtable = new JTable();
		billtable.setShowVerticalLines(false);
		billtable.setShowGrid(false);
		billtable.setShowHorizontalLines(false);
		billtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"MEDICINE ", "QUANTITY", "COST PER UNIT", "TOTAL"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		billtable.getColumnModel().getColumn(0).setMinWidth(105);
		billtable.getColumnModel().getColumn(1).setMinWidth(105);
		billtable.getColumnModel().getColumn(2).setMinWidth(105);
		billtable.getColumnModel().getColumn(3).setMinWidth(105);
		scrollPane.setViewportView(billtable);
		
		datefield = new JTextField();
		datefield.setBackground(new Color(255, 255, 255));
		datefield.setEditable(false);
		datefield.setBounds(256, 60, 272, 45);
		contentPane.add(datefield);
		datefield.setColumns(10);
		datefield.setText(date);
		
		JLabel lblNewLabel = new JLabel("GENERATED ON");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(54, 60, 179, 45);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("BILL ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(818, 58, 128, 45);
		contentPane.add(lblNewLabel_1);
		
		billid = new JTextField();
		billid.setText(String.valueOf(billID));
		billid.setEditable(false);
		billid.setColumns(10);
		billid.setBackground(Color.WHITE);
		billid.setBounds(957, 60, 128, 45);
		contentPane.add(billid);
		
		JLabel design = new JLabel("");
		design.setFont(new Font("Cambria", Font.BOLD, 14));
		design.setBounds(40, 134, 1163, 19);
		design.setText("---------------------------------------------------------------------------------------------------------------RECEIPT--------------------------------------------------------------------------------------------------------------");
		contentPane.add(design);
		
		lblthanksForVisiting = new JLabel("------------------------------------------------------------------------------------------------------------------THANKS FOR VISITING------------------------------------------------------------------------------------------------");
		lblthanksForVisiting.setHorizontalAlignment(SwingConstants.LEFT);
		lblthanksForVisiting.setFont(new Font("Cambria", Font.BOLD, 14));
		lblthanksForVisiting.setBounds(40, 603, 1163, 19);
		contentPane.add(lblthanksForVisiting);
		
		totbill = new JTextField();
		totbill.setEditable(false);
		totbill.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		totbill.setBounds(957, 562, 128, 32);
		contentPane.add(totbill);
		totbill.setText(String.valueOf(totalamt));
		totbill.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("AMOUNT");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel_2.setBounds(835, 564, 107, 28);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("MEDIQURE");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_3.setBounds(515, 11, 281, 54);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\vendor.png"));
		lblNewLabel_4.setBounds(866, 216, 349, 335);
		contentPane.add(lblNewLabel_4);
		
		DefaultTableModel model = (DefaultTableModel) billtable.getModel();
		model.setRowCount(0);
		for(int i=0;i<name.size();i++)
		{
			model.addRow(new Object[] {name.get(i),quant.get(i),cost.get(i),tot.get(i)});
		}
		
	}
}
