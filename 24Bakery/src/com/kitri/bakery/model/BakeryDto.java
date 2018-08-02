package com.kitri.bakery.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kitri.admin.model.BakeryDao;
import com.kitri.bakery.digain.UserMain;
import com.kitri.bakery.domain.BakeryBean;
import com.kitri.bakery.service.UserService;

public class BakeryDto {

	public Connection getDbCon() {
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
	}// getDbcon() 메소드 종료지점

	public void infoOrder() { //결제확인창에서 확인누르면 주문정보 주문table에 인서트
		Connection con = getDbCon();
		PreparedStatement pstmt = null;
		UserService us = new UserService();
		int code = 2;
		if (BakeryBean.member_id.equals("admin")) {
			code = 1;
		}
		try {
			con.setAutoCommit(false);
			for (int j = 0; j < us.list.size(); j++) {
				String sql = "insert into orders(order_id, member_id, product_id, product_ea,order_time, order_code, price)\r\n"
						+ "values(ORDER_SEQ.NEXTVAL,? , ?, ?, sysdate, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, BakeryBean.member_id);
				pstmt.setInt(2, us.list.get(j).getProduct_id());
				pstmt.setInt(3, us.list.get(j).getSu());
				pstmt.setInt(4, code);
				pstmt.setInt(5, us.list.get(j).getAllprice());
				pstmt.executeUpdate();
				con.commit();
			}
			con.setAutoCommit(true);
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<BakeryBean> oh_sdb() { // 결제내역 누르면 창띄우기 주문정보 주문table에서 가져오기
		Connection con = getDbCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BakeryBean> list = new ArrayList<BakeryBean>();
		try {
			String sql = "select o.order_id, p.PRODUCT_NAME, o.PRODUCT_EA, p.PRICE, o.price, o.ORDER_TIME\r\n" + 
					"from orders o, PRODUCT p\r\n" + 
					"where o.PRODUCT_ID = p.PRODUCT_ID\r\n" + 
					"and o.MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, BakeryBean.member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BakeryBean bb = new BakeryBean();
				bb.setOrder_id(rs.getInt(1));
				bb.setProduct_name(rs.getString(2));
				bb.setSu(rs.getInt(3));
				bb.setPrice(rs.getInt(4));
				bb.setAllprice(rs.getInt(5));
				bb.setOrder_date(rs.getString(6));
				list.add(bb);
			}
			con.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void updatePoint(int point) {// 결제하면 point 업로드 메소드 where 조건에 id값 받아와야댐
		Connection con = getDbCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update members\r\n" + 
					"set point = ?\r\n" + 
					"where MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, BakeryBean.member_id);
			pstmt.executeUpdate();
			con.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int selectPoint() { // 결제하기전 point값 받아오는 메소드 where 절에 멤머id값 받아와야댐
		Connection con = getDbCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		try {
			String sql = "select point\r\n" + 
					"from MEMBERS\r\n" + 
					"where MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, BakeryBean.member_id);
			rs = pstmt.executeQuery(); 
			rs.next();
			point = rs.getInt(1);
			con.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return point;
	}

	public List<BakeryBean> mis_sdb() {
		Connection con = getDbCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BakeryBean> list = new ArrayList<BakeryBean>();
		try {
			String sql = "select o.order_id, p.PRODUCT_NAME, o.PRODUCT_EA, p.PRICE, o.price, o.ORDER_TIME\r\n" + 
					"from orders o, PRODUCT p\r\n" + 
					"where o.PRODUCT_ID = p.PRODUCT_ID\r\n" + 
					"and o.MEMBER_ID = ?\r\n" +
					"order by o.order_time desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, BakeryBean.serchMember);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BakeryBean bb = new BakeryBean();
				bb.setOrder_id(rs.getInt(1));
				bb.setProduct_name(rs.getString(2));
				bb.setSu(rs.getInt(3));
				bb.setPrice(rs.getInt(4));
				bb.setAllprice(rs.getInt(5));
				bb.setOrder_date(rs.getString(6));
				list.add(bb);
			}
			con.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
