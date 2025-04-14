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
        String category = classifyBMI(bmi);

        String result = String.format("BMI: %.2f - %s", bmi, category);
        System.out.println(result);
        FileUtil.writeToFile("bmi.txt", result);
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
        return "BMI calculated based on height and weight.";
    }
}
