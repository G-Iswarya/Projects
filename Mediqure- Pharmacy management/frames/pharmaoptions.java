package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class pharmaoptions extends JFrame {

	private JPanel contentPane;
	private String username;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public pharmaoptions(String text) {
		username = text;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1198, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1048, 679);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setBounds(136, 92, 767, 488);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("VIEW MEDICINE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new medicineInfo(username).setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(pharmaoptions.class.getResource("/images/MEDICINEICON.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(49, 94, 235, 89);
		panel_1.add(btnNewButton);
		
		JButton btnUpdateMedicine = new JButton("SELL MEDICINE");
		btnUpdateMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new cart(username).setVisible(true);
				setVisible(false);
			}
		});
		btnUpdateMedicine.setIcon(new ImageIcon(pharmaoptions.class.getResource("/images/MEDICINEICON.png")));
		btnUpdateMedicine.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdateMedicine.setBounds(49, 213, 235, 96);
		panel_1.add(btnUpdateMedicine);
		
		JButton btnViewProfile = new JButton("PROFILE");
		btnViewProfile.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\addusericon.png"));
		btnViewProfile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewProfile.setBounds(406, 84, 246, 109);
		panel_1.add(btnViewProfile);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setIcon(new ImageIcon(pharmaoptions.class.getResource("/images/login.png")));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to logout?");
				if(a == 0)
				{
				new entryform().setVisible(true);
				setVisible(false);
				}
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogout.setBounds(406, 213, 246, 109);
		panel_1.add(btnLogout);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new entryform().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(589, 23, 136, 50);
		panel_1.add(btnBack);
		
		JButton btnChangePassword = new JButton("CHANGE PASSWORD");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextField userField = new JTextField();
			      JPasswordField passField = new JPasswordField();
			      String message = "Enter admin user name and password.";
			      int result = JOptionPane.showOptionDialog(null, new Object[] {message, userField, passField},"Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			      if(result == 0)
			      {
			    	  if(userField.getText().equals("admin") && passField.getText().equals("admin"))
			    	  {
			    		  setVisible(false);
			    		  new changePwd(username).setVisible(true);
			    	  }
			    	  else
			    	  {
			    		  JOptionPane.showMessageDialog(null, "Incorrect credentials");
			    	  }
			      }
				
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChangePassword.setBounds(49, 340, 235, 96);
		panel_1.add(btnChangePassword);
		
		JLabel lblNewLabel = new JLabel("WELCOME "+username);
		lblNewLabel.setForeground(new Color(255, 255, 128));
		lblNewLabel.setBounds(278, 22, 435, 59);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 28));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\adminbg.png"));
		lblNewLabel_1.setBounds(10, 0, 1028, 668);
		panel.add(lblNewLabel_1);
	}
}
