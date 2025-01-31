import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SIRModel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //User input for parameters
        System.out.println("=== SIR Model Simulation ===");
        System.out.println("Enter initial susceptible population (S): ");
        double S = scanner.nextDouble();
        System.out.println("Enter initial infected population (I): ");
        double I = scanner.nextDouble();
        System.out.println("Enter initial recovered population (R): ");
        double R = scanner.nextDouble();
        System.out.println("Enter transmission rate (beta): ");
        double beta = scanner.nextDouble();
        System.out.println("Enter recovery rate (gamma): ");
        double gamma = scanner.nextDouble();
        System.out.println("Enter time step (dt): ");
        double dt = scanner.nextDouble();
        System.out.println("Enter simulation duration(days): ");
        double days = scanner.nextDouble();

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
        ChartFrame frame = new ChartFrame("SIR Model Simulation", chart);
        frame.pack();
        frame.setVisible(true);
    }

}