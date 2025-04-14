package factories;



import interfaces.HealthModule;
import modules.*;

public class HealthModuleFactory {
    public static HealthModule getModule(int option) {
        switch (option) {
            case 1: return new BMIModule();
            case 2: return new CalorieModule();
            case 3: return new HydrationModule();
            case 4: return new MedicalVitalsModule();
            case 5: return new GoalModule();
            default: return null;
        }
    }
}
