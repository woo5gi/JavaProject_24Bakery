package com.kitri.update;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.kitri.domain.UserBean;
import com.kitri.lookup.User_Lookup_cont;
import com.kitri.lookup.User_Lookup_view;
import com.kitri.model.UserDao;

public class User_Update extends JFrame{

	public JPanel contentPane;
	public JTextField emailTxf;
	public JTextField nameTxf;
	
	public JTextField phoneTxf;
	public JButton bt3; 
	
	UserBean ub = new UserBean();
	//static User_Lookup_cont ulc = new User_Lookup_cont();
	//static User_Lookup_view uv;
	UserDao ud = new UserDao();
//	public JTextField comfile;
	public JLabel label_5;
	public JTextField adresTxf;
	public JButton bt1;
	public JTextField idTxf;
	public JTextField gender;
	public JPasswordField passwordField;
	public JLabel lblNewLabel;
	public JLabel lb_inform;
	
	
	public User_Update() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label.setBounds(49, 163, 62, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\uC774\uB984");
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_1.setBounds(49, 219, 62, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\uC544\uC774\uB514");
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_2.setBounds(49, 111, 62, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\uC131\uBCC4");
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_3.setBounds(49, 402, 62, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\uC804\uD654\uBC88\uD638");
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_4.setBounds(49, 358, 62, 18);
		contentPane.add(label_4);
		
		JLabel emaillabel = new JLabel("\uC774\uBA54\uC77C");
		emaillabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		emaillabel.setBounds(49, 257, 62, 18);
		contentPane.add(emaillabel);
		
		idTxf = new JTextField();
		idTxf.setBounds(147, 108, 194, 24);
		contentPane.add(idTxf);
		idTxf.setColumns(10);
		idTxf.setFocusable(false);
		

		emailTxf = new JTextField();
		emailTxf.setColumns(10);
		emailTxf.setBounds(147, 254, 225, 24);
		contentPane.add(emailTxf);
		
		nameTxf = new JTextField();
		nameTxf.setColumns(10);
		nameTxf.setBounds(147, 216, 116, 24);
		contentPane.add(nameTxf);
		
		phoneTxf = new JTextField();
		phoneTxf.setColumns(10);
		phoneTxf.setBounds(147, 355, 225, 24);
		contentPane.add(phoneTxf);
		
		bt1 = new JButton("\uC800\uC7A5");	//¼öÁ¤
		bt1.setBorderPainted(false);
		bt1.setBackground(new Color(255, 204, 102));
		bt1.setBounds(104, 495, 105, 27);
		contentPane.add(bt1);
		
		bt3 = new JButton("\uCDE8\uC18C");	//Ãë¼Ò
		bt3.setBorderPainted(false);
		bt3.setBackground(new Color(255, 204, 102));
		bt3.setBounds(239, 495, 105, 27);
		contentPane.add(bt3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(147, 160, 194, 24);
		contentPane.add(passwordField);
		

//		comfile = new JTextField();	// ¼öÁ¤ ¿Ï·á ¸Þ¼¼Áö
//		comfile.setBounds(104, 511, 242, 24);
//		contentPane.add(comfile);
//		comfile.setColumns(10);
	
		getContentPane().add(bt1);
		getContentPane().add(bt3);
		
		label_5 = new JLabel("\uC8FC\uC18C");
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_5.setBounds(49, 309, 62, 18);
		contentPane.add(label_5);
		
		adresTxf = new JTextField();	// ÁÖ¼Ò ÅØ½ºÆ®
		adresTxf.setColumns(10);
		adresTxf.setBounds(147, 306, 225, 24);
		contentPane.add(adresTxf);
		
		gender = new JTextField();
		gender.setBounds(147, 399, 116, 24);
		contentPane.add(gender);
		gender.setColumns(10);
		
		lblNewLabel = new JLabel("\uD68C\uC6D0 \uC815\uBCF4 \uC218\uC815");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblNewLabel.setBounds(147, 29, 194, 45);
		contentPane.add(lblNewLabel);
		
		lb_inform = new JLabel("\uC815\uBCF4\uB97C \uC785\uB825\uD574 \uC8FC\uC138\uC694.");
		lb_inform.setForeground(Color.RED);
		lb_inform.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lb_inform.setBounds(122, 435, 223, 34);
		contentPane.add(lb_inform);
		lb_inform.setVisible(false);
		
		
		
		
		
		
	}
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Update frame = new User_Update();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the frame.
	 * @return 
	 */
	public void User_Update1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}



	public String empinput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s_code = "";
		
		try {
			s_code = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return s_code;
	}


}








