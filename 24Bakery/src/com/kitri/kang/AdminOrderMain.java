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
	private JTextField oEaLbTf;// 수량
	private JScrollPane scrollPane; // 테이블 스크롤바 자동으로 생성되게 하기

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl"; // @호스트 IP : 포트 : SID
	private String colNames[] = { "발주번호", "상품ID", "상품이름", "입고일", "발주일", "수량" }; // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // 테이블 데이터 모델 객체 생성

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )
	private JTable table;
	private JComboBox<String> oKategorCb;// 카테고리
	private JComboBox<String> oNameCb;// 빵이름
	private JPanel panel_add;
	private JButton oOderBtn; // 주문하기
	private JButton oDelBtn;// 주문취소
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
		setTitle("발주");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel oTitleLb = new JLabel("\uBC1C\uC8FC\uBAA9\uB85D");
		oTitleLb.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		oTitleLb.setBounds(355, 15, 57, 15);
		contentPane.add(oTitleLb);

		panel_add = new JPanel();
		panel_add.setBackground(new Color(255, 255, 204));
		panel_add.setBounds(12, 42, 645, 50);
		contentPane.add(panel_add);
		panel_add.setLayout(null);

		JLabel oKategoriLb = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		oKategoriLb.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		oKategoriLb.setBounds(35, 11, 62, 18);
		panel_add.add(oKategoriLb);

		oKategorCb = new JComboBox<String>();
		oKategorCb.setBackground(new Color(255, 255, 255));
		oKategorCb.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		oKategorCb.setBounds(132, 11, 90, 24);
		oKategoriLb.setLabelFor(oKategorCb);
		String[] menuItem = getMenuItem();
		oKategorCb.setModel(new DefaultComboBoxModel<String>(menuItem));
		panel_add.add(oKategorCb);
		oKategorCb.addActionListener(this);
		JLabel onamelb = new JLabel("\uC774\uB984");
		onamelb.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		onamelb.setBounds(257, 11, 34, 18);
		panel_add.add(onamelb);

		oNameCb = new JComboBox<String>();
		oNameCb.setBackground(new Color(255, 255, 255));
		oNameCb.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		oNameCb.setBounds(326, 11, 90, 24);
		panel_add.add(oNameCb);

		JLabel oEaLb = new JLabel("\uC218\uB7C9");
		oEaLb.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		oEaLb.setBounds(451, 11, 28, 18);
		panel_add.add(oEaLb);

		oEaLbTf = new JTextField();
		oEaLbTf.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		oEaLbTf.setBounds(514, 11, 90, 24);
		panel_add.add(oEaLbTf);
		oEaLbTf.setColumns(10);

		table = new JTable(model); // 테이블에 모델객체 삽입
		scrollPane = new JScrollPane(table); // 테이블에 스크롤 생기게 하기
		scrollPane.setLocation(12, 110);
		scrollPane.setSize(749, 277);
		getContentPane().add(scrollPane);

		JPanel panel_btn = new JPanel();
		panel_btn.setBounds(12, 399, 749, 42);
		contentPane.add(panel_btn);

		// 발주하기 버튼,기능
		oOderBtn = new JButton();
		oOderBtn.setBounds(0, 0, 370, 42);
		oOderBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		oOderBtn.setBorderPainted(false);
		oOderBtn.setBackground(new Color(255, 204, 102));
		oOderBtn.addActionListener(new ActionListener() {

			 public void actionPerformed(ActionEvent e) {
				selectPId();
				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
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
						con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
					} catch (Exception e2) {
					}
				}
				model2.setRowCount(0); // 전체 테이블 화면을 지워줌
				select(); // 저장 후 다시 전체 값들을 받아옴.
			}
		});
		panel_btn.setLayout(null);
		oOderBtn.setText("\uC8FC\uBB38\uD558\uAE30");
		panel_btn.add(oOderBtn);

		// 주문 취소 버튼,기능
		oDelBtn = new JButton();
		oDelBtn.setBounds(379, 0, 370, 42);
		oDelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		oDelBtn.setBorderPainted(false);
		oDelBtn.setBackground(new Color(255, 204, 102));
		oDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴 where PRODUCT_ID= ?
				String query = "delete from admin_order where ad_order_seq=?";
				System.out.println("test");
				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "kitri", "kitri"); // DB 연결
					pstmt = con.prepareStatement(query);
					System.out.println(model2.getValueAt(row, 0).toString());
					// 물음표가 1개 이므로 4개 각각 입력해줘야한다.
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
				model2.removeRow(row); // 테이블 상의 한줄 삭제
			}
		});

		oDelBtn.setText("\uC8FC\uBB38\uCDE8\uC18C");
		panel_btn.add(oDelBtn);

		btnNewButton = new JButton("\uBCF8\uC0AC\uC7AC\uACE0");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
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

	// select문 화면상에 db값 뿌려주기
	private void select() { // 테이블에 보이기 위해 검색

		String query = "select ao.ad_order_seq, p.product_id,p.product_name,ao.ad_indate,ao.ad_orderdate,ao.ad_order_ea\r\n"
				+ "from product p, admin_order ao\r\n" + "where p.product_id = ao.product_id\r\n"
						+ "and p.product_code= '본사'\r\n"
				+"order by ao.ad_order_seq";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			// 리턴받아와서 데이터를 사용할 객체생성

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				model.addRow(new Object[] { (rs.getInt(1)), (rs.getInt(2)), (rs.getString(3)), (rs.getString(4)),
						(rs.getString(5)), (rs.getInt(6)), /* (rs.getString(6)) */ });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
			} catch (Exception e2) {
			}
		}
	}

	// 카테고리 콤보박스 가져오기
	private String[] getMenuItem() { // 테이블에 보이기 위해 검색

		String query = "select distinct product_kind from product\r\n" + "order by product_kind";
		String[] menuItem = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성

			List<String> al = new ArrayList<String>();
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
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
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
			} catch (Exception e2) {
			}
		}

		return menuItem;
	}

	// 빵이름 콤보박스 가져오기
	private String[] getSubMenuItem(String a) { // 테이블에 보이기 위해 검색

		System.out.println("test" + a);
		String query = "select product_name from product\r\n" + "where product_kind = ? \r\n" + "order by product_name";
		String[] menuItem = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, a);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			List<String> al = new ArrayList<String>();
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				System.out.println("rs시작");
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
				con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
			} catch (Exception e2) {
			}
		}

		return menuItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> NameCb = (JComboBox<String>) e.getSource();
		// 두번쨰 콤보박스 갱신하기
		System.out.println(NameCb.getSelectedItem());
		changeSubMenuList(NameCb.getSelectedItem().toString());
		System.out.println("cagne");

	}

	protected void changeSubMenuList(String name) {
		String[] test = getSubMenuItem(name);
		oNameCb.setModel(new DefaultComboBoxModel<String>(test));
	}

	// 주문하기 버튼 누르면 ID값 가져와주게 하는 메소드
	protected int selectPId() {
		String query = "select product_id \r\n" + "from product \r\n" + "where product_name = ?";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, oNameCb.getSelectedItem().toString());
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
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
