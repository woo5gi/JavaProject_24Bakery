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

		super("ȸ������ ���α׷�  v0.1.1");
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

		btnNewButton = new JButton("�ڷΰ���");
		btnNewButton.setBounds(232, 281, 97, 23);
		getContentPane().add(btnNewButton);

		jTable.addMouseListener(this); // ������ ���

		setSize(600, 369);
//		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnNewButton.addActionListener(this);
	}// end ������

	// JTable�� �÷�
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("���̵�");
		col.add("�̸�");
		col.add("��й�ȣ");
		col.add("����");
		col.add("�̸���");
		col.add("��ȭ");
		col.add("�ּ�");

		return col;
	}// getColumn

	// Jtable ���� ���� �޼ҵ�
	public void jTableRefresh() {
		memberDao dao = new memberDao();
		DefaultTableModel model = new DefaultTableModel(dao.getMemberList(), getColumn());
		jTable.setModel(model);

	}// jTableRefresh() ��������

	public static void main(String[] args) {
		new Member_List();

	}// main

	@Override
	public void mouseClicked(MouseEvent e) {
		// mouseClicked �� ���
		// �� ����� ������ �Ѿ(�ڱ� ���� ��ȸ)

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
