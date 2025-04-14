🩺 HealthBuddy

HealthBuddy is a modular, console-based Java application designed to help users manage and improve their health by calculating BMI, daily calorie and hydration needs, checking vital signs, and setting health-related goals with smart reminders

🚀 Features
✅ BMI Calculator
        -> Calculates Body Mass Index based on height and weight.
        -> Categorizes into:
              Severe Thinness, Moderate Thinness, Mild Thinness
              Normal, Overweight
              Obese Class I, II, III
        -> Displays your BMI category with an arrow and color-coded chart.

✅ Calorie Calculator
        -> Input: Age, Gender, Height, Weight, Activity Level
        -> Output:
              BMR (Basal Metabolic Rate)
              TDEE (Total Daily Energy Expenditure)
              Calories needed to maintain, lose, or gain weight
        -> Visual chart showing calorie targets

✅ Hydration Calculator 💧
        -> Input: Age, Height, Gender (and pregnancy/lactation status for females), Activity Level
        -> Output: Recommended daily water intake in liters
        -> 🔔 Real-time water drinking reminders running in the background

✅ Medical Vitals Checker 🧪
        -> Input: Pulse Rate, Blood Pressure, Oxygen Level, Body Temperature
        -> Output: Health status analysis and smart recommendations

✅ Goal Setter with Countdown ⏳
        -> Set, View, Delete, and Track personalized health goals
        -> Deadline-based countdown next to each goal
        -> Background reminder system notifies when deadlines are due
        -> If a goal isn't achieved, you're prompted to update the deadline
        -> Nicely formatted goal table view with date and remaining days


📦 Setup Instructions
        -> Clone the Repository
              git clone https://github.com/Ramisa105/HealthBuddy.git
        -> Navigate to the Project Directory
              cd health-tracker-console
        -> Compile the Java Files
              javac Main.java modules/.java interfaces/.java utils/.java factories/.java
        -> Run the Application
              java healthbuddy.Main


🛠 Requirements
        -> Java 8 or higher
        -> Terminal / Command Prompt to run the app


🧠 Coming Soon
        -> Weekly health reports with smart insights
        -> Enhanced notifications via desktop popups or mobile integration
        -> UI-based desktop version for broader access


💙 Built With Passion
        -> Crafted by a health-conscious coder to help people take control of their well-being
