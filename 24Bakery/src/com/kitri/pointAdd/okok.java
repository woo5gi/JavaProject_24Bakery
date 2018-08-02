package com.kitri.pointAdd;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.kitri.bakery.digain.UserMain;
import com.kitri.lookup.User_Lookup_view;

import javax.swing.JButton;

public class okok extends JFrame implements ActionListener{

	
	JButton mainGo = new JButton("\uD655\uC778");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					okok window = new okok();
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
	public okok() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uCDA9\uC804\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		lblNewLabel.setBounds(177, 101, 126, 15);
		this.getContentPane().add(lblNewLabel);
		
	
		mainGo.setBounds(143, 194, 160, 23);
		this.getContentPane().add(mainGo);
		
		mainGo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == mainGo) {
			User_Lookup_view ulv = new User_Lookup_view();
			ulv.setVisible(true);
			this.dispose();
		}
		
	}
}
