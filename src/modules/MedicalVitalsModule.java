package modules;

import interfaces.HealthModule;
import utils.FileUtil;

import java.util.Scanner;

public class MedicalVitalsModule implements HealthModule {

    @Override
    public void execute(Scanner sc) {
        System.out.println("\n--- Medical Vitals Check ---");

        System.out.print("Enter pulse rate (bpm): ");
        int pulse = sc.nextInt();

        System.out.print("Enter heart rate (bpm): ");
        int heartRate = sc.nextInt();

        System.out.print("Enter blood pressure - systolic (mm Hg): ");
        int systolic = sc.nextInt();

        System.out.print("Enter blood pressure - diastolic (mm Hg): ");
        int diastolic = sc.nextInt();

        System.out.print("Enter oxygen level (SpO2 %): ");
        int oxygen = sc.nextInt();

        System.out.print("Enter body temperature (°F): ");
        double temperature = sc.nextDouble();

        StringBuilder report = new StringBuilder();
        report.append("\n--- Vitals Report ---\n");

        report.append("Pulse Rate: ").append(pulse).append(" bpm - ")
                .append(checkPulse(pulse)).append("\n");

        report.append("Heart Rate: ").append(heartRate).append(" bpm - ")
                .append(checkHeartRate(heartRate)).append("\n");

        report.append("Blood Pressure: ").append(systolic).append("/").append(diastolic).append(" mm Hg - ")
                .append(checkBloodPressure(systolic, diastolic)).append("\n");

        report.append("Oxygen Level: ").append(oxygen).append("% - ")
                .append(checkOxygen(oxygen)).append("\n");

        report.append("Body Temperature: ").append(temperature).append("°F - ")
                .append(checkTemperature(temperature)).append("\n");

        System.out.println(report);
        FileUtil.writeToFile("medical_vitals.txt", report.toString());
    }

    private String checkPulse(int pulse) {
        if (pulse >= 60 && pulse <= 100) return "✅ Normal";
        if (pulse < 60) return "⚠️ Low - Possible Bradycardia";
        return "🚨 High - Seek medical advice";
    }

    private String checkHeartRate(int rate) {
        if (rate >= 60 && rate <= 100) return "✅ Normal";
        if (rate < 60) return "⚠️ Low - Monitor if symptomatic";
        return "🚨 High - Seek medical attention";
    }

    private String checkBloodPressure(int sys, int dia) {
        if (sys < 90 || dia < 60) return "⚠️ Low - Possible Hypotension";
        if ((sys >= 90 && sys <= 120) && (dia >= 60 && dia <= 80)) return "✅ Normal";
        if ((sys > 120 && sys <= 140) || (dia > 80 && dia <= 90)) return "⚠️ Prehypertension - Monitor";
        return "🚨 High - Possible Hypertension";
    }

    private String checkOxygen(int oxygen) {
        if (oxygen >= 95 && oxygen <= 100) return "✅ Normal";
        if (oxygen >= 90 && oxygen < 95) return "⚠️ Low - Consider resting";
        return "🚨 Critical - Seek immediate help";
    }

    private String checkTemperature(double temp) {
        if (temp >= 97.0 && temp <= 99.0) return "✅ Normal";
        if (temp > 99.0 && temp <= 100.4) return "⚠️ Mild Fever - Monitor";
        if (temp > 100.4) return "🚨 High Fever - Seek medical help";
        return "⚠️ Low - Possible Hypothermia";
    }
}
