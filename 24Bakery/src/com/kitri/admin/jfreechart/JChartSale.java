package com.kitri.admin.jfreechart;

import java.awt.BasicStroke;

import java.awt.Color;

import java.awt.Font;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFrame;

import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.CategoryAxis;

import org.jfree.chart.axis.CategoryLabelPositions;

import org.jfree.chart.axis.NumberAxis;

import org.jfree.chart.labels.CategoryItemLabelGenerator;

import org.jfree.chart.labels.ItemLabelAnchor;

import org.jfree.chart.labels.ItemLabelPosition;

import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;

import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.DatasetRenderingOrder;

import org.jfree.chart.plot.PlotOrientation;

import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.chart.renderer.category.LineAndShapeRenderer;

import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.ui.TextAnchor;

import com.kitri.admin.domain.OrderBean;
import com.kitri.admin.model.BakeryDao;

public class JChartSale {

	public JFreeChart getChart() {

		// ������ ����

		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset(); // line chart 1

		// ������ �Է� ( ��, ����, ī�װ� )

		BakeryDao bd = new BakeryDao();
		List<OrderBean> list = bd.getSale();
		int allPrice = 0;
		int i = 0;
		while (true) {
			allPrice = allPrice + list.get(i).getProduct_ea() * list.get(i).getPrice();
//			 System.out.println(allPrice);
//			 System.out.println(list.get(i).getOrder_time());
			if ((i == list.size() - 1) || (!list.get(i + 1).getOrder_time().equals(list.get(i).getOrder_time()))) {
				dataset2.addValue(allPrice, "����", list.get(i).getOrder_time());
				allPrice = 0;
//				System.out.println("����");
			}

			if (i >= list.size() - 1) {
				// System.out.println("��������");
				break;
			}
			i++;
		}

		// ������ ���� �� ����

		// ������ ����

		final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();

		// ���� �ɼ� ����

		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();

		final ItemLabelPosition p_center = new ItemLabelPosition(

				ItemLabelAnchor.CENTER, TextAnchor.CENTER

		);

		final ItemLabelPosition p_below = new ItemLabelPosition(

				ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT

		);
		
		Font f = new Font("Gulim", Font.BOLD, 14);

		Font axisF = new Font("Gulim", Font.PLAIN, 14);

		// ������ ����

		// �׷��� 3

		renderer2.setBaseItemLabelGenerator(generator);

		renderer2.setBaseItemLabelsVisible(true);

		renderer2.setBaseShapesVisible(true);

		renderer2.setDrawOutlines(true);

		renderer2.setUseFillPaint(true);

		renderer2.setBaseFillPaint(Color.WHITE);

		renderer2.setBaseItemLabelFont(f);

		renderer2.setBasePositiveItemLabelPosition(p_below);

		renderer2.setSeriesPaint(0, new Color(219, 121, 22));

		renderer2.setSeriesStroke(0, new BasicStroke(

				2.0f,

				BasicStroke.CAP_ROUND,

				BasicStroke.JOIN_ROUND,

				3.0f)

		);

		// plot ����

		final CategoryPlot plot = new CategoryPlot();

		// plot �� ������ ����

		plot.setDataset(2, dataset2);

		plot.setRenderer(2, renderer2);

		// plot �⺻ ����

		plot.setOrientation(PlotOrientation.VERTICAL); // �׷��� ǥ�� ����

		plot.setRangeGridlinesVisible(true); // X�� ���̵� ���� ǥ�ÿ���

		plot.setDomainGridlinesVisible(true); // Y�� ���̵� ���� ǥ�ÿ���

		// ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		// X�� ����

		plot.setDomainAxis(new CategoryAxis()); // X�� ���� ����

		plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����

		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // ī�װ� �� ��ġ ����

		// Y�� ����

		plot.setRangeAxis(new NumberAxis()); // Y�� ���� ����

		plot.getRangeAxis().setTickLabelFont(axisF); // Y�� ���ݶ� ��Ʈ ����

		// ���õ� plot�� �������� chart ����

		final JFreeChart chart = new JFreeChart(plot);

		return chart;

	}

	public static void main(final String[] args) {
		JChartSale jchart = new JChartSale();
		JFreeChart chart = jchart.getChart();
		ChartFrame frame1 = new ChartFrame("Sales Statistics", chart);
		frame1.setSize(800, 400);
		frame1.setVisible(true);
	}

}