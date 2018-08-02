package com.kitri.login.cont;

import com.kitri.login.bean.memberBean;
import com.kitri.login.db.memberDao;

public class AddCont { // Db에 뭔가 Insert 할때 쓰는 클래스
	public void addMem(memberBean eb) {
		
		memberDao md = new memberDao();
		md.addMem(eb);

	}//addMem()종료지점

}//AddCont 종료지점
