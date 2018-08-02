package com.kitri.update;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.kitri.secession.User_Secession_Msg;

public class User_Update_Msg extends JFrame {

	public JPanel contentPane;
	public JButton btnNewButton;

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
	public User_Update_Msg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC815\uBCF4\uAC00 \uC218\uC815 \uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 26));
		lblNewLabel.setBounds(72, 44, 293, 79);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(255, 204, 102));
		btnNewButton.setBounds(160, 145, 105, 27);
		contentPane.add(btnNewButton);
	}
}
