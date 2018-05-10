package booK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Add extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add frame = new Add();
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
	public Add() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 424);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 13, 406, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4E66\u540D");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(14, 28, 83, 24);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u4F5C\u8005");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(14, 76, 83, 24);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u51FA\u7248\u793E");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(14, 119, 83, 24);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u7C7B\u578B");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(14, 161, 83, 24);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u6570\u91CF");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(14, 203, 83, 24);
		panel.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(127, 25, 245, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(127, 73, 245, 24);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(127, 116, 245, 24);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(127, 158, 245, 24);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(127, 200, 245, 24);
		panel.add(textField_4);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1=textField.getText();
				String s2=textField_1.getText();
				String s3=textField_2.getText();
				String s4=textField_3.getText();
				String s5=textField_4.getText();
				String sql="Insert into book(bookname,bookwriter,bookpublish,booktype,booknum,flag) values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"',1)";
				String sql2="Insert into book(bookname,bookwriter,bookpublish,booktype,booknum,flag) values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"',0)";
				if(s1.length()==0||s2.length()==0||s3.length()==0||s4.length()==0||s5.length()==0){JOptionPane.showMessageDialog(null, "请输入完整信息！");}
				else{
					Conn conn=new Conn();
				
				conn.getConnection();
				try {
					Statement st=conn.con.createStatement();
					if(!s5.equals("0"))
					st.executeUpdate(sql);
					else st.executeUpdate(sql2);
					JOptionPane.showMessageDialog(null, "添加图书成功！");
					dispose();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				}
			}
		});
		button.setBounds(51, 274, 113, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("\u6E05\u7A7A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
			}
		});
		button_1.setBounds(212, 274, 113, 27);
		panel.add(button_1);
	}
}
