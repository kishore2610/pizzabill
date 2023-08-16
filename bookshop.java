package bookshop;

import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class bookshop {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookshop window = new bookshop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public bookshop() {
		initialize();
		connect();
		tableload();
	}
	
	
	
	Connection con;
	PreparedStatement pat;
	DefaultTableModel d;
	
	public void connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/bookshop","root","");
			System.out.println("connected");
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
		
	}
	
	public void tableload()
	{
		try {
			pat=con.prepareStatement("select * from book");
			ResultSet rs=pat.executeQuery();
			String[] columnNames = {"ID", "Name", "Edition", "Price"};
			d = new DefaultTableModel(columnNames, 0);
			table.setModel(d);

			while (rs.next()) {
			    Vector<String> rowData = new Vector<>();
			    rowData.add(rs.getString("id"));
			    rowData.add(rs.getString("name"));
			    rowData.add(rs.getString("edition"));
			    rowData.add(rs.getString("price"));
			    d.addRow(rowData);
			}
	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 675, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(274, 0, 156, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(38, 61, 289, 194);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(26, 34, 69, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(26, 89, 69, 28);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(26, 146, 69, 28);
		panel.add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setBounds(127, 38, 126, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 93, 126, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(127, 150, 126, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname=textField.getText();
			    edition=textField_1.getText();
				price=textField_2.getText();
				
				try {
					pat=con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
			        pat.setString(1,bname);
			        pat.setString(2,edition);
			        pat.setString(3,price);
			        pat.executeUpdate();
			        JOptionPane.showMessageDialog(null,"Record Addeddddd!!!!!");
			        tableload();
			        
			   
			        textField.setText("");
			        textField_1.setText("");
			        textField_2.setText("");
			        textField.requestFocus();
			       
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(38, 276, 89, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(139, 276, 89, 50);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
		        textField_1.setText("");
		        textField_2.setText("");
		        textField_3.setText("");
		        textField.requestFocus();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(238, 276, 89, 50);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(357, 60, 294, 272);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(38, 366, 289, 73);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String id= textField_3.getText();
					pat=con.prepareStatement("select name,edition,price from book where id=?");
					pat.setString(1, id);
					ResultSet rs=pat.executeQuery();
					
					if(rs.next()==true)
					{
						String name=rs.getString(1);
						String edition=rs.getString(2);
						String price=rs.getString(3);
						
						textField.setText(name);
				        textField_1.setText(edition);
				        textField_2.setText(price);
						
					}
					else {
						textField.setText("");
				        textField_1.setText("");
				        textField_2.setText("");
					}
					
					
				}
				catch(SQLException ex) {
					
				}
				
				
				
				
				
				
			}
		});
		textField_3.setBounds(133, 27, 131, 23);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(21, 30, 72, 20);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton_2_1 = new JButton("Update");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,id;
				bname=textField.getText();
			    edition=textField_1.getText();
				price=textField_2.getText();
				id=textField_3.getText();
				
				try {
					pat=con.prepareStatement("update book set name=?,edition=?,price=? where id=? ");
			        pat.setString(1,bname);
			        pat.setString(2,edition);
			        pat.setString(3,price);
			        pat.setString(4,id);
			        pat.executeUpdate();
			        JOptionPane.showMessageDialog(null,"Record updatedddd!!!!!");
			        tableload();
			        
			        textField.setText("");
			        textField_1.setText("");
			        textField_2.setText("");
			        textField.requestFocus();
			       
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2_1.setBounds(385, 366, 89, 50);
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Delete");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id;
				id=textField_3.getText();
				
				try {
					pat=con.prepareStatement("delete from book where id=? ");
			        pat.setString(1,id);
			        pat.executeUpdate();
			        JOptionPane.showMessageDialog(null,"Record deletedddd!!!!!");
			        tableload();
			        
			        textField.setText("");
			        textField_1.setText("");
			        textField_2.setText("");
			        textField.requestFocus();
			       
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2_2.setBounds(514, 366, 89, 50);
		frame.getContentPane().add(btnNewButton_2_2);
	}
}
