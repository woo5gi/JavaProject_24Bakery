package com.kitri.storestock.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.SystemColor;

public class StoreStock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cbKind;
	
/*	private String ssc[] =  {
			"product_id", "product_code", "product_kind", "product_name", "ad_indate", "price", "product_ea"
		};*/
	private String ssc[] =  {
			"��ǰ��ȣ", "�з��ڵ�", "��ǰ����", "��ǰ��", "������", "����", "���"
		};
		private DefaultTableModel model = new DefaultTableModel(ssc, 0) {
			public boolean isCellEditable(int i, int c) {
                if (c == 6) {
                    return true;
                }
                else{
                        return false;
                        }
               
            }
		};
		private JButton btnSSChange;
		private JButton btnSSDelete;
		private JButton btnARPUp;
		private JButton btnARPDown;
		private JButton btnAReaUp;
		private JButton btnAReaDown;
		private JLabel lbKind;


	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreStock frame = new StoreStock();
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
	public StoreStock() {
		setLocationRelativeTo(null);
		setResizable(false);
		
		setTitle("\uB9E4\uC7A5\uC7AC\uACE0\uAD00\uB9AC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//���̺� DB��������
		getContentPane().setLayout(null); // ���̾ƿ� ��ġ������ ����
		table = new JTable(model);			
		//�÷� ����
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setSize(500, 200);
		scrollPane.setBounds(13, 13, 546, 439);
		getContentPane().add(scrollPane);
		        
		table.addMouseListener(new JTableMouseListener()); // ���̺� ���콺������ ����
		initialize();
		select();
		getMenuItem();
	}
	
	//���콺������ - Ŭ���̺�Ʈ
	private class JTableMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			JTable jtable = (JTable)e.getSource();
//			���õ� ���̺� �ప
			int row = jtable.getSelectedRow();
//			���õ� ���̺� ����
			int col = jtable.getSelectedColumn();
//			���õ� ��ġ�� ���� ���
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
	
	//DB���� select��
	private void select() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
		
			String id = "kitri";
			String pass = "kitri";
			conn = DriverManager.getConnection(url, id, pass);
			
			String sql = "select p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.product_date, p.price, p.product_ea\r\n" + 
					"from product p\r\n" + 
					"where p.product_code = '����'\r\n" + 
					"order by p.product_id";
			pstmt = conn.prepareStatement(sql);
//			��ϵ� Ŀ���� �����Ű�� �����ϱ�
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// �� Row�� ������ ����
				model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7)});
				
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
	
	//������ DB���� select��
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
			
			String sql = "select p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.product_date, p.price, p.product_ea\r\n" + 
					"from product p\r\n" + 
					"where p.product_code = '����'\r\n" + 
					"and p.product_ea = 0\r\n" +
					"order by p.product_id";
			pstmt = conn.prepareStatement(sql);
//			��ϵ� Ŀ���� �����Ű�� �����ϱ�
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// �� Row�� ������ ����
				model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7)});
				
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
	
	//�޺��ڽ����̺� ���̱�
	private void getMenuItem() { // ���̺� ���̱� ���� �˻�

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
        String query = "select distinct product_kind from product\r\n" + "order by product_kind";
       
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
			String id = "kitri";
			String pass = "kitri";
			con = DriverManager.getConnection(url, id, pass);
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����

            while (rs.next()) { // ���� ���� �����ͼ� ���̺����� �߰�
                cbKind.addItem(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                con.close(); // ��ü ������ �ݴ� ������ ����� ��ü�� �ݾ��ش�.
            } catch (SQLException e) {
				e.printStackTrace();
			}		
        }
    }
	
	//�޺��ڽ� �����Ѱ� db�� ����
	
	
	private void initialize() {
		
		//�ڷΰ���
		JButton btnBack = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(255, 204, 102));
		btnBack.setFont(new Font("���� ���", Font.BOLD, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(681, 13, 92, 23);
		contentPane.add(btnBack); 
		
		//���ΰ�ħ
		JButton btnSClear = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		btnSClear.setBorderPainted(false);
		btnSClear.setBackground(new Color(255, 204, 102));
		btnSClear.setFont(new Font("���� ���", Font.BOLD, 12));
		btnSClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DB ��� �� ����
				model.setNumRows(0);
				//DB �ҷ���
				select();
			}
		});
		btnSClear.setBounds(578, 13, 99, 23);
		contentPane.add(btnSClear);
		
		//������
		JButton btnSPEZero = new JButton("\uC7AC\uACE0\uC5C6\uC74C");
		btnSPEZero.setBorderPainted(false);
		btnSPEZero.setBackground(new Color(255, 204, 102));
		btnSPEZero.setFont(new Font("���� ���", Font.BOLD, 12));
		btnSPEZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				selectdb();
			}
		});
		btnSPEZero.setBounds(578, 374, 195, 30);
		contentPane.add(btnSPEZero);
		
		//������ư
		btnSSChange = new JButton("\uC218\uC815");
		btnSSChange.setBorderPainted(false);
		btnSSChange.setBackground(new Color(255, 204, 102));
		btnSSChange.setFont(new Font("���� ���", Font.BOLD, 12));
		btnSSChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model1 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0)
					return;
				
				String query = "update product SET PRODUCT_EA = ? where product_id = ?";
				
				DefaultTableModel mod = new DefaultTableModel();
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				
					String id = "kitri";
					String pass = "kitri";
					
					conn = DriverManager.getConnection(url, id, pass);
					conn.setAutoCommit(false);
					pstmt =  conn.prepareStatement(query);
