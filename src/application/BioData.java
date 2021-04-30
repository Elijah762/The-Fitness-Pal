package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/*
	Author: Elijah Moya
	Date: 4-19-2021
	Utillity class to string arraylist to single String and manipulate properties file
 */
public class BioData {
	
	public static void addData(HashMap<String, ArrayList<String>> h, ArrayList<String> data, String key, String fileName)	{
		h.put(key, data);
		HashMap<String, String> stringifiedData= stringifyMap(h);
		saveProp(stringifiedData, fileName);
	}
	
	
	//converts hashmap from type <String, Arraylist> to type <String, String>
	public static HashMap<String, String> stringifyMap(HashMap<String, ArrayList<String>> h) {
		HashMap<String, String> stringedMap = new HashMap<String, String>();
		
		/* Iterates over each value in the hash map and converts each array list into a String using the toString method */
		for(Map.Entry<String, ArrayList<String>> mapElement : h.entrySet())	{
			ArrayList<String> keyData = mapElement.getValue();
			stringedMap.put(mapElement.getKey(), keyData.toString());
		}
		
		return stringedMap;
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
	
	
	/* Converts properties file to a Hash Map of type String, String */
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
	
}
