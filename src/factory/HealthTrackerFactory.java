package factory;

import interfaces.HealthTracker;
import trackers.*;

public class HealthTrackerFactory {

    public static HealthTracker getTracker(String type) {
        return switch (type.toLowerCase()) {
            case "diet" -> new DietTracker();
            case "sleep" -> new SleepTracker();
            case "exercise" -> new ExerciseTracker();
            case "mental" -> new MentalHealthTracker();
            case "medical" -> new MedicalRecordTracker();
            default -> throw new IllegalArgumentException("Unknown tracker type: " + type);
        };
    }
}
