package com.kitri.admin.project;

import com.kitri.admin.jfreechart.*;
import com.kitri.admin.panel.PBreadChart;
import com.kitri.admin.panel.PSaleChart;
import com.kitri.bakery.digain.UserMain;
import com.kitri.bakery.domain.BakeryBean;
import com.kitri.kang.AdminOrderMain;
import com.kitri.kang.ProductMain;
import com.kitri.login.view.Member_List;
import com.kitri.login.view.loginView;
import com.kitri.storestock.view.StockViewMove;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class Pro_Admin extends JFrame implements ActionListener {

	private JPanel contentPane;

	PBreadChart bread = new PBreadChart();
	PSaleChart sale = new PSaleChart();
	JChartSale jchart = new JChartSale();
	JChartBread jchart2 = new JChartBread();
	Order_Conf oc = new Order_Conf(); 
	ProductMain pm = new ProductMain();
	Member_List ml = new Member_List();
	loginView lv = new loginView();
	StockViewMove svm = new StockViewMove();


	JLabel lblNewLabel = new JLabel("°ü¸®ÀÚ ¸ÞÀÎ");
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	JButton Button0 = new JButton("ÇöÀå°áÁ¦");
	JButton Button1 = new JButton("»óÇ°°ü¸®");
	JButton Button2 = new JButton("¹ßÁÖ°ü¸®");
	JButton Button3 = new JButton("Àç°í°ü¸®");
	JButton Button4 = new JButton("È¸¿ø°ü¸®");
	JButton Button5 = new JButton("ÁÖ¹®È®ÀÎ");
	JButton Button6 = new JButton("·Î±×¾Æ¿ô");
	JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

	public JFreeChart MsJchart() {

		JFreeChart chart = jchart.getChart();
		return chart;
	}

	public JFreeChart MsJchart2() {
		JFreeChart chart = jchart2.getChart();
		return chart;
	}

	public Pro_Admin() {
		super("Administrator Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		// contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel.setBackground(new Color(255, 204, 102));
		panel.setBounds(10, 10, 922, 40);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(lblNewLabel);
		panel_1.setBackground(new Color(255, 255, 255));

		// panel_1.setBackground(new Color(255, 248, 220));
		panel_1.setBounds(10, 80, 922, 55);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 5, 10, 10));
		Button0.setBorderPainted(false);
		Button0.setBackground(new Color(255, 204, 102));

		Button0.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		// Button0.setBackground(Color.ORANGE);
		panel_1.add(Button0);
		Button1.setBorderPainted(false);
		Button1.setBackground(new Color(255, 204, 102));

		Button1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		// Button1.setBackground(Color.ORANGE);
		panel_1.add(Button1);
		Button2.setBorderPainted(false);
		Button2.setBackground(new Color(255, 204, 102));

		Button2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		// Button2.setBackground(Color.ORANGE);
		panel_1.add(Button2);
		Button3.setBorderPainted(false);
		Button3.setBackground(new Color(255, 204, 102));

		Button3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		// Button3.setBackground(Color.ORANGE);
		panel_1.add(Button3);
		Button4.setBorderPainted(false);
		Button4.setBackground(new Color(255, 204, 102));

		Button4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		// Button4.setBackground(Color.ORANGE);
		panel_1.add(Button4);
		Button5.setBorderPainted(false);
		Button5.setBackground(new Color(255, 204, 102));

		Button5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		// Button5.setBackground(Color.ORANGE);
		panel_1.add(Button5);
		Button6.setBorderPainted(false);
		Button6.setBackground(new Color(255, 204, 102));
		
		Button6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		// Button5.setBackground(Color.ORANGE);
		panel_1.add(Button6);

		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		panel_2.setBounds(10, 162, 922, 290);
		contentPane.add(panel_2);
		tab.setBackground(new Color(255, 255, 255));

		tab.addTab("ÀÏ¸ÅÃâ", sale);
		sale.setLayout(new BorderLayout(0, 0));
		ChartPanel CP = new ChartPanel(MsJchart());
		sale.add(CP, BorderLayout.CENTER);

		tab.addTab("»§¼ö¿ä", bread);
		bread.setLayout(new BorderLayout(0, 0));
		ChartPanel CP2 = new ChartPanel(MsJchart2());
		bread.add(CP2, BorderLayout.CENTER);

//		contentPane.add(tab, BorderLayout.CENTER);

		panel_2.add(tab);
		
		Button0.addActionListener(this);
		Button1.addActionListener(this);
		Button2.addActionListener(this);
		Button3.addActionListener(this);
		Button4.addActionListener(this);
		Button5.addActionListener(this);
		Button6.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == Button0) { 
			BakeryBean.member_id = "admin";
			UserMain um = new UserMain();
			um.setVisible(true);
			this.dispose();
//			System.out.println("0");
		} else if (ob == Button1) {
			pm.setVisible(true);
			this.dispose();
//			System.out.println("1");
		} else if (ob == Button2) {
			AdminOrderMain aom = new AdminOrderMain();
			aom.setVisible(true);
			this.dispose();
//			System.out.println("2");
		} else if (ob == Button3) {
			svm.setVisible(true);
			this.dispose();
//			System.out.println("3");
		} else if (ob == Button4) {
			ml.setVisible(true);
//			System.out.println("4");
		} else if (ob == Button5) { //
			Order_Conf oc = new Order_Conf();
			oc.allOrder();
			oc.resOrder();
			oc.setVisible(true);
//			System.out.println("5");
		} else if (ob == Button6) {
			this.dispose();
			lv.setVisible(true);
//			System.out.println("6");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pro_Admin pa = new Pro_Admin();
					pa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
