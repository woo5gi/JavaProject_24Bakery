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

	// DB에서 스윙 화면으로 테이블 값 가져오기(select) , 저장하기(insert), 수정하기(update), 삭제하기(delete)
	private static final long serialVersionUID = 1L;
	private JButton pAddBtn = null; // 테이블 한줄 추가 버튼
	private JButton pSavebutton = null; // 테이블 한줄 저장 버튼
	private JButton pModBtn = null; // 테이블 한줄 수정 버튼
	private JButton pDelBtn = null; // 테이블 한줄 삭제 벅튼
	private JTable table;
	private JScrollPane scrollPane; // 테이블 스크롤바 자동으로 생성되게 하기

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl"; // @호스트 IP : 포트 : SID
	
	
	
	private String colNames[] = { "상품ID", "상품분류", "상품카테고리", "상품이름", "상품설명", "가격" }; // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // 테이블 데이터 모델 객체 생성

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )

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

		getContentPane().setLayout(null); // 레이아웃 배치관리자 삭제
		table = new JTable(model); // 테이블에 모델객체 삽입
		table.addMouseListener(new JTableMouseListener()); // 테이블에 마우스리스너 연결
		contentPane.setLayout(null);
		scrollPane = new JScrollPane(table); // 테이블에 스크롤 생기게 하기
		scrollPane.setLocation(17, 44);
		scrollPane.setSize(745, 309);
		getContentPane().add(scrollPane);
		initialize();
		select();
	}

	private class JTableMouseListener implements MouseListener { // 마우스로 눌려진값확인하기
		public void mouseClicked(java.awt.event.MouseEvent e) { // 선택된 위치의 값을 출력

			JTable jtable = (JTable) e.getSource();
			int row = jtable.getSelectedRow(); // 선택된 테이블의 행값
			int col = jtable.getSelectedColumn(); // 선택된 테이블의 열값

			System.out.println(model.getValueAt(row, col)); // 선택된 위치의 값을 얻어내서 출력

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

	private void select() { // 테이블에 보이기 위해 검색

		String query = "SELECT PRODUCT_ID,PRODUCT_CODE,PRODUCT_KIND, PRODUCT_NAME,PRODUCT_EPL,PRICE,PRODUCT_ea FROM product order by PRODUCT_ID";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "kitri", "kitri");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				model.addRow(new Object[] { (rs.getInt(1)), (rs.getString(2)), (rs.getString(3)), rs.getString(4),
						rs.getString(5), rs.getInt(6) ,rs.getInt(7)});
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

	private void initialize() { // 액션이벤트와 버튼 컴포넌트 설정

		JPanel panel = new JPanel();
		panel.setBounds(17, 371, 745, 58);
		contentPane.add(panel);
		panel.setLayout(null);

		// 테이블 새로 한줄 추가하는 부분
		pAddBtn = new JButton();
		pAddBtn.setBounds(0, 0, 184, 58);
		pAddBtn.setBorderPainted(false);
		pAddBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		pAddBtn.setBackground(new Color(255, 204, 102));
		panel.add(pAddBtn);
		pAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				model2.addRow(new String[] { "", "", "", "", "", "" }); // 새테이블의 초기값
			}
		});
		pAddBtn.setText("추가");

		// 테이블 새로 저장하는 부분
		pSavebutton = new JButton();
		pSavebutton.setBounds(187, 0, 184, 58);
		pSavebutton.setBorderPainted(false);
		pSavebutton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		pSavebutton.setBackground(new Color(255, 204, 102));
		panel.add(pSavebutton);
		pSavebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴
				String query = "insert into PRODUCT(PRODUCT_ID,PRODUCT_CODE,PRODUCT_KIND, PRODUCT_NAME,PRODUCT_EPL,PRICE,PRODUCT_ea,product_date)"
						+ "values (?,?,?,?,?,?,0,sysdate)";

				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "kitri", "kitri"); // DB 연결
					pstmt = con.prepareStatement(query);

					// 물음표가 4개 이므로 4개 각각 입력해줘야한다.
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
				model2.setRowCount(0); // 전체 테이블 화면을 지워줌
				select(); // 저장 후 다시 전체 값들을 받아옴.
			}
		});
		pSavebutton.setText("저장");

		// 선택된 테이블 한줄 수정하는 부분
		pModBtn = new JButton();
		pModBtn.setBounds(374, 0, 184, 58);
		pModBtn.setBorderPainted(false);
		pModBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		pModBtn.setBackground(new Color(255, 204, 102));
		panel.add(pModBtn);
		pModBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴

				String query = "UPDATE PRODUCT SET PRODUCT_CODE=?,PRODUCT_KIND=?, PRODUCT_NAME=?, PRODUCT_EPL=?, PRICE=?"
						+ "where PRODUCT_ID=?";

				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, "kitri", "kitri"); // DB 연결
					pstmt = con.prepareStatement(query);

					// 물음표가 4개 이므로 4개 각각 입력해줘야한다.

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
				model2.setRowCount(0); // 전체 테이블 화면을 지워줌
				select(); // 수정 후다시 전체 값들을 받아옴.
			}
		});
		pModBtn.setText("수정");

		// 선택된 테이블 한줄 삭제하는 부분
		pDelBtn = new JButton();
		pDelBtn.setBounds(561, 0, 184, 58);
		pDelBtn.setBorderPainted(false);
		pDelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		pDelBtn.setBackground(new Color(255, 204, 102));
		panel.add(pDelBtn);
		pDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand()); // 선택된 버튼의 텍스트값 출력
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0)
					return; // 선택이 안된 상태면 -1리턴 where PRODUCT_ID= ?
				String query = "delete from PRODUCT where PRODUCT_ID=?";
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
		pDelBtn.setText("삭제");
		
		JLabel label = new JLabel("\uC0C1\uD488\uAD00\uB9AC");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label.setBounds(355, 15, 56, 21);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnNewButton.setBackground(new Color(255, 204, 102));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
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
