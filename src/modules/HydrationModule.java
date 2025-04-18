package modules;

import interfaces.HealthModule;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class HydrationModule implements HealthModule {

    @Override
    public void execute(Scanner sc) {
        double weightKg = promptWeight(sc);
        String gender = promptGender(sc);
        String femaleStatus = gender.equals("f") ? promptFemaleStatus(sc) : "none";
        int exerciseMinutes = promptExerciseMinutes(sc);

        double waterOunces = calculateWaterOunces(weightKg, exerciseMinutes, femaleStatus);
        double waterLiters = waterOunces * 0.0295735;

        System.out.printf("\n✅ Recommended Daily Water Intake: %.1f oz (%.2f liters)\n", waterOunces, waterLiters);

        if (askForReminders(sc)) {
            int interval = promptReminderInterval(sc);
            startReminder(interval);
        }
    }

    private double promptWeight(Scanner sc) {
        System.out.print("Enter your weight (in kg): ");
        return sc.nextDouble();
    }

    private String promptGender(Scanner sc) {
        System.out.print("Enter your gender (M/F): ");
        return sc.next().toLowerCase();
    }

    private String promptFemaleStatus(Scanner sc) {
        System.out.println("Select status:");
        System.out.println("   1. Not Pregnant");
        System.out.println("   2. Pregnant");
        System.out.println("   3. Lactating");
        System.out.print("Enter choice (1-3): ");
        int choice = sc.nextInt();

        return switch (choice) {
            case 2 -> "pregnant";
            case 3 -> "lactating";
            default -> "not pregnant";
        };
    }

    private int promptExerciseMinutes(Scanner sc) {
        System.out.print("\nEnter average daily exercise duration (in minutes): ");
        return sc.nextInt();
    }

    private double calculateWaterOunces(double weightKg, int exerciseMinutes, String femaleStatus) {
        double weightLbs = weightKg * 2.20462;
        double waterOunces = weightLbs / 2;
        waterOunces += (exerciseMinutes / 30.0) * 12;

        if (femaleStatus.equals("pregnant")) {
            waterOunces += 24;
        } else if (femaleStatus.equals("lactating")) {
            waterOunces += 32;
        }

        return waterOunces;
    }

    private boolean askForReminders(Scanner sc) {
        System.out.print("\nWould you like hydration reminders? (y/n): ");
        return sc.next().equalsIgnoreCase("y");
    }

    private int promptReminderInterval(Scanner sc) {
        System.out.print("Set reminder interval (in minutes): ");
        return sc.nextInt();
    }

    private void startReminder(int minutes) {
        Timer timer = new Timer();
        long intervalMillis = minutes * 60 * 1000L;

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n⏰ Reminder: Time to drink water! 💧\n");
                playSound("src/alarm.wav");
            }
        }, intervalMillis, intervalMillis);
    }

    private void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("❌ Failed to play sound: " + e.getMessage());
        }
    }
}
