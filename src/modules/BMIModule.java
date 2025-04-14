package modules;

import interfaces.HealthModule;
import interfaces.Summarizable;
import utils.FileUtil;
import java.util.Scanner;

public class BMIModule implements HealthModule, Summarizable {
    private double weight, heightInMeters;

    @Override
    public void execute(Scanner sc) {
        System.out.print("Enter weight (kg): ");
        weight = sc.nextDouble();

        System.out.print("Enter height - feet: ");
        int feet = sc.nextInt();
        System.out.print("Enter height - inches: ");
        int inches = sc.nextInt();

        heightInMeters = convertFeetInchesToMeters(feet, inches);

        double bmi = weight / (heightInMeters * heightInMeters);
        String category = classifyBMI(bmi);

        String result = String.format("BMI: %.2f - %s", bmi, category);
        System.out.println(result);
        displayBMIChart(bmi);
        FileUtil.writeToFile("bmi.txt", result);
    }

    private double convertFeetInchesToMeters(int feet, int inches) {
        int totalInches = feet * 12 + inches;
        double meters = totalInches * 0.0254;
        return meters;
    }

    private String classifyBMI(double bmi) {
        if (bmi < 16)
            return "Severe Thinness";
        else if (bmi < 17)
            return "Moderate Thinness";
        else if (bmi < 18.5)
            return "Mild Thinness";
        else if (bmi < 25)
            return "Normal";
        else if (bmi < 30)
            return "Overweight";
        else if (bmi < 35)
            return "Obese Class I";
        else if (bmi < 40)
            return "Obese Class II";
        else
            return "Obese Class III";
    }

    @Override
    public String generateSummary() {
        return "BMI calculated from weight (kg) and height (ft/in).";
    }

    private void displayBMIChart(double bmi) {
        String[] categories = {
                "Severe Thinness", "Moderate Thinness", "Mild Thinness", "Normal",
                "Overweight", "Obese Class I", "Obese Class II", "Obese Class III"
        };
        double[] bounds = {0, 16, 17, 18.5, 25, 30, 35, 40, 50};
        String[] colors = {
                "\u001B[35m", // Purple - Severe Thinness
                "\u001B[31m", // Red - Moderate Thinness
                "\u001B[91m", // Light Red - Mild Thinness
                "\u001B[32m", // Green - Normal
                "\u001B[33m", // Yellow - Overweight
                "\u001B[93m", // Light Yellow - Obese Class I
                "\u001B[91m", // Light Red - Obese Class II
                "\u001B[41m"  // Red Background - Obese Class III
        };
        String RESET = "\u001B[0m";

        int chartWidth = 80;
        double bmiMin = bounds[0];
        double bmiMax = bounds[bounds.length - 1];
        double bmiRange = bmiMax - bmiMin;
        int pointerPosition = (int) ((bmi - bmiMin) / bmiRange * chartWidth);
        pointerPosition = Math.min(pointerPosition, chartWidth - 1);

        // Draw color bar
        System.out.println("\n--- BMI CHART ---");
        System.out.print("|");
        for (int i = 0; i < bounds.length - 1; i++) {
            int start = (int) ((bounds[i] - bmiMin) / bmiRange * chartWidth);
            int end = (int) ((bounds[i + 1] - bmiMin) / bmiRange * chartWidth);
            String color = colors[i];
            String bar = color + "█".repeat(Math.max(1, end - start)) + RESET;
            System.out.print(bar);
        }
        System.out.println("|");

        // Draw pointer
        System.out.print(" ");
        for (int i = 0; i < pointerPosition; i++) System.out.print(" ");
        System.out.println("^");
        System.out.println("Your BMI: " + String.format("%.1f", bmi));

        // Category labels
        System.out.println("\nLegend:");
        for (int i = 0; i < categories.length; i++) {
            System.out.println(colors[i] + categories[i] + RESET + ": " + bounds[i] + " - " + bounds[i + 1]);
        }
    }



}
