package modules;

import interfaces.HealthModule;
import interfaces.Summarizable;
import utils.FileUtil;
import java.util.Scanner;

public class BMIModule implements HealthModule, Summarizable {
    private double weight, height;

    @Override
    public void execute(Scanner sc) {
        System.out.print("Enter weight (kg): ");
        weight = sc.nextDouble();
        System.out.print("Enter height (m): ");
        height = sc.nextDouble();

        double bmi = weight / (height * height);
        String category = (bmi < 18.5) ? "Underweight" :
                (bmi < 24.9) ? "Normal" :
                        (bmi < 29.9) ? "Overweight" : "Obese";

        String result = "BMI: " + bmi + " - " + category;
        System.out.println(result);
        FileUtil.writeToFile("bmi.txt", result);
    }

    @Override
    public String generateSummary() {
        return "BMI calculated based on height and weight.";
    }
}
