package com.kitri.pointAdd;

import java.sql.*;


import com.kitri.login.bean.memberBean;
import com.kitri.login.view.loginView;


public class pointDao {
	public Connection getDbCon() {
		// DB���� �޼ҵ�
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
			String id = "kitri";
			String pass = "kitri";
			con = DriverManager.getConnection(url, id, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}// getDbCon() ��������

	public void getEachInfo3(memberBean mod_eb) {//point ����+�Է¹�����  DB������Ʈ 
		Connection con = null;
		con = getDbCon();
		PreparedStatement pstmt = null;
		memberBean eb = new memberBean();
		String sql = "UPDATE members\r\n"
				+ "SET point = ?\r\n"
				+ "WHERE member_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mod_eb.getPoint() + eb.getPoint());
			pstmt.setString(2, mod_eb.getMember_id());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public memberBean getEachInfo4(String empno) {// ��� ���̵�� �˻��� eb����
		// �����ȣ�� DB ���� �� �ڷᰪ �����Ͽ� �����ϱ�
				Connection con = getDbCon();
				String sql = "SELECT member_id,point\r\n"
						+ "FROM members\r\n" + "WHERE member_id  = ?";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				memberBean eb = new memberBean();
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1,empno);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						eb.setMember_id(rs.getString(1));
						eb.setPoint(rs.getInt(2));
						
//					rs.close();
//					pstmt.close();
//					con.close();			

				}
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return eb;
			
	
	}//getEachInfo4 ��������
}// class ��������
