package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class InsertFoodExample {

	private HashMap<String, ArrayList<Integer>> hm  = new HashMap<String, ArrayList<Integer>>();
	private ArrayList<Integer> foodValues;
	private String propertiesName = "foodData.properties";
	
	public InsertFoodExample(){
	}

	public HashMap<String, ArrayList<Integer>> getFoodData(){
		loadFoodData();
		return hm;
	}

	// load existing hashmap data. Currently assuming it's a properties file.
	public void loadFoodData(){
		try{
			File file = new File(propertiesName);
			FileInputStream reader = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(reader);
			
			// Store property values into hm
			Enumeration<String> e = (Enumeration<String>)properties.propertyNames(); // we know the key is a String, so we can safely cast it... right?
			
			while(e.hasMoreElements()){
				String tempFoodName = e.nextElement();
				// the value is stored as a String. We need to turn it into an ArrayList<Integer>.
				String origValue, modValue;
				String[] tokens;
				ArrayList<Integer> tempFoodValues = new ArrayList<>(4);
				
				// System.out.println("DEBUG: " + properties.get(tempFoodName));
				// This doesn't work, you cannot convert String to ArrayList
				// ArrayList<Integer> tempFoodValues = (ArrayList<Integer>)properties.get(tempFoodName);
				
				origValue = (String)properties.get(tempFoodName);
				
				// remove square brackets
				modValue = origValue.replace("[", "").replace("]", "");
				
				// remove spaces
				modValue = modValue.replaceAll(" ", "");
				
				// get each element delimited by a comma
				tokens = modValue.split(",", 0);
				
				tempFoodValues.add(Integer.parseInt(tokens[0]));
				tempFoodValues.add(Integer.parseInt(tokens[1]));
				tempFoodValues.add(Integer.parseInt(tokens[2]));
				tempFoodValues.add(Integer.parseInt(tokens[3]));
				
				hm.put(tempFoodName, tempFoodValues);
			}
		} catch(FileNotFoundException e){
			System.out.println("Could not find properties file (it probably does not exist)");
		} catch(IOException ioE){
			System.out.println("IOException with loading properties file");
			ioE.printStackTrace();
		}	
	}
	
	// save hashmap data
	public void saveFoodData(){
		// open writer, etc.
		Properties properties = new Properties();
		File file = new File(propertiesName);
		FileOutputStream writer = null;
		
		try {
			writer = new FileOutputStream(file, true);
		} catch (FileNotFoundException e1) {
			System.out.println("writer open error.");
			e1.printStackTrace();
		}
		
		// iterate through hashmap and add to properties file
		for(Map.Entry<String, ArrayList<Integer>> mapElement : hm.entrySet()){
			String key = (String)mapElement.getKey();
			properties.setProperty(key, "" + hm.get(key));
			try {
				properties.store(writer, null);
			} catch (IOException e) {
				System.out.println("properties write error.");
				e.printStackTrace();
			}
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("writer close error.");
			e.printStackTrace();
		}
	}
	
	// constructor with only food name and calories
	// would be called on the "Add Custom Foods" page when only name and calorie info (required fields) are entered
	public void addFood(String name, int cal){
		foodValues = new ArrayList<Integer>(4);
		foodValues.add(cal);
		foodValues.add(-1);
		foodValues.add(-1);
		foodValues.add(-1);
		hm.put(name, foodValues);
	}
	
	// constructor with all data
	// would be called on the "Add Custom Foods" page when all fields, including optional ones, are entered
	public void addFood(String name, int cal, int carb, int protein, int fat){
		foodValues = new ArrayList<Integer>(4);
		foodValues.add(cal);
		foodValues.add(carb);
		foodValues.add(protein);
		foodValues.add(fat);
		hm.put(name, foodValues);
	}
	
	// returns the hashmap
	public HashMap<String, ArrayList<Integer>> getFoodHashMap(){
		return hm;
	}
	
	public void printContents(){
		System.out.println(hm);
	}
	
}
