package com.kitri.login.cont;

import com.kitri.login.bean.memberBean;
import com.kitri.login.db.memberDao;

public class AddCont { // Db�� ���� Insert �Ҷ� ���� Ŭ����
	public void addMem(memberBean eb) {
		
		memberDao md = new memberDao();
		md.addMem(eb);

	}//addMem()��������

}//AddCont ��������
