package com.kitri.admin.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.table.DefaultTableModel;

import com.kitri.admin.domain.OrderBean;
import com.kitri.admin.model.*;
import com.kitri.admin.panel.PAllOrder;
import com.kitri.admin.panel.PResOrder;

public class Order_Conf extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;

	PResOrder reserve = new PResOrder();
	PAllOrder all = new PAllOrder();
	
	JButton exit = new JButton("뒤로가기");
	JPanel panel = new JPanel();
	JPanel paneldown = new JPanel();
	JLabel label = new JLabel("\uC8FC\uBB38\uD655\uC778");
	JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

	/**
	 * Create the frame.
	 */
	public Order_Conf() {
		super("Order Confirm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		exit.setBackground(new Color(255, 204, 102));
		exit.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		exit.setBorderPainted(false);
		
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		contentPane.setBackground(new Color(255, 255, 255));
		panel.setBackground(new Color(255, 255, 255));
		paneldown.setBackground(new Color(255, 255, 255));

		
		String header[] = { "주문번호", "손님", "빵", "개수", "가격" };
		String header1[] = { "주문번호", "손님", "빵", "개수", "가격"};
		
		tab.addTab("전체주문", all);
		all.setLayout(new BorderLayout(0, 0));
		table = new JTable(allOrder(), header);
		all.add(new JScrollPane(table), BorderLayout.CENTER);

		tab.addTab("예약주문", reserve);
		reserve.setLayout(new BorderLayout(0, 0));
		table = new JTable(resOrder(), header1);
		reserve.add(new JScrollPane(table), BorderLayout.CENTER);

		contentPane.add(tab, BorderLayout.CENTER);

		contentPane.add(panel, BorderLayout.NORTH);
		
		contentPane.add(paneldown, BorderLayout.SOUTH);
		
		paneldown.setLayout(new FlowLayout());
		
		paneldown.add(exit);

		panel.add(label);
		
		exit.addActionListener(this);
	}
	
	public String[][] allOrder() {
		BakeryDao bd = new BakeryDao();
		List<OrderBean> listall = bd.getAll();
		String contents[][] = new String[listall.size()][5];
		
		for (int i = 0; i < listall.size(); i++) {
			for (int j = 0; j < 5; j++) {
				switch (j) {
				case 0:
					contents[i][j] = Integer.toString(listall.get(i).getOrder_id());
					break;
				case 1:
					contents[i][j] = listall.get(i).getMember_id();
					break;
				case 2:
					contents[i][j] = listall.get(i).getProduct_name();
					break;
				case 3:
					contents[i][j] = Integer.toString(listall.get(i).getProduct_ea());
					break;
				case 4:
					contents[i][j] = Integer.toString(listall.get(i).getPrice() * listall.get(i).getProduct_ea());
					break;
				}
			}
		}
		
		return contents;
	}
	
	public String[][] resOrder() {
		BakeryDao bd = new BakeryDao();
		List<OrderBean> listres = bd.getResev();
		String contents1[][] = new String[listres.size()][5];
		
		for (int i = 0; i < listres.size(); i++) {
			for (int j = 0; j < 5; j++) {
				switch (j) {
				case 0:
					contents1[i][j] = Integer.toString(listres.get(i).getOrder_id());
					break;
				case 1:
					contents1[i][j] = listres.get(i).getMember_id();
					break;
				case 2:
					contents1[i][j] = listres.get(i).getProduct_name();
					break;
				case 3:
					contents1[i][j] = Integer.toString(listres.get(i).getProduct_ea());
					break;
				case 4:
					contents1[i][j] = Integer.toString(listres.get(i).getPrice() * listres.get(i).getProduct_ea()) ;
					break;
				}
			}
		}
		return contents1;
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order_Conf frame = new Order_Conf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == exit) {
			this.dispose();
		}
	}

}
