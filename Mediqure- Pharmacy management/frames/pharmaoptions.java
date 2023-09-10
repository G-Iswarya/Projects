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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 1291, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, -15, 1288, 665);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0,0,0,100));
		panel_1.setBounds(241, 104, 809, 488);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("VIEW MEDICINE");
		
		btnNewButton.setBackground(new Color(213, 224, 232));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new medicineInfo(username).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\viewicon.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(49, 94, 235, 89);
		panel_1.add(btnNewButton);
		
		JButton btnUpdateMedicine = new JButton("SELL MEDICINE");
		btnUpdateMedicine.setBackground(new Color(213, 224, 232));
		
		btnUpdateMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new cart(username).setVisible(true);
				setVisible(false);
			}
		});
		btnUpdateMedicine.setIcon(new ImageIcon(pharmaoptions.class.getResource("/images/MEDICINEICON.png")));
		btnUpdateMedicine.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdateMedicine.setBounds(49, 213, 252, 96);
		panel_1.add(btnUpdateMedicine);
		
		JButton btnViewProfile = new JButton("PROFILE");
		btnViewProfile.setBackground(new Color(213, 224, 232));
		btnViewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new pharmaprofile(username).setVisible(true);
				setVisible(false);
			}
		});
		btnViewProfile.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\addusericon.png"));
		btnViewProfile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewProfile.setBounds(416, 84, 288, 109);
		panel_1.add(btnViewProfile);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBackground(new Color(213, 224, 232));
		btnLogout.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\logout.png"));
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
		btnLogout.setBounds(416, 213, 288, 109);
		panel_1.add(btnLogout);
		
		JButton btnChangePassword = new JButton("CHANGE PASSWORD");
		btnChangePassword.setBackground(new Color(213, 224, 232));
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
		btnChangePassword.setBounds(49, 340, 252, 96);
		panel_1.add(btnChangePassword);
		
		JLabel lblNewLabel = new JLabel("WELCOME "+username);
		lblNewLabel.setForeground(new Color(255, 255, 128));
		lblNewLabel.setBounds(425, 24, 435, 59);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 28));
		
		JButton btnBack = new JButton("BACK");
		btnBack.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\backicon.png"));
		btnBack.setBounds(981, 32, 146, 61);
		panel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new entryform().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setIcon(new ImageIcon(pharmaoptions.class.getResource("/images/Reporting on our Progress and ilestones (4).jpg")));
		lblNewLabel_1.setBounds(0, 11, 1278, 643);
		panel.add(lblNewLabel_1);
	}
}
