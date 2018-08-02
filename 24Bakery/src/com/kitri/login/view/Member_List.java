package com.kitri.login.view;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.kitri.login.db.memberDao;

public class Member_List extends JFrame implements MouseListener, ActionListener {

	Vector v;
	Vector cols;
	DefaultTableModel model;
	JTable jTable;
	JScrollPane pane;
	JPanel pbtn;
	private JButton btnNewButton;

	public Member_List() {

		super("회원관리 프로그램  v0.1.1");
		// v=getMemberList();
		// MemberDAO
		memberDao dao = new memberDao();
		v = dao.getMemberList();
		System.out.println("v=" + v);
		cols = getColumn();

		model = new DefaultTableModel(v, cols);
		getContentPane().setLayout(null);

		// jTable = new JTable(v,cols);
		jTable = new JTable(model);
		pane = new JScrollPane(jTable);
		pane.setBounds(0, 45, 584, 210);
		getContentPane().add(pane);

		pbtn = new JPanel();
		pbtn.setBounds(0, 0, 584, 255);

		getContentPane().add(pbtn);

		btnNewButton = new JButton("뒤로가기");
		btnNewButton.setBounds(232, 281, 97, 23);
		getContentPane().add(btnNewButton);

		jTable.addMouseListener(this); // 리스너 등록

		setSize(600, 369);
//		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnNewButton.addActionListener(this);
	}// end 생성자

	// JTable의 컬럼
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("아이디");
		col.add("이름");
		col.add("비밀번호");
		col.add("성별");
		col.add("이메일");
		col.add("전화");
		col.add("주소");

		return col;
	}// getColumn

	// Jtable 내용 갱신 메소드
	public void jTableRefresh() {
		memberDao dao = new memberDao();
		DefaultTableModel model = new DefaultTableModel(dao.getMemberList(), getColumn());
		jTable.setModel(model);

	}// jTableRefresh() 종료지점

	public static void main(String[] args) {
		new Member_List();

	}// main

	@Override
	public void mouseClicked(MouseEvent e) {
		// mouseClicked 만 사용
		// 상세 사용자 인포로 넘어감(자기 정보 조회)

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == btnNewButton) {
			this.dispose();
		}

	}

}
