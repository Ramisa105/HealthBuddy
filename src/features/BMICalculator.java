package features;

import models.UserProfile;

public class BMICalculator {

    public static double calculateBMI(UserProfile user) {
        double weight = user.getWeight();
        double heightInMeters = user.getHeight() / 100.0;

        return weight / (heightInMeters * heightInMeters);
    }

    public static String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal weight";
        else if (bmi < 29.9) return "Overweight";
        else return "Obese";
    }

    public static void showBMI(UserProfile user) {
        double bmi = calculateBMI(user);
        String category = getBMICategory(bmi);

        System.out.println("\n📏 BMI Calculator:");
        System.out.printf("Your BMI: %.2f (%s)\n", bmi, category);

        switch (category) {
            case "Underweight":
                System.out.println("⚠️ You might need to gain weight. Eat nutrient-rich foods and consult a doctor.");
                break;
            case "Overweight":
            case "Obese":
                System.out.println("⚠️ Consider reducing calorie intake, regular exercise, and medical advice.");
                break;
            default:
                System.out.println("✅ Your weight is in a healthy range. Keep it up!");
        }
    }
}
