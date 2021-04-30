#Fitness-Pal App
Team Zaela
***********
    Zach Hernandez
    Adittya Das 
    Elijah Moya 
    Loke Innocent 
    Allan Qi 
    
Description of app
*********************
    Do you find yourself feeling like you over eat day to day, under eat day to day, or even find yourself as a health 
    conscious person? If so than this is the app for you! Fitness-Pal is an all in one app that calculates your necessary 
    daily caloric intake based on your Biometrics and allows you to input your meals for the day into an easy to read 
    daily calendar that tracks calories eaten compared to your recommended caloric intake on a weekly basis.  
    
How to run
**************
1. Assure your machine meets all the Version Requirements listed at the bottom of this read me file. 
2. Download the zip file of this repository and open it in your desired IDE.
3. If running on Windows be sure that line 103 and line 109 may need to be changed to have ".\\"
   added to the front of user.properties, however this should only be done if "Recommended Weekly Intake" produces an error.
4. Assure that the properties files named "user.properties", "foodData.properties", and "dates.properties" exist before running
   the app.
5. Enjoy Fitness-Pal!
    
Known Bug
*************
1. There exists possible instances where food names with multiple names may not properly appear on the calender as 
the properties file that stores food data may add extranious values where the space should be.
2. Fitness-Pal was compiled in javaFX-16 and will prompt a yes/no upon launch if the user does not have javaFX-16 installed.
   If the user selects yes app should run as usual.
3. When attempting to run the app module-info.java may prevent launch. This may be a windows vs mac environment error or an error in
   the Eclipse IDE
4. Mac vs. Windows environment mismatches in the code may restirct functions of app

Login info
***********
    The first time you use Fitness-Pal begin with clicking the "Calculate" button found at the home screen and 
    fill out all necessary fields and click "Calculate". Now that you know your necessary daily caloric intake 
    you can begin to input your meals for the day and view them in the calendar section of Fitness-Pal.
    
Version Requirements
**********************
    Compatible version of Java, (Java8, jre8.0_221) or newer, and JavaFx-16 installed into your
    IDE of choice. For editing of FXML files SceneBuilder 3 or above will be required.
    
