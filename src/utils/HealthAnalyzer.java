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
        if (needsDoctor(record)) {
            return "⚠️ Warning: Your vitals are above normal range. Please consult a doctor.";
        }
        return "✅ Your vitals seem normal. Keep tracking regularly.";
    }

    public static boolean isAbnormal(MedicalRecord record) {
        return record.getPulseRate() < 60 || record.getPulseRate() > 100 ||
                record.getHeartRate() < 60 || record.getHeartRate() > 100 ||
                record.getBloodPressure() < 90 || record.getBloodPressure() > 140 ||
                record.getGlucoseLevel() < 70 || record.getGlucoseLevel() > 140;
    }

}
