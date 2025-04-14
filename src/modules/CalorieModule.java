package modules;

import interfaces.HealthModule;
import interfaces.Summarizable;
import utils.FileUtil;
import java.util.Scanner;

public class CalorieModule implements HealthModule, Summarizable {
    @Override
    public void execute(Scanner sc) {
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        System.out.print("Enter weight (kg): ");
        double weight = sc.nextDouble();
        System.out.print("Enter height (cm): ");
        double height = sc.nextDouble();
        System.out.print("Enter gender (M/F): ");
        String gender = sc.next();

        double bmr = gender.equalsIgnoreCase("M")
                ? 10 * weight + 6.25 * height - 5 * age + 5
                : 10 * weight + 6.25 * height - 5 * age - 161;

        String result = "Your estimated daily calorie need: " + bmr + " kcal";
        System.out.println(result);
        FileUtil.writeToFile("calorie.txt", result);
    }

    @Override
    public String generateSummary() {
        return "Calorie requirement calculated based on BMR.";
    }
}

