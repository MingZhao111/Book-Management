package booK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Change extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private static String zhang;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Change frame = new Change(zhang);
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
	public Change(String zhang) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 305);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(51, 42, 123, 33);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(51, 105, 123, 33);
		contentPane.add(label_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(188, 44, 237, 29);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(188, 107, 237, 29);
		contentPane.add(passwordField_2);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				String new1=passwordField_1.getText();
				String new2=passwordField_2.getText();
				System.out.println(zhang);
				if(!new1.equals(new2)){
					JOptionPane.showMessageDialog(null, "两次输入不一致！");
				}
				else{
					Conn conn=new Conn();
					conn.getConnection();
					try {
						Statement st=conn.con.createStatement();

								st.executeUpdate("update reader set repassword='"+new1+"'where renumber='"+zhang+"'");
								JOptionPane.showMessageDialog(null, "修改成功！");
								dispose();
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		});
		button.setBounds(81, 182, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u6E05\u7A7A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				passwordField_1.setText(null);
				passwordField_2.setText(null);
			}
		});
		button_1.setBounds(274, 182, 113, 27);
		contentPane.add(button_1);
	}
}
