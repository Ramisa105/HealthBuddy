package modules;

import interfaces.HealthModule;
import utils.FileUtil;
import java.util.Scanner;

public class HydrationModule implements HealthModule {
    @Override
    public void execute(Scanner sc) {
        System.out.print("Enter your weight (kg): ");
        double weight = sc.nextDouble();

        double waterIntake = weight * 0.033; // in liters
        String result = "Recommended daily water intake: " + String.format("%.2f", waterIntake) + " liters";
        System.out.println(result);
        FileUtil.writeToFile("hydration.txt", result);
    }
}
