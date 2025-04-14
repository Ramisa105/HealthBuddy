package modules;

import interfaces.HealthModule;
import utils.FileUtil;
import java.util.Scanner;

public class GoalModule implements HealthModule {
    @Override
    public void execute(Scanner sc) {
        sc.nextLine(); // clear buffer
        System.out.print("Enter your health goal: ");
        String goal = sc.nextLine();

        FileUtil.writeToFile("goal.txt", "Goal: " + goal);
        System.out.println("Goal saved successfully!");
    }
}
