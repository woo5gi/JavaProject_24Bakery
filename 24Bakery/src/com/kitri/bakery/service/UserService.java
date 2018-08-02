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
	public void actionPerformed(ActionEvent e) {// um ����â , um.up ����â , um.pc ����Ȯ��â , um.oh ��������â
		Object ob = e.getSource();
		Pro_Admin pa = new Pro_Admin();
		if (ob == um.lo_btn) { // �α׾ƿ�
			um.dispose();
			if(BakeryBean.member_id != "admin") {
				lv.setVisible(true);
			} else {
				pa.setVisible(true);
		}
		} else if (ob == um.ph_btn) {// ��������
			um.oh.setVisible(true);
			if (BakeryBean.member_id.equals("admin")) {
				
			} else {
				oh_Process();
			}
		} else if (ob == um.u_btn) { // ȸ������ ����
			// ȸ������ â �߿��
			User_Lookup_view us = new User_Lookup_view();
			us.setVisible(true);
			um.dispose();
		} else if (ob == um.p_btn) {// ����
			// �������� ������
			  if (um.o_model.getRowCount() == 0) {
	                JOptionPane.showMessageDialog(null, "�ֹ��� �־��ּ���", "���", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else {
	                um.dispose();
	                um.up.setVisible(true);
	                payProcess();
	            }
	        } else if (ob == um.plus_btn) {// �߰�
	            if (um.m_table.getSelectedRow() == -1) {
	                JOptionPane.showMessageDialog(null, "�޴��� �������ּ���.", "���", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else {
	                int row = um.m_table.getSelectedRow();
	                oldsu = Integer.parseInt(String.valueOf(um.m_model.getValueAt(row, 4)));
	                newsu = Integer.parseInt(um.su_spinner.getValue().toString());
	                if (Integer.parseInt(String.valueOf(um.su_spinner.getValue())) < 1) {
	                    JOptionPane.showMessageDialog(null, "������ 0�� ���ϰ� �ɼ������ϴ�.", "���", JOptionPane.ERROR_MESSAGE);
	                    return;
	                } else if (oldsu < newsu) {
	                    JOptionPane.showMessageDialog(null, "������ �ʰ��߽��ϴ�.", "���", JOptionPane.ERROR_MESSAGE);
	                    return;
	                } else {
	                    plusProcess();
	                }
	            }
		} else if (ob == um.d_btn) {// ����
			deletProcess();
		} else if (ob == um.up.p_btn) {// ����â ����
			BakeryDto bd = new BakeryDto();
			if (bd.selectPoint() < allprice) {
                JOptionPane.showMessageDialog(null, "����Ʈ�� �����մϴ�.\r\n�����ϼ���", "���", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                um.up.dispose();
                um.pc.setVisible(true);
            }
		} else if (ob == um.up.c_btn) {// ����â ���
			um.up.dispose();
			um.setVisible(true);
			int len = list.size();
			for (int i = 0; i < len; i++) {
				um.up.p_model.removeRow(0);
			}
		} else if (ob == um.pc.c_btn) { // ����Ȯ��â ���
			um.pc.dispose();
			um.up.setVisible(true);			
		} else if (ob == um.pc.ok_btn) {// ����Ȯ��â Ȯ��
			dbInProcess();
			Link l = new Link();
			System.out.println("�����Ϸ�");
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
		} else if (ob == um.oh.out_btn) {// �ֹ�����â ������
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
		um.oh.sp_tf.setText(Integer.toString(price) + "���");
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
		um.oh.sp_tf.setText(Integer.toString(price) + "���");
	}

	private void dbInProcess() {
		BakeryDto bd = new BakeryDto();
		bd.infoOrder();
		int point = bd.selectPoint() - allprice;
		bd.updatePoint(point);
	}

	private void payProcess() { // ������ư �������� �ֹ� j���̺��� �� �����ͼ� ���� j���̺� �Ѹ���, ���� �Ѱ��� �ջ� �Ѹ���, ����Ȯ���� �Ѱ��� �ջ� ������
		list.clear();
		list2.clear();
		allprice = 0;
		for (int i = 0; i <= um.o_table.getRowCount() - 1; i++) { // �ֹ�j���̺��� �� ���پ� �����ͼ� list�� ����
			BakeryBean bb = new BakeryBean();
			bb.setProduct_id(Integer.parseInt((String) um.o_model.getValueAt(i, 0)));
			bb.setProduct_name((String) um.o_model.getValueAt(i, 1));
			bb.setProduct_epl((String) um.o_model.getValueAt(i, 2));
			bb.setSu(Integer.parseInt((String) um.o_model.getValueAt(i, 3)));
			bb.setPrice(Integer.parseInt((String) um.o_model.getValueAt(i, 4)));
			bb.setAllprice(Integer.parseInt((String) um.o_model.getValueAt(i, 5)));
			list.add(bb);
		}
		
		for (int i = 0; i < list.size(); i++) { // list�� �� payJtabel �� ����
			um.up.p_model.addRow(new Object[] { list.get(i).getProduct_name(), list.get(i).getProduct_epl(),
					list.get(i).getSu(), list.get(i).getPrice(), list.get(i).getAllprice() });
			allprice += list.get(i).getAllprice();
		}
		um.up.aps_lb.setText(Integer.toString(allprice) + "���");
		um.pc.ap_lb.setText(Integer.toString(allprice) + "���");
	}

	private void deletProcess() { // �ֹ����̺� ����� �޼ҵ�
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

    private void plusProcess() { // �ֹ� ���̺� �� �߰� �޼ҵ�
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

	private void payPrice() { // �� ���� ���� ǥ�� �޼ҵ�
		int price = 0;
		int len = um.o_table.getRowCount();
		for (int i = 0; i <= len - 1; i++) {
			String onePay = (String) um.o_model.getValueAt(i, 5);
			price += Integer.parseInt(onePay);
		}
		um.ap_tf.setText(Integer.toString(price) + "���");
	}

}
