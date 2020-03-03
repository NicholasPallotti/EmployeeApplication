package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class XMLFile {

	//creates a new file, or if employees has been created links to that file
	File file = new File("employees.xml");
	
	XMLFile(){
	}
	
	
	@SuppressWarnings("unchecked")
	public ObservableList<Employee> readFile(){
		
		ObservableList<Employee> employeesFromFile = FXCollections.observableArrayList();
		
		if(file.exists()) {
			
			XMLDecoder decoder = null;
			try {
				//create a FileInputStream with the file name of restaurants.xml
				FileInputStream fileInputStream = new FileInputStream(file);
				
				//create a BufferedInputStream passing in the FileInputStream from above
				BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
				
				//get a new instance of XMLDecoder, passing the in the BufferedInputStream from above
				decoder = new XMLDecoder(bufferedInputStream);
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
			
			ArrayList<Employee> employeesFromFileAsArray = (ArrayList<Employee>)decoder.readObject();
			
			for(int i = 0; i <employeesFromFileAsArray.size(); i++) {
				
				employeesFromFile.add(employeesFromFileAsArray.get(i));
				
			}
			
		}	
		
		return  employeesFromFile;
	}
	
	public void saveFile(ObservableList<Employee> employeeList) {
		
		ArrayList<Employee> employeeListAsArray = new ArrayList<Employee>();
		
		for(int i = 0; i <employeeList.size(); i++) {
			
			employeeListAsArray.add(employeeList.get(i));
			
		}
		
		XMLEncoder xmlEncoder = null;
		
		try{
		
			FileOutputStream fileOutputStream = new FileOutputStream(file);

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

			xmlEncoder = new XMLEncoder(bufferedOutputStream);
		}
		catch(FileNotFoundException fileNotFound){
			fileNotFound.printStackTrace();
		}

		xmlEncoder.writeObject(employeeListAsArray);

		xmlEncoder.close();	
		
	}
}
