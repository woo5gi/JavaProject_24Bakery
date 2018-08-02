package com.kitri.login.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class join_ok_info extends JFrame implements ActionListener{

	
	 JLabel lblNewLabel = new JLabel("\uAC00\uC785\uC644\uB8CC");
	JButton btnNewButton = new JButton("\uB85C\uADF8\uC778\uD558\uAE30");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					join_ok_info window = new join_ok_info();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public join_ok_info() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		
		lblNewLabel.setBounds(181, 82, 57, 15);
		this.getContentPane().add(lblNewLabel);
		
		
		btnNewButton.setBounds(170, 184, 97, 23);
		this.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this); //리스너 등록
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btnNewButton ) {
			
			joinView jv = new joinView();
			loginView lo = new loginView();
		
			jv.dispose();//joinView 종료
			this.setVisible(false);//현재 창 닫음
			lo.setVisible(true);//login 창 띄움

		}
		
	}//actionPerformed 종료지점
}//join_ok_info 종료지점
