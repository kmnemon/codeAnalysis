package codedisplay;

import codeanalysis.AbsAndInsPair;
import codeanalysis.CodeDisplay;
import codeanalysis.ModuleInfo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoordinateDisplay implements CodeDisplay {
    @Override
    public void display(Map<ModuleInfo, Double> distance, Map<ModuleInfo, AbsAndInsPair> pair) {
        plot_2D(MapValueTodoubleArray(pair));
    }

    void plot_2D(double[][] data) {
        DefaultXYDataset xydataset = new DefaultXYDataset();
        xydataset.addSeries("Module", data);

        JFreeChart chart = ChartFactory.createScatterPlot("code analysis", "Instability", "Abstractness", xydataset,
                PlotOrientation.VERTICAL, true, true, false);

        ChartFrame frame = new ChartFrame("code analysis", chart, true);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setBackgroundPaint(Color.white);

        ValueAxis domainAxis = xyplot.getDomainAxis();
        domainAxis.setAxisLineStroke(new BasicStroke(1.5f));//set x
        domainAxis.setUpperBound(1);
        ValueAxis rangeAxis = xyplot.getRangeAxis();
        rangeAxis.setAxisLineStroke(new BasicStroke(1.5f));//set y
        rangeAxis.setUpperBound(1);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static double[][] MapValueTodoubleArray(Map<ModuleInfo, AbsAndInsPair> pair){
        List<AbsAndInsPair> pairs = new ArrayList<>(pair.values());
        double[][] data = new double[2][pairs.size()];

        int j = 0;
        for( AbsAndInsPair p : pairs){
            int i = 0;
            data[i++][j] = p.getInstability();
            data[i][j] = p.getAbstractness();
            j++;
        }
        return data;
    }

}
