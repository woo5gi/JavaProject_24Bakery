 package com.kitri.storestock.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.admin.project.Pro_Admin;
import com.kitri.kang.AdminOrderMain;

import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.awt.Color;

public class HeadStock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cbHKind;

	
	private String ssc[] =  {
			"제품번호", "분류코드", "제품종류", "제품명", "가격", "재고량"
		};
		private DefaultTableModel model = new DefaultTableModel(ssc, 0) {
			public boolean isCellEditable(int i, int c) {
	               return false;
	           }
		};
		private JButton btnMOrder;
		private JButton btnHSDelete;
		private JButton btnHPriceUp;
		private JButton btnHPriceDown;
		private JButton btnHEaUp;
		private JButton btnHEaDown;
		private JButton btnMOk;


	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeadStock frame = new HeadStock();
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
	public HeadStock() {
		setLocationRelativeTo(null);
		setResizable(false);
		
		setTitle("\uBCF8\uC0AC\uC7AC\uACE0\uAD00\uB9AC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//테이블 DB가져오기
		getContentPane().setLayout(null); // 레이아웃 배치관리자 삭제
		
		JLabel label_3 = new JLabel("[ \uC815\uB82C ]");
		label_3.setBounds(592, 149, 57, 15);
		contentPane.add(label_3);
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		table = new JTable(model); // 테이블에 모델객체 삽입
		JScrollPane scrollPane = new JScrollPane(table); // 테이블에 스크롤 생기게 하기
		scrollPane.setSize(500, 200);
		scrollPane.setBounds(13, 13, 546, 439);
		getContentPane().add(scrollPane);
		        
		table.addMouseListener(new JTableMouseListener()); // 테이블에 마우스리스너 연결
		initialize();
		select();
		getMenuItem();
	}
		
	//마우스리스너
	private class JTableMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			JTable jtable = (JTable)e.getSource();
//			선택된 테이블 행값
			int row = jtable.getSelectedRow();
//			선택된 테이블 열값
			int col = jtable.getSelectedColumn();
//			선택된 위치값 얻어내서 출력
			System.out.println(model.getValueAt(row, col));
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
	
	//공통 셀렉트문
	public void select() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
		
			String id = "kitri";
			String pass = "kitri";
			conn = DriverManager.getConnection(url, id, pass);
			
			String sql = "select DISTINCT p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.price, p.product_ea\r\n" + 
					"from product p\r\n" + 
					"where p.product_code = '본사'\r\n" + 
					"order by p.product_id";
			pstmt = conn.prepareStatement(sql);
//			등록된 커리문 실행시키고 저장하기
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 각 Row별 데이터 추출
				model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6)});
				
				}
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	}
	
	//재고없음 셀렉트문
	private void selectdb() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
		
			String id = "kitri";
			String pass = "kitri";
			conn = DriverManager.getConnection(url, id, pass);
			
			String sql = "select   p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.price, p.product_ea\r\n" + 
					"from product p\r\n" + 
					"where p.product_code = '본사'\r\n" + 
					"and p.product_ea = 0\r\n" +
					"order by p.product_id";
			pstmt = conn.prepareStatement(sql);
//			등록된 커리문 실행시키고 저장하기
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 각 Row별 데이터 추출
				model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), 
						rs.getInt(5), rs.getInt(6)});
				
				}
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	}
	
		//콤보박스테이블 보이기
		private void getMenuItem() { // 테이블에 보이기 위해 검색

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
	        String query = "select DISTINCT product_kind from product\r\n" + "order by product_kind";
	        try {
	        	Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				String id = "kitri";
				String pass = "kitri";
				con = DriverManager.getConnection(url, id, pass);
	            pstmt = con.prepareStatement(query);
	            rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성

	            while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
	            	
	                cbHKind.addItem(rs.getString(1));
	            }
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
				
			} catch (SQLException e) {
				e.printStackTrace();
	        } finally {
	            try {
	                rs.close();
	                pstmt.close();
	                con.close(); // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
	            } catch (SQLException e) {
					e.printStackTrace();
				}		
	        }
	    }
		
	
	private void initialize() {
		
		//뒤로가기버튼
		JButton btnBack = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnBack.setBackground(new Color(255, 204, 102));
		btnBack.setBorderPainted(false);
		btnBack.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockViewMove pa = new StockViewMove();
				dispose();
				pa.setVisible(true);
			}
		});
		btnBack.setBounds(681, 13, 92, 23);
		contentPane.add(btnBack); 
		
		//새로고침버튼
		JButton btnHClear = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		btnHClear.setBorderPainted(false);
		btnHClear.setBackground(new Color(255, 204, 102));
		btnHClear.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnHClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				select();
			}
		});
		btnHClear.setBounds(578, 13, 99, 23);
		contentPane.add(btnHClear);
		
		//재고없음버튼
		JButton btnHPEZero = new JButton("\uC7AC\uACE0\uC5C6\uC74C");
		btnHPEZero.setBackground(new Color(255, 204, 102));
		btnHPEZero.setBorderPainted(false);
		btnHPEZero.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnHPEZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				selectdb();
			}
		});
		btnHPEZero.setBounds(578, 384, 195, 29);
		contentPane.add(btnHPEZero);
		
		//발주창가기버튼
		btnMOrder = new JButton("\uBC1C\uC8FC\uCC3D\uC73C\uB85C \uC774\uB3D9");
		btnMOrder.setBorderPainted(false);
		btnMOrder.setBackground(new Color(255, 204, 102));
		btnMOrder.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnMOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminOrderMain pa = new AdminOrderMain();
				dispose();
				pa.setVisible(true);

			}
		});
		btnMOrder.setBounds(580, 46, 192, 45);
		contentPane.add(btnMOrder);
		
		//삭제버튼
		btnHSDelete = new JButton("\uC0AD\uC81C");
		btnHSDelete.setBorderPainted(false);
		btnHSDelete.setBackground(new Color(255, 204, 102));
		btnHSDelete.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnHSDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model1 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0)
					return;
			
				String query = "DELETE from admin_order \r\n" +
								"where product_id = ?";
				String query1 = "DELETE from product \r\n" + 
						"where product_id = ?";
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt1 = null;
//				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				
					String id = "kitri";
					String pass = "kitri";
					conn = DriverManager.getConnection(url, id, pass);
					conn.setAutoCommit(false);
					pstmt = conn.prepareStatement(query);
					pstmt1 = conn.prepareStatement(query1);
