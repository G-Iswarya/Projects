package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.jdbc;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class adminprofile extends JFrame {
	public String mailpattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
	public String numberPattern = "^[0-9]*$";

	private JPanel contentPane;
	private JTextField profilename;
	private JTextField profileemail;
	private JTextField profilemob;
	private JTextField profileuname;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					adminprofile frame = new adminprofile();
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
	public adminprofile(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1295, 700);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PROFILE");
		lblNewLabel.setForeground(new Color(255, 255, 128));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(326, 11, 488, 78);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(255, 255, 255));
		separator.setBounds(0, 86, 1274, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(adminprofile.class.getResource("/images/profile (2).png")));
		lblNewLabel_1.setBounds(130, 196, 216, 215);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("NAME");
		lblNewLabel_3.setForeground(new Color(255, 255, 128));
		lblNewLabel_3.setBackground(new Color(240, 240, 240));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(515, 104, 140, 32);
		contentPane.add(lblNewLabel_3);
		
		profilename = new JTextField();
		profilename.setBounds(513, 147, 317, 38);
		contentPane.add(profilename);
		profilename.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("EMAIL");
		lblNewLabel_3_1.setForeground(new Color(255, 255, 128));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_3_1.setBounds(515, 226, 140, 32);
		contentPane.add(lblNewLabel_3_1);
		
		profileemail = new JTextField();
		profileemail.setColumns(10);
		profileemail.setBounds(515, 269, 317, 38);
		contentPane.add(profileemail);
		
		JLabel lblNewLabel_3_2 = new JLabel("MOBILE NUMBER");
		lblNewLabel_3_2.setForeground(new Color(255, 255, 128));
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_3_2.setBounds(515, 353, 140, 32);
		contentPane.add(lblNewLabel_3_2);
		
		profilemob = new JTextField();
		profilemob.setColumns(10);
		profilemob.setBounds(515, 396, 317, 38);
		contentPane.add(profilemob);
		
		profileuname = new JTextField();
		profileuname.setForeground(new Color(255, 255, 255));
		profileuname.setHorizontalAlignment(SwingConstants.CENTER);
		profileuname.setEditable(false);
		profileuname.setColumns(10);
		profileuname.setBackground(new Color(0,0,0,100));
		profileuname.setText(username);
		profileuname.setBounds(166, 422, 144, 38);
		contentPane.add(profileuname);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname = profilename.getText();
				String pemail = profileemail.getText();
				String pmob = profilemob.getText();
				String puname = profileuname.getText();
				try
				{
					if(pname.equals("") || pemail.equals("") || pmob.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Fields cannot be empty");
					}
					else if(!pemail.matches(mailpattern))
					{
						JOptionPane.showMessageDialog(null, "Invalid email");
					}
					else if(!pmob.matches(numberPattern) || pmob.length()!=10)
					{
						JOptionPane.showMessageDialog(null, "Invalid mobile number");
					}
					else
					{
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					st.executeUpdate("update pharma set name='"+pname+"', mobileNumber='"+pmob+"', email = '"+pemail+"' where username='"+puname+"';");
					JOptionPane.showMessageDialog(null, "Updated successfully");
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\SAVEICON.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(369, 544, 156, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setBackground(Color.yellow);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setBackground(Color.white);
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminoptions(username).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(1012, 25, 128, 38);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(adminprofile.class.getResource("/images/Reporting on our Progress and ilestones (1).jpg")));
		lblNewLabel_4.setBounds(0, 0, 1274, 663);
		contentPane.add(lblNewLabel_4);
		
		try
		{
			Connection con = jdbc.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from pharma where username='"+username+"';");
			while(rs.next())
			{
				profilename.setText(rs.getString("name"));
				profileemail.setText(rs.getString("email"));
				profilemob.setText(rs.getString("mobileNumber"));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
