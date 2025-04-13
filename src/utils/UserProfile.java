package models;

public class UserProfile {
    private String name;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private String activityLevel;

    public UserProfile(String name, int age, String gender, double weight, double height, String activityLevel) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public String getActivityLevel() { return activityLevel; }

    @Override
    public String toString() {
        return "👤 Profile:\n" +
                "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Weight: " + weight + " kg\n" +
                "Height: " + height + " cm\n" +
                "Activity Level: " + activityLevel;
    }
}
