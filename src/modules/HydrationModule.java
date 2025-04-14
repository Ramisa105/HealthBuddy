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
        System.out.print("Enter your weight (in kg): ");
        double weightKg = sc.nextDouble();
        double weightLbs = weightKg * 2.20462;

        System.out.print("Enter your gender (M/F): ");
        String gender = sc.next().toLowerCase();

        String femaleStatus = "none";
        if (gender.equals("f")) {
            System.out.println("Select status:");
            System.out.println("   1. Not Pregnant");
            System.out.println("   2. Pregnant");
            System.out.println("   3. Lactating");
            System.out.print("Enter choice (1-3): ");
            int femaleChoice = sc.nextInt();

            switch (femaleChoice) {
                case 2:
                    femaleStatus = "pregnant";
                    break;
                case 3:
                    femaleStatus = "lactating";
                    break;
                default:
                    femaleStatus = "not pregnant";
                    break;
            }
        }

        System.out.print("\nEnter average daily exercise duration (in minutes): ");
        int exerciseMinutes = sc.nextInt();


        double waterOunces = weightLbs / 2;


        waterOunces += (exerciseMinutes / 30.0) * 12;

        if (femaleStatus.equals("pregnant")) waterOunces += 24;
        else if (femaleStatus.equals("lactating")) waterOunces += 32;

        double waterLiters = waterOunces * 0.0295735;

        System.out.printf("\n✅ Recommended Daily Water Intake: %.1f oz (%.2f liters)\n", waterOunces, waterLiters);

        System.out.print("\nWould you like hydration reminders? (y/n): ");
        String remindChoice = sc.next().toLowerCase();
        if (remindChoice.equals("y")) {
            System.out.print("Set reminder interval (in minutes): ");
            int intervalMinutes = sc.nextInt();
            startReminder(intervalMinutes);
        }
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
