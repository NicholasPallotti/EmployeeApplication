package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JSONFile {
	
	File file = new File("employees.json");
	
	//create objectMapper as an class variable since its used in both the read and write file methods
	ObjectMapper objectMapper = new ObjectMapper();
	
	//constructor creates the file
	JSONFile(){

		try {
			file.createNewFile();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//reads the file and returns a list of employees
	@SuppressWarnings("unchecked")
	public ObservableList<Employee> readFile() throws IOException, ClassNotFoundException{
		
		//the list that will be returned
		ObservableList<Employee> employeesFromFile = FXCollections.observableArrayList();
		
		//the list that the json value will be stored into
		ArrayList<Employee> employeesFromFileAsArray = new ArrayList<Employee>();
		
		if(file.exists()) {
			
			String json = null; 
			
			//create a string from the json file
			try {					
				json = new String(Files.readAllBytes(Paths.get("employees.json"))); 			    
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				
				e.printStackTrace();
			}
			
			ArrayList<Employee> tempEmployees = null;
			
			objectMapper.enableDefaultTyping();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			try {
				
				//read the objects form the json string into an arrayList tempEmployees
				tempEmployees = objectMapper.readValue(json, ArrayList.class);
				
				//add the employees form tempEmployees to employeesFromFileAsArray 
				for(int i = 0; i < tempEmployees.size(); i++) {
					//employeesFromFileAsArray.add(tempEmployees.get(i));
				}
			} 
			catch (JsonParseException e) {			
				e.printStackTrace();
			} 
			catch (JsonMappingException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//add the employees from employeesFromFileAsarray to employeesFromFile
		for(int i = 0; i <employeesFromFileAsArray.size(); i++) {
			employeesFromFile.add(employeesFromFileAsArray.get(i));			
		}
		
		return employeesFromFile;
	}
	
	//saves the current list as an json file
	public void saveFile(ObservableList<Employee> employeeList) throws IOException{
		
		ArrayList<Employee> employeeListAsArray = new ArrayList<Employee>();
		
		//copy the employees form an ObservableList to a ArrayList
		for(int i = 0; i <employeeList.size(); i++) {
			
			employeeListAsArray.add(employeeList.get(i));
			
		}
			
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.enableDefaultTyping();
		
		try {
			
			//create a string from employeeListAsArray
			String json = objectMapper.writeValueAsString(employeeListAsArray);

			try {
				
				//create a FileWrioter that will write to the json file
				FileWriter writer = new FileWriter(file);
				
				//write the string json to the file then close writer
				writer.write(json);
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
