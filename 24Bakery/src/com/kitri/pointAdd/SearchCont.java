package com.kitri.pointAdd;

import com.kitri.login.bean.memberBean;

public class SearchCont {
	public memberBean searchEachEmp4(String empno) {
		// �����ȣ�� �˻��Ͽ� ��� ���� ��ȸ�ϱ�
		// Model (Dao)�� �����Ͽ� DB�������� ���� �޾� ������ �����ϱ�
		pointDao ed = new pointDao();
		memberBean eb = ed.getEachInfo4(empno);
		
		return eb;		
	} // searchEachEmp4() ���� ����
}
