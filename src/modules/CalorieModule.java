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

        System.out.print("Enter height - feet: ");
        int feet = sc.nextInt();
        System.out.print("Enter height - inches: ");
        int inches = sc.nextInt();
        double heightCm = convertFeetInchesToCm(feet, inches);

        System.out.print("Enter gender (M/F): ");
        String gender = sc.next();

        System.out.println("Select exercise level:");
        System.out.println("1. Sedentary (little or no exercise)");
        System.out.println("2. Lightly active (light exercise/sports 1-3 days/week)");
        System.out.println("3. Moderately active (moderate exercise/sports 3-5 days/week)");
        System.out.println("4. Very active (hard exercise/sports 6-7 days a week)");
        System.out.println("5. Extra active (very hard exercise/sports & physical job or 2x training)");
        System.out.print("Enter choice (1-5): ");
        int activityChoice = sc.nextInt();

        double bmr = calculateBMR(gender, weight, heightCm, age);
        double tdee = bmr * getActivityFactor(activityChoice);

        StringBuilder result = new StringBuilder();
        result.append(String.format("Maintain weight: %.0f kcal/day (100%%)\n", tdee));
        result.append(String.format("Mild weight loss (0.5 lb/week): %.0f kcal/day (90%%)\n", tdee * 0.9));
        result.append(String.format("Weight loss (1 lb/week): %.0f kcal/day (80%%)\n", tdee * 0.8));
        result.append(String.format("Extreme weight loss (2 lb/week): %.0f kcal/day (61%%)\n", tdee * 0.61));
        
        result.append(String.format("Mild weight gain (0.5 lb/week): %.0f kcal/day (110%%)\n", tdee * 1.1));
        result.append(String.format("Weight gain (1 lb/week): %.0f kcal/day (120%%)\n", tdee * 1.2));
        result.append(String.format("Fast weight gain (2 lb/week): %.0f kcal/day (139%%)\n", tdee * 1.39));

        System.out.println(result.toString());
        FileUtil.writeToFile("calorie.txt", result.toString());
    }

    private double convertFeetInchesToCm(int feet, int inches) {
        int totalInches = feet * 12 + inches;
        return totalInches * 2.54;
    }

    private double calculateBMR(String gender, double weight, double height, int age) {
        if (gender.equalsIgnoreCase("M")) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }

    private double getActivityFactor(int choice) {
        switch (choice) {
            case 1: return 1.2;
            case 2: return 1.375;
            case 3: return 1.55;
            case 4: return 1.725;
            case 5: return 1.9;
            default: return 1.2;
        }
    }

    @Override
    public String generateSummary() {
        return "Calories calculated from gender, weight, height (ft/in), age, and activity.";
    }
}
