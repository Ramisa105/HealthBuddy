package healthbuddy;
import factories.HealthModuleFactory;
import interfaces.HealthModule;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String GREEN = "\u001B[34m";
        String RESET = "\u001B[0m";
        System.out.print("\n");
        String[] title2 = {
                "                                                       ──────▄▌▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\u200B▀▀▀▀▀▀",
                "                                                       ───▄▄██▌█     BEEP BEEP ",
                "                                                       ▄▄▄▌▐██▌█ Your Wellness Pal ",
                "                                                       ███████▌█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\u200B▄▄▄▄▄▄▌ ",
                "                                                       ▀(@)▀▀▀▀▀▀▀(@)(@)▀▀▀▀▀▀▀▀▀▀▀▀▀\u200B▀▀▀▀(@)▀",

        };
        String[] title3 = {

                "                            ██╗  ██╗ ███████╗  █████╗  ██╗   ████████╗ ██╗  ██╗      ██████╗  ██╗   ██╗  ██████╗  ██████╗  ██╗   ██╗",
                "                            ██║  ██║ ██╔════╝ ██╔══██╗ ██║   ╚══██╔══╝ ██║  ██║      ██╔══██╗ ██║   ██║  ██╔══██╗ ██╔══██╗ ╚██╗ ██╔╝",
                "                            ███████║ █████╗   ███████║ ██║      ██║    ███████║      ██████╔╝ ██║   ██║  ██║  ██║ ██║  ██║  ╚████╔╝ ",
                "                            ██╔══██║ ██╔══╝   ██╔══██║ ██║      ██║    ██╔══██║      ██╔══██╗ ██║   ██║  ██║  ██║ ██║  ██║   ╚██╔╝  ",
                "                            ██║  ██║ ███████╗ ██║  ██║ ███████╗ ██║    ██║  ██║      ██████╔╝ ╚██████╔╝  ██████╔╝ ██████╔╝    ██║   ",
                "                            ╚═╝  ╚═╝ ╚══════╝ ╚═╝  ╚═╝ ╚══════╝ ╚═╝    ╚═╝  ╚═╝      ╚═════╝   ╚═════╝   ╚═════╝  ╚═════╝     ╚═╝     ",
                ""
        };

        for (String line : title2) {
            System.out.println(line);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print(RESET);

        for (String line : title3) {
            System.out.println(GREEN+line);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(RESET);


        Scanner sc = new Scanner(System.in);
        int choice;

        while(true) {
            System.out.println("                                    ╔════════════════════════════════════════════════════════════════════╗");
            System.out.println("                                    ║                               Main Menu                            ║");
            System.out.println("                                    ╠════════════════════════════════════════════════════════════════════╣");
            System.out.println("                                    ║                                                                    ║");
            System.out.println("                                    ║    [1] BMI Calculator                                              ║");
            System.out.println("                                    ║    [2] Calorie Estimator                                           ║");
            System.out.println("                                    ║    [3] Hydration Tracker                                           ║");
            System.out.println("                                    ║    [4] Medical Vitals Analyzer                                     ║");
            System.out.println("                                    ║    [5] Goal Tracker                                                ║");
            System.out.println("                                    ║    [6] Exit                                                        ║");
            System.out.println("                                    ║                                                                    ║");
            System.out.println("                                    ╚════════════════════════════════════════════════════════════════════╝");

            System.out.print("\nEnter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();
            if (choice >= 1 && choice <= 5) {
                HealthModule module = HealthModuleFactory.getModule(choice);
                module.execute(sc);
            } if (choice == 6) {
                System.out.println("\uD83D\uDC4B Thank you for using HealthBuddy! Stay healthy!");
                return;
            }

        }

    }

}

