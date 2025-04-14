package utils;

import models.UserProfile;

import java.util.Scanner;

public class UserManager {
    private static UserProfile profile;

    public static UserProfile getProfile() {
        if (profile == null) {
            createProfile();
        }
        return profile;
    }

    private static void createProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("👤 Let's set up your HealthBuddy profile:");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter your gender (Male/Female): ");
        String gender = scanner.nextLine();

        System.out.print("Enter your weight (in kg): ");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter your height (in cm): ");
        double height = Double.parseDouble(scanner.nextLine());

        System.out.println("📊 Select your activity level:");
        System.out.println("1. Sedentary (little or no exercise)");
        System.out.println("2. Lightly active (1–3 days/week)");
        System.out.println("3. Moderately active (3–5 days/week)");
        System.out.println("4. Very active (6–7 days/week)");
        System.out.println("5. Super active (physical job or twice daily training)");

        System.out.print("Enter choice (1-5): ");
        String activityChoice = scanner.nextLine();
        String activityLevel = mapActivityLevel(activityChoice);

        profile = new UserProfile(name, age, gender, weight, height, activityLevel);

        System.out.println("\n✅ Profile created successfully!");
        System.out.println(profile);
    }

    private static String mapActivityLevel(String choice) {
        return switch (choice) {
            case "1" -> "Sedentary";
            case "2" -> "Lightly active";
            case "3" -> "Moderately active";
            case "4" -> "Very active";
            case "5" -> "Super active";
            default -> "Sedentary";
        };
    }
}
