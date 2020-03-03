package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.ObservableList;

public class EmployeeListModel {
	
	//create a file of each type the application uses
	CSVFile csv = new CSVFile();
	BinaryFile binary = new BinaryFile();
	XMLFile xml = new XMLFile();
	JSONFile json = new JSONFile();
	
	EmployeeListModel(){
		
	}
	
	//methods for reading a file of each type
	
	public ObservableList<Employee> readFileCSV() throws IOException {
		
		return csv.readFile();
	}
	
	public ObservableList<Employee> readFileBinary() throws ClassNotFoundException, IOException {

		return binary.readFile();
	}

	public ObservableList<Employee> readFileXML() {
	
		return xml.readFile();
	}

	public ObservableList<Employee> readFileJSON() throws ClassNotFoundException, IOException {
	
		return json.readFile();
	}
	
	
	//methods for saving a file of each type
	
	public void saveFileCSV(ObservableList<Employee> employeeList) throws FileNotFoundException {
		
		 csv.saveFile(employeeList);
	}
	
	public void saveFileBinary(ObservableList<Employee> employeeList) throws IOException {
    	
	 	binary.saveFile(employeeList);
	}
	
	public void saveFileXML(ObservableList<Employee> employeeList) throws FileNotFoundException {
    		
	 	xml.saveFile(employeeList);
	}
	
	public void saveFileJSON(ObservableList<Employee> employeeList) throws IOException {
    	
	 	json.saveFile(employeeList);
	}

}
