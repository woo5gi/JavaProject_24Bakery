package com.kitri.login.cont;

import com.kitri.login.bean.memberBean;
import com.kitri.login.db.memberDao;



public class SearchCont {//DB���� ���̵� �� �� ����� ã�ƿ�(Dao���� select ���°͵� ã�ƿ��� Ŭ����)

	public memberBean searchEachEmp(String empno) {
		// DB���� ���̵�����
		memberDao ed = new memberDao();
		memberBean eb = ed.getEachInfo(empno);
		return eb;		
	} // searchEachEmp() ���� ����
	
	public memberBean searchEachEmp2(String id_s, String passwd_s) {
		//DB���� ���������
		memberDao ed = new memberDao();
		memberBean eb = ed.getEachInfo2(id_s);
		return eb;		
	} // searchEachEmp2() ���� ����
	
	public memberBean searchEachEmp3(int point_s, String id_s) {
		//DB���� id������
		memberDao ed = new memberDao();
		memberBean eb = ed.getEachInfo3(point_s, id_s);
		return eb;		
	} // searchEachEmp2() ���� ����

} // SearchCont ��ü ���� ����
