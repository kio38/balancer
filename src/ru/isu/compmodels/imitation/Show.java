package ru.isu.compmodels.imitation;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.Arrays;

public class Show implements Runnable {

    CategoryChart chart;
    Boolean shouldStop=false;
    ArrayList<Number> fff=new ArrayList<Number>((Arrays.asList(new Number[]{0,0,0})));

    ArrayList<Balancer> list;
    public Show(){

        list=new ArrayList<Balancer>();
    }

    public void setBalancer(ArrayList<Balancer> balancer){
        list=balancer;
    }


    @Override
    public void run() {

        // Create Chart
        CategoryChart chart1 = new CategoryChartBuilder().width(800).height(600).title("Temperature vs. Color").xAxisTitle("Time").yAxisTitle("loading").theme(Styler.ChartTheme.GGPlot2).build();

        // Customize Chart

        // Series
        chart1.addSeries("server1",
                new ArrayList<String>(Arrays.asList(new String[] { "balancer1", "balancer2", "balancer3"})),
                new ArrayList<Number>(Arrays.asList(new Number[] {
                        list.get(0).getServerPool().get(0).getCurrentLoad(),
                        list.get(1).getServerPool().get(0).getCurrentLoad(),
                        list.get(2).getServerPool().get(0).getCurrentLoad() })));
        chart1.addSeries("server2",
                new ArrayList<String>(Arrays.asList(new String[] {"balancer1", "balancer2", "balancer3"})),
                new ArrayList<Number>(Arrays.asList(new Number[] {
                        list.get(0).getServerPool().get(1).getCurrentLoad(),
                        list.get(1).getServerPool().get(1).getCurrentLoad(),
                        list.get(2).getServerPool().get(1).getCurrentLoad() })));
        chart1.addSeries("server3",
                new ArrayList<String>(Arrays.asList(new String[] { "balancer1", "balancer2", "balancer3"})),
                new ArrayList<Number>(Arrays.asList(new Number[] {
                        list.get(0).getServerPool().get(2).getCurrentLoad(),
                        list.get(1).getServerPool().get(2).getCurrentLoad(),
                        list.get(2).getServerPool().get(2).getCurrentLoad() })));

        chart = chart1;

        SwingWrapper<CategoryChart> hello = new SwingWrapper<CategoryChart>(chart);
        hello.displayChart();
        while(!shouldStop) {

            chart.updateCategorySeries("server1",
                    new ArrayList<String>(Arrays.asList(new String[]{"balancer1", "balancer2", "balancer3"})),
                    new ArrayList<Number>(Arrays.asList(new Number[]{
                            list.get(0).getServerPool().get(0).getCurrentLoad(),
                            list.get(0).getServerPool().get(1).getCurrentLoad(),
                            list.get(0).getServerPool().get(2).getCurrentLoad()})), fff );

            chart.updateCategorySeries("server2",
                    new ArrayList<String>(Arrays.asList(new String[] { "balancer1", "balancer2", "balancer3"})),
                    new ArrayList<Number>(Arrays.asList(new Number[] {
                            list.get(1).getServerPool().get(0).getCurrentLoad(),
                            list.get(1).getServerPool().get(1).getCurrentLoad(),
                            list.get(1).getServerPool().get(2).getCurrentLoad() })),fff);
            chart.updateCategorySeries("server3",
                    new ArrayList<String>(Arrays.asList(new String[] { "balancer1", "balancer2", "balancer3"})),
                    new ArrayList<Number>(Arrays.asList(new Number[] {
                            list.get(2).getServerPool().get(0).getCurrentLoad(),
                            list.get(2).getServerPool().get(1).getCurrentLoad(),
                            list.get(2).getServerPool().get(2).getCurrentLoad() })),fff);

            hello.repaintChart();


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown() {
        shouldStop = true;
        //Будим, если он уснул по take на пустой очереди
        Thread.currentThread().interrupt();
    }
}
