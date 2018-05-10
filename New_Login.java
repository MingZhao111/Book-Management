package booK;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class New_Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New_Login frame = new New_Login();
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
	public New_Login() {
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 409);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(48, 58, 540, 260);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("幼圆", Font.PLAIN, 15));
		comboBox.setBounds(193, 36, 288, 24);
		comboBox.addItem("管理员");
		comboBox.addItem("借阅人");
		panel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(193, 77, 288, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setFont(new Font("幼圆", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-33, 75, 171, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("幼圆", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(-17, 112, 137, 24);
		panel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("\u9009\u62E9\u8EAB\u4EFD");
		label_1.setFont(new Font("幼圆", Font.PLAIN, 20));
		label_1.setBounds(24, 34, 96, 24);
		panel.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(193, 114, 288, 24);
		panel.add(passwordField);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setFont(new Font("幼圆", Font.PLAIN, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sel=(String) comboBox.getSelectedItem();
				String newpass=null;
				System.out.println(sel);
				if(textField.getText().length()==0||passwordField.getPassword().length==0)
				JOptionPane.showMessageDialog(null, "请输入登录信息！");
				else{
					Conn conn= new Conn();
					boolean judge;
					String zhang=textField.getText();
					String pass=passwordField.getText();
				conn.getConnection();
				if(sel.equals("管理员")){
					try {
						Statement st=conn.con.createStatement();
						String s="SELECT adminpass,CONVERT(VARCHAR(MAX ),CONVERT(VARCHAR(MAX ),DECRYPTBYCERT(CERT_ID('cert_Demo1'),sec,N'123!!!'))) DecryAddress FROM admin";
						ResultSet rs=st.executeQuery(s);
						if(rs.next()) 
						 newpass=rs.getString("adminpass");//解密后密码
						String ma="select * from admin where adminnumber='"+zhang+"' and "+ " adminpass='"+newpass+"'";
						rs=st.executeQuery(ma);

						if(rs.next()) judge=true;
						else judge=false;
						if(judge){	
							dispose();
							new Manager();}
						else JOptionPane.showMessageDialog(null, "输入账号或密码错误！");	
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
				
				}
				if(sel.equals("借阅人")){
					try {
						Statement st=conn.con.createStatement();
						String re="select * from reader where renumber='"+zhang+"' and "+ " repassword='"+pass+"'";
						ResultSet rs=st.executeQuery(re);
						if(rs.next()) judge=true;
						else judge=false;
						if(judge){	
							dispose();
							new Borrower(zhang);}
						else JOptionPane.showMessageDialog(null, "输入账号或密码错误！");	
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}

				}
			}
			}
		});
		button.setBounds(55, 180, 113, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.setFont(new Font("幼圆", Font.PLAIN, 15));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("注册");
				new Apply();
			}
		});
		button_1.setBounds(290, 180, 113, 27);
		panel.add(button_1);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		label.setBounds(191, 13, 287, 44);
		label.setFont(new Font("幼圆", Font.PLAIN, 20));
		contentPane.add(label);
	}
}
