package factory;

import interfaces.HealthTracker;
import trackers.*;

public class HealthTrackerFactory {

    public static HealthTracker getTracker(String type) {
        return switch (type.toLowerCase()) {
            case "medical" -> new MedicalRecordTracker();
            case "diet" -> new DietTracker();
            case "sleep" -> new SleepTracker();

            default -> throw new IllegalArgumentException("Unknown tracker type: " + type);
        };
    }
}
