package modules;

import interfaces.HealthModule;
import utils.FileUtil;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;
public class HydrationModule implements HealthModule {

    @Override
    public void execute(Scanner sc) {
        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        System.out.print("Enter your height (feet): ");
        int feet = sc.nextInt();

        System.out.print("Enter your height (inches): ");
        int inches = sc.nextInt();
        sc.nextLine();

        int totalInches = (feet * 12) + inches;
        double baseWater = totalInches * 0.5;

        System.out.println("\nSelect exercise level:");
        System.out.println("1. Sedentary (little or no exercise)");
        System.out.println("2. Lightly active (light exercise/sports 1-3 days/week)");
        System.out.println("3. Moderately active (moderate exercise/sports 3-5 days/week)");
        System.out.println("4. Very active (hard exercise/sports 6-7 days a week)");
        System.out.println("5. Extra active (very hard exercise/sports & physical job or 2x training)");
        System.out.print("Enter choice (1-5): ");
        int exerciseChoice = sc.nextInt();
        sc.nextLine();
        switch (exerciseChoice) {
            case 2:
                baseWater += 12;
                break;
            case 3:
                baseWater += 24;
                break;
            case 4:
                baseWater += 36;
                break;
            case 5:
                baseWater += 48;
                break;
            default:
                break;
        }

        System.out.print("Enter your gender (male/female): ");
        String gender = sc.nextLine().toLowerCase();

        String femaleStatus = "none";
        if (gender.equals("female")) {
            System.out.println("\nSelect status:");
            System.out.println("1. Not Pregnant");
            System.out.println("2. Pregnant");
            System.out.println("3. Lactating");
            System.out.print("Enter choice (1-3): ");
            int femaleChoice = sc.nextInt();
            sc.nextLine();

            switch (femaleChoice) {
                case 2:
                    femaleStatus = "pregnant";
                    baseWater += 16;
                    break;
                case 3:
                    femaleStatus = "lactating";
                    baseWater += 32;
                    break;
                default:
                    femaleStatus = "not pregnant";
                    break;
            }
        }

        double waterLiters = baseWater * 0.0295735;

        String result = String.format(
                "Recommended Daily Water Intake: %.1f ounces (%.2f liters)",
                baseWater, waterLiters
        );

        System.out.println("\n" + result);
        FileUtil.writeToFile("hydration.txt", result);

        System.out.print("\nWould you like hydration reminders? (yes/no): ");
        String remindChoice = sc.nextLine().toLowerCase();
        if (remindChoice.equals("yes")) {
            System.out.print("Set reminder interval (in minutes): ");
            int intervalMinutes = sc.nextInt();
            sc.nextLine();
            startReminder(intervalMinutes);
        }
    }

    private void startReminder(int minutes) {
        Timer timer = new Timer();
        long intervalMillis = minutes * 60 * 1000L;

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n⏰ Reminder: Time to drink water! 💧");
                Toolkit.getDefaultToolkit().beep();
            }
        }, intervalMillis, intervalMillis);
    }
}
