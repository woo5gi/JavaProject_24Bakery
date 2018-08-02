package com.kitri.link;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.admin.domain.OrderBean;
import com.kitri.admin.model.BakeryDao;
import com.kitri.bakery.service.UserService;

public class Link {

	public Link() {
		UserService us = new UserService();
		Connection con = null;
		BakeryDao bd = new BakeryDao();
		con = bd.getDbCon();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
//		List<PreparedStatement> list = new ArrayList<PreparedStatement>();
		List<OrderBean> list2 = new ArrayList<OrderBean>();
		int cnt = 0;

		try {
			con.setAutoCommit(false);
			
//			while (cnt < us.list.size()) {
//				pstmt1.setInt(1, us.list.get(cnt).getProduct_id());// 주문한 id를 ? 에 넣음
//				list.add(pstmt1);
//				cnt++;
//			}
//			cnt = 0;
			
			OrderBean ob = new OrderBean();
			for (int i = 0; i < us.list.size(); i++) {
				String sql1 = "select product_ea\r\n" + "from product\r\n" + "where product_id = ?";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setInt(1, us.list.get(i).getProduct_id());
				rs = pstmt1.executeQuery();
				rs.next();
				int p_ea = rs.getInt(1);// product_ea값 받아오기
				String sql2 = "update product\r\n" + "set product_ea = ?\r\n" + "where product_id = ?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, p_ea - us.list.get(i).getSu()); // DB에 넣기
				pstmt2.setInt(2, us.list.get(i).getProduct_id());
				pstmt2.executeUpdate();
				}
			



			con.setAutoCommit(true);
			pstmt1.close();
			pstmt2.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
