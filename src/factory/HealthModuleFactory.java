package factories;



import interfaces.HealthModule;
import modules.BMIModule;
import modules.CalorieModule;
import modules.HydrationModule;
import modules.GoalModule;

public class HealthModuleFactory {
    public static HealthModule getModule(int option) {
        switch (option) {
            case 1: return new BMIModule();
            case 2: return new CalorieModule();
            case 3: return new HydrationModule();
            case 4: return new GoalModule();
            default: return null;
        }
    }
}
