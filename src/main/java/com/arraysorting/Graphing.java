package com.arraysorting;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Graphing {
    public static void generateGraph(int[] sizes, long[][] data, String title, String yAxisLabel, String filename) {
        CategoryDataset dataset = createDataset(sizes, data);
        JFreeChart chart = ChartFactory.createLineChart(
                title,
                "Array Size",
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(new Color(255, 255, 255));
        chart.getTitle().setPaint(new Color(64, 64, 64));
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 18));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(230, 230, 230));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultShapesFilled(true);
        renderer.setDrawOutlines(true);

        renderer.setSeriesPaint(0, new Color(0, 0, 255)); // blue
        renderer.setSeriesPaint(1, new Color(255, 0, 0)); // red
        renderer.setSeriesPaint(2, new Color(0, 128, 0)); // green
        renderer.setSeriesPaint(3, new Color(255, 165, 0)); // orange
        renderer.setSeriesPaint(4, new Color(255, 0, 255)); // magenta
        renderer.setSeriesPaint(5, new Color(0, 255, 255)); // cyan
        renderer.setSeriesPaint(6, new Color(255, 192, 203)); // pink

        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setTickLabelFont(new Font("Serif", Font.PLAIN, 12));
        xAxis.setLabelFont(new Font("Serif", Font.BOLD, 14));

        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setTickLabelFont(new Font("Serif", Font.PLAIN, 12));
        yAxis.setLabelFont(new Font("Serif", Font.BOLD, 14));

        try {
            ChartUtils.saveChartAsPNG(new File(filename), chart, 800, 600);
            System.out.println("Generated " + filename + " successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CategoryDataset createDataset(int[] sizes, long[][] data) {
        DefaultCategoryDataset chartData = new DefaultCategoryDataset();
        String[] algorithms = {"Insertion Sort", "Heap Sort", "Merge Sort", "Quick Sort (Cutoff 0)", "Quick Sort (Cutoff 10)", "Quick Sort (Cutoff 50)", "Quick Sort (Cutoff 200)"};

        for (int sizeCount = 0; sizeCount < sizes.length; sizeCount++) {
            for (int algCount = 0; algCount < algorithms.length; algCount++) {
                chartData.addValue(data[sizeCount][algCount], algorithms[algCount], Integer.toString(sizes[sizeCount]));
            }
        }
        return chartData;
    }
}
