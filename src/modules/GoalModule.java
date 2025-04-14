package modules;

import interfaces.HealthModule;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class GoalModule implements HealthModule {

    private static final String FILE_NAME = "src\\goals.txt";
    private final List<Goal> goals = new ArrayList<>();

    static class Goal implements Serializable {
        String description;
        Date deadline;
        boolean isDone;

        Goal(String description, Date deadline) {
            this.description = description;
            this.deadline = deadline;
            this.isDone = false;
        }
    }

    @Override
    public void execute(Scanner sc) {
        loadGoals();
        checkForReminders(sc);

        while (true) {
            System.out.println("\n--- Goal Tracker ---");
            System.out.println("1. Add Goal");
            System.out.println("2. View Goals");
            System.out.println("3. Delete Goal");
            System.out.println("4. Mark Goal as Done");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addGoal(sc);
                case 2 -> viewGoals();
                case 3 -> deleteGoal(sc);
                case 4 -> markGoalAsDone(sc);
                case 5 -> {
                    saveGoals();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addGoal(Scanner sc) {
        System.out.print("Enter goal description: ");
        String desc = sc.nextLine();

        System.out.print("Enter deadline (dd-MM-yyyy): ");
        String dateStr = sc.nextLine();
        try {
            Date deadline = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
            goals.add(new Goal(desc, deadline));
            System.out.println("Goal added!");
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy.");
        }
    }

    private void viewGoals() {
        if (goals.isEmpty()) {
            System.out.println("No goals to show.");
            return;
        }

        SimpleDateFormat viewFormat = new SimpleDateFormat("dd MMM, yyyy");
        Date now = new Date();

        System.out.println("\nYour Goals:");
        System.out.printf("%-4s %-35s %-20s %-15s %-10s\n", "No.", "Description", "Deadline", "Countdown", "Status");
        System.out.println("-------------------------------------------------------------------------------");

        for (int i = 0; i < goals.size(); i++) {
            Goal g = goals.get(i);
            String deadlineStr = viewFormat.format(g.deadline);
            String status = g.isDone ? "Done" : "Pending";
            String countdown = getCountdown(now, g.deadline);
            System.out.printf("%-4d %-35s %-20s %-15s %-10s\n", (i + 1), g.description, deadlineStr, countdown, status);
        }
    }

    private String getCountdown(Date now, Date deadline) {
        long diffMillis = deadline.getTime() - now.getTime();
        long diffDays = diffMillis / (1000 * 60 * 60 * 24);

        if (diffDays > 0) return diffDays + " days left";
        if (diffDays == 0) return "Today!";
        return Math.abs(diffDays) + " days overdue";
    }

    private void deleteGoal(Scanner sc) {
        viewGoals();
        System.out.print("Enter goal number to delete: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < goals.size()) {
            goals.remove(index);
            System.out.println("Goal deleted.");
        } else {
            System.out.println("Invalid goal number.");
        }
    }

    private void markGoalAsDone(Scanner sc) {
        viewGoals();
        System.out.print("Enter goal number to mark as done: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < goals.size()) {
            goals.get(index).isDone = true;
            System.out.println("Goal marked as done!");
        } else {
            System.out.println("Invalid goal number.");
        }
    }

    private void checkForReminders(Scanner sc) {
        Date today = truncateTime(new Date());
        for (Goal goal : goals) {
            if (!goal.isDone && truncateTime(goal.deadline).equals(today)) {
                System.out.println("\n⏰ REMINDER: Today is the deadline for: " + goal.description);
                java.awt.Toolkit.getDefaultToolkit().beep();

                System.out.print("Have you completed this goal? (yes/no): ");
                String response = sc.nextLine().toLowerCase();

                if (response.contains("yes")) {
                    goal.isDone = true;
                    System.out.println("Marked as done!");
                } else {
                    System.out.print("Enter new deadline (dd-MM-yyyy): ");
                    String newDate = sc.nextLine();
                    try {
                        goal.deadline = new SimpleDateFormat("dd-MM-yyyy").parse(newDate);
                        System.out.println("Deadline updated.");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Deadline unchanged.");
                    }
                }
            }
        }
    }

    private Date truncateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private void saveGoals() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(goals);
        } catch (IOException e) {
            System.out.println("Error saving goals.");
        }
    }

    private void loadGoals() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Goal> loaded = (List<Goal>) ois.readObject();
            goals.clear();
            goals.addAll(loaded);
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }
}
