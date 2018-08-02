package com.kitri.lookup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.kitri.bakery.domain.BakeryBean;
import com.kitri.domain.UserBean;
import com.kitri.model.UserDao;
import com.kitri.secession.User_Secession_Msg;
import com.kitri.secession.User_Secessions;

import com.kitri.update.User_Update;
import com.kitri.update.User_Update_Msg;


public class User_Lookup_view extends JFrame {

		
		public JTextField idTxf;
		public JTextField passTxf;
		public JTextField emailTxf;
		public JTextField nameTxf;
		public JPanel contentPane;
		public JTextField phoneTxf;
		public JButton bt1;
		public JButton bt2;
		public JButton bt3;
		public JTextField textField_1;
		public JLabel address;
		public User_Update up;
		public JTextField gender1;
		public User_Secessions us;
		public JPasswordField passwordField;

		User_Secession_Msg usm = new User_Secession_Msg();
		User_Update_Msg uum = new User_Update_Msg();
		UserBean ub = new UserBean();
	//	static User_Lookup_cont ulc = new User_Lookup_cont();	// ÀÌ³ðÀÌ cont¿¡µµ ÀÖ°í view¿¡µµ Àü¿ª º¯¼ö·Î ÀÖ¾î¼­ Ã¢ÀÌ µÎ°³ ¶¹´Ù.
		
		UserDao ud = new UserDao();
		public JLabel lb_Title;
		public JTextField Luby;
		public JButton LubyCharge;
		
		
	
		
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					User_Lookup_view uv = new User_Lookup_view();
					try {
						uv = new User_Lookup_view();
						uv.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			);
//			System.out.println(ulc.User_Lookup_cont());
			
			
		}

		
		

		
		
		/**
		 * Create the frame.
		 */
		public User_Lookup_view() {
			ud.getAllInfo();
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 500, 600);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 255, 255));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel label = new JLabel("\uC544\uC774\uB514");
			label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			label.setBounds(69, 103, 62, 18);
			contentPane.add(label);
			
			JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
			label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			label_1.setBounds(69, 147, 62, 18);
			contentPane.add(label_1);
			
			JLabel label_2 = new JLabel("\uC774\uB984");
			label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			label_2.setBounds(69, 204, 62, 18);
			contentPane.add(label_2);
			
			JLabel label_3 = new JLabel("\uC131\uBCC4");
			label_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			label_3.setBounds(69, 395, 62, 18);
			contentPane.add(label_3);
			
			JLabel label_4 = new JLabel("\uC804\uD654\uBC88\uD638");
			label_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			label_4.setBounds(69, 351, 62, 18);
			contentPane.add(label_4);
			
			JLabel emaillabel = new JLabel("\uC774\uBA54\uC77C");
			emaillabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			emaillabel.setBounds(69, 258, 62, 18);
			contentPane.add(emaillabel);
			
			idTxf = new JTextField(ud.list.get(0).getMember_id());	// id
			idTxf.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			idTxf.setBounds(170, 100, 167, 24);
			contentPane.add(idTxf);
			idTxf.setColumns(10);
			idTxf.setFocusable(false);
			
			
			emailTxf = new JTextField(ud.list.get(0).getE_mail());		//email
			emailTxf.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			emailTxf.setColumns(10);
			emailTxf.setBounds(170, 260, 246, 24);
			contentPane.add(emailTxf);
			emailTxf.setFocusable(false);
			
			
			nameTxf = new JTextField(ud.list.get(0).getMem_name());
			nameTxf.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			nameTxf.setColumns(10);
			nameTxf.setBounds(170, 204, 116, 24);
			contentPane.add(nameTxf);
			nameTxf.setFocusable(false);
