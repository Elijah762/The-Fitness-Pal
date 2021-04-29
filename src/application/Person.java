package application;

import java.util.ArrayList;

public class Person
{
	private static double weight;
	private static double height;
	private static int age;
	private static char sex;
	private static boolean isImperial;
	private static String activityLevel;
	
	public Person(double w, int h, int a, char s, String m, String activity)
	{
		weight = w;

		height = h;

		age = a;

		sex = s;
		
		boolean editedM = Boolean.parseBoolean(m);
		isImperial = editedM;
		
		activityLevel = activity;
	}

	public ArrayList<String> getData(String fixesError) {
		ArrayList <String> data = new ArrayList<>();
		data.add("" + weight);
		data.add("" + height);
		data.add("" + age);
		data.add("" + sex);
		data.add("" + isImperial);
		data.add("" + activityLevel);
		
		return data;
	}

}
