🩺 **HealthBuddy**

HealthBuddy is a modular, console-based Java application designed to help users manage and improve their health by calculating BMI, daily calorie and hydration needs, checking vital signs, and setting health-related goals with smart reminders.

---

## 🚀 Features

### ✅ BMI Calculator  
- Calculates Body Mass Index based on height and weight  
- Categorizes into:  
  - Severe Thinness  
  - Moderate Thinness  
  - Mild Thinness  
  - Normal  
  - Overweight  
  - Obese Class I, II, III  
- Displays your BMI category with an arrow and color-coded chart

### ✅ Calorie Calculator  
- **Input:** Age, Gender, Height, Weight, Activity Level  
- **Output:**  
  - BMR (Basal Metabolic Rate)  
  - TDEE (Total Daily Energy Expenditure)  
  - Calories needed to maintain, lose, or gain weight  
- Visual chart showing calorie targets

### ✅ Hydration Calculator 💧  
- **Input:** Age, Height, Gender (plus pregnancy/lactation status for females), Activity Level  
- **Output:** Recommended daily water intake in liters  
- 🔔 Real-time water drinking reminders running in the background

### ✅ Medical Vitals Checker 🧪  
- **Input:** Pulse Rate, Blood Pressure, Oxygen Level, Body Temperature  
- **Output:** Health status analysis and smart recommendations

### ✅ Goal Setter with Countdown ⏳  
- Set, View, Delete, and Track personalized health goals  
- Deadline-based countdown next to each goal  
- Background reminder system notifies when deadlines are due  
- If a goal isn't achieved, you're prompted to update the deadline  
- Nicely formatted goal table view with date and remaining days

---

## 📦 Setup Instructions

1. Clone the Repository  
   ```bash
   git clone https://github.com/your-username/health-tracker-console.git
2. Navigate to the Project Directory
   ```bash
   cd health-tracker-console
3. Compile the Java Files
   ```bash
   javac Main.java modules/*.java interfaces/*.java utils/*.java factories/*.java
4. Run the Application
   ```bash
   java healthbuddy.Main

---

## 🛠 Requirements  
- Java 8 or higher  
- Console or Terminal to run the application  
- Optional: Text editor to view logs and health reports  

---

## 🔮 Coming Soon  
- Weekly health reports with AI-based suggestions  
- Desktop notifications and voice-based hydration alerts  
- Integration with fitness APIs and smartwatches  
- GUI version for better accessibility  
- Export health logs to PDF/Excel for personal or clinical use  

---

## 💙 Built With Passion  
HealthBuddy was crafted with care by a developer who believes in making health tools accessible, informative, and effective. Every module is built with user-centric design and real-world practicality in mind—because your health deserves attention.
