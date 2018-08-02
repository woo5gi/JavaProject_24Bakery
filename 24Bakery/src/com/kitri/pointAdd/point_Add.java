package com.kitri.pointAdd;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kitri.login.bean.memberBean;
import com.kitri.lookup.User_Lookup_view;

public class point_Add extends JFrame implements ActionListener {

	static JTextField textField = new JTextField();
	JButton ok = new JButton("\uD655\uC778");
	public static int ppp;
	JButton cancel = new JButton("\uB4A4\uB85C\uAC00\uAE30");
	private final JLabel lbl2 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					point_Add window = new point_Add();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public point_Add() {
		getContentPane().setBackground(Color.WHITE);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		this.setBounds(100, 100, 450, 382);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uB8E8\uBE44 \uACB0\uC81C");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(163, 27, 155, 35);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uACB0\uC81C\uD560 \uB8E8\uBE44\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		lblNewLabel_1.setBounds(127, 83, 229, 15);
		getContentPane().add(lblNewLabel_1);

		ok.setBounds(95, 277, 97, 23);
		getContentPane().add(ok);

		textField.setBackground(Color.WHITE);
		textField.setBounds(146, 169, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("");
	
		cancel.setBounds(231, 277, 97, 23);
		getContentPane().add(cancel);
		lbl2.setBounds(127, 225, 201, 15);
		
		getContentPane().add(lbl2);
		
		ok.addActionListener(this);
		textField.addActionListener(this);
		cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == ok) {  
				if(textField.getText().equals(""))
				{
					lbl2.setText("0이상 입력하세요");
					//0이상 입력하셈
				}else {
			memberBean mb = new memberBean();
			mb = pointUd();
			System.out.println(mb.getPoint());
			ppp = Integer.parseInt(textField.getText());
			point_ok po = new point_ok();
			po.setVisible(true);
			this.dispose();
			}
			
		}
		else if(ob == cancel) {
			this.setVisible(false);
			User_Lookup_view ulv = new User_Lookup_view();
			ulv.setVisible(true);
			
		}
	}
	
	public static memberBean pointUd() {
		memberBean mb = new memberBean();
		mb.setPoint(Integer.parseInt(textField.getText()));
		return mb;
	}
	
	public static void downPo() {
	
	}
}
