package com.kitri.pointAdd;

import com.kitri.login.bean.memberBean;

public class AddCont {

	public void modEmp(memberBean mod_eb) {
		//수정된 값 반영시키는 기능(Update)
		pointDao ed = new pointDao();
		ed.getEachInfo3(mod_eb);
		
	}
}
