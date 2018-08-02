// 이 곳에 DB를 연결하여 회원 정보 값을 받게 한다.
// 메소드 3개   조회, 수정, 탈퇴
// DB에서 하는거
package com.kitri.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.kitri.bakery.domain.BakeryBean;
import com.kitri.domain.UserBean;
import com.kitri.lookup.User_Lookup_view;
import com.kitri.secession.User_Secessions;


public class UserDao {
	
//	User_Lookup_view ulv = new User_Lookup_view();// User_Secession의 idTxf의 값을 t로 받아서 where에 넣는다.
//	String t=ulv.us.idTxf.getText();
	
	public static List<UserBean> list = new ArrayList<UserBean>();
	
	public void getAllInfo(){	// 조회 메소드 ###
		
		list.clear();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
				String id = "kitri";
				String pass = "kitri";
				conn = DriverManager.getConnection(url,id,pass);
				String sql = "select member_id, mem_pw, mem_name, e_mail, phone_number, gender, address, point\r\n" + 
						"from members\r\n" + 
						"where member_id = ?";
				
				System.out.println("데이터가 연동 되었습니다.");
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, BakeryBean.member_id);
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {
					UserBean ub = new UserBean();
					ub.setMember_id(rs.getString(1));
					ub.setMem_pw(rs.getString(2));
					ub.setMem_name(rs.getString(3));
					ub.setE_mail(rs.getString(4));
					ub.setPhone_number(rs.getString(5));
					ub.setGender(rs.getString(6));
					ub.setAddress(rs.getString(7));
					ub.setPoint(rs.getInt(8));
					UserBean.mem_point = rs.getInt(8);
					
					list.add(ub);	// list가 ub의 값을 받는다.
					

				}
				rs.close();
				pstmt.close();
				conn.close();
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		
		
	}// 조회 메소드 끝
	
	public UserBean getEachInfo(String ids) { // ids로 회원을 조회
		Connection con = getDbCon();
		String sql = " mem_pw, mem_name, e_mail, phone_number, gender, address" + 
				"		from Members\r\n" + 
				"		where member_id =?";
		
		
		
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				UserBean ub = new UserBean();
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "member_id");
					//pstmt.setInt(1, Integer.parseInt(ids));
					rs = pstmt.executeQuery();
					while (rs.next()) {

					
//					ub.setPasswd(rs.getString(2));
//					ub.setNames(rs.getString(3));
//					ub.setEmail(rs.getString(4));
//					ub.setAddress(rs.getString(5));
//					ub.setIds(rs.getString(6));
					
					
					ub.setMem_pw(rs.getString(2));
					ub.setMem_name(rs.getString(3));
					ub.setE_mail(rs.getString(4));
					ub.setPhone_number(rs.getString(5));
					ub.setGender(rs.getString(6));
					ub.setAddress(rs.getString(7));
					
					
				} 
					rs.close();
					pstmt.close();
					con.close();
					
				}	catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
		return ub;
	}
	
	
	public  Connection getDbCon() {
		Connection con = null;
		
		try {
			getClass().forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.12.27:1521:orcl";
			String id = "kitri";
			String pass = "kitri";
			try {
				con = DriverManager.getConnection(url,id,pass);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
		
	}
	
	public void getUserDTO(String member_id) {	// ub에지정된 회원의 정보를 rs에서 가져온다
		UserBean ub = new UserBean();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = getDbCon();
			String sql = "select * from Members where = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "member_id");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
//				ub.setIds(rs.getString("ids"));
//				ub.setPasswd(rs.getString("passwd"));
//				ub.setNames(rs.getString("names"));
//				ub.setEmail(rs.getString("email"));
//				ub.setAddress(rs.getString("address"));
				
				ub.setMember_id(rs.getString("member_id"));
				ub.setMem_pw(rs.getString("mem_pw"));
				ub.setMem_name(rs.getString("mem_name"));
				ub.setE_mail(rs.getString("e_mail"));
				ub.setPhone_number(rs.getString("phone_number"));
				ub.setGender(rs.getString("gender"));
				ub.setAddress(rs.getString("address"));
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
//	public String User_Lookup_cont_id() {	// 회원 조회 입력 값 넣기
//		UserDao ud = new UserDao();
//		List<UserBean> list = ud.getAllInfo();
//		
//		String val = list.get(0).getMember_id();	// list에 index 0번째 자리에 있는 getIds 를 가져온다.
//		return val;
//	}
//
//	public String User_Lookup_cont_pw() {
//		UserDao ud = new UserDao();
//		List<UserBean> list = ud.getAllInfo();
//		
//		String val2 = list.get(0).getMem_pw();
//		return val2;
//	}
//	
//	public String User_Lookup_cont_nm() {
//		UserDao ud = new UserDao();
//		List<UserBean> list = ud.getAllInfo();
//		
//		String val3 = list.get(0).getMem_name();
//		return val3;
//	}
//	
//	
//	public String User_Lookup_cont_em() {
//		UserDao ud = new UserDao();
//		List<UserBean> list = ud.getAllInfo();
//		
//		String val6 = list.get(0).getE_mail();
//		return val6;
//	}
//	
//	public String User_Lookup_cont_ad() {
//		UserDao ud = new UserDao();
//		List<UserBean> list = ud.getAllInfo();
//		
//		String val7 = list.get(0).getAddress();
//		return val7;
//	}
//	
//	public String User_Lookup_cont_pn() {
//		UserDao ud = new UserDao();
//		List<UserBean> list = ud.getAllInfo();
//		
//		String val8 = list.get(0).getPhone_number();
//		return val8;
//	}
//	
//	public String User_Lookup_cont_gn() {
//		UserDao ud = new UserDao();
//		List<UserBean> list = ud.getAllInfo();
//		
//		String val9 = list.get(0).getGender();
//		return val9;
//	}
	
	
	
	
	public void modUser(UserBean ub) { //업데이트
		Connection con = null;
		con = getDbCon();
		PreparedStatement pstmt = null;
		String sql = "update Members\r\n" + 
					"set mem_pw = ?, mem_name = ? , e_mail = ?, phone_number = ?, gender = ?"
					+ ", address = ?"
					+ "where member_id = ?";
		
		 
		try {
			pstmt = con.prepareStatement(sql);
			 
			
		    pstmt.setString(1, ub.getMem_pw());
			pstmt.setString(2, ub.getMem_name());
			pstmt.setString(3, ub.getE_mail());
			pstmt.setString(4, ub.getPhone_number());
			pstmt.setString(5, ub.getGender());
			pstmt.setString(6, ub.getAddress());
			pstmt.setString(7, ub.getMember_id());
			
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
			System.out.println(ub.getMem_name());	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void delete(UserBean ub) {
        Connection con = null;
        con = getDbCon();
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt = null;
        
    
        String sql = "update ORDERS\r\n" + 
                "set MEMBER_ID = '이타치'\r\n" + 
                "where MEMBER_ID = ?";
        String deleteSQL = "delete\r\n" + 
                "from members\r\n" + 
                "where member_id = ?";
        try {
            pstmt1 = con.prepareStatement(sql);
            pstmt1.setString(1, BakeryBean.member_id);
            pstmt1.executeQuery();
            //System.out.println(ub.getMember_id());
            pstmt = con.prepareStatement(deleteSQL);
            pstmt.setString(1, ub.getMember_id());    //  us에 있는 member_id에 값
            
            pstmt.executeUpdate();
            pstmt1.close();
            pstmt.close();
            con.close();
            System.out.println("하이하이");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}




















