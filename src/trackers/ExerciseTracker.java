package trackers;

import interfaces.HealthTracker;
import interfaces.Summarizable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExerciseTracker implements HealthTracker, Summarizable {
    private static class ExerciseEntry {
        String type;
        int duration;

        ExerciseEntry(String type, int duration) {
            this.type = type;
            this.duration = duration;
        }
    }

    private final List<ExerciseEntry> exercises = new ArrayList<>();

    @Override
    public void log() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🏋️ Log your workout:");

        System.out.print("Exercise type (e.g., Running, Yoga, Cycling): ");
        String type = scanner.nextLine().trim();

        System.out.print("Duration in minutes: ");
        int duration = Integer.parseInt(scanner.nextLine());

        exercises.add(new ExerciseEntry(type, duration));
        System.out.println("✅ Workout logged successfully.");
    }

    @Override
    public void generateReport() {
        System.out.println("\n📋 Exercise Report:");

        if (exercises.isEmpty()) {
            System.out.println("No workouts logged yet.");
            return;
        }

        int totalDuration = 0;
        for (ExerciseEntry entry : exercises) {
            System.out.println("- " + entry.type + ": " + entry.duration + " minutes");
            totalDuration += entry.duration;
        }

        System.out.println("Total Workout Sessions: " + exercises.size());
        System.out.println("Total Time Exercised: " + totalDuration + " minutes");

        if (totalDuration < 90) {
            System.out.println("⚠️ Consider increasing your weekly physical activity.");
        } else {
            System.out.println("✅ Great job staying active!");
        }
    }

    @Override
    public String getSummary() {
        if (exercises.isEmpty()) {
            return "🏃 Exercise: No data.";
        }

        int totalTime = exercises.stream().mapToInt(e -> e.duration).sum();
        return "🏃 Exercise: " + totalTime + " mins (" +
                (totalTime < 90 ? "⚠️ Low Activity" : "✅ Active") + ")";
    }

}
