package com.kitri.login.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kitri.login.bean.memberBean;
import com.kitri.login.cont.AddCont;
import com.kitri.login.db.memberDao;

public class joinView extends JFrame implements ActionListener {

	JButton ok = new JButton("\uAC00\uC785");
	JButton id_ok = new JButton("\uC911\uBCF5\uD655\uC778");
	JButton cancel = new JButton("\uCDE8\uC18C");
	JComboBox emailcombo = new JComboBox<>();
	JTextField name = new JTextField();
	JLabel id_label = new JLabel("ID");
	JTextField id = new JTextField();
	JPasswordField passwd = new JPasswordField();
	JPasswordField passwd_con = new JPasswordField();
	JRadioButton male = new JRadioButton();
	JRadioButton female = new JRadioButton();
	JComboBox phone1 = new JComboBox<>();

	ButtonGroup group = new ButtonGroup();
	String str;

	JTextField phone3 = new JTextField();
	JTextField phone2 = new JTextField();
	JTextField email1 = new JTextField();
	JTextField email2 = new JTextField();
	JTextField address1 = new JTextField();
	JTextField address2 = new JTextField();
	JLabel passwd_info = new JLabel("");
	JLabel id_con = new JLabel("");

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					joinView window = new joinView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public joinView() {
		initialize();
	}

