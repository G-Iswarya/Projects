package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.jdbc;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class pharmaprofile extends JFrame {

	private JPanel contentPane;
	private JTextField profilename;
	private JTextField profilemail;
	private JTextField profilemob;
	private JTextField profileuname;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					pharmaprofile frame = new pharmaprofile();
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
	public pharmaprofile(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1295, 700);
		contentPane = new JPanel();
		setExtendedState(MAXIMIZED_BOTH);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon(pharmaprofile.class.getResource("/images/profile (2).png")));
		lblNewLabel_1.setBounds(189, 184, 209, 257);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("NAME");
		lblNewLabel_3.setForeground(new Color(255, 255, 128));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_3.setBounds(601, 160, 140, 32);
		contentPane.add(lblNewLabel_3);
		
		profilename = new JTextField();
		profilename.setEditable(false);
		profilename.setColumns(10);
		profilename.setBounds(601, 203, 317, 38);
		contentPane.add(profilename);
		
		JLabel lblNewLabel_3_1 = new JLabel("EMAIL");
		lblNewLabel_3_1.setForeground(new Color(255, 255, 128));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_3_1.setBounds(601, 294, 140, 32);
		contentPane.add(lblNewLabel_3_1);
		
		profilemail = new JTextField();
		profilemail.setEditable(false);
		profilemail.setColumns(10);
		profilemail.setBounds(601, 337, 317, 38);
		contentPane.add(profilemail);
		
		JLabel lblNewLabel_3_2 = new JLabel("MOBILE NUMBER");
		lblNewLabel_3_2.setForeground(new Color(255, 255, 128));
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_3_2.setBounds(601, 428, 140, 32);
		contentPane.add(lblNewLabel_3_2);
		
		profilemob = new JTextField();
		profilemob.setEditable(false);
		profilemob.setColumns(10);
		profilemob.setBounds(601, 471, 317, 38);
		contentPane.add(profilemob);
		
		profileuname = new JTextField();
		profileuname.setHorizontalAlignment(SwingConstants.CENTER);
		profileuname.setForeground(new Color(255, 255, 255));
		profileuname.setText("<dynamic>");
		profileuname.setEditable(false);
		profileuname.setColumns(10);
		profileuname.setBackground(new Color(0,0,0,100));
		profileuname.setText(username);
		profileuname.setBounds(229, 428, 129, 38);
		contentPane.add(profileuname);
		
		JLabel lblNewLabel = new JLabel("PROFILE");
		lblNewLabel.setForeground(new Color(255, 255, 128));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(365, 8, 488, 78);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 84, 1281, 2);
		contentPane.add(separator);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setBackground(Color.YELLOW);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setBackground(Color.WHITE);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new pharmaoptions(username).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		btnNewButton_1.setBounds(967, 35, 140, 38);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		contentPane.add(btnNewButton_1);
		
		JLabel label = new JLabel("New label");
		label.setBounds(1068, 638, -418, -398);
		contentPane.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(pharmaprofile.class.getResource("/images/Reporting on our Progress and ilestones (1).jpg")));
		lblNewLabel_2.setBounds(10, 8, 1271, 663);
		contentPane.add(lblNewLabel_2);
		
		try
		{
			Connection con = jdbc.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from pharma where username='"+username+"';");
			while(rs.next())
			{
				profilename.setText(rs.getString("name"));
				profilemail.setText(rs.getString("email"));
				profilemob.setText(rs.getString("mobileNumber"));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
