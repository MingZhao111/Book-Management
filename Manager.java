package booK;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Manager extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	String[] columnNames={"编号","书名","作者","出版社","类型","数量","是否有余量"};
	String[] columnNames2={"编号","书名","作者","出版社","数量"};
	String[][] tableValues={};
	private DefaultTableModel tableModel=new DefaultTableModel(tableValues,columnNames);
	private DefaultTableModel tableModel2=new DefaultTableModel(tableValues,columnNames2);
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
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
	public Manager() {
		setTitle("\u7BA1\u7406\u5458\u64CD\u4F5C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 708, 663);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 13, 662, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u56FE\u4E66\u7F16\u53F7");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 13, 123, 18);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(112, 10, 86, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		
		JLabel label = new JLabel("\u591A\u6761\u4EF6\u67E5\u627E");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 61, 123, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u9009\u62E9\u56FE\u4E66\u7C7B\u578B");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(331, 13, 123, 18);
		panel.add(label_1);

		JComboBox<String> jc=new JComboBox<>();
		jc.setBounds(444, 10, 79, 24);
		jc.addItem("计算机");
		jc.addItem("文学");
		jc.addItem("语言");
		jc.addItem("科学");
		jc.addItem("数学");
		panel.add(jc);
		
		JButton button_1 = new JButton("\u67E5\u8BE2");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 117, 662, 328);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String se=(String) jc.getSelectedItem();
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
		button_1.setBounds(532, 9, 99, 27);
		panel.add(button_1);
		
		button.addActionListener(new ActionListener() {
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
		button.setBounds(204, 9, 113, 27);
		panel.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(122, 44, 540, 48);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_2 = new JLabel("\u4E66\u540D");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 17, 43, 18);
		panel_2.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(49, 14, 86, 24);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\u4F5C\u8005");
		label_3.setBounds(149, 17, 30, 18);
		panel_2.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(193, 14, 86, 24);
		panel_2.add(textField_2);
		
		JLabel label_4 = new JLabel("\u51FA\u7248\u793E");
		label_4.setBounds(293, 17, 61, 18);
		panel_2.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(350, 14, 86, 24);
		panel_2.add(textField_3);
		
		JButton button_5 = new JButton("\u67E5\u8BE2");
		button_5.addActionListener(new ActionListener() {
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
		button_5.setBounds(450, 13, 78, 27);
		panel_2.add(button_5);
		
		
		contentPane.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 458, 662, 145);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton button_2 = new JButton("\u5220\u9664\u56FE\u4E66");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int va=table.getSelectedRow();
				String key=(String) tableModel.getValueAt(va,0);
				Conn conn=new Conn();
				conn.getConnection();
				String sql="delete from book where bookno='"+key+"'";
				try {
					Statement st=conn.con.createStatement();
					st.executeUpdate(sql);
					tableModel.removeRow(va);
					JOptionPane.showMessageDialog(null, "删除成功！");
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

			}	
		});
		button_2.setBounds(178, 13, 143, 37);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("\u6DFB\u52A0\u56FE\u4E66");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Add();
				
			}
		});
		button_3.setBounds(14, 13, 143, 37);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("\u501F\u4E66/\u8FD8\u4E66");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int va=table.getSelectedRow();
		
				String key=(String) tableModel.getValueAt(va,0);
				new bore(key);
				
			}
		});
		button_4.setBounds(343, 13, 300, 37);
		panel_1.add(button_4);
		
		JButton button_6 = new JButton("\u6CE8\u9500");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new New_Login();
				

				
			}
		});
		button_6.setBounds(500, 80, 143, 37);
		panel_1.add(button_6);
		
	}
}
