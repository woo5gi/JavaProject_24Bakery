package com.kitri.secession;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.kitri.lookup.User_Lookup_view;

public class User_Secessions extends JFrame {

	public JPanel contentPane;
	public JTextField textField;
	public JTextField idTxf;
	public JButton success;
	public JButton exit;
	public JPasswordField passwordField;
	public JLabel label_1;

	

	
	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Secessions frame = new User_Secessions();
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
	public User_Secessions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uD68C\uC6D0 \uD0C8\uD1F4");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label.setBounds(183, 32, 121, 40);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel.setBounds(45, 112, 62, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_1.setBounds(45, 165, 62, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uD0C8\uD1F4 \uC0AC\uC720");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_2.setBounds(28, 251, 62, 18);
		contentPane.add(lblNewLabel_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uC7AC\uAC00\uC785\uC744 \uC704\uD574\uC11C");
		rdbtnNewRadioButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rdbtnNewRadioButton.setBounds(55, 277, 139, 27);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("\uC774\uC6A9 \uBD88\uD3B8");
		radioButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		radioButton.setBounds(55, 307, 139, 27);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\uAE30\uD0C0");
		radioButton_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		radioButton_1.setBounds(55, 368, 139, 27);
		contentPane.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("\uC7A5\uAE30\uAC04 \uBD80\uC7AC");
		radioButton_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		radioButton_2.setBounds(55, 338, 139, 27);
		contentPane.add(radioButton_2);
	
		JLabel lblNewLabel_3 = new JLabel("\uAE30\uD0C0\uC0AC\uC720");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_3.setBounds(55, 425, 79, 40);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(78, 461, 306, 243);
		contentPane.add(textField);
		textField.setColumns(10);
		
		idTxf = new JTextField();
		idTxf.setColumns(10);
		idTxf.setBounds(136, 109, 170, 24);
		contentPane.add(idTxf);
		idTxf.setFocusable(false);		
		
		success = new JButton("\uD0C8\uD1F4");
		success.setBorderPainted(false);
		success.setBackground(new Color(255, 204, 102));
		success.setBounds(107, 716, 105, 27);
		contentPane.add(success);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 162, 170, 21);
		contentPane.add(passwordField);
		
		exit = new JButton("\uCDE8\uC18C");
		exit.setBorderPainted(false);
		exit.setBackground(new Color(255, 204, 102));
		exit.setBounds(241, 716, 105, 27);
		contentPane.add(exit);
		
		label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD558\uC138\uC694.");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_1.setBounds(136, 195, 170, 18);
		contentPane.add(label_1);
		label_1.setVisible(false);
		
	}
}
