package com.kitri.kang;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.admin.project.Pro_Admin;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class ProductMain extends JFrame {

	private JPanel contentPane;

	// DB���� ���� ȭ������ ���̺� �� ��������(select) , �����ϱ�(insert), �����ϱ�(update), �����ϱ�(delete)
	private static final long serialVersionUID = 1L;
	private JButton pAddBtn = null; // ���̺� ���� �߰� ��ư
	private JButton pSavebutton = null; // ���̺� ���� ���� ��ư
	private JButton pModBtn = null; // ���̺� ���� ���� ��ư
	private JButton pDelBtn = null; // ���̺� ���� ���� ��ư
	private JTable table;
	private JScrollPane scrollPane; // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl"; // @ȣ��Ʈ IP : ��Ʈ : SID
	
	
	
	private String colNames[] = { "��ǰID", "��ǰ�з�", "��ǰī�װ�", "��ǰ�̸�", "��ǰ����", "����" }; // ���̺� �÷� ����
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // ���̺� ������ �� ��ü ����

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductMain frame = new ProductMain();
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
	public ProductMain() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 801, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		getContentPane().setLayout(null); // ���̾ƿ� ��ġ������ ����
		table = new JTable(model); // ���̺� �𵨰�ü ����
		table.addMouseListener(new JTableMouseListener()); // ���̺� ���콺������ ����
		contentPane.setLayout(null);
		scrollPane = new JScrollPane(table); // ���̺� ��ũ�� ����� �ϱ�
		scrollPane.setLocation(17, 44);
		scrollPane.setSize(745, 309);
		getContentPane().add(scrollPane);
		initialize();
		select();
	}

	private class JTableMouseListener implements MouseListener { // ���콺�� ��������Ȯ���ϱ�
		public void mouseClicked(java.awt.event.MouseEvent e) { // ���õ� ��ġ�� ���� ���

			JTable jtable = (JTable) e.getSource();
			int row = jtable.getSelectedRow(); // ���õ� ���̺��� �ప
			int col = jtable.getSelectedColumn(); // ���õ� ���̺��� ����

			System.out.println(model.getValueAt(row, col)); // ���õ� ��ġ�� ���� ���� ���

		}

		public void mouseEntered(java.awt.event.MouseEvent e) {
		}

		public void mouseExited(java.awt.event.MouseEvent e) {
		}

		public void mousePressed(java.awt.event.MouseEvent e) {
		}

		public void mouseReleased(java.awt.event.MouseEvent e) {
		}
	}

	private void select() { // ���̺� ���̱� ���� �˻�

		String query = "SELECT PRODUCT_ID,PRODUCT_CODE,PRODUCT_KIND, PRODUCT_NAME,PRODUCT_EPL,PRICE,PRODUCT_ea FROM product order by PRODUCT_ID";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����

			while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
				model.addRow(new Object[] { (rs.getInt(1)), (rs.getString(2)), (rs.getString(3)), rs.getString(4),
						rs.getString(5), rs.getInt(6) ,rs.getInt(7)});
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

	private void initialize() { // �׼��̺�Ʈ�� ��ư ������Ʈ ����

		JPanel panel = new JPanel();
		panel.setBounds(17, 371, 745, 58);
		contentPane.add(panel);
		panel.setLayout(null);

		// ���̺� ���� ���� �߰��ϴ� �κ�
		pAddBtn = new JButton();
		pAddBtn.setBounds(0, 0, 184, 58);
		pAddBtn.setBorderPainted(false);
		pAddBtn.setFont(new Font("���� ���", Font.BOLD, 16));
		pAddBtn.setBackground(new Color(255, 204, 102));
		panel.add(pAddBtn);
		pAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				model2.addRow(new String[] { "", "", "", "", "", "" }); // �����̺��� �ʱⰪ
			}
		});
		pAddBtn.setText("�߰�");

		// ���̺� ���� �����ϴ� �κ�
		pSavebutton = new JButton();
		pSavebutton.setBounds(187, 0, 184, 58);
		pSavebutton.setBorderPainted(false);
		pSavebutton.setFont(new Font("���� ���", Font.BOLD, 16));
		pSavebutton.setBackground(new Color(255, 204, 102));
		panel.add(pSavebutton);
		pSavebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // ������ �ȵ� ���¸� -1����
				String query = "insert into PRODUCT(PRODUCT_ID,PRODUCT_CODE,PRODUCT_KIND, PRODUCT_NAME,PRODUCT_EPL,PRICE,PRODUCT_ea,product_date)"
						+ "values (?,?,?,?,?,?,0,sysdate)";

				try {
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "kitri", "kitri"); // DB ����
					pstmt = con.prepareStatement(query);

					// ����ǥ�� 4�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.
					pstmt.setInt(1, Integer.parseInt((String) model2.getValueAt(row, 0)));

					pstmt.setString(2, (String) model2.getValueAt(row, 1));
					pstmt.setString(3, (String) model2.getValueAt(row, 2));
					pstmt.setString(4, (String) model2.getValueAt(row, 3));
					pstmt.setString(5, (String) model2.getValueAt(row, 4));
					pstmt.setInt(6, Integer.parseInt((String) model2.getValueAt(row, 5)));
					//pstmt.setInt(7, Integer.parseInt((String) model2.getValueAt(row, 6)));

					int cnt = pstmt.executeUpdate();
					// pstmt.executeUpdate(); create insert update delete
					// pstmt.executeQuery(); select
				} catch (Exception eeee) {
					System.out.println(eeee.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {
					}
				}
				model2.setRowCount(0); // ��ü ���̺� ȭ���� ������
				select(); // ���� �� �ٽ� ��ü ������ �޾ƿ�.
			}
		});
		pSavebutton.setText("����");

		// ���õ� ���̺� ���� �����ϴ� �κ�
		pModBtn = new JButton();
		pModBtn.setBounds(374, 0, 184, 58);
		pModBtn.setBorderPainted(false);
		pModBtn.setFont(new Font("���� ���", Font.BOLD, 16));
		pModBtn.setBackground(new Color(255, 204, 102));
		panel.add(pModBtn);
		pModBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // ������ �ȵ� ���¸� -1����

				String query = "UPDATE PRODUCT SET PRODUCT_CODE=?,PRODUCT_KIND=?, PRODUCT_NAME=?, PRODUCT_EPL=?, PRICE=?"
						+ "where PRODUCT_ID=?";

				try {
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, "kitri", "kitri"); // DB ����
					pstmt = con.prepareStatement(query);

					// ����ǥ�� 4�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.

					// pstmt.setString(1, (String) model2.getValueAt(row, 1));
					// pstmt.setString(2, (String) model2.getValueAt(row, 2));
					// pstmt.setString(3, (String) model2.getValueAt(row, 3));
					// pstmt.setString(4, (String) model2.getValueAt(row, 0));

					pstmt.setString(1, model2.getValueAt(row, 1).toString());
					pstmt.setString(2, model2.getValueAt(row, 2).toString());
					pstmt.setString(3, model2.getValueAt(row, 3).toString());
					pstmt.setString(4, model2.getValueAt(row, 4).toString());
					pstmt.setInt(5, Integer.parseInt(model2.getValueAt(row, 5).toString()));
					//pstmt.setInt(6, Integer.parseInt(model2.getValueAt(row, 6).toString()));
					pstmt.setInt(6, Integer.parseInt(model2.getValueAt(row, 0).toString()));

					int cnt = pstmt.executeUpdate();
					// //create insert update delete
					// pstmt.executeUpdate();
					// // select
					// pstmt.executeQuery();
				} catch (Exception eeee) {
					System.out.println(eeee.getMessage());
					eeee.printStackTrace();
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {
					}
				}
				model2.setRowCount(0); // ��ü ���̺� ȭ���� ������
				select(); // ���� �Ĵٽ� ��ü ������ �޾ƿ�.
			}
		});
		pModBtn.setText("����");

		// ���õ� ���̺� ���� �����ϴ� �κ�
		pDelBtn = new JButton();
		pDelBtn.setBounds(561, 0, 184, 58);
		pDelBtn.setBorderPainted(false);
		pDelBtn.setFont(new Font("���� ���", Font.BOLD, 16));
		pDelBtn.setBackground(new Color(255, 204, 102));
		panel.add(pDelBtn);
		pDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand()); // ���õ� ��ư�� �ؽ�Ʈ�� ���
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // ������ �ȵ� ���¸� -1���� where PRODUCT_ID= ?
				String query = "delete from PRODUCT where PRODUCT_ID=?";
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
		pDelBtn.setText("����");
		
		JLabel label = new JLabel("\uC0C1\uD488\uAD00\uB9AC");
		label.setFont(new Font("���� ���", Font.BOLD, 12));
		label.setBounds(355, 15, 56, 21);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnNewButton.setBackground(new Color(255, 204, 102));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("���� ���", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pro_Admin pa = new Pro_Admin();
				dispose();
				pa.setVisible(true);
			}
		});
		btnNewButton.setBounds(664, 12, 105, 27);
		contentPane.add(btnNewButton);
	}
}
