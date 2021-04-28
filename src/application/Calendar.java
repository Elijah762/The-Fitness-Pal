package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Calendar {
	
	private InsertFoodExample foodData = new InsertFoodExample();
	
	private HashMap<String, ArrayList<String>> dates = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<Integer>> foods = new HashMap<String, ArrayList<Integer>>();
	//private HashMap<String, ArrayList<String>> usersInfo = new HashMap<String, ArrayList<String>>();
	
	
	public void dateFood(String food) throws IOException {
		HashMap<String, ArrayList<String>> h=new HashMap<String, ArrayList<String>>();
		h = readProp("dates.properties");
		
		Date today = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("E/M/d/yyyy");
		
		if(h.get(ft.format(today)) != null) {
			h.get(ft.format(today)).add(food);
		}
		else {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(food);
			h.put(ft.format(today), temp);
		}
		
		//addData(h, "dates.properties");
		HashMap<String, String> stringifiedData= stringifyMap(h);
		saveProp(stringifiedData, "dates.properties");
		
		
	}
	
	public static void saveProp(HashMap<String, String> h, String fileName) {
		Properties properties = new Properties();
		try {
			properties.putAll(h);
			File file = new File(fileName);
			FileOutputStream writer;
			
			writer = new FileOutputStream(file, true);
			properties.store(writer, null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, ArrayList<String>> readProp(String fileName) {
		HashMap<String, ArrayList<String>> h = new HashMap<String, ArrayList<String>>();
		Properties properties = new Properties();
		
		try {	
			File file = new File(fileName);
			FileInputStream reader = new FileInputStream(file);
			properties.load(reader);
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		for(String key : properties.stringPropertyNames()) {
			ArrayList<String> parsedData = new ArrayList<String>();
			String unParsedData = properties.getProperty(key); 
			
			unParsedData = unParsedData.replace("[", "");
			unParsedData = unParsedData.replace("]", "");
			String [] semiParsedData = unParsedData.split(", ");
				
			for(int i = 0; i < semiParsedData.length; i++)
				parsedData.add(semiParsedData[i]);
			
			h.put(key, parsedData);
		}
		
		return h;
	}
	
	private static HashMap<String, String> stringifyMap(HashMap<String, ArrayList<String>> h) {
		HashMap<String, String> stringedMap = new HashMap<String, String>();
			
		/* Iterates over each value in the hash map and converts each array list into a String using the toString method */
		for(Map.Entry<String, ArrayList<String>> mapElement : h.entrySet())	{
			ArrayList<String> keyData = mapElement.getValue();
			stringedMap.put(mapElement.getKey(), keyData.toString());
		}
			
		return stringedMap;
	}
	

	public void getDays(String fullDate){
		dates = readProp("dates.properties");
		foods = foodData.getFoodData();
		//usersInfo = InsertFoodExample.readProp("persons.properties");
		
		String[] today = fullDate.split("."); 
		
		int num = 0;
		String startDate;
		
		// to get to Sunday switch
		switch(today[0]) {
		case("Sun"):
			num = 0;
			break;
		case("Mon"):
			num = -1;
			break;
		case("Tue"):
			num = -2;
			break;
		case("Wed"):
			num = -3;
			break;
		case("Thu"):
			num = -4;
			break;
		case("Fri"):
			num = -5;
			break;
		case("Sat"):
			num = -6;
			break;
		default:
		}
		
		startDate = shiftDate(fullDate, num);
		
		for(int i = 0; i < 7; ++i) {
			printCalendar(getReport(shiftDate(startDate, i)), startDate);
		}
		
	}
	
	public String getReport(String day) {
		
		int total = 0;
		String report = "";
		
		for (int i = 0; i < dates.get(day).size(); i++) {
			String temp = dates.get(day).get(i); // finds the ith food for day
			report += temp + "- " + foods.get(temp).get(0) + " Cal" + "\n";
            total += foods.get(temp).get(0); // add calories of the ith food for the day to the total
		}
		report += "Total: " + Integer.toString(total) + " Cal";
		return report;
		
	}
	
	public void printCalendar(String report, String day) {
		
		System.out.println(day + "\n" + report);
	}
	
	public int[] monthBound(int month, int year) {
		
		int monYr[] = {month, year};
		
		switch(month) {
		case(0):
			monYr[0] = 12;
			monYr[1] = year - 1;
		break;
		case(13):
			monYr[0] = 1;
			monYr[1] = year + 1; 
		break;
		default:
		}
		
		return monYr;
	}
	
	public String shiftDayName(String name, int shift) {
		int num = 0;
		
		for(int i = 0; i < 2; ++i) {
			switch(name) {
			case("Sun"):
				num = 0;
				name = "Sun";
				break;
			case("Mon"):
				num = 1;
				name = "Mon";
				break;
			case("Tue"):
				num = 2;
				name = "Tue";
				break;
			case("Wed"):
				num = 3;
				name = "Wed";
				break;
			case("Thu"):
				num = 4;
				name = "Thu";
				break;
			case("Fri"):
				num = 5;
				name = "Fri";
				break;
			case("Sat"):
				num = 6;
				name = "Sat";
				break;
			default:
			}
			if(i == 0) {
				num += shift;
			}
			if(num < 0) {
				num = num + 7;
			}
			else if(num > 6) {
				num = num - 7;
			}
		}
		return name;
	}
	
	
	
	public String shiftDate(String fullDate, int shift) {
		String[] today = fullDate.split("/"); //0 = day name, 1 = month, 2 = day number, 3 = year
		String sDate = "";
		String dayName = shiftDayName(today[0], shift);
		int dayNum = Integer.parseInt(today[2]) + shift;
		int month = Integer.parseInt(today[1]);
		int monthLoop = month - 1;
		int year = Integer.parseInt(today[3]);
		boolean isLeap = false;
		String sDay;
		String sMon;
		String sYear;
		int maxDays[] = {0, 0, 0}; //0 = max day for month before, 1 = max for current month, 2 = max for next month
		
		if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
			isLeap = true;
		}
		
		for(int i = 0; i < 3; ++i) {
			
			monthLoop = monthBound(monthLoop, year)[0];
			
			switch (monthLoop) {
			case(1):
			case(3):
			case(5):
			case(7):
			case(8):
			case(10):
			case(12):
				maxDays[i] = 31;
			break;
			case(4):
			case(6):
			case(9):
			case(11):
				maxDays[i] = 30;
			break;
			default:
				if(isLeap) {
					maxDays[i] = 29;
				}
				else {
					maxDays[i] = 28;
				}
			}
			monthLoop++;
		}
		
		if(dayNum < 1) {
			dayNum = maxDays[0] - dayNum;
			month -= 1;
		}
		else if(dayNum > maxDays[1]) {
			dayNum = dayNum - maxDays[2];
			month += 1;
		}
		
		sDay = Integer.toString(dayNum);
		sMon = Integer.toString(monthBound(month, year)[0]);
		sYear = Integer.toString(monthBound(month, year)[1]);
		sDate = dayName + "/" + sMon + "/" + sDay + "/" + sYear;
		
		return sDate;
	}
	
}
