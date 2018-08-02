package com.kitri.storestock.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.admin.project.Pro_Admin;

import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;

public class StockViewMove extends JFrame {

	private JPanel contentPane;
	
	private String ssc[] =  {
			"product_id", "product_code", "product_kind", "product_name", "ad_indate", "price", "product_ea"
		};
		private DefaultTableModel model = new DefaultTableModel(ssc, 0);
		private JButton btnHSMove;
		private JButton btnSSMove;
		private JButton btnBack;


	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockViewMove frame = new StockViewMove();
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
	public StockViewMove() {
		setLocationRelativeTo(null);
		setResizable(false);
		
		setTitle("\uC7AC\uACE0\uCC3D\uC774\uB3D9");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Å×ÀÌºí DB°¡Á®¿À±â
		getContentPane().setLayout(null);
		
		btnHSMove = new JButton("\uBCF8\uC0AC");
		btnHSMove.setBorderPainted(false);
		btnHSMove.setIcon(new ImageIcon("F:\\JavaData\\workspace\\javase\\Bakeryold\\src\\com\\kitri\\storestock\\view\\hs.png"));
		btnHSMove.setBackground(new Color(255, 204, 102));
		btnHSMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object ob = e.getSource();
				
				if(ob == btnHSMove) {
					HeadStock hsm = new HeadStock();
					dispose();
					hsm.setVisible(true);
				}
			}
		});
		btnHSMove.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		btnHSMove.setBounds(78, 100, 297, 288);
		contentPane.add(btnHSMove);
		
		btnSSMove = new JButton("\uB9E4\uC7A5");
		btnSSMove.setBorderPainted(false);
		btnSSMove.setIcon(new ImageIcon("F:\\JavaData\\workspace\\javase\\Bakeryold\\src\\com\\kitri\\storestock\\view\\ss.png"));
		btnSSMove.setBackground(new Color(240, 230, 140));
		btnSSMove.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		btnSSMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object ob = e.getSource();
				
				if(ob == btnSSMove) {
					StoreStock ssm = new StoreStock();
					ssm.setVisible(true);
				}
			}
		});
		btnSSMove.setBounds(415, 100, 297, 288);
		contentPane.add(btnSSMove);
		
		JLabel label = new JLabel("\uC7AC\uACE0\uAD00\uB9AC");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 32));
		label.setBounds(330, 9, 128, 64);
		contentPane.add(label);
		
		btnBack = new JButton("<<");
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(245, 245, 245));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pro_Admin pa = new Pro_Admin();
				dispose();
				pa.setVisible(true);
			}
		});
		btnBack.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		btnBack.setBounds(730, 10, 53, 25);
		contentPane.add(btnBack);
	}
}
