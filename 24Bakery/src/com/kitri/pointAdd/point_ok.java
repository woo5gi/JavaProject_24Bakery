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
	     	// 입력한 사원번호로 정보 수집하기 = 수정 전 사원 정보[eb]
			memberBean eb = sc.searchEachEmp4(empno); //아이디로 기존 포인트 조회
			// 입력한 수정된 사원 정보 수집하기 = 수정 후 사원 정보[mod_eb]
		    memberBean mod_eb = new memberBean();
			mod_eb.setMember_id(BakeryBean.member_id);
			mod_eb.setPoint(point_Add.ppp + eb.getPoint()); //mod_eb에 입력받은 값 저장(아이디,포인트)
			AddCont ac = new AddCont();
			ac.modEmp(mod_eb);
			
			System.out.println("기존포인트" + eb.getPoint());
			System.out.println(BakeryBean.member_id + "물고온아이디");
			System.out.println(point_Add.ppp + "물고온 포인트");
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
