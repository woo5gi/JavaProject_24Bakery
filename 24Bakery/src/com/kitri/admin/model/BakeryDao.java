package com.kitri.admin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.admin.domain.OrderBean;

public class BakeryDao {
	public Connection getDbCon() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
			String id = "kitri";
			String pass = "kitri";
			con = DriverManager.getConnection(url, id, pass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public List<OrderBean> getResev() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getDbCon();
		List<OrderBean> list1 = new ArrayList<OrderBean>();
		String sql = "select o.order_id, o.member_id, p.product_name, o.product_ea, p.price, o.order_code\r\n" + 
				"from orders o, Product p\r\n" + 
				"where o.product_id = p.product_id \r\n" + 
				"and o.order_code = 2\r\n" + 
				"order by order_id desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrder_id(rs.getInt(1));
				ob.setMember_id(rs.getString(2));
				ob.setProduct_name(rs.getString(3));
				ob.setProduct_ea(rs.getInt(4));
				ob.setPrice(rs.getInt(5));
				ob.setOrder_code(rs.getInt(6));
				list1.add(ob);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list1;
	}
	
	public List<OrderBean> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getDbCon();
		List<OrderBean> list = new ArrayList<OrderBean>();
		String sql = " select o.order_id, o.member_id, p.product_name, o.product_ea, p.price, o.order_code\r\n" + 
				"from orders o, Product p\r\n" + 
				"where o.product_id = p.product_id \r\n" + 
				"order by order_id desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrder_id(rs.getInt(1));
				ob.setMember_id(rs.getString(2));
				ob.setProduct_name(rs.getString(3));
				ob.setProduct_ea(rs.getInt(4));
				ob.setPrice(rs.getInt(5));
				list.add(ob);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
	
	public List<OrderBean> getBread() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getDbCon();
		List<OrderBean> list = new ArrayList<OrderBean>();
		String sql = "select o.product_id, p.product_name, sum(o.product_ea) sum \r\n" + 
				"from orders o, product p\r\n" + 
				"where o.product_id = p.product_id\r\n" + 
				"    and o.Order_time > sysdate - 7\r\n" + 
				"group by o.product_id, p.product_name\r\n" + 
				"order by sum(o.product_ea) desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setProduct_id(rs.getInt(1));
				ob.setProduct_name(rs.getString(2));
				ob.setSum(rs.getInt(3));
				list.add(ob);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
	
	public List<OrderBean> getSale() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getDbCon();
		List<OrderBean> list = new ArrayList<OrderBean>();
			String sql = "select to_char(o.Order_Time,'mm\"¿ù\"dd\"ÀÏ\"'), o.product_id, o.product_ea, P.Price\r\n" + 
					"from orders o, product p\r\n" + 
					"where o.product_id = p.product_id\r\n" + 
					"    and Order_time > sysdate - 7\r\n" + 
					"    order by o.order_time";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setOrder_time(rs.getString(1));
				ob.setProduct_id(rs.getInt(2));
				ob.setProduct_ea(rs.getInt(3));
				ob.setPrice(rs.getInt(4));
				list.add(ob);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

}
