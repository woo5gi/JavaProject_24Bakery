package com.kitri.login.db;

import java.sql.*;
import java.util.*;

import com.kitri.login.bean.memberBean;
import com.kitri.login.view.info;

public class memberDao {
	// DB ���� Ŭ����

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

	public Vector getMemberList() {
		// Member_List�� JTable ������ Vector�� �����ͼ� �ѷ��ִ� �޼ҵ�

		Vector data = new Vector();// ù��° Vector ����
		Connection con = null; // ����
		PreparedStatement pstmt = null; // ���
		ResultSet rs = null; // ���
		List<memberBean> list = new ArrayList<memberBean>(); // memberBean�� ������ ArrayList�� ����
		try {
			con = getDbCon(); // DB ���� �޼ҵ�
			String sql = "select member_id, mem_name, mem_pw, gender, e_mail, phone_number, " 
			+ "address from members"; // ����
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // ����

			while (rs.next()) {
				memberBean eb = new memberBean();

				eb.setName(rs.getString(1));// �����ͼ� ����
				eb.setMember_id(rs.getString(2));
				eb.setPassword(rs.getString(3));
				eb.setGender(rs.getString(4));
				eb.setEmail(rs.getString(5));
				eb.setPhone_number(rs.getString(6));
				eb.setAddress(rs.getString(7));
				list.add(eb);

				Vector row = new Vector();// �ι�° ���� ����

				row.add(eb.getName());
				row.add(eb.getMember_id());
				row.add(eb.getPassword());
				row.add(eb.getGender());
				row.add(eb.getEmail());
				row.add(eb.getPhone_number());
				row.add(eb.getAddress());

				data.add(row);// �Ʒ��� �� �߰�

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}// getMemberList()
	

	public void addMem(memberBean eb) { // ȸ������ insert �޼ҵ�
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
			info i = new info();// ������ ���̵� �ߺ�üũ �ؾߵ� â ��
		}

	}// addMem() ��������

	public memberBean getEachInfo(String empno) { // DB�� �ش� ���̵� �ִ��� ���� �� Ȯ��, �ߺ�üũ�� loginView ID���� ��
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
	} // getEachInfo()�޼ҵ� ���� ����

	public memberBean getEachInfo2(String id_s) {// ��� ���̵�� �˻��� ��й�ȣ�� �������� �޼ҵ�

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
	} // getEachInfo2()�޼ҵ� ���� ����
	
	public memberBean getEachInfo3(int point_s,String id_s) {// ��� ���̵�� �˻��� ����Ʈ �� ������Ʈ

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
	} // getEachInfo3()�޼ҵ� ���� ����
}
