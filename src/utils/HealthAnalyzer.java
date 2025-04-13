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
}
