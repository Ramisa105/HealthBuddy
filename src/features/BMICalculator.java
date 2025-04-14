package features;

import models.UserProfile;

public class BMICalculator {

    public static double calculateBMI(UserProfile user) {
        double weight = user.getWeightKg();
        double heightInMeters = user.getHeightCm() / 100.0;

        return weight / (heightInMeters * heightInMeters);
    }

    public static String getBMICategory(double bmi) {
        if (bmi < 16) return "Severe Thinness";
        else if (bmi < 17) return "Moderate Thinness";
        else if (bmi < 18.5) return "Mild Thinness";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else if (bmi < 35) return "Obese Class I";
        else if (bmi < 40) return "Obese Class II";
        else return "Obese Class III";
    }

    public static void showBMI(UserProfile user) {
        double bmi = calculateBMI(user);
        String category = getBMICategory(bmi);
        String range = getBMIRange(category);

        System.out.println("\n📏 BMI Calculator:");
        System.out.printf("Your BMI: %.2f\n", bmi);
        System.out.println("Category: " + category + " (" + range + ")");

        switch (category) {
            case "Severe Thinness":
            case "Moderate Thinness":
            case "Mild Thinness":
                System.out.println("\n⚠️ You are under the healthy range. Consider improving nutrition and consulting a healthcare provider.");
                break;
            case "Overweight":
            case "Obese Class I":
            case "Obese Class II":
            case "Obese Class III":
                System.out.println("\n⚠️ You are above the healthy range. Consider healthy diet changes and regular exercise.");
                break;
            case "Normal":
                System.out.println("\n✅ Your BMI is within the healthy range. Keep it up!");
                break;
        }
    }


    private static String getBMIRange(String category) {
        return switch (category) {
            case "Severe Thinness" -> "< 16";
            case "Moderate Thinness" -> "16 - 17";
            case "Mild Thinness" -> "17 - 18.5";
            case "Normal" -> "18.5 - 25";
            case "Overweight" -> "25 - 30";
            case "Obese Class I" -> "30 - 35";
            case "Obese Class II" -> "35 - 40";
            case "Obese Class III" -> "> 40";
            default -> "Unknown";
        };
    }

}