//					등록된 커리문 실행시키고 저장하기
//					rs = pstmt.executeQuery();
					
					pstmt.setString(1, model1.getValueAt(row, 0).toString());
					int cnt = pstmt.executeUpdate();
					pstmt1.setString(1, model1.getValueAt(row, 0).toString());
					int cnt1 = pstmt1.executeUpdate();
					
					if(cnt == 1 && cnt1 == 1) {
                    	System.out.println("커밋 성공!");
                    	conn.commit();
                    } else {
                    	System.out.println(cnt);
                    	System.out.println(cnt1);
                    	System.out.println("커밋 실패.");
                    	conn.rollback();                    	
                    }
                    conn.setAutoCommit(true);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				} catch (SQLException e2) {
					e2.printStackTrace();
					System.out.println(e2.getMessage());

				} finally {
					try {
//						rs.close();
						pstmt1.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
				}
//				테이블 상 한줄 삭제
				model1.removeRow(row);
			}
		});
		btnHSDelete.setBounds(578, 423, 197, 29);
		contentPane.add(btnHSDelete);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(583, 150, 190, 218);
		contentPane.add(panel);
		
		//정렬 가격순 레이블
		JLabel lbHPrice = new JLabel("\uAC00\uACA9\uC21C");
		lbHPrice.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbHPrice.setBounds(17, 35, 47, 15);
		panel.add(lbHPrice);
		
		//정렬 가격순up
		btnHPriceUp = new JButton("\u25B2");
		btnHPriceUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				
					String id = "kitri";
					String pass = "kitri";
					conn = DriverManager.getConnection(url, id, pass);
					
					String sql = "select DISTINCT p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '본사'\r\n" + 
							"order by p.price desc";
					pstmt = conn.prepareStatement(sql);
//					등록된 커리문 실행시키고 저장하기
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						// 각 Row별 데이터 추출
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4),
								rs.getInt(5), rs.getInt(6)});
						
						}
						
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				} finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e3) {
						e3.printStackTrace();
					}		
				}
			}
		});
		btnHPriceUp.setBorderPainted(false);
		btnHPriceUp.setBackground(new Color(255, 204, 102));
		btnHPriceUp.setBounds(76, 27, 45, 29);
		panel.add(btnHPriceUp);
		
		//정렬 가격순 down
		btnHPriceDown = new JButton("\u25BC");
		btnHPriceDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				
					String id = "kitri";
					String pass = "kitri";
					conn = DriverManager.getConnection(url, id, pass);
					
					String sql = "select DISTINCT p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '본사'\r\n" + 
							"order by p.price";
					pstmt = conn.prepareStatement(sql);
//					등록된 커리문 실행시키고 저장하기
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						// 각 Row별 데이터 추출
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4),
								rs.getInt(5), rs.getInt(6)});
						
						}
						
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				} finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e3) {
						e3.printStackTrace();
					}		
				}
			}
		});
		btnHPriceDown.setBorderPainted(false);
		btnHPriceDown.setBackground(new Color(255, 204, 102));
		btnHPriceDown.setBounds(126, 27, 45, 29);
		panel.add(btnHPriceDown);
		
		//정렬 재고순 레이블
		JLabel lbHEa = new JLabel("\uC7AC\uACE0\uC21C");
		lbHEa.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbHEa.setBounds(17, 85, 47, 15);
		panel.add(lbHEa);
		
		//정렬 재고순 up
		btnHEaUp = new JButton("\u25B2");
		btnHEaUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				
					String id = "kitri";
					String pass = "kitri";
					conn = DriverManager.getConnection(url, id, pass);
					
					String sql = "select DISTINCT p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '본사'\r\n" + 
							"order by p.product_ea desc";
					pstmt = conn.prepareStatement(sql);
