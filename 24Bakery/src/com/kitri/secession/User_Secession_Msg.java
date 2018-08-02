package com.kitri.secession;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class User_Secession_Msg extends JFrame {

	public JPanel contentPane;
	public JButton bt_exit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Secession_Msg frame = new User_Secession_Msg();
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
	public User_Secession_Msg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uADF8\uB3D9\uC548 \uC774\uC6A9\uD574\uC8FC\uC154\uC11C \uAC10\uC0AC\uD569\uB2C8\uB2E4.");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 26));
		lblNewLabel.setBounds(14, 46, 404, 79);
		contentPane.add(lblNewLabel);
		
		bt_exit = new JButton("\uD655\uC778");
		bt_exit.setBorderPainted(false);
		bt_exit.setBackground(new Color(255, 204, 102));
		bt_exit.setBounds(160, 145, 105, 27);
		contentPane.add(bt_exit);
	}
}