//					rs = pstmt.executeQuery();
						
                    pstmt.setInt(1, Integer.parseInt(model1.getValueAt(row, 6).toString()));
                    pstmt.setInt(2, Integer.parseInt(model1.getValueAt(row, 0).toString()));
//                  pstmt.setString(2, model1.getValueAt(row, 0).toString());
                    int cnt = pstmt.executeUpdate();
					
                    if(cnt == 1) {
                    	System.out.println("Ŀ�� ����!");
                    	conn.commit();
                    } else {                   	
                    	System.out.println("Ŀ�� ����.");
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
						//rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
				}
				model1.setRowCount(0);
				select();
			}
		});
		btnSSChange.setBounds(578, 414, 95, 38);
		contentPane.add(btnSSChange);
		
		//������ư
		btnSSDelete = new JButton("\uC0AD\uC81C");
		btnSSDelete.setBorderPainted(false);
		btnSSDelete.setBackground(new Color(255, 204, 102));
		btnSSDelete.setFont(new Font("���� ���", Font.BOLD, 12));
		btnSSDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model1 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0)
					return;
			
				else {
					String query = "DELETE from admin_order \r\n" +
									"where product_id = ?";
					String query1 = "DELETE from product \r\n" + 
							"where product_id = ?";
					
					Connection conn = null;
					PreparedStatement pstmt = null;
					PreparedStatement pstmt1 = null;
//					ResultSet rs = null;
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						
						String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
					
						String id = "kitri";
						String pass = "kitri";
						conn = DriverManager.getConnection(url, id, pass);
						conn.setAutoCommit(false);
						pstmt = conn.prepareStatement(query);
						pstmt1 = conn.prepareStatement(query1);
	//					��ϵ� Ŀ���� �����Ű�� �����ϱ�
	//					rs = pstmt.executeQuery();
						
						pstmt.setString(1, model1.getValueAt(row, 0).toString());
						int cnt = pstmt.executeUpdate();
						pstmt1.setString(1, model1.getValueAt(row, 0).toString());
						int cnt1 = pstmt1.executeUpdate();
						
						if(cnt == 1 && cnt1 == 1) {
	                    	System.out.println("Ŀ�� ����!");
	                    	conn.commit();
	                    } else {
	                    	System.out.println(cnt);
	                    	System.out.println(cnt1);
	                    	System.out.println("Ŀ�� ����.");
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
							//rs.close();
							pstmt.close();
							pstmt1.close();
							conn.close();
						} catch (SQLException e3) {
							e3.printStackTrace();
						}
					}
	//				���̺� �� ���� ����
					model1.removeRow(row);
				}
			}
		});
		btnSSDelete.setBounds(678, 414, 95, 38);
		contentPane.add(btnSSDelete);
		
		//���� label
		JLabel lbArray = new JLabel("[ \uC815\uB82C ]");
		lbArray.setFont(new Font("���� ���", Font.BOLD, 13));
		lbArray.setBounds(593, 67, 57, 15);
		contentPane.add(lbArray);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(583, 67, 190, 278);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//���� ���ݼ� label
		JLabel lbPrice = new JLabel("\uAC00\uACA9\uC21C");
		lbPrice.setFont(new Font("���� ���", Font.BOLD, 12));
		lbPrice.setBounds(17, 37, 47, 15);
		panel.add(lbPrice);
		
		//���� ���ݼ� up
		btnARPUp = new JButton("\u25B2");
		btnARPUp.setFont(new Font("���� ���", Font.PLAIN, 11));
		btnARPUp.setBorderPainted(false);
		btnARPUp.setBackground(new Color(255, 204, 102));
		btnARPUp.addActionListener(new ActionListener() {
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
					
					String sql = "select p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.product_date, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '����'\r\n" + 
							"order by p.price desc";
					pstmt = conn.prepareStatement(sql);
//					��ϵ� Ŀ���� �����Ű�� �����ϱ�
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						// �� Row�� ������ ����
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4), rs.getString(5), 
								rs.getInt(6), rs.getInt(7)});
						
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
		btnARPUp.setBounds(76, 29, 45, 29);
		panel.add(btnARPUp);
		
		//���� ���ݼ� down
		btnARPDown = new JButton("\u25BC");
		btnARPDown.setFont(new Font("���� ���", Font.PLAIN, 11));
		btnARPDown.setBorderPainted(false);
		btnARPDown.setBackground(new Color(255, 204, 102));
		btnARPDown.addActionListener(new ActionListener() {
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
					
					String sql = "select p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.product_date, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '����'\r\n" + 
							"order by p.price";
					pstmt = conn.prepareStatement(sql);
//					��ϵ� Ŀ���� �����Ű�� �����ϱ�
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						// �� Row�� ������ ����
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4), rs.getString(5), 
								rs.getInt(6), rs.getInt(7)});
						
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
		btnARPDown.setBounds(126, 29, 45, 29);
		panel.add(btnARPDown);
		
		//���� ��� label
		JLabel lbEa = new JLabel("\uC7AC\uACE0\uC21C");
		lbEa.setFont(new Font("���� ���", Font.BOLD, 12));
		lbEa.setBounds(17, 87, 47, 15);
		panel.add(lbEa);
		
		//���� ���� up
		btnAReaUp = new JButton("\u25B2");
		btnAReaUp.setFont(new Font("���� ���", Font.PLAIN, 11));
		btnAReaUp.setBorderPainted(false);
		btnAReaUp.addActionListener(new ActionListener() {
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
					
					String sql = "select p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.product_date, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '����'\r\n" + 
							"order by p.product_ea desc";
					pstmt = conn.prepareStatement(sql);
//					��ϵ� Ŀ���� �����Ű�� �����ϱ�
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						// �� Row�� ������ ����
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4), rs.getString(5), 
								rs.getInt(6), rs.getInt(7)});
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
		btnAReaUp.setBackground(new Color(255, 204, 102));
		btnAReaUp.setBounds(76, 81, 45, 29);
		panel.add(btnAReaUp);
		
		//���� ���� down
		btnAReaDown = new JButton("\u25BC");
		btnAReaDown.setFont(new Font("���� ���", Font.PLAIN, 11));
		btnAReaDown.setBorderPainted(false);
		btnAReaDown.addActionListener(new ActionListener() {
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
					
					String sql = "select p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.product_date, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '����'\r\n" + 
							"order by p.product_ea";
					pstmt = conn.prepareStatement(sql);
//					��ϵ� Ŀ���� �����Ű�� �����ϱ�
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						// �� Row�� ������ ����
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4), rs.getString(5), 
								rs.getInt(6), rs.getInt(7)});
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
		btnAReaDown.setBackground(new Color(255, 204, 102));
		btnAReaDown.setBounds(126, 81, 45, 29);
		panel.add(btnAReaDown);
		
		//���� ������ label
		lbKind = new JLabel("\uC81C\uD488\uC885\uB958\uBCC4");
		lbKind.setFont(new Font("���� ���", Font.BOLD, 12));
		lbKind.setBounds(17, 140, 79, 15);
		panel.add(lbKind);
		
		cbKind = new JComboBox();
		cbKind.setBackground(new Color(255, 255, 255));
		cbKind.setFont(new Font("���� ���", Font.BOLD, 12));
		cbKind.addItem("��ǰ�� �������ּ���!");
		cbKind.setSelectedItem("��ǰ�� �������ּ���!");
		cbKind.addActionListener(new ActionListener() {
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
					
					String sql = "select p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, p.product_date, p.price, p.product_ea\r\n" + 
							"from product p\r\n" + 
							"where p.product_code = '����'\r\n" + 
							"and p.product_kind = ? \r\n" + 
							"order by p.product_id";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cbKind.getSelectedItem().toString());
//					��ϵ� Ŀ���� �����Ű�� �����ϱ�
					rs = pstmt.executeQuery();
					while(rs.next()) {
						// �� Row�� ������ ����
						model.addRow(new Object[]{rs.getInt(1), rs.getString(2), 
								rs.getString(3), rs.getString(4), rs.getString(5), 
								rs.getInt(6), rs.getInt(7)});
						
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

		cbKind.setBounds(15, 168, 154, 45);
		panel.add(cbKind);
		
	}
}
