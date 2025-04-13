package trackers;

import interfaces.HealthTracker;
import interfaces.Summarizable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MentalHealthTracker implements HealthTracker, Summarizable {
    private static class MentalHealthEntry {
        String mood;
        int stressLevel;
        String notes;

        MentalHealthEntry(String mood, int stressLevel, String notes) {
            this.mood = mood;
            this.stressLevel = stressLevel;
            this.notes = notes;
        }
    }

    private final List<MentalHealthEntry> entries = new ArrayList<>();

    @Override
    public void log() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🧠 Mental Health Check-In");

        System.out.print("Mood (e.g., Happy, Sad, Anxious): ");
        String mood = scanner.nextLine().trim();

        System.out.print("Stress Level (1-10): ");
        int stress = Integer.parseInt(scanner.nextLine());

        System.out.print("Any thoughts to reflect on? (optional): ");
        String notes = scanner.nextLine().trim();

        entries.add(new MentalHealthEntry(mood, stress, notes));
        System.out.println("✅ Mental health entry saved.");
    }

    @Override
    public void generateReport() {
        System.out.println("\n📋 Mental Health Report:");

        if (entries.isEmpty()) {
            System.out.println("No mental health entries yet.");
            return;
        }

        MentalHealthEntry last = entries.get(entries.size() - 1);
        System.out.println("Latest Mood: " + last.mood);
        System.out.println("Latest Stress Level: " + last.stressLevel);
        if (!last.notes.isEmpty()) {
            System.out.println("Note: " + last.notes);
        }

        if (last.stressLevel >= 8) {
            System.out.println("⚠️ High stress detected. Consider relaxing or seeking support.");
        } else {
            System.out.println("✅ You seem to be managing stress well.");
        }
    }
    @Override
    public String getSummary() {
        if (entries.isEmpty()) {
            return "🧠 Mental Health: No data.";
        }

        MentalHealthEntry last = entries.get(entries.size() - 1);
        return "🧠 Mood: " + last.mood + ", Stress: " + last.stressLevel +
                (last.stressLevel >= 8 ? " (⚠️ High Stress)" : " (✅ Stable)");
    }


}

