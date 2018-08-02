package com.kitri.bakery.digain;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;


import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;



import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class UserPay extends JFrame {//°áÁ¦Ã¢

	public JPanel contentPane;
	public JTable p_table;
	public DefaultTableModel p_model;
	public JLabel ap_lb;
	public JLabel aps_lb;
	public int price = 0;
	public JButton p_btn;
	public JButton c_btn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPay frame = new UserPay();
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
	public UserPay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		p_btn = new JButton("\uACB0\uC81C");
		p_btn.setBorderPainted(false);
		p_btn.setBackground(new Color(255, 255, 102));
		p_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		p_btn.setBounds(654, 394, 116, 41);
		contentPane.add(p_btn);
		
		JLabel p_lb = new JLabel("\uACB0\uC81C");
		p_lb.setHorizontalAlignment(SwingConstants.CENTER);
		p_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		p_lb.setBounds(260, 21, 194, 69);
		contentPane.add(p_lb);
		
		c_btn = new JButton("\uCDE8\uC18C");
		c_btn.setBorderPainted(false);
		c_btn.setBackground(new Color(255, 255, 102));
		c_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		c_btn.setBounds(654, 46, 97, 23);
		contentPane.add(c_btn);
		
		JScrollPane p_sp = new JScrollPane();
		p_sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		p_sp.setBounds(23, 79, 749, 305);
		contentPane.add(p_sp);
		
		String p_header[] = {
				"»§", "»§¼³¸í", "¼ö·®", "´Ü°¡", "°¡°Ý"
		};
		p_model = new DefaultTableModel(p_header, 0) {
			public boolean isCellEditable(int i, int c) {
			    return false;
			} 
		};
		p_table = new JTable(p_model);
		p_table.setPreferredScrollableViewportSize(new Dimension(450, 900));
		p_sp.setViewportView(p_table);
		
		ap_lb = new JLabel("\uCD1D\uAE08\uC561");
		ap_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		ap_lb.setBounds(260, 407, 57, 28);
		contentPane.add(ap_lb);
		
		aps_lb = new JLabel("000000");
		aps_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		aps_lb.setBounds(329, 407, 243, 28);
		contentPane.add(aps_lb);
//		p_addrow();

		
	}

	// private void p_addrow() {// Å×ÀÌºí¿¡ °ª »Ñ¸®±â¿Í ¾Æ·¡ ÃÑ±Ý¾× lb¿¡ °ª»Ñ¸®±â
	// BakeryDto bd = new BakeryDto();
	// List<BakeryBean> list = bd.psdb();
	// for (int i = 0; i < list.size(); i++) {
	// p_model.addRow(new Object[] {list.get(i).getFirst_name(),
	// list.get(i).getDepartment_id(), list.get(i).getSalary(),
	// list.get(i).getEmployee_id(), list.get(i).getAllsalary()});
	// price += list.get(i).getAllsalary();
	// }
	// aps_lb.setText(Integer.toString(price)+ "·çºñ");
	//
	// }
}
