package features;

import models.UserProfile;

public class CalorieCalculator {

    public static double calculateBMR(UserProfile user) {
        double weight = user.getWeight();
        double height = user.getHeight();
        int age = user.getAge();
        String gender = user.getGender().toLowerCase();

        if (gender.equals("male")) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }

    public static double getActivityMultiplier(String activityLevel) {
        return switch (activityLevel.toLowerCase()) {
            case "sedentary" -> 1.2;
            case "lightly active" -> 1.375;
            case "moderately active" -> 1.55;
            case "very active" -> 1.725;
            case "super active" -> 1.9;
            default -> 1.2;
        };
    }

    public static double calculateTDEE(UserProfile user) {
        double bmr = calculateBMR(user);
        double multiplier = getActivityMultiplier(user.getActivityLevel());
        return bmr * multiplier;
    }

    public static void showCalorieNeeds(UserProfile user) {
        double bmr = calculateBMR(user);
        double tdee = calculateTDEE(user);

        System.out.println("\n🔥 Calorie Calculator Results:");
        System.out.printf("Basal Metabolic Rate (BMR): %.2f kcal/day\n", bmr);
        System.out.printf("Total Daily Energy Expenditure (TDEE): %.2f kcal/day\n", tdee);
        System.out.println("👉 To maintain weight: Eat ~" + Math.round(tdee) + " kcal/day");
        System.out.println("📉 To lose weight: Eat ~" + Math.round(tdee - 500) + " kcal/day");
        System.out.println("📈 To gain weight: Eat ~" + Math.round(tdee + 500) + " kcal/day");
    }
}
