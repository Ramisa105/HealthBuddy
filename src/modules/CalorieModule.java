package modules;

import interfaces.HealthModule;

import java.util.Scanner;

public class CalorieModule implements HealthModule {

    @Override
    public void execute(Scanner sc) {
        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        System.out.print("Enter your gender (M/F): ");
        String gender = sc.next().toLowerCase();

        System.out.print("Enter your weight (in kg): ");
        double weight = sc.nextDouble();

        System.out.print("Enter your height\n   Feet: ");
        int feet = sc.nextInt();
        System.out.print("   Inches: ");
        int inches = sc.nextInt();

        double heightCm = ((feet * 12) + inches) * 2.54;

        System.out.println("Select exercise level:");
        System.out.println("     1. Sedentary (little or no exercise)");
        System.out.println("     2. Lightly active (light exercise/sports 1–3 days/week)");
        System.out.println("     3. Moderately active (moderate exercise/sports 3–5 days/week)");
        System.out.println("     4. Very active (hard exercise/sports 6–7 days/week)");
        System.out.println("     5. Extra active (very hard exercise & physical job or 2x training)");
        System.out.print("Enter choice (1-5): ");
        int activityChoice = sc.nextInt();

        double activityFactor;
        switch (activityChoice) {
            case 1: activityFactor = 1.2; break;
            case 2: activityFactor = 1.375; break;
            case 3: activityFactor = 1.55; break;
            case 4: activityFactor = 1.725; break;
            case 5: activityFactor = 1.9; break;
            default:
                System.out.println("Invalid input. Using sedentary by default.");
                activityFactor = 1.2;
        }


        double bmr;
        if (gender.equalsIgnoreCase("m")) {
            bmr = 10 * weight + 6.25 * heightCm - 5 * age + 5;
        } else if (gender.equalsIgnoreCase("f")) {
            bmr = 10 * weight + 6.25 * heightCm - 5 * age - 161;
        } else {
            System.out.println("Invalid gender.");
            return;
        }

        double tdee = bmr * activityFactor;

        System.out.println("\n                    Your Calorie Stats");
        System.out.println("                   ════════════════════");
        System.out.printf("BMR (Basal Metabolic Rate)           : %.2f calories/day\n", bmr);
        System.out.printf("TDEE (Total Daily Energy Expenditure): %.2f calories/day\n\n", tdee);

        StringBuilder chart = new StringBuilder();
        chart.append("╔═════════════════════════════════╦══════════════════╦═══════════════╗\n");
        chart.append("║           Weight Goal           ║   Calories/day   ║   % of TDEE   ║\n");
        chart.append("╠═════════════════════════════════╬══════════════════╬═══════════════╣\n");
        chart.append(String.format("║ Maintain weight                 ║ %.0f             ║ 100%%          ║\n", tdee));
        chart.append(String.format("║ Mild weight loss (0.5lb/week)   ║ %.0f             ║ 90%%           ║\n", tdee * 0.9));
        chart.append(String.format("║ Weight loss (1lb/week)          ║ %.0f             ║ 80%%           ║\n", tdee * 0.8));
        chart.append(String.format("║ Extreme loss (2lb/week)         ║ %.0f             ║ 61%%           ║\n", tdee * 0.61));
        chart.append(String.format("║ Mild gain (0.5lb/week)          ║ %.0f             ║ 110%%          ║\n", tdee * 1.1));
        chart.append(String.format("║ Weight gain (1lb/week)          ║ %.0f             ║ 120%%          ║\n", tdee * 1.2));
        chart.append(String.format("║ Fast gain (2lb/week)            ║ %.0f             ║ 139%%          ║\n", tdee * 1.39));
        chart.append("╚═════════════════════════════════╩══════════════════╩═══════════════╝");

        System.out.println(chart);
        System.out.println("\n");

    }
}
