package modules;

import interfaces.HealthModule;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class GoalModule implements HealthModule {

    private static final String FILE_NAME = "src/goals.txt";
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
        checkForDueGoals(sc);

        while (true) {
            System.out.println("\n");
            System.out.println("                                                     ╔═════════════════════════════════╗");
            System.out.println("                                                     ║        Goal Tracker Menu        ║");
            System.out.println("                                                     ╠═════════════════════════════════╣");
            System.out.println("                                                     ║ [1] Add Goal                    ║");
            System.out.println("                                                     ║ [2] View Goals                  ║");
            System.out.println("                                                     ║ [3] Delete Goal                 ║");
            System.out.println("                                                     ║ [4] Mark Goal as Done           ║");
            System.out.println("                                                     ║ [5] Back to Main Menu           ║");
            System.out.println("                                                     ╚═════════════════════════════════╝");
            System.out.print("\nChoose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if(choice ==1) addGoal(sc);
            else if(choice ==2) viewGoals();
            else if(choice ==3) deleteGoal(sc);
            else if(choice ==4) markGoalAsDone(sc);
            else if(choice ==5) {
                saveGoals();
                return;
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

        SimpleDateFormat format = new SimpleDateFormat("dd MMM, yyyy");
        Date now = new Date();

        System.out.println("\n                                          📋 Your Goal List");
        System.out.println("╔════╦══════════════════════════════════════════════╦════════════════════╦══════════════════╦════════════╗");
        System.out.println("║ No ║                Description                   ║      Deadline      ║     Countdown    ║   Status   ║");
        System.out.println("╠════╬══════════════════════════════════════════════╬════════════════════╬══════════════════╬════════════╣");

        for (int i = 0; i < goals.size(); i++) {
            Goal g = goals.get(i);
            String deadlineStr = format.format(g.deadline);
            String status = g.isDone ? "Done" : "Pending";
            String countdown = getCountdown(now, g.deadline);
            System.out.printf("║ %-2d ║ %-44s ║ %-18s ║ %-16s ║ %-10s ║\n",
                    (i + 1), g.description, deadlineStr, countdown, status);
        }

        System.out.println("╚════╩══════════════════════════════════════════════╩════════════════════╩══════════════════╩════════════╝");
    }

    private String getCountdown(Date now, Date deadline) {
        long diff = deadline.getTime() - now.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        if (days > 0) return days + " days left";
        if (days == 0) return "Today!";
        return Math.abs(days) + " days overdue";
    }

    private void deleteGoal(Scanner sc) {
        viewGoals();
        System.out.print("Enter goal number to delete: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < goals.size()) {
            goals.remove(index);
            System.out.println("Goal deleted.");
        } else {
            System.out.println("Invalid number.");
        }
    }

    private void markGoalAsDone(Scanner sc) {
        viewGoals();
        System.out.print("Enter goal number to mark as done: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < goals.size()) {
            goals.get(index).isDone = true;
            System.out.println("Goal marked as done.");
        } else {
            System.out.println("Invalid number.");
        }
    }

    private void checkForDueGoals(Scanner sc) {
        Date today = truncateTime(new Date());
        for (Goal goal : goals) {
            if (!goal.isDone && truncateTime(goal.deadline).equals(today)) {
                System.out.println("\n⏰ Deadline reached for: " + goal.description);
                playAlarm();

                System.out.print("Did you complete this goal? (yes/no): ");
                String res = sc.nextLine().toLowerCase();
                if (res.contains("yes")) {
                    goal.isDone = true;
                    System.out.println("Goal marked done!");
                } else {
                    System.out.print("Enter new deadline (dd-MM-yyyy): ");
                    String newDate = sc.nextLine();
                    try {
                        goal.deadline = new SimpleDateFormat("dd-MM-yyyy").parse(newDate);
                        System.out.println("Deadline updated.");
                    } catch (Exception e) {
                        System.out.println("Invalid date. Deadline not changed.");
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

    private void playAlarm() {
        try {
            File sound = new File("src/alarm.wav");
            javax.sound.sampled.AudioInputStream audioIn = javax.sound.sampled.AudioSystem.getAudioInputStream(sound);
            javax.sound.sampled.Clip clip = javax.sound.sampled.AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.out.println("🔈 Beep! (Alarm failed)");
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }
}
