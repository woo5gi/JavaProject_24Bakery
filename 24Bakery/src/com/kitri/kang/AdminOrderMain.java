package com.kitri.kang;


import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.admin.project.Pro_Admin;
import com.kitri.storestock.view.HeadStock;
import java.awt.Color;
import java.awt.Font;

public class AdminOrderMain extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045576113142857091L;
	private JPanel contentPane;
	private JTextField oEaLbTf;// ����
	private JScrollPane scrollPane; // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl"; // @ȣ��Ʈ IP : ��Ʈ : SID
	private String colNames[] = { "���ֹ�ȣ", "��ǰID", "��ǰ�̸�", "�԰���", "������", "����" }; // ���̺� �÷� ����
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // ���̺� ������ �� ��ü ����

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )
	private JTable table;
	private JComboBox<String> oKategorCb;// ī�װ�
	private JComboBox<String> oNameCb;// ���̸�
	private JPanel panel_add;
	private JButton oOderBtn; // �ֹ��ϱ�
	private JButton oDelBtn;// �ֹ����
	public static int str = 0;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminOrderMain frame = new AdminOrderMain();
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
	public AdminOrderMain() {
		setBackground(new Color(255, 255, 255));
		select();
		setTitle("����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel oTitleLb = new JLabel("\uBC1C\uC8FC\uBAA9\uB85D");
		oTitleLb.setFont(new Font("���� ���", Font.BOLD, 12));
		oTitleLb.setBounds(355, 15, 57, 15);
		contentPane.add(oTitleLb);

		panel_add = new JPanel();
		panel_add.setBackground(new Color(255, 255, 204));
		panel_add.setBounds(12, 42, 645, 50);
		contentPane.add(panel_add);
		panel_add.setLayout(null);

		JLabel oKategoriLb = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		oKategoriLb.setFont(new Font("���� ���", Font.BOLD, 12));
		oKategoriLb.setBounds(35, 11, 62, 18);
		panel_add.add(oKategoriLb);

		oKategorCb = new JComboBox<String>();
		oKategorCb.setBackground(new Color(255, 255, 255));
		oKategorCb.setFont(new Font("���� ���", Font.BOLD, 12));
		oKategorCb.setBounds(132, 11, 90, 24);
		oKategoriLb.setLabelFor(oKategorCb);
		String[] menuItem = getMenuItem();
		oKategorCb.setModel(new DefaultComboBoxModel<String>(menuItem));
		panel_add.add(oKategorCb);
		oKategorCb.addActionListener(this);
		JLabel onamelb = new JLabel("\uC774\uB984");
		onamelb.setFont(new Font("���� ���", Font.BOLD, 12));
		onamelb.setBounds(257, 11, 34, 18);
		panel_add.add(onamelb);

		oNameCb = new JComboBox<String>();
		oNameCb.setBackground(new Color(255, 255, 255));
		oNameCb.setFont(new Font("���� ���", Font.BOLD, 12));
		oNameCb.setBounds(326, 11, 90, 24);
		panel_add.add(oNameCb);

		JLabel oEaLb = new JLabel("\uC218\uB7C9");
		oEaLb.setFont(new Font("���� ���", Font.BOLD, 12));
		oEaLb.setBounds(451, 11, 28, 18);
		panel_add.add(oEaLb);

		oEaLbTf = new JTextField();
		oEaLbTf.setFont(new Font("���� ���", Font.BOLD, 12));
		oEaLbTf.setBounds(514, 11, 90, 24);
		panel_add.add(oEaLbTf);
		oEaLbTf.setColumns(10);

		table = new JTable(model); // ���̺� �𵨰�ü ����
		scrollPane = new JScrollPane(table); // ���̺� ��ũ�� ����� �ϱ�
		scrollPane.setLocation(12, 110);
		scrollPane.setSize(749, 277);
		getContentPane().add(scrollPane);

		JPanel panel_btn = new JPanel();
		panel_btn.setBounds(12, 399, 749, 42);
		contentPane.add(panel_btn);

		// �����ϱ� ��ư,���
		oOderBtn = new JButton();
		oOderBtn.setBounds(0, 0, 370, 42);
		oOderBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		oOderBtn.setBorderPainted(false);
		oOderBtn.setBackground(new Color(255, 204, 102));
		oOderBtn.addActionListener(new ActionListener() {

			 public void actionPerformed(ActionEvent e) {
				selectPId();
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				String query = "insert into admin_order(ad_order_seq,product_id,ad_indate,ad_orderdate,ad_order_ea)\r\n"
						+ "values (ad_order_seq.NEXTVAL,?,sysdate+1,sysdate,?)";
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, "kitri", "kitri");
					pstmt = con.prepareStatement(query);
					//
					pstmt.setInt(1, str);
					// pstmt.setString(2, oIndateTf.getText());
					pstmt.setString(2, oEaLbTf.getText());

					pstmt.executeUpdate();
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						rs.close();
						pstmt.close();
						con.close(); // ��ü ������ �ݴ� ������ ����� ��ü�� �ݾ��ش�.
					} catch (Exception e2) {
					}
				}
				model2.setRowCount(0); // ��ü ���̺� ȭ���� ������
				select(); // ���� �� �ٽ� ��ü ������ �޾ƿ�.
			}
		});
		panel_btn.setLayout(null);
		oOderBtn.setText("\uC8FC\uBB38\uD558\uAE30");
		panel_btn.add(oOderBtn);

		// �ֹ� ��� ��ư,���
		oDelBtn = new JButton();
		oDelBtn.setBounds(379, 0, 370, 42);
		oDelBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		oDelBtn.setBorderPainted(false);
		oDelBtn.setBackground(new Color(255, 204, 102));
		oDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // ������ �ȵ� ���¸� -1���� where PRODUCT_ID= ?
				String query = "delete from admin_order where ad_order_seq=?";
				System.out.println("test");
				try {
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "kitri", "kitri"); // DB ����
					pstmt = con.prepareStatement(query);
					System.out.println(model2.getValueAt(row, 0).toString());
					// ����ǥ�� 1�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.
					pstmt.setInt(1, Integer.parseInt(model2.getValueAt(row, 0).toString()));
					int cnt = pstmt.executeUpdate();
					// pstmt.executeUpdate(); create insert update delete
					// pstmt.executeQuery(); select
				} catch (SQLException se) {
					se.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {
					}
				}
				model2.removeRow(row); // ���̺� ���� ���� ����
			}
		});

		oDelBtn.setText("\uC8FC\uBB38\uCDE8\uC18C");
		panel_btn.add(oDelBtn);

		btnNewButton = new JButton("\uBCF8\uC0AC\uC7AC\uACE0");
		btnNewButton.setFont(new Font("���� ���", Font.BOLD, 12));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(255, 204, 102));
		btnNewButton.setBounds(671, 42, 90, 50);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeadStock pa = new HeadStock();
				dispose();
				pa.setVisible(true);
			}
		});

	}

	// select�� ȭ��� db�� �ѷ��ֱ�
	private void select() { // ���̺� ���̱� ���� �˻�

		String query = "select ao.ad_order_seq, p.product_id,p.product_name,ao.ad_indate,ao.ad_orderdate,ao.ad_order_ea\r\n"
				+ "from product p, admin_order ao\r\n" + "where p.product_id = ao.product_id\r\n"
						+ "and p.product_code= '����'\r\n"
				+"order by ao.ad_order_seq";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			// ���Ϲ޾ƿͼ� �����͸� ����� ��ü����

			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
				model.addRow(new Object[] { (rs.getInt(1)), (rs.getInt(2)), (rs.getString(3)), (rs.getString(4)),
						(rs.getString(5)), (rs.getInt(6)), /* (rs.getString(6)) */ });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // ��ü ������ �ݴ� ������ ����� ��ü�� �ݾ��ش�.
			} catch (Exception e2) {
			}
		}
	}

	// ī�װ� �޺��ڽ� ��������
	private String[] getMenuItem() { // ���̺� ���̱� ���� �˻�

		String query = "select distinct product_kind from product\r\n" + "order by product_kind";
		String[] menuItem = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����

			List<String> al = new ArrayList<String>();
			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
				al.add(rs.getString(1));
			}

			menuItem = new String[al.size()];
			for (int i = 0; i < al.size(); i++) {
				menuItem[i] = al.get(i);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // ��ü ������ �ݴ� ������ ����� ��ü�� �ݾ��ش�.
			} catch (Exception e2) {
			}
		}

		return menuItem;
	}

	// ���̸� �޺��ڽ� ��������
	private String[] getSubMenuItem(String a) { // ���̺� ���̱� ���� �˻�

		System.out.println("test" + a);
		String query = "select product_name from product\r\n" + "where product_kind = ? \r\n" + "order by product_name";
		String[] menuItem = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, a);
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
			List<String> al = new ArrayList<String>();
			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
				System.out.println("rs����");
				al.add(rs.getString(1));
			}

			menuItem = new String[al.size()];
			for (int i = 0; i < al.size(); i++) {
				menuItem[i] = al.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // ��ü ������ �ݴ� ������ ����� ��ü�� �ݾ��ش�.
			} catch (Exception e2) {
			}
		}

		return menuItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> NameCb = (JComboBox<String>) e.getSource();
		// �ι��� �޺��ڽ� �����ϱ�
		System.out.println(NameCb.getSelectedItem());
		changeSubMenuList(NameCb.getSelectedItem().toString());
		System.out.println("cagne");

	}

	protected void changeSubMenuList(String name) {
		String[] test = getSubMenuItem(name);
		oNameCb.setModel(new DefaultComboBoxModel<String>(test));
	}

	// �ֹ��ϱ� ��ư ������ ID�� �������ְ� �ϴ� �޼ҵ�
	protected int selectPId() {
		String query = "select product_id \r\n" + "from product \r\n" + "where product_name = ?";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, oNameCb.getSelectedItem().toString());
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
			rs.next();
			str = rs.getInt(1);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
			}
		}
		return str;
	}
}
