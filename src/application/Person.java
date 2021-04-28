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
	
	public Person(String w, String h, String a, String s, String m, String activity)
	{
		double editedW = Double.parseDouble(w);
		weight = editedW;
		
		double editedH = Double.parseDouble(h);
		height = editedH;
		
		int editedA = Integer.parseInt(a);
		age = editedA;
		
		char editedS = s.charAt(0);
		sex = editedS;
		
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
