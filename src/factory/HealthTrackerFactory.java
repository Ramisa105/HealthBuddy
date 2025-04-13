package factory;

import interfaces.HealthTracker;
import trackers.MedicalRecordTracker;

public class HealthTrackerFactory {

    public static HealthTracker getTracker(String type) {
        return switch (type.toLowerCase()) {
            case "medical" -> new MedicalRecordTracker();
            default -> throw new IllegalArgumentException("Unknown tracker type: " + type);
        };
    }
}
