package booK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class Apply extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apply frame = new Apply();
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
	public Apply() {
		setTitle("\u6CE8\u518C");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 407);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		panel.setBounds(14, 13, 406, 310);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(14, 53, 125, 47);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(130, 61, 250, 37);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u5BC6\u7801");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		label.setBounds(14, 117, 125, 47);
		panel.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 125, 250, 37);
		panel.add(passwordField);
		
		JLabel label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		label_1.setBounds(14, 187, 125, 47);
		panel.add(label_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(130, 195, 250, 37);
		panel.add(passwordField_1);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String zhang=textField.getText();
				String pass=passwordField.getText();
				String con=passwordField_1.getText();
				if(!pass.equals(con)) JOptionPane.showMessageDialog(null, "两次密码输入不一致！");
				else{
					Conn conn=new Conn();
					conn.getConnection();
					String in="Insert into reader (renumber,repassword) values('"+zhang+"','"+pass+"')";
					try {
						Statement st=conn.con.createStatement();
						st.executeUpdate(in);
						JOptionPane.showMessageDialog(null, "注册成功！");
						dispose();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
				}

				
			}
		});
		button.setBounds(55, 258, 113, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("\u6E05\u7A7A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				passwordField.setText(null);
				passwordField_1.setText(null);
			}
		});
		button_1.setBounds(225, 258, 113, 27);
		panel.add(button_1);
	}
}