	public void initialize() {
		group.add(male);
		group.add(female);

		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		id_label.setBounds(200, 133, 57, 15);
		this.getContentPane().add(id_label);

		JLabel passwd_label = new JLabel("PassWord");
		passwd_label.setBounds(200, 168, 74, 15);
		this.getContentPane().add(passwd_label);

		JLabel passwdC_label = new JLabel("Password \uD655\uC778");
		passwdC_label.setBounds(200, 199, 97, 23);
		this.getContentPane().add(passwdC_label);

		JLabel gender_label = new JLabel("\uC131\uBCC4");
		gender_label.setBounds(200, 232, 57, 15);
		this.getContentPane().add(gender_label);

		JLabel phone_label = new JLabel("\uD734\uB300\uD3F0 \uBC88\uD638");
		phone_label.setBounds(200, 267, 74, 15);
		this.getContentPane().add(phone_label);

		JLabel email_label = new JLabel("Email");
		email_label.setBounds(200, 303, 57, 15);
		this.getContentPane().add(email_label);

		id.setBounds(296, 130, 116, 21);
		this.getContentPane().add(id);
		id.setColumns(10);
		id_ok.setBorderPainted(false);
		id_ok.setBackground(new Color(255, 153, 102));

		id_ok.setBounds(428, 129, 97, 23);
		this.getContentPane().add(id_ok);

		passwd.setBounds(296, 165, 118, 21);
		this.getContentPane().add(passwd);

		passwd_con.setBounds(297, 200, 120, 21);
		this.getContentPane().add(passwd_con);

		male = new JRadioButton("\uB0A8\uC790");
		male.setBackground(Color.WHITE);
		male.setBounds(296, 227, 57, 23);
		this.getContentPane().add(male);

		female = new JRadioButton("\uC5EC\uC790");
		female.setBackground(Color.WHITE);
		female.setBounds(360, 227, 121, 23);
		this.getContentPane().add(female);

		phone1 = new JComboBox();
		phone1.setBorder(null);
		phone1.setBackground(new Color(255, 153, 102));
		phone1.setBounds(296, 264, 52, 21);
		phone1.addItem("010");
		phone1.addItem("011");
		phone1.addItem("012");
		phone1.addItem("013");
		phone1.addItem("017");
		phone1.addItem("019");
		this.getContentPane().add(phone1);

		JLabel p1 = new JLabel("-");
		p1.setFont(new Font("굴림", Font.PLAIN, 15));
		p1.setBounds(355, 267, 30, 15);
		this.getContentPane().add(p1);

		phone2.setBounds(378, 264, 67, 21);
		this.getContentPane().add(phone2);
		phone2.setColumns(10);

		JLabel p2 = new JLabel("-");
		p2.setFont(new Font("굴림", Font.PLAIN, 15));
		p2.setBounds(457, 267, 20, 15);
		this.getContentPane().add(p2);

		phone3.setBounds(477, 264, 72, 21);
		this.getContentPane().add(phone3);
		phone3.setColumns(10);

		email1.setBounds(296, 300, 97, 21);
		this.getContentPane().add(email1);
		email1.setColumns(10);

		emailcombo = new JComboBox();
		emailcombo.setBorder(null);
		emailcombo.setBackground(new Color(255, 153, 102));
		emailcombo.setBounds(517, 300, 97, 21);
		emailcombo.addItem("직접입력");
		emailcombo.addItem("@naver.com");
		emailcombo.addItem("@daum.net");
		emailcombo.addItem("@gmail.com");
		emailcombo.addItem("@hanmail.net");
		this.getContentPane().add(emailcombo);

		JLabel address_label = new JLabel("\uC8FC\uC18C");
		address_label.setBounds(200, 334, 57, 15);
		this.getContentPane().add(address_label);

		address1.setBounds(296, 331, 116, 21);
		this.getContentPane().add(address1);
		address1.setColumns(10);

		JLabel name_label = new JLabel("\uC774\uB984");
		name_label.setBounds(200, 97, 57, 15);
		this.getContentPane().add(name_label);

		// name = new JTextField();
		name.setBounds(296, 94, 116, 21);
		this.getContentPane().add(name);
		name.setColumns(10);
		ok.setBorder(null);
		ok.setBackground(new Color(255, 153, 102));

		ok.setBounds(279, 401, 74, 20);
		this.getContentPane().add(ok);

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancel.setBorder(null);
		cancel.setBackground(new Color(255, 153, 102));
		cancel.setBounds(419, 401, 74, 20);
		this.getContentPane().add(cancel);

		JLabel m1 = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		m1.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		m1.setBounds(338, 28, 97, 38);
		this.getContentPane().add(m1);

		email2.setBounds(421, 300, 92, 21);
		this.getContentPane().add(email2);
		email2.setColumns(10);

		JLabel lblNewLabel = new JLabel("@");
		lblNewLabel.setBounds(401, 303, 44, 15);
		this.getContentPane().add(lblNewLabel);

		address2.setBounds(296, 362, 352, 21);
		this.getContentPane().add(address2);
		address2.setColumns(10);

		passwd_info.setBounds(438, 207, 176, 15);
		this.getContentPane().add(passwd_info);
		id_con.setBounds(438, 168, 197, 15);

		getContentPane().add(id_con);

		ok.addActionListener(this);
		id_ok.addActionListener(this);
		cancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object ob = e.getSource();

		if (ob == ok) {// 확인 버튼
			if (passwd.getText().equals(passwd_con.getText())) {
				// 비밀번호 확인 : 같을시 => 데이터 eb에 저장
				loginView lo = new loginView();
				
				memberBean eb = new memberBean();
				eb = MemAdd();
				AddCont ac = new AddCont();
				ac.addMem(eb);
				lo.setVisible(true);
				this.dispose();
				
			} else {
				// 다를시 = > 라벨에 텍스트 뿌려줌
				passwd_info.setText("비밀번호가 다릅니다");
			}
		} else if (ob == id_ok) {// 아이디 중복체크 버튼
			memberDao md = new memberDao();
			String no = id.getText();// id 필드 값 가져옴
			memberBean no2 = md.getEachInfo(no); // db의 id 값 가져옴

			if (no.equals(no2.getMember_id())) {
				// id필드값과 db id 값이 같을경우
				id_con.setText("이미 있는 아이디");
			} else {
				id_con.setText("사용가능한 아이디");
			}
		} else if(ob == cancel){
			loginView lv = new loginView();
			System.out.println("취소됨");
			this.dispose();
			lv.setVisible(true);
		}
	}// actionPerformed() 종료 지점

	public memberBean MemAdd() {
		// 데이터 Insert 하는 메소드
		// 데이터 타입 :memberBean

		memberBean eb = new memberBean();
		if (male.isSelected()) // Radio 선택된거 가져오는 if문
			str = "M";
		else
			str = "F";
		
//		String e_comb = (String) emailcombo.getSelectedItem();

		// memberBean에 텍스트필드 값 가져와서 저장
		eb.setName(name.getText());
		eb.setMember_id(id.getText());
		eb.setPassword(passwd.getText());
		eb.setGender(str);
		eb.setEmail(email1.getText() + email2.getText() + emailcombo.getSelectedItem());
		eb.setAddress(address1.getText() + address2.getText());
		eb.setPhone_number(phone1.getSelectedItem() + phone2.getText() + phone3.getText());

		return eb; // eb로 리턴
	}
}