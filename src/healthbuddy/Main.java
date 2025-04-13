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
                    HealthTracker medical = HealthTrackerFactory.getTracker("medical");
                    medical.log();
                    break;
                case "2":
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
        System.out.println("1. Log Medical Record (Vitals)");
        System.out.println("2. View Last Medical Record Report");
        System.out.println("0. Exit");
        System.out.print("👉 Enter your choice: ");
    }
}
