package models;

public class MedicalRecord {
    private int pulseRate;
    private int heartRate;
    private int bloodPressure;
    private int glucoseLevel;

    public MedicalRecord(int pulseRate, int heartRate, int bloodPressure, int glucoseLevel) {
        this.pulseRate = pulseRate;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.glucoseLevel = glucoseLevel;
    }

    public int getPulseRate() {
        return pulseRate;
    }
    public int getHeartRate() {
        return heartRate;
    }
    public int getBloodPressure() {
        return bloodPressure;
    }
    public int getGlucoseLevel() {
        return glucoseLevel;
    }
}
