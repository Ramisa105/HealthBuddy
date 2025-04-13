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

    @Override
    public void generateReport() {
        if (latestRecord == null) {
            System.out.println("❌ No medical records found.");
            return;
        }

        System.out.println("\n🩺 Last Medical Record:");
        System.out.println("Pulse Rate: " + latestRecord.getPulseRate());
        System.out.println("Heart Rate: " + latestRecord.getHeartRate());
        System.out.println("Blood Pressure: " + latestRecord.getBloodPressure());
        System.out.println("Glucose Level: " + latestRecord.getGlucoseLevel());
        System.out.println(HealthAnalyzer.generateWarning(latestRecord));
    }

}
