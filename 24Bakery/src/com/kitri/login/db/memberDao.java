package com.kitri.login.db;

import java.sql.*;
import java.util.*;

import com.kitri.login.bean.memberBean;
import com.kitri.login.view.info;

public class memberDao {
	// DB 관련 클래스

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

	public Vector getMemberList() {
		// Member_List의 JTable 데이터 Vector로 가져와서 뿌려주는 메소드

		Vector data = new Vector();// 첫번째 Vector 선언
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 명령
		ResultSet rs = null; // 결과
		List<memberBean> list = new ArrayList<memberBean>(); // memberBean의 데이터 ArrayList로 저장
		try {
			con = getDbCon(); // DB 연결 메소드
			String sql = "select member_id, mem_name, mem_pw, gender, e_mail, phone_number, " 
			+ "address from members"; // 쿼리
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 실행

			while (rs.next()) {
				memberBean eb = new memberBean();

				eb.setName(rs.getString(1));// 가져와서 저장
				eb.setMember_id(rs.getString(2));
				eb.setPassword(rs.getString(3));
				eb.setGender(rs.getString(4));
				eb.setEmail(rs.getString(5));
				eb.setPhone_number(rs.getString(6));
				eb.setAddress(rs.getString(7));
				list.add(eb);

				Vector row = new Vector();// 두번째 벡터 선언

				row.add(eb.getName());
				row.add(eb.getMember_id());
				row.add(eb.getPassword());
				row.add(eb.getGender());
				row.add(eb.getEmail());
				row.add(eb.getPhone_number());
				row.add(eb.getAddress());

				data.add(row);// 아래로 줄 추가

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}// getMemberList()
	

	public void addMem(memberBean eb) { // 회원가입 insert 메소드
		Connection con = null;
		con = getDbCon();

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO members(member_id, mem_name, mem_pw, gender, e_mail, phone_number, address)\r\n"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, eb.getMember_id());
			pstmt.setString(2, eb.getName());
			pstmt.setString(3, eb.getPassword());
			pstmt.setString(4, eb.getGender());
			pstmt.setString(5, eb.getEmail());
			pstmt.setString(6, eb.getPhone_number());
			pstmt.setString(7, eb.getAddress());
			pstmt.executeUpdate();

			con.setAutoCommit(true);
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			info i = new info();// 오류시 아이디 중복체크 해야됨 창 뜸
		}

	}// addMem() 종료지점

	public memberBean getEachInfo(String empno) { // DB에 해당 아이디 있는지 없는 지 확인, 중복체크와 loginView ID에서 씀
		Connection con = getDbCon();
		String sql = "SELECT member_id from members where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memberBean eb = new memberBean();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				eb.setMember_id(rs.getString(1));
			}
			String s = eb.getMember_id();
			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return eb;
	} // getEachInfo()메소드 종료 지점

	public memberBean getEachInfo2(String id_s) {// 멤버 아이디로 검색해 비밀번호값 가져오는 메소드

		Connection con = getDbCon();
		String sql = "SELECT member_id, mem_pw from members where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memberBean eb = new memberBean();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id_s);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				eb.setMember_id(rs.getString(1));
				eb.setPassword(rs.getString(2));
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return eb;
	} // getEachInfo2()메소드 종료 지점
	
	public memberBean getEachInfo3(int point_s,String id_s) {// 멤버 아이디로 검색해 포인트 값 업데이트

		Connection con = getDbCon();
		String sql = "update members set point = '?'" + 
				"where member_id = '?'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memberBean eb = new memberBean();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point_s);
			pstmt.setString(2, id_s);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				eb.setPoint(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return eb;
	} // getEachInfo3()메소드 종료 지점
}