//			Luby.setFocusable(false);
			
			
			phoneTxf = new JTextField(ud.list.get(0).getPhone_number());
			phoneTxf.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			phoneTxf.setColumns(10);
			phoneTxf.setBounds(170, 349, 246, 24);
			contentPane.add(phoneTxf);
			phoneTxf.setFocusable(false);
			
			
			Luby = new JTextField("" + ud.list.get(0).getPoint());
			Luby.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			Luby.setColumns(10);
			Luby.setBounds(170, 438, 116, 24);
			contentPane.add(Luby);
			Luby.setFocusable(false);
			
			
			
			bt1 = new JButton("\uC218\uC815");
			bt1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
			bt1.setBorderPainted(false);
			bt1.setBackground(new Color(255, 204, 102));
			bt1.setBounds(50, 472, 105, 27);
			contentPane.add(bt1);
			
			bt2 = new JButton("\uD0C8\uD1F4");	//Å»Åð
			bt2.setBorderPainted(false);
			bt2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
			bt2.setBackground(new Color(255, 204, 102));
			bt2.setBounds(181, 472, 105, 27);
			contentPane.add(bt2);
			
			bt3 = new JButton("\uCDE8\uC18C");	//Ãë¼Ò
			bt3.setBorderPainted(false);
			bt3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
			bt3.setBackground(new Color(255, 204, 102));
			bt3.setBounds(311, 472, 105, 27);
			contentPane.add(bt3);
			
			getContentPane().add(bt1);
			getContentPane().add(bt2);
			getContentPane().add(bt3);
			
			address = new JLabel("\uC8FC\uC18C");	// ÁÖ¼Ò ¶óº§
			address.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			address.setBounds(69, 307, 62, 18);
			contentPane.add(address);
			
			
			
			textField_1 = new JTextField(ud.list.get(0).getAddress());	// ÁÖ¼Ò
			textField_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			textField_1.setBounds(170, 304, 246, 24);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
			textField_1.setFocusable(false);
			
			gender1 = new JTextField(ud.list.get(0).getGender());	//¼ºº°
			gender1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			gender1.setBounds(170, 392, 116, 24);
			contentPane.add(gender1);
			gender1.setColumns(10);
			gender1.setFocusable(false);
			
			passwordField = new JPasswordField(ud.list.get(0).getMem_pw());	// Á¶È¸Ã¢ passÇÊµå	// ulvÀÇ pass ÇÊµå¿¡ µ¥ÀÌÅÍ¸¦ ³Ö´Â´Ù.
			passwordField.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			passwordField.setBounds(170, 144, 167, 24);
			contentPane.add(passwordField);
			passwordField.setFocusable(false);
			
			lb_Title = new JLabel("\uD68C\uC6D0 \uC815\uBCF4 \uC870\uD68C");
			lb_Title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			lb_Title.setBounds(156, 26, 181, 55);
			contentPane.add(lb_Title);
			
			JLabel label_5 = new JLabel("\uB8E8\uBE44 \uC794\uC561");
			label_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			label_5.setBounds(69, 443, 57, 15);
			contentPane.add(label_5);
			
			LubyCharge = new JButton("\uB8E8\uBE44 \uCDA9\uC804");
			LubyCharge.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
			LubyCharge.setBorderPainted(false);
			LubyCharge.setBackground(new Color(255, 204, 102));
			LubyCharge.setBounds(311, 435, 105, 27);
			contentPane.add(LubyCharge);
			if (BakeryBean.member_id == "admin") {
				label_5.setVisible(false);
				Luby.setVisible(false);
				LubyCharge.setVisible(false);
			}
			
			User_Lookup_cont ulc = new User_Lookup_cont(this);	// ulc °´Ã¼¸¦ ºÒ·¯¿À´Âµ¥ ulc¿¡¼­ »ý¼ºÀÚ¸¦ ºÒ·¯¿À´Â °Í ÀÌ´Ù. ±×·¡¼­ this´Â ulv ÀÌ´Ù.
			System.out.println("test userLookup");
			bt1.addActionListener(ulc);			// ulc Å¬·¡½º¿¡ ¾×¼Ç¸®½º³Ê¸¦ ºÎ¿©ÇÏ´Â °Í
			bt2.addActionListener(ulc);
			bt3.addActionListener(ulc);
			
			
			us = new User_Secessions();
			us.exit.addActionListener(ulc);
			us.success.addActionListener(ulc);
			
			up = new User_Update();
			up.bt1.addActionListener(ulc);
			up.bt3.addActionListener(ulc);
			
			usm = new User_Secession_Msg();
			usm.bt_exit.addActionListener(ulc);
			
			uum = new User_Update_Msg();
			uum.btnNewButton.addActionListener(ulc);
			LubyCharge.addActionListener(ulc);
			
			
			
			
		}
	}













