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
	User_Lookup_view ulv; // �̷��� ����� ������ �ؿ��� ������ ��� �� �� �ִ�. �׸��� ulv�� update�� �����ڰ� ��� �� �־
							// ### ulv.up.idTxf �̷� ������ �Ͽ� ��� �ؾ� �����Ͱ� ����.

	UserDao ud = new UserDao();

	public User_Lookup_cont(User_Lookup_view ulv) { // ulv���� setvisible �ϱ����� ������
		this.ulv = ulv;
	}

	// public static void main(String[] args) {
	// UserBean mod_ub = new UserBean(); // ����
	//
	// mod_ub.setEmployee_id(ub.getEmployee_id());
	// mod_ub.setFirst_name(ub.getFirst_name());

	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		UserBean ub = new UserBean();
 
		if (ob == ulv.bt1) { // ������Ʈâ ���� �̵�
			//ulv.up = new User_Update();	// User_Update ���� �ٷ� �޾ƿ͹��ȴ�. actionListener�� ���� Ŭ������ �޾� �ͼ� �ȵ� �� �̿���.
			ulv.up.setVisible(true);
			ulv.dispose();	
//			String id = ulv.idTxf.getText(); // ulv�� txf�� ���� up�� �̵�
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

		} else if (ob == ulv.bt2) {// Ż��â���� �̵�(delete)
			ulv.us.setVisible(true);
			ulv.dispose();
			
			
			ulv.us.idTxf.setText(ud.list.get(0).getMember_id());
			
//			String pass;
//			pass = ulv.passTxf.getText();
//			ulv.us.passTxf.setText(pass);
			

		} else if (ob == ulv.bt3) {// ���(ȭ�� ����)
			ulv.setVisible(false);
			UserMain um = new UserMain();
			um.point_lb.setText(String.valueOf(UserBean.mem_point) + "���");
			um.setVisible(true);
		 // User_Lookup_view ��
			
		} else if (ob == ulv.up.bt1) { // ����â ���� ������ ���� DB�� ����
			ud.getAllInfo();
			System.out.println(ulv.up.passwordField.getText());
//			if(ulv.up.passwordField.getText().equals(ud.User_Lookup_cont_pw()) ) {	// ��ư�� �������� ������ �� �ϴ� �� �̱� ������ get�̴�. passwordfield�� �ִ� �� get���� �ҷ����� �Ŵ�.
//				
//			}
			
			if(ulv.up.passwordField.getText().equals("") || ulv.up.nameTxf.getText().equals("")) {				// ������ �޾Ҵ�.
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
				//			ulv.up.comfile.setText("������ ������ ����Ǿ����ϴ�.");
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
			
			
		} else if (ob == ulv.up.bt3) {// User_Update ����
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
		}else if(ob == ulv.us.success) {	// Success â���� Ż�� ��ư�� ������
			
			ud.getAllInfo();
			ulv.us.label_1.setVisible(true);
			
			
	    	if(ud.list.get(0).getMem_pw().equals(ulv.us.passwordField.getText())) {
				
			
//			System.out.println(ulv.us.idTxf.getText());	// ���� ���ô�.
			ub.setMember_id(ulv.us.idTxf.getText());		// up���� �������� �Ŷ� �����ߴµ�, �ƴϴ�. User_Update�� idTxf�� �ִ� ���� delete �ϴ� �� �̴�.
//			ub.setMem_pw(ulv.up.passTxf.getText());
//			ub.setMem_name(ulv.up.nameTxf.getText());
//			ub.setE_mail(ulv.up.emailTxf.getText());
//			ub.setAddress(ulv.up.adresTxf.getText());
//			ub.setPhone_numbe2r(ulv.up.phoneTxf.getText());
//			ub.setGender(ulv.up.gender.getText());

			ud.delete(ub);
			
			ulv.us.dispose();
			System.out.println(ulv.us.idTxf.getText() + "���� Ż�� �Ͽ����ϴ�.");
			loginView lv = new loginView();
			lv.setVisible(true);
			}else {
			System.out.println("Ż�� �̷������ �ʾҽ��ϴ�.");
			}
	    	
		}else if(ob == ulv.usm.bt_exit) {
			System.out.println("����");
	    		ulv.usm.dispose();
	    		System.exit(0);
	    	
		
		} else if(ob == ulv.us.exit) {		// Success â���� ��� ��ư
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
