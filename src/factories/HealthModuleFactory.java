package factories;



import interfaces.HealthModule;
import modules.*;

public class HealthModuleFactory {
    public static HealthModule getModule(int option) {
        if(option ==1) return new BMIModule();
        if(option ==2) return new CalorieModule();
        if(option ==3) return new HydrationModule();
        if(option ==4) return new MedicalVitalsModule();
        if(option ==5) return new GoalModule();
        else return null;

    }
}
