/*
	Author: Elijah Moya
	Date: 4-29-2021
	Calculates Calories given necessary data
 */
package application;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public class Biometrics 
{	
	static final int FEMALE_CONST = 161;
	static final double WEIGHT_MULTI = 10;
	static final double HEIGHT_MULTI = 6.25;
	static final double AGE_MULTI = 5;
	static final double MALE_CONST = 5;

	static final double SEDENTARY_MULTI = 1.2;
	static final double LIGHT_MULTI = 1.375;
	static final double MODERATE_MULTI = 1.55;
	static final double VERY_MULTI = 1.725;
	static final double EXTRA_MULTI = 1.9;
	
	static final String personFile = "person.properties";

	/* Saves calories into user.properties file */
	public static void saveUserData(int calories)
	{
		HashMap<String, String> personData = new HashMap<String, String>();
		personData.put("user", "" + calories);
		Properties properties = new Properties();
		try {
			properties.putAll(personData);
			File file = new File("user.properties");
			FileOutputStream writer;

			writer = new FileOutputStream(file, true);
			properties.store(writer, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* using hashmap entered finds calories needed daily */
	public static int calculateBMR(HashMap<String, ArrayList<String>> h, String username)
	{
		int calories;

		if(!(h.containsKey(username)))
			return 0;

		calories = metricBMR(h.get(username).get(0), h.get(username).get(1), h.get(username).get(2), h.get(username).get(3),h.get(username).get(4));
		
		switch(h.get(username).get(5).trim())
		{
			case "sedentary":
				calories *= SEDENTARY_MULTI;
				break;
			case "light":
				calories *= LIGHT_MULTI;
				break;
			case "moderate":
				calories *= MODERATE_MULTI;
				break;
			case "very":
				calories *= VERY_MULTI;
				break;
			case "extra":
				calories *= EXTRA_MULTI;
				break;
		}
		
		
		return calories;
	}

	/* converts values from imperial measurements to metric then finds calories */
	public static int metricBMR(String w, String h, String a, String s, String measure)
	{
		int calories;
		
		double weight = Double.parseDouble(w.trim());
		double height = Double.parseDouble(h.trim());
		int age = Integer.parseInt(a.trim());
		char sex = s.trim().charAt(0);
		boolean isImperial = Boolean.parseBoolean(measure.trim());
		
		if(isImperial)//imperial measurements to metric
		{
			weight = weight/2.205;
			height = height * 2.54;
		}
		
		if(sex == 'm')
			calories = (int)((WEIGHT_MULTI * weight) + (HEIGHT_MULTI * height) - (AGE_MULTI * age) + MALE_CONST);
		else
			calories = (int)((WEIGHT_MULTI * weight) + (HEIGHT_MULTI * height) - (AGE_MULTI * age) - FEMALE_CONST);
		
		return calories;
	}
	
}
