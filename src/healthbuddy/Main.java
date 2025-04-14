package healthbuddy;

import factories.HealthModuleFactory;
import interfaces.HealthModule;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Health Tracker Menu =====");
            System.out.println("1. BMI Module");
            System.out.println("2. Calorie Module");
            System.out.println("3. Hydration Module");
            System.out.println("4. Goal Setting Module");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            HealthModule module = HealthModuleFactory.getModule(choice);
            if (module != null) {
                module.execute(sc);
            } else if (choice != 5) {
                System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        System.out.println("Thank you for using Health Tracker!");
    }
}
