package frames;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class adminoptions extends JFrame {
	private String username;
	private JPanel contentPane;
	private JPanel panel_2;
	private JButton btnLogout;
	private JLabel lblWelcomeUser;
	private final JLabel lblNewLabel = new JLabel("");

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public adminoptions(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1468, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 11, 1021, 651);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 240, 240));
		panel_2.setBounds(67, 74, 896, 523);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("USERS INFO");
		btnNewButton.setIcon(new ImageIcon(adminoptions.class.getResource("/images/addusericon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new useroption(username).setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(499, 197, 296, 101);
		panel_2.add(btnNewButton);
		
		JButton btnAddMedicine = new JButton("MEDICINE INFO");
		btnAddMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new medicineInfo(username).setVisible(true);
				setVisible(false);
			}
		});
		btnAddMedicine.setIcon(new ImageIcon(adminoptions.class.getResource("/images/MEDICINEICON.png")));
		btnAddMedicine.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddMedicine.setBounds(501, 47, 294, 96);
		panel_2.add(btnAddMedicine);
		
		JButton btnAddSupplier = new JButton("SUPPLIER INFO");
		btnAddSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new supplierinfo(username).setVisible(true);
				dispose();
			}
		});
		btnAddSupplier.setIcon(new ImageIcon(adminoptions.class.getResource("/images/addusericon.png")));
		btnAddSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddSupplier.setBounds(82, 199, 276, 96);
		panel_2.add(btnAddSupplier);
		
		JButton btnProfile = new JButton("PROFILE");
		btnProfile.setIcon(new ImageIcon(adminoptions.class.getResource("/images/profile.png")));
		btnProfile.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProfile.setBounds(82, 42, 276, 101);
		panel_2.add(btnProfile);
		
		JButton btnChangePassword = new JButton("CHANGE PASSWORD");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new changePwd(username).setVisible(true);
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChangePassword.setBounds(82, 346, 276, 96);
		panel_2.add(btnChangePassword);
		
		JButton btnViewSalesHistory = new JButton("VIEW SALES HISTORY");
		btnViewSalesHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new salesdata(username).setVisible(true);
			}
		});
		btnViewSalesHistory.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\profile.png"));
		btnViewSalesHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewSalesHistory.setBounds(499, 344, 296, 101);
		panel_2.add(btnViewSalesHistory);
		
		btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to logout?");
				if(a == 0)
				{
				new entryform().setVisible(true);
				setVisible(false);
				}}}
		);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogout.setBounds(870, 11, 141, 52);
		panel.add(btnLogout);
		
		lblWelcomeUser = new JLabel("WELCOME ADMIN");
		lblWelcomeUser.setForeground(new Color(255, 255, 128));
		lblWelcomeUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeUser.setFont(new Font("Arial", Font.BOLD, 27));
		lblWelcomeUser.setBounds(312, 4, 435, 59);
		panel.add(lblWelcomeUser);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\iswarya.g\\Downloads\\adminbg.png"));
		lblNewLabel.setBounds(-23, -15, 1224, 666);
		panel.add(lblNewLabel);
	}
}
