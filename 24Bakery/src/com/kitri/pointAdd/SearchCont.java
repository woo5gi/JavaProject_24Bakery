package com.kitri.pointAdd;

import com.kitri.login.bean.memberBean;

public class SearchCont {
	public memberBean searchEachEmp4(String empno) {
		// 사원번호로 검색하여 사원 정보 조회하기
		// Model (Dao)를 실행하여 DB에서부터 값을 받아 오도록 구성하기
		pointDao ed = new pointDao();
		memberBean eb = ed.getEachInfo4(empno);
		
		return eb;		
	} // searchEachEmp4() 종료 지점
}
