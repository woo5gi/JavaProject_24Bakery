package com.kitri.admin.jfreechart;

import java.awt.Color;

import java.awt.Font;

import java.awt.GradientPaint;

import java.awt.Paint;
import java.security.GeneralSecurityException;
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

import org.jfree.chart.renderer.category.CategoryItemRenderer;

import org.jfree.chart.renderer.category.LineAndShapeRenderer;

import org.jfree.chart.renderer.category.StandardBarPainter;

import org.jfree.chart.title.TextTitle;

import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.ui.GradientPaintTransformType;

import org.jfree.ui.HorizontalAlignment;

import org.jfree.ui.StandardGradientPaintTransformer;

import org.jfree.ui.TextAnchor;

import com.kitri.admin.domain.OrderBean;
import com.kitri.admin.model.BakeryDao;

/**
 * 
 * A simple demonstration application showing how to create a bar chart overlaid
 * 
 * with a line chart.
 * 
 */

public class JChartBread {
	// Run As > Java Application ���� �����ϸ� �ٷ� Ȯ���� �� ����.

	public JFreeChart getChart() {

		// ������ ����

		DefaultCategoryDataset dataset1 = new DefaultCategoryDataset(); // bar chart 1

		// ������ �Է� ( ��, ����, ī�װ� )

		int j = 0;
		BakeryDao bd = new BakeryDao();
		List<OrderBean> list2 = bd.getBread();
		while (true) {
			if (list2.get(j).getProduct_id() < 300) {
				dataset1.addValue(list2.get(j).getSum(), "��", list2.get(j).getProduct_name());
			}
			if (j >= list2.size() - 1) {
				// System.out.println("��������");
				break;
			}
			j++;
		}

		// ������ ���� �� ����

		// ������ ����

		final BarRenderer renderer = new BarRenderer();

		// ���� �ɼ� ����

		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();

		final ItemLabelPosition p_center = new ItemLabelPosition(

				ItemLabelAnchor.CENTER, TextAnchor.CENTER

		);

		final ItemLabelPosition p_below = new ItemLabelPosition(

				ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT

		);

		Font f = new Font("Gulim", Font.BOLD, 14);

		Font axisF = new Font("Gulim", Font.PLAIN, 10);

		// ������ ����

		// �׷��� 1

		renderer.setBaseItemLabelGenerator(generator);

		renderer.setBaseItemLabelsVisible(true);

		renderer.setBasePositiveItemLabelPosition(p_center);

		renderer.setBaseItemLabelFont(f);

		// renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(

		// GradientPaintTransformType.VERTICAL));

		// renderer.setSeriesPaint(0, new GradientPaint(1.0f, 1.0f, Color.white, 0.0f,
		// 0.0f, Color.blue));

		renderer.setSeriesPaint(0, new Color(0, 162, 255));

		// plot ����

		final CategoryPlot plot = new CategoryPlot();

		// plot �� ������ ����

		plot.setDataset(dataset1);

		plot.setRenderer(renderer);

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
		JChartBread jchart = new JChartBread();
		JFreeChart chart = jchart.getChart();
		ChartFrame frame1 = new ChartFrame("Bread Statistics", chart);
		frame1.setSize(800, 400);
		frame1.setVisible(true);
	}

}