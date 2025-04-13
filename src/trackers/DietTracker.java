package trackers;

import interfaces.HealthTracker;
import interfaces.Summarizable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DietTracker implements HealthTracker, Summarizable {
    private final List<String> meals = new ArrayList<>();

    @Override
    public void log() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🍽️ Log your meals (type 'done' to finish):");

        while (true) {
            System.out.print("Meal: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            if (!input.isEmpty()) meals.add(input);
        }

        System.out.println("✅ Meals logged successfully.");
    }

    @Override
    public void generateReport() {
        System.out.println("\n📋 Diet Report:");
        if (meals.isEmpty()) {
            System.out.println("No meals logged yet.");
            return;
        }

        for (int i = 0; i < meals.size(); i++) {
            System.out.println((i + 1) + ". " + meals.get(i));
        }

        if (meals.size() < 3) {
            System.out.println("⚠️ You’ve logged fewer than 3 meals. Don't skip meals!");
        } else {
            System.out.println("✅ You're maintaining a consistent meal pattern.");
        }
    }

    @Override
    public String getSummary() {
        return "🍽️ Meals Logged: " + meals.size() +
                (meals.size() < 3 ? " (⚠️ Low)" : " (✅ Good)");
    }
}
