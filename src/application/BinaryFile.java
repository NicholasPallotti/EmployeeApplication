package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BinaryFile{
	
			//class variable that creates the binary file
			private File file = new File("Employees.data");
			
			BinaryFile(){	
			}
			
			//reads the stored binary file and returns it as an list
			public ObservableList<Employee> readFile() throws IOException, ClassNotFoundException {
				
				//create a list of employees
				ObservableList<Employee> employeesFromFile = FXCollections.observableArrayList();
				
				//create FileInputStream to read in the binary file, and ObjectInputStream to read the obbjects in the file
				FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				
				//boolean thats set to false when no more objects are in the file
				boolean keepReading = true;
				
				//while there are employees to be read from the file
				while(keepReading){
					try {
						
						//create an object from the first object in the file
						Object object = objectInputStream.readObject();
					     
						//if block that checks the employee type of the object casts the object to that type of employee and adds it to EmployeesFromFile
						//or sets keepReading to falls if there are no more objects
						if(object != null) {
						  if (object instanceof SalariedEmployee) {
						        employeesFromFile.add((SalariedEmployee) object);
						  }
						  else if (object instanceof HourlyEmployee) {
						        employeesFromFile.add((HourlyEmployee) object);
						  }
						  else if (object instanceof BasePlusCommissionEmployee) {
						        employeesFromFile.add((BasePlusCommissionEmployee) object);
						  }
						  else if (object instanceof CommissionedEmployee) {
						        employeesFromFile.add((CommissionedEmployee) object);
						  }
						  else
						  {
							  keepReading = false;
						  }
						}			    	  
					}
					catch(EOFException e) {
						keepReading = false;
					}						      
		   		} 
				
				//close the input stream
				objectInputStream.close();
				
				return employeesFromFile;
			}
			
			public void saveFile(ObservableList<Employee> employees) throws IOException {
				
				//create file with FileOutputstream, and use that to create ObjectOutputStream, which will write to the file
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				
				//for every employee in the passed list of Employees write that Employee to the file
				for(int i =0; i < employees.size(); i++) {
					objectOutputStream.writeObject(employees.get(i));				
				}
				objectOutputStream.close();
			}
			
}
