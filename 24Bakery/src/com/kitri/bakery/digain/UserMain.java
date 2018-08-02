package com.kitri.bakery.digain;

import java.awt.Dimension;
import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.bakery.domain.BakeryBean;
import com.kitri.bakery.model.BakeryDto;
import com.kitri.bakery.service.UserService;
import com.kitri.domain.UserBean;

import java.awt.Color;
import java.awt.Font;

public class UserMain extends JFrame {// ÁÖ¹®Ã¢(¸ÞÀÎÃ¢)

	public JPanel contentPane;
	public JTable m_table;
	public JTable o_table;
	public JTextField ap_tf;
	public JButton lo_btn;
	public JButton p_btn;
	public JButton u_btn;
	public DefaultTableModel m_model;
	public DefaultTableModel o_model;
	public JButton plus_btn;
	public JButton d_btn;
	public JSpinner su_spinner;
	public UserPay up = new UserPay();
	public PayCheck pc = new PayCheck();
	public OrderHistory oh = new OrderHistory();
	public JButton ph_btn;
	public JLabel allap_lb;
	public JLabel oc_lb;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	public JLabel point_lb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain frame = new UserMain();
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
	public UserMain() {
		setTitle("\uC8FC\uBB38");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		u_btn = new JButton("\uD68C\uC6D0\uC815\uBCF4");
		u_btn.setBorderPainted(false);
		u_btn.setBackground(new Color(255, 204, 102));
		u_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		u_btn.setBounds(675, 14, 97, 23);
		contentPane.add(u_btn);

		oc_lb = new JLabel("\uC8FC\uBB38");
		oc_lb.setBackground(new Color(255, 204, 102));
		oc_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		oc_lb.setBounds(328, 14, 56, 22);
		contentPane.add(oc_lb);

		ap_tf = new JTextField();
		ap_tf.setBackground(new Color(255, 255, 255));
		ap_tf.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		ap_tf.setFocusable(false);
		ap_tf.setText("\uCD1D\uAC00\uACA9 : .....");
		ap_tf.setBounds(455, 422, 242, 26);
		contentPane.add(ap_tf);
		ap_tf.setColumns(10);

		plus_btn = new JButton("\uCD94\uAC00");
		plus_btn.setBorderPainted(false);
		plus_btn.setBackground(new Color(255, 204, 102));
		plus_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		plus_btn.setBounds(709, 160, 63, 30);
		contentPane.add(plus_btn);

		d_btn = new JButton("\uC0AD\uC81C");
		d_btn.setBorderPainted(false);
		d_btn.setBackground(new Color(255, 204, 102));
		d_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		d_btn.setBounds(709, 257, 63, 30);
		contentPane.add(d_btn);

		p_btn = new JButton("\uACB0\uC81C");
		p_btn.setBorderPainted(false);
		p_btn.setBackground(new Color(255, 204, 102));
		p_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		p_btn.setBounds(709, 419, 63, 30);
		contentPane.add(p_btn);

		su_spinner = new JSpinner();
		su_spinner.setBackground(new Color(255, 204, 102));
		su_spinner.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		su_spinner.setBounds(707, 128, 65, 22);
		contentPane.add(su_spinner);
		lo_btn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		lo_btn.setForeground(new Color(0, 0, 0));
		lo_btn.setBorderPainted(false);
		lo_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lo_btn.setBackground(new Color(211, 211, 211));
		
		JLabel su_lb = new JLabel("\uC218\uB7C9");
		su_lb.setBackground(new Color(255, 204, 102));
		su_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		su_lb.setHorizontalAlignment(SwingConstants.CENTER);
		su_lb.setBounds(736, 95, 36, 23);
		contentPane.add(su_lb);
		if(BakeryBean.member_id != "admin") {
			lo_btn.setText("\uB85C\uADF8\uC544\uC6C3");
		} else {
			lo_btn.setText("µÚ·Î°¡±â");
		}
		lo_btn.setBounds(12, 423, 97, 23);
		contentPane.add(lo_btn);

		String m_header[] = { "»§ÄÚµå ", "»§", "»§¼³¸í", "·çºñ", "¼ö·®"};
		m_model = new DefaultTableModel(m_header, 0) {
			public boolean isCellEditable(int i, int c) {
			    return false;
			} 
		};
		m_table = new JTable(m_model);
		m_table.setPreferredScrollableViewportSize(new Dimension(450, 900));

		JScrollPane m_sp = new JScrollPane();
		m_sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		m_sp.setBounds(12, 50, 685, 171);
		contentPane.add(m_sp);
		m_sp.setViewportView(m_table);

		JLabel m_lb = new JLabel("\uBE75\uBA54\uB274");
		m_sp.setRowHeaderView(m_lb);
		m_lb.setHorizontalAlignment(SwingConstants.CENTER);
		msdb();

		String o_header[] = { "»§ÄÚµå", "»§", "»§¼³¸í", "¼ö·®", "·çºñ", "ÃÑ·çºñ" };
		o_model = new DefaultTableModel(o_header, 0) {
			public boolean isCellEditable(int i, int c) {
			    return false;
			} 
		};
		o_table = new JTable(o_model);
		o_table.setPreferredScrollableViewportSize(new Dimension(450, 900));

		JScrollPane o_sp = new JScrollPane();
		o_sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		o_sp.setBounds(12, 231, 685, 185);
		contentPane.add(o_sp);

		o_table = new JTable(o_model);
		o_table.setPreferredScrollableViewportSize(new Dimension(450, 900));
		o_sp.setViewportView(o_table);

		ph_btn = new JButton("\uACB0\uC81C\uB0B4\uC5ED");
		ph_btn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		ph_btn.setBorderPainted(false);
		ph_btn.setBackground(new Color(255, 204, 102));
		ph_btn.setBounds(566, 14, 97, 23);
		contentPane.add(ph_btn);
		
		allap_lb = new JLabel("\uCD1D\uAC00\uACA9");
		allap_lb.setBackground(new Color(255, 204, 102));
		allap_lb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		allap_lb.setBounds(401, 421, 42, 26);
		contentPane.add(allap_lb);
		
		lblNewLabel = new JLabel("24K Bakery \uC5D0\uC11C\uB294 \uB2F9\uC77C\uAD6C\uC6B4 \uBE75\uB9CC \uD310\uB9E4\uD569\uB2C8\uB2E4!");
		lblNewLabel.setBackground(new Color(255, 204, 102));
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel.setBounds(12, 14, 275, 23);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\uB098\uC758\uB8E8\uBE44 :");
		lblNewLabel_1.setBounds(131, 428, 71, 15);
		contentPane.add(lblNewLabel_1);
		
		point_lb = new JLabel();
		point_lb.setBounds(212, 428, 172, 15);
		contentPane.add(point_lb);
		BakeryDto bd = new BakeryDto();
		point_lb.setText(bd.selectPoint() + "·çºñ");

		UserService us = new UserService(this);

		lo_btn.addActionListener(us);
		ph_btn.addActionListener(us);
		u_btn.addActionListener(us);
		p_btn.addActionListener(us);
		plus_btn.addActionListener(us);
		d_btn.addActionListener(us);

		up.p_btn.addActionListener(us);
		up.c_btn.addActionListener(us);

		pc.c_btn.addActionListener(us);
		pc.ok_btn.addActionListener(us);

		oh.out_btn.addActionListener(us);
		oh.input_tf.addActionListener(us);
		oh.clear_btn.addActionListener(us);
		
		if (BakeryBean.member_id.equals("admin")) {
			oc_lb.setText("ÀÏ¹ÝÁÖ¹®");
			point_lb.setVisible(false);
			lblNewLabel_1.setVisible(false);
		} else {
			oc_lb.setText("¿¹¾àÁÖ¹®");
			oh.clear_btn.setVisible(false);
			oh.clear_btn.setFocusable(false);
			oh.input_lb.setVisible(false);
			oh.input_tf.setVisible(false);
			oh.input_tf.setFocusable(false);
		}
	}

	public void msdb() { // db°ª meuntable¿¡ »Ñ·ÁÁÖ´Â ¸Þ¼Òµå
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
			String id = "kitri";
			String pass = "kitri";
			con = DriverManager.getConnection(url, id, pass);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String sql = "select product_id, product_name, product_epl, price, PRODUCT_EA from PRODUCT";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				m_model.addRow(new Object[] { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3),
						Integer.toString(rs.getInt(4)), rs.getInt(5) });
			}
			con.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
