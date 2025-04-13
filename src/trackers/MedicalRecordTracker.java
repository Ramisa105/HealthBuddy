package trackers;

import interfaces.HealthTracker;
import models.MedicalRecord;
import utils.HealthAnalyzer;

import java.util.Scanner;

public class MedicalRecordTracker implements HealthTracker {
    private MedicalRecord latestRecord;

    @Override
    public void log() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🩺 Enter your vitals:");

        System.out.print("Pulse Rate: ");
        int pulse = Integer.parseInt(scanner.nextLine());

        System.out.print("Heart Rate: ");
        int heartRate = Integer.parseInt(scanner.nextLine());

        System.out.print("Blood Pressure: ");
        int bp = Integer.parseInt(scanner.nextLine());

        System.out.print("Glucose Level: ");
        int glucose = Integer.parseInt(scanner.nextLine());

        latestRecord = new MedicalRecord(pulse, heartRate, bp, glucose);

        System.out.println("\n📊 Analysis:");
        System.out.println(HealthAnalyzer.generateWarning(latestRecord));
    }


}
