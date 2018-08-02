package com.kitri.pointAdd;

import java.sql.*;


import com.kitri.login.bean.memberBean;
import com.kitri.login.view.loginView;


public class pointDao {
	public Connection getDbCon() {
		// DB연결 메소드
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
	}// getDbCon() 종료지점

	public void getEachInfo3(memberBean mod_eb) {//point 과거+입력받은거  DB업데이트 
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

	public memberBean getEachInfo4(String empno) {// 멤버 아이디로 검색해 eb저장
		// 사원번호로 DB 접근 후 자료값 설정하여 관리하기
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
			
	
	}//getEachInfo4 종료지점
}// class 종료지점
