package models;

public class UserProfile {
    private String name;
    private int age;
    private String gender;
    private double heightCm;
    private double weightKg;
    private String activityLevel;

    public UserProfile(String name, int age, String gender, double heightCm, double weightKg, String activityLevel) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.activityLevel = activityLevel;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public double getHeightCm() { return heightCm; }
    public double getWeightKg() { return weightKg; }
    public String getActivityLevel() { return activityLevel; }
}
