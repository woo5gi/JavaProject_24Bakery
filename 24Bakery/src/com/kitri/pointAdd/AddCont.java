package com.kitri.pointAdd;

import com.kitri.login.bean.memberBean;

public class AddCont {

	public void modEmp(memberBean mod_eb) {
		//������ �� �ݿ���Ű�� ���(Update)
		pointDao ed = new pointDao();
		ed.getEachInfo3(mod_eb);
		
	}
}
