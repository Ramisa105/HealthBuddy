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
}
