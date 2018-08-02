package com.kitri.login.cont;

import com.kitri.login.bean.memberBean;
import com.kitri.login.db.memberDao;



public class SearchCont {//DB에서 아이디 값 및 비번값 찾아옴(Dao에서 select 쓰는것들 찾아오는 클래스)

	public memberBean searchEachEmp(String empno) {
		// DB에서 아이디가져옴
		memberDao ed = new memberDao();
		memberBean eb = ed.getEachInfo(empno);
		return eb;		
	} // searchEachEmp() 종료 지점
	
	public memberBean searchEachEmp2(String id_s, String passwd_s) {
		//DB에서 비번가져옴
		memberDao ed = new memberDao();
		memberBean eb = ed.getEachInfo2(id_s);
		return eb;		
	} // searchEachEmp2() 종료 지점
	
	public memberBean searchEachEmp3(int point_s, String id_s) {
		//DB에서 id가져옴
		memberDao ed = new memberDao();
		memberBean eb = ed.getEachInfo3(point_s, id_s);
		return eb;		
	} // searchEachEmp2() 종료 지점

} // SearchCont 객체 종료 지점