//					등록된 커리문 실행시키고 저장하기
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						// 각 Row별 데이터 추출
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4), 
								rs.getInt(5), rs.getInt(6)});
						}
						
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				} finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e3) {
						e3.printStackTrace();
					}		
				}
			}
		});
		btnHEaUp.setBorderPainted(false);
		btnHEaUp.setBackground(new Color(255, 204, 102));
		btnHEaUp.setBounds(76, 79, 45, 29);
		panel.add(btnHEaUp);
		
		//정렬 재고순 down
		btnHEaDown = new JButton("\u25BC");
		btnHEaDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				
					String id = "kitri";
					String pass = "kitri";
					conn = DriverManager.getConnection(url, id, pass);
					
					String sql = "select DISTINCT p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '본사'\r\n" + 
							"order by p.product_ea";
					pstmt = conn.prepareStatement(sql);
//					등록된 커리문 실행시키고 저장하기
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						// 각 Row별 데이터 추출
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4),
								rs.getInt(5), rs.getInt(6)});
						}
						
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				} finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e3) {
						e3.printStackTrace();
					}		
				}
			}
		});
		btnHEaDown.setBorderPainted(false);
		btnHEaDown.setBackground(new Color(255, 204, 102));
		btnHEaDown.setBounds(126, 79, 45, 29);
		panel.add(btnHEaDown);
		
		//정렬 제품순 레이블
		JLabel lbHKind = new JLabel("\uC81C\uD488\uC885\uB958\uBCC4");
		lbHKind.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbHKind.setBounds(17, 136, 79, 15);
		panel.add(lbHKind);
		
		//정렬 제품순 콤보박스
		cbHKind = new JComboBox();
		cbHKind.setBackground(new Color(255, 255, 255));
		cbHKind.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cbHKind.addItem("제품을 선택해주세요!");
		//콤보박스에서 제일먼저 가져올 값
		cbHKind.setSelectedItem("제품을 선택해주세요!");
		cbHKind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				
					String id = "kitri";
					String pass = "kitri";
					conn = DriverManager.getConnection(url, id, pass);
					
					String sql = "select DISTINCT p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '본사'\r\n" + 
							"and p.product_kind = ? \r\n" + 
							"order by p.product_id";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cbHKind.getSelectedItem().toString());
//					등록된 커리문 실행시키고 저장하기
					rs = pstmt.executeQuery();
					while(rs.next()) {
						// 각 Row별 데이터 추출
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4),
								rs.getInt(5), rs.getInt(6)});
						
						}
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}		
				}
			}
		});
		
		cbHKind.setBounds(15, 164, 154, 33);
		panel.add(cbHKind);
		
		//발주확인버튼
		btnMOk = new JButton("\uBC1C\uC8FC\uD655\uC778");
		btnMOk.setBackground(new Color(255, 204, 102));
		btnMOk.setBorderPainted(false);
		btnMOk.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnMOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ResultSet rs1 = null;
				PreparedStatement pstmt3 = null;
				PreparedStatement pstmt2 = null;
				PreparedStatement pstmt1 = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				
					String id = "kitri";
					String pass = "kitri";
					conn = DriverManager.getConnection(url, id, pass);
					
					String sql = "select p.product_id, p.PRODUCT_EA, ao.AD_ORDER_EA\r\n" + 
							"from product p, ADMIN_ORDER ao\r\n" + 
							"where p.product_id = AO.PRODUCT_ID";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
//					등록된 커리문 실행시키고 저장하기
					
					String sql1 = "select product_id, product_ea, sum(ad_order_ea) from (\r\n" + 
							"select p.product_id, p.PRODUCT_EA, ao.AD_ORDER_EA\r\n" + 
							"from product p, ADMIN_ORDER ao\r\n" + 
							"where p.product_id = AO.PRODUCT_ID) ea\r\n" + 
							"group by product_id, product_ea";
					pstmt1 = conn.prepareStatement(sql1);
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						int p_id = rs1.getInt(1);
						int p_ea = rs1.getInt(2);
						int a_ea = rs1.getInt(3);
						String sql2 = "update PRODUCT set PRODUCT_EA = ?\r\n" + 
								"where PRODUCT_ID = ?";
						pstmt2 = conn.prepareStatement(sql2);
						pstmt2.setInt(1, p_ea + a_ea);
						pstmt2.setInt(2, p_id);
						pstmt2.executeUpdate();
						pstmt2.close();
						String sql3 = "update ADMIN_ORDER set AD_ORDER_EA = 0\r\n" + 
								"where product_id = ?";
						pstmt3 = conn.prepareStatement(sql3);
						pstmt3.setInt(1, p_id);
						pstmt3.executeUpdate();
						pstmt3.close();
						}
					pstmt1.close();
					rs1.close();
					rs.close();
					pstmt.close();
					conn.close();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				select();
			}
		});
		btnMOk.setBounds(580, 98, 193, 35);
		contentPane.add(btnMOk);
		
	}
}
