package trackers;

import interfaces.HealthTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SleepTracker implements HealthTracker {
    private final List<Double> hoursSlept = new ArrayList<>();
    private final List<String> sleepQuality = new ArrayList<>();

    @Override
    public void log() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🛌 Log your sleep:");

        System.out.print("Hours slept: ");
        double hours = Double.parseDouble(scanner.nextLine());
        hoursSlept.add(hours);

        System.out.print("Sleep quality (Poor / Average / Good): ");
        String quality = scanner.nextLine().trim();
        sleepQuality.add(quality);

        System.out.println("✅ Sleep logged successfully.");
    }

    @Override
    public void generateReport() {
        System.out.println("\n📋 Sleep Report:");

        if (hoursSlept.isEmpty()) {
            System.out.println("No sleep data available.");
            return;
        }

        double total = 0;
        for (double h : hoursSlept) total += h;
        double avg = total / hoursSlept.size();

        System.out.println("Total Days Logged: " + hoursSlept.size());
        System.out.printf("Average Sleep: %.2f hours%n", avg);
        System.out.println("Last Sleep Quality: " + sleepQuality.get(sleepQuality.size() - 1));

        if (avg < 6.0) {
            System.out.println("⚠️ You're not getting enough sleep. Try to rest more!");
        } else {
            System.out.println("✅ Great! You're sleeping well.");
        }
    }
}

