package com.kitri.bakery.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.kitri.admin.model.BakeryDao;
import com.kitri.admin.project.Pro_Admin;
import com.kitri.bakery.digain.UserMain;
import com.kitri.bakery.domain.BakeryBean;
import com.kitri.bakery.model.BakeryDto;
import com.kitri.link.Link;
import com.kitri.login.view.loginView;
import com.kitri.lookup.User_Lookup_view;

public class UserService implements ActionListener {

	public UserMain um;
	loginView lv = new loginView();
	public static List<BakeryBean> list = new ArrayList<BakeryBean>();
	public static List<BakeryBean> list2 = new ArrayList<BakeryBean>();
	int allprice = 0;
	int oldsu = 0;
	int newsu = 0;
	public UserService() {

	}

	public UserService(UserMain um) {
		this.um = um;
	}

	@Override
	public void actionPerformed(ActionEvent e) {// um 메인창 , um.up 결제창 , um.pc 결제확인창 , um.oh 결제내역창
		Object ob = e.getSource();
		Pro_Admin pa = new Pro_Admin();
		if (ob == um.lo_btn) { // 로그아웃
			um.dispose();
			if(BakeryBean.member_id != "admin") {
				lv.setVisible(true);
			} else {
				pa.setVisible(true);
		}
		} else if (ob == um.ph_btn) {// 결제내역
			um.oh.setVisible(true);
			if (BakeryBean.member_id.equals("admin")) {
				
			} else {
				oh_Process();
			}
		} else if (ob == um.u_btn) { // 회원정보 보기
			// 회원정보 창 뜨우기
			User_Lookup_view us = new User_Lookup_view();
			us.setVisible(true);
			um.dispose();
		} else if (ob == um.p_btn) {// 결제
			// 결제정보 값전달
			  if (um.o_model.getRowCount() == 0) {
	                JOptionPane.showMessageDialog(null, "주문을 넣어주세요", "경고", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else {
	                um.dispose();
	                um.up.setVisible(true);
	                payProcess();
	            }
	        } else if (ob == um.plus_btn) {// 추가
	            if (um.m_table.getSelectedRow() == -1) {
	                JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요.", "경고", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else {
	                int row = um.m_table.getSelectedRow();
	                oldsu = Integer.parseInt(String.valueOf(um.m_model.getValueAt(row, 4)));
	                newsu = Integer.parseInt(um.su_spinner.getValue().toString());
	                if (Integer.parseInt(String.valueOf(um.su_spinner.getValue())) < 1) {
	                    JOptionPane.showMessageDialog(null, "수량은 0개 이하가 될수없습니다.", "경고", JOptionPane.ERROR_MESSAGE);
	                    return;
	                } else if (oldsu < newsu) {
	                    JOptionPane.showMessageDialog(null, "수량을 초과했습니다.", "경고", JOptionPane.ERROR_MESSAGE);
	                    return;
	                } else {
	                    plusProcess();
	                }
	            }
		} else if (ob == um.d_btn) {// 삭제
			deletProcess();
		} else if (ob == um.up.p_btn) {// 결제창 결제
			BakeryDto bd = new BakeryDto();
			if (bd.selectPoint() < allprice) {
                JOptionPane.showMessageDialog(null, "포인트가 부족합니다.\r\n충전하세요", "경고", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                um.up.dispose();
                um.pc.setVisible(true);
            }
		} else if (ob == um.up.c_btn) {// 결제창 취소
			um.up.dispose();
			um.setVisible(true);
			int len = list.size();
			for (int i = 0; i < len; i++) {
				um.up.p_model.removeRow(0);
			}
		} else if (ob == um.pc.c_btn) { // 결제확인창 취소
			um.pc.dispose();
			um.up.setVisible(true);			
		} else if (ob == um.pc.ok_btn) {// 결제확인창 확인
			dbInProcess();
			Link l = new Link();
			System.out.println("결제완료");
			int len = list.size();
			for (int i = 0; i < len; i++) {
				um.o_model.removeRow(0);
				um.up.p_model.removeRow(0);
			}
			allprice = 0;
			list.clear();
			um.ap_tf.setText("\uCD1D\uAC00\uACA9 : .....");
			um.pc.dispose();
			um.setVisible(true);
		} else if (ob == um.oh.out_btn) {// 주문내역창 나가기
			um.oh.input_tf.setText("");
			um.oh.sp_tf.setText("");			
			um.oh.dispose();
			int len = um.oh.h_model.getRowCount();
			for (int i = 0; i < len; i++) {
				um.oh.h_model.removeRow(0);
			}
		} else if (ob == um.oh.input_tf) {
			BakeryBean.serchMember = um.oh.input_tf.getText();
			outSerchMember_id(); 
			idSerchProcess();
		} else if (ob == um.oh.clear_btn) {
			int len = um.oh.h_model.getRowCount();
			for (int i = 0; i < len; i++) {
				um.oh.h_model.removeRow(0);
			}
			um.oh.input_tf.setText("");
			um.oh.sp_tf.setText("");
		}
	}

	public void outSerchMember_id() {
		BakeryBean.serchMember = um.oh.input_tf.getText();
	}

	private void idSerchProcess() {
		BakeryDto bd = new BakeryDto();
		List<BakeryBean> oh_list = bd.mis_sdb();
		int price = 0;
		for (int i = 0; i < oh_list.size(); i++) {
			um.oh.h_model.addRow(new Object[] { oh_list.get(i).getOrder_id(), oh_list.get(i).getProduct_name(),
					oh_list.get(i).getSu(), oh_list.get(i).getPrice(), oh_list.get(i).getAllprice(),
					oh_list.get(i).getOrder_date() });
			price += oh_list.get(i).getAllprice();
		}
		um.oh.sp_tf.setText(Integer.toString(price) + "루비");
	}

	private void oh_Process() {
		BakeryDto bd = new BakeryDto();
		List<BakeryBean> oh_list = bd.oh_sdb();
		int price = 0;
		for (int i = 0; i < oh_list.size(); i++) {
			um.oh.h_model.addRow(new Object[] { oh_list.get(i).getOrder_id(), oh_list.get(i).getProduct_name(),
					oh_list.get(i).getSu(), oh_list.get(i).getPrice(), oh_list.get(i).getAllprice(),
					oh_list.get(i).getOrder_date() });
			price += oh_list.get(i).getAllprice();
		}
		um.oh.sp_tf.setText(Integer.toString(price) + "루비");
	}

	private void dbInProcess() {
		BakeryDto bd = new BakeryDto();
		bd.infoOrder();
		int point = bd.selectPoint() - allprice;
		bd.updatePoint(point);
	}

	private void payProcess() { // 결제버튼 눌렀을때 주문 j테이블에서 값 가져와서 결제 j테이블에 뿌리기, 결제 총가격 합산 뿌리고, 결제확인의 총가격 합산 값전달
		list.clear();
		list2.clear();
		allprice = 0;
		for (int i = 0; i <= um.o_table.getRowCount() - 1; i++) { // 주문j테이블에서 값 한줄씩 가져와서 list에 저장
			BakeryBean bb = new BakeryBean();
			bb.setProduct_id(Integer.parseInt((String) um.o_model.getValueAt(i, 0)));
			bb.setProduct_name((String) um.o_model.getValueAt(i, 1));
			bb.setProduct_epl((String) um.o_model.getValueAt(i, 2));
			bb.setSu(Integer.parseInt((String) um.o_model.getValueAt(i, 3)));
			bb.setPrice(Integer.parseInt((String) um.o_model.getValueAt(i, 4)));
			bb.setAllprice(Integer.parseInt((String) um.o_model.getValueAt(i, 5)));
			list.add(bb);
		}
		
		for (int i = 0; i < list.size(); i++) { // list의 값 payJtabel 에 저장
			um.up.p_model.addRow(new Object[] { list.get(i).getProduct_name(), list.get(i).getProduct_epl(),
					list.get(i).getSu(), list.get(i).getPrice(), list.get(i).getAllprice() });
			allprice += list.get(i).getAllprice();
		}
		um.up.aps_lb.setText(Integer.toString(allprice) + "루비");
		um.pc.ap_lb.setText(Integer.toString(allprice) + "루비");
	}

	private void deletProcess() { // 주문테이블 지우는 메소드
        if (um.o_table.getSelectedRow() == -1) {
            return;
        } else {
            int row = um.o_table.getSelectedRow();
            int p_idrow = 0;
            int len = um.m_model.getRowCount();
            for (int i = 0; i <= len - 1; i++) {
                if (um.o_model.getValueAt(row, 1).equals(um.m_model.getValueAt(i, 1))) {
                    p_idrow = i;
                    break;
                }
            }
            int oldsu = Integer.parseInt(String.valueOf(um.m_model.getValueAt(p_idrow, 4)));
            int newsu = Integer.parseInt(String.valueOf(um.o_model.getValueAt(row, 3)));
            um.m_model.setValueAt(oldsu + newsu, p_idrow, 4);
            um.o_model.removeRow(um.o_table.getSelectedRow());
            payPrice();
        }
    }

    private void plusProcess() { // 주문 테이블 값 추가 메소드
        if (um.m_table.getSelectedRow() == -1) {
            return;
        } else {
            int row = um.m_table.getSelectedRow();
            String product_id = (String) um.m_model.getValueAt(row, 0);
            String name = (String) um.m_model.getValueAt(row, 1);
            String name2 = (String) um.m_model.getValueAt(row, 2);
            String onePay = (String) um.m_model.getValueAt(row, 3);
            String suStr = um.su_spinner.getValue().toString();
            String input[] = new String[6];
            int sumSP = Integer.parseInt(suStr) * Integer.parseInt(onePay);
            String SP = Integer.toString(sumSP);
            input[0] = product_id;
            input[1] = name;
            input[2] = name2;
            input[3] = suStr;
            input[4] = onePay;
            input[5] = SP;
            oldsu = Integer.parseInt(String.valueOf(um.m_model.getValueAt(row, 4)));
            newsu = Integer.parseInt(suStr);
            if (oldsu >= newsu) {
                um.m_model.setValueAt(oldsu - newsu, row, 4);
            } else {
                return;
            }
            um.o_model.addRow(input);
            payPrice();
        }
    }

	private void payPrice() { // 총 결제 가격 표시 메소드
		int price = 0;
		int len = um.o_table.getRowCount();
		for (int i = 0; i <= len - 1; i++) {
			String onePay = (String) um.o_model.getValueAt(i, 5);
			price += Integer.parseInt(onePay);
		}
		um.ap_tf.setText(Integer.toString(price) + "루비");
	}

}
