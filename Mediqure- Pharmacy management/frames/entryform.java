package frames;
import java.sql.ResultSet;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.jdbc;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class entryform extends JFrame {

	private JPanel contentPane;
	private JTextField usernamefield;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entryform frame = new entryform();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public entryform() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1273, 680);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox showpwdchkbox = new JCheckBox("Show password");
		showpwdchkbox.setForeground(new Color(255, 255, 255));
		showpwdchkbox.setBackground(new Color(64, 64, 64));
		showpwdchkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showpwdchkbox.isSelected()) 
				{
					passwordField.setEchoChar((char)0);
				} 
				else 
				{
					passwordField.setEchoChar('*');
				}
			}
		});
		showpwdchkbox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		showpwdchkbox.setBounds(1030, 442, 147, 23);
		contentPane.add(showpwdchkbox);
		
		JLabel lblNewLabel_2 = new JLabel("MEDIQURE");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(392, 48, 351, 67);
		contentPane.add(lblNewLabel_2);
		
		JLabel pharmacygblabel = new JLabel("");
		pharmacygblabel.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\vendor.png"));
		pharmacygblabel.setBounds(43, 101, 459, 439);
		contentPane.add(pharmacygblabel);
		
		JLabel login = new JLabel("LOGIN");
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setForeground(new Color(255, 255, 255));
		login.setBounds(659, 287, 98, 31);
		login.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(login);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(537, 362, 98, 31);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(537, 437, 98, 31);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblPassword);
		
		usernamefield = new JTextField();
		usernamefield.setBounds(770, 364, 248, 31);
		contentPane.add(usernamefield);
		usernamefield.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(770, 439, 248, 31);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname=usernamefield.getText();
				String pword=passwordField.getText();
				try
				{
					Connection con = jdbc.getCon();
					Statement st = con.createStatement();
					String query = "select * from pharma where username='"+uname+"'";
					ResultSet rs = st.executeQuery(query);
					if(rs.next()) {
					if(usernamefield.getText().replaceAll("\\s+","").equals("admin"))
					{
						if(passwordField.getText().replaceAll("\\s+","").equals(rs.getString("password")))
						{
						setVisible(false);
						new adminoptions(uname).setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Incorrect password");
						}
					}
					else
					{ int reg=0;
							if(rs.getString("username").equals(uname))
							{ reg=1;
								if(rs.getString("password").equals(pword))
								{
									JOptionPane.showMessageDialog(null, "Login Successful");
									reg = 2;
									setVisible(false);
									new pharmaoptions(uname).setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Incorrect Password");
								}
						}
						if(reg == 0)
						{
							JOptionPane.showMessageDialog(null, "User not registered. Request admin to register new pharmacist");
						}
					}}}
					
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\login.png"));
		btnNewButton.setBounds(628, 518, 115, 53);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\wrongicon.png"));
		btnClose.setBounds(925, 48, 142, 48);
		
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnClose);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\adminbg.png"));
		lblNewLabel.setBounds(0, 0, 1296, 650);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(381, 221, 577, 363);
		contentPane.add(lblNewLabel_1);
	}
}
