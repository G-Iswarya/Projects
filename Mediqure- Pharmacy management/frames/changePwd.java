package frames;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Statement;
import java.sql.ResultSet;

import db.jdbc;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JCheckBox;

public class changePwd extends JFrame {
	public String pwdpattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{6,20}$";

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					changePwd frame = new changePwd();
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
	public changePwd(String un) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1256, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CURRENT PASSWORD");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(130, 213, 168, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewPassword = new JLabel("NEW PASSWORD");
		lblNewPassword.setForeground(new Color(255, 255, 255));
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPassword.setBounds(130, 310, 168, 49);
		contentPane.add(lblNewPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(439, 213, 313, 43);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(439, 316, 313, 43);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\SAVEICON.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String oldpwd = passwordField.getText();
				String newpwd = passwordField_1.getText();
				if(newpwd.equals(oldpwd))
				{
					JOptionPane.showMessageDialog(null, "New password should not be same as existing password");
				}
				else if(!newpwd.matches(pwdpattern))
				{
					JOptionPane.showMessageDialog(null, "Password must contain 6 to 20 characters with atleast 1 uppercase, 1 lowercase, 1 number and a special character");
				}
				else
				{
					try
					{
						Connection con = jdbc.getCon();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select password from pharma where username = '"+un+"'");
						while(rs.next())
						{
							if(!rs.getString("password").equals(oldpwd))
							{
								JOptionPane.showMessageDialog(null, "Existing password is incorrect");
							}
							else
							{
								con = jdbc.getCon();
								st = con.createStatement();
								int row = st.executeUpdate("update pharma set password = '"+newpwd+"' where username = '"+un+"'");
								JOptionPane.showMessageDialog(null, "New password has been set successfully");
								passwordField.setText("");
								passwordField_1.setText("");

							}
						}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(375, 461, 146, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(un.equals("admin"))
				{
					setVisible(false);
					new adminoptions(un).setVisible(true);
				}
				else
				{
					setVisible(false);
					new pharmaoptions(un).setVisible(true);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnNewButton_1.setBounds(788, 60, 156, 60);
		contentPane.add(btnNewButton_1);
		
		JCheckBox showpwdchkbox_1 = new JCheckBox("Show password");
		showpwdchkbox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showpwdchkbox_1.isSelected()) 
				{
					passwordField.setEchoChar((char)0);
					passwordField_1.setEchoChar((char)0);
				} 
				else 
				{
					passwordField.setEchoChar('*');
					passwordField_1.setEchoChar('*');
				}
			}
		});
		showpwdchkbox_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		showpwdchkbox_1.setBounds(764, 223, 146, 30);
		contentPane.add(showpwdchkbox_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(changePwd.class.getResource("/images/Reporting on our Progress and ilestones (1).jpg")));
		lblNewLabel_1.setBounds(0, 0, 1278, 643);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
