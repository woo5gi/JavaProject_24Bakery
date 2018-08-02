package com.kitri.pointAdd;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kitri.bakery.domain.BakeryBean;
import com.kitri.login.bean.memberBean;
import com.kitri.login.view.loginView;
import com.kitri.lookup.User_Lookup_cont;
import com.kitri.lookup.User_Lookup_view;





public class point_ok extends JFrame implements ActionListener {

	JButton ok = new JButton("\uB124");
	okok ok2 = new okok();
	SearchCont sc = new SearchCont();
	JButton back = new JButton("\uB4A4\uB85C\uAC00\uAE30");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					point_ok window = new point_ok();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public point_ok() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		memberBean mb = new memberBean();
	

		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\uCD1D \uB8E8\uBE44");
		label.setBounds(86, 107, 57, 15);
		getContentPane().add(label);
		
		JLabel rb_receive = new JLabel("");
		mb  = point_Add.pointUd();
		rb_receive.setText(mb.getPoint() + "");
		rb_receive.setBounds(144, 107, 97, 15);
		getContentPane().add(rb_receive);
		
		JLabel lblNewLabel = new JLabel("\uCDA9\uC804\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C");
		lblNewLabel.setBounds(248, 107, 136, 15);
		getContentPane().add(lblNewLabel);
		
		
		back.setBounds(100, 197, 97, 23);
		getContentPane().add(back);
		
		
		ok.setBounds(234, 197, 97, 23);
		getContentPane().add(ok);
		ok.addActionListener(this);
		back.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == ok) {
//			pointDao pd = new pointDao();
//			point_Add pa = new point_Add();
//			
//			pd.getEachInfo3();
			
			
			String empno = loginView.aaa;
			System.out.println(loginView.aaa);
	     	// �Է��� �����ȣ�� ���� �����ϱ� = ���� �� ��� ����[eb]
			memberBean eb = sc.searchEachEmp4(empno); //���̵�� ���� ����Ʈ ��ȸ
			// �Է��� ������ ��� ���� �����ϱ� = ���� �� ��� ����[mod_eb]
		    memberBean mod_eb = new memberBean();
			mod_eb.setMember_id(BakeryBean.member_id);
			mod_eb.setPoint(point_Add.ppp + eb.getPoint()); //mod_eb�� �Է¹��� �� ����(���̵�,����Ʈ)
			AddCont ac = new AddCont();
			ac.modEmp(mod_eb);
			
			System.out.println("��������Ʈ" + eb.getPoint());
			System.out.println(BakeryBean.member_id + "����¾��̵�");
			System.out.println(point_Add.ppp + "����� ����Ʈ");
			this.dispose();
			ok2.setVisible(true);
			
			
			
			

		}else if(ob == back){
			this.dispose();
//			User_Lookup_view ulv = new User_Lookup_view();
//			ulv.setVisible(true);
			point_Add pa = new point_Add();
			
			pa.setVisible(true);
		}
		
	}
	


}
