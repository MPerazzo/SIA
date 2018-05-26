package ar.edu.itba.sia.core;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Graphics extends JPanel {

    public static final String FITNESS_AVERAGE_TITLE = "Fitness Promedio",
                    BEST_FITNESS_TITLE = "Maximo Fitness", WORST_FITNESS_TITLE = "Peor Fitness",
                    GRAPHICS_TITLE = "Gráfico de Fitness",X_TITLE = "Generación",
                    Y_TITLE = "Fitness";
    private XYSeries fitnessAverageSeries;
    private XYSeries bestFitnessSeries;
    private XYSeries worstFitnessSeries;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private XYPlot plot;
    private JPanel chartPanel;

    public Graphics() {
        super();
        this.fitnessAverageSeries = new XYSeries(FITNESS_AVERAGE_TITLE);
        this.bestFitnessSeries = new XYSeries(BEST_FITNESS_TITLE);
        this.worstFitnessSeries = new XYSeries(WORST_FITNESS_TITLE);

        this.dataset = new XYSeriesCollection();
        dataset.addSeries(fitnessAverageSeries);
        dataset.addSeries(bestFitnessSeries);
        dataset.addSeries(worstFitnessSeries);

        this.chart = ChartFactory.createXYLineChart(
                GRAPHICS_TITLE,
                X_TITLE,
                Y_TITLE,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        this.plot = (XYPlot)chart.getPlot();
        plot.setQuadrantPaint(0, Color.cyan);

        plot.addRangeMarker(new Marker() {
        });

        this.chartPanel = new ChartPanel(chart);

        add(chartPanel);
    }

    public XYSeries getFitnessAverageSeries() {
        return fitnessAverageSeries;
    }

    public XYSeries getBestFitnessSeries() {
        return bestFitnessSeries;
    }

    public XYSeries getWorstFitnessSeries() {
        return worstFitnessSeries;
    }
}
