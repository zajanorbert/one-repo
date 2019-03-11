package com.codecool.greencommitment.common;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.util.Date;

public class Linechart extends ApplicationFrame {


    public Linechart(String title) {
        super(title);
        DefaultCategoryDataset dataset = createDataset("1");
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "THERMOMETER", // Chart title
                "Date", // X-Axis Label
                "Celsius", // Y-Axis Label
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private DefaultCategoryDataset createDataset(String filename) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series1 = "Measurement";

        XMLread read = new XMLread();
        Measurements measurements1 = read.xmlParser("target/" + filename + ".xml");
        for (Measurement m : measurements1.getMeasurement()){
            double value = m.getValue();
            Date date = new Date(m.getTime());
            dataset.addValue(m.getValue(), series1, String.valueOf(date));

        }

        return dataset;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Linechart example = new Linechart("Line Chart Example");
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(800, 600);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
