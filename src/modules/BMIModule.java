package modules;

import interfaces.HealthModule;

import java.util.Scanner;

public class BMIModule implements HealthModule {
    private double weight, heightInMeters;

    @Override
    public void execute(Scanner sc) {
        System.out.print("Enter weight (kg): ");
        weight = sc.nextDouble();

        System.out.print("Enter your height:\n   Feet: ");
        int feet = sc.nextInt();
        System.out.print("   Inches: ");
        int inches = sc.nextInt();

        heightInMeters = convertFeetInchesToMeters(feet, inches);

        double bmi = weight / (heightInMeters * heightInMeters);
        int categoryIndex = classifyBMIIndex(bmi);

        String[] categories = {"Underweight", "Normal", "Overweight", "Obese"};
        String[] categoryColors = {
                "\u001B[94m",
                "\u001B[33m",
                "\u001B[38;5;208m",
                "\u001B[31m"
        };
        String RESET = "\u001B[0m";

        String coloredCategory = categoryColors[categoryIndex] + categories[categoryIndex] + RESET;
        System.out.println("\n");
        String result = String.format("BMI: %.2f - %s", bmi, coloredCategory);
        System.out.println(result);
        displayBMIBar(bmi, categoryIndex);
    }

    private double convertFeetInchesToMeters(int feet, int inches) {
        int totalInches = feet * 12 + inches;
        return totalInches * 0.0254;
    }

    private int classifyBMIIndex(double bmi) {
        if (bmi < 18.5) return 0;
        else if (bmi < 25) return 1;
        else if (bmi < 30) return 2;
        else return 3;
    }

    private void displayBMIBar(double bmi, int categoryIndex) {
        String[] categories = {"Underweight", "Normal", "Overweight", "Obese"};
        double[] bounds = {0, 18.5, 25, 30, 50};

        String[] colors = {
                "\u001B[94m",
                "\u001B[33m",
                "\u001B[38;5;208m",
                "\u001B[31m"
        };
        String RESET = "\u001B[0m";

        int chartWidth = 80;
        double bmiMin = bounds[0];
        double bmiMax = bounds[bounds.length - 1];
        double bmiRange = bmiMax - bmiMin;
        int pointerPosition = (int) ((bmi - bmiMin) / bmiRange * chartWidth);
        pointerPosition = Math.min(pointerPosition, chartWidth - 1);

        System.out.println("\n                                       BMI CHART                               ");

        System.out.print("|");
        for (int i = 0; i < bounds.length - 1; i++) {
            int start = (int) ((bounds[i] - bmiMin) / bmiRange * chartWidth);
            int end = (int) ((bounds[i + 1] - bmiMin) / bmiRange * chartWidth);
            String bar = colors[i] + "█".repeat(Math.max(1, end - start)) + RESET;
            System.out.print(bar);
        }
        System.out.println("|");

        System.out.print(" ");
        for (int i = 0; i < pointerPosition; i++) System.out.print(" ");
        System.out.println("⬆️");


        System.out.println();
        System.out.println("╔══════════════════╦═════════════════════════╗");
        System.out.println("║     Category     ║        BMI Range        ║");
        System.out.println("╠══════════════════╬═════════════════════════╣");
        for (int i = 0; i < categories.length; i++) {
            String coloredName = colors[i] + String.format("%-16s", categories[i]) + RESET;
            String range = String.format("%.1f - %.1f", bounds[i], bounds[i + 1]);
            System.out.printf("║ %-19s ║ %-23s ║\n", coloredName, range);
        }
        System.out.println("╚══════════════════╩═════════════════════════╝");
    }
}
