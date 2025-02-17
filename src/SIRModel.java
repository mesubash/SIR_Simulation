import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SIRModel {

    public static double getPositiveDouble(Scanner scanner, String prompt) {
        double value;
        do {
            System.out.println(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a positive number.");
                scanner.next();
            }
            value = scanner.nextDouble();
            if (value < 0) {
                System.out.println("Value cannot be negative. Please enter a positive number.");
            }
        } while (value < 0);
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //User input for parameters
        System.out.println("=== SIR Model Simulation ===");
        double S = getPositiveDouble(scanner, "Enter initial susceptible population (S): ");
        double I = getPositiveDouble(scanner, "Enter initial infected population (I): ");
        double R = getPositiveDouble(scanner, "Enter initial recovered population (R): ");
        double beta = getPositiveDouble(scanner, "Enter transmission rate (beta): ");
        double gamma = getPositiveDouble(scanner, "Enter recovery rate (gamma): ");
        double dt = getPositiveDouble(scanner, "Enter time step (dt): ");
        double days = getPositiveDouble(scanner, "Enter simulation duration(days): ");

        //Data series for plotting 
        XYSeries susceptibleSeries = new XYSeries("Susceptible");
        XYSeries infectedSeries = new XYSeries("Infected");
        XYSeries recoveredSeries = new XYSeries("Recovered");

        //CSV file for data storage
        try (FileWriter writer = new FileWriter("output/SIR_Simualation.csv");
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder().setHeader("Day", "Susceptible", "Infected", "Recovered").build())) {
            //Simulation loop using Euler's method
            for (int t = 0; t < days; t++) {
                double dS = -beta * S * I * dt;
                double dI = (beta * S * I - gamma * I) * dt;
                double dR = gamma * I * dt;

                S += dS;
                I += dI;
                R += dR;

                //Ensure population values are non-negative
                S = Math.max(S, 0);
                I = Math.max(I, 0);
                R = Math.max(R, 0);

                //Add data to series for plotting
                susceptibleSeries.add(t, S);
                infectedSeries.add(t, I);
                recoveredSeries.add(t, R);

                //Write data to CSV file
                csvPrinter.printRecord(t, S, I, R);
            }
            System.out.println("Simulation completed successfully! Data Saved to SIR_Simulation.csv");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        //Plotting the result
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(susceptibleSeries);
        dataset.addSeries(infectedSeries);
        dataset.addSeries(recoveredSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "SIR Model Simulation", "Days", "Population", dataset);
        chart.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.getXYPlot().setDomainGridlinesVisible(true);
        chart.getXYPlot().setRangeGridlinesVisible(true);
        chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);
        chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
        chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.BLUE);
        chart.getXYPlot().getRenderer().setSeriesPaint(1, Color.RED);
        chart.getXYPlot().getRenderer().setSeriesPaint(2, Color.GREEN);

        // Display the chart in a frame
        ChartFrame frame = new ChartFrame("SIR Model Simulation", chart);
        frame.pack();
        frame.setVisible(true);
        System.out.println("Do you want to save the chart as an image file? (y/n)");
        String response = scanner.next();
        if (!response.equalsIgnoreCase("y")) {
            return;
        }

        // Save the chart as an image file
        try {
            ChartUtils.saveChartAsPNG(new File("output/SIR_Simulation.png"), chart, 800, 600);
            System.out.println("Chart saved as output/SIR_Simulation.png");
        } catch (IOException e) {
            System.err.println("Error saving chart as image: " + e.getMessage());
        }
    }
}