package com.kitri.login.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class info extends JFrame implements ActionListener {

	JButton back = new JButton("\uB4A4\uB85C\uAC00\uAE30");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					info window = new info();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public info() {
		initialize();
	}

	private void initialize() {

		this.setBounds(100, 100, 335, 211);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514 \uC911\uBCF5\uC785\uB2C8\uB2E4");
		lblNewLabel.setBounds(98, 60, 139, 34);
		this.getContentPane().add(lblNewLabel);

		back.setBounds(98, 123, 97, 23);
		getContentPane().add(back);
		this.setVisible(true);

		back.addActionListener(this);// 리스너 등록
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == back) {
			dispose();// 현재 창닫기

		}

	}

}
