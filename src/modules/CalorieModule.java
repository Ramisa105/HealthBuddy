package modules;

import interfaces.HealthModule;

import java.util.Scanner;

public class CalorieModule implements HealthModule {

    @Override
    public void execute(Scanner sc) {
        int age = promptAge(sc);
        String gender = promptGender(sc);
        double weight = promptWeight(sc);
        double heightCm = promptHeightInCm(sc);
        double activityFactor = promptActivityLevel(sc);

        double bmr = calculateBMR(gender, weight, heightCm, age);
        double tdee = bmr * activityFactor;

        displayCalorieStats(bmr, tdee);
    }

    private int promptAge(Scanner sc) {
        System.out.print("Enter your age: ");
        return sc.nextInt();
    }

    private String promptGender(Scanner sc) {
        System.out.print("Enter your gender (M/F): ");
        return sc.next().toLowerCase();
    }

    private double promptWeight(Scanner sc) {
        System.out.print("Enter your weight (in kg): ");
        return sc.nextDouble();
    }

    private double promptHeightInCm(Scanner sc) {
        System.out.print("Enter your height\n   Feet: ");
        int feet = sc.nextInt();
        System.out.print("   Inches: ");
        int inches = sc.nextInt();
        return ((feet * 12) + inches) * 2.54;
    }

    private double promptActivityLevel(Scanner sc) {
        System.out.println("Select exercise level:");
        System.out.println("     1. Sedentary (little or no exercise)");
        System.out.println("     2. Lightly active (light exercise/sports 1–3 days/week)");
        System.out.println("     3. Moderately active (moderate exercise/sports 3–5 days/week)");
        System.out.println("     4. Very active (hard exercise/sports 6–7 days/week)");
        System.out.println("     5. Extra active (very hard exercise & physical job or 2x training)");
        System.out.print("Enter choice (1-5): ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1: return 1.2;
            case 2: return 1.375;
            case 3: return 1.55;
            case 4: return 1.725;
            case 5: return 1.9;
            default:
                System.out.println("Invalid input. Using sedentary by default.");
                return 1.2;
        }
    }

    private double calculateBMR(String gender, double weight, double heightCm, int age) {
        if (gender.equals("m")) {
            return 10 * weight + 6.25 * heightCm - 5 * age + 5;
        } else if (gender.equals("f")) {
            return 10 * weight + 6.25 * heightCm - 5 * age - 161;
        } else {
            System.out.println("Invalid gender. Calculation aborted.");
            return 0;
        }
    }

    private void displayCalorieStats(double bmr, double tdee) {
        System.out.println("\n                    Your Calorie Stats");
        System.out.println("                   ════════════════════");
        System.out.printf("BMR (Basal Metabolic Rate)           : %.2f calories/day\n", bmr);
        System.out.printf("TDEE (Total Daily Energy Expenditure): %.2f calories/day\n\n", tdee);

        System.out.println("╔═════════════════════════════════╦══════════════════╦═══════════════╗");
        System.out.println("║           Weight Goal           ║   Calories/day   ║   % of TDEE   ║");
        System.out.println("╠═════════════════════════════════╬══════════════════╬═══════════════╣");
        System.out.printf("║ Maintain weight                 ║ %.0f             ║ 100%%          ║\n", tdee);
        System.out.printf("║ Mild weight loss (0.5lb/week)   ║ %.0f             ║ 90%%           ║\n", tdee * 0.9);
        System.out.printf("║ Weight loss (1lb/week)          ║ %.0f             ║ 80%%           ║\n", tdee * 0.8);
        System.out.printf("║ Extreme loss (2lb/week)         ║ %.0f             ║ 61%%           ║\n", tdee * 0.61);
        System.out.printf("║ Mild gain (0.5lb/week)          ║ %.0f             ║ 110%%          ║\n", tdee * 1.1);
        System.out.printf("║ Weight gain (1lb/week)          ║ %.0f             ║ 120%%          ║\n", tdee * 1.2);
        System.out.printf("║ Fast gain (2lb/week)            ║ %.0f             ║ 139%%          ║\n", tdee * 1.39);
        System.out.println("╚═════════════════════════════════╩══════════════════╩═══════════════╝");
        System.out.println();
    }
}
