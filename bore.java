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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class bore extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static String key;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bore frame = new bore(key);
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
	public bore(String key) {
		setTitle("\u501F/\u8FD8\u4E66");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u56FE\u4E66\u7F16\u53F7");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(25, 37, 115, 31);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u501F\u9605\u4EBA\u7F16\u53F7");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(25, 95, 115, 31);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(154, 40, 231, 24);
		textField.setText(key);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(154, 98, 231, 24);
		contentPane.add(textField_1);
		
		JButton button = new JButton("\u501F\u4E66");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String zh=textField_1.getText();
				String key1=textField.getText();
				System.out.println(key1);
				Conn conn=new Conn();
				conn.getConnection();
				try {
					Statement st=conn.con.createStatement();
					ResultSet rs=st.executeQuery("select *from book where bookno='"+key1+"'");
					int key2=Integer.parseInt(key1);
					int num=0;
					String flag="0";
					if(rs.next()){         
					num=Integer.parseInt(rs.getString("booknum"));
					flag=rs.getString("flag");
					int newnum=num-1;
					System.out.println(num);
					System.out.println(flag);
					rs=st.executeQuery("select *from reader where reno='"+zh+"'");
					if(rs.next()){
						String no=rs.getString("reno");
						System.out.println(no);
					if(num==0||flag.equals("0")){
						JOptionPane.showMessageDialog(null, "可借数量为0！");
					}
					else{
						st.executeUpdate("Insert into borrow(reno,bookno) values ('"+no+"','"+key2+"')");
						st.executeUpdate("Update book set booknum='"+newnum+"' where bookno='"+key2+"'");
						JOptionPane.showMessageDialog(null, "借书成功！");
					}
					}
					else{
						JOptionPane.showMessageDialog(null, "未查到此用户！");
					}
				}
				}catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		button.setBounds(60, 160, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u8FD8\u4E66");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String zh=textField_1.getText();
				String key1=textField.getText();
				System.out.println(key1);  //书号
				Conn conn=new Conn();
				conn.getConnection();
				try {
					Statement st=conn.con.createStatement();
					ResultSet rs=st.executeQuery("select *from book where bookno='"+key1+"'");
					int key2=Integer.parseInt(key1);
					int num=0;
					String flag="0";
					if(rs.next()){         
					num=Integer.parseInt(rs.getString("booknum"));
					flag=rs.getString("flag");
					int newnum=num+1;
					System.out.println(num);
					System.out.println(flag);
					rs=st.executeQuery("select *from reader where reno='"+zh+"'");
					if(rs.next()){
						String no=rs.getString("reno");
						System.out.println(no);
					
				
						st.executeUpdate("delete from borrow where reno='"+no+"' AND bookno='"+key2+"'");
						if(flag.equals("1")){
							st.executeUpdate("Update book set booknum='"+newnum+"' where bookno='"+key2+"'");
						}
						else{
							st.executeUpdate("Update book set booknum='"+newnum+"',flag=1 where bookno='"+key2+"'");
						}
						JOptionPane.showMessageDialog(null, "还书成功！");
					
					}
					else{
						JOptionPane.showMessageDialog(null, "未查到此用户！");
					}
				}
				}catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(224, 160, 113, 27);
		contentPane.add(button_1);
	}

}
