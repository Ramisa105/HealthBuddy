package features;

import interfaces.Summarizable;

import java.util.List;

public class WeeklyReportGenerator {
    private final List<Summarizable> trackers;

    public WeeklyReportGenerator(List<Summarizable> trackers) {
        this.trackers = trackers;
    }

    public void generateFullReport() {
        System.out.println("\n📈 Weekly Health Report");

        for (Summarizable tracker : trackers) {
            System.out.println("----------------------------------");
            System.out.println(tracker.getSummary());
        }

        System.out.println("----------------------------------");
        System.out.println("✅ End of Weekly Report");
    }
}
