package booK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Borrower extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static String zhang;
	private JTable table;
	String[] columnNames={"编号","书名","作者","出版社","类型","数量","是否有余量"};
	String[] columnNames2={"书号","书名","作者","出版社","数量"};
	String[] columnNames3={"借阅人编号","书号","书名","作者","出版社"};
	String[][] tableValues={};
	private DefaultTableModel tableModel=new DefaultTableModel(tableValues,columnNames);
	private DefaultTableModel tableModel2=new DefaultTableModel(tableValues,columnNames2);
	private DefaultTableModel tableModel3=new DefaultTableModel(tableValues,columnNames3);
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrower frame = new Borrower(zhang);
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
	public Borrower(String zhang) {
		setTitle("\u501F\u9605\u4EBA\u64CD\u4F5C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 563);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 13, 662, 92);
		contentPane.add(panel);
		
		JLabel label = new JLabel("\u8F93\u5165\u56FE\u4E66\u7F16\u53F7");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 13, 123, 18);
		panel.add(label);
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		textField.setBounds(112, 10, 86, 24);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("\u591A\u6761\u4EF6\u67E5\u627E");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 61, 123, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u9009\u62E9\u56FE\u4E66\u7C7B\u578B");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(331, 13, 123, 18);
		panel.add(label_2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(444, 10, 79, 24);
		comboBox.addItem("计算机");
		comboBox.addItem("文学");
		comboBox.addItem("语言");
		comboBox.addItem("科学");
		comboBox.addItem("数学");
		panel.add(comboBox);
		
		JButton button = new JButton("\u67E5\u8BE2");

		
		JButton button_1 = new JButton("\u67E5\u8BE2");
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 114, 662, 328);
		contentPane.add(scrollPane);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String se=(String) comboBox.getSelectedItem();
				String sql="select* from book where booktype='"+ se+"' ";
				Conn conn=new Conn();
				conn.getConnection();
				try {
					Statement st=conn.con.createStatement();
					ResultSet rs=st.executeQuery(sql);
					table=new JTable(tableModel);
					tableModel.setRowCount(0);
					table.removeAll();
					table.setRowSorter(new TableRowSorter<>(tableModel));
					table.setRowHeight(30);
					while(rs.next()){
						String bookno=rs.getString("bookno");
						String bookname=rs.getString("bookname");
						String bookwriter=rs.getString("bookwriter");
						String bookpublish=rs.getString("bookpublish");
						String booktype=rs.getString("booktype");
						String booknum=rs.getString("booknum");
						String flag=rs.getString("flag");
						String[] rowValues={bookno,bookname,bookwriter,bookpublish,booktype,booknum,flag};
						
						tableModel.addRow(rowValues);
						scrollPane.setViewportView(table);
					}
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		});
		button.setBounds(532, 9, 99, 27);
		panel.add(button);
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

					String in=textField.getText();
					String sql="select *from book where bookno='"+in+"'";
					table=new JTable(tableModel);
					tableModel.setRowCount(0);
					table.removeAll();
					table.setRowSorter(new TableRowSorter<>(tableModel));
					table.setRowHeight(30);
					Conn conn=new Conn();
					conn.getConnection();
					try {
						Statement st=conn.con.createStatement();
						ResultSet rs=st.executeQuery(sql);
						while(rs.next()){
							String bookno=rs.getString("bookno");
							String bookname=rs.getString("bookname");
							String bookwriter=rs.getString("bookwriter");
							String bookpublish=rs.getString("bookpublish");
							String booktype=rs.getString("booktype");
							String booknum=rs.getString("booknum");
							String flag=rs.getString("flag");
							String[] rowValues={bookno,bookname,bookwriter,bookpublish,booktype,booknum,flag};
							
							tableModel.addRow(rowValues);
							scrollPane.setViewportView(table);
						}
						
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			}
		});
		button_1.setBounds(204, 9, 113, 27);
		panel.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(122, 44, 540, 48);
		panel.add(panel_2);
		
		JLabel label_3 = new JLabel("\u4E66\u540D");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 17, 43, 18);
		panel_2.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(49, 14, 86, 24);
		panel_2.add(textField_1);
		
		JLabel label_4 = new JLabel("\u4F5C\u8005");
		label_4.setBounds(149, 17, 30, 18);
		panel_2.add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(193, 14, 86, 24);
		panel_2.add(textField_2);
		
		JLabel label_5 = new JLabel("\u51FA\u7248\u793E");
		label_5.setBounds(293, 17, 61, 18);
		panel_2.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(350, 14, 86, 24);
		panel_2.add(textField_3);
		
		JButton button_4 = new JButton("\u67E5\u8BE2");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					String nm=textField_1.getText();
					String wr=textField_2.getText();
					String pu=textField_3.getText();
					if(nm.length()==0&&wr.length()==0&&pu.length()==0){
				JOptionPane.showMessageDialog(null, "请输入查询条件！");
			}
					else{
						Conn conn = new Conn();
						conn.getConnection();
						table=new JTable(tableModel2);
						tableModel2.setRowCount(0);
						table.removeAll();
						table.setRowSorter(new TableRowSorter<>(tableModel2));
						table.setRowHeight(30);
						if(nm.length()!=0&&wr.length()!=0&&pu.length()!=0){
						try {
							Statement st=conn.con.createStatement();
							ResultSet rs=st.executeQuery("select *from duo where bookname LIKE '"+nm+"%"+"' AND bookwriter LIKE '"+wr+"%"+"' AND bookpublish LIKE '"+pu+"%"+"'");
							while(rs.next()){
								String s1=rs.getString("bookno");
								String s2=rs.getString("bookname");
								String s3=rs.getString("bookwriter");
								String s4=rs.getString("bookpublish");
								String s5=rs.getString("booknum");
								String[] rowValues={s1,s2,s3,s4,s5};
								tableModel2.addRow(rowValues);
								scrollPane.setViewportView(table);
							}
						} catch (SQLException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						}
						else if(nm.length()!=0&&wr.length()==0&&pu.length()==0){
							try {
								Statement st=conn.con.createStatement();
								ResultSet rs=st.executeQuery("select *from duo where bookname LIKE '"+nm+"%"+"'");
								while(rs.next()){
									String s1=rs.getString("bookno");
									String s2=rs.getString("bookname");
									String s3=rs.getString("bookwriter");
									String s4=rs.getString("bookpublish");
									String s5=rs.getString("booknum");
									String[] rowValues={s1,s2,s3,s4,s5};
									tableModel2.addRow(rowValues);
									scrollPane.setViewportView(table);
								}
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							}
						else if(nm.length()==0&&wr.length()!=0&&pu.length()==0){
							try {
								Statement st=conn.con.createStatement();
								ResultSet rs=st.executeQuery("select *from duo where bookwriter LIKE '"+wr+"%"+"'");
								while(rs.next()){
									String s1=rs.getString("bookno");
									String s2=rs.getString("bookname");
									String s3=rs.getString("bookwriter");
									String s4=rs.getString("bookpublish");
									String s5=rs.getString("booknum");
									String[] rowValues={s1,s2,s3,s4,s5};
									tableModel2.addRow(rowValues);
									scrollPane.setViewportView(table);
								}
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							}
						else if(nm.length()==0&&wr.length()==0&&pu.length()!=0){
							try {
								Statement st=conn.con.createStatement();
								ResultSet rs=st.executeQuery("select *from duo where bookpublish LIKE '"+pu+"%"+"'");
								while(rs.next()){
									String s1=rs.getString("bookno");
									String s2=rs.getString("bookname");
									String s3=rs.getString("bookwriter");
									String s4=rs.getString("bookpublish");
									String s5=rs.getString("booknum");
									String[] rowValues={s1,s2,s3,s4,s5};
									tableModel2.addRow(rowValues);
									scrollPane.setViewportView(table);
								}
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							}
						else if(nm.length()!=0&&wr.length()!=0&&pu.length()==0){
							try {
								Statement st=conn.con.createStatement();
								ResultSet rs=st.executeQuery("select *from duo where bookname LIKE '"+nm+"%"+"' AND bookwriter LIKE '"+wr+"%"+"'");
								while(rs.next()){
									String s1=rs.getString("bookno");
									String s2=rs.getString("bookname");
									String s3=rs.getString("bookwriter");
									String s4=rs.getString("bookpublish");
									String s5=rs.getString("booknum");
									String[] rowValues={s1,s2,s3,s4,s5};
									tableModel2.addRow(rowValues);
									scrollPane.setViewportView(table);
								}
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							}
						else if(nm.length()==0&&wr.length()!=0&&pu.length()!=0){
							try {
								Statement st=conn.con.createStatement();
								ResultSet rs=st.executeQuery("select *from duo where bookwriter LIKE '"+wr+"%"+"' AND bookpublish LIKE '"+pu+"%"+"'");
								while(rs.next()){
									String s1=rs.getString("bookno");
									String s2=rs.getString("bookname");
									String s3=rs.getString("bookwriter");
									String s4=rs.getString("bookpublish");
									String s5=rs.getString("booknum");
									String[] rowValues={s1,s2,s3,s4,s5};
									tableModel2.addRow(rowValues);
									scrollPane.setViewportView(table);
								}
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							}
					}
			}
		});
		button_4.setBounds(450, 13, 78, 27);
		panel_2.add(button_4);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 455, 643, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton button_2 = new JButton("\u4FEE\u6539\u5BC6\u7801");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Change(zhang);
			}
		});
		button_2.setBounds(14, 13, 135, 27);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("\u67E5\u770B\u5DF2\u501F\u56FE\u4E66");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conn conn=new Conn();
				conn.getConnection();
				table=new JTable(tableModel3);
				tableModel3.setRowCount(0);
				table.removeAll();
				table.setRowSorter(new TableRowSorter<>(tableModel3));
				table.setRowHeight(30);
				try {
					Statement st=conn.con.createStatement();
					ResultSet rs=st.executeQuery("select* from shitu2 where renumber='"+zhang+"'");
					while(rs.next()){
						String ren=rs.getString("reno");
						String bon=rs.getString("bookno");
						String bona=rs.getString("bookname");
						String bow=rs.getString("bookwriter");
						String bot=rs.getString("bookpublish");
						String[] rowValues={ren,bon,bona,bow,bot};

						tableModel3.addRow(rowValues);
						scrollPane.setViewportView(table);
					
				
				
					}
					
							
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		button_3.setBounds(163, 13, 135, 27);
		panel_1.add(button_3);
		
		JButton button_5 = new JButton("\u6CE8\u9500");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new New_Login();
				
			}
		});
		button_5.setBounds(312, 13, 317, 27);
		panel_1.add(button_5);
	}
}
