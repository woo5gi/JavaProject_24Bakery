package com.kitri.bakery.digain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PayCheck extends JFrame {// ∞·¡¶»Æ¿Œ√¢

	private JPanel contentPane;
	public JButton ok_btn;
	public JButton c_btn;
	public JLabel ap_lb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayCheck frame = new PayCheck();
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
	public PayCheck() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 213);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ok_btn = new JButton("\uD655\uC778");
		ok_btn.setBorderPainted(false);
		ok_btn.setBackground(new Color(255, 255, 102));
		ok_btn.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		ok_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ok_btn.setBounds(52, 142, 97, 23);
		contentPane.add(ok_btn);

		c_btn = new JButton("\uCDE8\uC18C");
		c_btn.setBorderPainted(false);
		c_btn.setBackground(new Color(255, 255, 102));
		c_btn.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		c_btn.setBounds(237, 142, 97, 23);
		contentPane.add(c_btn);

		JLabel pc_lb = new JLabel("\uACB0\uC81C\uC815\uBCF4\uD655\uC778");
		pc_lb.setBounds(66, 10, 220, 55);
		contentPane.add(pc_lb);
		pc_lb.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 19));
		pc_lb.setHorizontalAlignment(SwingConstants.CENTER);

		ap_lb = new JLabel("325000\uC6D0");
		ap_lb.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		ap_lb.setBounds(178, 75, 156, 35);
		contentPane.add(ap_lb);

		JLabel apt_lb = new JLabel("\uCD1D\uAE08\uC561");
		apt_lb.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		apt_lb.setBounds(92, 75, 57, 35);
		contentPane.add(apt_lb);
	}
}
