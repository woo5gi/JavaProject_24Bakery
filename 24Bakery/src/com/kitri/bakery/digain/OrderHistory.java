package com.kitri.bakery.digain;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;

public class OrderHistory extends JFrame {// °áÁ¦³»¿ªÃ¢

	private JPanel contentPane;
	public JTextField sp_tf;
	public JButton out_btn;
	public DefaultTableModel h_model;
	public JTable h_table;
	private JTable h_table_1;
	public JLabel sp_lb;
	public JTextField input_tf;
	public JLabel input_lb;
	public JButton clear_btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderHistory frame = new OrderHistory();
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
	public OrderHistory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel h_lb = new JLabel("\uACB0\uC81C\uB0B4\uC5ED");
		h_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		h_lb.setHorizontalAlignment(SwingConstants.CENTER);
		h_lb.setBounds(254, 10, 197, 48);
		contentPane.add(h_lb);

		sp_lb = new JLabel("\uCD1D\uACB0\uC81C\uAE08\uC561");
		sp_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		sp_lb.setBounds(384, 387, 83, 21);
		contentPane.add(sp_lb);

		sp_tf = new JTextField();
		sp_tf.setFocusable(false);
		sp_tf.setText("");
		sp_tf.setBounds(456, 387, 299, 21);
		contentPane.add(sp_tf);
		sp_tf.setColumns(10);

		out_btn = new JButton("\uB098\uAC00\uAE30");
		out_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		out_btn.setBorderPainted(false);
		out_btn.setBackground(new Color(255, 255, 102));
		out_btn.setBounds(642, 29, 97, 23);
		contentPane.add(out_btn);

		String h_header[] = { "\uC8FC\uBB38\uBC88\uD638", "\uBE75\uC774\uB984", "\uC218\uB7C9", "\uB2E8\uAC00",
				"\uACB0\uC81C\uAE08\uC561", "\uC8FC\uBB38\uB0A0\uC9DC" };
		h_model = new DefaultTableModel(h_header, 0) {
			public boolean isCellEditable(int i, int c) {
			    return false;
			} 
		};
		h_table = new JTable(h_model);
		h_table.setPreferredScrollableViewportSize(new Dimension(450, 900));

		JScrollPane h_sp = new JScrollPane();
		h_sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		h_sp.setBounds(12, 68, 760, 312);
		contentPane.add(h_sp);

		h_table_1 = new JTable(h_model);
		h_table_1.setBackground(new Color(255, 255, 255));
		h_table_1.setPreferredScrollableViewportSize(new Dimension(450, 900));
		h_sp.setViewportView(h_table_1);
		
		input_tf = new JTextField();
		input_tf.setBounds(96, 421, 150, 32);
		contentPane.add(input_tf);
		input_tf.setColumns(10);
		
		input_lb = new JLabel("\uC544\uC774\uB514\uC785\uB825");
		input_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		input_lb.setBounds(12, 420, 83, 32);
		contentPane.add(input_lb);
		
		clear_btn = new JButton("\uC9C0\uC6B0\uAE30");
		clear_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		clear_btn.setBorderPainted(false);
		clear_btn.setBackground(new Color(255, 255, 102));
		clear_btn.setBounds(12, 386, 97, 23);
		contentPane.add(clear_btn);

	}
}
