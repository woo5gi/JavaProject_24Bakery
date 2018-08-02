package com.kitri.lookup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

import com.kitri.bakery.digain.UserMain;
import com.kitri.domain.UserBean;
import com.kitri.login.bean.memberBean;
import com.kitri.login.view.loginView;
import com.kitri.model.UserDao;
import com.kitri.pointAdd.point_Add;
import com.kitri.update.User_Update;

public class User_Lookup_cont implements ActionListener {

	// static UserBean ub = new UserBean();
	User_Lookup_view ulv; // 이렇게 만들어 놓으면 밑에서 여러개 사용 할 수 있다. 그리고 ulv에 update의 생성자가 들어 가 있어서
							// ### ulv.up.idTxf 이런 식으로 하여 사용 해야 데이터가 들어간다.

	UserDao ud = new UserDao();

	public User_Lookup_cont(User_Lookup_view ulv) { // ulv에서 setvisible 하기위한 생성자
		this.ulv = ulv;
	}

	// public static void main(String[] args) {
	// UserBean mod_ub = new UserBean(); // 수정
	//
	// mod_ub.setEmployee_id(ub.getEmployee_id());
	// mod_ub.setFirst_name(ub.getFirst_name());

	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		UserBean ub = new UserBean();
 
		if (ob == ulv.bt1) { // 업데이트창 으로 이동
			//ulv.up = new User_Update();	// User_Update 에서 바로 받아와버렸다. actionListener가 없는 클래스를 받아 와서 안된 것 이였다.
			ulv.up.setVisible(true);
			ulv.dispose();	
//			String id = ulv.idTxf.getText(); // ulv의 txf의 값을 up로 이동
			ulv.up.idTxf.setText(ud.list.get(0).getMember_id());
//			String pass = ulv.passwordField.getText();
//			ulv.up.passwordField.setText(pass);
//			String name = ulv.nameTxf.getText();
			ulv.up.nameTxf.setText(ud.list.get(0).getMem_name());
//			String mail = ulv.emailTxf.getText();
			ulv.up.emailTxf.setText(ud.list.get(0).getE_mail());
//			String address = ulv.textField_1.getText();
			ulv.up.adresTxf.setText(ud.list.get(0).getAddress());
//			String phone = ulv.phoneTxf.getText();
			ulv.up.phoneTxf.setText(ud.list.get(0).getPhone_number());
//			String gender = ulv.gender1.getText();
			ulv.up.gender.setText(ud.list.get(0).getGender());

		} else if (ob == ulv.bt2) {// 탈퇴창으로 이동(delete)
			ulv.us.setVisible(true);
			ulv.dispose();
			
			
			ulv.us.idTxf.setText(ud.list.get(0).getMember_id());
			
//			String pass;
//			pass = ulv.passTxf.getText();
//			ulv.us.passTxf.setText(pass);
			

		} else if (ob == ulv.bt3) {// 취소(화면 종료)
			ulv.setVisible(false);
			UserMain um = new UserMain();
			um.point_lb.setText(String.valueOf(UserBean.mem_point) + "루비");
			um.setVisible(true);
		 // User_Lookup_view 끝
			
		} else if (ob == ulv.up.bt1) { // 수정창 에서 수정된 내용 DB에 저장
			ud.getAllInfo();
			System.out.println(ulv.up.passwordField.getText());
//			if(ulv.up.passwordField.getText().equals(ud.User_Lookup_cont_pw()) ) {	// 버튼을 눌렀을때 조건을 비교 하는 것 이기 때문에 get이다. passwordfield에 있는 걸 get으로 불러오는 거다.
//				
//			}
			
			if(ulv.up.passwordField.getText().equals("") || ulv.up.nameTxf.getText().equals("")) {				// 조건을 달았다.
				System.out.println("dd");
				ulv.up.lb_inform.setVisible(true);
				
				return;
			}
			else {
				ub.setMember_id(ulv.up.idTxf.getText());
				ub.setMem_pw(ulv.up.passwordField.getText());
				ub.setMem_name(ulv.up.nameTxf.getText());
				ub.setE_mail(ulv.up.emailTxf.getText());
				ub.setAddress(ulv.up.adresTxf.getText());
				ub.setPhone_number(ulv.up.phoneTxf.getText());
				ub.setGender(ulv.up.gender.getText());
				//	System.out.println(ub.getMem_pw());
				//			System.out.println(ub.password);
				ud.modUser(ub);
				//			ulv.up.comfile.setText("수정된 내용이 저장되었습니다.");
				ulv.up.dispose();
				ulv.uum.setVisible(true);
			}
//			else if(ulv.up.nameTxf == null) {
//				ulv.up.lb_inform.setVisible(true);
//			else if(ud.list.get(0).getMem_pw() != null && ud.list.get(0).getMem_name() != null
//					&& ud.list.get(0).getE_mail() != null && ud.list.get(0).getAddress() != null &&
//					ud.list.get(0).getPhone_number() != null && ud.list.get(0).getGender() != null) {

//			}
		
		}else if(ob == ulv.uum.btnNewButton) {
				ulv.uum.dispose();
				ulv.setVisible(true);
			
				ud.getAllInfo();
				
				ulv.idTxf.setText(ud.list.get(0).getMember_id());
				ulv.passwordField.setText(ud.list.get(0).getMem_pw());
				ulv.nameTxf.setText(ud.list.get(0).getMem_name());
				ulv.emailTxf.setText(ud.list.get(0).getE_mail());
				ulv.address.setText(ud.list.get(0).getAddress());
				ulv.phoneTxf.setText(ud.list.get(0).getPhone_number());
				ulv.gender1.setText(ud.list.get(0).getGender());
			
			
		} else if (ob == ulv.up.bt3) {// User_Update 종료
			ulv.up.dispose();
			ulv.setVisible(true);
			
			ud.getAllInfo();
			
			ulv.idTxf.setText(ud.list.get(0).getMember_id());
			ulv.passwordField.setText(ud.list.get(0).getMem_pw());
			ulv.nameTxf.setText(ud.list.get(0).getMem_name());
			ulv.emailTxf.setText(ud.list.get(0).getE_mail());
			ulv.address.setText(ud.list.get(0).getAddress());
			ulv.phoneTxf.setText(ud.list.get(0).getPhone_number());
			ulv.gender1.setText(ud.list.get(0).getGender());
		//	System.exit(0);
			
			
//		} else if(ulv.idTxf == ulv.us.idTxf && ulv.passTxf == ulv.us.passTxf && ob == ulv.us.success) {
		}else if(ob == ulv.us.success) {	// Success 창에서 탈퇴 버튼을 누르면
			
			ud.getAllInfo();
			ulv.us.label_1.setVisible(true);
			
			
	    	if(ud.list.get(0).getMem_pw().equals(ulv.us.passwordField.getText())) {
				
			
//			System.out.println(ulv.us.idTxf.getText());	// 값을 찍어봤다.
			ub.setMember_id(ulv.us.idTxf.getText());		// up에서 가져오는 거라 생각했는데, 아니다. User_Update의 idTxf에 있는 것을 delete 하는 것 이다.
//			ub.setMem_pw(ulv.up.passTxf.getText());
//			ub.setMem_name(ulv.up.nameTxf.getText());
//			ub.setE_mail(ulv.up.emailTxf.getText());
//			ub.setAddress(ulv.up.adresTxf.getText());
//			ub.setPhone_numbe2r(ulv.up.phoneTxf.getText());
//			ub.setGender(ulv.up.gender.getText());

			ud.delete(ub);
			
			ulv.us.dispose();
			System.out.println(ulv.us.idTxf.getText() + "님이 탈퇴 하였습니다.");
			loginView lv = new loginView();
			lv.setVisible(true);
			}else {
			System.out.println("탈퇴가 이루어지지 않았습니다.");
			}
	    	
		}else if(ob == ulv.usm.bt_exit) {
			System.out.println("종료");
	    		ulv.usm.dispose();
	    		System.exit(0);
	    	
		
		} else if(ob == ulv.us.exit) {		// Success 창에서 취소 버튼
			ulv.us.dispose();
			ulv.setVisible(true);
			
			//System.exit(0);
			
		} else if (ob == ulv.LubyCharge) {
			loginView lv = new loginView();
			lv.dispose();
			memberBean mb = new memberBean();
            point_Add pa = new point_Add();
            mb = lv.idGet();
            pa.setVisible(true);
            ulv.dispose();
		}

	}

}
