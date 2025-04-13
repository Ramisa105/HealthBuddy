package healthbuddy;

import factory.HealthTrackerFactory;
import features.BMICalculator;
import features.CalorieCalculator;
import features.WeeklyReportGenerator;

import interfaces.Summarizable;
import models.UserProfile;
import trackers.*;
import utils.UserManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        DietTracker dietTracker = (DietTracker) HealthTrackerFactory.getTracker("diet");
        SleepTracker sleepTracker = (SleepTracker) HealthTrackerFactory.getTracker("sleep");
        ExerciseTracker exerciseTracker = (ExerciseTracker) HealthTrackerFactory.getTracker("exercise");
        MentalHealthTracker mentalHealthTracker = (MentalHealthTracker) HealthTrackerFactory.getTracker("mental");
        MedicalRecordTracker medicalRecordTracker = (MedicalRecordTracker) HealthTrackerFactory.getTracker("medical");
        UserProfile profile = UserManager.getProfile();

        System.out.println("╔════════════════════════════════╗");
        System.out.println("║      Welcome to HealthBuddy    ║");
        System.out.println("╚════════════════════════════════╝");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    dietTracker.log();
                    break;
                case "11":
                    dietTracker.generateReport();
                    break;

                case "2":
                    sleepTracker.log();
                    break;
                case "22":
                    sleepTracker.generateReport();
                    break;

                case "3":
                    exerciseTracker.log();
                    break;
                case "33":
                    exerciseTracker.generateReport();
                    break;

                case "4":
                    mentalHealthTracker.log();
                    break;
                case "44":
                    mentalHealthTracker.generateReport();
                    break;

                case "5":
                    medicalRecordTracker.log();
                    break;
                case "55":
                    medicalRecordTracker.generateReport();
                    break;

                case "6":
                    List<Summarizable> summaries = List.of(
                            dietTracker,
                            sleepTracker,
                            exerciseTracker,
                            mentalHealthTracker,
                            medicalRecordTracker
                    );
                    WeeklyReportGenerator reportGen = new WeeklyReportGenerator(summaries);
                    reportGen.generateFullReport();
                    break;

                case "7":
                    CalorieCalculator.showCalorieNeeds(profile);
                    break;

                case "8":
                    BMICalculator.showBMI(profile);
                    break;

                case "0":
                    System.out.println("👋 Stay healthy. Exiting HealthBuddy!");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n📋 Menu:");
        System.out.println("1.  🍎 Log Diet & Nutrition");
        System.out.println("11. 📄 View Diet Report");

        System.out.println("2.  😴 Log Sleep");
        System.out.println("22. 📄 View Sleep Report");

        System.out.println("3.  🏋️ Log Exercise");
        System.out.println("33. 📄 View Exercise Report");

        System.out.println("4.  🧠 Log Mental Health Check-In");
        System.out.println("44. 📄 View Mental Health Report");

        System.out.println("5.  🩺 Log Medical Record (Vitals)");
        System.out.println("55. 📄 View Last Medical Record Report");

        System.out.println("6.  📊 Generate Weekly Health Report");

        System.out.println("7. View Calorie Needs (BMR & TDEE)");

        System.out.println("8. Check BMI");

        System.out.println("0.  ❌ Exit");

        System.out.print("👉 Enter your choice: ");
    }
}
