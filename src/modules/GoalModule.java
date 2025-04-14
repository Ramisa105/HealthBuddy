package modules;

import interfaces.HealthModule;
import models.Goal;

import java.awt.Toolkit;
import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GoalModule implements HealthModule {
    private final List<Goal> goals = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String FILE_NAME = "goals.dat";

    @Override
    public void execute(Scanner ignored) {
        loadGoalsFromFile();

        while (true) {
            System.out.println("\n---- Goal Tracker ----");
            System.out.println("1. Add Goal");
            System.out.println("2. View Goals");
            System.out.println("3. Delete Goal");
            System.out.println("4. Mark Goal as Done");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addGoal();
                case 2 -> viewGoals();
                case 3 -> deleteGoal();
                case 4 -> markGoalDone();
                case 5 -> {
                    saveGoalsToFile();
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addGoal() {
        System.out.print("Enter goal title: ");
        String title = sc.nextLine();

        System.out.print("Enter deadline (yyyy-MM-dd HH:mm): ");
        String deadlineStr = sc.nextLine();
        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, formatter);

        Goal goal = new Goal(title, deadline);
        goals.add(goal);
        scheduleReminder(goal);
        saveGoalsToFile();
        System.out.println("Goal added and reminder scheduled!");
    }

    private void scheduleReminder(Goal goal) {
        Timer timer = new Timer();
        long delay = java.time.Duration.between(LocalDateTime.now(), goal.getDeadline()).toMillis();

        if (delay <= 0) return;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Toolkit.getDefaultToolkit().beep();
                System.out.println("\n⏰ Deadline reached for goal: " + goal.getTitle());

                if (!goal.isDone()) {
                    System.out.print("Did you complete the goal? (yes/no): ");
                    String response = sc.nextLine().toLowerCase();

                    if (response.equals("yes")) {
                        goal.markDone();
                        System.out.println("✅ Goal marked as done!");
                    } else {
                        System.out.print("Enter new deadline (yyyy-MM-dd HH:mm): ");
                        String newDeadlineStr = sc.nextLine();
                        LocalDateTime newDeadline = LocalDateTime.parse(newDeadlineStr, formatter);
                        goal.setDeadline(newDeadline);
                        scheduleReminder(goal);
                        System.out.println("⏳ New reminder scheduled.");
                    }
                    saveGoalsToFile();
                }
            }
        }, delay);
    }

    private void viewGoals() {
        if (goals.isEmpty()) {
            System.out.println("No goals added.");
            return;
        }

        System.out.println("\nYour Goals:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < goals.size(); i++) {
            Goal goal = goals.get(i);
            String status = goal.isDone() ? "✅ Done" : "❌ Not Done";
            String countdown = getCountdown(goal.getDeadline());

            System.out.printf("%d. Goal: %s | Deadline: %s | Status: %s | ⏳ %s\n",
                    i + 1, goal.getTitle(), goal.getDeadline().format(formatter), status, countdown);
        }
    }


    private void deleteGoal() {
        viewGoals();
        System.out.print("Enter goal number to delete: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        if (index >= 0 && index < goals.size()) {
            System.out.println("Deleted: " + goals.remove(index).getTitle());
            saveGoalsToFile();
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void markGoalDone() {
        viewGoals();
        System.out.print("Enter goal number to mark as done: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        if (index >= 0 && index < goals.size()) {
            goals.get(index).markDone();
            System.out.println("Goal marked as done!");
            saveGoalsToFile();
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void saveGoalsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(goals);
        } catch (IOException e) {
            System.out.println("Error saving goals: " + e.getMessage());
        }
    }

    private void loadGoalsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Goal> loadedGoals = (List<Goal>) ois.readObject();
            goals.addAll(loadedGoals);
            // Reschedule reminders for all future goals
            for (Goal goal : goals) {
                if (!goal.isDone() && goal.getDeadline().isAfter(LocalDateTime.now())) {
                    scheduleReminder(goal);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading goals: " + e.getMessage());
        }
    }

    private String getCountdown(LocalDateTime deadline) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, deadline);

        long days = Math.abs(duration.toDays());
        long hours = Math.abs(duration.toHours() % 24);
        long minutes = Math.abs(duration.toMinutes() % 60);

        if (deadline.isAfter(now)) {
            return String.format("%d days, %d hours, %d minutes left", days, hours, minutes);
        } else {
            return String.format("%d days, %d hours, %d minutes ago", days, hours, minutes);
        }
    }

}
