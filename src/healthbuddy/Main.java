package healthbuddy;

import factory.HealthTrackerFactory;
import interfaces.HealthTracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("╔════════════════════════════════╗");
        System.out.println("║      Welcome to HealthBuddy    ║");
        System.out.println("╚════════════════════════════════╝");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    HealthTracker diet = HealthTrackerFactory.getTracker("diet");
                    diet.log();
                    break;
                case "11":
                    HealthTracker dietReport = HealthTrackerFactory.getTracker("diet");
                    dietReport.generateReport();
                    break;

                case "2":
                    HealthTracker sleep = HealthTrackerFactory.getTracker("sleep");
                    sleep.log();
                    break;
                case "22":
                    HealthTracker sleepReport = HealthTrackerFactory.getTracker("sleep");
                    sleepReport.generateReport();
                    break;

                case "3":
                    HealthTracker exercise = HealthTrackerFactory.getTracker("exercise");
                    exercise.log();
                    break;
                case "33":
                    HealthTracker exerciseReport = HealthTrackerFactory.getTracker("exercise");
                    exerciseReport.generateReport();
                    break;

                case "4":
                    HealthTracker mental = HealthTrackerFactory.getTracker("mental");
                    mental.log();
                    break;
                case "44":
                    HealthTracker mentalReport = HealthTrackerFactory.getTracker("mental");
                    mentalReport.generateReport();
                    break;

                case "5":
                    HealthTracker medical = HealthTrackerFactory.getTracker("medical");
                    medical.log();
                    break;
                case "55":
                    HealthTracker med = HealthTrackerFactory.getTracker("medical");
                    med.generateReport();
                    break;

                case "0":
                    System.out.println("👋 Stay healthy. Exiting HealthBuddy!");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n📋 Menu:");
        System.out.println("1. Log Diet & Nutrition");
        System.out.println("11. View Diet Report");

        System.out.println("2. Log Sleep");
        System.out.println("22. View Sleep Report");

        System.out.println("3. Log Exercise");
        System.out.println("33. View Exercise Report");

        System.out.println("4. Log Mental Health Check-In");
        System.out.println("44. View Mental Health Report");

        System.out.println("5. Log Medical Record (Vitals)");
        System.out.println("55. View Last Medical Record Report");

        System.out.println("0. Exit");
        
        System.out.print("👉 Enter your choice: ");
    }
}
