package modules;

import interfaces.HealthModule;

import java.util.Scanner;

public class MedicalVitalsModule implements HealthModule {

    @Override
    public void execute(Scanner sc) {

        System.out.print("Enter pulse rate (bpm): ");
        int pulse = sc.nextInt();

        System.out.print("Enter blood pressure - systolic (mm Hg): ");
        int systolic = sc.nextInt();

        System.out.print("Enter blood pressure - diastolic (mm Hg): ");
        int diastolic = sc.nextInt();

        System.out.print("Enter oxygen level (SpO2 %): ");
        int oxygen = sc.nextInt();

        System.out.print("Enter body temperature (°F): ");
        double temperature = sc.nextDouble();


        String pulseEval = checkPulse(pulse);
        String bpEval = checkBloodPressure(systolic, diastolic);
        String oxygenEval = checkOxygen(oxygen);
        String tempEval = checkTemperature(temperature);



        System.out.println("\n                                                  📋 Vital Signs Report");
        System.out.println("╔════╦══════════════════════════╦══════════════════════════╦═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ No ║           Vital          ║           Value          ║                                 Status                                ║");
        System.out.println("╠════╬══════════════════════════╬══════════════════════════╬═══════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-2d ║ %-24s ║ %-24s ║ %-69s ║\n", 1, "Pulse Rate", pulse + " bpm", pulseEval);
        System.out.printf("║ %-2d ║ %-24s ║ %-24s ║ %-69s ║\n", 2, "Blood Pressure", systolic + "/" + diastolic + " mm Hg", bpEval);
        System.out.printf("║ %-2d ║ %-24s ║ %-24s ║ %-69s ║\n", 3, "Oxygen Level", oxygen + "%", oxygenEval);
        System.out.printf("║ %-2d ║ %-24s ║ %-24s ║ %-69s ║\n", 4, "Body Temp", temperature + "°F", tempEval);
        System.out.println("╚════╩══════════════════════════╩══════════════════════════╩═══════════════════════════════════════════════════════════════════════╝");
        System.out.println("\n");
    }

    private String checkPulse(int pulse) {
        if (pulse >= 60 && pulse <= 100) return "Normal";
        if (pulse < 60) return "Low (Bradycardia) - May be normal for athletes, otherwise consult a doctor.";
        return "High (Tachycardia) - Possible stress, fever, or heart issue.";
    }

    private String checkBloodPressure(int sys, int dia) {
        if (sys < 90 || dia < 60) return "Low - May cause dizziness or fainting. Monitor closely.";
        if (sys <= 120 && dia <= 80) return "Normal";
        if (sys <= 139 || dia <= 89) return "Elevated - Lifestyle changes recommended.";
        return "High - Risk of hypertension. Consult a doctor.";
    }

    private String checkOxygen(int oxygen) {
        if (oxygen >= 95) return "Normal";
        if (oxygen >= 90) return "Slightly Low - Take deep breaths, consider rest.";
        return "Critical - Seek emergency medical care immediately.";
    }

    private String checkTemperature(double temp) {
        if (temp >= 97.0 && temp <= 99.0) return "Normal";
        if (temp > 99.0 && temp <= 100.4) return "Mild Fever - Rest and stay hydrated.";
        if (temp > 100.4) return "High Fever - Possible infection. Consult a doctor.";
        return "Low - Possible hypothermia. Warm up and seek help if needed.";
    }

}
