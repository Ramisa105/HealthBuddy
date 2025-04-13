package utils;
import models.MedicalRecord;

public class HealthAnalyzer {

    public static boolean needsDoctor(MedicalRecord record) {
        return record.getBloodPressure() > 140
                || record.getHeartRate() > 100
                || record.getGlucoseLevel() > 180
                || record.getPulseRate() > 100;
    }

    public static String generateWarning(MedicalRecord record) {
        StringBuilder warning = new StringBuilder();

        boolean needsDoctor = false;

        if (record.getPulseRate() < 60 || record.getPulseRate() > 100) {
            warning.append("⚠️ Abnormal Pulse Rate: ").append(record.getPulseRate()).append("\n");
            needsDoctor = true;
        }

        if (record.getHeartRate() < 60 || record.getHeartRate() > 100) {
            warning.append("⚠️ Abnormal Heart Rate: ").append(record.getHeartRate()).append("\n");
            needsDoctor = true;
        }

        if (record.getBloodPressure() < 90 || record.getBloodPressure() > 140) {
            warning.append("⚠️ Blood Pressure out of range: ").append(record.getBloodPressure()).append("\n");
            needsDoctor = true;
        }

        if (record.getGlucoseLevel() < 70 || record.getGlucoseLevel() > 140) {
            warning.append("⚠️ Glucose level abnormal: ").append(record.getGlucoseLevel()).append("\n");
            needsDoctor = true;
        }

        if (needsDoctor) {
            warning.append("🛑 Suggestion: Please consult a doctor.\n");
        } else {
            warning.append("✅ All vitals are within healthy range. Keep it up!\n");
        }

        return warning.toString();
    }

    public static boolean isAbnormal(MedicalRecord record) {
        return record.getPulseRate() < 60 || record.getPulseRate() > 100 ||
                record.getHeartRate() < 60 || record.getHeartRate() > 100 ||
                record.getBloodPressure() < 90 || record.getBloodPressure() > 140 ||
                record.getGlucoseLevel() < 70 || record.getGlucoseLevel() > 140;
    }


}
